import matplotlib.pyplot as plt

# specifications:
# take cyclic voltammetry curve data from .txt file
# accept current or voltage number from user
# return expected hydrogen production per second at that current/voltage
# create graph representing hydrogen per amp/volt

# TODO: find inflection point in graph and disregard all points on the wrong side

class productionCalculator:

    voltages: list
    currents: list
    hydrogenPerAmp = 1.04e-5 # remains constant

    def readFile(self, filename: str):
            coordsList = []
            with open(filename, "r") as file:
                coordsList = file.readlines()
                file.close()

            #process coords into 2D array [cm^-1][intensity]
            newCoords = []
            for i in range(2, len(coordsList)):
                newCoords.append(coordsList[i].split())
                newCoords[i][0] = float(newCoords[i][0])
                newCoords[i][1] = float(newCoords[i][1])

            #separates x and y values into discrete lists
            self.voltages, self.currents = zip(*newCoords)

    def calcHydrogenPerAmp(self, amps: float):
        return amps * self.hydrogenPerAmp
    
    def calcHydrogenPerVolt(self, volts):
        # find closest voltage in voltages list
            # find first index with voltage greater than desired
            # return one index before that
        for i in range (0, len(self.voltages)):
             if self.voltages[i] > volts:
                  break
        i -= 1 # this is the index of the largest voltage observed which is not greater than desired

        # find corresponding amps
        resultAmps = self.currents[i]
        # return amps * rate
        return self.calcHydrogenPerAmp(self, resultAmps)
    
    def graphHydrogenPerAmp(self):
        """Graphs hydrogen produced per second relative to current"""
        hydrogenRate = []
        for element in self.currents:
            hydrogenRate.append(self.calcHydrogenPerAmp(element))

        plt.plot(self.currents, hydrogenRate, color="red")
        plt.xlabel("Amps")
        plt.ylabel("g H per second")
        plt.show(block=False)

        
    
    def graphHydrogenPerVolt(self):
        """Graphs hydrogen produced per second relative to voltage"""
        hydrogenRate = []
        for element in self.voltages:
            hydrogenRate.append(self.calcHydrogenPerVolt(element))

        plt.plot(self.voltages, hydrogenRate, color="blue")
        plt.xlabel("Volts")
        plt.ylabel("g H per second")
        plt.show(block=False)
    

