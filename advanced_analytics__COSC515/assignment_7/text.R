install.packages("readr")
install.packages("tidytext")
install.packages("dplyr")
install.packages("tm")
install.packages("ngram")



##Enter File Name
doc <- read_file("C:/Users/rleach/Desktop/workspace/trump.txt")

## A couple lines to clean up the docs and remove the numbers
doc <- gsub("\r\n", " ", doc)
doc <- removeNumbers(doc)

##This breaks the text down word by word aka Tokenizes them
tokens <- data_frame(text = doc) %>% unnest_tokens(word, text)

##This removes all of the Stop Words from our tokens
cleanTokens <- tokens[!tokens$word %in% stop_words$word,]

##Use this function to Export your file as a csv to use in Tableau or other
write.csv(tokens,"tokens.csv", row.names = FALSE)

##This will give us the sentiment from the Bing lexicon
sentiment <- cleanTokens %>%
  inner_join(get_sentiments("bing"))
table(sentiment$sentiment)

##This will give us the sentiment distribution from the Afinn lexicon
sentiment <- cleanTokens %>%
  inner_join(get_sentiments("afinn"))
table(sentiment$value)
hist(sentiment$value)

##This will give us the sentiment from the NRC lexicon
sentiment <- cleanTokens %>%
  inner_join(get_sentiments("nrc"))
table(sentiment$sentiment)
counts <- table(sentiment$sentiment)
barplot(counts,main = "NRC Sentiments",ylab = "Number",xlab ="Emotion")

##This will give us the sentiment from the Loughran Lexicon
sentiment <- cleanTokens %>%
  inner_join(get_sentiments("loughran"))
table(sentiment$sentiment)
counts <- table(sentiment$sentiment)
barplot(counts,main = "Loughran Sentiments",ylab = "Number",xlab ="Financial Context")

## We can play with multiple word sequences. Use this to create the n-gram where n is the
#number of words in the string
text <- concatenate(tokens$word)
ng <- ngram(text,2)

##This is the frequencies for the given n-gram
gram <- get.phrasetable(ng)
head(gram,10)

##some nonsense we can play around with
babble(ng,10)

## This will export the n-grams as a csv
write.csv(gram,"C:/Users/rleach/Desktop/workspace/grams.csv", row.names = FALSE)