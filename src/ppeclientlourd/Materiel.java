
package ppeclientlourd;

import java.util.Date;


class Materiel {
    private int numSerie;
    private Date dateVente;
    private Date dateInstall;
    private double prixVente;
    private String emplacement;
    private TypeMateriel leType;
    
    
    
    public Materiel(int numSerie, Date dateVente,Date dateInstall, double prixVente, String emplacement, TypeMateriel leType) {

		this.numSerie = numSerie;
		this.dateVente = dateVente;
		this.dateInstall = dateInstall;
		this.prixVente = prixVente;
		this.emplacement = emplacement;
		this.leType = leType;
	}

    public String toString() {
    	return numSerie +" "+ dateVente.toString()+" " + prixVente+" " + emplacement +  " " + leType.toString() ;
    }

    
	public String xmlMateriel(){
        //retourne la chaîne correspondant au code xml représentant le matériel(annexe)
		String str = "\t<materiel numSerie=\""+ numSerie + "\"> \n"
					 + "\t\t<type refInterne=\""+leType.getReferenceInterne() + "\" libelle=\""+leType.getLibelleTypeMateriel() +"\" /> \n"
					 + "\t\t<famille codeFamille=\""+leType.getLaFamille().getCodeFamille() + "\" libelle=\""+leType.getLaFamille().getLibelleFamille()+"\" /> \n";
					return str; 

    }

	public int getNumSerie() {
		return numSerie;
	}

	public Date getDateVente() {
		return dateVente;
	}

	public Date getDateInstall() {
		return dateInstall;
	}

	public double getPrixVente() {
		return prixVente;
	}

	public String getEmplacement() {
		return emplacement;
	}

	public TypeMateriel getLeType() {
		return leType;
	}
	
	
}
