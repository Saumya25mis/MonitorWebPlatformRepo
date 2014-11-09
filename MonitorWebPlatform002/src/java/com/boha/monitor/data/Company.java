/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "company")
@NamedQueries({
    @NamedQuery(name = "Company.findAll", 
            query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findStaff", 
            query = "SELECT c FROM Company c LEFT JOIN FETCH c.companyStaffList"),
    @NamedQuery(name = "Company.findByCompanyID", 
            query = "SELECT c FROM Company c WHERE c.companyID = :companyID"),
    @NamedQuery(name = "Company.findByCompanyName", 
            query = "SELECT c FROM Company c WHERE c.companyName = :companyName")})
public class Company implements Serializable {
    @JoinColumn(name = "countryID", referencedColumnName = "countryID")
    @ManyToOne
    private Country country;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Engineer> engineerList;
    
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "address")
    private String address;
    
    @Size(min = 1, max = 50)
    @Column(name = "taxNumber")
    private String taxNumber;
    
    @Size(min = 1, max = 50)
    @Column(name = "vatNumber")
    private String vatNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<BankDetail> bankDetailList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<PhotoUpload> photoUploadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @OrderBy("clientName")
    private List<Client> clientList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @OrderBy("invoiceDate DESC")
    private List<Invoice> invoiceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @OrderBy("taskName")
    private List<Task> taskList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @OrderBy("projectStatusName")
    private List<ProjectStatusType> projectStatusTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @OrderBy("taskStatusName")
    private List<TaskStatus> taskStatusList;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @OrderBy("lastName, firstName")
    private List<Beneficiary> beneficiaryList;
   
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @OrderBy("checkPointName")
    private List<CheckPoint> checkPointList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @OrderBy("dateRegistered desc")
    private List<GcmDevice> gcmDeviceList;
    @OneToMany(mappedBy = "company")
    private List<ErrorStoreAndroid> errorStoreAndroidList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "companyID")
    private Integer companyID;
    @Size(max = 255)
    @Column(name = "companyName")
    private String companyName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @OrderBy("dateRegistered")
    private List<Project> projectList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company", 
            fetch = FetchType.EAGER)
    @OrderBy("lastName, firstName")
    private List<CompanyStaff> companyStaffList;

    public Company() {
    }

    public Company(Integer companyID) {
        this.companyID = companyID;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

   

    public List<CompanyStaff> getCompanyStaffList() {
        return companyStaffList;
    }

    public void setCompanyStaffList(List<CompanyStaff> companyStaffList) {
        this.companyStaffList = companyStaffList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyID != null ? companyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.companyID == null && other.companyID != null) || (this.companyID != null && !this.companyID.equals(other.companyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Company[ companyID=" + companyID + " ]";
    }

    public List<ErrorStoreAndroid> getErrorStoreAndroidList() {
        return errorStoreAndroidList;
    }

    public void setErrorStoreAndroidList(List<ErrorStoreAndroid> errorStoreAndroidList) {
        this.errorStoreAndroidList = errorStoreAndroidList;
    }

    public List<GcmDevice> getGcmDeviceList() {
        return gcmDeviceList;
    }

    public void setGcmDeviceList(List<GcmDevice> gcmDeviceList) {
        this.gcmDeviceList = gcmDeviceList;
    }

    public List<Beneficiary> getBeneficiaryList() {
        return beneficiaryList;
    }

    public void setBeneficiaryList(List<Beneficiary> beneficiaryList) {
        this.beneficiaryList = beneficiaryList;
    }

   

    public List<CheckPoint> getCheckPointList() {
        return checkPointList;
    }

    public void setCheckPointList(List<CheckPoint> checkPointList) {
        this.checkPointList = checkPointList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<ProjectStatusType> getProjectStatusTypeList() {
        return projectStatusTypeList;
    }

    public void setProjectStatusTypeList(List<ProjectStatusType> projectStatusTypeList) {
        this.projectStatusTypeList = projectStatusTypeList;
    }

    public List<TaskStatus> getTaskStatusList() {
        return taskStatusList;
    }

    public void setTaskStatusList(List<TaskStatus> taskStatusList) {
        this.taskStatusList = taskStatusList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public List<PhotoUpload> getPhotoUploadList() {
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUpload> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public List<BankDetail> getBankDetailList() {
        return bankDetailList;
    }

    public void setBankDetailList(List<BankDetail> bankDetailList) {
        this.bankDetailList = bankDetailList;
    }

    public List<Engineer> getEngineerList() {
        return engineerList;
    }

    public void setEngineerList(List<Engineer> engineerList) {
        this.engineerList = engineerList;
    }

    
}
