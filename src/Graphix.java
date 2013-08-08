/*
 * Author Gokul S Evuri (gokul@evuri.com)
 * 
 * */


import javax.swing.*;

	public class Graphix extends JFrame {

	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;



	public Graphix(String name, int x, int y, int width, int height) {
	    super(name);
	    setBounds(x, y, width, height);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    JPanel panel = new GraphPanel();
	    add(panel);
	}

	
	
	
	}