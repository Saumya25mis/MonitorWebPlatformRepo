/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.RejectedInvoice;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author aubreymalabie
 */
public class RejectedInvoiceDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer rejectedInvoiceID;
    private Long dateRegistered;
    private InvoiceRejectReasonDTO invoiceRejectReason;
    private Integer invoiceID;

    public RejectedInvoiceDTO() {
    }

    public RejectedInvoiceDTO(Integer rejectedInvoiceID) {
        this.rejectedInvoiceID = rejectedInvoiceID;
    }

    public RejectedInvoiceDTO(RejectedInvoice a) {
        this.rejectedInvoiceID = a.getRejectedInvoiceID();
        this.dateRegistered = a.getDateRegistered().getTime();
        invoiceID = a.getInvoice().getInvoiceID();
        invoiceRejectReason = new InvoiceRejectReasonDTO(a.getInvoiceRejectReason());
    }

    public Integer getRejectedInvoiceID() {
        return rejectedInvoiceID;
    }

    public void setRejectedInvoiceID(Integer rejectedInvoiceID) {
        this.rejectedInvoiceID = rejectedInvoiceID;
    }

    public InvoiceRejectReasonDTO getInvoiceRejectReason() {
        return invoiceRejectReason;
    }

    public void setInvoiceRejectReason(InvoiceRejectReasonDTO invoiceRejectReason) {
        this.invoiceRejectReason = invoiceRejectReason;
    }

    public Long getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Long dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
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
        if (!(object instanceof RejectedInvoiceDTO)) {
            return false;
        }
        RejectedInvoiceDTO other = (RejectedInvoiceDTO) object;
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
