package assignment4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Assign4JUnitTest {

	WordLadderSolver wordLadderSolver = new WordLadderSolver("A4-words.txt");
	String one = "smart";
	String two = "money";
	
	String three = "hello";
	String four = "world";
	
	String five = "";
	String six = "";
	
	@Test
	public void normalTest() 
			throws NoSuchLadderException, IllegalArgumentException 
	{
		List<String> wordLadder = new ArrayList<String>();
		try
		{
			wordLadder = wordLadderSolver.computeLadder(one, two);
		}
		catch(NoSuchLadderException e)
		{
			assert(false);
		}
		catch(IllegalArgumentException e)
		{
			assert(false);
		}
		assert(wordLadderSolver.validateResult(one, two, wordLadder));
	}
	
	@Test
	public void noLadderTest()
	{
		try
		{
			List <String> wordLadder = wordLadderSolver.computeLadder(three, four);
		}
		catch(IllegalArgumentException e)
		{
			assert(false);
		}
		catch(NoSuchLadderException e)
		{
			assert(true);
		}
		
	}
	
	@Test
	public void invalidInputTest()
	{
		try
		{
			List<String> wordLadder = wordLadderSolver.computeLadder(five, six);
		}
		catch(IllegalArgumentException e)
		{
			assert(true);
		}
		catch(NoSuchLadderException e)
		{
			assert(false);
		}
	}

}
