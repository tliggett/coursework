# DS 212 Homework 2 by TJ Liggett

# Create six vectors reprensenting six characteristics

#Player: Vector of the names of players
Player <- c('Liggett', 'Theis', 'Balliette', 'Kujawa', 'Schwartz', 'Baker', 'Gubbrud', 'Wickersham', 'Hoops', 'Wallace', 'Herding', 'Boike', 'Witter')
#Position: Vector of player positions
Position <- c('Will', 'Will', 'Mike', 'Sam', 'Mike', 'Sam', 'Will', 'Sam', 'Will', 'Mike', 'Sam', 'Will', 'Mike')
#Hometown: Hometown of each player
Hometown <- c('Rosemount', 'Germantown', 'Bradenton', 'Waukesha', 'Mankato', 'Apple Valley', 'St. Paul', 'Brandon', 'Storm Lake', 'Kansas City', 'Mankato', 'Dawson', 'Miramar')
#State: Vector of the home states of players
State <- c('Minnesota', 'Wisconsin', 'Florida', 'Wisconsin', 'Minnesota', 'California', 'Minnesota', 'South Dakota', 'Iowa', 'Missouri', 'Minnesota', 'Minnesota', 'Florida')
#Year: Year of eligibility for each player
Year <- c('Sophomore', 'Senior', 'Junior', 'Senior', 'Junior', 'Freshman', 'Junior', 'Sophomore', 'Freshman', 'Redshirt', 'Redshirt', 'Redshirt', 'Sophomore')
#Number: Roster number for each player
Number <- c(43, 47, 33, 35, 57, 29, 37, 41, 46, 51, 50, 48, 16)

#Using the six vectors, create a dataframe
wolfPack <- data.frame(Player, Position, Hometown, State, Year, Number)

#print the dataframe and summary
wolfPack
summary(wolfPack)

