/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.InvoiceRejectReason;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aubreymalabie
 */
public class InvoiceRejectReasonDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer invoiceRejectReasonID;
    private String name;
    private List<RejectedInvoiceDTO> rejectedInvoiceList;

    public InvoiceRejectReasonDTO() {
    }

    public InvoiceRejectReasonDTO( InvoiceRejectReason a) {
        this.invoiceRejectReasonID = a.getInvoiceRejectReasonID();
        name = a.getName();
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

    public List<RejectedInvoiceDTO> getRejectedInvoiceList() {
        return rejectedInvoiceList;
    }

    public void setRejectedInvoiceList(List<RejectedInvoiceDTO> rejectedInvoiceList) {
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
        if (!(object instanceof InvoiceRejectReasonDTO)) {
            return false;
        }
        InvoiceRejectReasonDTO other = (InvoiceRejectReasonDTO) object;
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
