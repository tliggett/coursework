install.packages("car")
library("car")

car$isDiesel = 0
car$isDiesel[car$Fuel_Type=="Diesel"]=1

car$isAuto = 0
car$isAuto[car$Transmission=="Automatic"]=1

car$fromDealer = 0
car$fromDealer[car$Seller_Type=="Dealer"]=1

fit<-lm(Present_Price ~ Selling_Price+Year+Kms_Driven+Owner+isDiesel+fromDealer+isAuto, data = car)
summary(fit)

plot(fit)

vif(fit)


## The first graph shows up if we have a linear relationship. There is a pretty big kink in there so this assumption looks to be violated

## The Second graph is our normality assumption. The dots should follow the line which they do not. Normality of the errors seems to be violated

## The third graph is for homoscedasticity or equal variance across the error terms. From left to right, it looks like the residuals are very spread out around the line but then get tighter. This would seem to violate our assumption

## The last graph is for Cook’s Distance. We want all of our observations to be within the area and not outside of the dashed red line. We don’t even see the dashed red line area so we aren’t even close. This one passes
