import csv
import pathlib
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from bayes_opt import BayesianOptimization
from bayes_opt.logger import JSONLogger
from bayes_opt.event import Events
from bayes_opt.util import load_logs



class holder:
    def __init__(self, X_RANGE: list, Y_RANGE: list, userInput: list):
        self.X_RANGE = X_RANGE
        self.Y_RANGE = Y_RANGE
        pbounds = {'x': (userInput[5], userInput[6]), 'y': (userInput[7], userInput[8])}
        filename = userInput[4]
        self.optimizer = BayesianOptimization(
            f = self.black_box,
            pbounds= pbounds,
            verbose = 2,
            random_state= 1
        )
        try:
            load_logs(optimizer= self.optimizer, logs=filename + ".log")
        except:
            pass
        self.logger = JSONLogger(path=filename + ".log")
        self.optimizer.subscribe(Events.OPTIMIZATION_STEP, self.logger)
    
    def runSimulation(self, x_coord = 0.0, y_coord = 0.0):
        CSVFile = "Near E Plot 2 Ground.csv"
        return CSVFile
    
    def findAvgVal(self, location: str):
        filtered_data = []
        dataDir = pathlib.Path().cwd() / location

        try:
            with open(dataDir, newline= '') as file:
                reader = csv.DictReader(file)
                #_u,_v,abs(NearPoyntingTotal)[irrad_W_per_m2]
                for row in reader:
                    u = float(row["_u"])
                    v = float(row["_v"])
                    if self.X_RANGE[0] < u < self.X_RANGE[1] and self.Y_RANGE[0] < v < self.Y_RANGE[1]:
                        filtered_data.append(row)
        
                nearE_Totals = []
                for data in filtered_data:
                    nearE_Totals.append(float(data["dB(NearETotal)"]))
        
                nearE_Average = np.average(nearE_Totals)
    
        except:
            nearE_Average = 0
        
        return nearE_Average

    def black_box(self, x, y):
        filename = runSimulation(x, y)
        return self.findAvgVal(filename)
    
    def optimize(self, initial, iterations):
        self.optimizer.maximize(
        init_points=initial,
        n_iter=iterations)

        return self.optimizer.max
    

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

#writes the contents of resultsList to a CSV file. 
#attempts to call resultsToPickle if unable to write file.
def resultsToCSV(desiredFileName: str, resultsList: list):
    try:
        with open(desiredFileName, 'w', newline='') as csvfile:
                writer = csv.writer(csvfile, delimiter=",", quotechar=' ')

                writer.writerow(["Avg ", "RIS coordinates (X, Y) ", "Filename "])
                for entry in resultsList:
                    writer.writerow([entry['Avg'], entry['Parameters'], entry['File Name']])
    except:
        pass

def visualizeData(filename: str, X_Range: list, Y_Range: list):
    x_coords = []
    y_coords = []
    vals = []

    #read original csv file
    #take only coordinates that match desired range and add them to respective lists containing those values
    with open(filename, 'r') as file:
        reader = csv.DictReader(file)
        for row in reader:
            x = float(row["_u"])
            y = float(row["_v"])
            val = float(row['dB(NearETotal)'])

            if X_Range[0] < x < X_Range[1] and Y_Range[0] < y < Y_Range[1]:
                x_coords.append(x)
                y_coords.append(y)
                vals.append(val)


    data = {}  
    data['x'] = []
    data['y'] = []
    data['vals'] = []

    for value in x_coords:
        data['x'].append(value)
    for value in y_coords:
        data['y'].append(value)
    for value in vals:
        data['vals'].append(value)

    # Convert to DataFrame
    df = pd.DataFrame(data)

    # Reshape the data for pcolormesh
    x = df['x'].unique()
    y = df['y'].unique()

    # Create a 2D array for intensity values
    intensity_grid = df.pivot(index='y', columns='x', values='vals').values

    # Plot using pcolormesh
    plt.figure(figsize=(6, 5))
    plt.pcolormesh(x, y, intensity_grid, shading='auto', cmap='viridis')
    plt.colorbar(label='Near E Field (dB)')
    plt.xlabel('X Coordinate')
    plt.ylabel('Y Coordinate')
    plt.title(filename)
    plt.show(block=False)


#TODO: impliment runSimulation(), make clean quit function, add way of changing testing goal parameter,
# make means of choosing design file (stretch)

def run(userInput: list):
    #userInput format: 0 - Min X, 1 - Max X, 2 - Min Y, 3 - Max Y, 4 - output file name, 
    #5 - ris min X, 6 - ris max X, 7 - ris min Y, 8 - ris max Y
    #9 - desired number of initial tests, 10 - desired iterations

    X_Range = [userInput[0], userInput[1]]
    Y_Range = [userInput[2], userInput[3]]

    varHolder = holder(X_Range,Y_Range, userInput)

    varHolder.optimize(userInput[9], userInput[10])
    #results represented by JSON file


