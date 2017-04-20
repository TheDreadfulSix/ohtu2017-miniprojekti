/*
*   Tän luokan voi nimetä myös kuvaavammin kun logic
*   Placeholderi vaan atm!!!!
*/
package miniprojekti.logic;

import miniprojekti.domain.Reference;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joonas
 */
public class Logic {
    private List<Reference> list;
    
    public Logic(){
        list = new ArrayList<>();
    }
    
    public void add(Reference ref){
        list.add(ref);
    }
    
    public List<Reference> getList(){
        return list;
    }
}
