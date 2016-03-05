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
    	
    	if(oneLetterDifference(fromWord, toWord) != -1)
    	{
    		solutionList.add(toWord);
    		return true;
    	}
    	
    	//iterate through dictionary
    	ArrayList<String> tempList = createSortedTempList(fromWord, index);
    	
    	//find all difference of one words and put into templist
    	//make sure not to add words that are already in solutionList
    	
    	for(int i = 0; i < tempList.size(); i++)
    	{
    		if(makeLadder(tempList.get(i).substring(1), toWord, Integer.valueOf(tempList.get(i).substring(0, 1))) == true)
    		{
    			
    			return true;
    		}
    		else
    			solutionList.remove(tempList.get(i));
    	}
    	
    	return false;
    	
    }
    
    public ArrayList<String> createSortedTempList(String fromWord, int index)
    {
    	ArrayList<String> tempList = new ArrayList<String>();
    	for(int i = 0; i < dictionary.words.size(); i++)
    	{
    		if(oneLetterDifference(fromWord, dictionary.words.get(i)) != -1 && oneLetterDifference(fromWord, dictionary.words.get(i)) != index 
    				&& !solutionList.contains(dictionary.words.get(i)))
    		{
    			tempList.add("" + oneLetterDifference(fromWord, dictionary.words.get(i)) + dictionary.words.get(i));
    		}
    	}
    	Collections.sort(tempList);
    	return tempList;
    }
    
    public int oneLetterDifference(String one, String two)
    {
    	char arrayOne[] = one.toCharArray();
    	char arrayTwo[] = two.toCharArray();
    	int differenceCounter = 0;
    	int differenceIndex = -1;
    	
    	for(int i = 0; i < one.length(); i++)
    	{
    		if(arrayOne[i] != arrayTwo[i])
    		{
    			differenceCounter++;
    			differenceIndex = i;
    		}
    	}
    	
    	if(differenceCounter == 1)
    		return differenceIndex;
    	
    	return -1;
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    // add additional methods here
}