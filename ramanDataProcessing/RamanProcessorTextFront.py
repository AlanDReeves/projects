from RamanProcessor import RamanProcessor

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
    print("q - quit")
    return input()

#select file w/small GUI
def selectFile():
    pass

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
        print("Peak {index} - ".format(index = i), end="")
        print(backend.peaksList[i])

def normalizeYAxis(backend: RamanProcessor):
    backend.normalizeYAxis()


#main()
fileAddress = selectFile()

fileAddress = "ramanDataProcessing\EA Reactive GNP_rawdata_textfile.txt"

backend = RamanProcessor(fileAddress)