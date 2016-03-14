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
@Table(name = "tenderCompany")
@NamedQueries({
    @NamedQuery(name = "TenderCompany.findAll", query = "SELECT t FROM TenderCompany t"),
    @NamedQuery(name = "TenderCompany.findByTenderCompanyID", query = "SELECT t FROM TenderCompany t WHERE t.tenderCompanyID = :tenderCompanyID"),
    @NamedQuery(name = "TenderCompany.findByName", query = "SELECT t FROM TenderCompany t WHERE t.name = :name"),
    @NamedQuery(name = "TenderCompany.findByEmail", query = "SELECT t FROM TenderCompany t WHERE t.email = :email"),
    @NamedQuery(name = "TenderCompany.findByCellphone", query = "SELECT t FROM TenderCompany t WHERE t.cellphone = :cellphone"),
    @NamedQuery(name = "TenderCompany.findByDateRegistered", query = "SELECT t FROM TenderCompany t WHERE t.dateRegistered = :dateRegistered")})
public class TenderCompany implements Serializable {

    @OneToMany(mappedBy = "tenderCompanyID", fetch = FetchType.EAGER)
    private List<PhotoUpload> photoUploadList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tenderCompanyID")
    private Integer tenderCompanyID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "cellphone")
    private String cellphone;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenderCompany", fetch = FetchType.EAGER)
    private List<TenderCompanyProject> tenderCompanyProjectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenderCompany", fetch = FetchType.EAGER)
    private List<CompanyExperience> companyExperienceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenderCompany", fetch = FetchType.EAGER)
    private List<TenderCompanyType> tenderCompanyTypeList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenderCompany", fetch = FetchType.EAGER)
    private List<Invoice> invoiceList;

    public TenderCompany() {
    }

    public TenderCompany(Integer tenderCompanyID) {
        this.tenderCompanyID = tenderCompanyID;
    }

    public TenderCompany(Integer tenderCompanyID, String name, String email, String cellphone, Date dateRegistered) {
        this.tenderCompanyID = tenderCompanyID;
        this.name = name;
        this.email = email;
        this.cellphone = cellphone;
        this.dateRegistered = dateRegistered;
    }

    public Integer getTenderCompanyID() {
        return tenderCompanyID;
    }

    public void setTenderCompanyID(Integer tenderCompanyID) {
        this.tenderCompanyID = tenderCompanyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public List<TenderCompanyProject> getTenderCompanyProjectList() {
        return tenderCompanyProjectList;
    }

    public void setTenderCompanyProjectList(List<TenderCompanyProject> tenderCompanyProjectList) {
        this.tenderCompanyProjectList = tenderCompanyProjectList;
    }

    public List<CompanyExperience> getCompanyExperienceList() {
        return companyExperienceList;
    }

    public void setCompanyExperienceList(List<CompanyExperience> companyExperienceList) {
        this.companyExperienceList = companyExperienceList;
    }

    public List<TenderCompanyType> getTenderCompanyTypeList() {
        return tenderCompanyTypeList;
    }

    public void setTenderCompanyTypeList(List<TenderCompanyType> tenderCompanyTypeList) {
        this.tenderCompanyTypeList = tenderCompanyTypeList;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tenderCompanyID != null ? tenderCompanyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TenderCompany)) {
            return false;
        }
        TenderCompany other = (TenderCompany) object;
        if ((this.tenderCompanyID == null && other.tenderCompanyID != null) || (this.tenderCompanyID != null && !this.tenderCompanyID.equals(other.tenderCompanyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.TenderCompany[ tenderCompanyID=" + tenderCompanyID + " ]";
    }

    public List<PhotoUpload> getPhotoUploadList() {
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUpload> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }
    
}
