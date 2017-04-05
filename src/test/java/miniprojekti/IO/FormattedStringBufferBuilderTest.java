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

import static org.junit.Assert.assertEquals;


public class FormattedStringBufferBuilderTest {

    private Article article;
    private Collection<Article> articles;
    private Set<Field> fields;
    private BibReferenceFormatter formatter;
    private FormattedStringBufferBuilder bufferBuilder;
    
    @Before
    public void setUp() {
        fields = new HashSet<>();
        fields.add(new Field(FieldName.AUTHOR, "Anthony Robins and Janet Rountree and Nathan Rountree"));
        fields.add(new Field(FieldName.JOURNAL, "Computer Science Education"));
        fields.add(new Field(FieldName.TITLE, "Learning and teaching programming: A review and discussion"));
        fields.add(new Field(FieldName.YEAR, "20003"));
        fields.add(new Field(FieldName.VOLUME, "13"));
        fields.add(new Field(FieldName.PAGES, "137-172"));
        article = new Article("Robins+Rountrees", fields);
        articles = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            articles.add(article);
        }
        formatter = new BibReferenceFormatter();
        bufferBuilder = new FormattedStringBufferBuilder(formatter);
    }
    
    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Ignore
    @Test
    public void formatReferencesBuildsValidStringBuffer() {
        String expectedOutput =
                "@article{Robins+Rountrees,\n" +
                        "  PAGES = {137-172},\n" +
                        "  VOLUME = {13},\n" +
                        "  TITLE = {Learning and teaching programming: A review and discussion},\n" +
                        "  JOURNAL = {Computer Science Education},\n" +
                        "  YEAR = {20003},\n" +
                        "  AUTHOR = {Anthony Robins and Janet Rountree and Nathan Rountree},\n" +
                "}\n" +
                "@article{Robins+Rountrees,\n" +
                        "  PAGES = {137-172},\n" +
                        "  VOLUME = {13},\n" +
                        "  TITLE = {Learning and teaching programming: A review and discussion},\n" +
                        "  JOURNAL = {Computer Science Education},\n" +
                        "  YEAR = {20003},\n" +
                        "  AUTHOR = {Anthony Robins and Janet Rountree and Nathan Rountree},\n" +
                "}\n" +
                "@article{Robins+Rountrees,\n" +
                        "  PAGES = {137-172},\n" +
                        "  VOLUME = {13},\n" +
                        "  TITLE = {Learning and teaching programming: A review and discussion},\n" +
                        "  JOURNAL = {Computer Science Education},\n" +
                        "  YEAR = {20003},\n" +
                        "  AUTHOR = {Anthony Robins and Janet Rountree and Nathan Rountree},\n" +
                "}\n";
        String output = bufferBuilder.formatReferences(articles).toString();
        assertEquals(expectedOutput, output);
    }
}
