/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SR
 */
public class Config {

    private final static String filePath = System.getenv().get("HOMEPATH") + "\\Documents\\SRSoundboard\\config.properties";
    public static Properties prop = new Properties();

    public void saveConfig(String key, String value) {
        try {
            prop.setProperty(key, value);
            prop.store(new FileOutputStream(filePath), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getConfig(String string) {
        String value = null;
        try {
            prop.load(new FileInputStream(filePath));
            value = prop.getProperty(string);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    public Config() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                //Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
                String folderPath = filePath.substring(0, filePath.lastIndexOf("\\"));
                File folder = new File(folderPath);
                folder.mkdirs();
                try {
                    file.createNewFile();
                } catch (IOException ex1) {
                    Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        saveConfig("Columns", "3");
        saveConfig("Rows", "10");
        saveConfig("DeviceOutput", "Item 4");
        }
    }
}
