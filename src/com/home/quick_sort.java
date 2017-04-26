package com.home;
import java.util.ArrayList;

public class quick_sort {
	
	public ArrayList<Player> theList = null;
	
	public quick_sort( ArrayList<Player> newList){
		this.theList = newList;
	}
	
	public void quickSort(int low, int high){
		int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = this.theList.get(low + (high-low)/2).rank;

        // Divide into two lists
        while (i <= j) {
                while (this.theList.get(i).rank < pivot) {
                        i++;
                }
                while (this.theList.get(j).rank > pivot) {
                   j--;
                }
                
                if (i <= j) {
                        exchange(i, j);
                        i++;
                        j--;
                }
        }
        if (low < j)
        	quickSort(low, j);
        if (i < high)
        	quickSort(i, high);
	}
	
	private void exchange(int i, int j) {
        Player temp = this.theList.get(i);
        this.theList.set(i, this.theList.get(j));
        this.theList.set(j, temp);
	}
	
}