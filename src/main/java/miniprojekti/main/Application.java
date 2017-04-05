
package miniprojekti.main;

import javafx.scene.Scene;
import miniprojekti.gui.GUI;
import miniprojekti.io.IO;
import miniprojekti.logic.Logic;

/**
 *
 * @author Joonas
 */
public class Application {
    
    
    private static GUI gui;
    private static IO io;
    private static Logic logic;
    
    public static GUI getGUI(){
        if(Application.gui == null)
            gui = new GUI();
        return gui;
    }
    
    public static IO getIO(){
        if(Application.io == null)
            io = new IO();
        return io;
    }
    
    public static Logic getLogic(){
        if(Application.logic == null)
            logic = new Logic();
        return logic;
    }
    
    public static void setGUI(GUI newGui){
        gui = newGui;    
    }
    
    public static void setIO(IO newIo){
        io = newIo;
    }
    
    public static void setLogic(Logic newLogic){
        logic = newLogic;
    }
    
    public static void close(){
        Main.close();
    }
    
    public static void setScene(Scene newScene){
        Main.setScene(newScene);
    } 
}
