package ppeclientlourd;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xml.sax.SAXParseException;

import Fichier.Fichier;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		PersistanceSQL sql = new PersistanceSQL();
		
		//Client client = (Client) sql.ChargerDepuisBase("157856", "Client");
		GestionMateriels gestion = new GestionMateriels(sql);
		Client client = gestion.getClient("157856");
		String xml = gestion.XmlClient(client);
		
		
		System.out.println(xml);
		
		 Fichier f = new Fichier();
	        f.ouvrir("materielClient.xml", 'W');
	        f.ecrire(xml);
	        f.fermer();
	        
	        gestion.XmlClientValide(xml);
	        
           

		//System.out.println(client.getLeContrat().toString());
	
	}
 
}
