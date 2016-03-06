/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.TenderCompanyProject;
import java.io.Serializable;

/**
 *
 * @author aubreymalabie
 */
public class TenderCompanyProjectDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer tenderCompanyProjectID;
    private Integer projectID;
    private Integer tenderCompanyID;

    public TenderCompanyProjectDTO() {
    }

    public TenderCompanyProjectDTO( TenderCompanyProject a) {
        this.tenderCompanyProjectID = a.getTenderCompanyProjectID();
        projectID = a.getProject().getProjectID();
        tenderCompanyID = a.getTenderCompany().getTenderCompanyID();
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getTenderCompanyID() {
        return tenderCompanyID;
    }

    public void setTenderCompanyID(Integer tenderCompanyID) {
        this.tenderCompanyID = tenderCompanyID;
    }

    
    public Integer getTenderCompanyProjectID() {
        return tenderCompanyProjectID;
    }

    public void setTenderCompanyProjectID(Integer tenderCompanyProjectID) {
        this.tenderCompanyProjectID = tenderCompanyProjectID;
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
        if (!(object instanceof TenderCompanyProjectDTO)) {
            return false;
        }
        TenderCompanyProjectDTO other = (TenderCompanyProjectDTO) object;
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
