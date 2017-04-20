/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.gui;

import java.util.Arrays;
import java.util.Collections;
import miniprojekti.domain.Article;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import miniprojekti.domain.Inproceedings;
import miniprojekti.domain.Reference;
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
        window.setTitle("New Reference");

        setScene(new Article(),0);
    }
    
    /**
     * Asettaa ikkunan ulkoasun.
     * @return Ikkunan ulkoasu.
     */
    private static void setScene(Reference ref, int selected) {
        int y = 1;
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));
        HashMap<FieldName,TextField> input = new HashMap<>();

        Label source = new Label("Source:");
        GridPane.setConstraints(source, 0, y);
        
        ChoiceBox setSource = new ChoiceBox(FXCollections.observableArrayList("article",
            "book","booklet","inbook","incollection","inproceedings","manual","thesis",
            "misc","proceedings","tech report","unpublished"));
        setSource.getSelectionModel().select(selected);
        GridPane.setConstraints(setSource, 1, y++);
        
        setSource.getSelectionModel().selectedIndexProperty().addListener(new 
        ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue ov, Number value, Number new_value) {
                System.out.println(new_value.intValue());
                switch(new_value.intValue()){
                    case 0: setScene(new Article(),0);
                        System.out.println("Article"); 
                        break;
                    case 1: setScene(new Article(),0);
                        break;
                    case 2: setScene(new Article(),0);
                        break;
                    case 3: setScene(new Article(),0);
                        break;
                    case 4: setScene(new Article(),0);
                        break;
                    case 5: setScene(new Inproceedings(),5);
                        break;
                }
            }
        });
        
        Label required = new Label("Required fields");
        required.getStyleClass().add("header");
        GridPane.setConstraints(required, 0, y++);
        
        Label citkey = new Label("Citation key");
        citkey.getStyleClass().add("header");
        GridPane.setConstraints(citkey, 0, y);
        
        TextField citation = new TextField();
        GridPane.setConstraints(citation, 1, y++);
        
        for(FieldName fn: ref.getRequiredFields()){
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
        
        for(FieldName fn: ref.getOptionalFields()){
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
        create.setOnAction(e -> validateInput(setSource, input, citation, ref));
        
        layout.getChildren().addAll(source,setSource,close,create,optional,required,citkey,citation);
        layout.setVgap(8);
        layout.setHgap(10);
        layout.setPadding(new Insets(10,10,10,10));
        
        Scene scene = new Scene(layout,400, y * 35);
        scene.getStylesheets().add("style.css");
        window.setResizable(false);
        window.setScene(scene);
        window.show();
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
    
    private static void validateInput(ChoiceBox source, HashMap<FieldName, TextField> input, TextField cit, Reference ref) {
        ObservableMap<FieldName, Field> fields = FXCollections.observableHashMap();
        for(FieldName fn : input.keySet()){
            if(!input.get(fn).getText().isEmpty()) {
                fields.put(fn, new Field(fn,input.get(fn).getText()));
            }else if(ref.getRequiredFields().contains(fn)){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Required field is empty!");

                alert.showAndWait();
                return;
            }
        }
        ref.setReference(cit.getText(),fields);
        App.getLogic().add(ref);
        App.getGUI().setScene();
        window.close();
    }
}
