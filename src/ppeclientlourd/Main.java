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

		//Génération de l'interface graphique
	//	ZDialog zd = new ZDialog(null, "CASHCASH", true);
		//ZDialogInfo zInfo = zd.showZDialog(); 
	
		 
		 MailSender mail = new MailSender(gestion.getClient("157856"));
		 
		 try {
			mail.sendMail();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 

		
			}

}
