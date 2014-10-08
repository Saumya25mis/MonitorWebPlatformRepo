/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "invoiceItem")
@NamedQueries({
    @NamedQuery(name = "InvoiceItem.findAll", query = "SELECT i FROM InvoiceItem i"),
    @NamedQuery(name = "InvoiceItem.findByInvoiceItemID", query = "SELECT i FROM InvoiceItem i WHERE i.invoiceItemID = :invoiceItemID"),
    @NamedQuery(name = "InvoiceItem.findByDescription", query = "SELECT i FROM InvoiceItem i WHERE i.description = :description"),
    @NamedQuery(name = "InvoiceItem.findByQuantity", query = "SELECT i FROM InvoiceItem i WHERE i.quantity = :quantity"),
    @NamedQuery(name = "InvoiceItem.findByAmount", query = "SELECT i FROM InvoiceItem i WHERE i.amount = :amount")})
public class InvoiceItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "invoiceItemID")
    private Integer invoiceItemID;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "quantity")
    private Integer quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields
    //consider using these annotations to enforce field validation
    @Column(name = "amount")
    private Double amount;
    @JoinColumn(name = "invoiceID", referencedColumnName = "invoiceID")
    @ManyToOne(optional = false)
    private Invoice invoice;
    @JoinColumn(name = "invoiceCodeID", referencedColumnName = "invoiceCodeID")
    @ManyToOne
    private InvoiceCode invoiceCode;
    @JoinColumn(name = "projectSiteID", referencedColumnName = "projectSiteID")
    @ManyToOne(optional = false)
    private ProjectSite projectSite;

    public InvoiceItem() {
    }

    public InvoiceItem(Integer invoiceItemID) {
        this.invoiceItemID = invoiceItemID;
    }

    public Integer getInvoiceItemID() {
        return invoiceItemID;
    }

    public void setInvoiceItemID(Integer invoiceItemID) {
        this.invoiceItemID = invoiceItemID;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public InvoiceCode getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(InvoiceCode invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public ProjectSite getProjectSite() {
        return projectSite;
    }

    public void setProjectSite(ProjectSite projectSite) {
        this.projectSite = projectSite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceItemID != null ? invoiceItemID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvoiceItem)) {
            return false;
        }
        InvoiceItem other = (InvoiceItem) object;
        if ((this.invoiceItemID == null && other.invoiceItemID != null) || (this.invoiceItemID != null && !this.invoiceItemID.equals(other.invoiceItemID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.InvoiceItem[ invoiceItemID=" + invoiceItemID + " ]";
    }
    
}
