#install.packages("RCurl")
library(RCurl)
#install.packages("RJSONIO")
library(RJSONIO)
library(sqldf)

# Step 1: Load the data
# get data from the given URL
# ---------------------------
dataset <- getURL("http://opendata.maryland.gov/api/views/pdvh-tf2u/rows.json?accessType=DOWNLOAD")

# Hints:  You will want to look at the data first to see how a large JSON
#         file is structured; structure can vary so check it out.  Firefox
#         has some handy viewing utilities.  When you look at this data in
#         Firefox, wait for it to fully load and you will see that this data
#         has two major sections:  Meta and Data.  Now look at it in the raw
#         JSON tab to get a non-processed look of the file.  This is what you
#         downloaded in the step above.  The Raw JSON...
# Convert the JSON format dataset into R objects
# ----------------------------------------------
mydata <- fromJSON(dataset, simplify = FALSE, nullValue = NA)

# Look into the data summary
# --------------------------
summary(mydata)

# Step 2: Clean the data
# Get rid of metadata and only keep the second element in the list "mydata"
# --------------------------------------------------------------------------
myList <- mydata[[2]]

# Count how many elements are in "myList" and store the number into 
# variable "numRows"
# --------------------------------------------------------------------------
numRows <- length(myList)

# If we didn't have nullValue = NA in our fromJSON() call above, then we 
# would need to do the following:
#   Test every elements in "myList" one by one, for each element:
#      First, record the element into a temporary variable "record",
#      Second, if the original element is NULL, assign NA to "record". 
#               If not, keep the original value.
#      Third, re-assign the value of "record" to the element in "myList"
#         for(i in 1:numRows) {
#            record <- myList[[i]]
#            record[sapply(record, is.null)] <- NA
#            myList[[i]] <- record
#         }
#
# Unlist "myList" and transform it to a dataframe, whose number of rows is 
#  the value of "numRows".  This is similar to the steps followed on 
#  pages 134-135 of the text.
# ------------------------------------------------------------------------
df <- data.frame(matrix(unlist(myList), nrow=numRows, byrow=T), stringsAsFactors = FALSE)

# Remove the first 8 columns as instructed in step #2
# ------------------------------------------------------------------
df <- df[, -1:-8]

# Rename the rest of columns as instructed in step #2
# ------------------------------------------------------------------
colnames(df) <- c("CASE_NUMBER","BARRACK","ACC_DATE","ACC_TIME","ACC_TIME_CODE","DAY_OF_WEEK","ROAD","INTERSECT_ROAD","DIST_FROM_INTERSECT","DIST_DIRECTION","CITY_NAME","COUNTY_CODE","COUNTY_NAME","VEHICLE_COUNT","PROP_DEST","INJURY","COLLISION_WITH_1","COLLISION_WITH_2")


str(df)

# Step 3: SQL

# How many accidents happen on Sunday?
# Had to clean the row because of spaces
df$DAY_OF_WEEK <- gsub(' ', '', df$DAY_OF_WEEK)
sqldf('SELECT CASE_NUMBER, DAY_OF_WEEK FROM df')
sqldf("SELECT COUNT(CASE_NUMBER) AS AccidentsOnSunday FROM df WHERE DAY_OF_WEEK='SUNDAY'") 
# The output said there were 2373 Accidents on Sunday

# How many accidents had injuries (might need to remove NAs from the data)?
sqldf("SELECT COUNT(CASE_NUMBER) AS Injuries FROM df WHERE INJURY='YES'") 
# 6433 Injuries

# List the injuries by day.
sqldf('SELECT COUNT(CASE_NUMBER) AS Injuries, DAY_OF_WEEK FROM df WHERE INJURY="YES" GROUP BY DAY_OF_WEEK')


# Step 4: tapply

# How many accidents happen on Sunday?
tapply(df$CASE_NUMBER, df$DAY_OF_WEEK, length)
# We can observe from the table that 2373 accidents occurred on Sunday

# How many accidents had injuries (might need to remove NAs from the data)?
tapply(df$CASE_NUMBER, df$INJURY, length)
# Once again 6433 injuries

# List the injuries by day.
idf <- df[which(df$INJURY=='YES'),]
tapply(idf$CASE_NUMBER, idf$DAY_OF_WEEK, length)