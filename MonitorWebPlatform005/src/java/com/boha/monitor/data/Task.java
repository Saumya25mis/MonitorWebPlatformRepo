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
import javax.persistence.Lob;
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
@Table(name = "task")
@NamedQueries({
    @NamedQuery(name = "Task.findByProgramme", 
            query = "SELECT t FROM Task t where t.taskType.programme.programmeID = :programmeID "
                    + "order by t.taskType.taskTypeName, t.taskName"),
    @NamedQuery(name = "Task.findByType", 
            query = "SELECT t FROM Task t where t.taskType.taskTypeID = :taskTypeID"
                    + " order by t.taskType.taskTypeName, t.taskName")
    })
public class Task implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private List<SubTask> subTaskList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task", fetch = FetchType.LAZY)
    private List<ProjectTask> projectTaskList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taskID")
    private Integer taskID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "taskName")
    private String taskName;
    @Column(name = "taskNumber")
    private Integer taskNumber;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    
    @JoinColumn(name = "taskTypeID", referencedColumnName = "taskTypeID")
    @ManyToOne
    private TaskType taskType;

    public Task() {
    }


    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public Task(Integer taskID) {
        this.taskID = taskID;
    }

    public Task(Integer taskID, String taskName) {
        this.taskID = taskID;
        this.taskName = taskName;
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskID != null ? taskID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.taskID == null && other.taskID != null) || (this.taskID != null && !this.taskID.equals(other.taskID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Task[ taskID=" + taskID + " ]";
    }

    public List<ProjectTask> getProjectTaskList() {
        return projectTaskList;
    }

    public void setProjectTaskList(List<ProjectTask> projectTaskList) {
        this.projectTaskList = projectTaskList;
    }

    public List<SubTask> getSubTaskList() {
        return subTaskList;
    }

    public void setSubTaskList(List<SubTask> subTaskList) {
        this.subTaskList = subTaskList;
    }
    
}
