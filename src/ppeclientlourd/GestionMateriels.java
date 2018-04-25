
package ppeclientlourd;

public class GestionMateriels {
    private PersistanceSQL donnees;
   
    public GestionMateriels (PersistanceSQL lesDonnees){
        //construit un objet GestionMateriels avec un modèle de persistance associé
    }    
    public void getClient(int idClient){
       // return Client ;
        //retourne l'objet Distributeur qui possède l'identifiant idDistributeur passé en paramètre
        //retourne null si aucun Distributeur ne possède cet identifiant
    }
    
    public void XmlClient (Client unClient){
        //return String;
        //retourne une chaine de caractères qui représente le document XML de la liste des matériels 
        //du client passé en paramètre comme le montre l'exemple de l'annexe
    }
    
    public static void XmlClientValide (String xml){
        //retourneun booléen Vrai si le fichier xml respecte la DTD référencée dans le fichier XML
        //sinon FAUX
        
      //  return boolean;
        
    }
    
}
