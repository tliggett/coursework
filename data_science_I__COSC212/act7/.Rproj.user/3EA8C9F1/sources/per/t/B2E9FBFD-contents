# Chapter 15 R code from text

pos <- "positive-words.txt"
neg <- "negative-words.txt"
p <- scan(pos, character(0),sep = "\n")
n <- scan(neg, character(0),sep = "\n")

sbaFile <- "susan.txt"              # File that the speech text was saved to
#use readLines
sba <- readLines(sbaFile)

library(tm)

# using the tm package
# --------------------
words.vec <- VectorSource(sba)
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

# calculate the total number of words
totalWords <- sum(wordCounts)

# Create a vector that has all the unique words in it
words <- names(wordCounts)

# Use the match() function to show matches between the two lists
matched <- match(words, p, nomatch=0)

# Investigate a bit... (this is a bit different than your text)
head(matched,10)
matched[8]
p[1083]
words[8]

# Count the words that matched
mCounts <- wordCounts[which(matched !=0)]
length(mCounts)
mWords <- names(mCounts)
mWords
nPos<- sum(mCounts)
nPos

# Now do the same for the negative words
matched <- match(words, n, nomatch=0)
nCounts <- wordCounts[which(matched != 0)]
nNeg <- sum(nCounts)
nNeg
length(nCounts)

# calculate the percentage of words that are positive or negative
# totalWords <- length(words)
# -- or is it:  ??????????????????????
totalWords <- sum(wordCounts)
ratioPos <- nPos/totalWords
ratioPos
ratioNeg <- nNeg/totalWords
ratioNeg

