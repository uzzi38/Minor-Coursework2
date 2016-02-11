package philipusman;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JSlider;

public class Camera {
	private JSlider slider;
	private HashMap<Integer, Character> map;
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
		
		map.put(10, new Character('A'));
		map.put(11, new Character('B'));
		map.put(12, new Character('C'));
		map.put(13, new Character('D'));
		map.put(14, new Character('E'));
		map.put(15, new Character('F'));
		
	}
	public JSlider returnSlider(){
		return slider;
	}
	public void setPosition(List<Integer> l){
		for (int i = 0; i< l.size(); i++){
			int j = l.get(i);
			
		}
	}
}
