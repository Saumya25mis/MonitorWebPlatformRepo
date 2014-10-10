/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.dto;

import com.boha.monitor.data.Task;
import java.io.Serializable;

/**
 *
 * @author aubreyM
 */
public class TaskDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer taskID, companyID;
    private String taskName;
    private String description;

    public TaskDTO() {
    }

    public TaskDTO(Task a) {
        this.taskID = a.getTaskID();
        this.taskName = a.getTaskName();
        this.description = a.getDescription();
        this.companyID = a.getCompany().getCompanyID();
    }

    public TaskDTO(Integer taskID, String taskName, String description) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
}
