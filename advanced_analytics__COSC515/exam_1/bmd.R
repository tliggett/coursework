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

set.seed(206)

bmd$bmi <- bmd$weight_kg / (bmd$height_cm/100)^2

bmd$isFemale = 0
bmd$isFemale[bmd$sex=="F"]=1

bmd$isFracture = 0
bmd$isFracture[bmd$fracture=="fracture"]=1

sample <- sample.split(bmd$isFracture,SplitRatio=0.80)
train <- subset(bmd,sample==TRUE)
test <- subset(bmd,sample==FALSE)

fit<-glm(isFracture~bmd,data=train,family=binomial)
summary(fit)

pred <- round(predict(fit,test,type = "response"))
conf <- table(pred,test$isFracture)
confusionMatrix(conf)
confusionMatrix(conf)$byClass

tree <- rpart(fracture ~ age+sex+bmd+bmi, data=train, method = "class",
              parms = list(split = 'information'),cp=-1)
tree
fancyRpartPlot(tree)

printcp(tree)
plotcp(tree)
cp = tree$cptable[which.min(tree$cptable[,"xerror"]),"CP"]

post_tree <- rpart(fracture ~ age+sex+bmd+bmi, data=train, method = "class",
                   parms = list(split = 'information'),cp=cp)
post_tree
fancyRpartPlot(post_tree)

post_pred<-predict(post_tree,test,type = "class")
post_conf <- table(post_pred,test$fracture)
confusionMatrix(post_conf)
confusionMatrix(post_conf)$byClass
