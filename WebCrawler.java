package lyricsToTextFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;

/**
 * Collects all urls containing lyrics from the following site:
 * https://www.musica.com/
 */
public class WebCrawler {

	/**
	 * Helper method that processes a HashSet containing url's in String format
	 * and removes unwanted html
	 * @param string
	 * @param chop1
	 * @param chop2
	 * @param urlSet
	 * @return a HashSet with clean urls
	 */
	public static HashSet<String> chopOff(String string, String chop1, String chop2,
			HashSet<String> urlSet) {
        String[] parts = string.split(chop1);
        for(String part:parts) {
        	if(part.contains("letras.asp?letra=") && !part.contains("</aside>")) {
        		String result = part.
        				split(chop2)[0].replaceAll("\"", "");
        		urlSet.add("https://www.musica.com/" + result);
        	}
        }
        return urlSet;
    }


	/**
	 * @param urlString
	 * @return a HashSet with all the relevant urls linked to by urlString
	 */
	public static HashSet<String> collectUrls(String urlString){
		String readString;
		HashSet <String> urlSet = new HashSet<String>();
		try {
			URL url = new URL(urlString);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			while ((readString = in.readLine()) != null) {

				if(readString.contains("letras.asp?letra=")){
					urlSet = chopOff(readString, "<a href=", ">",urlSet);
					}
				}
			}
			catch (IOException e) {

				System.out.println("no access to URL: " + urlString);
			}

		return urlSet;
	}

	public static void main(String[] args) {
		HashSet<String> urls = collectUrls("https://www.musica.com/letras.asp?letras=16402&orden=alf");
		for(String url : urls)
			System.out.println(url);

	}
}
