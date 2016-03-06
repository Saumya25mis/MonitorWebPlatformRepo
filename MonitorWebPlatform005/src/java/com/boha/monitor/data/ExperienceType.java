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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreymalabie
 */
@Entity
@Table(name = "experienceType")
@NamedQueries({
    @NamedQuery(name = "ExperienceType.findAll", query = "SELECT e FROM ExperienceType e"),
    @NamedQuery(name = "ExperienceType.findByExperienceTypeID", query = "SELECT e FROM ExperienceType e WHERE e.experienceTypeID = :experienceTypeID"),
    @NamedQuery(name = "ExperienceType.findByName", query = "SELECT e FROM ExperienceType e WHERE e.name = :name")})
public class ExperienceType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "experienceTypeID")
    private Integer experienceTypeID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experienceType", fetch = FetchType.EAGER)
    private List<CompanyExperience> companyExperienceList;

    public ExperienceType() {
    }

    public ExperienceType(Integer experienceTypeID) {
        this.experienceTypeID = experienceTypeID;
    }

    public ExperienceType(Integer experienceTypeID, String name) {
        this.experienceTypeID = experienceTypeID;
        this.name = name;
    }

    public Integer getExperienceTypeID() {
        return experienceTypeID;
    }

    public void setExperienceTypeID(Integer experienceTypeID) {
        this.experienceTypeID = experienceTypeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CompanyExperience> getCompanyExperienceList() {
        return companyExperienceList;
    }

    public void setCompanyExperienceList(List<CompanyExperience> companyExperienceList) {
        this.companyExperienceList = companyExperienceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (experienceTypeID != null ? experienceTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExperienceType)) {
            return false;
        }
        ExperienceType other = (ExperienceType) object;
        if ((this.experienceTypeID == null && other.experienceTypeID != null) || (this.experienceTypeID != null && !this.experienceTypeID.equals(other.experienceTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ExperienceType[ experienceTypeID=" + experienceTypeID + " ]";
    }
    
}
