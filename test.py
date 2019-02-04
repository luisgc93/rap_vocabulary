#from nltk.book import *
from nltk.corpus import PlaintextCorpusReader
import nltk


# This is the function that will return the count of unique words
def distinct_number(text):
    return len(set(text))

def lexical_diversity(text):
    return len(set(text))/len(text)

def word_frequency(word,text):
    return text.count(word)*100/len(text)


corpus_root ='/home/l21009103/Desktop/Bham_CS/NLTK/lyrics'
wordlists = PlaintextCorpusReader(corpus_root, ".*\.txt")
wordlists.fileids()
corpus  = nltk.Text(wordlists.words())
