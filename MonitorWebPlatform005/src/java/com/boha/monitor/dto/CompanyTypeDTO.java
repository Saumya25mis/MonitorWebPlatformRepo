/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.CompanyType;
import java.io.Serializable;

/**
 *
 * @author aubreymalabie
 */
public class CompanyTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer companyTypeID;
    private String name;

    public CompanyTypeDTO() {
    }

    public CompanyTypeDTO( CompanyType a) {
        this.companyTypeID = a.getCompanyTypeID();
        name = a.getName();
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyTypeID != null ? companyTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyTypeDTO)) {
            return false;
        }
        CompanyTypeDTO other = (CompanyTypeDTO) object;
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
