/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.TenderCompany;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aubreymalabie
 */
public class TenderCompanyDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer tenderCompanyID;
    private String name;
    private String email;
    private String cellphone;
    private Long dateRegistered;
    private List<TenderCompanyProjectDTO> tenderCompanyProjectList;
    private List<CompanyExperienceDTO> companyExperienceList;
    private List<TenderCompanyTypeDTO> tenderCompanyTypeList;
    

    public TenderCompanyDTO() {
    }

    public TenderCompanyDTO(Integer tenderCompanyID) {
        this.tenderCompanyID = tenderCompanyID;
    }

    public TenderCompanyDTO(TenderCompany a) {
        this.tenderCompanyID = a.getTenderCompanyID();
        this.name = a.getName();
        this.email = a.getEmail();
        this.cellphone = a.getCellphone();
        this.dateRegistered = a.getDateRegistered().getTime();
        
    }

    public Integer getTenderCompanyID() {
        return tenderCompanyID;
    }

    public void setTenderCompanyID(Integer tenderCompanyID) {
        this.tenderCompanyID = tenderCompanyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Long getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Long dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public List<TenderCompanyProjectDTO> getTenderCompanyProjectList() {
        return tenderCompanyProjectList;
    }

    public void setTenderCompanyProjectList(List<TenderCompanyProjectDTO> tenderCompanyProjectList) {
        this.tenderCompanyProjectList = tenderCompanyProjectList;
    }

    public List<CompanyExperienceDTO> getCompanyExperienceList() {
        return companyExperienceList;
    }

    public void setCompanyExperienceList(List<CompanyExperienceDTO> companyExperienceList) {
        this.companyExperienceList = companyExperienceList;
    }

    public List<TenderCompanyTypeDTO> getTenderCompanyTypeList() {
        return tenderCompanyTypeList;
    }

    public void setTenderCompanyTypeList(List<TenderCompanyTypeDTO> tenderCompanyTypeList) {
        this.tenderCompanyTypeList = tenderCompanyTypeList;
    }


   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tenderCompanyID != null ? tenderCompanyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TenderCompanyDTO)) {
            return false;
        }
        TenderCompanyDTO other = (TenderCompanyDTO) object;
        if ((this.tenderCompanyID == null && other.tenderCompanyID != null) || (this.tenderCompanyID != null && !this.tenderCompanyID.equals(other.tenderCompanyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.TenderCompany[ tenderCompanyID=" + tenderCompanyID + " ]";
    }
    
}
