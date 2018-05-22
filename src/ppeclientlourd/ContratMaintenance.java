
package ppeclientlourd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class ContratMaintenance {
    public String numContrat;
    private Date dateSignature;
    public Date dateEcheance;
    private ArrayList<Materiel>lesMaterielsAssures = new  ArrayList<Materiel>();
    
    
    public String[][] getInfoTableau(){
    	String[][] tabs = new String[lesMaterielsAssures.size()][4];
    	for(int i = 0; i < lesMaterielsAssures.size(); i++) {
    		tabs[i][0] = Integer.toString(lesMaterielsAssures.get(i).getNumSerie());
    		tabs[i][1] = lesMaterielsAssures.get(i).getLeType().getLibelleTypeMateriel();
    		tabs[i][2] = lesMaterielsAssures.get(i).getLeType().getLaFamille().getLibelleFamille();
    		tabs[i][3] = lesMaterielsAssures.get(i).getDateVente().toString();
    	}
    	return tabs;
    }
    public long getJoursRestants(){
    	   long CONST_DURATION_OF_DAY = 1000l * 60 * 60 * 24;
        //renvoie le nb de jours avant que le contrat arrive à échéance
 
    	   

        

        // Formule permettant de calculer la différence entre les deux dates
    	   Date now = new Date();
        long diff =  dateEcheance.getTime() - now.getTime() ;
        

        
        
        long numberOfDay = (long) diff /CONST_DURATION_OF_DAY;
        
        return numberOfDay;
        
    
    
     }    
   
    
    public String toString() {
    	String str =  numContrat + " "+ dateSignature.toString()+ " " + dateEcheance.toString()+ " " ;
    	for(Materiel m : lesMaterielsAssures) {
    		str += "\n "+ m.toString(); 
    	}return str;
    	
    }

        
       
    
    
    public ContratMaintenance(String numContrat, Date dateSignature, Date dateEcheance) {

		this.numContrat = numContrat;
		this.dateSignature = dateSignature;
		this.dateEcheance = dateEcheance;
	}





    public String getNumContrat() {
		return numContrat;
	}


	public Date getDateSignature() {
		return dateSignature;
	}


	public Date getDateEcheance() {
		return dateEcheance;
	}


	public ArrayList<Materiel> getLesMaterielsAssures() {
		return lesMaterielsAssures;
	}


	//indique si le contrat est valide 
    //(la date du jour est entre la date de signature et la date d'échéance
	public boolean estValide(){
		Date now = new Date();
		if(now.getTime() > dateEcheance.getTime())
        return false;
		else return true;
    }
    
    public void ajouteMateriel(Materiel unMateriel){
    	if(unMateriel.getDateInstall().getTime() > dateSignature.getTime())
    		lesMaterielsAssures.add(unMateriel);
    	else
    		System.out.println("AJOUT DU MATERIEL " + unMateriel.getNumSerie() + "dans les contrat assuré impossible car la date de signature est antérieur a la date d'installation");
        //ajoute unMateriel à la collection lesMaterielsAssures si la date de signature du contrat est
        //antérieure à la date d'installation du matériel
    }
    
    
}
