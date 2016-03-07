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
import java.util.List;

//Purpose: Runs logic behind solving word ladders
public class Assign4Driver
{
    public static void main(String[] args)
    {
    	if (args.length != 2) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
        // Create a word ladder solver object
        try{
        	//args[0] is dictionary file
            Assignment4Interface wordLadderSolver = new WordLadderSolver(args[0]);
            
            //args[1] is user input file
            FileReader freader = new FileReader(args[1]);
            BufferedReader reader = new BufferedReader(freader);
            //read line of file
            String s = reader.readLine();	
            
            //loops through each line of input
            while(s != null)
            {
                try 
                {   
                    //split input line based on whitespace
                	String[] words = s.split("\\s+");				
                    String word1 = words[0];
                    String word2 = words[1];
                    List<String> result = wordLadderSolver.computeLadder(word1, word2);
                    System.out.println("For the input words \"" + word1 + "\" and \"" + word2 + "\":");
                    
                    //this loop prints out the word ladder
                    for (String word: result)						
                    {
                        System.out.println(word);
                    }
                    //asterisks separate different ladders
                    System.out.println("\n**********\n");				
                } 
                //thrown when no ladder is found
                catch (NoSuchLadderException e) 					
                {
                    System.out.println(e.getMessage() + "\n");
          
                }
                //thrown if at least one word is not a 5-letter word in the dictionary
                catch (IllegalArgumentException e)					
                {
                    System.err.println("Error: Invalid Argument\n" + e.getMessage() + "\n");
                }
                catch(SameWordException e)
                {
                    System.out.println(e.getMessage() + "\n");;
                }
                //thrown if input format is incorrect
                catch (IndexOutOfBoundsException e)					
                {
                	System.err.println("Error: Improper input format\n" + "There must be two words separated by a space\n");
                }
                finally
                {
                    //clears previous solution
                    wordLadderSolver.clear();		
                    //reads next line of input
                    s = reader.readLine();				
                }
            }
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
}
