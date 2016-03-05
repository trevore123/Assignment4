package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary
{
	public ArrayList<String> words = new ArrayList<String>();
	
	public Dictionary()
	{
		
	}
	
	public Dictionary(String fileName)
	{
		processLinesInFile(fileName);
		
	}
	
	public void processLinesInFile (String filename) 
	{ 
		Translator translator = new Translator(); 
		try 
		{
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);
			
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				String word = translator.translate(s);
				if(word != "")
				{
					words.add(word);
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