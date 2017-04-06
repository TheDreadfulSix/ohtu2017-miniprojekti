/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.gui;

import miniprojekti.domain.Article;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import miniprojekti.main.App;

/**
 *
 * @author Joonas
 */
public class GUI {
    private Scene scene;
    
    public GUI() {
        setScene();
    }
    
    /**
     * Asettaa ikkunaan uuden näkymän.
     */
    public void setScene() {
        VBox layout = new VBox();
        VBox box = new VBox();
        List<Article> list = App.getLogic().getList();
        for(Article a : list){
            box.getChildren().add(new Label(a.toString()));
        }
        box.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(Menus.setMenuBar(), box);
        scene = new Scene(layout, 1240, 720);
        scene.getStylesheets().add("style.css");
        App.setScene(scene);
    }
    
    /**
     * Palauttaa tämänhetkisen näkymän. 
     * @return Näkymä.
     */
    public Scene getScene() {
        return this.scene;
    }
    
    private static TableView<Article> generateTable(ObservableList<Article> list){
        
        
        
        return null;
    }
}
