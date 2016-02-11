package philipusman;

import javax.swing.JFrame;
import javax.swing.JSlider;

public class Communication {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Camera c = new Camera();
		JSlider slider = c.returnSlider();
		frame.add(slider);
		
		
		
		frame.setVisible(true);
	}

}
