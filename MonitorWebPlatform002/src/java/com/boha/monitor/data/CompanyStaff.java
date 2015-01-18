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
@Table(name = "companyStaff")
@NamedQueries({
    @NamedQuery(name = "CompanyStaff.findByCompany", 
            query = "SELECT c FROM CompanyStaff c where c.company.companyID = :companyID "
                    + "order by c.lastName, c.firstName"),
    @NamedQuery(name = "CompanyStaff.findByCompanyStaffID", 
            query = "SELECT c FROM CompanyStaff c WHERE c.companyStaffID = :companyStaffID"),
    @NamedQuery(name = "CompanyStaff.login", 
            query = "SELECT c FROM CompanyStaff c WHERE c.email = :email and c.pin = :pin"),
    @NamedQuery(name = "CompanyStaff.findByLastName", query = "SELECT c FROM CompanyStaff c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "CompanyStaff.findByEmail", query = "SELECT c FROM CompanyStaff c WHERE c.email = :email"),
    @NamedQuery(name = "CompanyStaff.findByCellphone", query = "SELECT c FROM CompanyStaff c WHERE c.cellphone = :cellphone")})
public class CompanyStaff implements Serializable {
    @Column(name = "appInvitationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appInvitationDate;
    @OneToMany(mappedBy = "companyStaff")
    private List<SubTaskStatus> subTaskStatusList;
    @OneToMany(mappedBy = "companyStaff")
    private List<ProjectSiteTaskStatus> projectSiteTaskStatusList;
    @OneToMany(mappedBy = "companyStaff")
    private List<PhotoUpload> photoUploadList;
     @OneToMany(mappedBy = "companyStaff")
    private List<ErrorStoreAndroid> errorStoreAndroidList;
    @Column(name = "pin")
    private String pin;
    @OneToMany(mappedBy = "companyStaff")
    private List<GcmDevice> gcmDeviceList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "companyStaffID")
    private Integer companyStaffID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "firstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "activeFlag")
    private Integer activeFlag;
    @Basic(optional = false)
    @NotNull
    @Column(name = "email")
    private String email;
    @Size(max = 50)
    @Column(name = "cellphone")
    private String cellphone;
    
    
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne(optional = false)
    private Company company;
  
    public CompanyStaff() {
    }

    public List<ErrorStoreAndroid> getErrorStoreAndroidList() {
        return errorStoreAndroidList;
    }

    public void setErrorStoreAndroidList(List<ErrorStoreAndroid> errorStoreAndroidList) {
        this.errorStoreAndroidList = errorStoreAndroidList;
    }

    public CompanyStaff(Integer companyStaffID) {
        this.companyStaffID = companyStaffID;
    }

    public CompanyStaff(Integer companyStaffID, String firstName, String lastName, String email) {
        this.companyStaffID = companyStaffID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public Integer getCompanyStaffID() {
        return companyStaffID;
    }

    public void setCompanyStaffID(Integer companyStaffID) {
        this.companyStaffID = companyStaffID;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyStaffID != null ? companyStaffID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyStaff)) {
            return false;
        }
        CompanyStaff other = (CompanyStaff) object;
        if ((this.companyStaffID == null && other.companyStaffID != null) || (this.companyStaffID != null && !this.companyStaffID.equals(other.companyStaffID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.CompanyStaff[ companyStaffID=" + companyStaffID + " ]";
    }

    public List<GcmDevice> getGcmDeviceList() {
        return gcmDeviceList;
    }

    public void setGcmDeviceList(List<GcmDevice> gcmDeviceList) {
        this.gcmDeviceList = gcmDeviceList;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<PhotoUpload> getPhotoUploadList() {
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUpload> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public List<ProjectSiteTaskStatus> getProjectSiteTaskStatusList() {
        return projectSiteTaskStatusList;
    }

    public void setProjectSiteTaskStatusList(List<ProjectSiteTaskStatus> projectSiteTaskStatusList) {
        this.projectSiteTaskStatusList = projectSiteTaskStatusList;
    }

    public List<SubTaskStatus> getSubTaskStatusList() {
        return subTaskStatusList;
    }

    public void setSubTaskStatusList(List<SubTaskStatus> subTaskStatusList) {
        this.subTaskStatusList = subTaskStatusList;
    }

    public Date getAppInvitationDate() {
        return appInvitationDate;
    }

    public void setAppInvitationDate(Date appInvitationDate) {
        this.appInvitationDate = appInvitationDate;
    }
    
}
