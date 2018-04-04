package view;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;

import javazoom.jl.decoder.JavaLayerException;
import model.ListeDeSons;
import model.Son;
import tools.MusicPlayer;

public class FenetrePrincipale {
	
	private HashMap<String, JButton> buttonMap = new HashMap<String, JButton>();
	private ListeDeSons listeDesSon = new ListeDeSons();
	
	private JPopupMenu popupMenu;
	private JFrame maFenetre;
	private Son son;
	private MusicPlayer mp;
	private Thread playerThread;
	
	public void affiche(int nombreDeLignes, int nombreDeColonnes) {
		
		maFenetre = new JFrame("SR SoundBoard");
		maFenetre.setLayout(new BorderLayout());
		
			// Création du Menu en haut de la fenetre
			JMenuBar barreDeMenu = new JMenuBar();
				JMenu option = new JMenu("Option");
			barreDeMenu.add(option);
			
		// Ajout de la barre de menu a la fenetre
		maFenetre.setJMenuBar(barreDeMenu);
		
			JPanel buttonGrid = new JPanel();
			buttonGrid.setLayout(new GridLayout(nombreDeLignes, nombreDeColonnes));
				
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
				buttonGrid.add(buttonMap.get(buttonKey));

			}
		maFenetre.add(buttonGrid, BorderLayout.CENTER);
		
			JPanel stopGrid = new JPanel();
			stopGrid.setLayout(new BorderLayout(1, 1));
			
			JButton stop = new JButton("STOP");
			stop.addActionListener(new StopListener());
			
			stopGrid.add(stop);
			
		maFenetre.add(stopGrid, BorderLayout.SOUTH);
		
		maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		maFenetre.pack();
		maFenetre.setVisible(true);
	}
	
	
	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("test");
			JButton button = (JButton) e.getSource();
			if (!button.getText().isEmpty()) {
				son = listeDesSon.getSonByName(button.getText());
				mp = new MusicPlayer();
				
				playerThread = new Thread() {
					public void run() {
						try {
							mp.play(son);
						} catch (JavaLayerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
				playerThread.start();
			}
			
		}
		
	}
	
	public class StopListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("stop");
			//Deprecated
			//playerThread.stop();
			// https://www.youtube.com/watch?v=Rk_4jCmAt48
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
