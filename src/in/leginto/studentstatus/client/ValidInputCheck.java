package in.leginto.studentstatus.client;

public class ValidInputCheck {
	
	
	public boolean isValidName(String name)
	{
		for(char c:name.toCharArray())
		{
			if(! ((c>=65 && c<=90) || (c>=97 && c<=122) || c==' '))
			{
				return false;
			}
		}
		
		return true;
	}
	
	
	
	public boolean isValidEmail(String email)
	{
		if(! email.contains("@"))
		{
			return false;
		}
		
		if(! email.substring(email.indexOf('@')+1).contains("."))
		{
			return false;
		}
		
		for(char c:email.toCharArray())
		{
			if(! ((c>=65 && c<=90) || (c>=97 && c<=122) || email.contains("_") || email.contains(".")))
			{
				return false;
			}
		}
		
		return true;
		
		
	}
	
	
	public boolean isValidMobile(String mobile)
	{
		if(mobile.length()!=10)
		{
			return false;
		}
		
		if(mobile.charAt(0)!='9')
		{
			return false;
		}
		
		
		for(char c:mobile.toCharArray())
		{
			if(! (c>=48 && c<=57))
			{
				return false;
			}
		}
		
		
		
		
		return true;
	}

}

class ConvertAlphabets
{
	public String convertSentenceCase(String name)
	{
		name = name.trim();
		name = name.toLowerCase();
		
		char[] nameChar = name.toCharArray();
		
		if(nameChar[0]>=97 && nameChar[0]<=122)
		{
			nameChar[0] = (char) (nameChar[0] - 32); 
		}
		
		for(int i=1; i< nameChar.length; i++)
		{
			if(nameChar[i]==32 && nameChar[i+1]!=32)
			{
				nameChar[i+1] = (char) (nameChar[i+1] - 32);
			}			
		}		
		
		name = nameChar.toString().valueOf(nameChar);
		return name;
	}	
}




