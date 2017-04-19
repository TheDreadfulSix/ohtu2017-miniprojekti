/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.domain;

import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author tapio
 */
public class ReferenceDaoTest {

    private Article article;
    private Set<Field> fields;

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
    }

    @Test 
    public void addReferenceToDb() {
       ReferenceDAO rDAO = new ReferenceDAO();
       rDAO.insertReference(article);
    }

}
