package philipusman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class HexConv {

    //Pass input with scanner as tokens into input array
    ArrayList<Character> input = new ArrayList<Character>();
    char[] charLookup = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    String[] hexLookup = {"41", "42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "5A"};


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
        for (int n = 0; n < input.size(); n++){
            for (int x = 0; x < charLookup.length; x++){
                if (input.get(n) == charLookup[x]){
                    out.add(hexLookup[x]);
                }
            }
        }
        //Outputs array of Strings, with each element being Hex code for respective character in input
        return out;
    }
    private String csvScanner(Character in) throws FileNotFoundException{
    	Scanner tableScanner = new Scanner (new File("ascii_table.csv"));
        tableScanner.useDelimiter(",");
        tableScanner.close();
        while(tableScanner.hasNextLine()){
        	Scanner lineScanner = new Scanner(tableScanner.nextLine());
        	String dec = lineScanner.next();
        	String oct = lineScanner.next();
        	String hex = lineScanner.next();
        	String bin = lineScanner.next();
        	String symbol = lineScanner.next();
        	String htmlNum = lineScanner.next();
        	String htmlName = lineScanner.next();
        	lineScanner.close();
        	if(in.equals(symbol)){
        		return hex;
        	}
        	else{
        		return "?";
        	}
        }
        return "?";
    }
}
