# CS 212 Homework 4
# TJ Liggett

install.packages('VGAM')
library('VGAM')
require('VGAM')

# Task 1: Write, test, and submit the necessary code in R to accomplish the following:

# 1. 	Generate a normal distribution, of 1,000 samples, with a mean of 80.
distro <- rnorm(1000, 80)
# 2. 	Write a function that takes three variables – a vector, a min and a max – and returns the number of elements in the vector that are between the min and max (including the min and max).
in.Range <- function(vect, min, max)
{
  forRet <- vect[vect >= min]
  forRet <- forRet[forRet <= max]
  return(length(forRet))
}
# 3. 	Use the function to see how many of your normal distribution samples are within the range of 79 to 81.
range <- in.Range(distro, 79, 81)
cat("There were", range, "within the range 79 - 81")
# 4. 	Repeat 3 times (creating a normal distribution and then calling your function) to see if the results vary.
replicate(3, in.Range(rnorm(1000, 80), 79, 81))
# The results varied a bit, as to be expected with random data. 

# Task 2: Write, test, and submit the necessary code in R to accomplish the following:

# 1. 	Generate 51 random numbers in a Pareto distribution and assign them to a variable called “FSApops.”
FSApops <- rpareto(51,577000,1.05)
# 2. 	Specify a “location” and a “shape” for your Pareto distribution that makes it as similar as possible to the actual distribution of state populations on page 89 of the textbook.
# For the first parameter (shape), I put 577,000, which is relatively close to the size of Wyoming. I really just fiddled with the second
# parameter to make it work
# 3. 	Create a histogram that shows the distribution of values in FSApops.
hist(FSApops, breaks = 20)
# 4. 	Use a command to report the actual mean and standard deviation of the 51 values stored in FSApops.
cat("FSApops mean: ",mean(FSApops))
cat("FSApops standard deviation: ", sd(FSApops))
# 5. 	Use a command to report the population of your largest fictional state (i.e., your California) and your smallest fictional state (i.e., your Wyoming).
cat("FSApops max: ", max(FSApops) ," | min: ",min(FSApops))