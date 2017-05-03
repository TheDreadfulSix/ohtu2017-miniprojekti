package miniprojekti.io;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import miniprojekti.domain.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
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
    String path = "src" + File.separator + "test" + File.separator +"resources" + File.separator;
    @Before
    public void setUp() throws IOException {
        formatter = mock(BibFileFormatter.class);
        generator = Mockito.spy(new BibFileGenerator(formatter));
        Files.createParentDirs(new File(path + "file"));
        
        
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
        
        assertTrue(generator.createFile(path, "testi", references));
        File expectedFile = new File(path + "testi.bib");
        assertTrue(expectedFile.isFile());
    }
    
    @Test
    public void fileHasCorrectContent() throws IOException{
        when(formatter.generateContents(references)).thenReturn(expectedContent);
        
        generator.createFile(path, "testi.bib", references);
        File expectedFile = new File(path + "testi.bib");
        
        String content = Files.toString(expectedFile, Charsets.UTF_8);
        
        assertEquals(expectedContent, content);
    }
    
    @Test
    public void timestampIsAddedIfFileExists() throws IOException{
        when(formatter.generateContents(references)).thenReturn(expectedContent);
        when(generator.generateTimestamp()).thenReturn("(2012-12-12-12:21:02)");

        
        generator.createFile(path, "testi.bib", references);
        generator.createFile(path, "testi.bib", references);
        File expectedFile = new File(path + "testi(2012-12-12-12:21:02).bib");
        assertTrue(expectedFile.isFile());
    }
    
    /*@Test
    public void writingFileCausesException() {
        try {
            generator.createFile("&/%/%/‚{{{{{{/%(%(%(", "testi.bib", references);
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }*/
    
    @After
    public void cleanFiles() {
        Arrays.stream(new File(path).listFiles(
                (File file) -> file.getName().endsWith(".bib"))
        ).forEach(File::delete);
    }
}
