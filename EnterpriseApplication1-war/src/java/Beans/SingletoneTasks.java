/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import TaskDAO.TaskDAOSingletoneLocal;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author User
 */
@Named(value = "singletoneTasks")
@Dependent
public class SingletoneTasks {
    @EJB
    private TaskDAOSingletoneLocal counter;
    
    private int hitCount;
    
    public SingletoneTasks(){
        this.hitCount = 0;
    }

    public int getHitCount() {
        hitCount= counter.getHits();
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }
}
