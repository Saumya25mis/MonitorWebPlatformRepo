/*
 * To change this license header, choose License Headers in ProjectDTO Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.ProjectTask;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author aubreyM
 */
public class ProjectTaskDTO implements Serializable, Comparable<ProjectTaskDTO> {

    private static final long serialVersionUID = 1L;
    private Integer projectTaskID;
    private Long dateRegistered;
    private Integer projectID, statusCount = 0, photoCount = 0;
    private TaskDTO task;
    private Double latitude, longitude;
    private String projectName;
    private List<PhotoUploadDTO> photoUploadList;
    private List<ProjectTaskStatusDTO> projectTaskStatusList;
    private ProjectTaskStatusDTO lastStatus;
    private PhotoUploadDTO lastPhoto;

    public ProjectTaskDTO() {
    }

    public ProjectTaskDTO(ProjectTask a) {
        setup(a);

    }
    
    private void setup(ProjectTask a) {
        this.projectTaskID = a.getProjectTaskID();
        if (a.getDateRegistered() != null) {
            this.dateRegistered = a.getDateRegistered().getTime();
        }
        if (a.getProject() != null) {
            projectID = a.getProject().getProjectID();
            latitude = a.getProject().getLatitude();
            longitude = a.getProject().getLongitude();
        }

        if (a.getTask() != null) {
            task = new TaskDTO(a.getTask());
        }

        if (a.getProjectTaskStatusList() != null) {
            statusCount = a.getProjectTaskStatusList().size();
            if (!a.getProjectTaskStatusList().isEmpty()) {
                lastStatus = new ProjectTaskStatusDTO(a.getProjectTaskStatusList().get(0));
            }
        }
        if (a.getPhotoUploadList() != null) {
            photoCount = a.getPhotoUploadList().size();
            if (!a.getPhotoUploadList().isEmpty()) {
                lastPhoto = new PhotoUploadDTO(a.getPhotoUploadList().get(0));
            }
        }
    }
    public PhotoUploadDTO getLastPhoto() {
        return lastPhoto;
    }

    public void setLastPhoto(PhotoUploadDTO lastPhoto) {
        this.lastPhoto = lastPhoto;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public ProjectTaskStatusDTO getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(ProjectTaskStatusDTO lastStatus) {
        this.lastStatus = lastStatus;
    }

    public Integer getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(Integer statusCount) {
        this.statusCount = statusCount;
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
        if (photoUploadList == null) {
            photoUploadList = new ArrayList<>();
        }
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUploadDTO> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public List<ProjectTaskStatusDTO> getProjectTaskStatusList() {
        if (projectTaskStatusList == null) {
            projectTaskStatusList = new ArrayList<>();
        }
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

    @Override
    public int compareTo(ProjectTaskDTO o) {
        return this.task.getTaskName().compareTo(o.getTask().getTaskName());
    }

}
