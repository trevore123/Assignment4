package assignment4;

import java.util.List;

public class Assign4Driver
{
    public static void main(String[] args)
    {
        // Create a word ladder solver object
    	if (args.length != 1) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
    	
        Assignment4Interface wordLadderSolver = new WordLadderSolver(args[0]);

        try 
        {
            List<String> result = wordLadderSolver.computeLadder("stone", "money");
            //boolean correct = wordLadderSolver.validateResult("money", "honey", result);
            for(String word : result)
            {
            	System.out.println(word);
            }
        } 
        catch (NoSuchLadderException e) 
        {
            e.printStackTrace();
        }
        
        
    }
    

}