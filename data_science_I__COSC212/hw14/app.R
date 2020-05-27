library(shiny)
library(leaflet)
library(RColorBrewer)
library(RCurl)
library(RJSONIO)
library(jsonlite)

url.LastHr <- "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.geojson"
url.LastDay <- "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_day.geojson"
url.lastMn <- "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_month.geojson"
url.lastWk <- "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_week.geojson"

ui <- bootstrapPage(
  tags$style(type = "text/css", "html, body {width:100%;height:100%}"),
  leafletOutput("map", width = "100%", height = "100%"),
  absolutePanel(top = 10, right = 10,
                sliderInput("range", "Magnitudes", 0.0, max(quakeRecord$mag),
                            value = range(quakeRecord$mag), step = 0.1
                ),
                selectInput("colors", "Color Scheme",
                            rownames(subset(brewer.pal.info, category %in% c("seq", "div")))
                ),
                selectInput("length", "Time Frame",
                            c("Past Hour", "Past Day", "Past Week", "Past 30 Days"), selected = "Past 30 Days"
                ),
                checkboxInput("legend", "Show legend", TRUE)
  )
)

setUrl <- function(set){
  if(set == "Past Hour")
  {
    return(url.LastHr) 
  }
  if(set == "Past Day")
  {
    return(url.LastDay)
  }
  if(set == "Past Week")
  {
    return(url.lastWk)
  }
  return(url.lastMn)
}

server <- function(input, output, session) {
  
  # Reactive expression for the data subsetted to what the user selected
  filteredData <- reactive({
    print(input$length)
    url <- setUrl(input$length)
    print(url)
    quakeData <- jsonlite:: fromJSON(url, flatten = TRUE)
    size <- dim(quakeData$features)[1]
    quakeRecord = data.frame()
    for (i in rep(1:size)) {
      tempCoords <- quakeData$features$geometry.coordinates[[i]]
      record <- data.frame(tempCoords [2] , tempCoords[1], tempCoords[3], quakeData$features$properties.mag[i])
      quakeRecord <- rbind(quakeRecord, record)
      # print(record) 
      }
    colnames(quakeRecord) <- c("lat", "long", "depth", "mag")
    quakeRecord[quakeRecord$mag >= input$range[1] & quakeRecord$mag <= input$range[2],]
  })
  
  # This reactive expression represents the palette function,
  # which changes as the user makes selections in UI.
  colorpal <- reactive({
    colorNumeric(input$colors, c(0,max(quakeRecord$mag)))
  })
  
  output$map <- renderLeaflet({
    # Use leaflet() here, and only include aspects of the map that
    # won't need to change dynamically (at least, not unless the
    # entire map is being torn down and recreated).
    leaflet(quakeRecord) %>% addTiles() %>%
      fitBounds(~min(long), ~min(lat), ~max(long), ~max(lat))
  })
  
  # Incremental changes to the map (in this case, replacing the
  # circles when a new color is chosen) should be performed in
  # an observer. Each independent set of things that can change
  # should be managed in its own observer.
  observe({
    pal <- colorpal()
    
    leafletProxy("map", data = filteredData()) %>%
      clearShapes() %>%
      addCircles(radius = ~10^mag/10, weight = 1, color = "#777777",
                 fillColor = ~pal(mag), fillOpacity = 0.7, popup = ~paste(mag)
      )
  })
  
  # Use a separate observer to recreate the legend as needed.
  observe({
    proxy <- leafletProxy("map", data = quakeRecord)
    
    # Remove any existing legend, and only if the legend is
    # enabled, create a new one.
    proxy %>% clearControls()
    if (input$legend) {
      magnitude <- c(0,max(quakeRecord$mag))
      pal <- colorpal()
      proxy %>% addLegend(position = "bottomright",
                          pal = pal, values = ~magnitude
      )
    }
  })
}

shinyApp(ui, server)