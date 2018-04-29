
package ppeclientlourd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


class ContratMaintenance {
    private String numContrat;
    private Date dateSignature;
    private Date dateEcheance;
    private ArrayList<Materiel>lesMaterielsAssures = new  ArrayList<Materiel>();
    
    public long getJoursRestants(){
        
        //renvoie le nb de jours avant que le contrat arrive à échéance
 
        Calendar dateSignature = new GregorianCalendar();
        Date date1 = dateSignature.getTime();
        
        Calendar dateEcheance = new GregorianCalendar();
        Date date2 = dateEcheance.getTime();
        
        // Formule permettant de calculer la différence entre les deux dates
	
        long diff =  date2.getTime()-date1.getTime() ;
        System.out.println(diff);
        long CONST_DURATION_OF_DAY = 1;
        
       //return diff;
          
        long numberOfDay = (long)diff/CONST_DURATION_OF_DAY;
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






	public boolean estValide(){
        //indique si le contrat est valide 
        //(la date du jour est entre la date de signature et la date d'échéance)
        return true;
    }
    
    public void ajouteMateriel(Materiel unMateriel){
    	lesMaterielsAssures.add(unMateriel);
        //ajoute unMateriel à la collection lesMaterielsAssures si la date de signature du contrat est
        //antérieure à la date d'installation du matériel
    }
    
    
}
