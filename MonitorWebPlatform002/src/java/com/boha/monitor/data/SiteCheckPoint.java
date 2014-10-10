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
@Table(name = "siteCheckPoint")
@NamedQueries({
    @NamedQuery(name = "SiteCheckPoint.findAll", query = "SELECT s FROM SiteCheckPoint s"),
    @NamedQuery(name = "SiteCheckPoint.findBySiteCheckPointID", query = "SELECT s FROM SiteCheckPoint s WHERE s.siteCheckPointID = :siteCheckPointID"),
    @NamedQuery(name = "SiteCheckPoint.findByCheckPointID", query = "SELECT s FROM SiteCheckPoint s WHERE s.checkPointID = :checkPointID"),
    @NamedQuery(name = "SiteCheckPoint.findByDateRegistered", query = "SELECT s FROM SiteCheckPoint s WHERE s.dateRegistered = :dateRegistered")})
public class SiteCheckPoint implements Serializable {
    @JoinColumn(name = "checkPointID", referencedColumnName = "checkPointID")
    @ManyToOne(optional = false)
    private CheckPoint checkPoint;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "siteCheckPointID")
    private Integer siteCheckPointID;
   
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @JoinColumn(name = "invoiceID", referencedColumnName = "invoiceID")
    @ManyToOne
    private Invoice invoice;
    @JoinColumn(name = "projectSiteID", referencedColumnName = "projectSiteID")
    @ManyToOne(optional = false)
    private ProjectSite projectSite;
    @JoinColumn(name = "projectSiteStaffID", referencedColumnName = "projectSiteStaffID")
    @ManyToOne(optional = false)
    private ProjectSiteStaff projectSiteStaff;

    public SiteCheckPoint() {
    }

    public SiteCheckPoint(Integer siteCheckPointID) {
        this.siteCheckPointID = siteCheckPointID;
    }

  

    public Integer getSiteCheckPointID() {
        return siteCheckPointID;
    }

    public void setSiteCheckPointID(Integer siteCheckPointID) {
        this.siteCheckPointID = siteCheckPointID;
    }

    public CheckPoint getCheckPoint() {
        return checkPoint;
    }

    public void setCheckPoint(CheckPoint checkPoint) {
        this.checkPoint = checkPoint;
    }

 

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public ProjectSite getProjectSite() {
        return projectSite;
    }

    public void setProjectSite(ProjectSite projectSite) {
        this.projectSite = projectSite;
    }

    public ProjectSiteStaff getProjectSiteStaff() {
        return projectSiteStaff;
    }

    public void setProjectSiteStaff(ProjectSiteStaff projectSiteStaff) {
        this.projectSiteStaff = projectSiteStaff;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (siteCheckPointID != null ? siteCheckPointID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SiteCheckPoint)) {
            return false;
        }
        SiteCheckPoint other = (SiteCheckPoint) object;
        if ((this.siteCheckPointID == null && other.siteCheckPointID != null) || (this.siteCheckPointID != null && !this.siteCheckPointID.equals(other.siteCheckPointID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.SiteCheckPoint[ siteCheckPointID=" + siteCheckPointID + " ]";
    }

    
}
