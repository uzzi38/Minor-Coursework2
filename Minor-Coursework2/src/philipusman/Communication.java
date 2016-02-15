package philipusman;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextArea;

public class Communication {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Camera c = new Camera();
		JSlider slider = c.returnSlider();
		frame.add(slider, BorderLayout.NORTH);
		
		JButton send = new JButton("Send");
		frame.add(send, BorderLayout.SOUTH);
		
		JTextArea message = new JTextArea();
		frame.add(message, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}

}
