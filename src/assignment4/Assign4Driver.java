package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    	
        Assignment4Interface wordLadderSolver = new WordLadderSolver("A4-words.txt");
        
        try 
        {
            List<String> result = wordLadderSolver.computeLadder("money", "smart");
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
    
    public static void processLinesInFile (String filename) 
    {
    	
    	Translator translator = new Translator(); 
    	try 
    	{
    		FileReader freader = new FileReader(filename);
    		BufferedReader reader = new BufferedReader(freader);
    		
    		for (String s = reader.readLine(); s != null; s = reader.readLine()) 
    		{
    			if(translator.checkInput(s))
    			{
    				
    			}
    			
    		}	
    	} 
    	catch (FileNotFoundException e) 
    	{
    		System.err.println ("Error: File not found. Exiting...");
    		e.printStackTrace();
    		System.exit(-1);
		  	}catch (IOException e) 
		  	{
		  		System.err.println ("Error: IO exception. Exiting...");
		  		e.printStackTrace();
		  		System.exit(-1);
		  	}
    	}
    
}