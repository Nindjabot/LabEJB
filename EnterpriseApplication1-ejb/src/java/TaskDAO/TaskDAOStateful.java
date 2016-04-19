/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TaskDAO;

import Task.Tas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

/**
 *
 * @author User
 */
@Stateful
@ConversationScoped
public class TaskDAOStateful implements TaskDAOStatefulLocal {

    private int count;
    @Inject
    Conversation conv;
    @Resource(lookup = "jdbc/clinic")
    private DataSource dataSource;

    @PostConstruct
    public void initStart() {//
        count = 0;
    }

    public void endConv() {
        count = 0;
        if (!conv.isTransient()) {
            conv.end();
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int addTask(Tas tas) throws Exception{
        try(Connection con = dataSource.getConnection()) {
            String query = "INSERT INTO task (name, description, due_date) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, tas.getName());
            stmt.setString(2, tas.getDescription());
            stmt.setTimestamp(3, new Timestamp(tas.getDueDate().getTime()));
            stmt.execute();
            if (conv.isTransient()) {
                conv.begin();
            }
            count++;
            return count;
        } catch (Exception ex) {
            throw new RuntimeException("An error has occured method", ex);
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    @Override
    public int editTask(Tas tas) throws Exception{
        try(Connection con = dataSource.getConnection()) {
            String query = "UPDATE task SET name=?, description=?, due_date=? WHERE idTask=? ";
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, tas.getName());
            statement.setString(2, tas.getDescription());
            statement.setTimestamp(3, new Timestamp(tas.getDueDate().getTime()));
            statement.setInt(4, tas.getId());
            statement.execute();
            endConv();
            return count;

        } catch (Exception ex) {
            throw  new RuntimeException("An error has occured method", ex);
        }
    }

}
