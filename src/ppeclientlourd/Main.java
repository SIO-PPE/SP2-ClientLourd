package ppeclientlourd;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.itextpdf.text.DocumentException;

import ie.ZDialog;
import ie.ZDialog.ZDialogInfo;
import pdf.MailSender;


public class Main {


	public static Client activeClient;
	public static GestionMateriels gestion;
	public static void main(String[] args) {
    
		//Chargement de la connexion sql
		PersistanceSQL sql = new PersistanceSQL();

		//ID CLIENT REFERENCE 157856

		//Remplie l'objet gestion des données de la base de données
		 gestion = new GestionMateriels(sql);
	 		//Check Email renew
		 
		 
		 
		    Thread t = new Thread(new ZDialog(null, "CASHCASH", true));
		    t.start();
		 
		 
		 System.out.println("Recherche de client aux contrat à renouveller..");
	for(Client c : sql.getClientsToSendMail()) {
		MailSender mail = new MailSender(c);
		try {
			if(mail.sendMail())
				sql.setRelanceEmail(c);
		} catch (UnsupportedEncodingException | MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
		 
		

		 
			

		 

		
			}

}
