# A very quick introduction to SQL using sqldf

library(sqldf)

# grab the Iris built-in datasets
data(iris)

# By convention, SQL commands are given in upper case and variable and database names
# written in lower case.
#
# A SELECT statement has this form:  SELECT variable1, variable2 FROM data
# ------------------------------------------------------------------------
sqldf('SELECT * FROM iris')          # Wildcard
sqldf('SELECT Species FROM iris')    # Specific column

# SQL doesn't work well with variables is . in their name.  Wrap in quotes to use
# -------------------------------------------------------------------------------
sqldf('SELECT "Petal.Width" FROM iris')

# To control the number of results returned, use LIMIT #
# ------------------------------------------------------
sqldf('SELECT "Petal.Width" FROM iris LIMIT 10')

# Conditionals in SQL are handled with the WHERE statement
# --------------------------------------------------------
sqldf('SELECT "Petal.Width" FROM iris WHERE Species = "setosa" ')
sqldf('SELECT "Petal.Width", Species FROM iris WHERE Species = "setosa" ')

# We can aggregate data with AVG, MEDIAN, MAX, MIN, and SUM
# ---------------------------------------------------------
sqldf('SELECT AVG("Petal.Width") FROM iris')
sqldf('SELECT MEDIAN("Petal.Width") FROM iris')
sqldf('SELECT MAX("Petal.Width") FROM iris')
sqldf('SELECT MIN("Petal.Width") FROM iris')
sqldf('SELECT SUM("Petal.Width") FROM iris')

# And we can name a new column with AS
# ------------------------------------
sqldf('SELECT AVG("Petal.Width") AS PetalAvgWidth FROM iris')

# Use GROUP BY to aggregate by groups
# -----------------------------------
sqldf('SELECT AVG("Petal.Width") AS PetalAvgWidth, Species FROM iris GROUP BY Species')

# Use ORDER BY var1 {ASC/DESC} to reorder
# ---------------------------------------
sqldf('SELECT * FROM iris ORDER BY "Sepal.Length" ASC')
sqldf('SELECT * FROM iris ORDER BY "Sepal.Length" DESC')

  