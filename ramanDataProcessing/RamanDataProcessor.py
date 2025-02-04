import matplotlib.pyplot as plt
import statistics

coordsList = []

#rip data from txt file
with open('EA Reactive GNP_rawdata_textfile.txt', "r") as file :
    coordsList = file.readlines()
    file.close()

#process coords into 2D array [cm^-1][intensity]
newCoords = []
for i in range(0, len(coordsList)):
    newCoords.append(coordsList[i].split())
    newCoords[i][0] = float(newCoords[i][0])
    newCoords[i][1] = float(newCoords[i][1])

#separates x and y values into discrete lists
x_vals, y_vals = zip(*newCoords)


#change style in future
plt.plot(x_vals, y_vals, marker='o', linestyle='-')


plt.xlabel("cm^-1")
plt.ylabel("intensity")
plt.title("Raman Data")

#plt.show()

#identify peaks
def findPeak(x_vals: list, y_vals: list):
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


#compare peak heights as ratio
#figure out how to identify baseline
#look into FFT - fast fourier transfer (for baseline)
#look into detrend function in SciPy package
def comparePeaks(baseLine, peak1Top, peak2Top):
    peak1Height = peak1Top - baseLine
    peak2Height = peak2Top - baseLine

    return peak1Height / peak2Height


print(findPeak(x_vals, y_vals))