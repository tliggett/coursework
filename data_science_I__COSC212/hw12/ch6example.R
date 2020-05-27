# -------------------------- Hw13: SVM -----------------------------
# install.packages("kernlab")   
library("kernlab")

# --------------------------------------------------------------------
# Step 1: Load the data
  air <- data.frame(airquality)

  # find which columns in the dataframe contain NAs.
  colnames(air)[colSums(is.na(air)) > 0]

  # find the NAs in column "Ozone" and replace them by the mean value of this column
  air$Ozone[is.na(air$Ozone)] <- mean(air$Ozone, na.rm=TRUE)

  # find the NAs in column "Solar.R" and replace those NAs by the mean value of this column
  air$Solar.R[is.na(air$Solar.R)] <- mean(air$Solar.R, na.rm=TRUE)

# --------------------------------------------------------------------
# Step 2: Create train and test data sets
  # create a list of random index for air data and store the index in a variable called "ranIndex"
  randIndex <- sample(1:dim(air)[1])

  # In order to split data, create a 2/3 cutpoint and round the number
  cutpoint2_3 <- floor(2*dim(air)[1]/3)

  # check the 2/3 cutpoint
  cutpoint2_3

  # create train data set, which contains the first 2/3 of overall data
  trainData <- air[randIndex[1:cutpoint2_3],]

  # create test data, which contains the left 1/3 of the overall data
  testData <- air[randIndex[(cutpoint2_3+1):dim(air)[1]],]

  # check train data set
  head(trainData)

  # check test data set
  dim(testData)

# --------------------------------------------------------------------
# Step 3: Build a Model using KSVM & visualize the results
  # 1) Build a model to predict Ozone and name it "svmOutput"
  svmOutput <- ksvm(Ozone~., # set "Ozone" as the target predicting variable; "." means use all other variables to predict "Ozone"
                  data = trainData, # specify the data to use in the analysis
                  kernel = "rbfdot", # kernel function that projects the low dimensional problem into higher dimensional space
                  kpar = "automatic",# kpar refer to parameters that can be used to control the radial function kernel(rbfdot)
                  C = 10, # C refers to "Cost of Constrains"
                  cross = 10, # use 10 fold cross validation in this model
                  prob.model = TRUE # use probability model in this model
                  )

  # check the model
  svmOutput

  # 2) Test the model
  svmPred <- predict(svmOutput, # use the built model "svmOutput" to predict 
                     testData, # use testData to generate predictions
                     type = "votes" # request "votes" from the prediction process
                     )

  str(svmPred)

  # create a comparison dataframe that contains the exact "Ozone" value and the predicted "Ozone" value
  compTable <- data.frame(testData[,1], svmPred[,1])

  # change the column names to "test" and "Pred"
  colnames(compTable) <- c("test","Pred")

  # comput the Root Mean Squared Error
  sqrt(mean((compTable$test-compTable$Pred)^2))


  # 3) Plot the results
  library(ggplot2)

  # compute absolute error for each case
  compTable$error <- abs(compTable$test - compTable$Pred)

  # create a new dataframe contains error, tempreture and wind
  svmPlot <- data.frame(compTable$error, testData$Temp, testData$Wind)

  # assign column names
  colnames(svmPlot) <- c("error","Temp","Wind")

  # polt result using ggplot, setting "Temp" as x-axis and "Wind" as y-axis
  ggplot(svmPlot, aes(x=Temp,y=Wind)) + 
    # use point size and color shade to illustrate how big is the error
    geom_point(aes(size=error, color=error))

  # 4) Compute models and plot the results for 'svm'(in the e1071) and 'lm'
  # install.packages("e1071")
  library(e1071)

  # svm function in "e1071
  svm_e <- svm(Ozone~., # set "Ozone" as target variable,and use all other variables to predict
             data=trainData  # specify the data to use in the analysis
             )

  # check the model
  svm_e

  # Test the model
  svm_ePred <- predict(svm_e,# use "svm_e" to predict 
                testData # use testData to generate predictions
                )

  # create a dataframe that contains the exact "Ozone" value and the predicted "Ozone" value
  compTable2 <- data.frame(testData[,1], svm_ePred)

  # change the column names to "test" and "Pred"
  colnames(compTable2) <- c("test","Pred")

  # comput the Root Mean Squared Error
  sqrt(mean((compTable2$test-compTable2$Pred)^2))

  # compute absolute error for each case
  compTable2$error <- abs(compTable2$test-compTable2$Pred)

  # create a new dataframe contains error, tempreture and wind
  svm_ePlot <- data.frame(compTable2$error, testData$Temp, testData$Wind)
  colnames(svm_ePlot) <- c("error","Temp","Wind")

  # polt result using ggplot
  ggplot(svm_ePlot,aes(x=Temp,y=Wind)) + geom_point(aes(size=error,color=error))
  
  # lm
  # create a linear model
  lm <- lm(formula = Ozone~.,# use all the other variables to predict "Ozone"
         data=trainData # use "trainData" in this analysis
         )
  # Test the model
  predLm <- predict(lm,  # use model "svm_e" to predict 
                  testData # use testData to do the test
  )

  # create a dataframe that contains the exact "Ozone" value and the predicted "Ozone" value
  compTable3 <- data.frame(testData[,1], predLm)

  # change the column names to "test" and "Pred"
  colnames(compTable3) <- c("test","Pred")

  # comput the Root Mean Squared Error
  sqrt(mean((compTable2$test-compTable2$Pred)^2))

  # compute absolute error for each case
  compTable3$error <- abs(compTable3$test-compTable3$Pred)

  # create a new dataframe contains error, tempreture and wind
  lmPlot <- data.frame(compTable3$error,testData$Temp,testData$Wind)
  colnames(lmPlot) <- c("error","Temp","Wind")

  # polt result using ggplot
  ggplot(lmPlot,aes(x=Temp,y=Wind)) + geom_point(aes(size=error,color=error))
  
  # show 3 results
  # install.packages("gridExtra")
  library(gridExtra)

  # add titles to each plot and remove legends
  plot_svm <- ggplot(svmPlot,aes(x=Temp,y=Wind)) + geom_point(aes(size=error,color=error)) + theme(legend.position="none") + ggtitle("ksvm")
  plot_svm_e <- ggplot(svm_ePlot,aes(x=Temp,y=Wind)) + geom_point(aes(size=error,color=error)) + theme(legend.position="none") + ggtitle("svm")
  plot_lm <- ggplot(lmPlot,aes(x=Temp,y=Wind)) + geom_point(aes(size=error,color=error)) + ggtitle("lm")
  
  # arrange the three plot in a 2 by 2 matrix
  grid.arrange(plot_svm,plot_svm_e,plot_lm,ncol=2,nrow=2)

