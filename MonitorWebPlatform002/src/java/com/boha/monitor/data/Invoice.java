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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "invoice")
@NamedQueries({
    @NamedQuery(name = "Invoice.findByProject", 
            query = "SELECT i FROM Invoice i where i.project.projectID = :projectID order by i.invoiceDate desc"),
     @NamedQuery(name = "Invoice.findByCompany", 
            query = "SELECT i FROM Invoice i where i.company.companyID = :companyID order by i.invoiceDate desc")


})
public class Invoice implements Serializable {
    @Size(max = 255)
    @Column(name = "documentURI")
    private String documentURI;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "invoiceNumber")
    private String invoiceNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "totalAmount")
    private double totalAmount;
    @JoinColumn(name = "clientID", referencedColumnName = "clientID")
    @ManyToOne(optional = false)
    private Client client;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne(optional = false)
    private Project project;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "reference")
    private String reference;
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
    @Column(name = "invoiceDueDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDueDate;
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @Column(name = "invoicePaidDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoicePaidDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
    private List<InvoiceItem> invoiceItemList;
    

    public Invoice() {
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

    public Date getInvoicePaidDate() {
        return invoicePaidDate;
    }

    public void setInvoicePaidDate(Date invoicePaidDate) {
        this.invoicePaidDate = invoicePaidDate;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Invoice(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Invoice(Integer invoiceID, Date invoiceDate) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
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

    public Date getInvoiceDueDate() {
        return invoiceDueDate;
    }

    public void setInvoiceDueDate(Date invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public List<InvoiceItem> getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(List<InvoiceItem> invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDocumentURI() {
        return documentURI;
    }

    public void setDocumentURI(String documentURI) {
        this.documentURI = documentURI;
    }

}
