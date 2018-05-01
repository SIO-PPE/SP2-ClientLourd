package ppeclientlourd;

import java.util.Date;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		PersistanceSQL sql = new PersistanceSQL();
		
		//Client client = (Client) sql.ChargerDepuisBase("157856", "Client");
		GestionMateriels gestion = new GestionMateriels(sql);
		String xml = gestion.XmlClient(gestion.getClient("157856"));
		
		
		System.out.println(xml);
		//System.out.println(client.getLeContrat().toString());
	
	}
 
}
