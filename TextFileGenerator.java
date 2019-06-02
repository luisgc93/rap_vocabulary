package lyricsToTextFile;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TextFileGenerator {

	/*
	 * For a given URL, parse the html code and output a .txt file with the lyrics
	 */

	public static void urlToTextFile(HashSet<String> urls) throws IOException {
		System.out.println(urls.size());
		String url1 = urls.stream().findFirst().get();
		Document docName = Jsoup.connect(url1).get();
		String filename = docName.title();
		if(filename.contains(" | Musica.com") && filename.contains("-")) {
			filename = docName.title()
					.replace(" | Musica.com", "")
					.split("- ")[1] + ".txt";
		}

		File file = new File(filename);
		for(String url : urls) {

			BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
			String SongTitle = Jsoup.connect(url).get().title();
			System.out.println(SongTitle);
			String body = Jsoup.connect(url).get().body().text();
			bw.newLine();
			// Line separator at the beginning of each song
			bw.write("---------------------------------------------------------------");
			bw.newLine();
			if(body.contains("LETRA"))
				body = body.split("LETRA")[1];
			if(body.contains("fuente: musica.com"))
				body = body.split("fuente: musica.com")[0];

			bw.write(body);
			bw.close();
		}
	}

	public static void main(String [] args) throws IOException {


	String [] artist_urls = {"https://www.musica.com/letras.asp?letras=16402&orden=alf",
			"https://www.musica.com/letras.asp?letras=12141&orden=alf",
				"https://www.musica.com/letras.asp?letras=16461&orden=alf",
				"https://www.musica.com/letras.asp?letras=16575&orden=alf",
				"https://www.musica.com/letras.asp?letras=21395&orden=alf",
				"https://www.musica.com/letras.asp?letras=10116&orden=alf",
				"https://www.musica.com/letras.asp?letras=18256&orden=alf",
				"https://www.musica.com/letras.asp?letras=36433&orden=alf",
				"https://www.musica.com/letras.asp?letras=30927&orden=alf",
				"https://www.musica.com/letras.asp?letras=44427&orden=alf",
				"https://www.musica.com/letras.asp?letras=31606&orden=alf",
				"https://www.musica.com/letras.asp?letras=16440&orden=alf",
				"https://www.musica.com/letras.asp?letras=21764&orden=alf",
				"https://www.musica.com/letras.asp?letras=23501&orden=alf",
				"https://www.musica.com/letras.asp?letras=17503&orden=alf",
				"https://www.musica.com/letras.asp?letras=53279&orden=alf",
				"https://www.musica.com/letras.asp?letras=52363&orden=alf",
				"https://www.musica.com/letras.asp?letras=20216&orden=alf",
				"https://www.musica.com/letras.asp?letras=3605&orden=alf",
				"https://www.musica.com/letras.asp?letras=21112&orden=alf",
				"https://www.musica.com/letras.asp?letras=19312&orden=alf",
				};

	for(String url: artist_urls) {

			HashSet<String> urls = WebCrawler.collectUrls(url);
			urlToTextFile(urls);
		}
	}
}
