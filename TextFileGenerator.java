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
		String filename = docName.title()
				.replace(" | Musica.com", "")
				.split("- ")[1] + ".txt";
		System.out.println(filename);

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

	HashSet<String> urls = WebCrawler.collectUrls("https://www.musica.com/letras.asp?letras=16402&orden=alf");
	urlToTextFile(urls);

	}
}
