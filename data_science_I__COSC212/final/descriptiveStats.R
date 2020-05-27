#
# From Chapter 9 in our Text
# Read in the census data set
# ---------------------------
readCensus <- function() {
  urlToRead <-"http://www2.census.gov/programs-surveys/popest/tables/2010-2011/state/totals/nst-est2011-01.csv"
  
  #read the data from the web
  testFrame <- read.csv(url(urlToRead))
  
  #remove the first 8 rows (‘header information’)
  testFrame<-testFrame[-1:-8,]
  
  #only keep the first 5 columns
  testFrame<-testFrame[,1:5]
  
  #rename the first column
  testFrame$stateName <- testFrame[,1]
  testFrame<-testFrame[,-1]
  
  #remove the last rows (tail info)
  testFrame<-testFrame[-52:-58,]
  
  #remove the ‘dot’ from the state name
  testFrame$stateName <- gsub("\\.","", testFrame$stateName)
  
  #convert the columns to actual numbers and rename columns
  testFrame$april10census <-Numberize(testFrame$X)
  testFrame$april10base <-Numberize(testFrame$X.1)
  testFrame$july10pop <-Numberize(testFrame$X.2)
  testFrame$july11pop <-Numberize(testFrame$X.3)
  testFrame <- testFrame[,-1:-4]
  
  #remove the old rownames, which are now confusing
  rownames(testFrame) <- NULL
  
  return(testFrame)
}

Numberize <- function(inputVector)
{
  # Get rid of commas
  inputVector<-gsub(",","", inputVector)
  # Get rid of spaces
  inputVector<-gsub(" ","", inputVector)
  
  return(as.numeric(inputVector))
}

USstatePops <- readCensus()
USstatePops$april10census[1:3]

mean(USstatePops$april10census)
median(USstatePops$april10census)
mode(USstatePops$april10census)
var(USstatePops$april10census)
sd(USstatePops$april10census)

# 
# Histograms creation for our slides
# ----------------------------------
hist(USstatePops$april10census)
hist(USstatePops$april10census, breaks=20)
hist(rnorm(51, 6043834, 6823984))

hist(rnorm(51, 6043834, 6823984), main="Example of a Normal Distribution", 
     xlab="Distribution with a Mean of 6,043,834 and standard deviation of 6,823,984")


