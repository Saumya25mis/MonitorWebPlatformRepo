/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.TenderCompanyType;
import java.io.Serializable;

/**
 *
 * @author aubreymalabie
 */
public class TenderCompanyTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer tenderCompanyTypeID;
    private CompanyTypeDTO companyType;
    private Integer tenderCompanyID;

    public TenderCompanyTypeDTO() {
    }

    public TenderCompanyTypeDTO( TenderCompanyType a) {
        this.tenderCompanyTypeID = a.getTenderCompanyTypeID();
        tenderCompanyID = a.getTenderCompany().getTenderCompanyID();
        companyType = new CompanyTypeDTO(a.getCompanyType());
    }

    public Integer getTenderCompanyID() {
        return tenderCompanyID;
    }

    public void setTenderCompanyID(Integer tenderCompanyID) {
        this.tenderCompanyID = tenderCompanyID;
    }

    
    public Integer getTenderCompanyTypeID() {
        return tenderCompanyTypeID;
    }

    public void setTenderCompanyTypeID(Integer tenderCompanyTypeID) {
        this.tenderCompanyTypeID = tenderCompanyTypeID;
    }

    public CompanyTypeDTO getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyTypeDTO companyType) {
        this.companyType = companyType;
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
        if (!(object instanceof TenderCompanyTypeDTO)) {
            return false;
        }
        TenderCompanyTypeDTO other = (TenderCompanyTypeDTO) object;
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
