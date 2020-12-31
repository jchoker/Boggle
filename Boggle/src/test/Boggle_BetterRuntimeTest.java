package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.Boggle_BetterRuntime;

public class Boggle_BetterRuntimeTest {

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
		{
			var words = new String[] {"add","sad","dad", "sake"};
			
			var res = Boggle_BetterRuntime.findWords(boggle, words);		
			
			assertEquals(3, res.size());
			
			assertArrayEquals(new String[] {"add", "sad", "dad"}, res.toArray());			
		}		
		{
			var words = new String[] {"a"};
			
			var res = Boggle_BetterRuntime.findWords( new char[][]{ {'a'}}, words);		
			
			assertEquals(1, res.size());
			
			assertArrayEquals(new String[] {"a"}, res.toArray());
		}
		{
			var words = new String[] {"a","b"};
			
			var res = Boggle_BetterRuntime.findWords( new char[][]{ {'a', 'b'}}, words);		
			
			assertEquals(2, res.size());
			
			assertArrayEquals(new String[] {"a","b"}, res.toArray());
		}
	}
}
