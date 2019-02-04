from nltk.corpus import PlaintextCorpusReader
import nltk

# root folder for our .txt files
corpus_root = '/home/l21009103/Desktop/Bham_CS/NLTK/lyrics2/lyrics'

# This is an object of type PlaintextCorpusReader, it is NOT indexed
rap_corpus = PlaintextCorpusReader(corpus_root, '.*')

# Prints the names of all the files that form the corpus
#print(rap_corpus.fileids())

# Add Pushkin and Oxxymiron Russian

# Creating all of our Text objects
akapellah = nltk.Text(nltk.word_tokenize(rap_corpus.raw('akapellah.txt')))[0:35000]
akil_ammar = nltk.Text(nltk.word_tokenize(rap_corpus.raw('akil-ammar.txt')))[0:35000]
aldeanos = nltk.Text(nltk.word_tokenize(rap_corpus.raw('aldeanos.txt')))[0:35000]
arma_blanca = nltk.Text(nltk.word_tokenize(rap_corpus.raw('arma-blanca.txt')))[0:35000]
bad_bunny = nltk.Text(nltk.word_tokenize(rap_corpus.raw('bad-bunny.txt')))[0:35000]
cartel_de_santa = nltk.Text(nltk.word_tokenize(rap_corpus.raw('cartel-de-santa.txt')))[0:35000]
# Reference objects (El Quijote & Shakespeare)
cervantes = nltk.Text(nltk.word_tokenize(rap_corpus.raw('quijote.txt')))[0:35000]
chojin = nltk.Text(nltk.word_tokenize(rap_corpus.raw('chojin.txt')))[0:35000]
control_machete = nltk.Text(nltk.word_tokenize(rap_corpus.raw('control-machete.txt')))[0:35000]
duo_kie = nltk.Text(nltk.word_tokenize(rap_corpus.raw('duo-kie.txt')))[0:35000]
falsa_alarma = nltk.Text(nltk.word_tokenize(rap_corpus.raw('falsa-alarma.txt')))[0:35000]
kase_o = nltk.Text(nltk.word_tokenize(rap_corpus.raw('kase-o.txt')))[0:35000]
nach = nltk.Text(nltk.word_tokenize(rap_corpus.raw('nach.txt')))[0:35000]
porta = nltk.Text(nltk.word_tokenize(rap_corpus.raw('porta.txt')))[0:35000]
rapsusklei = nltk.Text(nltk.word_tokenize(rap_corpus.raw('rapsusklei.txt')))[0:35000]
residente = nltk.Text(nltk.word_tokenize(rap_corpus.raw('residente.txt')))[0:35000]
santa_rm = nltk.Text(nltk.word_tokenize(rap_corpus.raw('santa-rm.txt')))[0:35000]
sfdk = nltk.Text(nltk.word_tokenize(rap_corpus.raw('sfdk.txt')))[0:35000]
tego_calderon = nltk.Text(nltk.word_tokenize(rap_corpus.raw('tego-calderon.txt')))[0:35000]
tote_king = nltk.Text(nltk.word_tokenize(rap_corpus.raw('tote-king.txt')))[0:35000]
tres_coronas = nltk.Text(nltk.word_tokenize(rap_corpus.raw('tres-coronas.txt')))[0:35000]
vico_c = nltk.Text(nltk.word_tokenize(rap_corpus.raw('vico-c.txt')))[0:35000]
violadores_del_verso = nltk.Text(nltk.word_tokenize(rap_corpus.raw('violadores-del-verso.txt')))[0:35000]
zenit = nltk.Text(nltk.word_tokenize(rap_corpus.raw('zenit.txt')))[0:35000]
zpu = nltk.Text(nltk.word_tokenize(rap_corpus.raw('zpu.txt')))[0:35000]


#file = open('lyrics/violadores-del-verso.txt', 'r')

#line = file.readline()
#print(detect(rap_corpus.raw('violadores_del_verso.txt')))

dict = {'Akapellah':akapellah, 'Akil Ammar':akil_ammar,'Aldeanos': aldeanos, 'Bad Bunny': bad_bunny,
'Cartel de Santa' : cartel_de_santa, 'Cervantes': cervantes, 'Chojin': chojin, 'Control Machete': control_machete,
'Duo Kie': duo_kie, 'Falsa Alarma': falsa_alarma, 'Kase.O': kase_o, 'Nach': nach, 'Porta':porta,
'Rapsusklei':rapsusklei, 'Residente':residente, 'Santa RM': santa_rm, 'SFDK':sfdk, 'Tego Calderon':tego_calderon,
'Tote King':tote_king, 'Tres Coronas':tres_coronas, 'Vico C': vico_c, 'Violadores del Verso':violadores_del_verso }
