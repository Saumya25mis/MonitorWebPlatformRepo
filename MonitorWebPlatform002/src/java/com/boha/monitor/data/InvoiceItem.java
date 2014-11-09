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
import javax.validation.constraints.NotNull;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "invoiceItem")
@NamedQueries({
    @NamedQuery(name = "InvoiceItem.findAll", 
            query = "SELECT i FROM InvoiceItem i"),
       })
public class InvoiceItem implements Serializable {
   
    @Basic(optional = false)
    @NotNull
    @Column(name = "unitPrice")
    private double unitPrice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tax")
    private double tax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nettPrice")
    private double nettPrice;
    @JoinColumn(name = "projectSiteTaskID", referencedColumnName = "projectSiteTaskID")
    @ManyToOne
    private ProjectSiteTask projectSiteTask;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "invoiceItemID")
    private Integer invoiceItemID;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @JoinColumn(name = "invoiceID", referencedColumnName = "invoiceID")
    @ManyToOne(optional = false)
    private Invoice invoice;
    
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


    public ProjectSite getProjectSite() {
        return projectSite;
    }

    public void setProjectSite(ProjectSite projectSite) {
        this.projectSite = projectSite;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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


    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getNettPrice() {
        return nettPrice;
    }

    public void setNettPrice(double nettPrice) {
        this.nettPrice = nettPrice;
    }

    public ProjectSiteTask getProjectSiteTask() {
        return projectSiteTask;
    }

    public void setProjectSiteTask(ProjectSiteTask projectSiteTask) {
        this.projectSiteTask = projectSiteTask;
    }


    
}
