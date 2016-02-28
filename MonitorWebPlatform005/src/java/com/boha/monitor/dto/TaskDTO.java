/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.Task;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class TaskDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer taskID;
    private String taskName, programmeName, taskTypeName;
    private Integer taskNumber;
    private String desc;
    private List<ProjectTaskDTO> projectTaskList;
    private List<SubTaskDTO> subTaskList;
    private Integer companyID;
    private Boolean selected;

    public TaskDTO() {
    }

    public TaskDTO(Task a) {
        this.taskID = a.getTaskID();
        this.taskName = a.getTaskName();
        desc = a.getDescription();
        taskNumber = a.getTaskNumber();
        if (a.getCompany() != null) {
            companyID = a.getCompany().getCompanyID();
        }
       
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    
    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public String getTaskTypeName() {
        return taskTypeName;
    }

    public void setTaskTypeName(String taskTypeName) {
        this.taskTypeName = taskTypeName;
    }

 

    public List<SubTaskDTO> getSubTaskList() {
        return subTaskList;
    }

    public void setSubTaskList(List<SubTaskDTO> subTaskList) {
        this.subTaskList = subTaskList;
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
        return desc;
    }

    public void setDescription(String description) {
        this.desc = description;
    }

    public List<ProjectTaskDTO> getProjectTaskList() {
        return projectTaskList;
    }

    public void setProjectTaskList(List<ProjectTaskDTO> projectTaskList) {
        this.projectTaskList = projectTaskList;
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
        if (!(object instanceof TaskDTO)) {
            return false;
        }
        TaskDTO other = (TaskDTO) object;
        if ((this.taskID == null && other.taskID != null) || (this.taskID != null && !this.taskID.equals(other.taskID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Task[ taskID=" + taskID + " ]";
    }

}
