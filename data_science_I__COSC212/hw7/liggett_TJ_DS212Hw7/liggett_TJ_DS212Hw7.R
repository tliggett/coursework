# COSC 212 Homework 7
# TJ Liggett

library(RDSTK)
library(ggplot2)
library(ggmap)

# Reading in the csv from my web space
# Data was found here: https://8ballride.com/?page_id=500
# Was going to load in directly, but got lost in googlesheets api.
stateCaps <- read.csv("https://www.codetj.com/caps1.csv", stringsAsFactors = FALSE)


addresses <- stateCaps$Address
print(addresses)

# fixes for state capitols
addresses[18] <- "150 Martin Luther King Jr Blvd S, Dover, DE 19901"
addresses[21] <- "300 Capitol Ave, Hartford, CT 06106"
addresses[47] <- "120 SW 10th Ave, Topeka, KS 66612"
latitude <- rep(0.0, 48)
longitude <- rep(0.0, 48)

# Loop method (helpful for debugging addresses)
for (indx in c(1:48)) {
  print(indx)
  print(addresses[indx])
  loc <- street2coordinates(addresses[indx])
  print(loc)
  latitude[indx] <- loc$latitude
  longitude[indx] <- loc$longitude
}

# My location data frame
locsLoop <- data.frame(addresses, latitude, longitude)

# Code from maps example
us <- map_data("state")
dummyDF <- data.frame(state.name, stringsAsFactors=FALSE)
dummyDF$state <- tolower(dummyDF$state.name)

# Code from maps example
map.simple <- ggplot(dummyDF, aes(map_id = state))  
map.simple <- map.simple + geom_map(map = us, fill="white", color="black")
map.simple <- map.simple + expand_limits(x = us$long, y = us$lat)
map.simple <- map.simple + coord_map() + ggtitle("basic map of continental USA")

# My beautiful map
map.simple <- map.simple + geom_point(data=locsLoop, aes(x = locsLoop$longitude, y = locsLoop$latitude, color="black"))
map.simple
