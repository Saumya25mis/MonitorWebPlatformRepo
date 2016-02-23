/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.PhotoUpload;
import com.boha.monitor.data.ProjectTaskStatus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class ProjectTaskStatusDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer projectTaskStatusID;
    private Long statusDate;
    private Long dateUpdated;
    private Integer projectTaskID;
    private TaskStatusTypeDTO taskStatusType;
    private Integer staffID;
    private Integer monitorID;
    private String staffName, monitorName, taskName;
    private List<PhotoUploadDTO> photoUploadList;

    public ProjectTaskStatusDTO() {
    }

    public ProjectTaskStatusDTO(ProjectTaskStatus a) {
        this.projectTaskStatusID = a.getProjectTaskStatusID();
        if (a.getDateUpdated() != null) {
            this.dateUpdated = a.getDateUpdated().getTime();
        }
        if (a.getStatusDate() != null) {
            statusDate = a.getStatusDate().getTime();
        }
        if (a.getProjectTask() != null) {
            projectTaskID = a.getProjectTask().getProjectTaskID();
            taskName = a.getProjectTask().getTask().getTaskName();
        }
        if (a.getTaskStatusType() != null) {
            taskStatusType = new TaskStatusTypeDTO(a.getTaskStatusType());
        }
        if (a.getStaff() != null) {
            staffID = a.getStaff().getStaffID();
            staffName = a.getStaff().getFirstName() + " " + a.getStaff().getLastName();
        }
        if (a.getMonitor() != null) {
            monitorID = a.getMonitor().getMonitorID();
            monitorName = a.getMonitor().getFirstName() + " " + a.getMonitor().getLastName();
        }
        photoUploadList = new ArrayList<>();
        if (a.getPhotoUploadList() != null) {
            for (PhotoUpload p : a.getPhotoUploadList()) {
                photoUploadList.add(new PhotoUploadDTO(p));
            }
        }
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public List<PhotoUploadDTO> getPhotoUploadList() {
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUploadDTO> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    public Integer getProjectTaskStatusID() {
        return projectTaskStatusID;
    }

    public void setProjectTaskStatusID(Integer projectTaskStatusID) {
        this.projectTaskStatusID = projectTaskStatusID;
    }

    public Integer getProjectTaskID() {
        return projectTaskID;
    }

    public void setProjectTaskID(Integer projectTaskID) {
        this.projectTaskID = projectTaskID;
    }

    public TaskStatusTypeDTO getTaskStatusType() {
        return taskStatusType;
    }

    public void setTaskStatusType(TaskStatusTypeDTO taskStatusType) {
        this.taskStatusType = taskStatusType;
    }

    public Long getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Long statusDate) {
        this.statusDate = statusDate;
    }

    public Long getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Long dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public Integer getMonitorID() {
        return monitorID;
    }

    public void setMonitorID(Integer monitorID) {
        this.monitorID = monitorID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectTaskStatusID != null ? projectTaskStatusID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectTaskStatusDTO)) {
            return false;
        }
        ProjectTaskStatusDTO other = (ProjectTaskStatusDTO) object;
        if ((this.projectTaskStatusID == null && other.projectTaskStatusID != null) || (this.projectTaskStatusID != null && !this.projectTaskStatusID.equals(other.projectTaskStatusID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ProjectTaskStatus[ projectTaskStatusID=" + projectTaskStatusID + " ]";
    }

}
