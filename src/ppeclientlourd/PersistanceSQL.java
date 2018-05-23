
package ppeclientlourd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersistanceSQL {//&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
	public String url = "jdbc:mysql://localhost:3306/ppe_mlge?useSSL=false";
	public String utilisateur = "root";
	public String motDePasse = "";
	static Connection con;

	
	//SELECT `Numero_Client` FROM `contrat_de_maintenance` WHERE `Date_Echeance` < NOW()
	public PersistanceSQL (){
		//construit un objet dans PersistanceSQL
		//cet objet permettra de charger les données depuis une bdd ou de les sauvegarder dans la base  



		try
		{
			//loading the jdbc driver
			//Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			//Get la connexion à la base de donnée
			con = DriverManager.getConnection(url,utilisateur,motDePasse);

		}
		catch(SQLException e)
		{
			e.printStackTrace();  
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}
	
	public void setRelanceEmail(Client client) {
		PreparedStatement stmt1;
		try {
			stmt1 = con.prepareStatement("UPDATE contrat_de_maintenance SET RelanceEmail = 1 WHERE Numero_Client = "+client.getNumClient());

			stmt1.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    //SELECT `Numero_Client` FROM `contrat_de_maintenance` WHERE `Date_Echeance` > NOW() and `RelanceEmail` = 0;
    public ArrayList<Client> getClientsToSendMail(){
    	ArrayList<Client> clientsNeedRenew = new ArrayList<Client>();
    	PreparedStatement stmt;
		
			try {
				stmt = con.prepareStatement("SELECT Numero_Client FROM contrat_de_maintenance WHERE Date_Echeance < NOW() and RelanceEmail = 0");

			stmt.executeQuery();
			ResultSet result = stmt.getResultSet();
			while(result.next()){
				
				clientsNeedRenew.add((Client) ChargerDepuisBase(result.getString(1), "Client"));
			}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
    	return clientsNeedRenew;
    }

	//stock les données de l'objet dans la base de données 
	public void RangerDansBase(Object unObjet){  
		//FOnction inutilisé
	}

	
	public Object ChargerDepuisBase(String id, String nomClasse) {


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

		//On remplie l'objet Client 
		if(nomClasse.equalsIgnoreCase("Client")) {
			PreparedStatement stmt;
			try {
				stmt = con.prepareStatement("select * from client where Numero_Client = ?");
				stmt.setString(1, id);
				stmt.executeQuery();
				ResultSet result = stmt.getResultSet();

				//Parcour des résultat
				if(result.next()){

					//Création d'un client avec les données de l'occurence en cour
					Client client = new Client(id, result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(8), result.getInt(9),result.getInt(10));
					//Création du contrat lié au client
					ContratMaintenance contrat = (ContratMaintenance) ChargerDepuisBase(result.getString(11), "ContratMaintenance");
					client.setLeContrat(contrat);
					
					//CHARGEMENT DES MATERIEL SANS CONTRAT
					PreparedStatement stmt1;
					stmt1 = con.prepareStatement("SELECT * FROM materiel WHERE Numero_de_contrat IS NULL ");
					stmt1.executeQuery();
					ResultSet result1 = stmt1.getResultSet();


					while(result1.next()){
						Materiel mat = new Materiel(result1.getInt(1), result1.getDate(2), result1.getDate(3), result1.getInt(4), result1.getString(5), (TypeMateriel)  ChargerDepuisBase(result1.getString(6), "LeTypeMateriel")); //TODO LE TYPE;
						client.getLesMateriels().add(mat);
						
					}
					return client;
				}

				stmt.close(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		else if (nomClasse.equalsIgnoreCase("ContratMaintenance")) {
			PreparedStatement stmt;
			try {
				stmt = con.prepareStatement("select * from contrat_de_maintenance where Numero_de_contrat = ?");
				stmt.setString(1, id);
				stmt.executeQuery();
				ResultSet result = stmt.getResultSet();

				//CHARGEMENT MATERIEL AVEC CONTRAT
				if(result.next()){
					ContratMaintenance contrat = new ContratMaintenance(id, result.getDate(2), result.getDate(3));
					PreparedStatement stmt1;
					stmt1 = con.prepareStatement("SELECT * FROM materiel WHERE Numero_de_contrat= ?");
					stmt1.setString(1, id);
					stmt1.executeQuery();
					ResultSet result1 = stmt1.getResultSet();


					while(result1.next()){
						Materiel mat = new Materiel(result1.getInt(1), result1.getDate(2), result1.getDate(3), result1.getInt(4), result1.getString(5), (TypeMateriel)  ChargerDepuisBase(result1.getString(6), "LeTypeMateriel")); //TODO LE TYPE;
						contrat.ajouteMateriel(mat);
						
					}
					return contrat;




				}

				stmt.close(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (nomClasse.equalsIgnoreCase("LeTypeMateriel")) 
		{
			PreparedStatement stmt;
			try {
				stmt = con.prepareStatement("select * from type_materiel where Reference_Interne = ?");
				stmt.setString(1, id);
				stmt.executeQuery();
				ResultSet result = stmt.getResultSet();
				if(result.next()){
					TypeMateriel tMat = new TypeMateriel(id, result.getString(2),(Famille) ChargerDepuisBase(result.getString(3), "Famille"));
					return tMat;
				}
				stmt.close(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (nomClasse.equalsIgnoreCase("Famille")) 
		{
			PreparedStatement stmt;
			try {
				stmt = con.prepareStatement("select * from famille where Code_Famille = ?");
				stmt.setString(1, id);
				stmt.executeQuery();
				ResultSet result = stmt.getResultSet();
				if(result.next()){
					Famille f = new Famille(id, result.getString(2));
					return f;
				}
				stmt.close(); 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
