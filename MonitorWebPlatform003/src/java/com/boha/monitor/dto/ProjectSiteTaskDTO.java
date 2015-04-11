/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.ProjectSiteTask;
import com.boha.monitor.dto.transfer.PhotoUploadDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class ProjectSiteTaskDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer projectSiteTaskID, statusCount;
    private TaskDTO task;
    private long dateRegistered;
    private Integer projectSiteID, projectID;
    private List<ProjectSiteTaskStatusDTO> projectSiteTaskStatusList = new ArrayList<>();
    private List<SubTaskStatusDTO> subTaskStatusList = new ArrayList<>();
    private List<PhotoUploadDTO> photoUploadList = new ArrayList<>();
    private String projectSiteName;

    public ProjectSiteTaskDTO() {
    }

    public ProjectSiteTaskDTO(ProjectSiteTask a) {
        this.projectID = a.getProjectSite().getProject().getProjectID();
        this.projectSiteName = a.getProjectSite().getProjectSiteName();
        this.projectSiteTaskID = a.getProjectSiteTaskID();
        this.task = new TaskDTO(a.getTask());
        this.dateRegistered = a.getDateRegistered().getTime();
        this.projectSiteID = a.getProjectSite().getProjectSiteID();
        
       
        
    }

    public List<SubTaskStatusDTO> getSubTaskStatusList() {
        return subTaskStatusList;
    }

    public void setSubTaskStatusList(List<SubTaskStatusDTO> subTaskStatusList) {
        this.subTaskStatusList = subTaskStatusList;
    }

    public String getProjectSiteName() {
        return projectSiteName;
    }

    public void setProjectSiteName(String projectSiteName) {
        this.projectSiteName = projectSiteName;
    }

    public Integer getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(Integer statusCount) {
        this.statusCount = statusCount;
    }

    public List<PhotoUploadDTO> getPhotoUploadList() {
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUploadDTO> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getProjectSiteTaskID() {
        return projectSiteTaskID;
    }

    public TaskDTO getTask() {
        return task;
    }

    public void setTask(TaskDTO task) {
        this.task = task;
    }

    public void setProjectSiteTaskID(Integer projectSiteTaskID) {
        this.projectSiteTaskID = projectSiteTaskID;
    }

    public long getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(long dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Integer getProjectSiteID() {
        return projectSiteID;
    }

    public void setProjectSiteID(Integer projectSiteID) {
        this.projectSiteID = projectSiteID;
    }

    public List<ProjectSiteTaskStatusDTO> getProjectSiteTaskStatusList() {
        return projectSiteTaskStatusList;
    }

    public void setProjectSiteTaskStatusList(List<ProjectSiteTaskStatusDTO> projectSiteTaskStatusList) {
        this.projectSiteTaskStatusList = projectSiteTaskStatusList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectSiteTaskID != null ? projectSiteTaskID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectSiteTaskDTO)) {
            return false;
        }
        ProjectSiteTaskDTO other = (ProjectSiteTaskDTO) object;
        if ((this.projectSiteTaskID == null && other.projectSiteTaskID != null) || (this.projectSiteTaskID != null && !this.projectSiteTaskID.equals(other.projectSiteTaskID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ProjectSiteTask[ projectSiteTaskID=" + projectSiteTaskID + " ]";
    }

}
