/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.dto;

import com.boha.monitor.data.Task;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class TaskDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer taskID, companyID, taskNumber;
    private String taskName;
    private String description;
    private List<TaskPriceDTO> taskPriceList = new ArrayList<>();
    private List<SubTaskDTO> subTaskList;

    public TaskDTO() {
    }

    public TaskDTO(Task a) {
        this.taskID = a.getTaskID();
        this.taskName = a.getTaskName();
        this.description = a.getDescription();
        this.companyID = a.getCompany().getCompanyID();
        this.taskNumber = a.getTaskNumber();
        if (a.getSubTaskList() != null) {
            
        }
        
    }

    public List<SubTaskDTO> getSubTaskList() {
        return subTaskList;
    }

    public void setSubTaskList(List<SubTaskDTO> subTaskList) {
        this.subTaskList = subTaskList;
    }

    
    public List<TaskPriceDTO> getTaskPriceList() {
        return taskPriceList;
    }

    public void setTaskPriceList(List<TaskPriceDTO> taskPriceList) {
        this.taskPriceList = taskPriceList;
    }

  

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
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
