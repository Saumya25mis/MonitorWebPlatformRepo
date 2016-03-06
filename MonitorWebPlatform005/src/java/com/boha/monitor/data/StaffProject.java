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
@Table(name = "staffProject")
@NamedQueries({
    
    @NamedQuery(name = "StaffProject.countStaffByProject", 
            query = "SELECT s.project.projectID, count(s) as staffCount FROM StaffProject s WHERE s.project.projectID in :list group by s.project.projectID"),
    
    @NamedQuery(name = "StaffProject.findByStaff", 
            query = "SELECT s FROM StaffProject s WHERE s.staff.staffID = :staffID and s.activeFlag = TRUE order by s.project.projectName"),
    
    @NamedQuery(name = "StaffProject.findStaffProjects", 
            query = "SELECT s FROM StaffProject s WHERE s.staff.staffID = :staffID and s.activeFlag = TRUE order by s.project.projectName"),
       
    @NamedQuery(name = "StaffProject.findByActiveFlag", 
            query = "SELECT s FROM StaffProject s WHERE s.activeFlag = :activeFlag")
})
public class StaffProject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "staffProjectID")
    private Integer staffProjectID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateAssigned")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAssigned;
    @Column(name = "activeFlag")
    private Boolean activeFlag;
    @JoinColumn(name = "staffID", referencedColumnName = "staffID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Staff staff;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Project project;

    public StaffProject() {
    }

    public StaffProject(Integer staffProjectID) {
        this.staffProjectID = staffProjectID;
    }

    public StaffProject(Integer staffProjectID, Date dateAssigned) {
        this.staffProjectID = staffProjectID;
        this.dateAssigned = dateAssigned;
    }

    public Integer getStaffProjectID() {
        return staffProjectID;
    }

    public void setStaffProjectID(Integer staffProjectID) {
        this.staffProjectID = staffProjectID;
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
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
        hash += (staffProjectID != null ? staffProjectID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StaffProject)) {
            return false;
        }
        StaffProject other = (StaffProject) object;
        if ((this.staffProjectID == null && other.staffProjectID != null) || (this.staffProjectID != null && !this.staffProjectID.equals(other.staffProjectID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.StaffProject[ staffProjectID=" + staffProjectID + " ]";
    }
    
}
