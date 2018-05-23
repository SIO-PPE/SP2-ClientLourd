package ie;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ppeclientlourd.Main;

public class ZDialog extends JDialog implements Runnable {
	public class ZDialogInfo

	{
		private String nom;

		public ZDialogInfo(){}
		public ZDialogInfo(String nom){
			this.nom = nom;
		}
		
		public String getNom(){
			return nom;
		}

	}

	private ZDialogInfo zInfo = new ZDialogInfo();
	private boolean sendData;
	private JLabel nomLabel, icon;
	private JTextField nom;

	public ZDialog(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setSize(500, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
		
	}

	public ZDialogInfo getZDialog(){
		return zInfo;
	}
	public ZDialogInfo showZDialog(){
		this.sendData = false;
		this.setVisible(true);      
		return this.zInfo;      
	}
	
	public void valideForm() {
		zInfo = new ZDialogInfo(nom.getText());
		Main.activeClient = Main.gestion.getClient(nom.getText());
		if(Main.activeClient != null) {
			setVisible(false);
			Fenetre fen = new Fenetre();
		}else {
			JOptionPane jop3 = new JOptionPane();
        	jop3.showMessageDialog(null, "Identifiant introuvable", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void initComponent(){
		//Ic�ne
	//	icon = new JLabel(new ImageIcon("../images/logo.png"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		//panIcon.add(icon);

		//Le nom
		JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(300, 75));
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(100, 25));
		panNom.setBorder(BorderFactory.createTitledBorder("Accès dossier client"));
		nomLabel = new JLabel("Saisir l'identifiant du Client :");
		panNom.add(nomLabel);
		panNom.add(nom);

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panNom);

		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
		nom.addKeyListener(new ClavierListener(this));
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {        

				valideForm();
				
			}


		});

		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				System.exit(EXIT_ON_CLOSE);
			}      
		});

		control.add(okBouton);
		control.add(cancelBouton);

		this.getContentPane().add(panIcon, BorderLayout.NORTH);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}  
	
	class ClavierListener implements KeyListener{
		private ZDialog z;
		public ClavierListener(ZDialog z) {
			this.z =z;
		}

	    public void keyPressed(KeyEvent event) {
	    	if(event.getKeyCode() == KeyEvent.VK_ENTER) 
	    		z.valideForm();
	    }


	    public void keyReleased(KeyEvent event) {
	    }


	    public void keyTyped(KeyEvent event) {


	    }  
	}

	@Override
	public void run() {
		showZDialog(); 
		
	}
}