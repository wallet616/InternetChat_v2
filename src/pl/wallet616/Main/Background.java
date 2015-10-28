package pl.wallet616.Main;

public class Background extends Main{
	public static Thread background = new Thread() {
		public void run() {
			while (timerActive) {
				try {
					ConnectionHandler.connection("arch:" + lastMessage);
					
					ConnectionHandler.connection("say:KKKSKKDKSKDKK");
					
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
