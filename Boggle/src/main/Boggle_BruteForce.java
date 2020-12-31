/**
 * This file contains an implementation of the Boggle game board using depth-first-search(DFS) recursive brute force approach  with a runtime of O(mn8^mn) - easily exceeds time limit (TL).
 * https://en.wikipedia.org/wiki/Boggle
 * @author J. Choker, jalal.choker@gmail.com
 */

package main;

import java.util.ArrayList;

public class Boggle_BruteForce {
	
	public static ArrayList<String> findWords(char[][] boggle, String[] words) {
		
		if(boggle == null) throw new IllegalArgumentException("boggle cannot be null");		
		if(words == null) return new ArrayList<String>(); // special case		
		
		var rows = boggle.length;
		var cols = boggle[0].length;
		
		var allCombinations = new ArrayList<String>(); // store all possible string combinations in the matrix
		var seen = new boolean[rows][cols];		
		
		for(var i = 0; i < rows; i++)
			for(var j = 0; j < cols; j++)
			{
				boggleDfs(boggle, new BoardIndex(i, j), "", allCombinations, seen);
			}
		
		var result = new ArrayList<String>();
		
		for(var word : words)
			if(allCombinations.contains(word)) result.add(word);		
		
		return result;
	}
	
	private static void boggleDfs(char[][] boggle, BoardIndex idx, String path, ArrayList<String> allCombinations, boolean[][] seen) {
		
		var ch = boggle[idx.row()][idx.col()];
		path += ch;
		allCombinations.add(path);
		boggle[idx.row()][idx.col()] = '*';

		var neighbors = BoggleHelper.getUnvisitedNeighbors(idx, boggle);
		
		for (var n : neighbors)		
			boggleDfs(boggle, n, path, allCombinations, seen);		
		
		boggle[idx.row()][idx.col()] = ch;
	}	
}
