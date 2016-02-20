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
/**
 * This class represents the Frame
 * @author Usman Liaqat and Philip To
 * Extends JFrame
 */
public class Communication extends JFrame{

    private JButton send;
    private static Camera c;
    private JSlider slider;
    private ArrayList<Character> values;
	/**
	 * Main method for the program. Creates an instance of Camera and Communication
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
    public static void main(String[] args) throws InterruptedException, IOException {
		c = new Camera();
		Communication co = new Communication(c);
	}

    /**
     * This is the constructor for Communication. 
     * @param c - an instance of a Camera
     */
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
    	send.addActionListener(new ActionListener() {		//Adds an actionListener to sthe button
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HashMap<Character,Integer> map= c.returnMap(); //gets the map used in Camera
				ArrayList<String> out = new ArrayList<>();	  
				try {
					out = c.hexConvert(message.getText());	   //Calls the hexConvert method from Camera
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				try {
					values = co.valueCreate(out);			   //Separates the hex values into individual characters
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				co.setTitle("Sending...");					   //Changes the title of the frame to Sending
				new Thread(){								   //Creates a new thread 
					public void run (){
						try {
							co.pointTowards(values);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}.start();
				co.setTitle("Communication");				   //Resets the Title of the frame to Communication again
				message.setText("");						   //Empties the textArea
    		}	
		});
    }
    /**
     * This method splits the string of hex values into individual characters
     * @param out
     * @return ArrayList of split hex values
     * @throws InterruptedException
     */
    private ArrayList<Character> valueCreate(ArrayList<String> out) throws InterruptedException{
		ArrayList<Character> values = new ArrayList<>();
    	for (int i = 0; i < out.size(); i++){
    		String s = out.get(i);			//Takes a single hex value for a character
    		for (int j = 0; j< s.length(); j++){
    			values.add(s.charAt(j));	//And splits it into its individual characters
    		}
    	}
    	return values; 						//Returns the ArrayList
    }
    /**
     * This method sets the Camera to point at certain values and puts the thread to sleep for a second
     * @param in - an arrayList of Characters
     * @throws InterruptedException
     */
    private void pointTowards(ArrayList<Character> in) throws InterruptedException{
    	for (int i = 0; i < in.size(); i++){
        	slider.setValue(in.get(i));
	    	Thread.sleep(1000);
    	}
    }
}
