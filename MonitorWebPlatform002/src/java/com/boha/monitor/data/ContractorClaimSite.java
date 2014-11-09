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
 * @author aubreyM
 */
@Entity
@Table(name = "contractorClaimSite")
@NamedQueries({
    @NamedQuery(name = "ContractorClaimSite.findBySite", 
            query = "SELECT c FROM ContractorClaimSite c where c.contractorClaim.contractorClaimID = :contractorClaimID order by c.projectSite.projectSiteName"),
    @NamedQuery(name = "ContractorClaimSite.findByProject", 
            query = "SELECT c FROM ContractorClaimSite c WHERE c.projectSite.project.projectID = :projectID")
})
public class ContractorClaimSite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contractorClaimSiteID")
    private Integer contractorClaimSiteID;
    @JoinColumn(name = "contractorClaimID", referencedColumnName = "contractorClaimID")
    @ManyToOne
    private ContractorClaim contractorClaim;
    @JoinColumn(name = "projectSiteID", referencedColumnName = "projectSiteID")
    @ManyToOne
    private ProjectSite projectSite;

    public ContractorClaimSite() {
    }

    public ContractorClaimSite(Integer contractorClaimSiteID) {
        this.contractorClaimSiteID = contractorClaimSiteID;
    }

    public Integer getContractorClaimSiteID() {
        return contractorClaimSiteID;
    }

    public void setContractorClaimSiteID(Integer contractorClaimSiteID) {
        this.contractorClaimSiteID = contractorClaimSiteID;
    }

    public ContractorClaim getContractorClaim() {
        return contractorClaim;
    }

    public void setContractorClaim(ContractorClaim contractorClaim) {
        this.contractorClaim = contractorClaim;
    }

    public ProjectSite getProjectSite() {
        return projectSite;
    }

    public void setProjectSite(ProjectSite projectSite) {
        this.projectSite = projectSite;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contractorClaimSiteID != null ? contractorClaimSiteID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContractorClaimSite)) {
            return false;
        }
        ContractorClaimSite other = (ContractorClaimSite) object;
        if ((this.contractorClaimSiteID == null && other.contractorClaimSiteID != null) || (this.contractorClaimSiteID != null && !this.contractorClaimSiteID.equals(other.contractorClaimSiteID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ContractorClaimSite[ contractorClaimSiteID=" + contractorClaimSiteID + " ]";
    }
    
}
