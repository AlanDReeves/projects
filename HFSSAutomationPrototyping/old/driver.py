from testResult import testResult
from scoreHolder import ScoreHolder

dummy = testResult(8.0, 22, 45, "asfjkl")
print("printing data for dummy testResult object")
print(dummy.getAddrs(), " address")
print(dummy.getCapScore(), " CapScore")
print(dummy.getchanScore(), " chanScore")
print(dummy.getSNRScore(), " SNR score")

holder = ScoreHolder()

print("adding test data to scoreHolder")
for x in range(1, 6):
    holder.addResult(testResult(0, 0, x, ""))

print("printing list contents")
holder.printList()