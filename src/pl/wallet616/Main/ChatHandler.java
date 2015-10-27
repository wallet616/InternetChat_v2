package pl.wallet616.Main;

public class ChatHandler extends Main{
	public static void chat(String message) {
		final int liczbaMax = 10;
		String[] listaWiadomosci = new String[liczbaMax];
		String[] wiadomosc = new String [3];
		
		try 
		{
			listaWiadomosci = message.split("(<:>)");
		
			for (int i = 0; i < liczbaMax; i++) {
				if (listaWiadomosci[i] != null) {
					wiadomosc = listaWiadomosci[i].split(":", 3);
				}
				
				Log.log(wiadomosc[0]);
			}
			
			lastMessage = String.valueOf(wiadomosc[0]);
		}
		catch (Exception e)
		{
			Log.log("Unable to split messages.");
		}
	}
}
