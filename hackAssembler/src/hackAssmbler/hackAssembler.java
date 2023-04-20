package hackAssmbler;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class hackAssembler {

	public static void main(String[] args) throws IOException {
		int i,j;
		try (
		RandomAccessFile file = new RandomAccessFile("C:\\Users\\Arun Prasad\\Desktop\\e folder\\nand2tetris\\projects\\06\\max\\MaxL.asm", "r")) { // file Input
	    Path fileName = Path.of("C:\\Users\\Arun Prasad\\Desktop\\e folder\\nand2tetris\\projects\\06\\max\\MaxL.hack"); // output file destination
				// Creating a list to store the file line by line 
				List<String> allLines = new ArrayList<String>();
				List<String> updLines = new ArrayList<String>();
				List<String> finlLines = new ArrayList<String>();

				String line;                                             
				while ((line = file.readLine()) != null)           // While loop is reads one line at a time and store in the "String line" 
				{
					if(!line.isEmpty())                            // skips empty lines
					{
						line=line.trim();                          // removes leading and trailings blank spaces
						String[] split = line.split("//");         // removes in line comments
						if(!split[0].isEmpty())                    // skips full line comments
					 	{
							line=split[0];
							allLines.add(line);                    // Stores the edited line in the "list allLines"
							System.out.println(line);
						}
					}
				}
				searchLabel.numlabel(allLines);
				storesSymbol.numvar(allLines);                    // Calls to store the label and variable  
				System.out.println(allLines);
				//CONVERTING to normal A INSTRUCTION
				for (i = 0; i < allLines.size(); i++)             
				{
					String updline = allLines.get(i);
					if(!updline.contains("("))                       // Skips Label declaration "(label)"
					{
						updline.trim();
						
						if(updline.contains("@"))
						{
							char bin = updline.charAt(1);            // Stores 2nd letter in "bin
				    		if(!Character.isDigit(bin))              // Checks if it is a label or a normal A INSTRUCTION 
				    		{
								String[] split = updline.split("@");  // Variable Name is stored in var
				    		    String var=split[1];
				    			int num =table.get_val_sym(var);      // num stores the respective number to the variable/label
					    		String s=Integer.toString(num);
					    		updline=updline.replace(var, s);      // edits @var ==> @16
					    		//System.out.println(num);
					    		updLines.add(updline);                // stores to the list updLines
				    		}	
				    		else 
				    		{
				    			updLines.add(updline);                // Stores normal A INSTRUCTION to the list updLines 
				    		}	 
						}
						else
						{
							updLines.add(updline);                    // Stores C INSTRUCTION to the list updLines 
						} 
					}
				}
				System.out.println(updLines);
				
				for (i = 0; i < updLines.size(); i++) 
				{
					String finline = updLines.get(i);
					System.out.println(finline);
					boolean destTrue = finline.contains("=");      // Checks if C Instruction contains destination eg: M=D+1 
					boolean jmpTrue = finline.contains(";");       // Checks if C Instruction contains jump eg: 0;JMP 
					// Converting A instruction number to 16 binary
					if(finline.contains("@")) 
					{
						String[] split1 = finline.split("@");
			    		String ins=split1[1];                     // number is stored in ins
			    		int num1 = Integer.parseInt(ins);         // Convert String into integer
			    		String bin = Integer.toBinaryString(num1);// Convert number into BinaryString 
			       		int stringSize= bin.length();             // Stores the length of the converted binary
			       		int count= 16-stringSize;                 // Stores how many "0" to be added to make it 16 bit number 
			       		String upbin=bin;
			       		
			       		for(j=0;j<count;j++)
			       		{
			       			upbin = "0"+upbin;                    // converts to 16 bit binary 
			       		}
			       		finlLines.add(upbin);                     // Add the converted A Instruction to the finLines list
					}
					else if (destTrue && jmpTrue)                 // if all jmp,dest and comp is present in the instruction eg: D=M+1;JEQ
					{
						String[] split2 = finline.split("="); 
						String dest=split2[0].trim();                    // dest Stores "D" 
						String temp=split2[1].trim();                    // temp Stores "M+1;JEQ"
						String[] split3 = temp.split(";");
						String comp=split3[0].trim();                   // comp Stores "M+1"
						String jump=split3[1].trim();                   // jmp Stores "JEQ"
						String destbin = table.get_dest_code(dest);     // Gets binary equivalent of whatever stored in dest from dictionary
						String compbin = table.get_comp_code(comp);     // Gets binary equivalent of whatever stored in comp from dictionary
						String jumpbin = table.get_jump_code(jump);     // Gets binary equivalent of whatever stored in jmp from dictionary
						String cins = "111"+compbin+destbin+jumpbin;    // concatenate the final C Instruction
						finlLines.add(cins);
					}
					else if (destTrue && !jmpTrue)                      // if ONLY dest,comp is present in the instruction eg: D=M+1
					{
						String[] split2 = finline.split("="); 
						String dest=split2[0].trim();
						String comp=split2[1].trim();
						String destbin = table.get_dest_code(dest);
						String compbin = table.get_comp_code(comp);
						String cins = "111"+compbin+destbin+"000";
						finlLines.add(cins);
					}
					else if (!destTrue && jmpTrue)                       // if ONLY dest,comp is present in the instruction eg: 0;JMP
					{ 
						String[] split2 = finline.split(";"); 
						String comp=split2[0].trim();
						String jump=split2[1].trim();
						String compbin = table.get_comp_code(comp);
						String jumpbin = table.get_jump_code(jump);
						String cins = "111"+compbin+"000"+jumpbin;
						finlLines.add(cins);
					}
					else if (!destTrue && !jmpTrue)                     // if comp is present in the instruction eg: M+1
					{
						String[] split2 = finline.split(";"); 
						String comp=split2[0].trim();
						String compbin = table.get_comp_code(comp);
						String cins = "111"+compbin+"000"+"000";
						finlLines.add(cins);
					}
					System.out.println(finlLines);
				}
				Files.write(fileName, finlLines);                             //Write the content stored in "finLines"
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

