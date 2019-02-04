package lyricsToTextFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Collects all urls containing lyrics from this site:
 * https://www.musica.com/letras.asp?letras=16402
 * @author l21009103
 *
 */
public class WebCrawler {
	
	public static ArrayList<String> chopOff(String string, String chop1, String chop2,
			ArrayList <String> urlList) {
        String[] parts = string.split(chop1);
        for(String part:parts) {
        	if(part.contains("letras.asp?letra=")) {
        		String result = part.
        				split(chop2)[0].replaceAll("\"", "");
        		urlList.add("https://www.musica.com/" + result);
        	}        	
        }  
        return urlList;   
    }
	
	/**
	 * Removes duplicate url's from our ArrayList
	 * @param urlList
	 * @return urlList without duplicates
	 */
	public static ArrayList<String> removeDuplicates(ArrayList<String> urlList){
		
		Set<String> urls = new HashSet<>(urlList);
		urlList.clear();
		urlList.addAll(urls);
		
		return urlList;
	}
	
	public static ArrayList<String> collectUrls(String urlString){
		String readString;
		ArrayList <String> urlList = new ArrayList<String>();
		try {
			URL url = new URL(urlString);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			BufferedWriter bw = new BufferedWriter(new FileWriter("string.txt"));
			while ((readString = in.readLine()) != null) {
				
				if(readString.contains("letras.asp?letra=")) {
					bw.write(readString);
					bw.newLine();				
					urlList = chopOff(readString, "<a href=", ">",urlList);	
					}				
				}	
			}
			catch (IOException e) {
				
				System.out.println("no access to URL: " + urlString);
			}
		removeDuplicates(urlList);
		return urlList;
		
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(collectUrls("https://www.musica.com/letras.asp?letras=16402").size());
		
		
	}

}
