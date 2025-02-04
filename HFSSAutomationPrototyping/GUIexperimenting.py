from tkinter import *

root = Tk()

root.title("Testing GUI")
root.geometry('600x400')

menu = Menu(root)
item = Menu(menu)
item.add_command(label="New")
menu.add_cascade(label="File", menu=item)
root.config(menu=menu)

labL = Label(root, font=("Arial", 16, "bold"), text= "GUI testing")
labL.grid(column=0, row=0)

lab2 = Label(root, font=("Arial", 12), text= "")
lab2.grid(column=0, row=1)

txt = Entry(root, width=10)
txt.grid(column=1, row=0)


def clicked():
    res = "You wrote " + txt.get()
    labL.configure(text = res)

def button2():
    lab2.configure(text="You pressed button 2")



btn = Button(root, text= "click here", fg = "red", command=clicked)
btn.grid(column=2, row=0)

btn2 = Button(root, text= "button 2", fg = "blue", command=button2)
btn2.grid(column = 2, row = 2)


root.mainloop()

