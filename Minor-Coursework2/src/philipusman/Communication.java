package philipusman;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextArea;

public class Communication extends JFrame{

    private JButton send;
    private static Camera c;
	public static void main(String[] args) throws InterruptedException, IOException {
		c = new Camera();
		Communication co = new Communication(c);
	}

    //Action Listener class implementation so that send button can perform event
    public Communication(Camera c){
		super("Communication");
    	setLayout(new BorderLayout());
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//New slider created and placed on north of border
		JSlider slider = c.returnSlider();
		add(slider, BorderLayout.NORTH);

		//New TextArea created in center of border
		JTextArea message = new JTextArea();
		add(message, BorderLayout.CENTER);
		setVisible(true);
		
		//New JButton created on south of border
		send = new JButton("Send");
		add(send, BorderLayout.SOUTH);
    	send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<Character,Integer> map= c.returnMap();
				ArrayList<String> out = new ArrayList<>();
				try {
					out = c.hexConvert(message.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				for (int i = 0; i < out.size(); i++){
					String s = out.get(i);
					System.out.println(s);
					for (int j = 0; j < s.length(); j++){
						Character c = new Character(s.charAt(j));
						slider.setValue(map.get(c).intValue());
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});
    }
}
