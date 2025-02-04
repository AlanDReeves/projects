from testResult import testResult

class ScoreHolder:
    def __init__(self):
        self.start = None
        self.tail = None

    @staticmethod
    def scoreCSV(filePath: str):
        pass
    
    def addResult(self, newRec: testResult):
        #always adds the new record to the end of the list
        if self.start == None:
            self.start = newRec
            self.tail = newRec
        else:
            cursor = self.start
            while (cursor.next != None):
                cursor = cursor.next
            cursor.next = newRec
            self.tail = newRec
        
    def printList(self):
        cursor = self.start
        while (cursor != None):
            print(cursor.getSNRScore())
            cursor = cursor.next

    def getTail(self):
        return self.tail
    
    ##can implement sorting when exact parameters are known