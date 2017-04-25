/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.gui;

import java.util.Optional;
import miniprojekti.domain.Reference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import miniprojekti.main.App;

/**
 *
 * @author Joonas
 */
public class GUI {
    private Scene scene;
    private GridPane pane;
    
    public GUI() {
        setScene();
    }
    
    /**
     * Asettaa ikkunaan uuden näkymän.
     */
    public void setScene() {
        VBox layout = new VBox();
        ScrollPane sp = new ScrollPane(createPane());
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
        
        layout.getChildren().addAll(Menus.setMenuBar(), sp);
        scene = new Scene(layout, 1240, 720);
        scene.getStylesheets().add("style.css");
        
        sp.requestFocus();
        App.setScene(scene);
    }
    
    /**
     * Palauttaa tämänhetkisen näkymän. 
     * @return Näkymä.
     */
    public Scene getScene() {
        return this.scene;
    }
    
    public GridPane createPane() {
        pane = new GridPane();
        
        ObservableList<Reference> oList = FXCollections.observableArrayList(App.getLogic().getList());
        int lineCounter = 0;
        for (Reference ref : oList) {
            lineCounter++;
            if(lineCounter > 0){
                GridPane innerPane = new GridPane();
                innerPane.setHgap(3);
                ColumnConstraints col1 = new ColumnConstraints();
                col1.setPercentWidth(90);
                ColumnConstraints col2 = new ColumnConstraints();
                col2.setPercentWidth(5);
                ColumnConstraints col3 = new ColumnConstraints();
                col3.setPercentWidth(5);
                innerPane.getColumnConstraints().addAll(col1,col2,col3);
                Text referenceText = new Text(ref.toString());
                Button deleteButton = new Button("Delete");
                Button editButton = new Button("Edit");
                
                deleteButton.setOnAction(e -> {
                    //TODO delete ref.
                });
                
                deleteButton.setOnAction((ActionEvent event) -> {
                    //TODO edit ref.
                });
                
                innerPane.add(referenceText, 0, 0);
                innerPane.add(editButton, 1, 0);
                innerPane.add(deleteButton, 2, 0);
                innerPane.getStyleClass().add("innerCell");
                
                pane.add(innerPane, 0, lineCounter);// (item, column, row)
                pane.getStyleClass().add("outterGrid");
            }
        }
        return pane;
    }
    
}
