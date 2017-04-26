import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import miniprojekti.domain.Article;
import miniprojekti.domain.Book;
import miniprojekti.domain.Field;
import miniprojekti.domain.FieldName;
import miniprojekti.domain.Inproceedings;
import miniprojekti.domain.Reference;
import miniprojekti.io.BibFileFormatter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author esksami
 */
public class BibFileFormatterTest {
    BibFileFormatter formatter;
    Collection<Reference> references;
    
    @Before
    public void setUp() {
        formatter = new BibFileFormatter();
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
    public void formatCorrectlyWithValidInput() {
        String contents = formatter.generateContents(references);
        
        String expected = "@Article{Robins+Rountrees,\n" +
                          "  AUTHOR = {Anthony Robins and Janet Rountree and Nathan Rountree},\n"+
                          "  JOURNAL = {Computer Science Education},\n"+
                          "  PAGES = {137-172},\n"+
                          "  TITLE = {Learning and teaching programming: A review and discussion},\n"+
                          "  VOLUME = {13},\n"+
                          "  YEAR = {20003}\n"+
                          "}\n\n"+

                          "@Book{PeMa,\n"+
                          "  EDITOR = {Manu},\n"+
                          "  PUBLISHER = {Pena},\n"+
                          "  TITLE = {How to Java},\n"+
                          "  YEAR = {20005}\n"+
                          "}\n\n"+

                          "@Inproceedings{Penselmi,\n"+
                          "  AUTHOR = {Penaelmi},\n"+
                          "  BOOKTITLE = {How to Java},\n"+
                          "  TITLE = {Manselmi},\n"+
                          "  YEAR = {20004}\n"+
                          "}\n";
        
        assertEquals(expected, contents);
    }
}
