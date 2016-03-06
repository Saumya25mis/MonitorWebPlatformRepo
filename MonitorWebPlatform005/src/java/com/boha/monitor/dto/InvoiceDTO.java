/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreymalabie
 */
public class InvoiceDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer invoiceID;
    private Long invoiceDate;
    private double amount;
    private Long paymentDate;
    private Integer daysLate;
    private Integer daysEarly;
    private String invoiceNumber;
    private Integer year;
    private Integer month;
    private List<RejectedInvoiceDTO> rejectedInvoiceList;
    private Integer tenderCompanyID;

    public InvoiceDTO() {
    }

    public InvoiceDTO(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public InvoiceDTO(Invoice a) {
        this.invoiceID = a.getInvoiceID();
        this.invoiceDate = a.getInvoiceDate().getTime();
        this.amount = a.getAmount();
        daysEarly = a.getDaysEarly();
        daysLate = a.getDaysLate();
        tenderCompanyID = a.getTenderCompany().getTenderCompanyID();
        year = a.getYear();
        month = a.getMonth();
        invoiceNumber = a.getInvoiceNumber();
        if (a.getPaymentDate() != null) {
            paymentDate = a.getPaymentDate().getTime();
        }
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Integer getDaysLate() {
        return daysLate;
    }

    public void setDaysLate(Integer daysLate) {
        this.daysLate = daysLate;
    }

    public Integer getDaysEarly() {
        return daysEarly;
    }

    public void setDaysEarly(Integer daysEarly) {
        this.daysEarly = daysEarly;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public List<RejectedInvoiceDTO> getRejectedInvoiceList() {
        return rejectedInvoiceList;
    }

    public void setRejectedInvoiceList(List<RejectedInvoiceDTO> rejectedInvoiceList) {
        this.rejectedInvoiceList = rejectedInvoiceList;
    }

    public Long getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Long invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Long getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Long paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getTenderCompanyID() {
        return tenderCompanyID;
    }

    public void setTenderCompanyID(Integer tenderCompanyID) {
        this.tenderCompanyID = tenderCompanyID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceID != null ? invoiceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceDTO)) {
            return false;
        }
        InvoiceDTO other = (InvoiceDTO) object;
        if ((this.invoiceID == null && other.invoiceID != null) || (this.invoiceID != null && !this.invoiceID.equals(other.invoiceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Invoice[ invoiceID=" + invoiceID + " ]";
    }
    
}
