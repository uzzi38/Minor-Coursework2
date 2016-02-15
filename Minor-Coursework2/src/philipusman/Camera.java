package philipusman;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JSlider;

public class Camera {
	private JSlider slider;
	private HashMap<Character, Integer> map;
	private ArrayList<String> chars; //Holds the value of the string in hex
	private ArrayList<Character> input = new ArrayList<Character>(); //The string that will be entered split up
	/**
	 * 
	 */
	public Camera () {
		Hashtable<Integer, JLabel> labels = new Hashtable<>();
		map = new HashMap<>();
		for (int i = 0; i < 10; i++){
			JLabel label = new JLabel(Integer.toString(i));
			labels.put(i, label);
		}
		labels.put(10, new JLabel("A"));
		labels.put(11, new JLabel("B"));
		labels.put(12, new JLabel("C"));
		labels.put(13, new JLabel("D"));
		labels.put(14, new JLabel("E"));
		labels.put(15, new JLabel("F"));

		slider = new JSlider(0,15);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setValue(0);
		slider.setLabelTable(labels);
		
		map.put('A', 10);	map.put('B', 11);	map.put('C', 12);	map.put('D', 13);	
		map.put('E', 14);	map.put('F', 15);	map.put('0', 0);	map.put('1', 1);	
		map.put('2', 2);	map.put('3', 3);	map.put('4', 4);	map.put('5', 5);
		map.put('6', 6);	map.put('7', 7);	map.put('8', 8);	map.put('9', 9);
	}
	/**
	 * 
	 * @return
	 */
	public JSlider returnSlider(){
		return slider;
	}
	public void sendMessage(String in) throws FileNotFoundException, InterruptedException{
		chars = hexConvert(in);
		for (int i = 0; i < chars.size(); i++){
			String s = chars.get(i);
			for (int j = 0; j < 2; j++){
				Character c = new Character(s.charAt(j));
				slider.setValue(map.get(c));
				Thread.sleep(500);
			}
		}
	}
	/**
	 * 
	 * @param in
	 * @return
	 * @throws FileNotFoundException
	 */
	private ArrayList<String> hexConvert(String in) throws FileNotFoundException{
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
	/**
	 * 
	 * @param in
	 * @return
	 * @throws FileNotFoundException
	 */
	private String csvScanner(Character in) throws FileNotFoundException{
    	Scanner tableScanner = new Scanner (new File("ascii_table.csv"));
        tableScanner.useDelimiter(",");
        while(tableScanner.hasNextLine()){
        	tableScanner.nextLine();
        	tableScanner.next();
        	tableScanner.next();
        	String hex = tableScanner.next();
        	tableScanner.next();
        	String symbol = tableScanner.next();
        	if(in.equals(symbol)){
                tableScanner.close();
        		return hex;
        	}
        }
        tableScanner.close();
        return "?";
    }
}
