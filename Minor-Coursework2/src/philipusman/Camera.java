package philipusman;
import javax.swing.JFrame;
import javax.swing.JSlider;

public class Camera {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 300);
		
		JSlider yesNo = new JSlider(0,15);
		yesNo.setMajorTickSpacing(1);
		yesNo.setPaintTicks(true);
		yesNo.setSnapToTicks(true);
		yesNo.setPaintLabels(true);
		
		
		frame.add(yesNo);
		frame.setVisible(true);
	}

}
