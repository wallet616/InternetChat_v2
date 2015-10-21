package pl.wallet616.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DataRead extends Main{
	private static File folder;
	private static File file;
	private static final String OS = System.getProperty("os.name");
	
	public static boolean loadUser(String userKey) {
		boolean repeat = false;
		try {
			if (OS.startsWith("Win")) {
				folder = new File(System.getenv("APPDATA") + "/wallet616");
				file = new File(System.getenv("APPDATA") + "/wallet616/data.dat");
			} else {
				folder = new File("/home/wallet616");
				file = new File("/home/wallet616/data.dat");
			}
			
			if (!folder.exists()) {
				folder.mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
		    
			// Data to assign in loops.
			BufferedReader br = new BufferedReader(new FileReader(file));
		    String line;
		    boolean userKeyFound = false;
		    
			loopbreak:
			while ((line = br.readLine()) != null) {
				line = clearText(line);
			    
			    if (line.startsWith("UserKey: ") && userKeyFound) {
			    	break loopbreak;
			    }
			    
			    if (line.equals("UserKey: ")) {
			    	userData[0] = line.substring(9);
			    	userKeyFound = true;
			    }
			    if (line.startsWith("UserName: ") && userKeyFound) {
			    	userData[1] = line.substring(10);
			    }
		    }
	
		    br.close();
		    
			if (repeat) {
			    Log.log("User key " + userData[0] + " has been loaded.");
			    Log.log("User name " + userData[1] + " has been loaded.");
			} else {
			    Log.log("User has not been loaded.");
			}
	    
		} catch (IOException e) {
			Log.error("Unable to load users list.");
		}
		return repeat;
	}
	
	public static String clearText(String message) {
		try 
		{
			message = message.replaceAll("\\s+", " ");
			
			if ((message.substring(0, 1)).equals(" ")) {
				message = message.substring(1);
			}
			if ((message.substring((message.length() - 1), message.length())).equals(" ")) {
				message = message.substring(0, (message.length() - 1));
			}
		}
		catch (Exception e)
	    {
			Log.error("Unable to clear text.");
		}
		return message;
	}
}
