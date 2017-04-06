/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.gui;

import miniprojekti.domain.Article;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import miniprojekti.main.App;

/**
 *
 * @author Joonas
 */
public class CreateReference {
    
    private static Stage window;
    
    public static void display(){
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("New maze");

        VBox layout = new VBox();

        layout.getChildren().add(setLayout());
        layout.setPadding(new Insets(10,10,10,10));
        Scene scene = new Scene(layout,400,500);
        scene.getStylesheets().add("style.css");
        window.setResizable(true);
        window.setScene(scene);
        window.show();
    }
    
    /**
     * Asettaa ikkunan ulkoasun.
     * @return Ikkunan ulkoasu.
     */
    private static GridPane setLayout() {
        int y = 1;
        GridPane layout = new GridPane();
        HashMap<FieldName,TextField> input = new HashMap<>();
        Label source = new Label("Source:");
       
        GridPane.setConstraints(source, 0, y);
        
        ChoiceBox setSource = new ChoiceBox(FXCollections.observableArrayList("article",
            "book","booklet","inbook","incollection","inproceedings","manual","thesis",
            "misc","proceedings","tech report","unpublished"));
        GridPane.setConstraints(setSource, 1, y++);
        
        //This part of the code is needed when other reference types are added!
//        setSource.getSelectionModel().selectedIndexProperty().addListener(new 
//        ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue ov, Number value, Number new_value) {
//                
//            }
//        });
        
        Label required = new Label("Required fields");
        required.getStyleClass().add("header");
        GridPane.setConstraints(required, 0, y++);
        
        for(FieldName fn: Article.getRequiredFields()){
            Label label = new Label(fn.name());
            TextField text = new TextField();
            GridPane.setConstraints(label, 0, y);
            GridPane.setConstraints(text, 1, y++);
            layout.getChildren().addAll(label,text);
            input.put(fn, text);
        }
        
        Label optional = new Label("Optional fields");
        optional.getStyleClass().add("header");
        GridPane.setConstraints(optional, 0, y++);
        
        for(FieldName fn: Article.getOptionalFields()){
            Label label = new Label(fn.name());
            TextField text = new TextField();
            GridPane.setConstraints(label, 0, y);
            GridPane.setConstraints(text, 1, y++);
            layout.getChildren().addAll(label,text);
            input.put(fn, text);
        }
        
        Button close = new Button("Close");
        GridPane.setConstraints(close, 0, y);
        close.setOnAction(e -> window.close());
        
        Button create = new Button("Create");
        GridPane.setConstraints(create, 1, y);
        create.setOnAction(e -> validateInput(layout, setSource, input));
        
        layout.getChildren().addAll(source,setSource,close,create,optional,required);
        layout.setVgap(8);
        layout.setHgap(10);
        layout.setPadding(new Insets(10,10,10,10));
        return layout;
    }
    
    /**
     * Palauttaa totuusarvon siitä, onko annettu syöte kokonaisluku.
     * @param input Käyttäjän syöte.
     * @return Totuusarvo siitä, onko syöte kokonaisluku.
     */
    private static boolean isInt(TextField input) {
        try{
            Integer.parseInt(input.getText());
            return true;
        }catch(NumberFormatException e){
            input.setStyle("-fx-background-color: #FF0000;");
            return false;
        }
    }
    
    private static void validateInput(GridPane layout, ChoiceBox source, HashMap<FieldName, TextField> input) {
        HashMap<FieldName, Field> fields = new HashMap<>();
        for(FieldName fn : input.keySet()){
            if(!input.get(fn).getText().isEmpty())
                fields.put(fn, new Field(fn,input.get(fn).getText()));
        }
        Article article = new Article("",fields);
        App.getLogic().add(article);
        App.getGUI().setScene();
        window.close();
    }
}
