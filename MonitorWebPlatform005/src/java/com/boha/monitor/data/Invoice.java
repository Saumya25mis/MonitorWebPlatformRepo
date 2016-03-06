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
@Entity
@Table(name = "invoice")
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i"),
    @NamedQuery(name = "Invoice.findByInvoiceID", query = "SELECT i FROM Invoice i WHERE i.invoiceID = :invoiceID"),
    @NamedQuery(name = "Invoice.findByInvoiceDate", query = "SELECT i FROM Invoice i WHERE i.invoiceDate = :invoiceDate"),
    @NamedQuery(name = "Invoice.findByAmount", query = "SELECT i FROM Invoice i WHERE i.amount = :amount"),
    @NamedQuery(name = "Invoice.findByPaymentDate", query = "SELECT i FROM Invoice i WHERE i.paymentDate = :paymentDate"),
    @NamedQuery(name = "Invoice.findByDaysLate", query = "SELECT i FROM Invoice i WHERE i.daysLate = :daysLate"),
    @NamedQuery(name = "Invoice.findByDaysEarly", query = "SELECT i FROM Invoice i WHERE i.daysEarly = :daysEarly"),
    @NamedQuery(name = "Invoice.findByInvoiceNumber", query = "SELECT i FROM Invoice i WHERE i.invoiceNumber = :invoiceNumber"),
    @NamedQuery(name = "Invoice.findByYear", query = "SELECT i FROM Invoice i WHERE i.year = :year"),
    @NamedQuery(name = "Invoice.findByMonth", query = "SELECT i FROM Invoice i WHERE i.month = :month")})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "invoiceID")
    private Integer invoiceID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "invoiceDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amount")
    private double amount;
    @Column(name = "paymentDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
    @Column(name = "daysLate")
    private Integer daysLate;
    @Column(name = "daysEarly")
    private Integer daysEarly;
    @Size(max = 50)
    @Column(name = "invoiceNumber")
    private String invoiceNumber;
    @Column(name = "year")
    private Integer year;
    @Column(name = "month")
    private Integer month;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice", fetch = FetchType.EAGER)
    private List<RejectedInvoice> rejectedInvoiceList;
    @JoinColumn(name = "paymentCompanyID", referencedColumnName = "tenderCompanyID")
    @ManyToOne(fetch = FetchType.EAGER)
    private TenderCompany paymentCompany;
    @JoinColumn(name = "tenderCompanyID", referencedColumnName = "tenderCompanyID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TenderCompany tenderCompany;

    public Invoice() {
    }

    public Invoice(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Invoice(Integer invoiceID, Date invoiceDate, double amount) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.amount = amount;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
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

    public List<RejectedInvoice> getRejectedInvoiceList() {
        return rejectedInvoiceList;
    }

    public void setRejectedInvoiceList(List<RejectedInvoice> rejectedInvoiceList) {
        this.rejectedInvoiceList = rejectedInvoiceList;
    }

    public TenderCompany getPaymentCompany() {
        return paymentCompany;
    }

    public void setPaymentCompany(TenderCompany paymentCompany) {
        this.paymentCompany = paymentCompany;
    }

    public TenderCompany getTenderCompany() {
        return tenderCompany;
    }

    public void setTenderCompany(TenderCompany tenderCompany) {
        this.tenderCompany = tenderCompany;
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
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
