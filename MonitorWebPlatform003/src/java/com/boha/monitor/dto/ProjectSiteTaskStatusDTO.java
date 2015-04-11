/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.CompanyStaff;
import com.boha.monitor.data.Project;
import com.boha.monitor.data.ProjectSiteTask;
import com.boha.monitor.data.ProjectSiteTaskStatus;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class ProjectSiteTaskStatusDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer projectSiteTaskStatusID;
    private Date dateUpdated;
    private Date statusDate;
    private TaskStatusDTO taskStatus;
    private TaskDTO task;
    private Integer projectSiteTaskID, projectID, projectSiteID;
    private Integer companyStaffID;
    private String projectSiteName, projectName, staffName;
    private List<SubTaskStatusDTO> subTaskStatusList;

    public ProjectSiteTaskStatusDTO() {
    }

    public ProjectSiteTaskStatusDTO(ProjectSiteTaskStatus a) {
        this.projectSiteTaskStatusID = a.getProjectSiteTaskStatusID();
        ProjectSiteTask pst = a.getProjectSiteTask();
        this.task = new TaskDTO(pst.getTask());
        this.projectSiteName = pst.getProjectSite().getProjectSiteName();
        this.projectSiteID = pst.getProjectSite().getProjectSiteID();
        this.projectSiteTaskID = pst.getProjectSiteTaskID();
        this.taskStatus = new TaskStatusDTO(a.getTaskStatus());
        this.dateUpdated = a.getDateUpdated();
        this.statusDate = a.getStatusDate();
        
        if (a.getCompanyStaff() != null) {
            CompanyStaff cs = a.getCompanyStaff();
            this.companyStaffID = cs.getCompanyStaffID();
            this.staffName = cs.getFirstName() + " " + cs.getLastName();
        }
        
        Project p = pst.getProjectSite().getProject();
        if (p != null) {
            projectID = p.getProjectID();
            //projectName = p.getProjectName();
        }
    }

    public Integer getProjectSiteID() {
        return projectSiteID;
    }

    public void setProjectSiteID(Integer projectSiteID) {
        this.projectSiteID = projectSiteID;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public List<SubTaskStatusDTO> getSubTaskStatusList() {
        return subTaskStatusList;
    }

    public void setSubTaskStatusList(List<SubTaskStatusDTO> subTaskStatusList) {
        this.subTaskStatusList = subTaskStatusList;
    }

    public TaskDTO getTask() {
        return task;
    }

    public void setTask(TaskDTO task) {
        this.task = task;
    }

    public Integer getCompanyStaffID() {
        return companyStaffID;
    }

    public void setCompanyStaffID(Integer companyStaffID) {
        this.companyStaffID = companyStaffID;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getProjectSiteName() {
        return projectSiteName;
    }

    public void setProjectSiteName(String projectSiteName) {
        this.projectSiteName = projectSiteName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getProjectSiteTaskStatusID() {
        return projectSiteTaskStatusID;
    }

    public void setProjectSiteTaskStatusID(Integer projectSiteTaskStatusID) {
        this.projectSiteTaskStatusID = projectSiteTaskStatusID;
    }

    public TaskStatusDTO getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatusDTO taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getProjectSiteTaskID() {
        return projectSiteTaskID;
    }

    public void setProjectSiteTaskID(Integer projectSiteTaskID) {
        this.projectSiteTaskID = projectSiteTaskID;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ProjectSiteTaskStatus[ projectSiteTaskStatusID=" + projectSiteTaskStatusID + " ]";
    }

}
