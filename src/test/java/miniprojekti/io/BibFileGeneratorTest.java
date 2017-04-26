package miniprojekti.io;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import miniprojekti.domain.Article;
import miniprojekti.domain.Book;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;
import miniprojekti.domain.Inproceedings;
import miniprojekti.domain.Reference;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author esksami
 */
public class BibFileGeneratorTest {
    BibFileFormatter formatter;
    BibFileGenerator generator;
    Collection<Reference> references;
    String expectedContent ="@Article{Robins+Rountrees,\n" +
                            "  AUTHOR = {Anthony Robins and Janet Rountree and Nathan Rountree},\n"+
                            "  JOURNAL = {Computer Science Education},\n"+
                            "  PAGES = {137-172},\n"+
                            "  TITLE = {Learning and teaching programming: A review and discussion},\n"+
                            "  VOLUME = {13},\n"+
                            "  YEAR = {20003}\n"+
                            "}";
    
    @Before
    public void setUp() {
        formatter = mock(BibFileFormatter.class);
        generator = Mockito.spy(new BibFileGenerator(formatter));
        
        
        references = new ArrayList<>();
        Set<Field> fields = new HashSet<>();
        fields.add(new Field(FieldName.AUTHOR, "Anthony Robins and Janet Rountree and Nathan Rountree"));
        fields.add(new Field(FieldName.JOURNAL, "Computer Science Education"));
        fields.add(new Field(FieldName.TITLE, "Learning and teaching programming: A review and discussion"));
        fields.add(new Field(FieldName.YEAR, "20003"));
        fields.add(new Field(FieldName.VOLUME, "13"));
        fields.add(new Field(FieldName.PAGES, "137-172"));
        references.add(new Article("Robins+Rountrees", fields));
        fields.clear();
        fields.add(new Field(FieldName.EDITOR, "Manu"));
        fields.add(new Field(FieldName.TITLE, "How to Java"));
        fields.add(new Field(FieldName.YEAR, "20005"));
        fields.add(new Field(FieldName.PUBLISHER, "Pena"));
        references.add(new Book("PeMa", fields));
        fields.clear();
        fields.add(new Field(FieldName.YEAR, "20004"));
        fields.add(new Field(FieldName.AUTHOR, "Penaelmi"));
        fields.add(new Field(FieldName.BOOKTITLE, "How to Java"));
        fields.add(new Field(FieldName.TITLE, "Manselmi"));
        references.add(new Inproceedings("Penselmi", fields));
    }

    @Test
    public void fileIsGeneratedWithValidInput() {
        when(formatter.generateContents(references)).thenReturn("");
        
        assertTrue(generator.createFile("src/test/resources/", "testi", references));
        File expectedFile = new File("src/test/resources/testi.bib");
        assertTrue(expectedFile.isFile());
    }
    
    @Test
    public void fileHasCorrectContent() throws IOException{
        when(formatter.generateContents(references)).thenReturn(expectedContent);
        
        generator.createFile("src/test/resources/", "testi.bib", references);
        File expectedFile = new File("src/test/resources/testi.bib");
        
        String content = Files.toString(expectedFile, Charsets.UTF_8);
        
        assertEquals(expectedContent, content);
    }
    
    @Test
    public void timestampIsAddedIfFileExists() throws IOException{
        when(formatter.generateContents(references)).thenReturn(expectedContent);
        when(generator.generateTimestamp()).thenReturn("(2012-12-12-12:21:02)");

        
        generator.createFile("src/test/resources/", "testi.bib", references);
        generator.createFile("src/test/resources/", "testi.bib", references);
        File expectedFile = new File("src/test/resources/testi(2012-12-12-12:21:02).bib");
        assertTrue(expectedFile.isFile());
    }
    
    @After
    public void cleanFiles() {
        Arrays.stream(new File("src/test/resources/").listFiles(
                (File file) -> file.getName().endsWith(".bib"))
        ).forEach(File::delete);
    }
}
