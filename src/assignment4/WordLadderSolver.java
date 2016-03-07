/**
 * Classes to solve word ladders
 * Solves EE422C programming assignment #4
 * @author Tauseef Aziz
 * @author Trevor Eggenberger
 * @version 1.00 2016-03-06
 */


package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

// do not change class name or interface it implements
//Purpose: encapsulates the WordLadderSolver
public class WordLadderSolver implements Assignment4Interface
{
	// declare class members here.
	private List<String> solutionList;				//holds word ladder
	private HashSet<String> dict;					//holds dictionary
	private HashSet<String> visited;				//holds words that have already been tried
	
    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there
	/**
	 * Constructs a WordLadderSolver using the proved dictionary file
	 * @param fileName
	 */
	public WordLadderSolver(String fileName)
	{
		solutionList = new ArrayList<String>();
		dict = new HashSet<String>();
		visited = new HashSet<String>();
        try 
        {
        	//this portion constructs dictionary from provided dictionary file
            FileReader freader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(freader);
            String s = reader.readLine();
            while (s != null)
            {
                if (s.charAt(0) != '*')
                {		
                    dict.add(s.substring(0,5));
                }
                s = reader.readLine();
            }
            reader.close();
        } 
        catch (FileNotFoundException e) 
        {
            System.err.println ("Error: File not found. Exiting...");
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) 
        {
            System.err.println ("Error: IO exception. Exiting...");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    // do not change signature of the method implemented from the interface
	/**
	 * Attempts to a word ladder between two words
	 * @param startWord the start word of the ladder
	 * @param endWord the end word of the ladder
	 * @return ArrayList of Strings that contain the word ladder if one is found, throws error otherwise
	 */
    @Override
    public List<String> computeLadder(String startWord, String endWord) 
            throws NoSuchLadderException, IllegalArgumentException, SameWordException
    {
    	//conditional makes sure each word is a 5-letter word that can be found in the dictionary
        if (startWord.length() != 5 || endWord.length() != 5 || !(dict.contains(startWord)) || !(dict.contains(endWord))) 
        {
            throw new IllegalArgumentException("At least one of the words \'" + startWord + "\' or \'" + endWord +
                                                "\' is not a legitimate 5-letter word from the dictionary");
        }
        //if start word and end word are the same, then just print out word twice
        if(startWord.equals(endWord)) 
        {
            String s = ("For the input words \"" + startWord + "\" and \"" + endWord + "\":\n");
            throw new SameWordException(s + startWord + " and " + endWord + " are the same word\n\n*********");
        }
        //valid word ladder can be found -> return the word ladder
    	if(makeLadder(startWord, endWord, -1))
    	{
    		return solutionList;
    	}
    	
        //no valid word ladder found -> throw exception
    	else throw new NoSuchLadderException("For the input words \"" + startWord + "\" and \"" + endWord + "\":\n" + 
    			"There is no word ladder between " + startWord + " and " + endWord + "\n\n**********");
    }
	/**
	 * Recursive function that attempts to construct a word ladder between two words
	 * word ladder is placed in solutionList if one can be made
	 * @param fromWord the starting word
	 * @param toWord   the ending word
	 * @param index    the index of the character that was last changed to produce a new word
	 * @return true if a word ladder can be found, false otherwise
	 */
    public boolean makeLadder(String fromWord, String toWord, int index)
    {
        solutionList.add(fromWord);
        if (compareLetters(fromWord, toWord) == 1)
        {
            solutionList.add(toWord);
            return true;
        }
    	//conditional checks for words that have already been tried
        if (visited.contains(fromWord))
        {
            return false;
        }
    	visited.add(fromWord);

    	
    	/*
    	 * iterates through dictionary and adds one letter of difference words from fromWord
    	 * word cannot be obtained by changing the letter of the same index as the previous change
    	 * prepends these words with string representation of how many letters off from target
    	 * sorts the list in lexicographical order
    	 */
    	ArrayList<String> tempList = new ArrayList<String>();
        for(String s: dict)
        {
            int differenceToTarget = compareLetters(s, toWord);
            
            //conditional checks for only one letter difference between words, if the word is already in the solution list,
            //and checks if the letter changed to obtain word is the same index as the previous change
            if(compareLetters(fromWord, s) == 1 && !(solutionList.contains(s)) && getDifferenceIndex(fromWord, s) != index)
            {
                tempList.add(differenceToTarget + s);
            }
        }
        //sorts tempList and puts words closest to target at top of list
        Collections.sort(tempList);				
       
    	
    	//iterates through tempList and makes recursive calls to makeLadder to see if one of the words 
        //can form a valid word ladder
    	for(int i = 0; i < tempList.size(); i++)
    	{
    		if(makeLadder(tempList.get(i).substring(1), toWord, getDifferenceIndex(fromWord, tempList.get(i).substring(1))))
    		{
    			
    			return true;
    		}
    		//word is not a solution, so remove from solutionList
    		else solutionList.remove(tempList.get(i));			
    	}
    	//word cannot create a word ladder, so remove word from solutionList and return false
    	solutionList.remove(fromWord);							
    	return false;
    	
    }

    
	/**
	 * Finds and returns the index of the character that is different between the two words
	 * precondition: String one and String two must be the same length
	 * @param one first word to compare
	 * @param two second word to compare
	 * @return index of the character that is different in two words
	 */
    public int getDifferenceIndex(String one, String two)
    {
    	int differenceIndex = -1;
    	for(int i = 0; i < one.length(); i++)
    	{
    		if(one.charAt(i) != two.charAt(i))
    		{
    			differenceIndex = i;
    		}
    	}
    	return differenceIndex;
    }
    
    
	/**
	 * Counts number of letters that are different between two words
	 * precondition: String word1 and String word2 must be the same length
	 * @param word1 first word to compare
	 * @param word2 second word to compare
	 * @return number of different letters
	 */
    private int compareLetters(String word1, String word2)
    {
        int count = 0;
        for (int i = 0; i < word1.length(); i++)
        {
            if (word1.charAt(i) != word2.charAt(i)) count++;
        }
        return count;
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
    	if(startWord.equals(endWord) && !wordLadder.isEmpty())
    		return false;
    	if(!startWord.equals(endWord) && wordLadder.isEmpty())
    		return false;
        if(!wordLadder.isEmpty())
        {
        	if(!wordLadder.get(0).equals(startWord) || !wordLadder.get(wordLadder.size() - 1).equals(endWord))
        	{
        		return false;
        	}
        	for(int i = 0; i < wordLadder.size(); i++)
        	{
        		if(i == wordLadder.size() - 1) break;
        		if(compareLetters(wordLadder.get(i), wordLadder.get(i + 1)) != 1)
        		{
        			return false;
        		}
        	}
        	
        }  	
    	return true;
    }

    
	/**
	 * Clears the solutionList and visited list
	 */
    public void clear()
    {
        solutionList.clear();
	    visited.clear();
    }
}

/**
 * 
 * Exception used to specify that there is an invalid argument
 * Invalid arguments can be non-English words, words that aren't five letters, and
 * lines with incorrect number of words (must be only 2)
 */
class IllegalArgumentException extends Exception
{
    public IllegalArgumentException(String message)
    {
        super(message);
    }
}

/**
 * 
 * Exception used to specify that the starting and ending word are the same
 *
 */
class SameWordException extends Exception
{
    public SameWordException(String message)
    {
        super(message);
    }
}