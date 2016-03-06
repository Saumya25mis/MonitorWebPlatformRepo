/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreymalabie
 */
@Entity
@Table(name = "invoiceRejectReason")
@NamedQueries({
    @NamedQuery(name = "InvoiceRejectReason.findAll", query = "SELECT i FROM InvoiceRejectReason i"),
    @NamedQuery(name = "InvoiceRejectReason.findByInvoiceRejectReasonID", query = "SELECT i FROM InvoiceRejectReason i WHERE i.invoiceRejectReasonID = :invoiceRejectReasonID"),
    @NamedQuery(name = "InvoiceRejectReason.findByName", query = "SELECT i FROM InvoiceRejectReason i WHERE i.name = :name")})
public class InvoiceRejectReason implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "invoiceRejectReasonID")
    private Integer invoiceRejectReasonID;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceRejectReason", fetch = FetchType.EAGER)
    private List<RejectedInvoice> rejectedInvoiceList;

    public InvoiceRejectReason() {
    }

    public InvoiceRejectReason(Integer invoiceRejectReasonID) {
        this.invoiceRejectReasonID = invoiceRejectReasonID;
    }

    public Integer getInvoiceRejectReasonID() {
        return invoiceRejectReasonID;
    }

    public void setInvoiceRejectReasonID(Integer invoiceRejectReasonID) {
        this.invoiceRejectReasonID = invoiceRejectReasonID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RejectedInvoice> getRejectedInvoiceList() {
        return rejectedInvoiceList;
    }

    public void setRejectedInvoiceList(List<RejectedInvoice> rejectedInvoiceList) {
        this.rejectedInvoiceList = rejectedInvoiceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceRejectReasonID != null ? invoiceRejectReasonID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceRejectReason)) {
            return false;
        }
        InvoiceRejectReason other = (InvoiceRejectReason) object;
        if ((this.invoiceRejectReasonID == null && other.invoiceRejectReasonID != null) || (this.invoiceRejectReasonID != null && !this.invoiceRejectReasonID.equals(other.invoiceRejectReasonID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.InvoiceRejectReason[ invoiceRejectReasonID=" + invoiceRejectReasonID + " ]";
    }
    
}
