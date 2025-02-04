
class testResult:

    #add something to store initial parameters given to HFSS

    def __init__(self, avgPynting: float, fileAddress: str, next = None, previous = None):
        self.avgPynting = avgPynting
        self.touchStoneAddrs = fileAddress
        self.next = next
        self.previous = previous

    def getAvgPynting(self):
        return self.avgPynting
    
    def getAddrs(self):
        return self.touchStoneAddrs
    
    def setNext(self, next):
        self.next = next
    
    def getNext(self):
        return self.next
    
    def setPrevious(self, previous):
        self.previous = previous

    def getPrevious(self):
        return self.previous