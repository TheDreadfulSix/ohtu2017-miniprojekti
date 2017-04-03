package miniprojekti.domain;

import domain.Article;
import domain.Field;
import domain.FieldName;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;


public class ArticleTest {
    Article article;
    Set<Field> fields;
    
    @Before
    public void setUp() {
        fields = new HashSet<>();
        
        fields.add(new Field(FieldName.AUTHOR, "Anthony Robins and Janet Rountree and Nathan Rountree"));
        fields.add(new Field(FieldName.JOURNAL, "Computer Science Education"));
        fields.add(new Field(FieldName.TITLE, "Learning and teaching programming: A review and discussion"));
        fields.add(new Field(FieldName.YEAR, "20003"));
        fields.add(new Field(FieldName.VOLUME, "13"));
        fields.add(new Field(FieldName.PAGES, "137-172"));
        
    }
    
    @Rule
    public ExpectedException expected = ExpectedException.none();
    
    @Test
    public void constructorFailsWithMissingRequiredFields() {
        fields = new HashSet<>();
        
        fields.add(new Field(FieldName.AUTHOR, "Anthony Robins and Janet Rountree and Nathan Rountree"));
        fields.add(new Field(FieldName.JOURNAL, "Computer Science Education"));
        fields.add(new Field(FieldName.TITLE, "Learning and teaching programming: A review and discussion"));
        
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Required fields are missing");
        
        article = new Article(fields);
    }
    
    @Test
    public void constructorFailsWithInvalidOptionalFields() {
        fields = new HashSet<>();
        
        fields.add(new Field(FieldName.AUTHOR, "Anthony Robins and Janet Rountree and Nathan Rountree"));
        fields.add(new Field(FieldName.JOURNAL, "Computer Science Education"));
        fields.add(new Field(FieldName.TITLE, "Learning and teaching programming: A review and discussion"));
        fields.add(new Field(FieldName.YEAR, "20003"));
        // Invalid field
        fields.add(new Field(FieldName.EDITION, "5"));
        
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage("Invalid optional fields");
        
        article = new Article(fields);
    }
}
