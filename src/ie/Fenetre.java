package ie;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Fichier.Fichier;
import ppeclientlourd.Main;

//157856
public class Fenetre extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 
	private JPanel content = new JPanel();
	

	public Fenetre(){
		
		this.setTitle("CashCash");
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//JPanel pan = new JPanel();
		

		//pan.setBackground(Color.WHITE);       
		//this.setResizable(false);
		
		//this.setAlwaysOnTop(true);
		
		
		//Panel panel = new Panel();
		
		/*
		 * HEADER
		 * 
		 */
		JPanel header = new JPanel();
		header.setPreferredSize(new Dimension(200,120));

		

		JLabel label = new JLabel();


		header.add(new JLabel("ID Client : "+ Main.activeClient.getNumClient()) );
		header.add(new JLabel(" "));
		header.add(new JLabel(" "));
		header.add(new JLabel(" "));

		header.add(new JLabel("<html>Raison Sociale : "+ Main.activeClient.getRaisonSociale()
							+ "<br>Siren : "+ Main.activeClient.getSiren() 
							+ "<br>Code APE : "+ Main.activeClient.getCodeApe()
							+ "<br>Adresse : "+ Main.activeClient.getAdresse()
							+ "<br>Téléphone : "+ Main.activeClient.getTelClient()
							+ "<br>Email : "+ Main.activeClient.getEmail()
							+ "</html>") );
		
		
		this.getContentPane().add(header,BorderLayout.NORTH);
		
		
		/*
		 * 
		 * 
		 */
		
		
		
		//contrats = DATABASE.loadContrat(Main.nom);
		
		
		/*for(Contrat c : contrats){
			 JPanel panNom = new JPanel();
			    panNom.setBackground(Color.white);
			    panNom.setPreferredSize(new Dimension(500, 100));
				panNom.setBorder(BorderFactory.createTitledBorder(c.id +" "));
				
				Object[][] data = {
						{c.MAT, c.APM}
				};
				String  title[] = {"Charge restante MAT", "Charge restante APM"};
				JTable tableau = new JTable(data, title);
				tableau.setEnabled(false);
				
				
				tableau.setEnabled(false);
				tableau.getTableHeader().setReorderingAllowed(false);
				tableau.getTableHeader().setResizingAllowed(false);
				panNom.add(new JScrollPane(tableau));
				
				content.add(panNom);
		

		}*/
		 
				
		
		//this.getContentPane().add(center, BorderLayout.CENTER);
		
		content.setBackground(Color.white);
		//content.setLayout(cl);
		JTextField showFolderSave = new JTextField();
		showFolderSave.setPreferredSize(new Dimension(200, 25));
		JLabel labelFolderSave = new JLabel("Destination XML");
		
		JButton selectFolder = new JButton("...");
		selectFolder.addActionListener(new ActionListener(){

	        public void actionPerformed(ActionEvent arg0) {
	            JFileChooser dialogue = new JFileChooser();

	            dialogue.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	            // affichage
	            dialogue.showOpenDialog(null);
	            // récupération du fichier sélectionné
	           
	            System.out.println("Dossier save XML choisi : " + dialogue.getSelectedFile());
	            showFolderSave.setText(""+dialogue.getSelectedFile());
	        }         

	      });  
		
		content.add(labelFolderSave);
		content.add(showFolderSave);
		content.add(selectFolder);
		

		this.getContentPane().add(content, BorderLayout.CENTER);
		
		JPanel panBouton = new JPanel();
		panBouton.setBackground(Color.GRAY);
		JButton genererXML = new JButton("Génêrer fichier XML");
		genererXML.addActionListener(new ActionListener(){

	        public void actionPerformed(ActionEvent arg0) {
	            if(showFolderSave.getText().isEmpty()) {
	            	JOptionPane jop3 = new JOptionPane();
	            	jop3.showMessageDialog(null, "Merci de saisir une destination", "Erreur", JOptionPane.ERROR_MESSAGE);
	            }else {
	            	String xml = Main.gestion.XmlClient(Main.activeClient);


	        		System.out.println(xml);

	        		 Fichier f = new Fichier();
						    try {
						    	f.ouvrir(showFolderSave.getText() + "\\materielClient"+Main.activeClient.getNumClient()+".xml", 'W');
								f.ecrire(xml);
								f.fermer();
							} catch (FileNotFoundException e) {
								JOptionPane jop3 = new JOptionPane();
				            	jop3.showMessageDialog(null, "Choix de destination de dossier invalide", "Erreur", JOptionPane.ERROR_MESSAGE);
								
							} catch (IOException e) {
								// TODO Auto-generated catch block
							
								e.printStackTrace();
							}
						
	        	 
	        	        

	        	        Main.gestion.XmlClientValide(xml);
	            }
	        }         

	      });  
		panBouton.add(genererXML);
		
		this.getContentPane().add(panBouton, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}

	/*public class Panel extends JPanel {
	
		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
			
			Graphics2D g2d = (Graphics2D)g; 
			GradientPaint gp = new GradientPaint(0, 0, Color.RED, 30, 30, Color.cyan, true);                
			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());  

			
				//IMAGE
				//Image img = ImageIO.read(new File("../images/logo.png"));
			//	g.drawImage(img, 10, 10, this);
				//DATE
				//g.setFont(new Font("Times New Roman", Font.BOLD, 20));
				g.setColor(Color.black);
				//g.drawString("DATE", 400, 60);
				//g.setFont(new Font("Times New Roman", Font.PLAIN, 16));
				SimpleDateFormat shortDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			//	g.drawString("Coucou", 465, 60);
				//Nom Soustraitant
				//g.setFont(new Font("Times New Roman", Font.PLAIN, 14));
				g.drawString("ID Client : "+ Main.activeClient.getNumClient(), 200, 20);
		
				g.drawString("Raison Sociale : "+ Main.activeClient.getRaisonSociale(), 10, 40);
				
				g.drawString("Siren : "+ Main.activeClient.getSiren(), 10, 60);
				g.drawString("Code APE : "+ Main.activeClient.getCodeApe(), 10, 80);
				g.drawString("Adresse : "+ Main.activeClient.getAdresse(), 300, 40);
				g.drawString("Téléphone : "+ Main.activeClient.getTelClient(), 300, 60);
				g.drawString("Email : "+ Main.activeClient.getEmail(), 300, 80);
				g.setFont(new Font("Times New Roman", Font.PLAIN, 14));
			//	g.drawString(": " + Main.sousTraitantName, 145, 100);
				// Contrat
			
		
 //    nomLabel = new JLabel("Saisir un nom :");
				  //  panNom.add(nomLabel);
				  //  panNom.add(nom);
        
		}    
	}*/
}
