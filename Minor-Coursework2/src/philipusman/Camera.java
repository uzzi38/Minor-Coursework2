package philipusman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JSlider;

public class Camera {
    //New JSlider created
	private JSlider slider;
    //New HashMap created
	private HashMap<Character, Integer> map = new HashMap<>();
    //Import of ASCII csv file
	private final File asciiTable = new File("ascii_table.csv");
    //New HashMap created
	private HashMap<String, String> symbolToHex = new HashMap<>();
    //Holds value of string in hex
	private ArrayList<String> chars;
    //The String that will be entered split up
	private ArrayList<Character> input = new ArrayList<Character>();

	public Camera () {

        //New labels for slider from 1 to 10
		Hashtable<Integer, JLabel> labels = new Hashtable<>();
		for (int i = 0; i < 10; i++){
			JLabel label = new JLabel(Integer.toString(i));
			labels.put(i, label);
		}
        //New labels for slider from A to F
		labels.put(10, new JLabel("A"));
		labels.put(11, new JLabel("B"));
		labels.put(12, new JLabel("C"));
		labels.put(13, new JLabel("D"));
		labels.put(14, new JLabel("E"));
		labels.put(15, new JLabel("F"));

        //Denotes there are 15 points on the JSlider
		slider = new JSlider(0,15);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setValue(0);
		slider.setLabelTable(labels);

        //Denotes where the labels are put in accordance to positions of the points
		map.put('A', 10);	map.put('B', 11);	map.put('C', 12);	map.put('D', 13);	
		map.put('E', 14);	map.put('F', 15);	map.put('0', 0);	map.put('1', 1);	
		map.put('2', 2);	map.put('3', 3);	map.put('4', 4);	map.put('5', 5);
		map.put('6', 6);	map.put('7', 7);	map.put('8', 8);	map.put('9', 9);
	}
	/**
	 * Returns the slider
	 * @return slider
	 */
	public JSlider returnSlider(){
		return slider;
	}
	
	/**
	 * Hex Converter
	 * @return Returns the Array
	 * @throws IOException 
	 */
	private ArrayList<String> hexConvert(String str) throws IOException{
        csvScanner2();
		ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < str.length(); i++){
            //String split into individual characters
        	input.add(str.charAt(i));
        }
        for (int i = 0; i< input.size(); i++){
        	String scanned = symbolToHex.get(input.get(i).toString());
        	out.add(scanned);
        	System.out.println(symbolToHex.get("r"));
        }
        //Iterate input and correlate to characters in charLookup
        return out;
    }
	/**
	 * Scans the ASCII Table csv file
	 * @throws FileNotFoundException If the file is not present, it throws this exception.
	 */
	private void csvScanner() throws FileNotFoundException{
        Scanner tableScanner = new Scanner(asciiTable);
		tableScanner.useDelimiter(",");
		int i = 0;
        while (tableScanner.hasNext()){
        	String hex = null,symbol = null;
        	if(tableScanner.hasNext()){tableScanner.next();} 
        	if(tableScanner.hasNext() && i != 0){tableScanner.next(); i++;} 
        	if(tableScanner.hasNext()){tableScanner.next();} 
        	if(tableScanner.hasNext()){hex = tableScanner.next(); System.out.println(hex);} 
        	if(tableScanner.hasNext()){tableScanner.next();} 
        	if(tableScanner.hasNext()){symbol = tableScanner.next();}
        	if(tableScanner.hasNext()){tableScanner.next();} 
        	if(tableScanner.hasNext() && i != 0){tableScanner.next(); i++;} 
        	if(symbol != null && hex != null){
        		symbolToHex.put(hex, symbol);
        	}
        	
        }
        tableScanner.close();
    }
	private void csvScanner2 () throws IOException, FileNotFoundException{
		BufferedReader csvScanner = new BufferedReader(new FileReader(asciiTable));
		String line;
		final String DELIMTER = ",";
		while ((line = csvScanner.readLine()) != null){		//while there is a next line. At the same time, reads the next line
			String[] tokens = line.split(DELIMTER);
			String hex = tokens[2];
			String symbol = tokens[4];
			symbolToHex.put(hex, symbol);
		}
		csvScanner.close();
	}

	/**
	 *
	 * @param in
	 * @throws InterruptedException Stops the program for 500 ms, 'Thread.sleep(500)'
	 * @throws IOException 
     */
	
	public void sendMessage(String in) throws InterruptedException, IOException{
		chars = hexConvert(in);
		for (int i = 0; i < chars.size(); i++){
			String s = chars.get(i);
			for (int j = 0; j < 3; j++){

				Character c = new Character(s.charAt(j));
				slider.setValue(map.get(c).intValue());
				Thread.sleep(500);
			}
		}
	}
}
