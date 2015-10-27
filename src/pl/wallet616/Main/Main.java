package pl.wallet616.Main;

import java.io.File;

public class Main {
	// Static data.
	public static boolean serverStatus = false;
	public static boolean timerActive = true;
	public static String lastMessage = "0";
	
	// User Data. UserKey : UserName
	public static String[] userData = new String[5];
	
	// Files.
	public static File mainFolder;
	public static File mainServerFolder;
	public static File mainErrorFolder;
	public static File mainLogFolder;
	public static File dataFile;
	public static String logFile;
	public static String errorLogFile;

	public static void main(String[] args) {
		
		DataSave.getPaths();
		DataRead.loadUser();
		Background.background.start();
	}

}
