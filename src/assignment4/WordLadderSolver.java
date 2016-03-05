/*
    ADD YOUR HEADER HERE
 */

package assignment4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
	// declare class members here.
	private List<String> solutionList;
	private Dictionary dictionary;
	
    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there
	WordLadderSolver(String fileName)
	{
		solutionList = new ArrayList<String>();
		dictionary = new Dictionary(fileName);
	}

    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
    	if(makeLadder(startWord, endWord, -1))
    		return solutionList;
    	
        // implement this method
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    
    public boolean makeLadder(String fromWord, String toWord, int index)
    {
    	solutionList.add(fromWord);
    	
    	if(isOneDifference(fromWord, toWord))
    	{
    		solutionList.add(toWord);
    		return true;
    	}
    	
    	//iterate through dictionary
    	ArrayList<String> tempList = createSortedTempList(fromWord, toWord, index);
    	
    	//find all difference of one words and put into templist
    	//make sure not to add words that are already in solutionList
    	
    	for(int i = 0; i < tempList.size(); i++)
    	{
    		if(makeLadder(tempList.get(i).substring(1), toWord, getDifferenceIndex(fromWord, tempList.get(i).substring(1))) == true)
    		{
    			return true;
    			
    		}
    		else
    			solutionList.remove(tempList.get(i));
    	}
    	solutionList.remove(fromWord);
    	return false;
    	
    }
    
    public ArrayList<String> createSortedTempList(String fromWord, String toWord, int index)
    {
    	ArrayList<String> tempList = new ArrayList<String>();
    	for(int i = 0; i < dictionary.words.size(); i++)
    	{
    		if(isOneDifference(fromWord, dictionary.words.get(i)))
    		{
    			if(getDifferenceIndex(fromWord, dictionary.words.get(i)) != index 
    				&& !solutionList.contains(dictionary.words.get(i)))
    			{
    				tempList.add(countDiff(dictionary.words.get(i), toWord) + dictionary.words.get(i));
    			}
    		}
    	}
    	Collections.sort(tempList);
    	return tempList;
    }
    
    public boolean isOneDifference(String one, String two)
    {
    	int counter = 0;
    	
    	for(int i = 0; i < one.length(); i++)
    	{
    		if(one.charAt(i) != two.charAt(i))
    		{
    			counter++;
    		}
    		
    		if(counter > 1)
    		{
    			return false;
    		}
    			
    	}
    	
    	return true;
    }
    
    public int getDifferenceIndex(String one, String two)
    {
    	int index = -1;
    	
    	for(int i = 0; i < one.length(); i++)
    	{
    		if(one.charAt(i) != two.charAt(i))
    		{
    			index = i;
    		}
    	}
    	return index;
    }
    
    public int countDiff(String one, String two)
    {
    	int counter = 0;
    	for(int i = 0; i < one.length(); i++)
    	{
    		if(one.charAt(i) != two.charAt(i));
    		{
    			counter = counter + 1;
    		}
    	}
    	
    	return counter;
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
    	
    	
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    // add additional methods here
}