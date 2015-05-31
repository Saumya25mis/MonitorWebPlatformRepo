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
import javax.persistence.ManyToOne;
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
@Table(name = "portfolio")
@NamedQueries({
    @NamedQuery(name = "Portfolio.findAll", query = "SELECT p FROM Portfolio p"),
    @NamedQuery(name = "Portfolio.findByPortfolioID", query = "SELECT p FROM Portfolio p WHERE p.portfolioID = :portfolioID"),
    @NamedQuery(name = "Portfolio.findByPortfolioName", query = "SELECT p FROM Portfolio p WHERE p.portfolioName = :portfolioName"),
    @NamedQuery(name = "Portfolio.findByDateRegistered", query = "SELECT p FROM Portfolio p WHERE p.dateRegistered = :dateRegistered")})
public class Portfolio implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "portfolio")
    private List<Programme> programmeList;
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "portfolioID")
    private Integer portfolioID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "portfolioName")
    private String portfolioName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne(optional = false)
    private Company company;

    public Portfolio() {
    }

    public Portfolio(Integer portfolioID) {
        this.portfolioID = portfolioID;
    }

    public Portfolio(Integer portfolioID, String portfolioName, Date dateRegistered) {
        this.portfolioID = portfolioID;
        this.portfolioName = portfolioName;
        this.dateRegistered = dateRegistered;
    }

    public Integer getPortfolioID() {
        return portfolioID;
    }

    public void setPortfolioID(Integer portfolioID) {
        this.portfolioID = portfolioID;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (portfolioID != null ? portfolioID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Portfolio)) {
            return false;
        }
        Portfolio other = (Portfolio) object;
        if ((this.portfolioID == null && other.portfolioID != null) || (this.portfolioID != null && !this.portfolioID.equals(other.portfolioID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Portfolio[ portfolioID=" + portfolioID + " ]";
    }

    

    public List<Programme> getProgrammeList() {
        return programmeList;
    }

    public void setProgrammeList(List<Programme> programmeList) {
        this.programmeList = programmeList;
    }
    
}
