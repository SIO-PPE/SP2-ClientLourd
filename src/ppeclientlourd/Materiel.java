
package ppeclientlourd;

import java.util.Date;


class Materiel {
    private int numSerie;
    private Date dateVente;
    private double prixVente;
    private String emplacement;
    private TypeMateriel leType;
    
    
    
    public Materiel(int numSerie, Date dateVente, double prixVente, String emplacement, TypeMateriel leType) {

		this.numSerie = numSerie;
		this.dateVente = dateVente;
		this.prixVente = prixVente;
		this.emplacement = emplacement;
		this.leType = leType;
	}

    public String toString() {
    	return numSerie +" "+ dateVente.toString()+" " + prixVente+" " + emplacement +  " " + leType.toString() ;
    }

    
	public void xmlMateriel(String xmlMateriel){
        //retourne la chaîne correspondant au code xml représentant le matériel(annexe)
    }
}
