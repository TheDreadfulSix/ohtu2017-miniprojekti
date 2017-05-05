/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Here we test insert, delete and retrieve methods to a test database.
 */
public class ReferenceDaoTest {

    private Reference reference;
    private Set<Field> fields;
    private static ReferenceDAO referenceDAO;

    @Before
    public void setUp() {
        referenceDAO = new ReferenceDAO();
        referenceDAO.useTestDatabase(false);
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
        reference = new Article("Testi", fields);
        referenceDAO.insertReference(reference);
    }

    @Test
    public void getReferencesReturnsAllTHerFourReferencesInTheDatabase() {
        List<Reference> references = referenceDAO.getReferences();
        assertEquals(4, references.size());
    }

    @Test
    public void deletingReferenceFromTheDatabaseIsSuccesful() {
        fields = new HashSet<>();
        fields.add(new Field(FieldName.AUTHOR, "Matti Meikäläinen"));
        fields.add(new Field(FieldName.JOURNAL, "Tärkeä artikkeli"));
        fields.add(new Field(FieldName.TITLE, "Suurien ajatusten lehti"));
        fields.add(new Field(FieldName.YEAR, "2017"));
        fields.add(new Field(FieldName.VOLUME, "99"));
        fields.add(new Field(FieldName.PAGES, "137-172"));
        reference = new Article("testiArtikkeli1", fields);
        referenceDAO.deleteReference(reference);
    }
    
    @After
    public void cleanFiles() {
        for (Reference reference1 : referenceDAO.getReferences()) {
            referenceDAO.deleteReference(reference1);
        }
    }
}