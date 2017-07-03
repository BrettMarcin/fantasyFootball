package com.home;


import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class theData {
	
	public theData(){
		
	}
	
	/*
	public static void main(String[] args) throws IOException
	{
		TreeMap<String,Player>  bst = getPlayers();
		Iterator itr = bst.values().iterator();
		while(itr.hasNext()){
			Player thePlayer = (Player)itr.next();
			System.out.println("THe rank: " + Integer.toString(thePlayer.rank) + " name: " + thePlayer.first + " " + thePlayer.last + " passYds: " + thePlayer.passYards + " rushYds: " + thePlayer.rushYards);
		}
	}
	*/
	
	public static TreeMap<String,Player> getRankingPlayers() throws IOException{
		TreeMap<String,Player> playersOpen = new TreeMap<String,Player>();
		Document doc = Jsoup.connect("https://www.fantasypros.com/nfl/rankings/consensus-cheatsheets.php?partner=cbs_nfl_rankings_pre_p").get();
		Element table = doc.select("table").get(0); 
		Elements rows = table.select("tr");
		for(int i = 2; i < rows.size(); i++){
			Element row = rows.get(i);
			Elements cols = row.select("td");
			String text = cols.text();
			boolean tierCheck = text.contains("Tier");
			if(tierCheck != true){
				String[] words=text.split("\\s");
				try{
					String name = words[2];
					Player p1 = new Player(Integer.parseInt(words[0]), words[1], name, words[4].substring(0, 2));
					playersOpen.put(p1.first + p1.last + p1.pos + words[3].toUpperCase(), p1);
				}catch(Exception e){
					
				}
			}
		}
		return playersOpen;
	}
	public static TreeMap<String,Player> getPlayerInfo(TreeMap<String,Player> bst) throws IOException{
		int location = 1;
		String url = null;
		Document doc = null;
		String words[];
		while(location < 627){
			url = "http://fantasy.nfl.com/research/scoringleaders?offset=";
			url = url + location;
			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Element table = doc.select("table").get(0); 
			Elements rows = table.select("tr");
			for(int k = 2; k < rows.size(); k++){
				Element row = rows.get(k);
				Elements cols = row.select("td");
				String text = cols.text();
				words = text.split("\\s");
				int off = 0;
				Player p1 = null;
				if(words.length < 18){
					off = -2;
					//              first,    last,     pos,        FPoints         passYards passTDs,          ints,        rushYards,     rushTDs,         recYards,     recTDs,      fumble
					p1 = new Player(words[1], words[2], words[3], words[17+off], words[7+off], words[8+off], words[9+off], words[10+off], words[11+off], words[12+off], words[13+off], words[16+off]);
				} else {
					//              first,    last,     pos,        FPoints         passYards passTDs,          ints,        rushYards,     rushTDs,         recYards,     recTDs,      fumble        team
					p1 = new Player(words[1], words[2], words[3], words[17+off], words[7+off], words[8+off], words[9+off], words[10+off], words[11+off], words[12+off], words[13+off], words[16+off], words[5].toUpperCase());
				}
				Player p2 = bst.get(words[1] + words[2] + words[3].toUpperCase() + p1.team.toUpperCase());
				if(p2 != null){
					p1.rank = p2.rank;
					bst.remove(words[1] + words[2]);
					bst.put(words[1] + words[2] + words[3].toUpperCase() + p1.team.toUpperCase(), p1);
				}
			}
			location+= 25;
		}
		return bst;
	}
	
	public static ArrayList<Player> getPlayers() throws IOException{
		TreeMap<String,Player> bst = getRankingPlayers();
		bst = getPlayerInfo(bst);
		ArrayList<Player> list = new ArrayList<Player>(bst.values());
		return list;
	}
}
