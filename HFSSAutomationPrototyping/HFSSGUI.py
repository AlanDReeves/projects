from tkinter import *
from tkinter import filedialog as fd
import HFSSAutowGUI as backEnd

root = Tk()

root.title("HFSS Auto Testing v1")
root.geometry('600x300')

#coordinate labels and entries
coordLabel = Label(root, font=("Arial", 12), text= "User Defined Work Area Coordinate Ranges")
coordLabel.grid(column = 0, row = 0)

minXLabel = Label(root, font=("Arial", 10), text= "Minimum X value")
minXLabel.grid(column = 0, row = 1)

minXEnter = Entry(root, width = 20)
minXEnter.grid(column = 1, row = 1)

maxXLabel = Label(root, font=("Arial", 10), text= "Maximum X value")
maxXLabel.grid(column = 0, row = 2)

maxXEnter = Entry(root, width = 20)
maxXEnter.grid(column = 1, row = 2)

minYLabel = Label(root, font=("Arial", 10), text= "Minimum Y value")
minYLabel.grid(column = 0, row = 3)

minYEnter = Entry(root, width = 20)
minYEnter.grid(column = 1, row = 3)

maxYLabel = Label(root, font=("Arial", 10), text= "Maximum Y value")
maxYLabel.grid(column = 0, row = 4)

maxYEnter = Entry(root, width = 20)
maxYEnter.grid(column = 1, row = 4)

#end of coordinate labels and entries

spacer = Label(root, text = "")
spacer.grid(column = 0, row = 5)

#file name input labels and entries

inputFileLab = Label(root, font=("Arial", 12), text = "Name of testing parameter file")
inputFileLab.grid(column = 0, row = 6)

inputFileEnter = Entry(root, width = 25)
inputFileEnter.grid(column = 1, row = 6)

outputFileLab = Label(root, font=("Arial", 12), text = "Desired name of output file")
outputFileLab.grid(column = 0, row = 7)

sublabel = Label(root, font=("Arial", 8), text = "(do not include a file extension such as .csv)")
sublabel.grid(column = 0, row = 8)

outputFileEnter = Entry(root, width = 25)
outputFileEnter.grid(column = 1, row = 7)

#buttons
def pushStart():
    GUIOutput = []
    try:
        GUIOutput.append(float(minXEnter.get()))
        GUIOutput.append(float(maxXEnter.get()))
        GUIOutput.append(float(minYEnter.get()))
        GUIOutput.append(float(maxYEnter.get()))
        GUIOutput.append(inputFileEnter.get())
        GUIOutput.append(outputFileEnter.get())
    except:
        return

    #now feed this to back end
    backEnd.run(GUIOutput)

    pushQuit()


goButton = Button(root, text = "Start test", command = pushStart)
goButton.grid(column = 3, row = 1)

def pushQuit():
    root.destroy()
quitButton = Button(root, text = "Quit", command = pushQuit)
quitButton.grid(column = 3, row = 2)

def visData():
    X_Range = []
    Y_Range = []
    try:
        X_Range.append(float(minXEnter.get()))
        X_Range.append(float(maxXEnter.get()))
        Y_Range.append(float(minYEnter.get()))
        Y_Range.append(float(maxYEnter.get()))
    except:
        return

    filetypes = (
        ('csv files', '*.csv'),
        ('All files', '*.*')
    )

    filename = fd.askopenfilename(
        title='Open a file',
        initialdir='/',
        filetypes=filetypes)
    
    backEnd.visualizeData(filename, X_Range, Y_Range)

dataVisButton = Button(root, text = "Visualize Old Data", command = visData)
dataVisButton.grid(column=3, row = 3)


# main
root.mainloop()