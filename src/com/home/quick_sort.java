package com.home;
import java.util.ArrayList;

public class quick_sort {
	
	public static ArrayList<Player> sort(ArrayList<Player> theList, int low, int high){
		int i = low, j = high;
        int pivot = theList.get(low + (high-low)/2).rank;

        while (i <= j) {
                while (theList.get(i).rank < pivot) {
                        i++;
                }
                while (theList.get(j).rank > pivot) {
                   j--;
                }
                
                if (i <= j) {
                		theList = exchange(theList, i, j);
                        i++;
                        j--;
                }
        }
        if (low < j)
        	theList = sort(theList, low, j);
        if (i < high)
        	theList = sort(theList, i, high);
        return theList;
	}
	
	private static ArrayList<Player> exchange(ArrayList<Player> theList, int i, int j) {
        Player temp = theList.get(i);
        theList.set(i, theList.get(j));
        theList.set(j, temp);
        return theList;
	}
	
}