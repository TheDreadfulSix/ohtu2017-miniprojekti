/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author Joonas
 */
public class Menus {
    public static MenuBar setMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu mainMenu = new Menu("Reference");
        Menu bibMenu = new Menu(".bib");
        
        MenuItem newReference = new MenuItem("Add");
        
        newReference.setOnAction(e -> {
            CreateReference.display();
        });
        
        MenuItem newBib = new MenuItem("generate .bib file");
        
        newBib.setOnAction(e -> {
            GenerateBibFile.display();
        });
        
        mainMenu.getItems().addAll(newReference);
        bibMenu.getItems().addAll(newBib);
        menuBar.getMenus().addAll(mainMenu, bibMenu);
        return menuBar;
    }
}
