/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.dto;

import com.boha.monitor.data.*;
import java.io.Serializable;

/**
 *
 * @author aubreyM
 */
public class SubTaskDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer subTaskID;
    private String subTaskName;
    private Integer subTaskNumber;
    private String desc;
    private Integer taskID;

    public SubTaskDTO() {
    }

    public SubTaskDTO(Integer subTaskID) {
        this.subTaskID = subTaskID;
    }

    public SubTaskDTO(SubTask a) {
        this.subTaskID = a.getSubTaskID();
        this.subTaskName = a.getSubTaskName();
        desc = a.getDescription();
        taskID = a.getTask().getTaskID();
        subTaskNumber = a.getSubTaskNumber();
    }

    public Integer getTaskID() {
        return taskID;
    }

    public void setTaskID(Integer taskID) {
        this.taskID = taskID;
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

    public String getDescription() {
        return desc;
    }

    public void setDescription(String description) {
        this.desc = description;
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
        if (!(object instanceof SubTaskDTO)) {
            return false;
        }
        SubTaskDTO other = (SubTaskDTO) object;
        if ((this.subTaskID == null && other.subTaskID != null) || (this.subTaskID != null && !this.subTaskID.equals(other.subTaskID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.SubTask[ subTaskID=" + subTaskID + " ]";
    }
    
}
