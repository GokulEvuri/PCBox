import java.awt.Point;
import java.util.Scanner;



public class PCBox {

	static Point p = new Point();
	boolean bool = true;
	String str;	
	
	public static void main(String args[]){
		PCBox pb = new PCBox();
		pb.render_loop();
		
		new Graphix("PCBOX", 60, 60, 800, 400).setVisible(true);
	}
	

	public void render_loop(){
		Scanner in = new Scanner(System.in);
		
		if (bool!= false){
			System.out.println("Enter X point or exit");
			str = in.nextLine();
			if(str.equalsIgnoreCase("exit")){
				System.out.println(str);
				bool=false;
			}
			else{
				p.x = Integer.parseInt(str);
				System.out.println("Enter Y point");
				str = in.nextLine();
				p.y = Integer.parseInt(str);
			}
		}
	}
	
	
}
