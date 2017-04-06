/*
*   Tän luokan voi nimetä myös kuvaavammin kun logic
*   Placeholderi vaan atm!!!!
*/
package miniprojekti.logic;

import miniprojekti.domain.Article;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joonas
 */
public class Logic {
    private List<Article> list;
    
    public Logic(){
        list = new ArrayList<>();
    }
    
    public void add(Article article){
        list.add(article);
    }
    
    public List<Article> getList(){
        return list;
    }
}
