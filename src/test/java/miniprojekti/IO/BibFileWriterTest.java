package miniprojekti.IO;

import miniprojekti.domain.Article;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;
import miniprojekti.io.BibFileWriter;
import miniprojekti.io.BibReferenceFormatter;
import miniprojekti.io.FormattedStringBufferBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.util.*;
import miniprojekti.domain.Reference;

import static org.junit.Assert.*;


public class BibFileWriterTest {

    private Reference reference;
    private Collection<Reference> references;
    private Set<Field> fields;
    private BibReferenceFormatter formatter;
    private FormattedStringBufferBuilder bufferBuilder;
    private BibFileWriter writer = new BibFileWriter("testi", "US-ASCII");
    private final String testFolderPath = "src/test/resources/";
    File excpectedFile = new File(testFolderPath + "testi.bib");
    String output;

    @Before
    public void setUp() { //Creates new Reference objects -> in these tests as Articles.
        File dirBuilder = new File(testFolderPath);
        if (!dirBuilder.exists() || !dirBuilder.isDirectory()) { //Checks that paths folders exists. If not -> creates them..
            dirBuilder.mkdirs();
        }
        fields = new HashSet<>();
        fields.add(new Field(FieldName.AUTHOR, "Anthony Robins and Janet Rountree and Nathan Rountree"));
        fields.add(new Field(FieldName.JOURNAL, "Computer Science Education"));
        fields.add(new Field(FieldName.TITLE, "Learning and teaching programming: A review and discussion"));
        fields.add(new Field(FieldName.YEAR, "20003"));
        fields.add(new Field(FieldName.VOLUME, "13"));
        fields.add(new Field(FieldName.PAGES, "137-172"));
        reference = new Article("Robins+Rountrees", fields);
        references = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            references.add(reference);
        }
        formatter = new BibReferenceFormatter();
        bufferBuilder = new FormattedStringBufferBuilder(formatter);
        output = bufferBuilder.formatReferences(references).toString();
        writer.setPath(testFolderPath);
    }

    @After
    public void deleteAllTheFilesInTheTestDirectory() {
        Arrays.stream(new File(testFolderPath).listFiles()).forEach(File::delete);
    }

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void BibFileWriterWritesFile() {
        writer.writeFile(output);
        assertTrue(excpectedFile.exists());
    }

    @Test
    public void BibFileWriterWritesNewFileIfFileWithTheSameNameExists() {
        writer.writeFile(output);
        writer.writeFile(output);
        int numberOfFiles = new File(testFolderPath).listFiles().length;
        int expectedNumberOfFiles = 2;
        assertEquals(expectedNumberOfFiles, numberOfFiles);
    }
    
    
}
