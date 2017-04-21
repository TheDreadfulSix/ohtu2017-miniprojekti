/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.domain;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Here we test insert, delete and retrieve methods to a test database.
 */
public class ReferenceDaoTest {

    private Article article;
    private Set<Field> fields;
    private static ReferenceDAO referenceDAO;

    @Before
    public void setUp() {
        referenceDAO = new ReferenceDAO();
        referenceDAO.useTestDatabase();
    }

    @Test
    public void insertingReferenceWithUniqueCitationKeyIsSuccesful() {
        fields = new HashSet<>();
        fields.add(new Field(FieldName.AUTHOR, "Anthony Robins and Janet Rountree and Nathan Rountree"));
        fields.add(new Field(FieldName.JOURNAL, "Computer Science Education"));
        fields.add(new Field(FieldName.TITLE, "Learning and teaching programming: A review and discussion"));
        fields.add(new Field(FieldName.YEAR, "20003"));
        fields.add(new Field(FieldName.VOLUME, "13"));
        fields.add(new Field(FieldName.PAGES, "137-172"));
        article = new Article("Testi", fields);
        referenceDAO.insertReference(article);
    }

    @Test
    public void getReferencesReturnsAllTHerFourReferencesInTheDatabase() {
        List<Reference> references = referenceDAO.getReferences();
        assertEquals(4, references.size());
    }
}
