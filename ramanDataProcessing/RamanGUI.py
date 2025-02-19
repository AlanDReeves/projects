from PyQt5.QtWidgets import QApplication, QMainWindow, QWidget, QPushButton, QVBoxLayout, QFileDialog, QListWidget, QSpinBox, QLabel, QSlider
from PyQt5.QtGui import QPalette, QColor
from PyQt5.QtCore import Qt
import pyqtgraph as pg
from RamanProcessor import RamanProcessor

app = QApplication([])

class newMainWindow(QMainWindow):
    #contains constructor and effectively is the main function
    def __init__(self, parent = ..., flags = ...):
        # set up
        super().__init__()
        self.setWindowTitle("Raman GUI")
        layout = QVBoxLayout()
        self.setMinimumSize(800, 600)

        self.fileSelected = False
        self.ramanFile: str = ''
        self.backend: RamanProcessor = None

        #internal devices
        quitButton = QPushButton("Close Program")
        quitButton.setCheckable(True)
        layout.addWidget(quitButton)

        fileSelectorButton = QPushButton("Select A File")
        fileSelectorButton.setCheckable(True)
        layout.addWidget(fileSelectorButton)

        makePyPlotButton = QPushButton("Make PyPlot")
        makePyPlotButton.setCheckable(True)
        layout.addWidget(makePyPlotButton)

        normalizerButton = QPushButton("Normalize Data")
        normalizerButton.setCheckable(True)
        layout.addWidget(normalizerButton)

        self.screenSlider = QSlider(Qt.Horizontal)
        self.screenSlider.setMinimum(20)
        self.screenSlider.setMaximum(250)
        layout.addWidget(self.screenSlider)
    
        sliderLabel = QLabel("Peak Detection Sensitivity Setting: " + str(self.screenSlider.value()))
        layout.addWidget(sliderLabel)

        sliderExplanation = QLabel("Smaller peak sensitivity is MORE sensitive. Default is 50")
        layout.addWidget(sliderExplanation)

        peakList = QListWidget()
        peakList.addItem("Peaks List")
        peakList.addItem("X val, Y val")
        peakList.addItem("Select a file to see values")
        layout.addWidget(peakList)

        self.peakSelectorA = QSpinBox()
        self.peakSelectorA.setPrefix("Peak Number: ")
        self.peakSelectorA.setMinimum(0)
        self.peakSelectorA.setMaximum(10)
        layout.addWidget(self.peakSelectorA)
        
        self.peakSelectorB = QSpinBox()
        self.peakSelectorB.setPrefix("Peak Number: ")
        self.peakSelectorB.setMinimum(0)
        self.peakSelectorB.setMaximum(10)
        layout.addWidget(self.peakSelectorB)

        self.ratioLabel = QLabel("Ratio of peak A to peak B: ")
        layout.addWidget(self.ratioLabel)

        #button functions

        #updates list to show recent changes
        def setNewPeaks(self):
            peakList.clear()
            peakList.addItem("Peaks List")
            peakList.addItem("X val, Y val")
            i = int(0)
            for element in self.backend.peaksList:
                peakList.addItem("Peak " + str(i) + ": " + str(element[0]) + ', ' + str(element[1]))
                i += 1
            self.peakSelectorA.setMaximum(len(self.backend.peaksList) - 1)
            self.peakSelectorB.setMaximum(len(self.backend.peaksList) - 1)


        def openFileBrowser():
            #selects a file, creates a RamanProcessor object, adds peaks found to list, and limits peak selector boxes
            options = QFileDialog.Options()
            filePath, _ = QFileDialog.getOpenFileName(self, "Select a File", "", "All Files (*);;Text Files (*.txt)", options=options)
            self.ramanFile = filePath
            self.fileSelected = True
            self.backend = RamanProcessor(self.ramanFile)
            setNewPeaks(self)

        def makePyPlot():
            if (self.fileSelected == True):
                self.backend.makePlot()

        def comparepeaks():
            if (self.fileSelected == True):
                peak1Val = self.backend.peaksList[int(self.peakSelectorA.value())][1]
                peak2Val = self.backend.peaksList[int(self.peakSelectorB.value())][1]
                ratio = self.backend.comparePeaks(peak1Val, peak2Val)
                self.ratioLabel.setText("Ratio of peak A to peak B: " + str(ratio))
            else:
                pass

        def normalizeData():
            if (self.fileSelected == True):
                self.backend.normalizeYAxis()
                setNewPeaks(self)

        def changeScreenSize():
            if (self.fileSelected == True):
                self.backend.setScreenSize(self.screenSlider.value())
                sliderLabel.setText("Peak Detection Sensitivity Setting: " + str(self.screenSlider.value()))
                self.backend.findPeak(x_vals=self.backend.x_vals, y_vals=self.backend.y_vals)
                setNewPeaks(self)
        
        # connecting buttons to functions
        quitButton.clicked.connect(self.close)
        fileSelectorButton.clicked.connect(openFileBrowser)
        makePyPlotButton.clicked.connect(makePyPlot)
        self.peakSelectorA.valueChanged.connect(comparepeaks)
        self.peakSelectorB.valueChanged.connect(comparepeaks)
        normalizerButton.clicked.connect(normalizeData)
        self.screenSlider.valueChanged.connect(changeScreenSize)

        #setting "widget", which holds all other devices, as the central widget
        holderWidget = QWidget()
        holderWidget.setLayout(layout)

        self.setCentralWidget(holderWidget)



window = newMainWindow()
window.show()
app.exec()

# TODO: Add something to display the plot inside GUI using pyqtgraph