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
	 * WordLadderSolver constructor
	 * @param fileName
	 */
	public WordLadderSolver(String fileName)
	{
		solutionList = new ArrayList<String>();
		dict = new HashSet<String>();
		visited = new HashSet<String>();
        try 
        {
        	//this portion constructs dictionary from provided file
        	//assignment4 pdf contained instructions on how to do so
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
	 * Computes the word ladder
	 * @param startWord the start of the ladder
	 * @param endWord the end of the ladder
	 * @return String ArrayList containing the word ladder
	 */
    @Override
    public List<String> computeLadder(String startWord, String endWord) 
            throws NoSuchLadderException, IllegalArgumentException
    {
    	//conditional makes sure each word is a 5-letter word that can be found in the dictionary
        if (startWord.length() != 5 || endWord.length() != 5 || !(dict.contains(startWord)) || !(dict.contains(endWord))) 
        {
            throw new IllegalArgumentException("At least one of the words \'" + startWord + "\' or \'" + endWord +
                                                "\' is not a legitimate 5-letter word from the dictionary");
        }
        //there is no ladder to print for the same words
        if(startWord.equals(endWord)) 
        {
            //solutionList.add(startWord);
            //solutionList.add(startWord);
            return solutionList;
        }
    	if(makeLadder(startWord, endWord, -1))
    	{
    	    solutionList.add(endWord);		//adds endWord to end of solution
    		return solutionList;
    	}
    	
        // implement this method
    	else throw new NoSuchLadderException("For the input words \"" + startWord + "\" and \"" + endWord + "\":\n" + 
    			"There is no word ladder between " + startWord + " and " + endWord + "\n**********");
    }
	/**
	 * Recursive function that constructs the word ladder
	 * word ladder is placed in solutionList
	 * @param fromWord
	 * @param toWord
	 * @param index 
	 * @return true if there is a word ladder; false if there is not
	 */
    public boolean makeLadder(String fromWord, String toWord, int index)
    {
    	//conditional checks for words that have already been tried
        if (visited.contains(fromWord))
        {
            return false;
        }
    	solutionList.add(fromWord);
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
            //conditional checks for one letter of difference, if the word is already in the solution
            //checks if the letter changed to obtain word is the same index as the previous change
            if(compareLetters(fromWord, s) == 1 && !(solutionList.contains(s)) && getDifferenceIndex(fromWord, s) != index)
            {
                if (differenceToTarget == 1)
                {
                    solutionList.add(s);
                    return true;
                }
                tempList.add(differenceToTarget + s);
            }
        }
        Collections.sort(tempList);				//sorts tempList
       
    	
    	//iterates through tempList and makes recursive calls to makeLadder
    	for(int i = 0; i < tempList.size(); i++)
    	{
    		if(makeLadder(tempList.get(i).substring(1), toWord, getDifferenceIndex(fromWord, tempList.get(i).substring(1))))
    		{
    			
    			return true;
    		}
    		else solutionList.remove(tempList.get(i));			//removes nonsolution word from solutionList
    	}
    	solutionList.remove(fromWord);							//removes attempted word from solutionList
    	return false;
    	
    }

    
	/**
	 * Finds and returns the index of the differentiating chars
	 * precondition: String one and String two must be the same length
	 * @param one String to compare
	 * @param two String to compare
	 * @return index of the differentiating chars
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
	 * Loops through strings and counts differentiating chars
	 * precondition: String word1 and String word2 must be the same length
	 * @param word1 String to compare
	 * @param word2 String to compare
	 * @return number of different chars
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
	 * Clears the solutionList and visited
	 */
    public void clear()
    {
        solutionList.clear();
	    visited.clear();
    }
}


class IllegalArgumentException extends Exception
{
    public IllegalArgumentException(String message)
    {
        super(message);
    }
}