from tkinter import *
from tkinter import filedialog as fd
import HFSSAutoBayes as backEnd

root = Tk()

root.title("HFSS Auto Testing v1")
root.geometry('600x400')

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

#start of RIS placement labels

RISLimits = Label(root, font=("Arial", 12), text= "RIS Placement Boundaries")
RISLimits.grid(column = 0, row = 5)

risMinXLab = Label(root, font=("Arial", 10), text= "Minimum X value")
risMinXLab.grid(column = 0, row = 6)

risMinXEnter = Entry(root, width = 20)
risMinXEnter.grid(column = 1, row = 6)

risMaxXLab = Label(root, font=("Arial", 10), text= "Maximum X value")
risMaxXLab.grid(column = 0, row = 7)

risMaxXEnter = Entry(root, width = 20)
risMaxXEnter.grid(column = 1, row = 7)

risMinYLab = Label(root, font=("Arial", 10), text= "Minimum Y value")
risMinYLab.grid(column = 0, row = 8)

risMinYEnter = Entry(root, width = 20)
risMinYEnter.grid(column = 1, row = 8)

risMaxYLab = Label(root, font=("Arial", 10), text= "Maximum Y value")
risMaxYLab.grid(column = 0, row = 9)

risMaxYEnter = Entry(root, width = 20)
risMaxYEnter.grid(column = 1, row = 9)

#bayesian inital and desired iterations

BayesLab = Label(root, font=("Arial", 12), text= "Bayesian Iteration Parameters")
BayesLab.grid(column = 0, row= 10)

innitialLab = Label(root, font=("Arial", 10), text= "Number of random iterations to start")
innitialLab.grid(column = 0, row= 11)
innitialEnter = Entry(root, width= 20)
innitialEnter.grid(column = 1, row = 11)

iterationsLab = Label(root, font=("Arial", 10), text= "Number of Bayesian optimization iterations")
iterationsLab.grid(column = 0, row = 12)
iterationsEnter = Entry(root, width = 20)
iterationsEnter.grid(column = 1, row = 12)


#end of coordinate labels and entries

spacer = Label(root, text = "")
spacer.grid(column = 0, row = 13)

#file name input labels and entries

outputFileLab = Label(root, font=("Arial", 12), text = "Desired name of output file")
outputFileLab.grid(column = 0, row = 14)

sublabel = Label(root, font=("Arial", 8), text = "(do not include a file extension such as .csv)")
sublabel.grid(column = 0, row = 15)

outputFileEnter = Entry(root, width = 25)
outputFileEnter.grid(column = 1, row = 16)

#buttons
def pushStart():
    GUIOutput = []
    try:
        GUIOutput.append(float(minXEnter.get()))
        GUIOutput.append(float(maxXEnter.get()))
        GUIOutput.append(float(minYEnter.get()))
        GUIOutput.append(float(maxYEnter.get()))
        #input file button removed in this version.
        GUIOutput.append(outputFileEnter.get())
        GUIOutput.append(float(risMinXEnter.get()))
        GUIOutput.append(float(risMaxXEnter.get()))
        GUIOutput.append(float(risMinYEnter.get()))
        GUIOutput.append(float(risMaxYEnter.get()))
        GUIOutput.append(int(innitialEnter.get()))
        GUIOutput.append(int(iterationsEnter.get()))
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