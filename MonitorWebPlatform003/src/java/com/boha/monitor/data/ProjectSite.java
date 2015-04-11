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
@Table(name = "projectSite")
@NamedQueries({
    @NamedQuery(name = "ProjectSite.findByProjectAndSiteName",
            query = "SELECT p FROM ProjectSite p where p.project.projectID = :projectID and p.projectSiteName = :name"),
    @NamedQuery(name = "ProjectSite.findByProject",
            query = "SELECT p FROM ProjectSite p WHERE p.project.projectID = :projectID order by p.projectSiteName"),
    @NamedQuery(name = "ProjectSite.countByProject",
            query = "SELECT count(p) FROM ProjectSite p WHERE p.project.projectID = :projectID"),
    @NamedQuery(name = "ProjectSite.findByCompany",
            query = "SELECT p FROM ProjectSite p WHERE p.project.company.companyID = :companyID order by p.project.projectName, p.projectSiteName"),
    })
public class ProjectSite implements Serializable {
    @OneToMany(mappedBy = "projectSite", fetch = FetchType.LAZY)
    private List<Chat> chatList;
    @JoinColumn(name = "happyLetterID", referencedColumnName = "happyLetterID")
    @ManyToOne
    private HappyLetter happyLetter;
    @JoinColumn(name = "beneficiaryID", referencedColumnName = "beneficiaryID")
    @ManyToOne
    private Beneficiary beneficiary;
    @OneToMany(mappedBy = "projectSite")
    private List<ContractorClaimSite> contractorClaimSiteList;
    @OneToMany(mappedBy = "projectSite")
    private List<PhotoUpload> photoUploadList;
     @Column(name = "accuracy")
    private Float accuracy;
    @Size(min = 1, max = 100)
    @Column(name = "standErfNumber")
    private String standErfNumber;
    @Column(name = "locationConfirmed")
    private Integer locationConfirmed;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectSite")
    private List<InvoiceItem> invoiceItemList;
   

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectSite")
    private List<GcmDevice> gcmDeviceList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "projectSiteID")
    private Integer projectSiteID;
    @Size(max = 255)
    @Column(name = "projectSiteName")
    private String projectSiteName;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "activeFlag")
    private Integer activeFlag;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectSite")
    @OrderBy("dateRegistered DESC")
    private List<ProjectSiteTask> projectSiteTaskList;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne(optional = false)
    private Project project;
    
    public ProjectSite() {
    }

    public ProjectSite(Integer projectSiteID) {
        this.projectSiteID = projectSiteID;
    }

    public Integer getProjectSiteID() {
        return projectSiteID;
    }

    public void setProjectSiteID(Integer projectSiteID) {
        this.projectSiteID = projectSiteID;
    }

    public String getProjectSiteName() {
        return projectSiteName;
    }

    public void setProjectSiteName(String projectSiteName) {
        this.projectSiteName = projectSiteName;
    }

    public Integer getLocationConfirmed() {
        return locationConfirmed;
    }

    public void setLocationConfirmed(Integer locationConfirmed) {
        this.locationConfirmed = locationConfirmed;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public HappyLetter getHappyLetter() {
        return happyLetter;
    }

    public void setHappyLetter(HappyLetter happyLetter) {
        this.happyLetter = happyLetter;
    }

    public List<ProjectSiteTask> getProjectSiteTaskList() {
        return projectSiteTaskList;
    }

    public void setProjectSiteTaskList(List<ProjectSiteTask> projectSiteTaskList) {
        this.projectSiteTaskList = projectSiteTaskList;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectSiteID != null ? projectSiteID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectSite)) {
            return false;
        }
        ProjectSite other = (ProjectSite) object;
        if ((this.projectSiteID == null && other.projectSiteID != null) || (this.projectSiteID != null && !this.projectSiteID.equals(other.projectSiteID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ProjectSite[ projectSiteID=" + projectSiteID + " ]";
    }

    public List<GcmDevice> getGcmDeviceList() {
        return gcmDeviceList;
    }

    public void setGcmDeviceList(List<GcmDevice> gcmDeviceList) {
        this.gcmDeviceList = gcmDeviceList;
    }

    public List<InvoiceItem> getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(List<InvoiceItem> invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
    }

    public String getStandErfNumber() {
        return standErfNumber;
    }

    public void setStandErfNumber(String standErfNumber) {
        this.standErfNumber = standErfNumber;
    }

    public List<PhotoUpload> getPhotoUploadList() {
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUpload> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public List<ContractorClaimSite> getContractorClaimSiteList() {
        return contractorClaimSiteList;
    }

    public void setContractorClaimSiteList(List<ContractorClaimSite> contractorClaimSiteList) {
        this.contractorClaimSiteList = contractorClaimSiteList;
    }

    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }

  
   
   

}
