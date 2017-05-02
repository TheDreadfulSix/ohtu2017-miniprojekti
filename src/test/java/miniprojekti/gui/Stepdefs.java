package miniprojekti.gui;

import miniprojekti.main.App;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.HashSet;
import java.util.Set;
import javafx.application.Application;
import miniprojekti.domain.Article;
import miniprojekti.domain.Book;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;
import miniprojekti.domain.Reference;
import miniprojekti.logic.Logic;
import miniprojekti.main.Main;
import static org.junit.Assert.*;

public class Stepdefs {
    Reference ref;
    Set<Field> fields;
    String citationKey;
    Logic logic;

    @Given("^user chooses to edit reference that exists$")
    public void user_chooses_to_edit_reference_that_exists() throws Throwable {
        logic = new Logic();
        citationKey = "testkey";
        fields = new HashSet<>();
        
        fields.add(new Field(FieldName.AUTHOR, "Anthony Robins and Janet Rountree and Nathan Rountree"));
        fields.add(new Field(FieldName.JOURNAL, "Computer Science Education"));
        fields.add(new Field(FieldName.TITLE, "Learning and teaching programming: A review and discussion"));
        fields.add(new Field(FieldName.YEAR, "20003"));
        fields.add(new Field(FieldName.VOLUME, "13"));
        fields.add(new Field(FieldName.PAGES, "137-172"));
        ref = new Article(citationKey, fields);
        logic.add(ref);
    }

    @When("^user gives valid change$")
    public void user_gives_valid_change() throws Throwable {
        Set<Field> fields2 = new HashSet<>();
        
        fields2.add(new Field(FieldName.AUTHOR, "Anthony and Janet Rountree"));
        fields2.add(new Field(FieldName.JOURNAL, "Computer Science Education"));
        fields2.add(new Field(FieldName.TITLE, "Learning: A review and discussion"));
        fields2.add(new Field(FieldName.YEAR, "0001"));
        fields2.add(new Field(FieldName.VOLUME, "13"));
        fields2.add(new Field(FieldName.PAGES, "137-172"));
        Reference ref2 = new Article(citationKey, fields2);
        logic.edit(ref2);
    }

    @Then("^edit reference is success$")
    public void edit_reference_is_success(int val) throws Throwable {
        if (logic.getList().get(0).getField(FieldName.AUTHOR).equals("Anthony and Janet Rountree")) {
            assertTrue(true);
        }
        assertTrue(false);
    }
    
    @When("^user deletes text from required field$")
    public void user_deletes_text_from_required_field() throws Throwable {
        Set<Field> fields2 = new HashSet<>();
        
        fields2.add(new Field(FieldName.AUTHOR, "Anthony and Janet Rountree"));
        fields2.add(new Field(FieldName.JOURNAL, ""));
        fields2.add(new Field(FieldName.TITLE, "Learning: A review and discussion"));
        fields2.add(new Field(FieldName.YEAR, "0001"));
        fields2.add(new Field(FieldName.VOLUME, "13"));
        fields2.add(new Field(FieldName.PAGES, "137-172"));
        Reference ref2 = new Article(citationKey, fields2);
        logic.edit(ref2);
    }
    
    @Then("^did not edit reference$")
    public void did_not_edit_reference(int val) throws Throwable {
        if (logic.getList().get(0).getField(FieldName.AUTHOR).equals("Anthony Robins and Janet Rountree and Nathan Rountree")) {
            assertTrue(true);
        }
        assertTrue(false);
    }
}
