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

    public ArrayList<String> hexConvert(String in) throws FileNotFoundException{
        ArrayList<String> out = new ArrayList<String>();
        Scanner scnr = new Scanner (new File("ascii_table.csv"));
        String current = scnr.next();
        for (int i = 0; i < current.length(); i++){
            //String split into individual characters
            input.add(current.charAt(i));
        }
        //Iterate input and correlate to characters in charLookup
        for (int n = 0; n < input.size(); n++){
            for (int x = 0; x < charLookup.length; x++){
                if (input.get(n) == charLookup[x]){
                    out.add(hexLookup[x]);
                }
            }
        }
        scnr.close();
        //Outputs array of Strings, with each element being Hex code for respective character in input
        return out;
    }

    public HexConv(){

    }
}
