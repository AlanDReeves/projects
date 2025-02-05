from PyQt5.QtWidgets import QApplication, QWidget, QPushButton, QMainWindow

app = QApplication([])

class newMainWindow(QMainWindow):
    def __init__(self, parent = ..., flags = ...):
        super().__init__()
        self.setWindowTitle("Basic GUI")

        button = QPushButton("push here")

        self.setCentralWidget(button)
        self.setMinimumSize(800, 600)

window = newMainWindow()

window.show()

app.exec()