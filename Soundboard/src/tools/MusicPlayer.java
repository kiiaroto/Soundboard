/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import model.Son;

/**
 *
 * @author SR
 */
public class MusicPlayer {

    private FileInputStream fis;
    private BufferedInputStream bis;
    private AdvancedPlayer advancedPlayer = null;
    private long pauseLocation;
    private long songTotalLength;
    Son son;

    public void play(Son son) throws FileNotFoundException, JavaLayerException {
        this.son = son;
        stop();
        fis = new FileInputStream(son.getFile());
        bis = new BufferedInputStream(fis);

        advancedPlayer = new AdvancedPlayer(bis);
        

        new Thread() {
            @Override
            public void run() {
                try {
                    advancedPlayer.play();
                } catch (JavaLayerException ex) {
                    Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();

    }
    
    public void resume() throws FileNotFoundException, JavaLayerException, IOException {
        
        stop();
        fis = new FileInputStream(son.getFile());
        bis = new BufferedInputStream(fis);
        
        advancedPlayer = new AdvancedPlayer(bis);
        
        songTotalLength = fis.available();
        
        fis.skip(songTotalLength - pauseLocation);

        new Thread() {
            @Override
            public void run() {
                try {
                    advancedPlayer.play();
                } catch (JavaLayerException ex) {
                    Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();

    }

    public void stop() {
        if (advancedPlayer != null) {
            advancedPlayer.close();
            advancedPlayer = null;
            pauseLocation = 0;
            songTotalLength = 0;
        }
    }
    
    public void pause() throws IOException {
        if (advancedPlayer != null) {
            // check the rest of the song which haven't been played yet
            pauseLocation = fis.available();
            
            advancedPlayer.close();
            advancedPlayer = null;
        }
    }
}
