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
		int offset = 0;
		String last;
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
					offset = 0;
					last = words[2];
					if(words[2] == "Jr."){
						last += " Jr.";
						offset = 1;
					}
					Player p1 = new Player(Integer.parseInt(words[0]), words[1], last, words[4 + offset].substring(0, 2));
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
				// Name, position, and team
				String[] Name = cols.get(1).select("a").get(0).text().split("\\s");
				String[] teamAndPos = cols.get(1).select("em").get(0).text().split("\\s");
				String pos = teamAndPos[0].toUpperCase();
				//passing yards
				String passingYds = cols.get(3).text();
				String passingTds = cols.get(4).text();
				String ints = cols.get(5).text();
				String rushingYds = cols.get(6).text();
				String rushingTds = cols.get(7).text();
				String recYds = cols.get(8).text();
				String recTds = cols.get(9).text();
				String fumbles = cols.get(12).text();
				String total = cols.get(13).text();
				String last = "";
				int off = 0;
				Player p1 = null;
				// Build last name
				for(int i = 1; i < Name.length; i++){
					last += Name[i];
					if (i + 1 != Name.length){
						last += " ";
					}
				}
				if(teamAndPos.length == 1){
					//              first,   last, pos, FPoints  passYards passTDs,    ints,   rushYards,     rushTDs,   recYards,     recTDs,      fumble
					p1 = new Player(Name[0], last, pos, total, passingYds, passingTds, ints, rushingYds, rushingTds, recYds, recTds, fumbles);
				} else {
					//              first,    last,     pos,        FPoints         passYards passTDs,          ints,        rushYards,     rushTDs,         recYards,     recTDs,      fumble        team
					p1 = new Player(Name[0], last, pos, total, passingYds, passingTds, ints, rushingYds, rushingTds, recYds, recTds, fumbles, teamAndPos[2].toUpperCase());
				}
				Player p2 = bst.get(Name[0] + last + pos + p1.team.toUpperCase());
				if(p2 != null){
					p1.rank = p2.rank;
					bst.remove(Name[0] + last);
					bst.put(Name[0] + last + pos + p1.team.toUpperCase(), p1);
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
