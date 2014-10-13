/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.dto;

import com.boha.monitor.data.Invoice;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aubreyM
 */

public class InvoiceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer invoiceID;
    private Date invoiceDate;
    private Date invoiceDueDate;
    private Date dateRegistered;
    private Double totalAmount;
    private ClientDTO client;
    private CompanyDTO company;
    private ProjectDTO project;
    private List<InvoiceItemDTO> invoiceItemList;
    private List<SiteCheckPointDTO> siteCheckPointList;

    public InvoiceDTO() {
    }

    public InvoiceDTO(Invoice a) {
        this.invoiceID = a.getInvoiceID();
        invoiceDate = a.getInvoiceDate();
        invoiceDueDate = a.getInvoiceDueDate();
        dateRegistered = a.getDateRegistered();
        client = new ClientDTO(a.getClient());
        company = new CompanyDTO(a.getCompany());
        project = new ProjectDTO(a.getProject());
        totalAmount = a.getTotalAmount();
    }

    public InvoiceDTO(Integer invoiceID, Date invoiceDate) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
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

    public List<InvoiceItemDTO> getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(List<InvoiceItemDTO> invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
    }

    public List<SiteCheckPointDTO> getSiteCheckPointList() {
        return siteCheckPointList;
    }

    public void setSiteCheckPointList(List<SiteCheckPointDTO> siteCheckPointList) {
        this.siteCheckPointList = siteCheckPointList;
    }

   
    
}
