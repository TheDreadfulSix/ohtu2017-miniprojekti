package miniprojekti.logic;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import javafx.scene.control.TextField;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import miniprojekti.domain.Article;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;
import miniprojekti.domain.Inproceedings;
import miniprojekti.domain.Reference;
import miniprojekti.logic.Logic;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

public class Stepdefs {
    Reference ref;
    Set<Field> fields;
    String citationKey;
    Logic logic;
    
    @BeforeClass
    public void cleanFilesBefore() {
        this.logic.getAllReferences().forEach((reference1) -> {
            this.logic.delete(reference1);
        });
    }

    @Given("^user chooses to edit reference that exists$")
    public void user_chooses_to_edit_reference_that_exists() throws Throwable {
        this.logic = new Logic();
        this.logic.referenceDAO.useTestDatabase(true);
        this.citationKey = "test_key";
        this.fields = new HashSet<>();
        
        this.fields.add(new Field(FieldName.AUTHOR, "Anthony Robins and Janet Rountree and Nathan Rountree"));
        this.fields.add(new Field(FieldName.JOURNAL, "Computer Science Education"));
        this.fields.add(new Field(FieldName.TITLE, "Learning and teaching programming: A review and discussion"));
        this.fields.add(new Field(FieldName.YEAR, "20003"));
        this.fields.add(new Field(FieldName.VOLUME, "13"));
        this.fields.add(new Field(FieldName.PAGES, "137-172"));
        this.ref = new Article(this.citationKey, this.fields);
        this.logic.add(this.ref);
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
        Reference ref2 = new Article(this.citationKey, fields2);
        this.logic.edit(ref2);
    }

    @Then("^edit reference is success$")
    public void edit_reference_is_success() throws Throwable {
        if (this.logic.getList().get(0).getField(FieldName.AUTHOR).getValue().equals("Anthony and Janet Rountree")) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
        emptyList();
    }
    
    @Given("^user has added multiple references$")
    public void user_has_added_multiple_references() throws Throwable {
        this.logic = new Logic();
        this.logic.referenceDAO.useTestDatabase(true);
        this.citationKey = "test_key";
        
        fields = new HashSet<>();
        fields.add(new Field(FieldName.AUTHOR, "Anthony Smith"));
        fields.add(new Field(FieldName.JOURNAL, "Computer Science Education Super Edition!"));
        fields.add(new Field(FieldName.TITLE, "Learning: Java"));
        fields.add(new Field(FieldName.YEAR, "0002"));
        fields.add(new Field(FieldName.VOLUME, "13"));
        fields.add(new Field(FieldName.PAGES, "137-172"));
        
        ref = new Article(this.citationKey, this.fields);
        this.logic.add(ref);
        
        HashSet<Field> fields2 = new HashSet<>();
        fields2.add(new Field(FieldName.AUTHOR, "Anthony Robins and Janet Rountree and Nathan Rountree"));
        fields2.add(new Field(FieldName.BOOKTITLE, "Computer Science Education"));
        fields2.add(new Field(FieldName.TITLE, "Learning and teaching programming: A review and discussion"));
        fields2.add(new Field(FieldName.YEAR, "20003"));
        ref = new Inproceedings("anotherKey", fields2, "tag1 tag2");
        this.logic.add(ref);
    }

    @When("^user filters references with a keyword$")
    public void user_filters_references_with_a_keyword() throws Throwable {
        this.logic.filter("Super");
    }

    @Then("^logic return only filtered list$")
    public void logic_return_only_filtered_list() throws Throwable {
        assertEquals(this.logic.getList().size(), 1);
        emptyList();
    }
    
    public void emptyList() {
        this.logic.emptyFilter();
        this.logic.getAllReferences().forEach((reference1) -> {
            this.logic.delete(reference1);
        });
    }
    
}
