package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;

import javazoom.jl.decoder.JavaLayerException;
import model.ListeDeSons;
import model.Son;
import tools.MusicPlayer;

public class FenetrePrincipale {
	
	HashMap<String, JButton> buttonMap = new HashMap<String, JButton>();
	ListeDeSons listeDesSon = new ListeDeSons();
	
	JPopupMenu popupMenu;
	JFrame maFenetre;
	Son son;
	
	public void affiche(int nombreDeLignes, int nombreDeColonnes) {
		
		maFenetre = new JFrame("Fenetre");
		maFenetre.setLayout(new GridLayout(nombreDeLignes, nombreDeColonnes));
		
			// Création du Menu en haut de la fenetre
			JMenuBar barreDeMenu = new JMenuBar();
				JMenu option = new JMenu("Option");
			barreDeMenu.add(option);
			
		// Ajout de la barre de menu a la fenetre
		maFenetre.setJMenuBar(barreDeMenu);
		
		
			
			for (int i = 0; i < nombreDeLignes * nombreDeColonnes; i++) {
				String buttonKey = "button" + String.valueOf(i);
				JButton button = new JButton();
				
				popupMenu = new JPopupMenu();
					JMenuItem chooseFile = new JMenuItem("Choose File");
					chooseFile.addActionListener(new ChooseFileListener());
				popupMenu.add(chooseFile);
				
				button.setComponentPopupMenu(popupMenu);
				button.addActionListener(new ButtonListener());
				buttonMap.put(buttonKey, button);
				maFenetre.add(buttonMap.get(buttonKey));
				
			}
		
		maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maFenetre.pack();
		maFenetre.setVisible(true);
	}
	
	
	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				System.out.println("test");
				JButton button = (JButton) e.getSource();
				son = listeDesSon.getSonByName(button.getText());
				MusicPlayer mp = new MusicPlayer();
				
				mp.play(son);
				
				
			} catch (FileNotFoundException | JavaLayerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	
	public class ChooseFileListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser fc = new JFileChooser();
			
			FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("MP3",	"mp3");
			fc.setFileFilter(fileFilter);
			fc.setAcceptAllFileFilterUsed(false);
			int buttonPressed = fc.showOpenDialog(maFenetre);
			
			if (buttonPressed == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				son = new Son(file);
				listeDesSon.addSon(son);
				
				JMenuItem menuItem = (JMenuItem) e.getSource();
				JPopupMenu popupMenu = (JPopupMenu) menuItem.getParent();
				System.out.println(son.getName());

				
				for (String s : buttonMap.keySet()) {
					if (buttonMap.get(s).getComponentPopupMenu().equals(popupMenu)) {
						buttonMap.get(s).setText(son.getName());
						break;
					}
				}
			}
			maFenetre.revalidate();
			maFenetre.pack();
			maFenetre.repaint();
		}
		
	}
	
	

}
