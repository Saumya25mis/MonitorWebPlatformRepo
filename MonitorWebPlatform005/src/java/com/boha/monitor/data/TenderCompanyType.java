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
@Table(name = "tenderCompanyType")
@NamedQueries({
    @NamedQuery(name = "TenderCompanyType.findAll", query = "SELECT t FROM TenderCompanyType t"),
    @NamedQuery(name = "TenderCompanyType.findByTenderCompanyTypeID", query = "SELECT t FROM TenderCompanyType t WHERE t.tenderCompanyTypeID = :tenderCompanyTypeID")})
public class TenderCompanyType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tenderCompanyTypeID")
    private Integer tenderCompanyTypeID;
    @JoinColumn(name = "companyTypeID", referencedColumnName = "companyTypeID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CompanyType companyType;
    @JoinColumn(name = "tenderCompanyID", referencedColumnName = "tenderCompanyID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TenderCompany tenderCompany;

    public TenderCompanyType() {
    }

    public TenderCompanyType(Integer tenderCompanyTypeID) {
        this.tenderCompanyTypeID = tenderCompanyTypeID;
    }

    public Integer getTenderCompanyTypeID() {
        return tenderCompanyTypeID;
    }

    public void setTenderCompanyTypeID(Integer tenderCompanyTypeID) {
        this.tenderCompanyTypeID = tenderCompanyTypeID;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
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
        hash += (tenderCompanyTypeID != null ? tenderCompanyTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TenderCompanyType)) {
            return false;
        }
        TenderCompanyType other = (TenderCompanyType) object;
        if ((this.tenderCompanyTypeID == null && other.tenderCompanyTypeID != null) || (this.tenderCompanyTypeID != null && !this.tenderCompanyTypeID.equals(other.tenderCompanyTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.TenderCompanyType[ tenderCompanyTypeID=" + tenderCompanyTypeID + " ]";
    }
    
}
