/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Task.Tas;
import TaskDAO.TaskDAOStatefulLocal;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author User
 */
@Named(value = "statefulTasks")
@SessionScoped
public class StatefulTasks implements Serializable{

@EJB
private TaskDAOStatefulLocal taskDAOStatefulLocal;

  @PostConstruct
    private void initBean() {
        count = 0;
        tas = new Tas();
    }
    private int count;
    private Tas tas;
    private int id;
    private int localId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Tas getTas() {
        return tas;
    }

    public void setTas(Tas tas) {
        this.tas = tas;
    }
       public String add() throws Exception {  
        java.sql.Date newDate = new java.sql.Date(tas.getDueDate().getTime());
        tas.setDueDate(newDate);
        this.count = taskDAOStatefulLocal.addTask(tas);
        return "/index.xhtml";
    }
       
 public String toEdit(int id) throws Exception{
        localId = id;
        return "/editTask.xhtml";
    }

    public String editTask() throws Exception {   
        this.taskDAOStatefulLocal.editTask(tas);
        return "/index.xhtml";
    }
}
