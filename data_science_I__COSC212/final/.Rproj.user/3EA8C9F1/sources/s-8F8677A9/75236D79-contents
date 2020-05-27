# TJ Liggett
# DS 212 Homework 9

library(tm)


# Read in positive and negative words files
pos <- "positive-words.txt"
neg <- "negative-words.txt"
p <- scan(pos, character(0),sep = "\n")
n <- scan(neg, character(0),sep = "\n")


# Pull all tweet texts into a vector from csv
scrapeTweets <- function(filename, column)
{
  c <- read.csv(filename)
  return(c[[column]])
}

# Returns the word counts for the given character vector
getWordCounts <- function(cvec)
{
  require(tm)
  words.vec <- VectorSource(cvec)
  words.corpus <- Corpus(words.vec)
  words.corpus
  words.corpus <- tm_map(words.corpus, content_transformer(tolower))
  words.corpus <- tm_map(words.corpus, removePunctuation)
  words.corpus <- tm_map(words.corpus, removeNumbers)
  words.corpus <- tm_map(words.corpus, removeWords, stopwords("english"))
  tdm <- TermDocumentMatrix(words.corpus)
  m <- as.matrix(tdm)
  wordCounts <- rowSums(m)
  wordCounts <- sort(wordCounts, decreasing=TRUE)
  return(wordCounts)
}


# Do the things we're supposed to do
sentimentAnalysis <- function(filename, column)
{
  cat("Filename: ",filename, "\n")
  wordCounts <- getWordCounts(scrapeTweets(filename, column))
  # Do word counts
  cat("Total number of words in text: ", sum(wordCounts), "\n")
  words <- names(wordCounts)
  cat("Number of words used (vocabulary): ", length(words), "\n")
  # positive words
  matched <- match(words, p, nomatch=0)
  mCounts <- wordCounts[which(matched !=0)]
  nPos <- sum(mCounts)
  cat("Number of occurrences of positive words: ", nPos, "\n")
  cat("Positive word vocabulary count: ", length(mCounts), "\n")
  # negative words
  matched <- match(words, n, nomatch=0)
  nCounts <- wordCounts[which(matched !=0)]
  nNeg <- sum(nCounts)
  cat("Number of occurrences of negative words: ", nNeg, "\n")
  cat("Negative word vocabulary count: ", length(nCounts), "\n")
  # Technically a ratio and not a percent...
  cat("Percentage of positive words: ", nPos/sum(wordCounts), "\n")
  cat("Percentage of negative words: ", nNeg/sum(wordCounts), "\n")
}


# The functions above make a single call necessary for each document
sentimentAnalysis("Donald-Tweets.csv", "Tweet_Text")
sentimentAnalysis("womenmarch.csv", "text")
sentimentAnalysis("elonmusk_tweets.csv", "text")
