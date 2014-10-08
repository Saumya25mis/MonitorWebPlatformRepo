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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "companyClient")
@NamedQueries({
    @NamedQuery(name = "CompanyClient.findAll", query = "SELECT c FROM CompanyClient c"),
    @NamedQuery(name = "CompanyClient.findByCompanyClientID", query = "SELECT c FROM CompanyClient c WHERE c.companyClientID = :companyClientID"),
    @NamedQuery(name = "CompanyClient.findByDateRegistered", query = "SELECT c FROM CompanyClient c WHERE c.dateRegistered = :dateRegistered")})
public class CompanyClient implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "companyClientID")
    private Integer companyClientID;
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyClient")
    private List<Invoice> invoiceList;
    @JoinColumn(name = "clientID", referencedColumnName = "clientID")
    @ManyToOne(optional = false)
    private Client client;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne(optional = false)
    private Company company;

    public CompanyClient() {
    }

    public CompanyClient(Integer companyClientID) {
        this.companyClientID = companyClientID;
    }

    public Integer getCompanyClientID() {
        return companyClientID;
    }

    public void setCompanyClientID(Integer companyClientID) {
        this.companyClientID = companyClientID;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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
        hash += (companyClientID != null ? companyClientID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyClient)) {
            return false;
        }
        CompanyClient other = (CompanyClient) object;
        if ((this.companyClientID == null && other.companyClientID != null) || (this.companyClientID != null && !this.companyClientID.equals(other.companyClientID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.CompanyClient[ companyClientID=" + companyClientID + " ]";
    }
    
}
