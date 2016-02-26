/*
    ADD YOUR HEADER HERE
 */

package assignment4;

import java.util.ArrayList;
import java.util.List;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface
{
	List<String> solutionList = new ArrayList<String>();
	
    // declare class members here.

    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there

    // do not change signature of the method implemented from the interface
    @Override
    public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException 
    {
    	
        // implement this method
        throw new UnsupportedOperationException("Not implemented yet!");
    }
    
    public boolean makeLadder(String fromWord, String toWord, int index)
    {
    	if(fromWord difference of one toWord)
    		return true;
    	
    	solutionList.add(fromWord);
    	//iterate through dictionary
    	//find all difference of one words and put into templist
    	
    	for(int i = 0; i < tempList.size(); i++)
    	{
    		if(makeLadder(tempList.get(i), toWord, index++) == true)
    		{
    			return true;
    		}
    		else
    			solutionList.remove(tempList.get(i))
    	}
    	
    	return false;
    	
    }

    @Override
    public boolean validateResult(String startWord, String endWord, List<String> wordLadder) 
    {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    // add additional methods here
}
