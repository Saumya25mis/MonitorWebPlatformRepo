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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "programme")
@NamedQueries({
    @NamedQuery(name = "Programme.findAll", query = "SELECT p FROM Programme p"),
    @NamedQuery(name = "Programme.findByProgrammeID", query = "SELECT p FROM Programme p WHERE p.programmeID = :programmeID"),
    @NamedQuery(name = "Programme.findByProgrammeName", query = "SELECT p FROM Programme p WHERE p.programmeName = :programmeName"),
    @NamedQuery(name = "Programme.findByDateRegistered", query = "SELECT p FROM Programme p WHERE p.dateRegistered = :dateRegistered"),
    @NamedQuery(name = "Programme.findByCompleteFlag", query = "SELECT p FROM Programme p WHERE p.completeFlag = :completeFlag")})
public class Programme implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "programmeID")
    private Integer programmeID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "programmeName")
    private String programmeName;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @Column(name = "completeFlag")
    private Integer completeFlag;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programme")
    private List<Project> projectList;
    @JoinColumn(name = "portfolioID", referencedColumnName = "portfolioID")
    @OneToOne(optional = false)
    private Portfolio portfolio;

    public Programme() {
    }

    public Programme(Integer programmeID) {
        this.programmeID = programmeID;
    }

    public Programme(Integer programmeID, String programmeName, Date dateRegistered) {
        this.programmeID = programmeID;
        this.programmeName = programmeName;
        this.dateRegistered = dateRegistered;
    }

    public Integer getProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(Integer programmeID) {
        this.programmeID = programmeID;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Integer getCompleteFlag() {
        return completeFlag;
    }

    public void setCompleteFlag(Integer completeFlag) {
        this.completeFlag = completeFlag;
    }


    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (programmeID != null ? programmeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programme)) {
            return false;
        }
        Programme other = (Programme) object;
        if ((this.programmeID == null && other.programmeID != null) || (this.programmeID != null && !this.programmeID.equals(other.programmeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Programme[ programmeID=" + programmeID + " ]";
    }
    
}
