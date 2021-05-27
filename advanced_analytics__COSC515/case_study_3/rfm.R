install.packages("factoextra")

library(factoextra)

##take a peek at our data
summary(shop)

## Check for missing data
sum(is.na(shop))

## It looks like Invoices are stored as numeric but we want them as strings
shop$invoice<-as.character(shop$invoice)

## We need to change the date variable from a string to date
shop$date <- as.Date(shop$date, '%m/ %d/ %Y')

## Let's set a reference point for the date saying that the current date is 12/31/2020
now <- as.Date("12/31/2020",'%m/ %d/ %Y')

## Let's see how many unique customers we have
length(unique(shop$customer))

## Calculating the RFM Table we first start with Recency by looking at the most
## recent invoice for each customer
## This will take the latest date for each customer and store it in a table
R_table <- aggregate(date ~ customer,shop,FUN=max)
## This will give the number of days since last purchase according to our 
## reference point of 12/31/2020
R_table$days <- as.numeric(now-R_table$date)

## R is done let's move to Frequency. We will again aggregate but this time add the
## number of invoices for each customer
F_table <- aggregate(invoice~customer,shop,FUN=length)

## RF are done lastly let's find M by taking the sum of all the invoices for each
## customer ID

M_table <-aggregate(sale~customer,shop,FUN=sum)

## Now let's merge these three tables into one using the customer ID as the merging
## condition
RFM_table <-merge(R_table,F_table,by.x="customer",by.y="customer")
RFM_table <-merge(RFM_table,M_table,by.x="customer",by.y="customer")
## We can delete the most recent invoice date by deleting the second column
RFM_table <- RFM_table[-2]

## Giving the R score based on most recent being 5 and least recent a 1
RFM_table$R <- abs(6-findInterval(RFM_table$days, quantile(RFM_table$days, c(0.0, 0.2, 0.4, 0.6, 0.8)))) 

## Giving the F score based on most orders 5 and least orders a 1
RFM_table$F <- findInterval(RFM_table$invoice, quantile(RFM_table$invoice, c(0.0, 0.2, 0.4, 0.6, 0.8)))

## Giving the M score based on most money 5 and least money 1
RFM_table$M <- findInterval(RFM_table$sale, quantile(RFM_table$sale, c(0.0, 0.2, 0.4, 0.6, 0.8)))

## We can combine all 3 into one variable
RFM_table$RFM <-as.character(paste(RFM_table$R,RFM_table$F,RFM_table$M))

## Let's do some k-means cluster analysis. First create a dataframe with only days since last order,
## total number of orders, and total dollar amount spent
clus <- RFM_table[,c(2,3,4)]

## These two graphs will help us find the optimal number of clusters
fviz_nbclust(clus, kmeans,method="wss")
fviz_nbclust(clus, kmeans,method="silhouette")

## Applying k-means cluster analysis with 4 distinct groups and 30 random starts
km<-kmeans(clus,centers=5,nstart=30)

## Here is a 2D visualization of the clusters
fviz_cluster(km, data = clus)

## Lastly Adding the k-means clusters to our table
RFM_table$cluster <- km$cluster
