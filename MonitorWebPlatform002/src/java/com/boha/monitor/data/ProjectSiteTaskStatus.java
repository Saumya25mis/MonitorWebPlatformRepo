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
@Table(name = "projectSiteTaskStatus")
@NamedQueries({
    @NamedQuery(name = "ProjectSiteTaskStatus.findbyTask",
            query = "SELECT p FROM ProjectSiteTaskStatus p where p.projectSiteTask.projectSiteTaskID = :id order by p.dateUpdated desc"),
    @NamedQuery(name = "ProjectSiteTaskStatus.findByProject",
            query = "SELECT p FROM ProjectSiteTaskStatus p WHERE p.projectSiteTask.projectSite.project.projectID = :projectID order by p.dateUpdated desc"),
    @NamedQuery(name = "ProjectSiteTaskStatus.findByProjectSite",
            query = "SELECT p FROM ProjectSiteTaskStatus p WHERE p.projectSiteTask.projectSite.projectSiteID = :projectSiteID order by p.dateUpdated desc"),
    @NamedQuery(name = "ProjectSiteTaskStatus.findByCompany",
            query = "SELECT p FROM ProjectSiteTaskStatus p "
            + "WHERE p.projectSiteTask.projectSite.project.company.companyID = :companyID "
            + "order by p.statusDate desc"),
    
    @NamedQuery(name = "ProjectSiteTaskStatus.findByCompanyInPeriod",
            query = "SELECT p FROM ProjectSiteTaskStatus p WHERE p.projectSiteTask.projectSite.project.company.companyID = :companyID "
            + "and p.statusDate between :startDate and :endDate "
            + "order by p.statusDate desc"),
        
    @NamedQuery(name = "ProjectSiteTaskStatus.countByCompanyInPeriod",
            query = "SELECT count(p) FROM ProjectSiteTaskStatus p WHERE p.projectSiteTask.projectSite.project.company.companyID = :companyID "
            + "and p.statusDate between :startDate and :endDate "
            + "order by p.statusDate desc"),
    @NamedQuery(name = "ProjectSiteTaskStatus.findByProjectInPeriod",
            query = "SELECT p FROM ProjectSiteTaskStatus p WHERE p.projectSiteTask.projectSite.project.projectID = :projectID "
            + "and p.statusDate between :startDate and :endDate "
            + "order by p.statusDate desc"),
    @NamedQuery(name = "ProjectSiteTaskStatus.countByProjectInPeriod",
            query = "SELECT count(p) FROM ProjectSiteTaskStatus p WHERE p.projectSiteTask.projectSite.project.projectID = :projectID "
            + "and p.statusDate between :startDate and :endDate "
            + "order by p.dateUpdated desc"),
    @NamedQuery(name = "ProjectSiteTaskStatus.findByProjectSiteInPeriod",
            query = "SELECT p FROM ProjectSiteTaskStatus p WHERE p.projectSiteTask.projectSite.projectSiteID = :projectSiteID "
            + "and p.statusDate between :startDate and :endDate "
            + "order by p.statusDate desc"),

    @NamedQuery(name = "ProjectSiteTaskStatus.countByProject",
            query = "SELECT count(p) FROM ProjectSiteTaskStatus p WHERE p.projectSiteTask.projectSite.project.projectID = :projectID"),
    @NamedQuery(name = "ProjectSiteTaskStatus.countByProjectSite",
            query = "SELECT count(p) FROM ProjectSiteTaskStatus p WHERE p.projectSiteTask.projectSite.projectSiteID = :projectSiteID")})
public class ProjectSiteTaskStatus implements Serializable {
    @OneToMany(mappedBy = "projectSiteTaskStatus")
    private List<SubTaskStatus> subTaskStatusList;

    @Column(name = "statusDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusDate;
    @JoinColumn(name = "companyStaffID", referencedColumnName = "companyStaffID")
    @ManyToOne
    private CompanyStaff companyStaff;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "projectSiteTaskStatusID")
    private Integer projectSiteTaskStatusID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateUpdated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
    @JoinColumn(name = "taskStatusID", referencedColumnName = "taskStatusID")
    @ManyToOne(optional = false)
    private TaskStatus taskStatus;
    @JoinColumn(name = "projectSiteTaskID", referencedColumnName = "projectSiteTaskID")
    @ManyToOne(optional = false)
    private ProjectSiteTask projectSiteTask;

    public ProjectSiteTaskStatus() {
    }

    public ProjectSiteTaskStatus(Integer projectSiteTaskStatusID) {
        this.projectSiteTaskStatusID = projectSiteTaskStatusID;
    }

    public ProjectSiteTaskStatus(Integer projectSiteTaskStatusID, Date dateUpdated) {
        this.projectSiteTaskStatusID = projectSiteTaskStatusID;
        this.dateUpdated = dateUpdated;
    }

    public Integer getProjectSiteTaskStatusID() {
        return projectSiteTaskStatusID;
    }

    public void setProjectSiteTaskStatusID(Integer projectSiteTaskStatusID) {
        this.projectSiteTaskStatusID = projectSiteTaskStatusID;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public ProjectSiteTask getProjectSiteTask() {
        return projectSiteTask;
    }

    public void setProjectSiteTask(ProjectSiteTask projectSiteTask) {
        this.projectSiteTask = projectSiteTask;
    }

    public CompanyStaff getCompanyStaff() {
        return companyStaff;
    }

    public void setCompanyStaff(CompanyStaff companyStaff) {
        this.companyStaff = companyStaff;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectSiteTaskStatusID != null ? projectSiteTaskStatusID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectSiteTaskStatus)) {
            return false;
        }
        ProjectSiteTaskStatus other = (ProjectSiteTaskStatus) object;
        if ((this.projectSiteTaskStatusID == null && other.projectSiteTaskStatusID != null) || (this.projectSiteTaskStatusID != null && !this.projectSiteTaskStatusID.equals(other.projectSiteTaskStatusID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ProjectSiteTaskStatus[ projectSiteTaskStatusID=" + projectSiteTaskStatusID + " ]";
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public List<SubTaskStatus> getSubTaskStatusList() {
        return subTaskStatusList;
    }

    public void setSubTaskStatusList(List<SubTaskStatus> subTaskStatusList) {
        this.subTaskStatusList = subTaskStatusList;
    }

}
