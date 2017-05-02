/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.gui;

import java.util.HashMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import miniprojekti.domain.Article;
import miniprojekti.domain.Book;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;
import miniprojekti.domain.Inproceedings;
import miniprojekti.domain.Reference;
import miniprojekti.main.App;

/**
 * A class for creating the window for editing a reference.
 *
 * @author Viliina
 */
public class EditReference {

    private static Stage window;
    private Reference ref;
    private InputValidator validator;

    public void display(Reference ref) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Reference");
        this.ref = ref;
        this.validator = new InputValidator(false);

        setScene(0);
    }

    /**
     * Sets the window's layout.
     *
     * @return window's layout.
     */
    private void setScene(int selected) {
        int y = 1;
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        HashMap<FieldName, TextField> input = new HashMap<>();

        Label source = new Label("Source:");
        GridPane.setConstraints(source, 0, y);

        TextField setSource = new TextField(ref.getClass().getName());
        GridPane.setConstraints(setSource, 1, y++);
        setSource.setEditable(false);

        Label required = new Label("Required fields");
        required.getStyleClass().add("header");
        GridPane.setConstraints(required, 0, y++);

        Label citkey = new Label("Citation key");
        citkey.getStyleClass().add("header");
        GridPane.setConstraints(citkey, 0, y);

        TextField citation = new TextField(ref.getCitationKey());
        GridPane.setConstraints(citation, 1, y++);
        citation.setEditable(false);

        for (FieldName fn : ref.getRequiredFields()) {
            y = createInputFields(fn, y, layout, input);
        }

        Label alternative = new Label("Alternative fields");
        if (!ref.getAlternativeFields().isEmpty()) {
            alternative.setText("No Alternative fields");
        }
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

        Button close = new Button("Close");
        GridPane.setConstraints(close, 0, y);
        close.setOnAction(e -> window.close());

        Button edit = new Button("Edit");
        GridPane.setConstraints(edit, 1, y);
        edit.setOnAction(e -> {
            if(validator.validateInput(input, citation, ref)) {
                App.getLogic().edit(ref);
            }
            validator.getAlertGenerator().alert("Confirmation", "Reference has been saved.");
            App.getGUI().setScene();
            window.close();
            App.getGUI().setScene();
        });

        layout.getChildren().addAll(source, setSource, close, edit, optional, alternative, required, citkey, citation);
        layout.setVgap(8);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(layout, 400, y * 35);
        scene.getStylesheets().add("style.css");
        window.setResizable(false);
        window.setScene(scene);
        window.show();
    }

    private int createInputFields(FieldName fn, int y, GridPane layout, HashMap<FieldName, TextField> input) {
        Label label = new Label(fn.name());
        TextField text = new TextField();
        if (ref.getFieldMap().get(fn) != null) {
            text.setText(ref.getFieldMap().get(fn).getValue());
        }
        GridPane.setConstraints(label, 0, y);
        GridPane.setConstraints(text, 1, y++);
        layout.getChildren().addAll(label, text);
        input.put(fn, text);
        return y;
    }

}
