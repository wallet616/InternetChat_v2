package pl.wallet616.Main;

public class Main {
	public static String[] userData = new String[5];
	public static boolean serverStatus = false;
	public static boolean timerActive = true;

	public static void main(String[] args) {
		DataRead.loadUser();
		Background.background.start();
		

	}

}
