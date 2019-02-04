from langdetect import detect, detect_langs
import langid
import os
import glob

# Language detection works best when distinguishing between only 2 languages
# Run this method twice: one to remove English, then to remove Portuguese
def foreignDetect(string, lang):
    langid.set_languages(['es', lang])  # ISO 639-1 codes
    lang, score = langid.classify(string)
    return lang


def removeForeign(filename):
    f1 = open(filename, 'r')
    f2 = open(os.path.join('lyrics2',filename), 'w+')
    lines = (line.rstrip() for line in f1)
    for line in lines:
        if foreignDetect(line, 'en')!= 'es' or foreignDetect(line, 'pt')!= 'es':
            print(line)
        else:
            f2.write(line)
            f2.write("\n")
    f1.close()
    f2.close()

path = 'lyrics'

for filename in glob.glob(os.path.join(path, '*.txt')):
    removeForeign(filename)
