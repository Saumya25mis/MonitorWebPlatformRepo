/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreymalabie
 */
@Entity
@Table(name = "companyType")
@NamedQueries({
    @NamedQuery(name = "CompanyType.findAll", query = "SELECT c FROM CompanyType c"),
    @NamedQuery(name = "CompanyType.findByCompanyTypeID", query = "SELECT c FROM CompanyType c WHERE c.companyTypeID = :companyTypeID"),
    @NamedQuery(name = "CompanyType.findByName", query = "SELECT c FROM CompanyType c WHERE c.name = :name")})
public class CompanyType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "companyTypeID")
    private Integer companyTypeID;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyType", fetch = FetchType.LAZY)
    private List<TenderCompanyType> tenderCompanyTypeList;

    public CompanyType() {
    }

    public CompanyType(Integer companyTypeID) {
        this.companyTypeID = companyTypeID;
    }

    public Integer getCompanyTypeID() {
        return companyTypeID;
    }

    public void setCompanyTypeID(Integer companyTypeID) {
        this.companyTypeID = companyTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TenderCompanyType> getTenderCompanyTypeList() {
        return tenderCompanyTypeList;
    }

    public void setTenderCompanyTypeList(List<TenderCompanyType> tenderCompanyTypeList) {
        this.tenderCompanyTypeList = tenderCompanyTypeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyTypeID != null ? companyTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyType)) {
            return false;
        }
        CompanyType other = (CompanyType) object;
        if ((this.companyTypeID == null && other.companyTypeID != null) || (this.companyTypeID != null && !this.companyTypeID.equals(other.companyTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.CompanyType[ companyTypeID=" + companyTypeID + " ]";
    }
    
}
