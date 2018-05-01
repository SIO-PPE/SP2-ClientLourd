
package ppeclientlourd;

class TypeMateriel {
    private String referenceInterne;
    private String libelleTypeMateriel;
    private Famille laFamille;
    
    
	public TypeMateriel(String referenceInterne, String libelleTypeMateriel, Famille laFamille) {
		
		this.referenceInterne = referenceInterne;
		this.libelleTypeMateriel = libelleTypeMateriel;
		this.laFamille = laFamille;
	}
	
	public String toString() {
		return "" +referenceInterne +" " + libelleTypeMateriel + " " + laFamille.toString();
	}

	public String getReferenceInterne() {
		return referenceInterne;
	}

	public String getLibelleTypeMateriel() {
		return libelleTypeMateriel;
	}

	public Famille getLaFamille() {
		return laFamille;
	}
    
    
    
    //public libre
}
