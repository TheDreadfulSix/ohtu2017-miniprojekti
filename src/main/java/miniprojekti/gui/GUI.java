/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.gui;

import miniprojekti.domain.Article;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;
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
//        for(Article a : list){
//            box.getChildren().add(new Label(a.toString()));
//        }
        TableView table = generateTable(list);
        box.getChildren().add(table);
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
    
    private static TableView<ObservableMap> generateTable(List<Article> list){
        TableView<ObservableMap> table = new TableView();
        
        ObservableList<ObservableMap> rowMaps = FXCollections.observableArrayList();
        
        for(Article article : list){
            rowMaps.add(article.getFieldMap());
        }
        
        table.setItems(rowMaps);
        
        TableColumn authorColumn = new TableColumn("Author");
        authorColumn.setCellValueFactory(new ObservableMapValueFactory<Field>(FieldName.AUTHOR));
        
        TableColumn editionColumn = new TableColumn("Edition");
        editionColumn.setCellValueFactory(new ObservableMapValueFactory<Field>(FieldName.EDITION));
        
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
        
        table.getColumns().addAll(authorColumn,editionColumn,journalColumn,monthColumn,
                noteColumn,numberColumn,pagesColumn,titleColumn,volumeColumn,yearColumn);
        return table;
    }
}
