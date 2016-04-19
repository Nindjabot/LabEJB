
package TaskDAO;

import Task.Tas;
import java.util.List;

import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface TaskDAOStatelessLocal {
    public List<Tas> findAllTasks();
        
}
