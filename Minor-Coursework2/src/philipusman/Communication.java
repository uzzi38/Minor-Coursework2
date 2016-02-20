package philipusman;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JSlider slider;
    private ArrayList<Character> values;
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
		Communication co = this;
		//New slider created and placed on north of border
		slider = c.returnSlider();
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
				try {
					values = co.valueCreate(out);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				co.setTitle("Sending...");
				new Thread(){
					public void run (){
						try {
							for(int k = 0; k < values.size(); k++){	
								co.pointTowards(values.get(k));
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}.start();
				co.setTitle("Communication");
    		}	
		});
    }
    private ArrayList<Character> valueCreate(ArrayList<String> out) throws InterruptedException{
		ArrayList<Character> values = new ArrayList<>();
    	for (int i = 0; i < out.size(); i++){
    		String s = out.get(i);
    		for (int j = 0; j< s.length(); j++){
    			values.add(s.charAt(j));
    		}
    	}
    	return values;
    }
    private void pointTowards(int i) throws InterruptedException{
    	slider.setValue(i);
    	Thread.sleep(1000);
    }
}
