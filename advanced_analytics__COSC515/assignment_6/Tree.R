## Load up some packages we will be using for Decision Trees
install.packages("caTools")
library(caTools)

install.packages("rpart")
library(rpart)

install.packages("rattle")
library(rattle)

install.packages("rpart.plot")
library(rpart.plot)

install.packages("RColorBrewer")
library(RColorBrewer)

install.packages("caret")
library(caret) 

## This tutorial is built with the diabetes.csv dataset. Load that before starting

## Split the data into a training set and test test
set.seed(5)
sample <- sample.split(loan$Default,SplitRatio=0.70)
train <- subset(loan,sample==TRUE)
test <- subset(loan,sample==FALSE)

## Creating the Full Tree Model

tree <- rpart(Default ~ ., data=train, method = "class",
                     parms = list(split = 'information'),cp=-1)
tree
fancyRpartPlot(tree)

## Calculate the Predictions from the test data

pred <-predict(tree,test,type = "class")

## Calculate the Confusion Matrix and fit measures

conf <- table(pred,test$Default)
confusionMatrix(conf)
confusionMatrix(conf)$byClass

## Pre-Pruning Method
## Fit the Model
pre_tree <-  rpart(Default ~ ., data=train, method = "class",
               parms = list(split = 'information'),maxdepth = 4, minsplit = 10, minbucket = 5)
pre_tree
fancyRpartPlot(pre_tree)

## Make the predictions
pre_pred<-predict(pre_tree,test,type = "class")

## Measures of Fit
pre_conf <- table(pre_pred,test$Default)
confusionMatrix(pre_conf)
confusionMatrix(pre_conf)$byClass

## Post-Pruning Method
## Find the CP
printcp(tree)
plotcp(tree)
cp = tree$cptable[which.min(tree$cptable[,"xerror"]),"CP"]

## Fit the Model
post_tree <- rpart(Default ~ ., data=train, method = "class",
             parms = list(split = 'information'),cp=cp)
post_tree
fancyRpartPlot(post_tree)

## Make the Predictions
post_pred<-predict(post_tree,test,type = "class")

## Measures of Fit
post_conf <- table(post_pred,test$Default)
confusionMatrix(post_conf)
confusionMatrix(post_conf)$byClass

## How does it stack up to the Logistic Model?
fit <- glm(Default~Checking_amount+Term+Credit_score+
             Education_loan+Saving_amount,data = test, family = binomial)
fit_pred <- round(predict(fit,test,type = "response"))
fit_conf <- table(fit_pred,test$Default)
confusionMatrix(fit_conf)
confusionMatrix(fit_conf)$byClass

confusionMatrix(conf)
confusionMatrix(pre_conf)
confusionMatrix(post_conf)
confusionMatrix(fit_conf)