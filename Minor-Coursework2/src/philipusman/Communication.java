package philipusman;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.InterruptedIOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextArea;

public class Communication implements ActionListener{

    private static JButton send;
    private static Camera c;

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		c = new Camera();
		JSlider slider = c.returnSlider();
		frame.add(slider, BorderLayout.NORTH);
		
		send = new JButton("Send");
		frame.add(send, BorderLayout.SOUTH);
		
		JTextArea message = new JTextArea();
		frame.add(message, BorderLayout.CENTER);
		c.sendMessage("hello");
		frame.setVisible(true);
	}

    public Communication(){
        send.addActionListener(this);
    }

    public void actionPerformed (ActionEvent e){
        try {
			c.sendMessage(send.getText());
		} catch (FileNotFoundException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

}
