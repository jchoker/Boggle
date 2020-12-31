/**
 * This file contains an implementation of the Boggle game board by applying DFS on each word to find on the 2D array
 * with a runtime of O(lmn8^k) where l is no. of words to find and k the average length of the words.
 * The best runtime among 3 algorithms / beats the majority of online submissions
 * @author J. Choker, jalal.choker@gmail.com
 */

package main;

import java.util.ArrayList;

public class Boggle_BetterRuntime {
	
	public static ArrayList<String> findWords(char[][] boggle, String[] words) {		
	
		if(boggle == null) throw new IllegalArgumentException("boggle cannot be null");

		var m = boggle.length;
		var n = boggle[0].length;
        
        var result = new ArrayList<String>();
        
        for(var w : words) {
            var found = false; // reset found for each new word
    		for(var i = 0; i < m; i++)
    		{
    			if(found) break;
    					
    			for(var j = 0; j < n; j++)			
    				if(dfs(boggle, new BoardIndex(i, j), w, 0))
    				{
    					result.add(w);
        				found = true; // to break from outer for loop
        				break; // break from inner for loop
    				}
    		}
        }
		
		return result;
	}

	private static boolean dfs(char[][] boggle, BoardIndex cell, String word, int idx) {

		
		var ch = boggle[cell.row()][cell.col()];
		
		if(ch != word.charAt(idx)) return false;
		
		if(idx == word.length()-1) return true;

		boggle[cell.row()][cell.col()] = '*'; // mark
		
		var toVisit = BoggleHelper.getUnvisitedNeighbors(cell, boggle);
		
		var found = false;
		
		for(var n : toVisit)
			if(dfs(boggle, n, word, idx + 1))
			{
				found = true;
				break;
			}
		
		boggle[cell.row()][cell.col()] = ch; // finally unmark

		return found;
	}
}
