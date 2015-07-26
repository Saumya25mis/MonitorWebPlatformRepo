/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.TaskType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class TaskTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer taskTypeID;
    private String taskTypeName, sectionName;
    private Integer programmeID;
    private List<TaskDTO> taskList = new ArrayList<>();

    public TaskTypeDTO() {
    }

    public TaskTypeDTO(TaskType a) {
        this.taskTypeID = a.getTaskTypeID();
        this.taskTypeName = a.getTaskTypeName();
        sectionName = a.getSectionName();
       
        if (a.getProgramme() != null) {
            programmeID = a.getProgramme().getProgrammeID();
        }

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

    public List<TaskDTO> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskDTO> taskList) {
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
        if (!(object instanceof TaskTypeDTO)) {
            return false;
        }
        TaskTypeDTO other = (TaskTypeDTO) object;
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
