import matplotlib.pyplot as plt
import statistics
import numpy as np
import scipy
import scipy.signal

class RamanProcessor:

    x_vals = []
    y_vals = []
    peaksList = [] # list of just the peaks as x/y pairs


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
        plt.plot(self.x_vals, self.y_vals, marker='o', linestyle='-')

        plt.xlabel("cm^-1")
        plt.ylabel("intensity")
        plt.title("Raman Data")

        plt.show(block=True)
        #Does not show graph if I put block to false?

    #identify peaks
    def findPeak(self, x_vals: list, y_vals: list):
        averageHeight = statistics.mean(y_vals)
        peaksList = []
        newPeak = False

        #if y goes above double average, start tracking
        #when y decreases, record previous point as peak

        for i in range(1, len(y_vals)):
            if y_vals[i] > (2 * averageHeight) and y_vals[i-1] < y_vals[i]:
                newPeak = True
            if y_vals[i] < y_vals[i-1] and newPeak is True:
                peaksList.append([x_vals[i-1], y_vals[i-1]])
                newPeak = False

        return peaksList

    def comparePeaks(self, peak1Top: float, peak2Top: float):
        return peak1Top / peak2Top
    

    def normalizeYAxis(self):
        baseLineYAxis = scipy.signal.detrend(self.y_vals)
        self.y_vals = baseLineYAxis
    
    def __init__(self, filename: str):
        self.x_vals = []
        self.y_vals = []
        self.readFile(filename)
        self.peaksList = self.findPeak(self.x_vals, self.y_vals)


