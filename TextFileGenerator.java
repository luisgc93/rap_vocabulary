package lyricsToTextFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import org.apache.tika.langdetect.OptimaizeLangDetector;
import org.apache.tika.language.detect.LanguageDetector;
import org.jsoup.Jsoup;

/**
 * Class must be able to collect song lyrics from a series or urls
 * and store them by generating a single .txt file
 * @author l21009103
 *
 */
public class TextFileGenerator {
	
	static boolean start = false;
	static boolean end = false;
	
	/**
	 * Sets the boolean variable start to true when the bufferedReader
	 * reads the first line of the lyrics
	 * @param line
	 */
	public static void lyricStart(String line) {
		
		if(line.contains("Letra de la canción")) {
			
			start = true;
		}
		
	}
	
	/**
	 * Sets the boolean variable end to true when the bufferedReader
	 * reads the final line of the lyrics
	 * @param line
	 */
	public static void lyricEnd(String line) {
		
		if(line.contains("fuente: musica.com")) {
			
			end = true;
		}
		
	}
	
	/**
	 * Returns the ISO language code for the target language
	 * @param text - the text to be analysed
	 * @return 
	 */

	public static String findLang(String text) {
		LanguageDetector detector = new OptimaizeLangDetector().loadModels();
		return detector.detect(text).getLanguage();
	}
	
	public static String concatenate(ArrayList<String> lines) {
		String s = "";
		
		for(String line:lines) {
			s+= "\n" + line;
		}
		
		return s;
		
	}
	
	public static void deleteForeign(ArrayList<String> foreign, File filename) {	
		String delete = concatenate(foreign);
		System.out.println("DELETE: " + delete);
		try {

			File temp = File.createTempFile("temp", ".txt");
			// reads "old" file with errors 
			BufferedReader in = new BufferedReader(new FileReader(filename));
			// writes into "new" file
			BufferedWriter bw = new BufferedWriter(new FileWriter(temp, true));
			String line;
			while((line = in.readLine()) != null) {
				// THIS CODE NEVER SEEMS TO EVALUATE TO TRUE!!?
				if(line.equals(delete)) {
					bw.write("DELETED");
					System.out.println(line);
					System.out.println(delete);
					line = line.replace(delete, "DELETED");
				}
				bw.write(line);
				bw.newLine();
			}
			
			in.close();
			bw.close();
			filename.delete();
			temp.renameTo(filename);
		}catch (IOException e) {
			
			System.out.println("Whoops");
		} 
		
		
	}
	
	/**
	 * Searchs the url for song lyrics, stores them in a .txt file and returns
	 * an array list of type string with content in a foreign language to be
	 * deleted by another function
	 * @param urlString
	 * @param filename
	 * @return
	 */
	public static ArrayList<String> urlToText(String urlString, File filename) {
		// Resets boolean variables
		start = false;
		end = false;
		ArrayList<String> foreign = new ArrayList<String>();
		String readString;
		try {
			URL url = new URL(urlString);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
			// Adds space and a separator between each song
			bw.newLine();
			bw.write("---------------------------------------------------------------");
			bw.newLine();
			// && end == false --> this makes the program work without having to catch an IO exception, not sure why
			readString = in.readLine();
			ArrayList<String> lines = new ArrayList<String>();
			while ((readString = in.readLine()) != null && end == false) {
				lyricStart(readString);
				
				// the second condition is so that the last bit doesn't get added twice
				if(start == true && !readString.contains("fuente: musica.com")) {			
					if(readString.contains("<h2>Letra de la canción</h2>")) {
						// removes introductory text before the lyrics start
						readString = readString.split("<h2>Letra de la canción</h2>")[1];
					}
					// remove unwanted HTML tokens from readString before writing it onto output.txt
					readString = Jsoup.parse(readString).text();
					// Filters out english lyrics (1st filter)
					if(!findLang(readString).equals("en")) {						
						bw.write(readString);
						bw.newLine();
					}
					
					lines.add(readString);
				}
				if(readString.contains("fuente: musica.com")) {
					// this makes end == true
					// somehow it also stops an exception being thrown & catched, not sure why
					lyricEnd(readString);
					bw.write(Jsoup.parse(readString.split("fuente: musica.com")[0]).text());
					bw.close();
				}
			}
			// deletes entire songs from our lyrics file if they are not in Spanish
			// here we can afford to be very selective as we have concatenated the strings
			// and the language detection should be very accurate
			if(!findLang(concatenate(lines)).equals("es")) {
				// THIS PART WORKS OK
				//System.out.println(concatenate(lines));
				//System.out.println(findLang(concatenate(lines)));
				foreign.add(concatenate(lines));			
			}
			
			bw.close();
			in.close();
		}catch (IOException e) {
			
			System.out.println("no access to URL: " + urlString);
		}
		return foreign;
	}
	
	public static void main(String[] args) {

		// url de todas las letras: https://www.musica.com/letras.asp?letras=16402
		File file = new File("bbb.txt");
		ArrayList<String> urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=16402");
		
		
		for(String url:urls) {
			ArrayList<String> f = urlToText(url, file);
			if(!f.isEmpty()) {
				System.out.println("SOME ERRORS...");
				deleteForeign(f, file);
			}
		}
		
		
		
		/*
		
		file = new File("sfdk.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=12141");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("falsa-alarma.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=16461");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("cpv.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=20329");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("arma-blanca.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=16575");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("crew-cuervos.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=36248");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("duo-kie.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=21395");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("nach.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=10116");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("residente.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=18256");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("kase-o.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=36433");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("aldeanos.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=30927");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("akapellah.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=44427");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("lil-supa.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=31606");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("portavoz.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=45376");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("aczino.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=33576");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		
		file = new File("tego-calderon.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=21764");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("vico-c.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=14736");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("chojin.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=23501");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("tres-coronas.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=17503");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("bad-bunny.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=53279");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("ana-tijoux.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=22842");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("c-tangana.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=52363");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("mala-rodriguez.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=52363");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("porta.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=20216");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		System.out.println("TWENTY FIVE");
		
		file = new File("cartel-de-santa.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=3605");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("zpu.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=21112");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("rapsusklei.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=22247");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("santa-rm.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=25937");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("tote-king.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=19312");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("control-machete.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=4089");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("control-machete.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=4089");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("zenit.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=15212");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("akil-ammar.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=22204");
		for(String url:urls) {
			urlToText(url, file);
		}
		
		file = new File("boca-floja.txt");
		urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=17392");
		for(String url:urls) {
			urlToText(url, file);
		}
		*/
	}

}
	
	
