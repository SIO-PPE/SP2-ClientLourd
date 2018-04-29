
package ppeclientlourd;

import java.util.ArrayList;

public class Client {
    private String numClient;
    private String raisonSociale;
    public String siren;
    private String codeApe;
    private String adresse; 
    private String telClient;
    private String email;
    private int dureeDeplacement;
    private int distanceKm;
    private ArrayList<Materiel>lesMateriels;
    private ContratMaintenance leContrat;
    
    
    
    
    
    public Client(String numClient, String raisonSociale, String siren, String codeApe, String adresse,
			String telClient, String email, int dureeDeplacement, int distanceKm)//,ContratMaintenance leContrat)
    {
		
		this.numClient = numClient;
		this.raisonSociale = raisonSociale;
		this.siren = siren;
		this.codeApe = codeApe;
		this.adresse = adresse;
		this.telClient = telClient;
		this.email = email;
		this.dureeDeplacement = dureeDeplacement;
		this.distanceKm =  distanceKm;
		//this.leContrat = leContrat;
	}

    public String toString() {
    	return numClient +" "+ raisonSociale+" " + siren+" " + codeApe+" " + adresse+" " + telClient+" "+ email+" "+  dureeDeplacement+" "+ distanceKm;
    }
    
	public ArrayList<Materiel> getLesMateriels() {
		return lesMateriels;
	}



	public void setLesMateriels(ArrayList<Materiel> lesMateriels) {
		this.lesMateriels = lesMateriels;
	}



	public ContratMaintenance getLeContrat() {
		return leContrat;
	}



	public void setLeContrat(ContratMaintenance leContrat) {
		this.leContrat = leContrat;
	}



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


