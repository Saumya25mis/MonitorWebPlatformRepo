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
    @NamedQuery(name = "StaffProject.findAll", query = "SELECT s FROM StaffProject s"),
    @NamedQuery(name = "StaffProject.findByStaff", 
            query = "SELECT s FROM StaffProject s WHERE s.companyStaff.companyStaffID = :companyStaffID "
                    + "order by s.project.dateRegistered desc"),
    @NamedQuery(name = "StaffProject.findByDateAssigned", query = "SELECT s FROM StaffProject s WHERE s.dateAssigned = :dateAssigned"),
    @NamedQuery(name = "StaffProject.findByActiveFlag", query = "SELECT s FROM StaffProject s WHERE s.activeFlag = :activeFlag")})
public class StaffProject implements Serializable {
    @Column(name = "dateUpdated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "activeFlag")
    private boolean activeFlag;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Project project;
    @JoinColumn(name = "companyStaffID", referencedColumnName = "companyStaffID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CompanyStaff companyStaff;

    public StaffProject() {
    }

    public StaffProject(Integer staffProjectID) {
        this.staffProjectID = staffProjectID;
    }

    public StaffProject(Integer staffProjectID, Date dateAssigned, boolean activeFlag) {
        this.staffProjectID = staffProjectID;
        this.dateAssigned = dateAssigned;
        this.activeFlag = activeFlag;
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

    public boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
    
}
