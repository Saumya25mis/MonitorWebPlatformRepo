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
import javax.validation.constraints.NotNull;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "projectStatus")
@NamedQueries({
    @NamedQuery(name = "ProjectStatus.findAll", query = "SELECT p FROM ProjectStatus p"),
    @NamedQuery(name = "ProjectStatus.findByProjectStatusID", query = "SELECT p FROM ProjectStatus p WHERE p.projectStatusID = :projectStatusID"),
    @NamedQuery(name = "ProjectStatus.findByStatusDate", query = "SELECT p FROM ProjectStatus p WHERE p.statusDate = :statusDate"),
    @NamedQuery(name = "ProjectStatus.findByDateUpdated", query = "SELECT p FROM ProjectStatus p WHERE p.dateUpdated = :dateUpdated")})
public class ProjectStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "projectStatusID")
    private Integer projectStatusID;
    @Column(name = "statusDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateUpdated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne(optional = false)
    private Project project;
    @JoinColumn(name = "projectStatusTypeID", referencedColumnName = "projectStatusTypeID")
    @ManyToOne(optional = false)
    private ProjectStatusType projectStatusType;
    @JoinColumn(name = "staffID", referencedColumnName = "staffID")
    @ManyToOne(optional = false)
    private Staff staff;

    public ProjectStatus() {
    }

    public ProjectStatus(Integer projectStatusID) {
        this.projectStatusID = projectStatusID;
    }

    public ProjectStatus(Integer projectStatusID, Date dateUpdated) {
        this.projectStatusID = projectStatusID;
        this.dateUpdated = dateUpdated;
    }

    public Integer getProjectStatusID() {
        return projectStatusID;
    }

    public void setProjectStatusID(Integer projectStatusID) {
        this.projectStatusID = projectStatusID;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

  

    public ProjectStatusType getProjectStatusType() {
        return projectStatusType;
    }

    public void setProjectStatusType(ProjectStatusType projectStatusType) {
        this.projectStatusType = projectStatusType;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectStatusID != null ? projectStatusID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectStatus)) {
            return false;
        }
        ProjectStatus other = (ProjectStatus) object;
        if ((this.projectStatusID == null && other.projectStatusID != null) || (this.projectStatusID != null && !this.projectStatusID.equals(other.projectStatusID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ProjectStatus[ projectStatusID=" + projectStatusID + " ]";
    }
    
}
