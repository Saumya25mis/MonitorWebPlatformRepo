/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.data;

import java.io.Serializable;
import java.util.Collection;
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
@Table(name = "monitor")
@NamedQueries({
    @NamedQuery(name = "Monitor.findByCompany",
            query = "SELECT m FROM Monitor m WHERE m.company.companyID = :companyID and m.activeFlag = TRUE "
            + "ORDER BY m.lastName, m.firstName"),

    @NamedQuery(name = "Monitor.login",
            query = "SELECT m FROM Monitor m WHERE m.email = :email and m.pin = :pin and m.activeFlag = TRUE")
})
public class Monitor implements Serializable {
    @OneToMany(mappedBy = "monitor")
    private List<SimpleMessageDestination> simpleMessageDestinationList;
    @OneToMany(mappedBy = "monitor")
    private List<SimpleMessage> simpleMessageList;

    @Column(name = "gender")
    private Short gender;
    @OneToMany(mappedBy = "monitor")
    private List<VideoUpload> videoUploadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monitor")
    private List<MonitorTrade> monitorTradeList;
    @OneToMany(mappedBy = "monitor")
    private List<PhotoUpload> photoUploadList;
    @OneToMany(mappedBy = "monitor")
    private List<LocationTracker> locationTrackerList;
    @OneToMany(mappedBy = "monitor")
    private List<ErrorStoreAndroid> errorStoreAndroidList;
    @OneToMany(mappedBy = "monitor")
    private Collection<ChatMessage> chatMessageCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "monitorID")
    private Integer monitorID;
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
    @Size(max = 512)
    @Column(name = "address")
    private String address;

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
    @Size(max = 45)
    @Column(name = "IDNumber")
    private String iDNumber;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne(optional = false)
    private Company company;
    @OneToMany(mappedBy = "monitor")
    private List<ProjectTaskStatus> projectTaskStatusList;
    @OneToMany(mappedBy = "monitor")
    private List<ChatMember> chatMemberList;
    @OneToMany(mappedBy = "monitor")
    private List<GcmDevice> gcmDeviceList;
    @OneToMany(mappedBy = "monitor")
    private List<Chat> chatList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "monitor")
    private List<MonitorProject> monitorProjectList;

    public Monitor() {
    }

    public Monitor(Integer monitorID) {
        this.monitorID = monitorID;
    }

    public Monitor(Integer monitorID, String firstName, String lastName, String email, Date dateRegistered) {
        this.monitorID = monitorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateRegistered = dateRegistered;
    }

    public Integer getMonitorID() {
        return monitorID;
    }

    public void setMonitorID(Integer monitorID) {
        this.monitorID = monitorID;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getiDNumber() {
        return iDNumber;
    }

    public void setiDNumber(String iDNumber) {
        this.iDNumber = iDNumber;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getIDNumber() {
        return iDNumber;
    }

    public void setIDNumber(String iDNumber) {
        this.iDNumber = iDNumber;
    }

    public List<ProjectTaskStatus> getProjectTaskStatusList() {
        return projectTaskStatusList;
    }

    public void setProjectTaskStatusList(List<ProjectTaskStatus> projectTaskStatusList) {
        this.projectTaskStatusList = projectTaskStatusList;
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

    public List<MonitorProject> getMonitorProjectList() {
        return monitorProjectList;
    }

    public void setMonitorProjectList(List<MonitorProject> monitorProjectList) {
        this.monitorProjectList = monitorProjectList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monitorID != null ? monitorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Monitor)) {
            return false;
        }
        Monitor other = (Monitor) object;
        if ((this.monitorID == null && other.monitorID != null) || (this.monitorID != null && !this.monitorID.equals(other.monitorID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Monitor[ monitorID=" + monitorID + " ]";
    }

    public Collection<ChatMessage> getChatMessageCollection() {
        return chatMessageCollection;
    }

    public void setChatMessageCollection(Collection<ChatMessage> chatMessageCollection) {
        this.chatMessageCollection = chatMessageCollection;
    }

    public List<ErrorStoreAndroid> getErrorStoreAndroidList() {
        return errorStoreAndroidList;
    }

    public void setErrorStoreAndroidList(List<ErrorStoreAndroid> errorStoreAndroidList) {
        this.errorStoreAndroidList = errorStoreAndroidList;
    }

    public List<LocationTracker> getLocationTrackerList() {
        return locationTrackerList;
    }

    public void setLocationTrackerList(List<LocationTracker> locationTrackerList) {
        this.locationTrackerList = locationTrackerList;
    }

    public List<PhotoUpload> getPhotoUploadList() {
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUpload> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public List<MonitorTrade> getMonitorTradeList() {
        return monitorTradeList;
    }

    public void setMonitorTradeList(List<MonitorTrade> monitorTradeList) {
        this.monitorTradeList = monitorTradeList;
    }

    public List<VideoUpload> getVideoUploadList() {
        return videoUploadList;
    }

    public void setVideoUploadList(List<VideoUpload> videoUploadList) {
        this.videoUploadList = videoUploadList;
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public List<SimpleMessage> getSimpleMessageList() {
        return simpleMessageList;
    }

    public void setSimpleMessageList(List<SimpleMessage> simpleMessageList) {
        this.simpleMessageList = simpleMessageList;
    }

    public List<SimpleMessageDestination> getSimpleMessageDestinationList() {
        return simpleMessageDestinationList;
    }

    public void setSimpleMessageDestinationList(List<SimpleMessageDestination> simpleMessageDestinationList) {
        this.simpleMessageDestinationList = simpleMessageDestinationList;
    }

}
