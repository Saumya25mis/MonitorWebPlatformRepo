/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "projectEngineer")
@NamedQueries({
    @NamedQuery(name = "ProjectEngineer.findByProject", 
            query = "SELECT p FROM ProjectEngineer p where p.project.projectID = :projectID order by p.engineer.engineerName")
    })
public class ProjectEngineer implements Serializable {
    @JoinColumn(name = "engineerID", referencedColumnName = "engineerID")
    @ManyToOne(optional = false)
    private Engineer engineer;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "projectEngineerID")
    private Integer projectEngineerID;
    
    @Column(name = "activeFlag")
    private Integer activeFlag;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne(optional = false)
    private Project project;
    @OneToMany(mappedBy = "projectEngineer")
    private List<ContractorClaim> contractorClaimList;

    public ProjectEngineer() {
    }

    public ProjectEngineer(Integer projectEngineerID) {
        this.projectEngineerID = projectEngineerID;
    }

    public ProjectEngineer(Integer projectEngineerID, int engineerID) {
        this.projectEngineerID = projectEngineerID;
    }

    public Integer getProjectEngineerID() {
        return projectEngineerID;
    }

    public void setProjectEngineerID(Integer projectEngineerID) {
        this.projectEngineerID = projectEngineerID;
    }

  

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


    public List<ContractorClaim> getContractorClaimList() {
        return contractorClaimList;
    }

    public void setContractorClaimList(List<ContractorClaim> contractorClaimList) {
        this.contractorClaimList = contractorClaimList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectEngineerID != null ? projectEngineerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectEngineer)) {
            return false;
        }
        ProjectEngineer other = (ProjectEngineer) object;
        if ((this.projectEngineerID == null && other.projectEngineerID != null) || (this.projectEngineerID != null && !this.projectEngineerID.equals(other.projectEngineerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ProjectEngineer[ projectEngineerID=" + projectEngineerID + " ]";
    }

    public Engineer getEngineer() {
        return engineer;
    }

    public void setEngineer(Engineer engineer) {
        this.engineer = engineer;
    }

    
}
