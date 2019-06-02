# rap_vocabulary
This data science project is inspired by Matt Daniel's work: https://pudding.cool/projects/vocabulary/

The goal is to replicate his findings with Spanish-speaking rappers. 

The first step of the project was to create a corpus of lyrics from a number of artists. For this purpose, TextFileGenerator.java and WebCrawler.java scrape the site https://www.musica.com for an artist's lyrics and store them in a .txt file. TextFileGenerator makes use of the Jsoup library to parse and remove unwanted HTML.

The module removeLang.py polishes our data with the use of the langdetect library by removing lyrics in foreign languages such as English or Portuguese, which would artificially inflate an artist's word count.

The file is then processed with python's NLTK library. The module analyse.py creates Text objects for each file and performs a simple tokenizing process to retrieve the unique word counts for each artist. Both .py files require the python3 command.

The results are then saved to results.csv and plotted with the C3 JavaScript library using code from the following repository as a template:

https://github.com/huyle333/graphs-from-csv

The final graph can be visualized with the commands:
```
cd plot/
python -m SimpleHTTPServer
```
And then visiting http://localhost:8000

![Alt text](/screenshot.png)
