package hackAssmbler;

import java.util.List;

class searchLabel
{
	public static void numlabel(List<String> prasedcode )  // allLines is given as the input 
	{
		int Lineindex=-1;
		for (int i = 0; i < prasedcode.size(); i++)        // Reading each element (line) stored in allLines
		{
			Lineindex++;                                   // Stores the current line number
		    String line = prasedcode.get(i);               // Reads one line at a time and store in the "String line"
		    char ain = line.charAt(0);                     // Stores first letter in "ain
		  
		    if(ain=='(')                                   // Checking if line has label declaration
		    {
				int index = line.indexOf(")");
				String labelname = line.substring(1, index);  // labelname stores the name of label
				table.sym_table();                        
				
				if(!table.sym_table.containsKey(labelname))  // Check if the Label is Already stored in the dictionary
	    		{
	    			table.put_label(labelname, Lineindex);   // Adds the label to sym_table 
	    		}
				
				Lineindex--;                             // decrementing line number if Label is found
		    }
		}
	}
}
public class storesSymbol 
{
	public static void numvar(List<String> prasedcode ) 
	{
		for (int i = 0; i < prasedcode.size(); i++) 
		{
		    String line = prasedcode.get(i);
		    char ain = line.charAt(0);                   // Stores 1st letter in "ain"
		    char bin = line.charAt(1);                   // Stores 2nd letter in "bin"
		    
		    if (ain=='@')                                // checks if it is a A INSTRUCTION
		    {
		    	if(!Character.isDigit(bin))              // checks if it is a variable
		    	{
		    		String[] split = line.split("@");    
		    		String var=split[1];                 // name of the variable is stored in "var"
		    		table.sym_table();
		    		//System.out.println(split[1]);
		    		if(!table.sym_table.containsKey(var)) // Check if the Variable is Already stored in the dictionary
		    		{
		    			table.add_var(var);               // variable is added to Sym_table
		    		}
		    	}
		    } 
		}
	}
}

		