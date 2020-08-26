/**
 * This file contains an implementation of the Boggle game board using depth-first-search(DFS) recursive brute force approach.
 * https://en.wikipedia.org/wiki/Boggle
 * @author J. Choker, jalal.choker@gmail.com
 */

package main;

import java.util.ArrayList;

public class Boggle {
	
	public static ArrayList<String> findAllWords(char[][] boggle, String[] words) {
		
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
			
		path += boggle[idx.row()][idx.col()];
		allCombinations.add(path);
		seen[idx.row()][idx.col()] = true;

		var neighbors = getNeighbors(idx, boggle);
		
		for (var n : neighbors) {
			
			if(!seen[n.row()][n.col()])
				boggleDfs(boggle, n, path, allCombinations, seen);
		}
		
		seen[idx.row()][idx.col()] = false;
	}
	
	private static ArrayList<BoardIndex> getNeighbors(BoardIndex idx, char[][] boggle) {
	
		var neighbors = new ArrayList<BoardIndex>();		
		
		// move clockwise starting from North
		var rMove = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
		var cMove = new int[] {0, 1, 1, 1, 0, -1, -1, -1};

		for (int i = 0; i < 8; i++)
		{			
			var neighbor = new BoardIndex(idx.row() + rMove[i], idx.col() + cMove[i]) ;
			
			if(isSafe(neighbor, boggle ))
				neighbors.add(neighbor);		
 		}
		
		return neighbors;
	}	
	
	private static boolean isSafe(BoardIndex idx, char[][] boggle) {
		
		var rows = boggle.length;
		var cols = boggle[0].length;
		
		return (idx.row() >= 0 && idx.row() < rows) && (idx.col() >= 0 && idx.col() < cols);		
	}
	
	// private convenience immutable class for referencing 2D cells
	private static class BoardIndex
	{
		public BoardIndex(int row, int col)
		{
			this.row = row;
			this.col = col;
		}
		
		private int row;
		private int col;
	
		public int row() { return row; }
		public int col() { return col; }
	}
}
