/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;

/**
 *
 * @author SR
 */
public class Son {

    private File file;
    private String shortcut;
    private String name;

    public String getNameAndShortcut() {
        return name + "\n" + "(" + shortcut + ")";
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Son(File file) {
        this.file = file;
        this.name = file.getPath().substring(file.getPath().lastIndexOf("\\") + 1, file.getPath().lastIndexOf("."));
    }
}
