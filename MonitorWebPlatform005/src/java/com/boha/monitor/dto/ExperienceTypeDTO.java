/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.ExperienceType;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aubreymalabie
 */
public class ExperienceTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer experienceTypeID;
    private String name;
    private List<CompanyExperienceDTO> companyExperienceList;

    public ExperienceTypeDTO() {
    }

    public ExperienceTypeDTO( ExperienceType a) {
        this.experienceTypeID = a.getExperienceTypeID();
        name = a.getName();
    }

    public ExperienceTypeDTO(Integer experienceTypeID, String name) {
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

    public List<CompanyExperienceDTO> getCompanyExperienceList() {
        return companyExperienceList;
    }

    public void setCompanyExperienceList(List<CompanyExperienceDTO> companyExperienceList) {
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
        if (!(object instanceof ExperienceTypeDTO)) {
            return false;
        }
        ExperienceTypeDTO other = (ExperienceTypeDTO) object;
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
