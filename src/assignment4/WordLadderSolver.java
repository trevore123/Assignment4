/*
    ADD YOUR HEADER HERE
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
public class WordLadderSolver implements Assignment4Interface
{
	// declare class members here.
	private List<String> solutionList;
	private HashSet<String> dict;
	private HashSet<String> visited;
	
    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there
	public WordLadderSolver(String fileName)
	{
		solutionList = new ArrayList<String>();
		dict = new HashSet<String>();
		visited = new HashSet<String>();
        try 
        {
            FileReader freader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(freader);
            String s = reader.readLine();
            while (s != null)
            {
                if (s.charAt(0) != '*'){
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
    @Override
    public List<String> computeLadder(String startWord, String endWord) 
            throws NoSuchLadderException, IllegalArgumentException
    {
        if (startWord.length() != 5 || endWord.length() != 5 || !(dict.contains(startWord)) || !(dict.contains(endWord))) 
        {
            throw new IllegalArgumentException("At least one of the words \'" + startWord + "\' or \'" + endWord +
                                                "\' is not a legitimate 5-letter word from the dictionary");
        }
        if(startWord.equals(endWord)) 
        {
            solutionList.add(startWord);
            solutionList.add(startWord);
            return solutionList;
        }
    	if(makeLadder(startWord, endWord, -1))
    	{
    	    solutionList.add(endWord);
    		return solutionList;
    	}
    	
        // implement this method
    	else throw new NoSuchLadderException("There is no word ladder between " + startWord + " and " + endWord);
    }
    
    public boolean makeLadder(String fromWord, String toWord, int index)
    {
        if (visited.contains(fromWord))
        {
            return false;
        }
    	solutionList.add(fromWord);
    	visited.add(fromWord);

    	
    	//iterate through dictionary
    	ArrayList<String> tempList = new ArrayList<String>();
        for(String s: dict)
        {
            int differenceToTarget = compareLetters(s, toWord);
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
        Collections.sort(tempList);
       
    	
    	//find all difference of one words and put into templist
    	//make sure not to add words that are already in solutionList
    	for(int i = 0; i < tempList.size(); i++)
    	{
    		if(makeLadder(tempList.get(i).substring(1), toWord, getDifferenceIndex(fromWord, tempList.get(i).substring(1))))
    		{
    			
    			return true;
    		}
    		else solutionList.remove(tempList.get(i));
    	}
    	solutionList.remove(fromWord);
    	return false;
    	
    }
    
    public ArrayList<String> createSortedTempList(String fromWord, String toWord, int index)
    {
        ArrayList<String> tempList = new ArrayList<String>();
        for(String s: dict)
        {
            int letterDifference = compareLetters(s, toWord);
            if(compareLetters(fromWord, s) == 1)
            {
                if(getDifferenceIndex(fromWord, s) != index && !(solutionList.contains(s)))
                {
                    tempList.add(letterDifference + s);
                }
            }
        }
        Collections.sort(tempList);
    	return tempList;
    }
    
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
    
    private int compareLetters(String word1, String word2)
    {
        int count = 0;
        for (int i = 0; i < word1.length(); i++){
            if (word1.charAt(i) != word2.charAt(i)) count++;
        }
        return count;
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    // add additional methods here
    
    public void clear()
    {
        solutionList.clear();
    }
}


class IllegalArgumentException extends Exception
{
    public IllegalArgumentException(String message)
    {
        super(message);
    }
}