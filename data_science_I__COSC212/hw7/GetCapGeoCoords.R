library(RDSTK)
library(ggplot2)
library(ggmap)


stateCaps <- read.csv("https://www.codetj.com/caps1.csv", stringsAsFactors = FALSE)

addresses <- stateCaps$Address
print(addresses)
# fixes

addresses[18] <- "150 Martin Luther King Jr Blvd S, Dover, DE 19901"
addresses[21] <- "300 Capitol Ave, Hartford, CT 06106"
addresses[47] <- "120 SW 10th Ave, Topeka, KS 66612"
#addresses[28] <- "101 North Carson Street, Carson City, Nevada"
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

locsLoop <- data.frame(addresses, latitude, longitude)

us <- map_data("state")
dummyDF <- data.frame(state.name, stringsAsFactors=FALSE)
dummyDF$state <- tolower(dummyDF$state.name)

  map.simple <- ggplot(dummyDF, aes(map_id = state))  
  map.simple <- map.simple + geom_map(map = us, fill="white", color="black")
  map.simple <- map.simple + expand_limits(x = us$long, y = us$lat)
  map.simple <- map.simple + coord_map() + ggtitle("Title")
  
  # map.simple <- map.simple + geom_point(data=locsLoop, aes(x = locsLoop$longitude, y = locsLoop$latitude, color="black"))
  map.simple <- map.simple + 
    geom_point(data = locsLoop, aes(x = locsLoop$longitude, y = locsLoop$latitude),  inherit.aes = FALSE)
  
  map.simple
    