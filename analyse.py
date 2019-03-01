from nltk.corpus import PlaintextCorpusReader
import nltk
import os

# Module creates Text objects for NLP analysis

# Try to do all the steps from rap.py but without all the duplicated code

# root folder for our .txt files
corpus_root = '/home/l21009103/Desktop/Bham_CS/NLTK/corpus/lyrics'

# This is an object of type PlaintextCorpusReader, it is NOT indexed
rap_corpus = PlaintextCorpusReader(corpus_root, '.*')

def analysis(directory):
    myDict = {}
    for filename in os.listdir(directory):
        # Step 1: Create Text object
        text_obj = nltk.Text(nltk.word_tokenize(rap_corpus.raw(filename)))[0:35000]
        rapper_name = os.path.splitext(filename)[0]
        # Step 2: Store Text objects in a dictionary with key = 'Rapper_name', value =  TextObj
        myDict[rapper_name] = text_obj
        #print(rapper_name)
        for k,v in myDict.items():
            print(k)

def main():
    analysis(corpus_root)


if __name__ == "__main__":
    main()
