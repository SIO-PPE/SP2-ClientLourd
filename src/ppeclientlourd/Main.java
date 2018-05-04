package ppeclientlourd;

import javax.swing.JFileChooser;

import Fichier.Fichier;
import ie.Fenetre;
import ie.ZDialog;
import ie.ZDialog.ZDialogInfo;


public class Main {


	public static Client activeClient;
	public static GestionMateriels gestion;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	   
        // création de la boîte de dialogue

 
    
		PersistanceSQL sql = new PersistanceSQL();

		//Client client = (Client) sql.ChargerDepuisBase("157856", "Client");
		 gestion = new GestionMateriels(sql);

		/*	String xml = gestion.XmlClient(client);


		System.out.println(xml);

		 Fichier f = new Fichier();
	        f.ouvrir("dsqd\\Documents\\materielClient.xml", 'W');
	        f.ecrire(xml);
	        f.fermer();

	        gestion.XmlClientValide(xml);

		 
*/
		ZDialog zd = new ZDialog(null, "CASHCASH", true);
		ZDialogInfo zInfo = zd.showZDialog(); 
		String nom = zd.getZDialog().getNom();
		activeClient = gestion.getClient(nom);
		if(activeClient!= null) {

			Fenetre fen = new Fenetre();
		}
	}

}
