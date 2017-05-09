/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.gui;

import miniprojekti.domain.Article;
import miniprojekti.domain.FieldName;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import miniprojekti.domain.Book;
import miniprojekti.domain.Inproceedings;
import miniprojekti.domain.Reference;
import miniprojekti.main.App;

/**
 *
 * @author Joonas
 */
public class CreateReference {

    private static Stage window;
    private static InputValidator validator = new InputValidator(true);

    public static void display() {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("New Reference");

        setScene(new Article(), 0);
    }
    
    public static Stage getStage() {
        return window;
    }

    /**
     * Sets the window's layout.
     *
     * @return window's layout.
     */
    private static void setScene(Reference ref, int selected) {
        int y = 1;
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        HashMap<FieldName, TextField> input = new HashMap<>();

        Label source = new Label("Source:");
        GridPane.setConstraints(source, 0, y);

        ChoiceBox setSource = new ChoiceBox(FXCollections.observableArrayList("article",
                "book", "booklet", "inbook", "incollection", "inproceedings", "manual", "thesis",
                "misc", "proceedings", "tech report", "unpublished"));
        setSource.getSelectionModel().select(selected);
        GridPane.setConstraints(setSource, 1, y++);

        setSource.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue ov, Number value, Number new_value) {
                System.out.println(new_value.intValue());
                switch (new_value.intValue()) {
                    case 0:
                        setScene(new Article(), 0);
                        System.out.println("Article");
                        break;
                    case 1:
                        setScene(new Book(), 1);
                        break;
                    case 2:
                        setScene(new Article(), 0);
                        break;
                    case 3:
                        setScene(new Article(), 0);
                        break;
                    case 4:
                        setScene(new Article(), 0);
                        break;
                    case 5:
                        setScene(new Inproceedings(), 5);
                        break;
                    case 6:
                        setScene(new Article(), 0);
                        break;
                    case 7:
                        setScene(new Article(), 0);
                        break;
                    case 8:
                        setScene(new Article(), 0);
                        break;
                    case 9:
                        setScene(new Article(), 0);
                        break;
                    case 10:
                        setScene(new Article(), 0);
                        break;
                    case 11:
                        setScene(new Article(), 0);
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

        for (FieldName fn : ref.getRequiredFields()) {
            y = createInputFields(fn, y, layout, input);
        }

        Label alternative = new Label("Alternative fields");
        alternative.getStyleClass().add("header");
        GridPane.setConstraints(alternative, 0, y++);

        for (FieldName fn : ref.getAlternativeFields()) {
            y = createInputFields(fn, y, layout, input);
        }

        Label optional = new Label("Optional fields");
        optional.getStyleClass().add("header");
        GridPane.setConstraints(optional, 0, y++);

        for (FieldName fn : ref.getOptionalFields()) {
            y = createInputFields(fn, y, layout, input);
        }
        
        Label tags = new Label("Tags");
        tags.getStyleClass().add("header");
        GridPane.setConstraints(tags, 0, y);

        TextField tagwords = new TextField();
        GridPane.setConstraints(tagwords, 1, y++);
        
        Label taginfo = new Label("Tags are keywords separated by whitespace.");
        taginfo.getStyleClass().add("label");
        GridPane.setRowIndex(taginfo, y++);
        GridPane.setColumnSpan(taginfo, 2);

        Button close = new Button("Cancel");
        GridPane.setConstraints(close, 0, y);
        close.setOnAction(e -> window.close());

        Button create = new Button("Create");
        GridPane.setConstraints(create, 1, y++);
        create.setOnAction(e -> {
            if (validator.validateInput(input, citation, ref)) {
                if (validator.checkTagField(tagwords.getText())) {
                    ref.setTags(tagwords.getText());
                    App.getLogic().add(ref);
                    validator.getAlertGenerator().alert("Confirmation", "Reference has been saved.");
                    App.getGUI().setScene();
                    window.close();
                    App.getGUI().setScene();
                }
            }
        });

        layout.getChildren().addAll(source, setSource, close, create, optional, required, citkey, citation, tags, tagwords, taginfo);
        if (ref.getAlternativeFields().size() > 0) {
            layout.getChildren().add(alternative);
        }
        layout.setVgap(8);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout, 400, y * 35);
        scene.getStylesheets().add("style.css");
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }

    private static int createInputFields(FieldName fn, int y, GridPane layout, HashMap<FieldName, TextField> input) {
        Label label = new Label(fn.name());
        TextField text = new TextField();
        GridPane.setConstraints(label, 0, y);
        GridPane.setConstraints(text, 1, y++);
        layout.getChildren().addAll(label, text);
        input.put(fn, text);
        return y;
    }

}
