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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "invoiceCode")
@NamedQueries({
    @NamedQuery(name = "InvoiceCode.findByCompany", 
            query = "SELECT i FROM InvoiceCode i where i.company.companyID = :companyID ORDER BY i.invoiceCodeName"),
    @NamedQuery(name = "InvoiceCode.findByInvoiceCodeID", query = "SELECT i FROM InvoiceCode i WHERE i.invoiceCodeID = :invoiceCodeID"),
    @NamedQuery(name = "InvoiceCode.findByInvoiceCodeName", query = "SELECT i FROM InvoiceCode i WHERE i.invoiceCodeName = :invoiceCodeName"),
    @NamedQuery(name = "InvoiceCode.findByInvoiceCodeNumber", query = "SELECT i FROM InvoiceCode i WHERE i.invoiceCodeNumber = :invoiceCodeNumber")})
public class InvoiceCode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "invoiceCodeID")
    private Integer invoiceCodeID;
    @Size(max = 45)
    @Column(name = "invoiceCodeName")
    private String invoiceCodeName;
    @Size(max = 45)
    @Column(name = "invoiceCodeNumber")
    private String invoiceCodeNumber;
    @OneToMany(mappedBy = "invoiceCode")
    private List<InvoiceItem> invoiceItemList;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne(optional = false)
    private Company company;

    public InvoiceCode() {
    }

    public InvoiceCode(Integer invoiceCodeID) {
        this.invoiceCodeID = invoiceCodeID;
    }

    public Integer getInvoiceCodeID() {
        return invoiceCodeID;
    }

    public void setInvoiceCodeID(Integer invoiceCodeID) {
        this.invoiceCodeID = invoiceCodeID;
    }

    public String getInvoiceCodeName() {
        return invoiceCodeName;
    }

    public void setInvoiceCodeName(String invoiceCodeName) {
        this.invoiceCodeName = invoiceCodeName;
    }

    public String getInvoiceCodeNumber() {
        return invoiceCodeNumber;
    }

    public void setInvoiceCodeNumber(String invoiceCodeNumber) {
        this.invoiceCodeNumber = invoiceCodeNumber;
    }

    public List<InvoiceItem> getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(List<InvoiceItem> invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
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
        hash += (invoiceCodeID != null ? invoiceCodeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceCode)) {
            return false;
        }
        InvoiceCode other = (InvoiceCode) object;
        if ((this.invoiceCodeID == null && other.invoiceCodeID != null) || (this.invoiceCodeID != null && !this.invoiceCodeID.equals(other.invoiceCodeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.InvoiceCode[ invoiceCodeID=" + invoiceCodeID + " ]";
    }
    
}
