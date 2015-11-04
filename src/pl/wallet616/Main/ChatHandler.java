package pl.wallet616.Main;

public class ChatHandler extends Main{
	public static boolean chat(String message) {
		try 
		{
			String[] listaWiadomosci = message.split(".ws1");
			
			for (int i = 0; i < listaWiadomosci.length; i++) {
				String[] mess = listaWiadomosci[i].split(".ws2", 4);
				lastMessage = mess[0];
				DataSave.archiveSave(mess[1], mess[2], mess[3]);
				
			}
			return true;
		}
		catch (Exception e)
		{
			Log.log("Unable to split messages.");
		}
		return false;
	}
}
