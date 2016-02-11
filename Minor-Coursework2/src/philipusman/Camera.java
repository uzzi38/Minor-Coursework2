package philipusman;

import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JSlider;

public class Camera {
	private JSlider slider;
	public Camera () {
		Hashtable<Integer, JLabel> labels = new Hashtable<>();
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

	}
	public JSlider returnSlider(){
		return slider;
	}
	
}
