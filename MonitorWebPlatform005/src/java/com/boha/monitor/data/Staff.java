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
@Table(name = "staff")
@NamedQueries({
    @NamedQuery(name = "Staff.findByCompany", 
            query = "SELECT s FROM Staff s WHERE s.company.companyID = :companyID ORDER BY s.lastName, s.firstName")
})
public class Staff implements Serializable {
    @OneToMany(mappedBy = "staff")
    private List<VideoUpload> videoUploadList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "staffID")
    private Integer staffID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "firstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lastName")
    private String lastName;
    @Size(max = 50)
    @Column(name = "cellphone")
    private String cellphone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Column(name = "activeFlag")
    private Integer activeFlag;
    @Size(max = 10)
    @Column(name = "pin")
    private String pin;
    @Column(name = "appInvitationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date appInvitationDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @OneToMany(mappedBy = "staff")
    private List<ErrorStoreAndroid> errorStoreAndroidList;
    @OneToMany(mappedBy = "staff")
    private List<PhotoUpload> photoUploadList;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumn(name = "staffTypeID", referencedColumnName = "staffTypeID")
    @ManyToOne(optional = false)
    private StaffType staffType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "staff")
    private List<ChatMessage> chatMessageList;
    @OneToMany(mappedBy = "staff")
    private List<ProjectTaskStatus> projectTaskStatusList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "staff")
    private List<ProjectStatus> projectStatusList;
    @OneToMany(mappedBy = "staff")
    private List<ChatMember> chatMemberList;
    @OneToMany(mappedBy = "staff")
    private List<GcmDevice> gcmDeviceList;
    @OneToMany(mappedBy = "staff")
    private List<Chat> chatList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "staff")
    private List<LocationTracker> locationTrackerList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "staff")
    private List<StaffProject> staffProjectList;

    public Staff() {
    }

    public Staff(Integer staffID) {
        this.staffID = staffID;
    }

    public Staff(Integer staffID, String firstName, String lastName, String email, Date dateRegistered) {
        this.staffID = staffID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateRegistered = dateRegistered;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
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

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Date getAppInvitationDate() {
        return appInvitationDate;
    }

    public void setAppInvitationDate(Date appInvitationDate) {
        this.appInvitationDate = appInvitationDate;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public List<ErrorStoreAndroid> getErrorStoreAndroidList() {
        return errorStoreAndroidList;
    }

    public void setErrorStoreAndroidList(List<ErrorStoreAndroid> errorStoreAndroidList) {
        this.errorStoreAndroidList = errorStoreAndroidList;
    }

    public List<PhotoUpload> getPhotoUploadList() {
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUpload> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public StaffType getStaffType() {
        return staffType;
    }

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }


    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    public List<ProjectTaskStatus> getProjectTaskStatusList() {
        return projectTaskStatusList;
    }

    public void setProjectTaskStatusList(List<ProjectTaskStatus> projectTaskStatusList) {
        this.projectTaskStatusList = projectTaskStatusList;
    }

    public List<ProjectStatus> getProjectStatusList() {
        return projectStatusList;
    }

    public void setProjectStatusList(List<ProjectStatus> projectStatusList) {
        this.projectStatusList = projectStatusList;
    }

    public List<ChatMember> getChatMemberList() {
        return chatMemberList;
    }

    public void setChatMemberList(List<ChatMember> chatMemberList) {
        this.chatMemberList = chatMemberList;
    }

    public List<GcmDevice> getGcmDeviceList() {
        return gcmDeviceList;
    }

    public void setGcmDeviceList(List<GcmDevice> gcmDeviceList) {
        this.gcmDeviceList = gcmDeviceList;
    }

    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }

    public List<LocationTracker> getLocationTrackerList() {
        return locationTrackerList;
    }

    public void setLocationTrackerList(List<LocationTracker> locationTrackerList) {
        this.locationTrackerList = locationTrackerList;
    }

    public List<StaffProject> getStaffProjectList() {
        return staffProjectList;
    }

    public void setStaffProjectList(List<StaffProject> staffProjectList) {
        this.staffProjectList = staffProjectList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staffID != null ? staffID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.staffID == null && other.staffID != null) || (this.staffID != null && !this.staffID.equals(other.staffID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Staff[ staffID=" + staffID + " ]";
    }

    public List<VideoUpload> getVideoUploadList() {
        return videoUploadList;
    }

    public void setVideoUploadList(List<VideoUpload> videoUploadList) {
        this.videoUploadList = videoUploadList;
    }
    
}
