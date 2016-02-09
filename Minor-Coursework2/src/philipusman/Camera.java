package philipusman;
import javax.swing.JFrame;
import javax.swing.JSlider;

public class Camera {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 300);
		
		JSlider slider = new JSlider(0,15);
		slider.setMajorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setPaintLabels(true);
		slider.setValue(0);
		
		
		frame.add(slider);
		frame.setVisible(true);
	}

}
