from langdetect import detect, detect_langs
import langid
import os
import glob

# This module removes foreign languages from our corpus files

# Language detection works best when distinguishing between only 2 languages
# Run this method twice: one to remove English, then to remove Portuguese
def langDetect(string, lang):
    langid.set_languages(['es', lang])  # ISO 639-1 language codes
    lang, score = langid.classify(string)
    return lang

def removeForeign(filename):
    with open(filename, "r") as f:
        lines = f.readlines()
    with open(filename, "w") as f:
        for line in lines:
            if line == '---------------------------------------------------------------\n':
                continue
            if langDetect(line, 'en') == 'es' and langDetect(line, 'pt') == 'es':
                f.write(line)
            else:
                print("FOREIGN LANGAUGE DETECTED: " + line)


def main():
    path = 'lyrics_raw'
    for filename in glob.glob(os.path.join(path, '*.txt')):
        print(filename)
        removeForeign(filename)

if __name__ == "__main__":
    main()