/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.gui;

import java.util.HashMap;
import miniprojekti.domain.Reference;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;
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
        pane.setHgap(3);
        pane.setPadding(new Insets(10, 10, 10, 10));
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(80);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(10);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(10);
        pane.getColumnConstraints().addAll(col1,col2,col3);
        
        List<Reference> list = App.getLogic().getList();
        int lineCounter = 0;
        for (Reference ref : list) {
            lineCounter++;
            if(lineCounter > 0){
                Text referenceText = new Text(ref.toString());
                Button deleteButton = new Button("Delete");
                //Button editButton = new Button("Edit");
                
                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //TODO delete ref.
                    }
                });
                
                /*Button editButton = new Button("Edit");
                deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //TODO edit ref.
                    }
                });*/
                
                pane.add(referenceText, 0, lineCounter);
                //pane.add(editButton, 1, lineCounter);
                pane.add(deleteButton, 2, lineCounter); // (item, column, row)
            }
        }
        return pane;
    }
    /*private static TableView<ObservableMap> generateTable(List<Reference> list){
        TableView<HashMap> table = new TableView();
        table.setPrefSize(1100, 680);
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY); 
        //ObservableList<ObservableMap> rowMaps = FXCollections.observableArrayList();
        
        TableColumn referenceColumn = new TableColumn("Reference");
        referenceColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.8));
        referenceColumn.setCellValueFactory(new ObservableMapValueFactory<Field>(FieldName.AUTHOR));
        
        /*TableColumn editColumn = new TableColumn("Edit");
        editColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        authorColumn.setCellValueFactory(new ObservableMapValueFactory<Field>(FieldName.EDIT));
        
        TableColumn deleteColumn = new TableColumn("Delete");
        deleteColumn.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
        deleteColumn.setCellValueFactory(new ObservableMapValueFactory<Field>(FieldName.EDITION));
        
        table.getColumns().addAll(referenceColumn,deleteColumn); 
        
        HashMap<String, O>
        for(Reference ref : list){
            
        }
        
        table.setItems(rowMaps);
        
        
        
        TableColumn journalColumn = new TableColumn("Journal");
        journalColumn.setCellValueFactory(new ObservableMapValueFactory<Field>(FieldName.JOURNAL));
        
        TableColumn monthColumn = new TableColumn("Month");
        monthColumn.setCellValueFactory(new ObservableMapValueFactory<Field>(FieldName.MONTH));
        
        TableColumn noteColumn = new TableColumn("Note");
        noteColumn.setCellValueFactory(new ObservableMapValueFactory<Field>(FieldName.NOTE));
        
        TableColumn numberColumn = new TableColumn("Number");
        numberColumn.setCellValueFactory(new ObservableMapValueFactory<Field>(FieldName.NUMBER));
        
        TableColumn pagesColumn = new TableColumn("Pages");
        pagesColumn.setCellValueFactory(new ObservableMapValueFactory<Field>(FieldName.PAGES));
        
        TableColumn titleColumn = new TableColumn("Title");
        titleColumn.setCellValueFactory(new ObservableMapValueFactory<Field>(FieldName.TITLE));
        
        TableColumn volumeColumn = new TableColumn("Volume");
        volumeColumn.setCellValueFactory(new ObservableMapValueFactory<Field>(FieldName.VOLUME));
        
        TableColumn yearColumn = new TableColumn("Year");
        yearColumn.setCellValueFactory(new ObservableMapValueFactory<Field>(FieldName.YEAR));
        
            
        return table;
    }*/
    
    
}
