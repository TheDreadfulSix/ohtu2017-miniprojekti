package miniprojekti.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class ActionMenu extends Menu{
    public ActionMenu() {
        super("Actions");

        MenuItem addRef= new MenuItem("Add Reference");
        MenuItem genFile = new MenuItem("Generate BibTeX File"); 

        addRef.setOnAction(e -> {
            CreateReference.display();
        });

        genFile.setOnAction(e -> {
            GenerateBibFile.display();
        });

        this.getItems().addAll(addRef, genFile);
    }
}
