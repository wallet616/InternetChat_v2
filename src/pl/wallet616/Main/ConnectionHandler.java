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
	private static final int port = 27015;
	
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
			
			if ((message.length() - message.replaceAll(":", "").length()) < 2) {
				message = "0:0:0";
				Log.error("Invaild input message received.");
			} else {
				serverStatus = true;
			}
			
			if (message.startsWith("1:2:")) {
				if (!message.equals("1:2:0")) {
					ChatHandler.chat(message.substring(4));
				}
				return true;
			} else if (message.equals("1:1:1")) {
				Log.log("Succes.");
				return true;
			} else if (message.equals("1:1:0")) {
				Log.log("Failed.");
				return false;
			} else if (message.equals("1:3:0")) {
				Log.log("Joining server...");
				connection("load:" + userData[0]);
				return false;
			} else if (message.equals("1:3:1")) {
				Log.log("Connected.");
				return true;
			} else if (message.equals("1:0:0")) {
				Log.error("Command not reconised.");
				return false;
			} else {
				Log.error("Invalid repeat message received.");
			}
			
		} catch (UnknownHostException e) {
			serverStatus = false;
			Log.log("Unable to connect to server.");
		} catch (IOException e) {
			serverStatus = false;
			Log.log("Unable to create connection.");
		}
		return false;
	};
}
