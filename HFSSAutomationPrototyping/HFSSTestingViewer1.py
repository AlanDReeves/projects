import pickle

def displayResults(results: list):
    for result in results:
        print(result)

def sortByAvg():
    pass

def sortByFilename():
    pass

def searchForFilename():
    pass

def searchByX():
    pass

def searchByY():
    pass

def searchByXY():
    pass

def userInterface():
    pass

with open("TEST2", "rb") as inFile:
    results = pickle.load(inFile)
    for entry in results:
        print(entry)