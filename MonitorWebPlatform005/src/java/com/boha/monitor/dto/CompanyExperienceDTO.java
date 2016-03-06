/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.CompanyExperience;
import java.io.Serializable;

/**
 *
 * @author aubreymalabie
 */
public class CompanyExperienceDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer companyExperienceID;
    private Long experienceDate;
    private ExperienceTypeDTO experienceType;
    private Integer tenderCompanyID;

    public CompanyExperienceDTO() {
    }

    public CompanyExperienceDTO(Integer companyExperienceID) {
        this.companyExperienceID = companyExperienceID;
    }

    public CompanyExperienceDTO(CompanyExperience a) {
        this.companyExperienceID = a.getCompanyExperienceID();
        this.experienceDate = a.getExperienceDate().getTime();
        tenderCompanyID = a.getTenderCompany().getTenderCompanyID();
        experienceType = new ExperienceTypeDTO(a.getExperienceType());
    }

    public Integer getCompanyExperienceID() {
        return companyExperienceID;
    }

    public void setCompanyExperienceID(Integer companyExperienceID) {
        this.companyExperienceID = companyExperienceID;
    }


    public ExperienceTypeDTO getExperienceType() {
        return experienceType;
    }

    public void setExperienceType(ExperienceTypeDTO experienceType) {
        this.experienceType = experienceType;
    }

    public Long getExperienceDate() {
        return experienceDate;
    }

    public void setExperienceDate(Long experienceDate) {
        this.experienceDate = experienceDate;
    }

    public Integer getTenderCompanyID() {
        return tenderCompanyID;
    }

    public void setTenderCompanyID(Integer tenderCompanyID) {
        this.tenderCompanyID = tenderCompanyID;
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
        if (!(object instanceof CompanyExperienceDTO)) {
            return false;
        }
        CompanyExperienceDTO other = (CompanyExperienceDTO) object;
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
