import csv
import pathlib
import numpy as np
import pickle

#gets simulation parameters to run from a CSV file
#returns a list of the parameters if successful, returns -1 if fail
def getParams(location: str):
    #takes a file name, extracts a list of x and y coordinates to place the IRS in the simulation
    paramsList = []
    try:
        with open(pathlib.Path().cwd() / location, 'r') as file:
            reader = csv.DictReader(file)
            for row in reader:
                x = float(row["x_coord"])
                y = float(row["y_coord"])
                paramsList.append({"x": x, "y": y})
    except :
        return -1
    return paramsList

#starts the HFSS simulation with x and y coordinates given
def runSimulation(x_coord = 0.0, y_coord = 0.0):
    #runs test through API to obtain CSV file, records name
    CSVFile = "Near E Plot 2 Ground.csv"
    return CSVFile


#finds avg value of tested parameters in the HFSS simulation
#returns 0 if there is an error.
def findAvgVal(location: str, X_Range: list, Y_Range: list):
    filtered_data = []
    dataDir = pathlib.Path().cwd() / location

    try:
        with open(dataDir, newline= '') as file:
            reader = csv.DictReader(file)
            #_u,_v,abs(NearPoyntingTotal)[irrad_W_per_m2]
            for row in reader:
                u = float(row["_u"])
                v = float(row["_v"])
                if X_Range[0] < u < X_Range[1] and Y_Range[0] < v < Y_Range[1]:
                    filtered_data.append(row)
        
            nearE_Totals = []
            for data in filtered_data:
                nearE_Totals.append(float(data["dB(NearETotal)"]))
        
            nearE_Average = np.average(nearE_Totals)
    
    except:
        nearE_Average = 0
        
    return nearE_Average
    
# writes contents of the list given as a pickle file
# passes if unable to write file.    
def resultsToPickle(desiredFileName: str, resultsList: list):
    try:
        with open(desiredFileName, "wb") as dumpFile:
            pickle.dump(resultsList, dumpFile)

    except:
        pass

#writes the contents of resultsList to a CSV file. 
#attempts to call resultsToPickle if unable to write file.
def resultsToCSV(desiredFileName: str, resultsList: list):
    try:
        with open(desiredFileName, 'w', newline='') as csvfile:
                writer = csv.writer(csvfile, delimiter=",", quotechar=' ')

                writer.writerow(["Avg ", "Parameters ", "Filename "])
                for entry in resultsList:
                    writer.writerow([entry['Avg'], entry['Parameters'], entry['File Name']])
    except:
        resultsToPickle(desiredFileName, resultsList)

#basic text UI that runs on terminal. Asks for X and Y ranges to check, input file name, and output file name
#returns a list containing the above
def userInterface():
    #TODO error handling
    userCheck = "n"
    UIOutput = []
    stepComplete = False

    while userCheck == "n":
        print("X and Y ranges to check for poynting in simulation:")
        while stepComplete is False:
            try:
                minX = float(input("enter minimum X value "))
                stepComplete = True
            except:
                print("please input a number")
        UIOutput.append(minX)
        stepComplete = False

        while stepComplete is False:
            try:
                maxX = float(input("enter maximum X value "))
                stepComplete = True
            except:
                print("please input a number")
        UIOutput.append(maxX)
        stepComplete = False

        while stepComplete is False:
            try:
                minY = float(input("enter minimum Y value "))
                stepComplete = True
            except:
                print("please input a number")
        UIOutput.append(minY)
        stepComplete = False

        while stepComplete is False:
            try:
                maxY = float(input("enter maximum Y value "))
                stepComplete = True
            except:
                print("please input a number")
        UIOutput.append(maxY)
        stepComplete = False

        inputFileName = input("Enter name of parameter enter file ")
        UIOutput.append(inputFileName)

        outputFileName = input("Enter desired output file name ")
        UIOutput.append(outputFileName)

        print("inputs were:")
        print(f"X range: {minX} - {maxX}")
        print(f"Y range: {minY} - {maxY}")
        print(f"Input file name: {inputFileName}")
        print(f"Output file name: {outputFileName}")
        print("Is this ok? Enter n to re-enter data")

        userCheck = input()

    return UIOutput


#TODO: implement runSimulation(), BAYESIAN optimization, make clean quit function, 
# make means of choosing design file

############    main function begins below    ################
def main():
    resultsList = []
    userInput = []
    paramsList = -1

    #TODO error handling done with print statement. Redo for GUI
    while type(paramsList) is int:
        userInput = userInterface()
        inputFileName = userInput[4]
        paramsList = getParams(inputFileName)
        if type(paramsList) is int:
            print("Could not find file. Please try again.")

    X_Range = [userInput[0], userInput[1]]
    Y_Range = [userInput[2], userInput[3]]

    desiredFileName = userInput[5]

    if type(paramsList) is list:
        for coords in paramsList:
            x = coords["x"]
            y = coords["y"]

            parameters = str(x) + " " + str(y)

            fileLocation = runSimulation(x, y)
            resultVal = findAvgVal(fileLocation, X_Range, Y_Range)

            resultsList.append({"Avg" : resultVal, "Parameters": parameters, "File Name" : fileLocation})

    resultsToCSV(desiredFileName + ".csv", resultsList)
    resultsToPickle(desiredFileName + ".pickle", resultsList)