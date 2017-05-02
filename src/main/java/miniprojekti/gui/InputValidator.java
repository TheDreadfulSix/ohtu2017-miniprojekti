/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.gui;

import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;
import miniprojekti.domain.Reference;
import miniprojekti.main.App;

/**
 * Class for checking the input of the User.
 *
 * @author Viliina
 */
public class InputValidator {

    private AlertGenerator alertG;
    private boolean newReference;

    public InputValidator(boolean newReference) {
        this.alertG = new AlertGenerator();
        this.newReference = newReference;
    }

    /**
     * Palauttaa totuusarvon siitä, onko annettu syöte kokonaisluku.
     *
     * @param input Käyttäjän syöte.
     * @return Totuusarvo siitä, onko syöte kokonaisluku.
     */
    private static boolean isInt(TextField input) {
        try {
            Integer.parseInt(input.getText());
            return true;
        } catch (NumberFormatException e) {
            input.setStyle("-fx-background-color: #FF0000;");
            return false;
        }
    }

    public boolean validateInput(HashMap<FieldName, TextField> input, TextField cit, Reference ref) {
        ObservableMap<FieldName, Field> fields = FXCollections.observableHashMap();
        for (FieldName fn : input.keySet()) {
            if (!input.get(fn).getText().isEmpty()) {
                //awesome ugly and way too long if thingy
                if (fn.equals(FieldName.YEAR) || fn.equals(FieldName.CHAPTER) || fn.equals(FieldName.NUMBER) || fn.equals(FieldName.VOLUME)) {
                    if (!isInt(input.get(fn))) {
                        alertG.alert("Error", "The fields in red should be in numerical format.");
                        return false;
                    }
                }
                fields.put(fn, new Field(fn, input.get(fn).getText()));
            } else if (ref.getRequiredFields().contains(fn)) {
                alertG.alert("Error", "Required fields missing.");
                return false;
            }
        }
        if (!ref.getAlternativeFields().isEmpty()) {
            int help = 0;
            for (FieldName fn : ref.getAlternativeFields()) {
                if (fields.containsKey(fn)) {
                    help++;
                }
            }
            if (help == 0) {
                alertG.alert("Error", "Required alternative field missing.");
                return false;
            } else if (help > 1) {
                alertG.alert("Error", "Too many alternative fields filled.");
                return false;
            }
        }
        if (newReference) {
            for (Reference reference : App.getLogic().getList()) {
                if (reference.getCitationKey().equals(cit.getText())) {
                    alertG.alert("Error", "Reference with selected citation key already exists.");
                    return false;
                }
            }
        }
        ref.setReference(cit.getText(), fields);
        return true;
    }

    public AlertGenerator getAlertGenerator() {
        return alertG;
    }

}
