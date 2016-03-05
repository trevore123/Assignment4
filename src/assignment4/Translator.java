package assignment4;

public class Translator
{
	final static int WORD_LENGTH = 5;
	
	public String translateDictionary(String data)
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
	
	public boolean checkInput(String data)
	{
		String array[] = data.split(" ");
		if(array.length != 2)
		{
			System.out.println("Too many arguements in line");
			return false;
		}
		if(array[0].length() != 5)
		{
			System.out.println(array[0] + " is not the correct length");
			return false;
		}
		if(array[1].length() != 5)
		{
			System.out.println(array[1] + " is not the correct length");
			return false;
		}
		
		return true;
	}
	
}