package pl.wallet616.Main;

public class Background extends Main{
	public static boolean timerActive = true;
	public static Thread background = new Thread() {
		public void run() {
			while (timerActive) {
				try {
					
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.error("Unable to keep the backgound process alive.");
				}
			}
		}
	};
	
}
