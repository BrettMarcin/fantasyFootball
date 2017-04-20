package com.home;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class theData {
	public String theWord;
	
	public theData() throws IOException{
		Document doc = Jsoup.connect("http://www.espn.com/").get();
		Element link = doc.getElementsByTag("p").first();
		this.theWord = link.text();
	}
	
}
