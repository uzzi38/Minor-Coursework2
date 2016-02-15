package philipusman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HexConv {

    //Pass input with scanner as tokens into input array
    private ArrayList<Character> input = new ArrayList<Character>();
   
    public HexConv(){}
    public ArrayList<String> hexConvert(String in) throws FileNotFoundException{
        ArrayList<String> out = new ArrayList<String>();
        in.toUpperCase();
        for (int i = 0; i < in.length(); i++){
            //String split into individual characters
            input.add(in.charAt(i));
        }
        for (int i = 0; i< input.size(); i++){
        	out.add(csvScanner(input.get(i)));
        }
        //Iterate input and correlate to characters in charLookup
        return out;
    }
    private String csvScanner(Character in) throws FileNotFoundException{
    	Scanner tableScanner = new Scanner (new File("ascii_table.csv"));
        tableScanner.useDelimiter(",");
        while(tableScanner.hasNextLine()){
        	Scanner lineScanner = new Scanner(tableScanner.nextLine());
        	lineScanner.next();
        	lineScanner.next();
        	String hex = lineScanner.next();
        	lineScanner.next();
        	String symbol = lineScanner.next();
        	lineScanner.close();
        	tableScanner.close();
        	if(in.equals(symbol)){
        		return hex;
        	}
        }
        tableScanner.close();
        return "?";
    }
}
