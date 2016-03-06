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
import javax.persistence.FetchType;
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
@Table(name = "monitorProject")
@NamedQueries({
    @NamedQuery(name = "MonitorProject.countMonitorsByProject", 
            query = "SELECT s.project.projectID, count(s) as staffCount FROM MonitorProject s WHERE s.project.projectID in :list group by s.project.projectID"),
    
    @NamedQuery(name = "MonitorProject.findProjectsByMonitor",
            query = "SELECT m.project FROM MonitorProject m WHERE m.monitor.monitorID = :monitorID "
            + "ORDER BY m.project.dateRegistered desc"),
    @NamedQuery(name = "MonitorProject.findMonitorProjects",
            query = "SELECT m FROM MonitorProject m WHERE m.monitor.monitorID = :monitorID "
            + "ORDER BY m.project.projectName"),
    @NamedQuery(name = "MonitorProject.countProjectsByMonitor",
            query = "SELECT count(m) FROM MonitorProject m WHERE m.monitor.monitorID = :monitorID "),
    
    @NamedQuery(name = "MonitorProject.findMonitorsByProjectList",
    query = "SELECT m.monitor FROM MonitorProject m WHERE m.project.projectID IN :list ORDER BY m.project.projectID, m.monitor.lastName, m.monitor.firstName"),

    @NamedQuery(name = "MonitorProject.findMonitorsByProject",
            query = "SELECT m.monitor FROM MonitorProject m WHERE m.project.projectID = :projectID ORDER BY m.monitor.lastName, m.monitor.firstName"),})
public class MonitorProject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "monitorProjectID")
    private Integer monitorProjectID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateAssigned")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAssigned;
    @Column(name = "activeFlag")
    private Boolean activeFlag;
    @JoinColumn(name = "monitorID", referencedColumnName = "monitorID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Monitor monitor;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Project project;

    public MonitorProject() {
    }

    public MonitorProject(Integer monitorProjectID) {
        this.monitorProjectID = monitorProjectID;
    }

    public MonitorProject(Integer monitorProjectID, Date dateAssigned) {
        this.monitorProjectID = monitorProjectID;
        this.dateAssigned = dateAssigned;
    }

    public Integer getMonitorProjectID() {
        return monitorProjectID;
    }

    public void setMonitorProjectID(Integer monitorProjectID) {
        this.monitorProjectID = monitorProjectID;
    }

    public Date getDateAssigned() {
        return dateAssigned;
    }

    public void setDateAssigned(Date dateAssigned) {
        this.dateAssigned = dateAssigned;
    }

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monitorProjectID != null ? monitorProjectID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonitorProject)) {
            return false;
        }
        MonitorProject other = (MonitorProject) object;
        if ((this.monitorProjectID == null && other.monitorProjectID != null) || (this.monitorProjectID != null && !this.monitorProjectID.equals(other.monitorProjectID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.MonitorProject[ monitorProjectID=" + monitorProjectID + " ]";
    }

}
