package pl.wallet616.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataSave extends Main{
	// Default paths to files.
		public static void getPaths() {
			String os = System.getProperty("os.name");
			
			if (os.startsWith("Win")) {
				mainFolder = new File(System.getenv("APPDATA") + "/wallet616");
				mainErrorFolder = new File(System.getenv("APPDATA") + "/wallet616/error");
				mainLogFolder = new File(System.getenv("APPDATA") + "/wallet616/log");
				dataFile = new File(System.getenv("APPDATA") + "/wallet616/data.dat");
				logFile = System.getenv("APPDATA") + "/wallet616/log/";
				errorLogFile = System.getenv("APPDATA") + "/wallet616/error/";
			} else {
				mainFolder = new File("/home/wallet616");
				mainErrorFolder = new File("/home/wallet616/error");
				mainLogFolder = new File("/home/wallet616/log");
				dataFile = new File("/home/wallet616/data.dat");
				logFile = "/home/wallet616/log/";
				errorLogFile = "/home/wallet616/error/";
			}
			
			try {
				if (!mainFolder.exists()) {
					mainFolder.mkdirs();
				}
				if (!mainLogFolder.exists()) {
					mainLogFolder.mkdirs();
				}
				if (!dataFile.exists()) {
					dataFile.createNewFile();
				}
			} catch (IOException e) {
				Log.error("Unable to create necesery files and folders");
			}
		}
	
	public static boolean changeData(String userKey, String position, String data) {
		boolean repeat = false;
		try {
			BufferedReader br = new BufferedReader(new FileReader(dataFile));
		    String line;
		    String newString = "";
		    boolean foundKey = false;
		    
		    while ((line = br.readLine()) != null)
			{
		    	if (line.substring(9).startsWith(userKey)) {
			    	foundKey = true;
			    }
			    if (foundKey) {
			    	if (position.equals("userKey") && DataRead.clearText(line).startsWith("UserKey")) {
			    		newString += "UserKey: " + DataRead.clearText(data) + "\n";
			    		repeat = true;
			    		foundKey = false;
			    	} else if (position.equals("userName") && DataRead.clearText(line).startsWith("UserName")) {
			    		newString += "	UserName: " + DataRead.clearText(data) + "\n";
				    	repeat = true;
				    	foundKey = false;
			    	} else {
				    	newString += line + "\n";
				    }
			    }
		    }
		    br.close();
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(dataFile));
			bw.write(newString);
		    bw.close();
		    
		    if (repeat) {
		    	Log.log("Data has been changed in data file.");
		    } else {
		    	Log.log("Data has not been changed.");
		    }
		    
	    
		} catch (IOException e) {
			Log.error("Unable to add change data in data file.");
		}
		return repeat;
	}
	
	// Adding to log file.
	public static boolean archiveSave(String date, String userName, String message) {
		try {
			// Creating file to save log.
			File log = new File(logFile + date.substring(0, 10) + ".txt");
			if (!log.exists()) {
				log.createNewFile();
			}
			
			// Adding new data at the end of the line. 
			BufferedWriter bw = new BufferedWriter(new FileWriter(log, true));
			bw.append("[" + date.substring(11) + "] [" + userName + "]: " + message + "\n");
		    bw.close();
		    
		    // Display messages in console.
			System.out.println("[" + date.substring(11) + "] [" + userName + "]: " + message);
	    
		} catch (IOException e) {
			Log.error("System error: Unable to save errorlog.");
		}
		return true;
	}
}
