package miniprojekti.gui;

import java.util.HashMap;
import javafx.collections.FXCollections;
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
import miniprojekti.domain.Article;
import miniprojekti.domain.FieldName;
import miniprojekti.io.IO;
import miniprojekti.main.App;

/**
 * This class for generating a .bib file of the references added by the user.
 *
 * @author Viliina
 */
public class GenerateBibFile {

    private static Stage window;

    /**
     * Displays the window for choosing filename and generating .bib file.
     */
    public static void display() {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Generate .bib file");

        VBox layout = new VBox();

        layout.getChildren().add(setLayout());
        layout.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(layout, 400, 200);
        scene.getStylesheets().add("style.css");
        window.setResizable(true);
        window.setScene(scene);
        window.show();
    }

    /**
     * Sets the layout for the window.
     *
     * @return returns the layout for the window
     */
    private static GridPane setLayout() {
        int y = 1;
        GridPane layout = new GridPane();

        Label filename = new Label("File name");
        TextField file = new TextField();
        GridPane.setConstraints(filename, 0, y);
        GridPane.setConstraints(file, 1, y++);

        Label path = new Label("Where do you want to save? \n (If left empty, the path is set \n to the project root directory.)");
        TextField pathname = new TextField();
        GridPane.setConstraints(path, 0, y);
        GridPane.setConstraints(pathname, 1, y++);

        Button close = new Button("Close");
        GridPane.setConstraints(close, 0, y);
        close.setOnAction(e -> window.close());

        Button generate = new Button("Generate");
        GridPane.setConstraints(generate, 1, y);
        generate.setOnAction(e -> generate(file.getText(), pathname.getText()));

        layout.getChildren().addAll(filename, file, path, pathname, close, generate);
        layout.setVgap(8);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        return layout;
    }

    /**
     * Calls the method in IO for writing a .bib file of all the references
     * currently in Logic's list. Checks if the list is empty first.
     *
     * @param filename The filename that the user has given for the .bib.
     * @param path The path to save to.
     */
    public static void generate(String filename, String path) {
        
        if (filename.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("How about a filename for your .bib file?");

            alert.showAndWait();
            return;
        }

        if (App.getLogic().getList().size() == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("You must provide some references first.");

            alert.showAndWait();
            return;
        }
        if (path.isEmpty()) {
            path = System.getProperty("user.dir");
            //TO DO check if path is valid. In which class?         
//        } else if () {
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("The path provided is invalid.");
//
//            alert.showAndWait();
//            return;
        }
        App.getIO().writeBibFile(filename, path, App.getLogic().getList());
        App.getGUI().setScene();
        window.close();
    }

}
