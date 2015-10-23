package pl.wallet616.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionHandler extends Main {
	// Connection handler connection.
	private static Socket socket;
	private static final String host = "localhost";
	private static final int port = 616;
	
	public static boolean connection(String command) {
		try {
			InetAddress address = InetAddress.getByName(host);
		    socket = new Socket(address, port);
		    
		    // Send the message to the server
		    OutputStream os = socket.getOutputStream();
		    OutputStreamWriter osw = new OutputStreamWriter(os);
	        BufferedWriter bw = new BufferedWriter(osw);
	        bw.write(userData[0] + ":" + command + "\n");
		    bw.flush();
	       
	        // Get the return message from the server
	        InputStream is = socket.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        String message = br.readLine();
	        
	        try 
			{
            	if ((message.length() - message.replaceAll(":", "").length()) < 2) {
					message = "0:0:0";
				}
			} catch (Exception e) {}
	        
	        if (message.equals("1:1:1")) {
	        	return true;
	        } else if (message.equals("1:2:0")) {
	        	connection("load:" + userData[1]);
	        	Log.log("User no reconised.");
	        	return false;
	        } else if (message.equals("1:0:0")) {
	        	Log.log("Command not reconised.");
	        	return false;
	        } else if (message.startsWith("1:1:")) {
	        	Log.log(message.substring(5));
	        	return true;
	        }
	        
			} catch (UnknownHostException e) {
			serverStatus = false;
			Log.error("Unable to connect to server.");
		} catch (IOException e) {
			serverStatus = false;
			Log.log("Unable to create connection.");
		}
		return false;
	};
}
