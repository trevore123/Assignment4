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
        try{
            Assignment4Interface wordLadderSolver = new WordLadderSolver("A4-words.txt");
        
            FileReader freader = new FileReader(args[0]);
            BufferedReader reader = new BufferedReader(freader);
            String s = reader.readLine();
                while(s != null){
                try 
                {   
                    String[] words = s.split("\\s+");
                    String word1 = words[0];
                    String word2 = words[1];
                    List<String> result = wordLadderSolver.computeLadder(word1, word2);
                    System.out.println("For the input words \'" + word1 + "\' and \'" + word2 + "\':");
                    for (String word: result)
                    {
                        System.out.println(word);
                    }
                    System.out.println("**********");
                    //boolean correct = wordLadderSolver.validateResult("money", "honey", result);
                } 
                catch (NoSuchLadderException e) 
                {
                    System.err.println(e.getMessage() + "\n");
          
                }
                catch (IllegalArgumentException e)
                {
                    System.err.println("Error: Invalid Argument\n" + e.getMessage() + "\n");
                }
                finally
                {
                    wordLadderSolver.clear();
                    s = reader.readLine();
                }
            }
        }
        catch (FileNotFoundException e){
            System.err.println ("Error: File not found. Exiting...");
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e){
            System.err.println ("Error: IO exception. Exiting...");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
