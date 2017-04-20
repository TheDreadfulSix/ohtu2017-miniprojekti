/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import static org.junit.Assert.assertEquals;

/**
 * @author tapio
 */
public class ReferenceDaoTest {

    private Article article1;
    private Article article2;
    private Set<Field> fields;
    ReferenceDAO rDAO;

    @Before
    public void setUp() {
        fields = new HashSet<>();
        fields.add(new Field(FieldName.AUTHOR, "Anthony Robins and Janet Rountree and Nathan Rountree"));
        fields.add(new Field(FieldName.JOURNAL, "Computer Science Education"));
        fields.add(new Field(FieldName.TITLE, "Learning and teaching programming: A review and discussion"));
        fields.add(new Field(FieldName.YEAR, "20003"));
        fields.add(new Field(FieldName.VOLUME, "13"));
        fields.add(new Field(FieldName.PAGES, "137-172"));
        article1 = new Article("Testi1", fields);
        article2 = new Article("Testi2", fields);
        rDAO = new ReferenceDAO();
    }

    @Ignore
    @Test
    public void addAndGetTwoReferences() {
        rDAO.insertReference(article1);
        rDAO.insertReference(article2);
        Collection<Reference> references = rDAO.getReferences();
        assertEquals(2, references.size());
    }
}
