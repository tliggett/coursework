# TJ Liggett
# COSC 212 Homework #3

loanStats <- read.csv('LoanStats3a.csv', skip=1)

summary(loanStats)
# The id field was completely blank except a few rows, so I checked the last rows to see what was up
# 42,378 observations of 145 variables. Obviously going to be a lot of things to shave off.

# Here are the columns you ordered sir
requestedColumns <- c("loan_amnt", "term", "int_rate", "installment", "grade", "emp_length",
                      "home_ownership",  "annual_inc", "loan_status", "purpose", "title",
                      "zip_code", "addr_state",  "dti", "earliest_cr_line", "inq_last_6mths",
                      "open_acc", "revol_bal", "revol_util", "total_acc", "total_pymnt",
                      "total_rec_prncp",  "total_rec_int")

# Trimming the loan stats dataframe
loanStatsT <- loanStats[requestedColumns]


tail(loanStats, 5)
# The last two rows are blank except the id field contains totals

#Trim the last two rows:
#Note: Weird from a programmer's perspective that there is no 0 row in R. Threw me off
loanStatsT <- loanStatsT[-c(nrow(loanStatsT), nrow(loanStatsT)-1),]


# Reobserving the now trimmed data set
summary(loanStatsT)
str(loanStatsT)

# A lot of the variables look relatively clean at this point. 
# loan_status, home_ownership, term all have one blank value, might look to find that
# Some of the factors could be treated numerically:
#   *term is a factor with only 3 levels: 36 months, 60 months, and blank
#   *int_rate is a factor     of percentages; these percentages could be looked at as numeric (num) values
#   *revol_util is also a factor of percentages
#   *emp_length is is a factor of year numbers. Could be refactored and measured numerically
#   *earliest_cr_line is a factor of dates, could possibly be formatted as true dates 
#       (I don't know if we've talked about dates but I know R can handle them)

# Modifying int_rate to create numeric and then reobserving
loanStatsT$int_rate <- as.numeric(gsub("%", "",  loanStatsT$int_rate))
summary(loanStatsT$int_rate)
# There is one NA, which suggests to me we might have a lingering ugly column

# Modifying revol_util to create numeric and then reobserving
loanStatsT$revol_util <- as.numeric(gsub("%", "",  loanStatsT$revol_util))
summary(loanStatsT$revol_util)
# 91 NA's are concerning, but I'll leave that be for now

# Going to leave the original terms column, as the factor is helpful in its own right for this one
loanStatsT$termNumeric <- as.numeric(gsub("months", "",  loanStatsT$term))
summary(loanStatsT$termNumeric)

# Just keeping this in my personal notes right now
# loanStatsT$earliest_cr_line <- gsub("-", " ", loanStatsT$earliest_cr_line)
# loanStatsT$earliest_cr_line <- as.Date(loanStatsT$earliest_cr_line, format = "%b %Y")

# I'm not going to touch emp_length or earliest_cr_line right now. There's a lot going on there and I think
# the solutions are well outside the scope of what we've talked about so far. I don't have time to work on those
# right now. I don't want to deal with the regex 


