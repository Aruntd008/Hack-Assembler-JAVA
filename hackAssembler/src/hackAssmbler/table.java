package hackAssmbler;
import java.util.*;

public class table {
	static int reg=16;
	
	private static Hashtable<String, String> destnation_bin_table = new Hashtable<String, String>(7);
    private static Hashtable<String, String> jump_bin_table = new Hashtable<String, String>(7);
    private static Hashtable<String, String> computation_bin_table = new Hashtable<String, String>();
    static Hashtable<String, Integer> sym_table = new Hashtable<String, Integer>();

    static void sym_table() {                 // dictionary for Symbols
    	sym_table.put("SCREEN", 16384);       // here is where the labels and variable are stored
        sym_table.put("KBD", 24576);
        sym_table.put("SP", 0);
        sym_table.put("LCL", 1);
        sym_table.put("ARG", 2);
        sym_table.put("THIS", 3);
        sym_table.put("THAT", 4);
        sym_table.put("R0",0 );
        sym_table.put("R1",1 );
        sym_table.put("R2",2 );
        sym_table.put("R3",3 );
        sym_table.put("R4",4 );
        sym_table.put("R5",5 );
        sym_table.put("R6",6 );
        sym_table.put("R7",7 );
        sym_table.put("R8",8 );
        sym_table.put("R9",9 );
        sym_table.put("R10",10 );
        sym_table.put("R11",11 );
        sym_table.put("R12",12 );
        sym_table.put("R13",13 );
        sym_table.put("R14",14 );
        sym_table.put("R15",15 );
        
    }
    private static void dest_val_table() {       // dictionary for destination
       destnation_bin_table.put("M", "001");
       destnation_bin_table.put("D", "010");	
       destnation_bin_table.put("MD", "011");
       destnation_bin_table.put("A", "100");	
       destnation_bin_table.put("AM", "101");
       destnation_bin_table.put("AD", "110");
       destnation_bin_table.put("AMD", "111");
    } 
    
    private static void jump_val_table() {      // dictionary for jump
       jump_bin_table.put("JGT", "001");	
       jump_bin_table.put("JEQ", "010");	
       jump_bin_table.put("JGE", "011");	
       jump_bin_table.put("JLT", "100");	
       jump_bin_table.put("JNE", "101");	
       jump_bin_table.put("JLE", "110");	
       jump_bin_table.put("JMP", "111");	
    }

    private static void comp_val_table() {     // dictionary for computation
       computation_bin_table.put("0", "0101010");
       computation_bin_table.put("1", "0111111");
       computation_bin_table.put("-1", "0111010");
       computation_bin_table.put("D", "0001100");
       computation_bin_table.put("A", "0110000");
       computation_bin_table.put("!D", "0001101");
       computation_bin_table.put("!A", "0110001");
       computation_bin_table.put("-D", "0001111");
       computation_bin_table.put("-A", "0110011");
       computation_bin_table.put("D+1", "0011111");
       computation_bin_table.put("1+D", "0011111");
       computation_bin_table.put("A+1", "0110111");
       computation_bin_table.put("1+A", "0110111");
       computation_bin_table.put("D-1", "0001110");
       computation_bin_table.put("A-1", "0110010");
       computation_bin_table.put("D+A", "0000010");
       computation_bin_table.put("A+D", "0000010");
       computation_bin_table.put("D-A", "0010011");
       computation_bin_table.put("A-D", "0000111");
       computation_bin_table.put("D&A", "0000000");
       computation_bin_table.put("A&D", "0000000");
       computation_bin_table.put("D|A", "0010101");
       computation_bin_table.put("A|D", "0010101");
       computation_bin_table.put("M", "1110000");
       computation_bin_table.put("!M", "1110001");
       computation_bin_table.put("-M", "1110011");
       computation_bin_table.put("M+1", "1110111");
       computation_bin_table.put("1+M", "1110111");
       computation_bin_table.put("M-1", "1110010");
       computation_bin_table.put("D+M", "1000010");
       computation_bin_table.put("M+D", "1000010");
       computation_bin_table.put("D-M", "1010011");
       computation_bin_table.put("M-D", "1000111");
       computation_bin_table.put("D&M", "1000000");
       computation_bin_table.put("M&D", "1000000");
       computation_bin_table.put("D|M", "1010101");
       computation_bin_table.put("M|D", "1010101");
    } 

public static String get_comp_code(final String key) { // to get the corresponding binary value to the key
		comp_val_table();
		return computation_bin_table.get(key);
	}

public static  String get_dest_code(final String key) { // to get the corresponding binary value to the key
	  dest_val_table();
	  return destnation_bin_table.get(key);
	}

public static String get_jump_code(final String key) { // to get the corresponding binary value to the key
		jump_val_table();
       return jump_bin_table.get(key);
   }
public static void add_var(final String varname) { // adds variable as key and register number to Symbol Table
	sym_table.put(varname, reg);
	reg++;                                          // increments the register where variable is stored from 16
   }
public static void put_label(final String labelname, final int linenum) {  // adds label as key and current line number as value to Symbol Table
    sym_table.put(labelname, linenum);
   }
public static int get_val_sym(final String varname) {                      // to get the corresponding integer value to the key
	sym_table();
    return sym_table.get(varname);
}
	  
}
