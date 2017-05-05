/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.main;

import java.util.concurrent.TimeoutException;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;


public class MainTest extends ApplicationTest {
    public Stage stage;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        new Main().start(stage);
    }
    
    @After
    public void afterEachTest() throws TimeoutException {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }
    @Test
    public void test1() throws TimeoutException {
        double x = this.stage.getX() + 730;
        double y = this.stage.getY() + 20;
        clickOn("Actions");
        clickOn(x, y).write("testiArtikkeli1"); 
        clickOn("Actions");
        doubleClickOn(x, y).clickOn(x, y).write("testiArtikkeli2");
        clickOn("Actions");
        doubleClickOn(x, y).clickOn(x, y).write("testiKirja1");
        clickOn("Actions");
        doubleClickOn(x, y).clickOn(x, y).write("testiKirja2");
        clickOn("Actions");
        doubleClickOn(x, y).clickOn(x, y).write("testiNothing");
        clickOn("Actions");
        doubleClickOn(x, y).clickOn(x, y).write("");
        clickOn("Actions");
        
        /* TestFX seems to have pretty big downside, the fact that it only keeps track of your root scenes objects,
        So the moment the software opens alert or other second window, it gets to loop rtying to find the new screens
        objects forever. Controllers might be apply to fix/go around it, but gets too complicated for small projects. TestFX Version:4.0.6-alpha*/
        
        /* These wont work as TestFX cant find them.
        clickOn("Actions");
        clickOn("Add Reference");
        type(KeyCode.getKeyCode("Elias Lönnrot"));
        hitTAB(1);
        type(KeyCode.getKeyCode("Eepos"));
        hitTAB(1);
        type(KeyCode.getKeyCode("Kalevala"));
        hitTAB(1);
        type(KeyCode.getKeyCode("1835"));
        hitTAB(9);
        type(KeyCode.getKeyCode("ElLö1835"));
        */
    }

}
