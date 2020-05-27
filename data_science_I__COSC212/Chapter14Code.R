sbaFile <- "susan.txt"                        # File that the speech text was saved to

# using scan
# ----------
sba <- scan(sbaFile, character(0), sep="\n")  # Lines are separated by a newline character
head(sba,3)

#use readLines
sba <- readLines(sbaFile)
head(sba, 3)

#Use a web file: Note the web location for the speech
sbaLocation <- URLencode("http://www.historyplace.com/speeches/anthony.htm")

# Read and parse HTML file
doc.html = htmlTreeParse(sbaLocation, useInternal = TRUE)

# Extract all the paragraphs (HTML tag is p, starting at
# the root of the document). Unlist flattens the list to
# create a character vector.
sba = unlist(xpathApply(doc.html, '//p', xmlValue))
head(sba, 3)

library(XML)
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
tdm

m <- as.matrix(tdm)
wordCounts <- rowSums(m)
wordCounts <- sort(wordCounts, decreasing=TRUE)
head(wordCounts)

# WordClouds
# ----------
library(wordcloud)
cloudFrame <- data.frame(word = names(wordCounts), freq=wordCounts)

wordcloud(cloudFrame$word, cloudFrame$freq)

wordcloud(names(wordCounts), wordCounts, min.freq=20, max.words=50, rot.per=0.35, 
          use.r.layout=FALSE, colors=brewer.pal(8, "Dark2"))

