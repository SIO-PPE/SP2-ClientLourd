
package ppeclientlourd;
public class PersistanceSQL {
	String ipBase;
	String nomBaseDonnee;
	int port;

	public PersistanceSQL (String ipBase, String nomBaseDonnee, int port){
		//construit un objet dans PersistanceSQL
		//cet objet permettra de charger les données depuis une bdd ou de les sauvegarder dans la base
		ipBase=this.ipBase;
		nomBaseDonnee=this.nomBaseDonnee;
		port=this.port;    
	}

	public void RangerDansBase(Object unObjet){
		//stock les données de l'objet dans la base de données   
	}

	public void ChargerDepuisBase(String id, String nomClasse){
		//retourne l'objet de la classe NomClasse dont l'identifiant est "id"
		//cet objet est chargé
		//depuis la bdd, ainsi que l'ensemble de ses objets liés (voir l'exemple d'utilisation ci-dessous)
		//reyourne null si aucun objet de cette classe ne possède cet identifiant

		/*Exemple d'utilisation:
  //persist est une instance de PersistanceSQL
          Client leClient <- persist.chargerDepuisBase ("2", "Client")
          //leClient est l'instance de la classe dont l'identifiant est 2.

                  //Toutes les commandes du distributeur sont au tomatiquement chargées
                  //dans la collection leClient.lesMateriels
                  //toutes les données utiles sont également chargées
		 * */
	}
}
