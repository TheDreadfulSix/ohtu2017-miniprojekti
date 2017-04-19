/*
*   Tän luokan voi nimetä myös kuvaavammin kun logic
*   Placeholderi vaan atm!!!!
*/
package miniprojekti.logic;

import java.util.ArrayList;
import java.util.List;
import miniprojekti.domain.Reference;

/**
 *
 * @author Joonas
 */
public class Logic {
    private List<Reference> list;
    
    public Logic(){
        list = new ArrayList<>();
    }
    
    public void add(Reference reference){
        list.add(reference);
    }
    
    public List<Reference> getList(){
        return list;
    }
}
