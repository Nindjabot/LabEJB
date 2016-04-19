
package TaskDAO;

import Task.Tas;
import javax.ejb.Local;

@Local
public interface TaskDAOStatefulLocal {
    public int addTask(Tas tas) throws Exception;
    public int editTask(Tas tas)throws Exception;
}
