package miniprojekti.IO;

import miniprojekti.domain.Article;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;
import miniprojekti.io.BibReferenceFormatter;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import miniprojekti.domain.Reference;


public class BibReferenceFormatterTest {
    private Reference reference;
    private Set<Field> fields;
    private BibReferenceFormatter formatter;
    
    @Before
    public void setUp() {
        fields = new HashSet<>();
        fields.add(new Field(FieldName.AUTHOR, "Anthony Robins and Janet Rountree and Nathan Rountree"));
        fields.add(new Field(FieldName.JOURNAL, "Computer Science Education"));
        fields.add(new Field(FieldName.TITLE, "Learning and teaching programming: A review and discussion"));
        fields.add(new Field(FieldName.YEAR, "20003"));
        fields.add(new Field(FieldName.VOLUME, "13"));
        fields.add(new Field(FieldName.PAGES, "137-172"));
        reference = new Article("Robins+Rountrees", fields);
        formatter = new BibReferenceFormatter();
    }
    
    @Rule
    public ExpectedException expected = ExpectedException.none();

    
    @Test
    public void formatFieldFormatsFieldCorrectly() {
        
        
        
        formatter.formatReference(reference);
        String output = formatter.getStringBuffer().toString();
        assertTrue(output.contains("@article{Robins+Rountrees,"));
        assertTrue(output.contains(" PAGES = {137-172}"));
        assertTrue(output.contains("  VOLUME = {13},"));
        assertTrue(output.contains("  TITLE = {Learning and teaching programming: A review and discussion},"));
        assertTrue(output.contains("  JOURNAL = {Computer Science Education},"));
        assertTrue(output.contains("  YEAR = {20003},"));
        assertTrue(output.contains("  AUTHOR = {Anthony Robins and Janet Rountree and Nathan Rountree},"));
    }
}
