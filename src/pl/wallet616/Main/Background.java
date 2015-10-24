package pl.wallet616.Main;

public class Background extends Main{
	public static Thread background = new Thread() {
		public void run() {
			while (timerActive) {
				try {
					// Tutaj dorobic liste do zapytan o wiadomosci.
					ConnectionHandler.connection("arch:on");
					if (serverStatus) {
						Thread.sleep(500);
					} else {
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					Log.error("Unable to keep the backgound process alive.");
				}
			}
		}
	};
}
