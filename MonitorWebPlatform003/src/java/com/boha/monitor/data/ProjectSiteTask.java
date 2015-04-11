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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "projectSiteTask")
@NamedQueries({
    @NamedQuery(name = "ProjectSiteTask.findByCompany", 
            query = "SELECT p FROM ProjectSiteTask p "
                    + "where p.projectSite.project.company.companyID = :companyID "
                    + "order by p.projectSite.project.dateRegistered desc"),
    @NamedQuery(name = "ProjectSiteTask.findByProject", 
            query = "SELECT p FROM ProjectSiteTask p where p.projectSite.project.projectID = :projectID order by p.projectSite.projectSiteName"),
    @NamedQuery(name = "ProjectSiteTask.findByProjectSite", 
            query = "SELECT p FROM ProjectSiteTask p "
                    + "WHERE p.projectSite.projectSiteID = :projectSiteID order by p.dateRegistered desc"),
    @NamedQuery(name = "ProjectSiteTask.findByDateRegistered", query = "SELECT p FROM ProjectSiteTask p WHERE p.dateRegistered = :dateRegistered")})
public class ProjectSiteTask implements Serializable {
    @OneToMany(mappedBy = "projectSiteTask")
    private List<SubTaskStatus> subTaskStatusList;
    @OneToMany(mappedBy = "projectSiteTask")
    private List<InvoiceItem> invoiceItemList;
    @OneToMany(mappedBy = "projectSiteTask")
    private List<PhotoUpload> photoUploadList;
    @JoinColumn(name = "taskID", referencedColumnName = "taskID")
    @ManyToOne(optional = false)
    private Task task;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "projectSiteTaskID")
    private Integer projectSiteTaskID;
   
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @JoinColumn(name = "projectSiteID", referencedColumnName = "projectSiteID")
    @ManyToOne(optional = false)
    private ProjectSite projectSite;
    @OneToMany(cascade = CascadeType.ALL, 
            mappedBy = "projectSiteTask")
    private List<ProjectSiteTaskStatus> projectSiteTaskStatusList;

    public ProjectSiteTask() {
    }

    public ProjectSiteTask(Integer projectSiteTaskID) {
        this.projectSiteTaskID = projectSiteTaskID;
    }

   

    public Integer getProjectSiteTaskID() {
        return projectSiteTaskID;
    }

    public void setProjectSiteTaskID(Integer projectSiteTaskID) {
        this.projectSiteTaskID = projectSiteTaskID;
    }

   

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public ProjectSite getProjectSite() {
        return projectSite;
    }

    public void setProjectSite(ProjectSite projectSite) {
        this.projectSite = projectSite;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }


    public List<ProjectSiteTaskStatus> getProjectSiteTaskStatusList() {
        return projectSiteTaskStatusList;
    }

    public void setProjectSiteTaskStatusList(List<ProjectSiteTaskStatus> projectSiteTaskStatusList) {
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
        if (!(object instanceof ProjectSiteTask)) {
            return false;
        }
        ProjectSiteTask other = (ProjectSiteTask) object;
        if ((this.projectSiteTaskID == null && other.projectSiteTaskID != null) || (this.projectSiteTaskID != null && !this.projectSiteTaskID.equals(other.projectSiteTaskID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ProjectSiteTask[ projectSiteTaskID=" + projectSiteTaskID + " ]";
    }

    public List<PhotoUpload> getPhotoUploadList() {
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUpload> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public List<InvoiceItem> getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(List<InvoiceItem> invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
    }

    public List<SubTaskStatus> getSubTaskStatusList() {
        return subTaskStatusList;
    }

    public void setSubTaskStatusList(List<SubTaskStatus> subTaskStatusList) {
        this.subTaskStatusList = subTaskStatusList;
    }

   
    
}
