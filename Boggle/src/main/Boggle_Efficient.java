/**
 * This file contains an implementation of the Boggle game board using a custom Trie data structure with a runtime that doesn't exceed TL.
 * @author J. Choker, jalal.choker@gmail.com
 */

package main;

import java.util.ArrayList;
import java.util.HashSet;

public class Boggle_Efficient {
	public static ArrayList<String> findWords(char[][] boggle, String[] words) {
		if(boggle == null) throw new IllegalArgumentException("boggle cannot be null");

		var m = boggle.length;
		var n = boggle[0].length;
        
        var trie = new Trie();
        for(var w : words) trie.insert(w);
		
        var result = new HashSet<String>();
        
		for(var i = 0; i < m; i++)
			for(var j = 0; j < n; j++)			
				dfs(boggle, new BoardIndex(i, j), "", trie, result);				
		
		return new ArrayList<String>(result);
	}	
    
	private static void dfs(char[][] boggle, BoardIndex cell, String pref, Trie trie, HashSet<String> result) {
		
        var ch = boggle[cell.row()][cell.col()];
        pref += ch;
        if(!trie.startsWith(pref)) return;
        
        boggle[cell.row()][cell.col()]='*'; // mark visited
        
        if(trie.contains(pref)) result.add(pref);

        var neighbors = BoggleHelper.getUnvisitedNeighbors(cell, boggle);        
        for(var n : neighbors)
		  dfs(boggle, n, pref, trie, result);
        
        boggle[cell.row()][cell.col()] = ch; // unmark visited
	}
}

class Trie
{
    class TrieNode
    {
        TrieNode[] Children = new TrieNode[26];
        boolean IsEnd;
    }
    
    final TrieNode root = new TrieNode();
    
    public void insert(String word)
    {
        var node = root;
        for (int i = 0; i < word.length(); i++){
            var ch = word.charAt(i); 
            if(node.Children[ch - 'a'] == null) node.Children[ch - 'a']  = new TrieNode();
            node = node.Children[ch - 'a'];
            }
        node.IsEnd = true;
    }
    
    public boolean contains(String word)
    {
        var node = root;
        for (int i = 0; i < word.length(); i++){
            var ch = word.charAt(i); 
            if(node.Children[ch - 'a'] == null) return false;
            node = node.Children[ch - 'a'];
            }
        return node.IsEnd;
    }
    
    public boolean startsWith(String prf)
    {
        var node = root;
        for (int i = 0; i < prf.length(); i++){
            var ch = prf.charAt(i); 
            if(node.Children[ch - 'a'] == null) return false;
            node = node.Children[ch - 'a'];
            }
        return true;
    }
}
