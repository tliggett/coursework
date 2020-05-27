# CS 212 Homework 5
# TJ Liggett

# For my data set, I'm going to average the games played of all players in the pro football hall of fame
# NOTE: The data came in a little messy, so I might have to clean it up to get her running.

hofStats <- read.csv("hof.csv")
str(hofStats)
summary(hofStats)

# It appears games played is located under column G. The min is 46 and the max is 382, so that column itself is
#probably clean 
#renaming the column

colnames(hofStats)[colnames(hofStats)=="G"] <- "games.played"
summary(hofStats$games.played)

hofStats$Player

#The names column came in ugly, with some junk on the end of all the names. I'm going to clean this up using regex
#NOTE: while this is outside the scope of the assignment, it's bothering me and I might use this data again

# hofStats$Player <- gsub("\\.*", "", hofStats$Player)
# hofStats$Player

# I tried for about 15 minutes messing around with regex, but I hate regex so I will burn this bridge another day

SampleMeans <- replicate(10000, mean(sample(hofStats$games.played, size=20, replace=TRUE)))

hist(SampleMeans)
# The histogram showed a normal distribution of sample means

# Calculating the sampling mean and standard error 
# The standard error for the data is just the sd of the sample means
samplingMean <- mean(SampleMeans)
error <- sd(SampleMeans)
cat("Sampling Mean",samplingMean, "Standard error: ", error)

# The distribution cut points of 2.5% and 97.5% are the sampling mean +/- 2 standard errors
cat("Distribution cut points 2.5%: ", samplingMean - (2*error), " | 97.5%: ", samplingMean + (2*error))

# With these cut points, we can be 95% confident the sample lies between them
# For my run, I am 95% sure that the average games played of a hall of fame NFL player is between 142 and 189
# If we obtain a new sample mean that is outside this range, we can infer something different is going on with the data