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

/**
 *
 * @author Joonas
 */
public class Logic {
    private List<Reference> list;
    private ReferenceDAO referenceDAO;
    
    public Logic(){
        list = new ArrayList<>();
        referenceDAO = new ReferenceDAO();
    }
    
    public void add(Reference ref){
        list.add(ref);
        referenceDAO.insertReference(ref);
    }
    
    public List<Reference> getList(){
        return list;
    }

    public Collection<Reference> getAllReferences() {
        return referenceDAO.getReferences();
    }
}
