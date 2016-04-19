
package TaskDAO;

import javax.ejb.Singleton;

@Singleton
public class TaskDAOSingletone implements TaskDAOSingletoneLocal {
    private int hits = 1;
   
    
    @Override
    public int getHits(){
        return hits++;
    }
}
