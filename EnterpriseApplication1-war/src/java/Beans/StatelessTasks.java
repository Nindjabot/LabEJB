
package Beans;

import Task.Tas;
import TaskDAO.TaskDAOStatelessLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;


@Named(value = "statelessTasks")
@Dependent
public class StatelessTasks {
    @EJB
    private TaskDAOStatelessLocal state;
    
    public List<Tas> findAll() {
        return this.state.findAllTasks();
    }
    
}
