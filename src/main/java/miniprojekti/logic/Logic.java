/*
 *   Tän luokan voi nimetä myös kuvaavammin kun logic
 *   Placeholderi vaan atm!!!!
 */
package miniprojekti.logic;

import miniprojekti.domain.Reference;
import miniprojekti.domain.ReferenceDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import miniprojekti.domain.FieldName;
import miniprojekti.main.App;

/**
 *
 * @author Joonas
 */
public class Logic {

    private List<Reference> list; //this will contain all references, at all times.
    private List<Reference> filtered; //this has only ones that have been through filter.
    private ReferenceDAO referenceDAO;

    public Logic() {
        list = new ArrayList<>();
        filtered = new ArrayList<>();
        referenceDAO = new ReferenceDAO();
    }

    public void add(Reference ref) {
        list.add(ref);
        referenceDAO.insertReference(ref);
        //TODO Might have to call filter here to get new refreshed list.
        //now only empties the filter and lists all references.
        App.getLogic().emptyFilter();
    }

    public void delete(Reference ref) {
        list.remove(ref);
        referenceDAO.deleteReference(ref);
        //TODO Might have to call filter here to get new refreshed list.
        //now only empties the filter and lists all references.
        App.getLogic().emptyFilter();
    }

    public void edit(Reference ref) {
        //TODO editing reference. Not in sprint 3.
    }

    public void filter(String word) {
        //TODO 
        this.filtered = referenceDAO.getFilteredReferences(word);
    }

    public void emptyFilter() {
        this.filtered.clear();
    }

    public List<Reference> getList() {
        if (filtered.isEmpty() || filtered.containsAll(list)) {
            list = (List<Reference>) this.getAllReferences();
            return list;
        }
        return filtered; //If filtered has something in it, but not all references, the return filtered list.
    }

    public Collection<Reference> getAllReferences() { //a bit redundant method.
        return referenceDAO.getReferences();
    }
}
