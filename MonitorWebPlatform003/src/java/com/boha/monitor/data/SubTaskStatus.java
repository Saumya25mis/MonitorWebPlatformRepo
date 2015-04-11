/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.data;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "subTaskStatus")
@NamedQueries({
    @NamedQuery(name = "SubTaskStatus.findByTask",
            query = "SELECT s FROM SubTaskStatus s where s.subTask.task.taskID = :taskID"),

    @NamedQuery(name = "SubTaskStatus.findBySite",
            query = "SELECT s FROM SubTaskStatus s where s.projectSiteTask.projectSite.projectSiteID = :projectSiteID "
            + "order by s.projectSiteTask.projectSiteTaskID, s.statusDate desc"),
    @NamedQuery(name = "SubTaskStatus.findByProject",
            query = "SELECT s FROM SubTaskStatus s where s.projectSiteTask.projectSite.project.projectID = :projectID "
            + "order by s.projectSiteTask.projectSiteTaskID, s.statusDate desc"),

    @NamedQuery(name = "SubTaskStatus.countByProject",
            query = "SELECT count(s) FROM SubTaskStatus s where s.projectSiteTask.projectSite.project.projectID = :projectID ")

})
public class SubTaskStatus implements Serializable {

    @JoinColumn(name = "projectSiteTaskID", referencedColumnName = "projectSiteTaskID")
    @ManyToOne
    private ProjectSiteTask projectSiteTask;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "subTaskStatusID")
    private Integer subTaskStatusID;
    @Column(name = "statusDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusDate;
    @Column(name = "dateUpdated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
    @JoinColumn(name = "taskStatusID", referencedColumnName = "taskStatusID")
    @ManyToOne
    private TaskStatus taskStatus;
    @JoinColumn(name = "subTaskID", referencedColumnName = "subTaskID")
    @ManyToOne
    private SubTask subTask;
    @JoinColumn(name = "companyStaffID", referencedColumnName = "companyStaffID")
    @ManyToOne
    private CompanyStaff companyStaff;

    public SubTaskStatus() {
    }

    public SubTaskStatus(Integer subTaskStatusID) {
        this.subTaskStatusID = subTaskStatusID;
    }

    public ProjectSiteTask getProjectSiteTask() {
        return projectSiteTask;
    }

    public void setProjectSiteTask(ProjectSiteTask projectSiteTask) {
        this.projectSiteTask = projectSiteTask;
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

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public SubTask getSubTask() {
        return subTask;
    }

    public void setSubTask(SubTask subTask) {
        this.subTask = subTask;
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
        hash += (subTaskStatusID != null ? subTaskStatusID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SubTaskStatus)) {
            return false;
        }
        SubTaskStatus other = (SubTaskStatus) object;
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
