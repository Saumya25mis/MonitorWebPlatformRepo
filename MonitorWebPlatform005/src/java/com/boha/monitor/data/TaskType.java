/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "TaskType.findAll", query = "SELECT t FROM TaskType t"),
    @NamedQuery(name = "TaskType.findByTaskTypeID", query = "SELECT t FROM TaskType t WHERE t.taskTypeID = :taskTypeID"),
    @NamedQuery(name = "TaskType.findByTaskTypeName", query = "SELECT t FROM TaskType t WHERE t.taskTypeName = :taskTypeName"),
    @NamedQuery(name = "TaskType.findByProgrammeID", query = "SELECT t FROM TaskType t WHERE t.programmeID = :programmeID"),
    @NamedQuery(name = "TaskType.findBySectionName", query = "SELECT t FROM TaskType t WHERE t.sectionName = :sectionName"),
    @NamedQuery(name = "TaskType.findByTaskTypeNumber", query = "SELECT t FROM TaskType t WHERE t.taskTypeNumber = :taskTypeNumber")})
public class TaskType implements Serializable {
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
    @Column(name = "programmeID")
    private Integer programmeID;
    @Size(max = 512)
    @Column(name = "sectionName")
    private String sectionName;
    @Column(name = "taskTypeNumber")
    private Integer taskTypeNumber;

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

    public Integer getProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(Integer programmeID) {
        this.programmeID = programmeID;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Integer getTaskTypeNumber() {
        return taskTypeNumber;
    }

    public void setTaskTypeNumber(Integer taskTypeNumber) {
        this.taskTypeNumber = taskTypeNumber;
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
    
}
