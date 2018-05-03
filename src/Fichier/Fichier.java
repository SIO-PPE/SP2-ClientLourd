/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fichier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.IOException;

/**
 *
 * @author sio
 */
public class Fichier {

    private BufferedWriter fW;
    private BufferedReader fR;
    private char mode;

    public Fichier(){
        
    }
    public void ouvrir(String nomDuFichier, char mode)  {
        this.mode = mode;
        File f = new File(nomDuFichier);
        if (mode == 'R' || mode == 'L') {
            try {
				fR = new BufferedReader(new FileReader(f));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else if (mode == 'W' || mode == 'e') {
            try {
				fW = new BufferedWriter(new FileWriter(f));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    public String lire() throws IOException{
        return fR.readLine();
    }
        
    public void lireTout() throws IOException{
        String ligne; 
       while((ligne = fR.readLine()) != null){
           System.out.println(ligne);
       }
    }
    
    public void ecrire(int tmp) throws IOException{
        String chaine = String.valueOf(tmp);
        if(chaine != null){
            fW.write(chaine, 0, chaine.length());
            fW.newLine();
        }
    }
    
        public void ecrire(String chaine) {
            
        if(chaine != null){
            try {
				fW.write(chaine, 0, chaine.length());

	            fW.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    public void fermer() {
        if(mode == 'R'){
            try {
				fR.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else
			try {
				fW.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}
