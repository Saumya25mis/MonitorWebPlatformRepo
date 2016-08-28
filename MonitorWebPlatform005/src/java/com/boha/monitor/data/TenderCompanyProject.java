/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.data;

import java.io.Serializable;
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

/**
 *
 * @author aubreymalabie
 */
@Entity
@Table(name = "tenderCompanyProject")
@NamedQueries({
    @NamedQuery(name = "TenderCompanyProject.findAll", query = "SELECT t FROM TenderCompanyProject t"),
    @NamedQuery(name = "TenderCompanyProject.findByTenderCompanyProjectID", query = "SELECT t FROM TenderCompanyProject t WHERE t.tenderCompanyProjectID = :tenderCompanyProjectID")})
public class TenderCompanyProject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tenderCompanyProjectID")
    private Integer tenderCompanyProjectID;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Project project;
    @JoinColumn(name = "tenderCompanyID", referencedColumnName = "tenderCompanyID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TenderCompany tenderCompany;

    public TenderCompanyProject() {
    }

    public TenderCompanyProject(Integer tenderCompanyProjectID) {
        this.tenderCompanyProjectID = tenderCompanyProjectID;
    }

    public Integer getTenderCompanyProjectID() {
        return tenderCompanyProjectID;
    }

    public void setTenderCompanyProjectID(Integer tenderCompanyProjectID) {
        this.tenderCompanyProjectID = tenderCompanyProjectID;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public TenderCompany getTenderCompany() {
        return tenderCompany;
    }

    public void setTenderCompany(TenderCompany tenderCompany) {
        this.tenderCompany = tenderCompany;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tenderCompanyProjectID != null ? tenderCompanyProjectID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TenderCompanyProject)) {
            return false;
        }
        TenderCompanyProject other = (TenderCompanyProject) object;
        if ((this.tenderCompanyProjectID == null && other.tenderCompanyProjectID != null) || (this.tenderCompanyProjectID != null && !this.tenderCompanyProjectID.equals(other.tenderCompanyProjectID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.TenderCompanyProject[ tenderCompanyProjectID=" + tenderCompanyProjectID + " ]";
    }
    
}
