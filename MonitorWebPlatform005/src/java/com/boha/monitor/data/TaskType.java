/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "taskType")
@NamedQueries({
    @NamedQuery(name = "TaskType.findByProgramme", 
            query = "SELECT t FROM TaskType t WHERE t.programme.programmeID = :programmeID ORDER BY t.taskTypeName"),
    
})
public class TaskType implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskType")
    private List<MonitorTrade> monitorTradeList;
    
    
    @JoinColumn(name = "programmeID", referencedColumnName = "programmeID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Programme programme;
       
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taskTypeID")
    private Integer taskTypeID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "taskTypeName")
    private String taskTypeName;
    
    @Basic(optional = true)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "sectionName")
    private String sectionName;
    
    @OneToMany(mappedBy = "taskType")
    private List<Task> taskList;

    public TaskType() {
    }

    public TaskType(Integer taskTypeID) {
        this.taskTypeID = taskTypeID;
    }

    public TaskType(Integer taskTypeID, String taskTypeName) {
        this.taskTypeID = taskTypeID;
        this.taskTypeName = taskTypeName;
    }

    public Integer getTaskTypeID() {
        return taskTypeID;
    }

    public void setTaskTypeID(Integer taskTypeID) {
        this.taskTypeID = taskTypeID;
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskTypeID != null ? taskTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskType)) {
            return false;
        }
        TaskType other = (TaskType) object;
        if ((this.taskTypeID == null && other.taskTypeID != null) || (this.taskTypeID != null && !this.taskTypeID.equals(other.taskTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.TaskType[ taskTypeID=" + taskTypeID + " ]";
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public List<MonitorTrade> getMonitorTradeList() {
        return monitorTradeList;
    }

    public void setMonitorTradeList(List<MonitorTrade> monitorTradeList) {
        this.monitorTradeList = monitorTradeList;
    }

    

}
