package assignment4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Assign4JUnitTest {
    
    //used A4-words.txt as dictionary file since we didn't know if it was supposed to 
    //be a command line argument for JUnit tests
	WordLadderSolver wordLadderSolver = new WordLadderSolver("A4-words.txt");

	
	@Test
	public void ladderExistsTest() 
			throws NoSuchLadderException, IllegalArgumentException 
	{
	    String one = "smart";
	    String two = "money";
		List<String> wordLadder = new ArrayList<String>();
		try
		{
			wordLadder = wordLadderSolver.computeLadder(one, two);
		}
		catch(SameWordException e)
        {
            assert(false);
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
	    String one = "hello";
        String two = "world";
		try
		{
			List <String> wordLadder = wordLadderSolver.computeLadder(one, two);
			fail("Expected a NoSuchLadderException to be thrown");
		}
		catch(SameWordException e)
        {
            assert(false);
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
	public void nullStringsTest()
	{
	    String one = "";
        String two = "";
		try
		{
			List<String> wordLadder = wordLadderSolver.computeLadder(one, two);
			fail("Expected an IllegalArgumentException to be thrown");
		}
		catch(SameWordException e)
        {
            assert(false);
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
	
	@Test
	public void nullStringTest2()
	{
	    String one = "world";
        String two = "";
        try
        {
            List<String> wordLadder = wordLadderSolver.computeLadder(one, two);
            fail("Expected an IllegalArgumentException to be thrown");
        }
        catch(SameWordException e)
        {
            assert(false);
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
	
	@Test
	public void nullStringTest3()
    {
        String one = "";
        String two = "world";
        try
        {
            List<String> wordLadder = wordLadderSolver.computeLadder(one, two);
            fail("Expected an IllegalArgumentException to be thrown");
        }
        catch(SameWordException e)
        {
            assert(false);
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
	
	@Test
	public void invalidWordTest1()
    {
        String one = "ryan";
        String two = "world";
        try
        {
            List<String> wordLadder = wordLadderSolver.computeLadder(one, two);
            fail("Expected an IllegalArgumentException to be thrown");
        }
        catch(SameWordException e)
        {
            assert(false);
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
	
	@Test
	public void invalidWordTest2()
    {
        String one = "world";
        String two = "ryan";
        try
        {
            List<String> wordLadder = wordLadderSolver.computeLadder(one, two);
            fail("Expected an IllegalArgumentException to be thrown");
        }
        catch(SameWordException e)
        {
            assert(false);
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
	
	@Test
    public void notFiveLettersTest1()
    {
        String one = "boom";
        String two = "world";
        try
        {
            List<String> wordLadder = wordLadderSolver.computeLadder(one, two);
            fail("Expected an IllegalArgumentException to be thrown");
        }
        catch(SameWordException e)
        {
            assert(false);
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
	
	@Test
    public void notFiveLettersTest2()
    {
        String one = "world";
        String two = "boom";
        try
        {
            List<String> wordLadder = wordLadderSolver.computeLadder(one, two);
            fail("Expected an IllegalArgumentException to be thrown");
        }
        catch(SameWordException e)
        {
            assert(false);
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
	
	@Test
    public void notFiveLettersTest3()
    {
        String one = "world";
        String two = "worlds";
        try
        {
            List<String> wordLadder = wordLadderSolver.computeLadder(one, two);
            fail("Expected an IllegalArgumentException to be thrown");
        }
        catch(IllegalArgumentException e)
        {
            assert(true);
        }
        catch(SameWordException e)
        {
            assert(false);
        }
        catch(NoSuchLadderException e)
        {
            assert(false);
        }
    }
	
	@Test
    public void notFiveLettersTest4()
    {
        String one = "worlds";
        String two = "world";
        try
        {
            List<String> wordLadder = wordLadderSolver.computeLadder(one, two);
            fail("Expected an IllegalArgumentException to be thrown");
        }
        catch(SameWordException e)
        {
            assert(false);
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
	
	@Test
    public void sameWordTest()
    {
        String one = "world";
        String two = "world";
        try
        {
            List<String> wordLadder = wordLadderSolver.computeLadder(one, two);
            fail("Expected an IllegalArgumentException to be thrown");
        }
        catch(SameWordException e)
        {
            assert(true);
        }
        catch(IllegalArgumentException e)
        {
            assert(false);
        }
        catch(NoSuchLadderException e)
        {
            assert(false);
        }
    }


}
