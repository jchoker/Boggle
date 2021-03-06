package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Boggle_BruteForce;

public class Boggle_BruteForceTest {

	private char[][] boggle;
	
	@BeforeEach
	public void setUp() throws Exception {
		boggle = new char[][]
				{
				  {'s','a','k'},
				  {'d','d','q'},
				  {'r','e','f'}
				};
	}
	
	@Test
	public void testFindAllWords() {
		var words = new String[] {"add","sad","dad", "sake"};
		
		var res = Boggle_BruteForce.findWords(boggle, words);		
		
		assertEquals(3, res.size());
		
		assertArrayEquals(new String[] {"add","sad","dad"}, res.toArray());
	}
}
