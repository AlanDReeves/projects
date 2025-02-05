from PyQt5.QtWidgets import QApplication, QMainWindow, QWidget, QPushButton, QVBoxLayout
from PyQt5.QtGui import QPalette, QColor

app = QApplication([])

class newMainWindow(QMainWindow):
    def __init__(self, parent = ..., flags = ...):
        super().__init__()
        self.setWindowTitle("Basic GUI")

        button = QPushButton("push here")
        button.setCheckable(True)

        def the_button_was_clicked(self):
            print("Clicked the button")
        
        button.clicked.connect(self.close)

        self.setCentralWidget(button)
        self.setMinimumSize(800, 600)
        button.setLayout(QVBoxLayout())



window = newMainWindow()

window.show()

app.exec()