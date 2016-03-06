/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author aubreymalabie
 */
@Entity
@Table(name = "rejectedInvoice")
@NamedQueries({
    @NamedQuery(name = "RejectedInvoice.findAll", query = "SELECT r FROM RejectedInvoice r"),
    @NamedQuery(name = "RejectedInvoice.findByRejectedInvoiceID", query = "SELECT r FROM RejectedInvoice r WHERE r.rejectedInvoiceID = :rejectedInvoiceID"),
    @NamedQuery(name = "RejectedInvoice.findByDateRegistered", query = "SELECT r FROM RejectedInvoice r WHERE r.dateRegistered = :dateRegistered")})
public class RejectedInvoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rejectedInvoiceID")
    private Integer rejectedInvoiceID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @JoinColumn(name = "invoiceRejectReasonID", referencedColumnName = "invoiceRejectReasonID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private InvoiceRejectReason invoiceRejectReason;
    @JoinColumn(name = "invoiceID", referencedColumnName = "invoiceID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Invoice invoice;

    public RejectedInvoice() {
    }

    public RejectedInvoice(Integer rejectedInvoiceID) {
        this.rejectedInvoiceID = rejectedInvoiceID;
    }

    public RejectedInvoice(Integer rejectedInvoiceID, Date dateRegistered) {
        this.rejectedInvoiceID = rejectedInvoiceID;
        this.dateRegistered = dateRegistered;
    }

    public Integer getRejectedInvoiceID() {
        return rejectedInvoiceID;
    }

    public void setRejectedInvoiceID(Integer rejectedInvoiceID) {
        this.rejectedInvoiceID = rejectedInvoiceID;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public InvoiceRejectReason getInvoiceRejectReason() {
        return invoiceRejectReason;
    }

    public void setInvoiceRejectReason(InvoiceRejectReason invoiceRejectReason) {
        this.invoiceRejectReason = invoiceRejectReason;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rejectedInvoiceID != null ? rejectedInvoiceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RejectedInvoice)) {
            return false;
        }
        RejectedInvoice other = (RejectedInvoice) object;
        if ((this.rejectedInvoiceID == null && other.rejectedInvoiceID != null) || (this.rejectedInvoiceID != null && !this.rejectedInvoiceID.equals(other.rejectedInvoiceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.RejectedInvoice[ rejectedInvoiceID=" + rejectedInvoiceID + " ]";
    }
    
}
