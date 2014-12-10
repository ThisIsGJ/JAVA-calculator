import java.util.ArrayList;

/*
 * This class have 4 method:
 * calPartAnswer() This method is used to calculate the expression without bracket.
 * calAnswer() This method is used to remove the bracket, then use the calPartAnswer to get the final result.
 * isNumeric() This method is used to check the string whether is a number or not.
 *  makeInt() This method is used to change the out put result,such as change "6.0" to "6".
 * */
public class BasicOperation
{
	private Double result;
	private ArrayList<String> save;
	private ArrayList<String> after;
	
	BasicOperation()
	{
		save = new ArrayList<String>();
		
	}
	
	//basic operation + - * /
	private String calPartAnswer(ArrayList<String> partList)
	{
		if (partList.size() != 1)
		{	
			while (partList.contains("*") || partList.contains("/")) 
			{
				for (int i = 0; i < partList.size(); i++)
				{
					if (partList.get(i).equals("*"))
					{	

						float result1 = (float) (Double.parseDouble(partList.get(i-1)) * Double.parseDouble(partList.get(i+1)));
						String result2 = String.valueOf(result1);
						partList.set(i,result2);
						partList.remove(i-1);
						partList.remove(i);
						return calPartAnswer(partList);
					}
					else if (partList.get(i).equals("/"))
					{
						float result1 = Float.parseFloat(partList.get(i-1)) / Float.parseFloat(partList.get(i+1));
						String result2 = String.valueOf(result1);
						partList.set(i,result2);
						partList.remove(i-1);
						partList.remove(i);
						return calPartAnswer(partList);
					}
				}				
			}
			for (int i = 0; i < partList.size(); i++)
			{
				if (partList.get(i).equals("+"))
				{	
					float result1 = Float.parseFloat(partList.get(i-1)) + Float.parseFloat(partList.get(i+1));
					String result2 = String.valueOf(result1);
					partList.set(i,result2);
					partList.remove(i-1);
					partList.remove(i);
					return calPartAnswer(partList);
				}
				else if (partList.get(i).equals("-"))
				{
					float result1 = Float.parseFloat(partList.get(i-1)) - Float.parseFloat(partList.get(i+1));
					String result2 = String.valueOf(result1);
					partList.set(i,result2);
					partList.remove(i-1);
					partList.remove(i);
					return calPartAnswer(partList);
				}
					
			}
			//return calPartAnswer(partList);
				
		}
		return partList.get(0);		
	}
	
	/*
	 * calculate the expression answer
	 * @inputString The inputString is the userInput
	 * */
	public String calAnswer(ArrayList<String> inputString)
	{		
		int len = inputString.size();
		save = new ArrayList<String>();
		//remove all the bracket from the expression
		if(inputString.contains("(") && inputString.contains(")"))
		{
			for(int i = len-1; i >= 0; i--)
			{
				if(inputString.get(i).equals("("))
				{
					for (int j = i+1; j < len; j++)
					{	
						if (inputString.get(j).equals(")"))
						{	
							for(int c = i+1;c < j; c++ )
							{	
								save.add(inputString.get(c));
							}
							inputString.subList(i,j+1).clear();
							inputString.add(i,calPartAnswer(save));
							return calAnswer(inputString);
						}					
					}
					
				}
			}		
			return calPartAnswer(inputString);
		}
		else
		{ 	
			return calPartAnswer(inputString);
		}
	}
	
	//check the String whether is a number
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException e)  
	  {  
	    return true;  
	  }  
	  return false;  
	}

	
	// change the out put "6.0" to "6"
	public String makeInt(String result)
	{
		char List[] = new char[50];
		after = new ArrayList<String>();
		List = result.toCharArray();
		for(Object o : List)
		{	
			String t = o.toString();
			after.add(t);
		}
		if(after.get(after.size()-2).equals(".") && after.get(after.size()-1).equals("0"))
		{	
			double result1 = Double.parseDouble(result);
			int result2 = (int) result1;
			String result3 = String.valueOf(result2);
			return result3;
		}
		else
		{
			return result;
		}


	}
	
}



	


	