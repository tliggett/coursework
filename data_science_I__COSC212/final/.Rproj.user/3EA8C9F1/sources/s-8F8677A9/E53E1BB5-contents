# TJ Liggett
# DS 212          

# ---------------- HW11: Word Association --------------
  # install package "arules"
  install.packages("arules")
  # library package "arules"
  library(arules)
  # install package"arulesViz"
  install.packages("arulesViz")
  # library package "arulesViz"
  library(arulesViz)

# ------------------------------------------------------
# Step 1: Load data
  # read in the data
  load("termDocMatrix.rdata")
  # store the data in a new object called "Data"
  Data <- termDocMatrix
  # check the first 5 rows of the matrix
  head(Data,5)

  Data[Data>1] <- 1
  
# ------------------------------------------------------
# Step 2: Transpose the matrix
  df <- t(Data)
  # check the first 5 rows to make sure the transpose was done correctly
  head(df,5)

# use as(0 to force an object to belong to a class.  Here my df object is converted into a transaction object)
  d <- as(df, "transactions")
  itemFrequencyPlot(d)
# ------------------------------------------------------
# Step3: Generate association arules
  rules <- apriori(df,parameter=list(support=0.01,# support indicate how frequently iterms in LHS and RHS occur together
                                     confidence=0.5))  # confidence indicate how often the rule has found to be true
  # check the rules
  summary(rules)
  inspect(rules)
  
  
  # visualize rules
  plot(rules)
  
  
  # pick the rules whose lift is higher than 18
  goodrules <- rules[quality(rules)$lift > 18]
  
  
  
  # check better rules
  summary(goodrules)
  
  # check the good rules one by one use function inspect
  inspect(goodrules)
  
  
    # generate another rulset, setting the RHS to be exactly "analysis"
       # support indicates how frequently iterms in LHS and RHS occur together
       # confidence indicates how often the rule has found to be true
       # set RHS to be "analysis" (use the appearance parameter)
    analyrules <- apriori(df,parameter=list(support=0.01, confidence=0.5), appearance = list(rhs=c("analysis")))
                                
  
  # check ruleset
  summary(analyrules)
  
  # visualize rules2
  plot(analyrules)
  
  # pick the rules whose lift is higher than 6
  goodanalyrules <- analyrules[quality(analyrules)$lift > 6]
  
  # check betterrules2
  summary(goodanalyrules)
  
  # check the good rules one by one with function inspect
  inspect(goodanalyrules)
  

# Step4: Interpret result
  
  # ** Your answers here **
  # The rhs words that appeared most with high lifts were time and series, followed by parallel. 
  # Tweets with words such as analysis, r, mining, series, time, examples, etc. were likely to be associated
  # with the words above.
  
  # When filtering right-hand results to be strictly analysis, words that often correlated on the lhs included
  # positions, time, social, network, series, and mining.
  
  # These results show some words that are closely correlated to others. In large sets of documents, these
  # correlations could help find more relevant documents when searching based on a few words.

  