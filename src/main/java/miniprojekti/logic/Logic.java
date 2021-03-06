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
import miniprojekti.main.App;

/**
 *
 * @author Joonas
 */
public class Logic {

    private List<Reference> list; //this will contain all references, at all times.
    private List<Reference> filtered; //this has only ones that have been through filter.
    public ReferenceDAO referenceDAO; //public for tests.
    private String filter = "";

    public Logic() {
        list = new ArrayList<>();
        filtered = new ArrayList<>();
        referenceDAO = new ReferenceDAO();
    }

    public void add(Reference ref) {
        list.add(ref);
        referenceDAO.insertReference(ref);
        App.getLogic().emptyFilter();
    }

    public void delete(Reference ref) {
        list.remove(ref);
        referenceDAO.deleteReference(ref);
        App.getLogic().emptyFilter();
    }
    
    public void edit(Reference ref) {
        this.delete(ref);
        this.add(ref);
    }

    public void filter(String word) {
        //TODO 
        filter = word;
        List<Reference> toFilter = (List<Reference>)getAllReferences();
        word = word.toLowerCase();
        List<Reference> filtered = new ArrayList();
        for (Reference toFilter1 : toFilter) {
            if (toFilter1.toString().toLowerCase().contains(word)) {
                filtered.add(toFilter1);
            }
        }
        this.filtered = filtered;
    }

    public void emptyFilter() {
        this.filtered.clear();
    }

    public List<Reference> getList() {
        if (filter.isEmpty() || filtered.containsAll(list)) {
            list = (List<Reference>) this.getAllReferences();
            return list;
        }
        return filtered; //If filtered has something in it, but not all references, the return filtered list.
    }

    public Collection<Reference> getAllReferences() { //a bit redundant method.
        return referenceDAO.getReferences();
    }
}
