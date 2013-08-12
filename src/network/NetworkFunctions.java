/*
 * Author Gokul S Evuri
 * 
 * ToDo List [Development]
 * 
 * 
 * ToDo List [After deployment]
 * [1] Implementation to send a trace/error record to quintessoft 
 * when all ports are busy
 * [2] Send crash report
 * Q's
 * Checking socket connection success for null - is too defensive coding, 
 * 	is there a necessity to adapt let it crash approach?
 * 
 * */

package network;

import java.io.IOException;
import java.net.*;

public class NetworkFunctions {

	/* Put a bunch of unused portnumbers from wikipedia */
	static int[] PORT_NUM_LIST = { 4449 };

	/*
	 * Returns a ServerSocket object for the given PortNumber in case fails to
	 * open the port returns null
	 */
	public ServerSocket openPort(int portNumber) {
		ServerSocket serverSock = null;
		try {
			serverSock = new ServerSocket(portNumber);
			System.out.println("Port in use: " + portNumber);
			serverSock.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return serverSock;
	}

	/*
	 * Returns a ServerSocket object using any one of the portnumbers array [1]
	 */
	public ServerSocket openPort(int[] portNumbers, NetworkFunctions ps) {
		ServerSocket serverSock = null;
		boolean connectionSuccess = false;
		int pnumlen = portNumbers.length;
		while (!connectionSuccess) {
			if (pnumlen > 0 && serverSock == null) {
				serverSock = ps.openPort(portNumbers[pnumlen - 1]);
			} else {
				connectionSuccess = true;
			}
			pnumlen--;
		}
		if (serverSock != null) {
			return serverSock;
		} else {
			/* [1] Handle it here */
			return null;
		}

	}

	public ServerSocket openPort() {
		NetworkFunctions ps = new NetworkFunctions();
		return ps.openPort(PORT_NUM_LIST, ps); /*Caution, not sure about the object passing*/
	}
	
	/* Use the method with caution, might become a bottle neck, serverSock.accept() will
	 * make the thread wait until a client connection happens*/
	public Socket acceptClientConnection(ServerSocket serverSock){
		Socket clientSocket = null;
		try {
			clientSocket = serverSock.accept();
				} 
		catch (IOException e) {
			/**[2] Handle it here**/
			System.exit(-1);
		}
		return clientSocket;
	}

}
