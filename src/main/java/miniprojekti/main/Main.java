package miniprojekti.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Tähän luokkaan ei pitäisi tarvita enää koskea. Sovelluksen voi sulkea ja
 * vaihtaa sceneä Application luokasta niin pysyy kaikki sovelluksen kutsut
 * siinä luokassa. Sen takia tässä ja Application luokassa on kaksi samaa
 * metodia.
 * @author Joonas Ilvonen
 */
public class Main extends Application{
    private static Stage window;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Reference converter");
        App.getGUI();
        window.show();
    }
    
    public static void close(){
        window.close();
    }
    
    public static void setScene(Scene newScene){
        window.setScene(newScene);
    }
}