# --------------------------------------------------------------------
# Step 4: Create a "goodOzone" variable
  # calculate average Ozone
  meanOzone <- mean(air$Ozone,na.rm=TRUE)
 
  # create a new variable named "goodOzone" in train data set
    # goodOzone = 0 if Ozone is below average Ozone
    # googOzone = 1 if Ozone is eaqual or above the average ozone
  trainData$goodOzone <- ifelse(trainData$Ozone<meanOzone, 0, 1)

  # do the same thing for test dataset
  testData$goodOzone <- ifelse(testData$Ozone<meanOzone, 0, 1)

  # remove "Ozone" from train data
  trainData <- trainData[,-1]

  # remove "Ozone" from test data
  testData <- testData[,-1]

# --------------------------------------------------------------------
# Step 5: See if we can do a better job predicting 'good' and 'bad' days
  # convert "goodOzone" in train data from numeric to factor
  trainData$goodOzone <- as.factor(trainData$goodOzone)

  # convert "goodOzone" in test data from numeric to factor
  testData$goodOzone <- as.factor(testData$goodOzone)

 # 1)	Build a model 
  # build a model using ksvm function,and use all other variables to predict
  svmGood <- ksvm(goodOzone~., # set "Ozone" as target variable; "." means use all other variables to predict "Ozone"
       data=trainData, # specify the data to use in the analysis
       kernel="rbfdot", # kernel function that projects the low dimensional problem into higher dimensional space
       kpar="automatic",# kpar refer to parameters that can be used to control the radial function kernel(rbfdot)
       C=10, # C refers to "Cost of Constrains"
       cross=10, # use 10 fold cross validation in this model
       prob.model=TRUE # use probability model in this model
  )

  # check the model
  svmGood

  # 2) Test the model
  goodPred <- predict(svmGood, # use model "svmGood" to predict
                      testData # use testData to do the test
                      )

  # create a dataframe that contains the exact "goodOzone" value and the predicted "goodOzone"
  compGood1 <- data.frame(testData[,6], goodPred)

  # change column names
  colnames(compGood1) <- c("test","Pred")

  # Compute the percentage of correct cases
  perc_ksvm <- length(which(compGood1$test==compGood1$Pred))/dim(compGood1)[1]
  perc_ksvm

  # 3)	Plot the results. 
  # determine the prediction is "correct" or "wrong" for each case
  compGood1$correct <- ifelse(compGood1$test==compGood1$Pred,"correct","wrong")

  # create a new dataframe contains correct, tempreture and wind, and goodZone
  Plot_ksvm <- data.frame(compGood1$correct,testData$Temp,testData$Wind,testData$goodOzone,compGood1$Pred)
  
  # change column names
  colnames(Plot_ksvm) <- c("correct","Temp","Wind","goodOzone","Predict")
  # polt result using ggplot
  # size representing correct/wrong; color representing actual good/bad day; shape representing predicted good/bad day.
  ggplot(Plot_ksvm, aes(x=Temp,y=Wind)) + geom_point(aes(size=correct,color=goodOzone,shape = Predict))
  

  # 4)	Compute models and plot the results for "svm" and "nb" 
  ## svm
  # build a model using svm function,and use all other variables to predict
  svmGood_e <- svm(goodOzone~., data=trainData)

  # Make prediction
  GoodPred2 <- predict(svmGood_e, testData)

  # create a dataframe that contains the exact "goodOzone" value and the predicted "goodOzone"
  compGood2 <- data.frame(testData[,6], GoodPred2)

  # change column names
  colnames(compGood2) <- c("test","Pred")

  # Compute the percentage
  perc_svm_e <- length(which(compGood2$test==compGood2$Pred))/dim(compGood2)[1]
  perc_svm_e
  
  # Plot the results
  # determine the prediction is "correct" or "wrong" for each case
  compGood2$correct <- ifelse(compGood2$test==compGood2$Pred,'correct','wrong')

  # create a new dataframe contains correct, tempreture and wind, and goodZone
  Plot_svm_e <- data.frame(compGood2$correct,testData$Temp,testData$Wind,testData$goodOzone,compGood2$Pred)
  
  # change column names
  colnames(Plot_svm_e) <- c("correct","Temp","Wind","goodOzone","Predict")

  # polt result using ggplot
  ggplot(Plot_svm_e,aes(x=Temp,y=Wind)) + geom_point(aes(size=correct,color=goodOzone,shape=Predict))

  ## nb
  # build a model using naive bayes algorithm, and use all other variables to predict
  nb <- naiveBayes(goodOzone~., data=trainData)

  # Make prediction
  Pred_nb <- predict(nb, testData)

  # create a dataframe that contains the exact "goodOzone" value and the predicted "goodOzone"
  compNb <- data.frame(testData[,6], Pred_nb)

  # change column names
  colnames(compNb) <- c("test","Pred")

  # Compute the percentage
  perc_nb <- length(which(compNb$test==compNb$Pred))/dim(compNb)[1]
  perc_nb
  
  # Plot the results. 
  # determine the prediction is "correct" or "wrong" for each case
  compNb$correct <- ifelse(compNb$test==compNb$Pred,'correct','wrong')

  # create a new dataframe contains correct, tempreture and wind, and goodZone
  Plot_nb <- data.frame(compNb$correct,testData$Temp,testData$Wind,testData$goodOzone,compNb$Pred)

  # change column names
  colnames(Plot_nb) <- c("correct","Temp","Wind","goodOzone","Predict")

  # polt result using ggplot
  ggplot(Plot_nb,aes(x=Temp,y=Wind)) + geom_point(aes(size=correct,color=goodOzone,shape=Predict))

  # show all charts in one window
  g1 <- ggplot(Plot_ksvm,aes(x=Temp,y=Wind))+geom_point(aes(size=correct,color=goodOzone,shape = Predict)) + theme(legend.position="none") + ggtitle("ksvm")
  g2 <- ggplot(Plot_svm_e,aes(x=Temp,y=Wind))+geom_point(aes(size=correct,color=goodOzone,shape=Predict)) + theme(legend.position="none") + ggtitle("svm")
  g3 <- ggplot(Plot_nb,aes(x=Temp,y=Wind))+geom_point(aes(size=correct,color=goodOzone,shape=Predict)) + ggtitle("Naive Bayes")
  grid.arrange(g1,g2,g3,nrow=2)

# --------------------------------------------------------------------
# Step 6: Which are the best Models for this data? 
#  Your answers here!



