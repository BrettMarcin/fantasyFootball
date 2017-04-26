package com.home;

import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class theData {
	public String theWord;

	
	public theData() throws IOException{
		
		Document doc = Jsoup.connect("http://fantasy.nfl.com/research/scoringleaders").get();
		//String title = doc.title();
		Element link = doc.getElementsByTag("p").first();
		this.theWord = link.text();
		
	}
	
	public HashSet<Player> getRankingPlayers(){
		//call https://www.fantasypros.com/nfl/rankings/consensus-cheatsheets.php?partner=cbs_nfl_rankings_pre_p
		
		
		return null;
	}
	
	public HashSet<Player> getRankingPlayers(HashSet<Player> original){
		//call http://fantasy.nfl.com/research/scoringleaders#researchScoringLeaders=researchScoringLeaders%2C%2Fresearch%2Fscoringleaders%253Foffset%253D26%2526position%253DO%2526sort%253Dpts%2526statCategory%253Dstats%2526statSeason%253D2016%2526statType%253DseasonStats%2526statWeek%253D17%2Creplace
		//original.clone()
		return null;
	}
}
