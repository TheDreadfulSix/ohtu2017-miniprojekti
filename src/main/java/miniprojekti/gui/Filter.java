/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import miniprojekti.main.App;

/**
 *
 * @author mxsampsa
 */
public class Filter {

    public static GridPane createFilterBar() {
        GridPane filterPane = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(60);//this needs to be edited if more content(text, elements etc.) are added to this pane.
        filterPane.getColumnConstraints().addAll(col1);

        Label dummyLabel = new Label("");
        dummyLabel.setPadding(new Insets(10, 10, 10, 10));
        filterPane.add(dummyLabel, 0, 0);

        Label filterLabel = new Label("Filter by keyword: ");
        filterLabel.setMinWidth(Control.USE_PREF_SIZE);
        filterPane.add(filterLabel, 1, 0);

        TextField filterInput = new TextField();
        filterInput.setMinWidth(Control.USE_PREF_SIZE);
        filterPane.add(filterInput, 2, 0);
        
        filterInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals("")) {
                App.getLogic().emptyFilter();
                //Filter with filterInput buttons String. Filtering happens in referenceDAO(when it gets merged).
                //TODO Make the Map for database here if possible. :D Did not do that. Fix it maybe?
                App.getLogic().filter(newValue);
                //App.getGUI().setScene();
                App.getGUI().refreshReferenceList();

            } else {
                App.getLogic().emptyFilter();
                App.getLogic().filter(newValue);
                // Filter references so that all the references are shown.
                
                //App.getGUI().setScene();
                App.getGUI().refreshReferenceList();
            }
        });

        return filterPane;
    }
}
