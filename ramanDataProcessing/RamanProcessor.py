import matplotlib.pyplot as plt
import scipy
import scipy.signal

class RamanProcessor:

    x_vals = []
    y_vals = []
    peaksList = [] # list of just the peaks as x/y pairs
    screenSize = int(50)


    def readFile(self, filename: str):
        coordsList = []
        with open(filename, "r") as file:
            coordsList = file.readlines()
            file.close()

        #process coords into 2D array [cm^-1][intensity]
        newCoords = []
        for i in range(0, len(coordsList)):
            newCoords.append(coordsList[i].split())
            newCoords[i][0] = float(newCoords[i][0])
            newCoords[i][1] = float(newCoords[i][1])

        #separates x and y values into discrete lists
        self.x_vals, self.y_vals = zip(*newCoords)
            

    def makePlot(self):
        #change style in future
        plt.plot(self.x_vals, self.y_vals, marker=',', linestyle='-')

        plt.xlabel("cm^-1")
        plt.ylabel("intensity")
        plt.title("Raman Data")

        if (len(self.peaksList) > 0):
            # split peaks into distinct x and y coordinate arrays
            # allows scatter plot to be made for highlighting peaks
            peaksX = []
            peaksY = []
            for element in self.peaksList:
                peaksX.append(element[0])
                peaksY.append(element[1])

            plt.scatter(peaksX, peaksY, color="red", label="peaks", zorder=3)

            for i, peak_index in enumerate(self.peaksList):
                plt.text(peaksX[i], peaksY[i] + 0.05, str(peak_index), 
                fontsize=8, ha='center', color='blue', fontweight='bold')

        plt.show(block=False)

    #detects if the Y value at the index given is greather than the surrounding 2 * screenSize many Y values, if they exist
    def isBigPeak(self, index: int):
        #take y value
        checkVal = self.y_vals[index]

        for i in range(-(self.screenSize), self.screenSize):
            if ((index + i >= 0 and index + i < len(self.y_vals))):
                if (self.y_vals[index + i] > checkVal):
                    return False
        return True

    #identify peaks
    def findPeak(self, x_vals: list, y_vals: list):
        peaksList = []
        newPeak = False

        #if y increases start tracking
        #when y decreases, record previous point as peak

        #may decrease time by sorting Y values

        for i in range(1, len(y_vals)):
            if  y_vals[i-1] < y_vals[i] and self.isBigPeak(i):
                newPeak = True
            if y_vals[i] < y_vals[i-1] and newPeak is True:
                peaksList.append([x_vals[i-1], y_vals[i-1]])
                newPeak = False

        peaksList.sort(key=lambda point: point[1], reverse=True)

        return peaksList

    def comparePeaks(self, peak1Top: float, peak2Top: float):
        return peak1Top / peak2Top
    
    def normalizeYAxis(self):
        baseLineYAxis = scipy.signal.detrend(self.y_vals)
        self.y_vals = baseLineYAxis
        self.peaksList = self.findPeak(self.x_vals, self.y_vals)

    def setScreenSize(self, newSize: int):
        self.screenSize = newSize
        self.peaksList = self.findPeak(x_vals=self.x_vals, y_vals=self.y_vals)

    def splitXandY():
        pass
    
    def __init__(self, filename: str):
        self.x_vals = []
        self.y_vals = []
        self.screenSize = int(50)
        self.readFile(filename)
        self.peaksList = self.findPeak(self.x_vals, self.y_vals)


#TODO: (maybe) export peak data in some way