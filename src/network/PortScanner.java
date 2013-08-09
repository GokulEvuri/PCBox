/*
 * Author Gokul S Evuri
 * 
 * ToDo List
 * [1] Implementation to send a trace/error record to quintessoft 
 * when all ports are busy
 * 
 * */

package network;

import java.net.*;

public class PortScanner {

	/*Put a bunch of unused portnumbers from wikipedia*/
	static int [] PORT_NUM_LIST = {4449};
	
	/*Returns a ServerSocket object for the given PortNumber 
	 * in case fails to open the port returns null*/
	public ServerSocket openPort(int portNumber){
		ServerSocket serverSock = null;
		try  
        {  
         serverSock = new ServerSocket(portNumber);  
         System.out.println("Port in use: " + portNumber );  
         serverSock.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();   
        }  
		return serverSock;
	}
	
	/*Returns a ServerSocket object using any one 
	 * of the portnumbers array [1]*/
	public ServerSocket openPort(int[] portNumbers,PortScanner ps){
		ServerSocket serverSock = null;
		boolean connectionSuccess = false;
		int pnumlen = portNumbers.length;
		while(!connectionSuccess){
			if(pnumlen>0 && serverSock == null){
				serverSock = ps.openPort(portNumbers[pnumlen-1]);
			}
			else {
				connectionSuccess = true;
			}
			pnumlen--;
		}
		if(serverSock != null){
			return serverSock;
		}
		else{
			/*[1] Handle it here*/
			return null;
			}			
		
	}
	
	public ServerSocket openPort(){
		PortScanner ps = new PortScanner();
		return ps.openPort(PORT_NUM_LIST, ps);
	}
	
}
