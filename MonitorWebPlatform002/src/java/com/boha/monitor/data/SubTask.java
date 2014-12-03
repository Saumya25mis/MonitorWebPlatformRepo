/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "subTask")
@NamedQueries({
    @NamedQuery(name = "SubTask.findByTask", 
            query = "SELECT s FROM SubTask s where s.task.taskID = :taskID order by s.subTaskNumber"),
    @NamedQuery(name = "SubTask.findByCompany", 
            query = "SELECT s FROM SubTask s where s.task.company.companyID = :companyID")
})
public class SubTask implements Serializable {
    @OneToMany(mappedBy = "subTask")
    private List<SubTaskStatus> subTaskStatusList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "subTaskID")
    private Integer subTaskID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "subTaskName")
    private String subTaskName;
    @Column(name = "subTaskNumber")
    private Integer subTaskNumber;
    @JoinColumn(name = "taskID", referencedColumnName = "taskID")
    @ManyToOne(optional = false)
    private Task task;

    public SubTask() {
    }

    public SubTask(Integer subTaskID) {
        this.subTaskID = subTaskID;
    }

    public SubTask(Integer subTaskID, String subTaskName) {
        this.subTaskID = subTaskID;
        this.subTaskName = subTaskName;
    }

    public Integer getSubTaskID() {
        return subTaskID;
    }

    public void setSubTaskID(Integer subTaskID) {
        this.subTaskID = subTaskID;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public Integer getSubTaskNumber() {
        return subTaskNumber;
    }

    public void setSubTaskNumber(Integer subTaskNumber) {
        this.subTaskNumber = subTaskNumber;
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
        hash += (subTaskID != null ? subTaskID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubTask)) {
            return false;
        }
        SubTask other = (SubTask) object;
        if ((this.subTaskID == null && other.subTaskID != null) || (this.subTaskID != null && !this.subTaskID.equals(other.subTaskID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.SubTask[ subTaskID=" + subTaskID + " ]";
    }

    public List<SubTaskStatus> getSubTaskStatusList() {
        return subTaskStatusList;
    }

    public void setSubTaskStatusList(List<SubTaskStatus> subTaskStatusList) {
        this.subTaskStatusList = subTaskStatusList;
    }
    
}
