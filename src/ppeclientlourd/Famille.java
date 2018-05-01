
package ppeclientlourd;


class Famille {
    private String codeFamille;
    private String libelleFamille;

    public Famille(String codeFamille, String libelleFamille) {

		this.codeFamille = codeFamille;
		this.libelleFamille = libelleFamille;
	}
    
public String toString() {
	return codeFamille + " " + libelleFamille;
}

public String getCodeFamille() {
	return codeFamille;
}

public String getLibelleFamille() {
	return libelleFamille;
}


     
    //libre
}
