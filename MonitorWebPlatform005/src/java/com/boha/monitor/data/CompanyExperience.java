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
 * @author aubreymalabie
 */
@Entity
@Table(name = "companyExperience")
@NamedQueries({
    @NamedQuery(name = "CompanyExperience.findAll", query = "SELECT c FROM CompanyExperience c"),
    @NamedQuery(name = "CompanyExperience.findByCompanyExperienceID", query = "SELECT c FROM CompanyExperience c WHERE c.companyExperienceID = :companyExperienceID"),
    @NamedQuery(name = "CompanyExperience.findByExperienceDate", query = "SELECT c FROM CompanyExperience c WHERE c.experienceDate = :experienceDate")})
public class CompanyExperience implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "companyExperienceID")
    private Integer companyExperienceID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "experienceDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date experienceDate;
    @JoinColumn(name = "experienceTypeID", referencedColumnName = "experienceTypeID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ExperienceType experienceType;
    @JoinColumn(name = "tenderCompanyID", referencedColumnName = "tenderCompanyID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TenderCompany tenderCompany;

    public CompanyExperience() {
    }

    public CompanyExperience(Integer companyExperienceID) {
        this.companyExperienceID = companyExperienceID;
    }

    public CompanyExperience(Integer companyExperienceID, Date experienceDate) {
        this.companyExperienceID = companyExperienceID;
        this.experienceDate = experienceDate;
    }

    public Integer getCompanyExperienceID() {
        return companyExperienceID;
    }

    public void setCompanyExperienceID(Integer companyExperienceID) {
        this.companyExperienceID = companyExperienceID;
    }

    public Date getExperienceDate() {
        return experienceDate;
    }

    public void setExperienceDate(Date experienceDate) {
        this.experienceDate = experienceDate;
    }

    public ExperienceType getExperienceType() {
        return experienceType;
    }

    public void setExperienceType(ExperienceType experienceType) {
        this.experienceType = experienceType;
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
        hash += (companyExperienceID != null ? companyExperienceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyExperience)) {
            return false;
        }
        CompanyExperience other = (CompanyExperience) object;
        if ((this.companyExperienceID == null && other.companyExperienceID != null) || (this.companyExperienceID != null && !this.companyExperienceID.equals(other.companyExperienceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.CompanyExperience[ companyExperienceID=" + companyExperienceID + " ]";
    }
    
}
