/*
 * Author Gokul S Evuri (gokul@evuri.com)
 * 
 * */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;


public class GraphPanel extends JPanel{

	  /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			private int diameter = 20;

			
			public void paint(Graphics g) {
			
				
				Point gPoint = 	PCBox.p;
				g.setColor(Color.white);
		        g.fillRect(0, 0, 800, 400);
		        g.setColor(Color.black);
				g.fillOval(gPoint.x, gPoint.y, diameter, diameter);
		        g.setColor(Color.black);
		        g.drawOval(gPoint.x, gPoint.y, diameter, diameter);
		    }
			

		
			
			

}