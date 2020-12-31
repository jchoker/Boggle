package main;

import java.util.ArrayList;

class BoggleHelper
{
	static ArrayList<BoardIndex> getUnvisitedNeighbors(BoardIndex cell, char[][] boggle) {
		
		var neighbors = new ArrayList<BoardIndex>();		
		
		// move clockwise starting from North
		var dirs = new int[][] {{-1, 0,},{-1, 1,},{ 0, 1}, { 1, 1}, { 1, 0,},{ 1, -1},{ 0, -1},{ -1, -1}};

		for (var d : dirs)
		{			
			var neighbor = new BoardIndex(cell.row() + d[0], cell.col() + d[1]) ;
			
			if(isSafe(neighbor, boggle ) && boggle[neighbor.row()][neighbor.col()] != '*')
				neighbors.add(neighbor);		
 		}
		
		return neighbors;
	}	
	
	private static boolean isSafe(BoardIndex idx, char[][] boggle) {
		
		var rows = boggle.length;
		var cols = boggle[0].length;
		
		return (idx.row() >= 0 && idx.row() < rows) && (idx.col() >= 0 && idx.col() < cols);		
	}
}

//convenience immutable class to reference 2D cells
class BoardIndex
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
