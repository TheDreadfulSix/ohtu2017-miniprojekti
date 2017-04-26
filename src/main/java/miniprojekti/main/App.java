package miniprojekti.main;

import javafx.scene.Scene;
import miniprojekti.gui.GUI;
import miniprojekti.logic.Logic;

/**
 *
 * @author Joonas
 */
public class App {

    private static GUI gui;
    private static Logic logic;

    public static GUI getGUI() {
        if (App.gui == null) {
            gui = new GUI();
        }
        return gui;
    }

    public static Logic getLogic() {
        if (App.logic == null) {
            logic = new Logic();
        }
        return logic;
    }

    public static void setGUI(GUI newGui) {
        gui = newGui;
    }

    public static void setLogic(Logic newLogic) {
        logic = newLogic;
    }

    public static void close() {
        Main.close();
    }

    public static void setScene(Scene newScene) {
        Main.setScene(newScene);
    }
}
