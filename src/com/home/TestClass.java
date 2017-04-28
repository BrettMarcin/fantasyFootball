package com.home;

import java.io.IOException;
import java.util.HashSet;

import javax.swing.text.html.CSS.Attribute;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestClass {

	public static void main(String[] args) {
		HomeController home = new HomeController();
		Document doc = null;
		try {
			doc = Jsoup.connect("https://www.fantasypros.com/nfl/rankings/consensus-cheatsheets.php?partner=cbs_nfl_rankings_pre_p").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element table = doc.select("table").get(0); 
		Elements rows = table.select("tr");
		String[] words;
		/*for(int i = 2; i < rows.size(); i++){
			Element row = rows.get(i);
			Elements cols = row.select("td");
			String text = cols.text();
			boolean tierCheck = text.contains("Tier");
			
			if(tierCheck != true){
				words=text.split("\\s");
				/*System.out.println("size: " + words.length);
				try{
					System.out.println("Rank: " + words[0] + ", First: " + words[1] + ", Last: " + words[2]);
				}catch(Exception e){
					
				}
				
				try{
					Player p1 = new Player(Integer.parseInt(words[0]), words[1], words[2]);
					home.setHash(p1);
				}catch(Exception e){
					
				}
				HashSet<Player> set = HomeController.getHash();
				for(Player p:set){  
					System.out.println(p.rank + " " + p.first + " " + p.last);  
				} 
				
			}
		}*/
		String url = "http://fantasy.nfl.com/research/scoringleaders?offset=01&position=O&sort=pts&statCategory=stats&statSeason=2016&statType=seasonStats&statWeek=1";
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table = doc.select("table").get(0); 
		rows = table.select("tr");
		for(int k = 2; k < rows.size(); k++){
			Element row = rows.get(k);
			Elements cols = row.select("td");
			String text = cols.text();
			words = text.split("\\s");
			for(int z = 0; z < words.length; z++){
				System.out.print(words[z]);
			}
			System.out.println("");
			//System.out.println(url);
		}
		String secondHalf = "&position=O&sort=pts&statCategory=stats&statSeason=2016&statType=seasonStats&statWeek=1";
		int location = 26;
		while (location < 100){
			url = "http://fantasy.nfl.com/research/scoringleaders?offset=";
			url = url + location;
			url = url + secondHalf;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			table = doc.select("table").get(0); 
			rows = table.select("tr");
			for(int k = 2; k < rows.size(); k++){
				Element row = rows.get(k);
				Elements cols = row.select("td");
				String text = cols.text();
				words = text.split("\\s");
				for(int z = 0; z < words.length; z++){
					System.out.print(words[z]);
				}
				System.out.println("");
				//System.out.println(url);
			}
			location += 25;
		}
		while(location < 627){
			url = "http://fantasy.nfl.com/research/scoringleaders?offset=";
			url = url + location;
			url = url + secondHalf;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			table = doc.select("table").get(0); 
			rows = table.select("tr");
			for(int k = 2; k < rows.size(); k++){
				Element row = rows.get(k);
				Elements cols = row.select("td");
				String text = cols.text();
				words = text.split("\\s");
				for(int z = 0; z < words.length; z++){
					System.out.print(words[z]);
				}
				System.out.println("");
			}
			location++;
		}	
	}

}
