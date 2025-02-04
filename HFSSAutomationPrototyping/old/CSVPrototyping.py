import pandas as pd

filename = "Near Poynting Plot 1.csv"
columns = ['x', 'y', 'poynting']
poynting = []
df = pd.read_csv(filename, usecols=columns)
print(df)


#open file
#add all poynting values to a list
#find average of the list
#return the average