/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.gui;

import java.util.ArrayList;
import java.util.Collection;
import miniprojekti.domain.Reference;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
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
    private AlertGenerator alertG;

    public GUI() {
        setScene();
        alertG = new AlertGenerator();
    }

    /**
     * Sets a new scene.
     */
    public void setScene() {
        VBox layout = new VBox();
        layout.getStyleClass().add("referenceView");
        ScrollPane sp = new ScrollPane(createVPane());
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
        
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(new ActionMenu());
        menuBar.getStyleClass().add("menuBar");
        
        layout.getChildren().addAll(menuBar, Filter.createFilterBar(), sp);
        scene = new Scene(layout, 1240, 720);
        scene.getStylesheets().add("style.css");
        
        sp.requestFocus();
        App.setScene(scene);
    }

    /**
     * Returns the current scene.
     * @return scene.
     */
    public Scene getScene() {
        return this.scene;
    }
    
    public VBox createVPane() {
        VBox referenceView = new VBox();
        referenceView.setPadding(new Insets(5));
        referenceView.setSpacing(8);
        referenceView.getStyleClass().add("referenceView");
        
        ObservableList<Reference> oList = FXCollections.observableArrayList(App.getLogic().getList());

        Text title = new Text("References");
        title.setStyle("-fx-fill: linear-gradient(from 0% 0% to 200% 100%, repeat, #55AABB 0%, #AA0000 50%);-fx-font-size:30px;");
        referenceView.getChildren().add(title);
        
        for (Reference ref : oList) {
            HBox referenceItem = new HBox();
            referenceItem.setPadding(new Insets(5, 5, 5, 5));
            referenceItem.setSpacing(10);
            referenceItem.getStyleClass().add("referenceItem");
            
            Text referenceText = new Text(ref.toString());
            Button deleteButton = new Button("Delete");
            Button editButton = new Button("Edit");

            deleteButton.setOnAction(e -> {
                if(alertG.alertWithChoice("Confirm deletion", "Are you sure?")) {
                    App.getLogic().delete(ref);
                    setScene();
                }
            });

            editButton.setOnAction((ActionEvent event) -> {
                EditReference edit = new EditReference();
                edit.display(ref);
            });

            Pane spacer = new Pane();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            spacer.setMinSize(10, 1);
            

            referenceItem.getChildren().addAll(referenceText, spacer, deleteButton, editButton);
            referenceView.getChildren().add(referenceItem);
        }
        
        return referenceView;
    }
}
