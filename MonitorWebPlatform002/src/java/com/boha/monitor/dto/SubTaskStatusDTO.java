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
public class SubTaskStatusDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer subTaskStatusID;
    private Date statusDate;
    private Date dateUpdated;
    private TaskStatusDTO taskStatus;
    private Integer subTaskID, projectSiteTaskStatusID;
    private Integer companyStaffID;
    private String staffName, subTaskName;

    public SubTaskStatusDTO() {
    }

    public SubTaskStatusDTO(SubTaskStatus a) {
        this.subTaskStatusID = a.getSubTaskStatusID();
        this.subTaskID = a.getSubTask().getSubTaskID();
        this.subTaskName = a.getSubTask().getSubTaskName();
        this.projectSiteTaskStatusID = a.getProjectSiteTaskStatus().getProjectSiteTaskStatusID();
        this.statusDate = a.getStatusDate();
        this.dateUpdated = a.getDateUpdated();
        this.taskStatus = new TaskStatusDTO(a.getTaskStatus());
        CompanyStaff cs = a.getCompanyStaff();
        this.companyStaffID = cs.getCompanyStaffID();
        this.staffName = cs.getFirstName() + " " + cs.getLastName();
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public Integer getProjectSiteTaskStatusID() {
        return projectSiteTaskStatusID;
    }

    public void setProjectSiteTaskStatusID(Integer projectSiteTaskStatusID) {
        this.projectSiteTaskStatusID = projectSiteTaskStatusID;
    }

    
    public Integer getSubTaskStatusID() {
        return subTaskStatusID;
    }

    public void setSubTaskStatusID(Integer subTaskStatusID) {
        this.subTaskStatusID = subTaskStatusID;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public TaskStatusDTO getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatusDTO taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getSubTaskID() {
        return subTaskID;
    }

    public void setSubTaskID(Integer subTaskID) {
        this.subTaskID = subTaskID;
    }

    public Integer getCompanyStaffID() {
        return companyStaffID;
    }

    public void setCompanyStaffID(Integer companyStaffID) {
        this.companyStaffID = companyStaffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subTaskStatusID != null ? subTaskStatusID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubTaskStatusDTO)) {
            return false;
        }
        SubTaskStatusDTO other = (SubTaskStatusDTO) object;
        if ((this.subTaskStatusID == null && other.subTaskStatusID != null) || (this.subTaskStatusID != null && !this.subTaskStatusID.equals(other.subTaskStatusID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.SubTaskStatus[ subTaskStatusID=" + subTaskStatusID + " ]";
    }
    
}
