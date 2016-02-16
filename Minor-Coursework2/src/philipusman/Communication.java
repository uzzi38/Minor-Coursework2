package philipusman;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextArea;

public class Communication implements ActionListener{

    private static JButton send;
    private static Camera c;

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        //New interface created of size 800x800, with program to stop when window is closed
        JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//New slider created and placed on north of border
		c = new Camera();
		JSlider slider = c.returnSlider();
		frame.add(slider, BorderLayout.NORTH);

        //New JButton created on south of border
		send = new JButton("Send");
		frame.add(send, BorderLayout.SOUTH);

        //New TextArea created in center of border
		JTextArea message = new JTextArea();
		frame.add(message, BorderLayout.CENTER);
		c.sendMessage("hello");
		frame.setVisible(true);
	}

    //Action Listener class implementation so that send button can perform event
    public Communication(){
        send.addActionListener(this);
    }

	/**
	 *
	 * @param e
     */
    public void actionPerformed (ActionEvent e){
        try {
			c.sendMessage(send.getText());
		} catch (FileNotFoundException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

}
