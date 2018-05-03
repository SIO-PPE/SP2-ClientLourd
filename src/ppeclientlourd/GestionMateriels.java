
package ppeclientlourd;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.xml.sax.SAXParseException;

public class GestionMateriels {
    private PersistanceSQL donnees;
   
    public GestionMateriels (PersistanceSQL lesDonnees){
        //construit un objet GestionMateriels avec un modèle de persistance associé
    	this.donnees = lesDonnees;
    }    
    public Client getClient(String idClient){
       return (Client) donnees.ChargerDepuisBase(idClient, "Client");
    }
    
    public String XmlClient (Client unClient){
    	String str = "<xml version=\"1.0\" encoding\"UTF-8\"?>\n<!DOCTYPE listeMateriel SYSTEM \"materielClient.dtd\">\n"
    			+	 "<listeMateriel>\n"
    			+ 	 "<materiels idClient=\""+unClient.getNumClient() + "\">\n"
    			+    "\t<sousContrat>\n";
    			for(Materiel m : unClient.getLeContrat().getLesMaterielsAssures()) {
    				str+=m.xmlMateriel(unClient.getLeContrat().getJoursRestants());
    			}
    	str +=  "\t</sousContrat>\n"
    		  + "\t<horsContrat>\n";
    	for(Materiel m : unClient.getLesMateriels()) {
			str+=m.xmlMateriel(0);
		}
    	str +=  "\t</horsContrat>\n"
    			+ "</listeMateriel>";
    	
        //return String;
        //retourne une chaine de caractères qui représente le document XML de la liste des matériels 
        //du client passé en paramètre comme le montre l'exemple de l'annexe
    	
    	return str;
    	
    }
    
    public void XmlClientValide (String xml){
    	
        ArrayList<SAXParseException> erreurs = new ArrayList();
    try {
        erreurs = ValiderDocXml.chercherErreurs(xml);
    
        if (!erreurs.isEmpty()) {
            System.out.println("YA des erreurs !");
            for(SAXParseException e : erreurs){
             Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
            }
            
        } else {
            System.out.println("Pas derreur !");
        }
        } catch (Exception ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    }
    

    
}
