data("iris")
library(ggplot2)

## Scatter Plot of the data
ggplot(iris, aes(Petal.Length, Petal.Width, color = Species)) + geom_point()

## Create the kmeans clusters. This will create 3 clusters using the petal length and width
irisCluster <-kmeans(iris[,3:4],3,nstart=20)
irisCluster

## This table tells us how good our estimates were
table(irisCluster$cluster,iris$Species)

## Here is a graph of our classifications
irisCluster$cluster <- as.factor(irisCluster$cluster)
ggplot(iris, aes(Petal.Length, Petal.Width, color = irisCluster$cluster)) + geom_point()



## Association Rules
install.packages("arules")
library(arules)
data(Groceries)

## Take a peek at the Data
inspect(head(Groceries))

##Creates a list of the most frequently purchased items
frequentItems <- eclat (Groceries, parameter = list(supp = 0.07, maxlen = 15))

##Take a peek at said items and graph the top 10
inspect(frequentItems)
itemFrequencyPlot(Groceries, topN=10, type="absolute", main="Item Frequency")

## Create our Association Rules
rules <- apriori (Groceries, parameter = list(supp = 0.001, conf = 0.5))

## Rules Sorted by Confidence
rules_conf <- sort (rules, by="confidence", decreasing=TRUE)
inspect(head(rules_conf))

## Rules Sorted by Lift
rules_lift <- sort (rules, by="lift", decreasing=TRUE)
inspect(head(rules_lift))

## Control Length of Rules and Confidence. Higher "conf" Means stronger rules
## higher "maxlen" means longer rules
rules <- apriori(Groceries, parameter = list (supp = 0.001, conf = 0.5, maxlen=3))

## You can search for items on the RHS by changing what's in quotes
rules <- apriori (data=Groceries, parameter=list (supp=0.001,conf = 0.08), appearance = list (default="lhs",rhs="bottled beer"), control = list (verbose=F))
rules_conf <- sort (rules, by="confidence", decreasing=TRUE)
inspect(head(rules_conf))

## You can search for items on the LHS by changing what's in quotes
rules <- apriori (data=Groceries, parameter=list (supp=0.001,conf = 0.15,minlen=2), appearance = list(default="rhs",lhs="bottled beer"), control = list (verbose=F))
rules_conf <- sort (rules, by="confidence", decreasing=TRUE)
inspect(head(rules_conf))
