package miniprojekti.gui;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import miniprojekti.io.BibFileFormatter;
import miniprojekti.io.BibFileGenerator;
import miniprojekti.main.App;

/**
 * This class for generating a .bib file of the references added by the user.
 *
 * NOTE! Stuff is static because if not; total destruction.
 *
 * @author Viliina
 */
public class GenerateBibFile {

    private static Stage window;
    private static AlertGenerator alertG;
    private static BibFileFormatter formatter = new BibFileFormatter();
    private static BibFileGenerator generator= new BibFileGenerator(formatter);

    /**
     * Displays the window for choosing filename and generating .bib file.
     */
    public static void display() {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Generate .bib file");

        VBox layout = new VBox();

        layout.getChildren().add(setLayout(window));
        layout.setPadding(new Insets(10, 10, 10, 10));
        Scene scene = new Scene(layout, 450, 200);
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
    private static GridPane setLayout(Stage stage) {
        int y = 1;
        GridPane layout = new GridPane();

        Label filename = new Label("Filename");
        TextField file = new TextField();
        GridPane.setConstraints(filename, 0, y);
        GridPane.setConstraints(file, 1, y++);
        
        Label path = new Label("Path \n (If left empty, the path is set \n to the project root directory.)");
        TextField pathname = new TextField();
        GridPane.setConstraints(path, 0, y);
        GridPane.setConstraints(pathname, 1, y++);
        
        //Possible way to do: ask for name in the textfield, and then path with directoryChoosere.
        /*Label pathname = new Label("Application folder");
        Button pathButton = new Button("Choose save folder");
            pathButton.setOnAction(e -> {
                DirectoryChooser dirChooser = new DirectoryChooser();
                dirChooser.setTitle("Choose path for the references");
                File file1 = dirChooser.showDialog(stage);
            if (file1 != null) {
                
            }
        });
        
        GridPane.setConstraints(pathButton, 0, y);
        GridPane.setConstraints(pathname, 1, y++);*/
        
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
        alertG = new AlertGenerator();
        if (filename.isEmpty()) {
            alertG.alert("Error", "Please enter a filename");
            return;
        }

        if (App.getLogic().getList().size() == 0) {
            alertG.alert("Error", "You must provide some references first");
            return;
        }
        if (path.isEmpty()) {
            path = System.getProperty("user.dir");
            path += "/";
        } else if (path.charAt(path.length() - 1) != '/') {
            path += "/";
            if (pathInvalid(path, filename)) {
                alertG.alert("Error", "The path is invalid");
                return;
            }
        }
        
        generator.createFile(path, filename, App.getLogic().getAllReferences());
        App.getGUI().setScene();
        window.close();
    }

    private static boolean pathInvalid(String path, String filename) {
        if (Files.exists(Paths.get(path + filename))) {
            return false;
        }
        return true;
    }

}
