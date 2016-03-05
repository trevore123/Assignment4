package assignment4;

public class Translator
{
	final static int WORD_LENGTH = 5;
	
	public String translate(String data)
	{
		char[] parsedData = data.toCharArray();
		if(parsedData[0] == '*')
		{
			return "";
		}
		else
		{
			String word = new String(parsedData, 0, WORD_LENGTH);
			return word;
		}
			
		
	}
	
}