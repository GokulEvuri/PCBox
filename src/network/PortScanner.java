package network;

import java.net.*;

public class PortScanner {

	public void openPort(int PortNumber){
		try  
        {  
         ServerSocket ServerSok = new ServerSocket(PortNumber);  
         System.out.println("Port in use: " + PortNumber );  
         ServerSok.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();   
        }  
		
	}
	
}
