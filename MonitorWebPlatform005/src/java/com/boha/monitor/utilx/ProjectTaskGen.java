package com.boha.monitor.utilx;

import com.boha.monitor.data.Project;
import com.boha.monitor.data.ProjectTask;
import com.boha.monitor.data.Task;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author aubreymalabie
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ProjectTaskGen {

    @PersistenceContext
    EntityManager em;

    @SuppressWarnings("unchecked")
    public String generate(Integer companyID) {
        StringBuilder sb = new StringBuilder();
        sb.append("##### Generate started .................\n\n");
        Query q = em.createNamedQuery("ProjectTask.deleteByCompany", Project.class);
        q.setParameter("companyID", companyID);
        int deleted = q.executeUpdate();
        sb.append("%%% project tasks deleted: ").append(deleted).append("\n");
        em.flush();

        q = em.createNamedQuery("Project.findByCompany", Project.class);
        q.setParameter("companyID", companyID);
        List<Project> pList = q.getResultList();

        q = em.createNamedQuery("Task.findByCompany", Task.class);
        q.setParameter("companyID", companyID);
        List<Task> taskList = q.getResultList();

        for (Task task : taskList) {
            for (Project p : pList) {
                ProjectTask pt = new ProjectTask();
                pt.setProject(p);
                pt.setTask(task);
                pt.setDateRegistered(new Date());
                em.persist(pt);
                sb.append("###### task : ").append(task.getTaskName()).append(" - ").append(p.getProjectName());
                sb.append("\n");
            }
        }
        sb.append("\n\nDone with project tasks");
        return sb.toString();

    }

}
