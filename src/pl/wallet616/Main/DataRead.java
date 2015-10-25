package pl.wallet616.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataRead extends Main{
	
	public static boolean loadUser() {
		boolean repeat = false;
		try {
			// Data to assign in loops.
			BufferedReader br = new BufferedReader(new FileReader(dataFile));
		    String line;
		    boolean userKeyFound = false;
		    
			loopbreak:
			while ((line = br.readLine()) != null) {
				line = clearText(line);
			    
			    if (line.startsWith("UserKey: ") && userKeyFound) {
			    	break loopbreak;
			    }
			    
			    if (line.startsWith("UserKey: ")) {
			    	userData[0] = line.substring(9);
			    	userKeyFound = true;
			    	repeat = true;
			    }
			    if (line.startsWith("UserName: ") && userKeyFound) {
			    	userData[1] = line.substring(10);
			    }
		    }
	
		    br.close();
		    
			if (repeat) {
			    Log.log("User key: " + userData[0] + ".");
			    Log.log("User name: " + userData[1] + ".");
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
