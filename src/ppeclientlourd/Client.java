
package ppeclientlourd;

import java.util.ArrayList;

public class Client {
    private String numClient;
    private String raisonSociale;
    private String siren;
    private String codeApe;
    private String adresse; 
    private String telClient;
    private String email;
    private int dureeDeplacement;
    private int distanceKm;
    private ArrayList<Materiel>lesMateriels;
    private ContratMaintenance leContrat;
    
    public void getMateriels(ArrayList lesMateriels){
        //retourne l'ensemble des matériels du client
        
        
    } 
    
    public void getMaterielsSousContrat(){
        //retourne l'ensemble des mat&ériels pour lesquels le client a souscrit un contrat de maintenance 
        //qui est encore valide (la date du jour est entre la date de signature et date d'échéance)
       
       // return ArrayList<Materiel>;
    }
    
    public boolean estAssure(){
        //retourne vrai si le client est assuré, sinon faux
        return true;
    }
}


