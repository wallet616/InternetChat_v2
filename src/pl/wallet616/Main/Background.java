package pl.wallet616.Main;

public class Background extends Main{
	public static Thread background = new Thread() {
		public void run() {
			while (timerActive) {
				try {
					ConnectionHandler.connection("status:on");
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Log.error("Unable to keep the backgound process alive.");
				}
			}
		}
	};
	
}
