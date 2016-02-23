/*
 * To change this license header, choose License Headers in ProjectDTO Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.PhotoUpload;
import com.boha.monitor.data.ProjectTask;
import com.boha.monitor.data.ProjectTaskStatus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class ProjectTaskDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer projectTaskID;
    private Long dateRegistered;
    private Integer projectID;
    private TaskDTO task;
    private Double latitude, longitude;
    private String projectName;
    private List<PhotoUploadDTO> photoUploadList;
    private List<ProjectTaskStatusDTO> projectTaskStatusList;

    public ProjectTaskDTO() {
    }

    public ProjectTaskDTO(ProjectTask a) {
        this.projectTaskID = a.getProjectTaskID();
        if (a.getDateRegistered() != null) {
            this.dateRegistered = a.getDateRegistered().getTime();
        }
        if (a.getProject() != null) {
            projectID = a.getProject().getProjectID();
//            projectName = a.getProject().getProjectName();
            latitude = a.getProject().getLatitude();
            longitude = a.getProject().getLongitude();
        }

        if (a.getTask() != null) {
            task = new TaskDTO(a.getTask());
        }

        
        if (a.getProjectTaskStatusList() != null) {
            projectTaskStatusList = new ArrayList<>();
            for (ProjectTaskStatus ps : a.getProjectTaskStatusList()) {
                projectTaskStatusList.add(new ProjectTaskStatusDTO(ps));
            }
        }
        if (a.getPhotoUploadList() != null) {
            photoUploadList = new ArrayList<>();
            for (PhotoUpload p : a.getPhotoUploadList()) {
                photoUploadList.add(new PhotoUploadDTO(p));
            }
        }

    }

    public TaskDTO getTask() {
        return task;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setTask(TaskDTO task) {
        this.task = task;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getProjectTaskID() {
        return projectTaskID;
    }

    public void setProjectTaskID(Integer projectTaskID) {
        this.projectTaskID = projectTaskID;
    }

    public Long getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Long dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public List<PhotoUploadDTO> getPhotoUploadList() {
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUploadDTO> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public List<ProjectTaskStatusDTO> getProjectTaskStatusList() {
        return projectTaskStatusList;
    }

    public void setProjectTaskStatusList(List<ProjectTaskStatusDTO> projectTaskStatusList) {
        this.projectTaskStatusList = projectTaskStatusList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectTaskID != null ? projectTaskID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectTaskDTO)) {
            return false;
        }
        ProjectTaskDTO other = (ProjectTaskDTO) object;
        if ((this.projectTaskID == null && other.projectTaskID != null) || (this.projectTaskID != null && !this.projectTaskID.equals(other.projectTaskID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ProjectTask[ projectTaskID=" + projectTaskID + " ]";
    }

}
