import nltk
import os
import csv
from collections import OrderedDict
from nltk.corpus import PlaintextCorpusReader

# This module creates Text objects for NLP analysis and outputs the results in a .csv file

# root folder for our .txt files
corpus_root = '/home/l21009103/Desktop/Bham_CS/NLTK/lyrics'

# This is an object of type PlaintextCorpusReader, it is NOT indexed
rap_corpus = PlaintextCorpusReader(corpus_root, '.*')

def analysis(directory):
    myDict = {}
    for filename in os.listdir(directory):
        # Step 1: Create Text object
        text_obj = nltk.Text(nltk.word_tokenize(rap_corpus.raw(filename)))[0:35000]
        rapper_name = os.path.splitext(filename)[0].title()
        # Step 2: Check word length > 35000 & populate dictionary
        if len(text_obj) == 35000:
            # Key = 'Rapper_name', value =  unique word count of TextObj
            myDict[rapper_name] = len(set(text_obj))
    myDict = OrderedDict(sorted(myDict.items(), key=lambda t: t[1]))
    return myDict

def WriteDictToCSV(dict, csv_file):
    with open(csv_file, 'w') as csvfile:
        writer = csv.writer(csvfile)
        # Write categories at the top
        writer.writerow(['Artist', 'Count'])
        # Write out data
        for key,value in dict.items():
            writer.writerow([key, value])

def main():
        dict = analysis(corpus_root)
        currentPath = os.getcwd()
        csv_file = currentPath + "/plot/data/results.csv"
        WriteDictToCSV(dict, csv_file)


if __name__ == "__main__":
    main()
