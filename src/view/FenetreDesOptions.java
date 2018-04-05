package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class FenetreDesOptions {
	
	JFrame maFenetre;
	
	public void affiche() {
		
		maFenetre = new JFrame("SR SoundBoard");
		maFenetre.setLayout(new BorderLayout());
		
		
		maFenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		maFenetre.pack();
		maFenetre.setVisible(true);
	}

}
