package tools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.FactoryRegistry;
import javazoom.jl.player.advanced.AdvancedPlayer;
import model.Son;

public class MusicPlayer {

	AdvancedPlayer advancedPlayer = null;

	public void play(Son son) throws FileNotFoundException, JavaLayerException {

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(son.getFile()));

		advancedPlayer = new AdvancedPlayer(bis, FactoryRegistry.systemRegistry().createAudioDevice());
		
		advancedPlayer.play(500);
		
		
	}
	
	public void killPlayer() {
		if(advancedPlayer != null) {
			advancedPlayer.stop();
			advancedPlayer = null;
		}
	}
}
