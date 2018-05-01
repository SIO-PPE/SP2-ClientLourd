
package ppeclientlourd;

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
    	String str = "<xml version=\"1.0\" encoding\"UTF-8\"?>\n"
    			+	 "<listeMateriel>\n"
    			+ 	 "<materiels idClient=\""+unClient.getNumClient() + "\">\n"
    			+    "\t<sousContrat>\n";
    			for(Materiel m : unClient.getLeContrat().getLesMaterielsAssures()) {
    				str+=m.xmlMateriel();
    			}
    			
    	
        //return String;
        //retourne une chaine de caractères qui représente le document XML de la liste des matériels 
        //du client passé en paramètre comme le montre l'exemple de l'annexe
    	return str;
    	
    }
    
    public static void XmlClientValide (String xml){
        //retourneun booléen Vrai si le fichier xml respecte la DTD référencée dans le fichier XML
        //sinon FAUX
        
      //  return boolean;
        
    }
    
}
