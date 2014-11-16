/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "contractorClaim")
@NamedQueries({
    @NamedQuery(name = "ContractorClaim.findByProject", 
            query = "SELECT c FROM ContractorClaim c where c.project.projectID = :projectID "
                    + "order by c.claimDate desc"),
    @NamedQuery(name = "ContractorClaim.findByCompany", 
            query = "SELECT c FROM ContractorClaim c WHERE c.project.company.companyID = :companyID  "
                    + "order by c.claimDate desc"),
    @NamedQuery(name = "ContractorClaim.findByClaimNumber", 
            query = "SELECT c FROM ContractorClaim c WHERE c.claimNumber = :claimNumber"),
    @NamedQuery(name = "ContractorClaim.findByClaimDate", 
            query = "SELECT c FROM ContractorClaim c WHERE c.claimDate = :claimDate")})
public class ContractorClaim implements Serializable {
    @JoinColumn(name = "projectEngineerID", referencedColumnName = "projectEngineerID")
    @ManyToOne
    private ProjectEngineer projectEngineer;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contractorClaimID")
    private Integer contractorClaimID;
    @Size(max = 255)
    @Column(name = "claimNumber")
    private String claimNumber;
    @Column(name = "claimDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date claimDate;
    @OneToMany(mappedBy = "contractorClaim")
    private List<ContractorClaimSite> contractorClaimSiteList;
    
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne
    private Project project;
    @JoinColumn(name = "taskID", referencedColumnName = "taskID")
    @ManyToOne
    private Task task;

    public ContractorClaim() {
    }

    public ContractorClaim(Integer contractorClaimID) {
        this.contractorClaimID = contractorClaimID;
    }

    public Integer getContractorClaimID() {
        return contractorClaimID;
    }

    public void setContractorClaimID(Integer contractorClaimID) {
        this.contractorClaimID = contractorClaimID;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public List<ContractorClaimSite> getContractorClaimSiteList() {
        return contractorClaimSiteList;
    }

    public void setContractorClaimSiteList(List<ContractorClaimSite> contractorClaimSiteList) {
        this.contractorClaimSiteList = contractorClaimSiteList;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contractorClaimID != null ? contractorClaimID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContractorClaim)) {
            return false;
        }
        ContractorClaim other = (ContractorClaim) object;
        if ((this.contractorClaimID == null && other.contractorClaimID != null) || (this.contractorClaimID != null && !this.contractorClaimID.equals(other.contractorClaimID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ContractorClaim[ contractorClaimID=" + contractorClaimID + " ]";
    }

    public ProjectEngineer getProjectEngineer() {
        return projectEngineer;
    }

    public void setProjectEngineer(ProjectEngineer projectEngineer) {
        this.projectEngineer = projectEngineer;
    }

   
}
