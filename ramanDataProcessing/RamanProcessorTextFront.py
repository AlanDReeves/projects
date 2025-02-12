from RamanProcessor import RamanProcessor
from tkinter import filedialog as fd

#funcs list:
#print menu
    #take input
def printMenu():
    print("Select an option:")
    print("1 - Select file and analyze")
    print("2 - Show data plot")
    print("3 - List peaks")
    print("4 - Compare peaks")
    print("5 - Normalize data on Y axis")
    print("6 - Change the screen size for detecting peaks")
    print("q - quit")
    return input()

#select file w/small GUI
def selectFile():
    filetypes = (
        ('txt files', '*.txt'),
        ('All files', '*.*')
    )
    filename = fd.askopenfilename(
        title='Open a file',
        initialdir='/',
        filetypes= filetypes)
    return filename

def makePlot(backend: RamanProcessor):
    backend.makePlot()

def comparePeaks(backend: RamanProcessor):
    print("The peak ratio will be returned in ratio of peak A to peak B")
    peakNumA = int(input("Input peak number from list of peak A: "))
    peakNumB = int(input("Input peak number from list of peak B: "))
    ratio = backend.comparePeaks(backend.peaksList[peakNumA][1], backend.peaksList[peakNumB][1])
    print("The ratio of peak A to B is : {r}".format(r = ratio))
    #make sure to restrict number of decimals in ratio


def listPeaks(backend: RamanProcessor):
    print("Peak number - [X, Y]")
    for i in range(0, len(backend.peaksList)):
        if (i < len(backend.peaksList)):
            print("Peak {index} - ".format(index = i), end="")
            print(backend.peaksList[i])

def normalizeYAxis(backend: RamanProcessor):
    backend.normalizeYAxis()


# main function
userInput = '-1'
backend = None
fileSelected = False

while (userInput != 'q'):
    userInput = printMenu()
#take input, give to a switch statement
    match userInput:
        case "1":
            filename = selectFile()
            try:
                backend = RamanProcessor(filename)
                fileSelected = True
            except Exception as e:
                print(e)
                print("Unable to read file. Is this the right file type?")

        case "2":
            if fileSelected == True:    
                makePlot(backend)
            else:
                print("No file selected yet")

        case "3":
            if fileSelected == True:
                listPeaks(backend)
            else:
                print("No file selected yet")
            
        case "4":
            if fileSelected == True:
                comparePeaks(backend)
            else:
                print("No file selected yet")
            
        case "5":
            if fileSelected == True:
                normalizeYAxis(backend)
            else:
                print("No file selected yet")

        case "6":
            if (fileSelected == True):
                print("A larger screen size will produce fewer peaks, while a smaller size will produce more peaks.")
                print("The default screen size is 50")
                try:
                    newScreen = int(input("Please enter the new screen size: "))
                    backend.setScreenSize(newScreen)
                except Exception as e:
                    print(e)
            else:
                print("No file selected yet")
            
        case _:
            if userInput == 'q':
                break
            else:
                print("Input not understood. Please enter something else.")
                print()
