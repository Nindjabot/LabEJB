
package TaskDAO;

import Task.Tas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

@Stateless
public class TaskDAOStateless implements TaskDAOStatelessLocal {

    @Resource(lookup="jdbc/clinic")
    private DataSource dataSource;
    
    @Override
    public List<Tas> findAllTasks() {
         try (Connection connection = dataSource.getConnection()) {
            String query = "SELECT * FROM Task";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            List<Tas> tasks = new ArrayList<>();
            while (res.next()) {
                Tas mod = new Tas();
                mod.setId(res.getInt(1));
                mod.setName(res.getString(2));
                mod.setDescription(res.getString(3));
                mod.setDueDate(res.getDate(4));
                tasks.add(mod);
            }
            return tasks;
        } catch (Exception ex) {
            throw new RuntimeException("An error has occured in lisPets methos", ex);
        }

    }


}
