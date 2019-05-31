# rap_vocabulary
This data science project is inspired by Matt Daniel's work for the following publication:
https://pudding.cool/projects/vocabulary/

The goal is to replicate his findings with Spanish-speaking rappers. 

The first step of the project was to create a corpus of lyrics from a number of artists. For this purpose, TextFileGenerator.java and WebCrawler.java were created. They scrape the following site https://www.musica.com for an artist's lyrics and generate the output.txt file. 

The file is then processed with python's NLTK library for natural language processing. The module correct.py polishes our data and removes lyrics in foreign languages such as English or Portuguese.

The module analyse.py then creates Text objects for each file and performs a simple tokenizing process to retrieve the unique word counts for each artist.The results are then written out and saved on results.csv.

The results are then plotted with matplotlib although an interactive javascript version of the graph is currently being developed. 

![Alt text](/results.png)
