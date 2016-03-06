/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author aubreyM
 */
//
@Entity
@Table(name = "projectTask")
@NamedQueries({
    @NamedQuery(name = "ProjectTask.findByProjectTask", 
            query = "SELECT p FROM ProjectTask p where p.project.projectID = :projectID and p.task.taskID = :taskID"),
    @NamedQuery(name = "ProjectTask.findByMonitor", 
            query = "SELECT p FROM ProjectTask p, MonitorProject s where s.monitor.monitorID = :monitorID and s.project.projectID = p.project.projectID and s.activeFlag = :activeFlag order by p.project.projectID"),
    
    @NamedQuery(name = "ProjectTask.findByStaff", 
            query = "SELECT p FROM ProjectTask p, StaffProject s where s.staff.staffID = :staffID and s.project.projectID = p.project.projectID and s.activeFlag = :activeFlag order by p.project.projectID"),
    @NamedQuery(name = "ProjectTask.findByCompany", 
            query = "SELECT p FROM ProjectTask p where p.project.company.companyID = :companyID"),
    @NamedQuery(name = "ProjectTask.deleteByCompany", 
            query = "DELETE FROM ProjectTask p where p.project.company.companyID = :companyID"),
    @NamedQuery(name = "ProjectTask.findByProject", 
            query = "SELECT p FROM ProjectTask p where p.project.projectID = :projectID order by p.task.taskName")
    
})
public class ProjectTask implements Serializable {
    @OneToMany(mappedBy = "projectTask", fetch = FetchType.EAGER)
    @OrderBy("dateTaken desc")
    private List<VideoUpload> videoUploadList;
    
    @JoinColumn(name = "taskID", referencedColumnName = "taskID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Task task;
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "projectTaskID")
    private Integer projectTaskID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Project project;
    
    @OneToMany(mappedBy = "projectTask", fetch = FetchType.EAGER)
    @OrderBy("dateTaken desc")
    private List<PhotoUpload> photoUploadList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectTask", fetch = FetchType.EAGER)
    @OrderBy("dateUpdated desc")
    private List<ProjectTaskStatus> projectTaskStatusList;

    
    public ProjectTask() {
    }

    public ProjectTask(Integer projectTaskID) {
        this.projectTaskID = projectTaskID;
    }

    public ProjectTask(Integer projectTaskID, Date dateRegistered) {
        this.projectTaskID = projectTaskID;
        this.dateRegistered = dateRegistered;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Integer getProjectTaskID() {
        return projectTaskID;
    }

    public void setProjectTaskID(Integer projectTaskID) {
        this.projectTaskID = projectTaskID;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

   
   
    public List<PhotoUpload> getPhotoUploadList() {
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUpload> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public List<ProjectTaskStatus> getProjectTaskStatusList() {
        return projectTaskStatusList;
    }

    public void setProjectTaskStatusList(List<ProjectTaskStatus> projectTaskStatusList) {
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
        if (!(object instanceof ProjectTask)) {
            return false;
        }
        ProjectTask other = (ProjectTask) object;
        if ((this.projectTaskID == null && other.projectTaskID != null) || (this.projectTaskID != null && !this.projectTaskID.equals(other.projectTaskID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ProjectTask[ projectTaskID=" + projectTaskID + " ]";
    }

    public List<VideoUpload> getVideoUploadList() {
        return videoUploadList;
    }

    public void setVideoUploadList(List<VideoUpload> videoUploadList) {
        this.videoUploadList = videoUploadList;
    }

  
    
}
