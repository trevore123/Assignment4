package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class Dictionary {
    
    HashSet<String> dict;
    
    public Dictionary(String file){
        dict = new HashSet<String>();
        try 
        {
            FileReader freader = new FileReader(file);
            BufferedReader reader = new BufferedReader(freader);
            String s;
            while ((s = reader.readLine()) != null)
            {
                if (s.charAt(0) != '*'){
                    dict.add(s.substring(0,5));
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
    
    public boolean contains(String s) 
    {
        return dict.contains(s);
    }
    
    public int size(){
        return dict.size();
    }

}
