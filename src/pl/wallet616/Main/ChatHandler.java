package pl.wallet616.Main;

public class ChatHandler extends Main{
	public static void chat(String message) {
		final int liczbaMax = 10;
		String[] listaWiadomosci = new String[liczbaMax];
		String[] wiadomosc = new String [4];
		
		try 
		{
			listaWiadomosci = message.split(".ws1");
			Log.log(message);
			
			for (int i = 0; i < liczbaMax; i++) {
				if (listaWiadomosci[i] != null) {
					wiadomosc = listaWiadomosci[i].split(".ws2", 4);
					lastMessage = wiadomosc[0];
				}
			}
		}
		catch (Exception e)
		{
			Log.log("Unable to split messages.");
		}
	}
}
