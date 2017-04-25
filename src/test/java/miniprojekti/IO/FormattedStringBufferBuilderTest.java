package miniprojekti.IO;

import miniprojekti.domain.Article;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;
import miniprojekti.io.BibReferenceFormatter;
import miniprojekti.io.FormattedStringBufferBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;
import miniprojekti.domain.Book;
import miniprojekti.domain.Inproceedings;
import miniprojekti.domain.Reference;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class FormattedStringBufferBuilderTest {

    private Reference reference;
    private Collection<Reference> references;
    private Set<Field> fields;
    private BibReferenceFormatter formatter;
    private FormattedStringBufferBuilder bufferBuilder;
    
    @Before
    public void setUp() { //Creates new Reference object -> in these tests as Article.
        fields = new HashSet<>();
        fields.add(new Field(FieldName.AUTHOR, "Anthony Robins and Janet Rountree and Nathan Rountree"));
        fields.add(new Field(FieldName.JOURNAL, "Computer Science Education"));
        fields.add(new Field(FieldName.TITLE, "Learning and teaching programming: A review and discussion"));
        fields.add(new Field(FieldName.YEAR, "20003"));
        fields.add(new Field(FieldName.VOLUME, "13"));
        fields.add(new Field(FieldName.PAGES, "137-172"));
        reference = new Article("Robins+Rountrees", fields);
        references = new ArrayList<>();
        references.add(reference);
        fields.clear();
        fields.add(new Field(FieldName.EDITOR, "Manu"));
        fields.add(new Field(FieldName.TITLE, "How to Java"));
        fields.add(new Field(FieldName.YEAR, "20005"));
        fields.add(new Field(FieldName.PUBLISHER, "Pena"));
        reference = new Book("PeMa", fields);
        references.add(reference);
        fields.clear();
        fields.add(new Field(FieldName.YEAR, "20004"));
        fields.add(new Field(FieldName.AUTHOR, "Penaelmi"));
        fields.add(new Field(FieldName.BOOKTITLE, "How to Java"));
        fields.add(new Field(FieldName.TITLE, "Manselmi"));
        reference = new Inproceedings("Penselmi", fields);
        references.add(reference);
        formatter = new BibReferenceFormatter();
        bufferBuilder = new FormattedStringBufferBuilder(formatter);
    }
    
    @Rule
    public ExpectedException expected = ExpectedException.none();

    
    @Test
    public void formatReferencesBuildsValidStringBuffer() {
        
        String output = bufferBuilder.formatReferences(references).toString();
        assertTrue(output.contains("@article{Robins+Rountrees,"));
        assertTrue(output.contains("  PAGES = {137-172}"));
        assertTrue(output.contains("  VOLUME = {13}"));
        assertTrue(output.contains("  TITLE = {Learning and teaching programming: A review and discussion}"));
        assertTrue(output.contains("  JOURNAL = {Computer Science Education}"));
        assertTrue(output.contains("  YEAR = {20003}"));
        assertTrue(output.contains("  AUTHOR = {Anthony Robins and Janet Rountree and Nathan Rountree}"));
        assertTrue(output.contains("@book{PeMa,"));
        assertTrue(output.contains("  EDITOR = {Manu}"));
        assertTrue(output.contains("  YEAR = {20005}"));
        assertTrue(output.contains("  PUBLISHER = {Pena}"));
        assertTrue(output.contains("  TITLE = {How to Java}"));
        assertTrue(output.contains("@inproceedings{Penselmi,"));
        assertTrue(output.contains("  BOOKTITLE = {How to Java}"));
        assertTrue(output.contains("  TITLE = {Manselmi}"));
        assertTrue(output.contains("  AUTHOR = {Penaelmi}"));
        assertTrue(output.contains("  YEAR = {20004}"));
        
    }
}
