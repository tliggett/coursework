library(leaflet)
leaflet() %>%
  setView(-93.09451517007155, 44.7710229358209, zoom = 16) %>% 
  addTiles() %>%
  addMarkers(-93.09451517007155, 44.7710229358209, popup = "TJ's home") 
