# TJ Liggett
# DS 212 Homework 8

# Reading through Treasure Island

# Read in the file
sbaFile <- "https://www.gutenberg.org/files/120/120-0.txt"   
sba <- readLines(sbaFile)
sba <- gsub("“", "\"", sba)
sba <- gsub("”", "\"", sba)
head(sba, 3)

# Load relevant libraries
library(XML)
library(tm)

# using the tm package
# Created a word corpus of the book Treasure Island 
# --------------------
words.vec <- VectorSource(sba)
words.corpus <- Corpus(words.vec)
words.corpus
words.corpus <- tm_map(words.corpus, content_transformer(tolower))
words.corpus <- tm_map(words.corpus, removePunctuation)
words.corpus <- tm_map(words.corpus, removeNumbers)
# Added a few extra stop words to make the image better
words.corpus <- tm_map(words.corpus, removeWords, c(stopwords("english"), "said", "like"))
tdm <- TermDocumentMatrix(words.corpus)
tdm

# Analyzed the top words
m <- as.matrix(tdm)
wordCounts <- rowSums(m)
wordCounts <- sort(wordCounts, decreasing=TRUE)
head(wordCounts)
# Most used words included now, one, man, captain, and silver


# WordClouds
# ----------
# Created a word cloud for Treasure Island  
library(wordcloud)
cloudFrame <- data.frame(word = names(wordCounts), freq=wordCounts)

wordcloud(names(wordCounts), wordCounts, min.freq=20, max.words=50, rot.per=0.35, 
          use.r.layout=FALSE, colors=brewer.pal(8, "Spectral"))

