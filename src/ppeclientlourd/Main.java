package ppeclientlourd;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		PersistanceSQL sql = new PersistanceSQL();
		Client client = (Client) sql.ChargerDepuisBase("157856", "Client");
		System.out.println(client.toString());
		System.out.println(client.getLeContrat().toString());

	
	}
 
}
