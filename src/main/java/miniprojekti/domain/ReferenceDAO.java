/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojekti.domain;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for inserting and retrieving references to/from the database.
 */
public class ReferenceDAO extends BaseDAO {

    /**
     * Inserts the reference to the database.
     */
    public void insertReference(Reference reference) {
        try {
            String sql = formatInsertQuery(reference);
            initializeQuery(sql);
            implementQuery();
        } finally {
            close();
        }
    }

    /**
     * Delete reference.
     */
    public void deleteReference(Reference reference) {
        try {
            String sql = "DELETE FROM references WHERE citationKey = '" + reference.getCitationKey() + "'";
            initializeQuery(sql);
            implementQuery();
        } finally {
            close();
        }
    }

    /**
     * Retrieves all the references in the database
     *
     * @return Collection of references
     */
    public List<Reference> getReferences() {
        List<Reference> references = new ArrayList<>();
        try {
            String sql = "SELECT * FROM references";
            initializeQuery(sql);
            implementQuery();
            while (results.next()) {
                Reference ref = returnReference();
                references.add(ref);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReferenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close();
        }
        return references;
    }

    private String formatInsertQuery(Reference reference) {
        Map<FieldName, Field> fieldMap = reference.getFieldMap();
        StringBuilder sql = new StringBuilder("INSERT INTO references(");
        StringBuilder sqlFields = new StringBuilder();
        StringBuilder sqlValues = new StringBuilder();
        fieldMap.values().stream().forEach((field) -> {
            sqlFields.append(field.getName().toString() + ", ");
            sqlValues.append("'" + field.getValue() + "', ");
        });
        // add class and citationKey as the last field-value pairs
        sqlFields.append("class, citationKey, tags");
        sqlValues.append("'" + reference.getClass().getSimpleName() + "', '" + reference.getCitationKey() + "', '" + reference.getTags() + "'");
        sql.append(sqlFields + ") VALUES(" + sqlValues + ")");
        return sql.toString();
    }

    private Reference returnReference() {
        Object reference = null;
        Collection<Field> fields = new ArrayList<>();
        try {
            ResultSetMetaData metaData = results.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                // Class and citationKey are not field and thus they are handled separately. We'll also want to skip nulls.
                if (!columnName.equalsIgnoreCase("class") && !columnName.equalsIgnoreCase("citationKey") && 
                        !columnName.equalsIgnoreCase("tags")
                        && results.getString(columnName) != null) {
                    fields.add(new Field(FieldName.valueOf(columnName), results.getString(columnName)));
                }
            }
            String citationKey = results.getString("citationKey");
            String className = results.getString("class");
            String tags = results.getString("tags");
            className = "miniprojekti.domain." + className.substring(0, 1).toUpperCase() + className.substring(1).toLowerCase();
            Class cl = Class.forName(className);
            Constructor con = cl.getConstructor(String.class, Collection.class, String.class);
            reference = con.newInstance(citationKey, fields, tags);
            
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ReferenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (Reference) reference;
    }
}
