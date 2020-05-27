# DS 212 Homework 2 Python3 Edition
# TJ Liggett

import pandas as pd
import numpy as np

Player = ['Liggett', 'Theis', 'Balliette', 'Kujawa', 'Schwartz', 'Baker', 'Gubbrud', 'Wickersham', 'Hoops', 'Wallace', 'Herding', 'Boike', 'Witter']

#Position: Vector of player positions
Position = ['Will', 'Will', 'Mike', 'Sam', 'Mike', 'Sam', 'Will', 'Sam', 'Will', 'Mike', 'Sam', 'Will', 'Mike']
#Hometown: Hometown of each player
Hometown = ['Rosemount', 'Germantown', 'Bradenton', 'Waukesha', 'Mankato', 'Apple Valley', 'St. Paul', 'Brandon', 'Storm Lake', 'Kansas City', 'Mankato', 'Dawson', 'Miramar']
#State: Vector of the home states of players
State = ['Minnesota', 'Wisconsin', 'Florida', 'Wisconsin', 'Minnesota', 'California', 'Minnesota', 'South Dakota', 'Iowa', 'Missouri', 'Minnesota', 'Minnesota', 'Florida']
#Year: Year of eligibility for each player
Year = ['Sophomore', 'Senior', 'Junior', 'Senior', 'Junior', 'Freshman', 'Junior', 'Sophomore', 'Freshman', 'Redshirt', 'Redshirt', 'Redshirt', 'Sophomore']
#Number: Roster number for each player
Number = [43, 47, 33, 35, 57, 29, 37, 41, 46, 51, 50, 48, 16]


#Using the six vectors, create a dataframe
d = {'Player':Player, 'Position': Position, 'Hometown': Hometown, 'State':State, 'Year':Year, 'Number':Number}
df = pd.DataFrame(d)

#print the dataframe and summary
print(df)
print(df.describe())
