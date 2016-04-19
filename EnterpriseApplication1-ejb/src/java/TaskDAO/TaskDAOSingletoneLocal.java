
package TaskDAO;

import javax.ejb.Local;

@Local
public interface TaskDAOSingletoneLocal {
    public int getHits();
}
