/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author aubreyM
 */
public class TaskPriceDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer taskPriceID;
    private Date startDate;
    private Date endDate;
    private double price;
    String taskName;
    private Integer taskID, projectID;

    public TaskPriceDTO(TaskPrice a) {
        taskPriceID = a.getTaskPriceID();
        startDate = a.getStartDate();
        endDate = a.getEndDate();
        price = a.getPrice();
        taskID = a.getTask().getTaskID();
        taskName = a.getTask().getTaskName();
        if (a.getProject() != null) {
            projectID = a.getProject().getProjectID();
        }
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
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

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
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
        if (!(object instanceof TaskPriceDTO)) {
            return false;
        }
        TaskPriceDTO other = (TaskPriceDTO) object;
        if ((this.taskPriceID == null && other.taskPriceID != null) || (this.taskPriceID != null && !this.taskPriceID.equals(other.taskPriceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.TaskPrice[ taskPriceID=" + taskPriceID + " ]";
    }

}
