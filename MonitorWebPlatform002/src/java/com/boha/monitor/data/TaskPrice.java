/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "taskPrice")
@NamedQueries({
    @NamedQuery(name = "TaskPrice.findByTask", 
            query = "SELECT t FROM TaskPrice t where t.task.taskID = :taskID "
                    + "ORDER BY t.taskPriceID desc"),
    
    @NamedQuery(name = "TaskPrice.findByCompany", 
            query = "SELECT t FROM TaskPrice t where t.task.company.companyID = :companyID "
                    + "order by t.task.taskID, t.taskPriceID desc"),
    
    @NamedQuery(name = "TaskPrice.findByProject", 
            query = "SELECT t FROM TaskPrice t where t.project.projectID = :projectID  "
                    + "order by t.taskPriceID desc")
    
})
public class TaskPrice implements Serializable {
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne
    private Project project;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taskPriceID")
    private Integer taskPriceID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private double price;
    @JoinColumn(name = "taskID", referencedColumnName = "taskID")
    @ManyToOne(optional = false)
    private Task task;

    public TaskPrice() {
    }

    public TaskPrice(Integer taskPriceID) {
        this.taskPriceID = taskPriceID;
    }

    public TaskPrice(Integer taskPriceID, Date startDate, double price) {
        this.taskPriceID = taskPriceID;
        this.startDate = startDate;
        this.price = price;
    }

    public Integer getTaskPriceID() {
        return taskPriceID;
    }

    public void setTaskPriceID(Integer taskPriceID) {
        this.taskPriceID = taskPriceID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskPriceID != null ? taskPriceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskPrice)) {
            return false;
        }
        TaskPrice other = (TaskPrice) object;
        if ((this.taskPriceID == null && other.taskPriceID != null) || (this.taskPriceID != null && !this.taskPriceID.equals(other.taskPriceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.TaskPrice[ taskPriceID=" + taskPriceID + " ]";
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    
}
