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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "gcmDevice")
@NamedQueries({
    @NamedQuery(name = "GcmDevice.findCompanyDevices", 
            query = "SELECT g FROM GcmDevice g WHERE g.company.companyID = :companyID"),
    
    @NamedQuery(name = "GcmDevice.findByMonitorIDs", 
            query = "SELECT g FROM GcmDevice g WHERE g.monitor.monitorID IN :list"),
    
    @NamedQuery(name = "GcmDevice.findByStaffIDs", 
            query = "SELECT g FROM GcmDevice g WHERE g.staff.staffID IN :list"),
    
    @NamedQuery(name = "GcmDevice.findByModel", 
            query = "SELECT g FROM GcmDevice g WHERE g.model = :model"),
    @NamedQuery(name = "GcmDevice.findBySerialNumberApp", 
            query = "SELECT g FROM GcmDevice g WHERE g.serialNumber = :serialNumber AND g.app = :app"),
    @NamedQuery(name = "GcmDevice.findByAndroidVersion", 
            query = "SELECT g FROM GcmDevice g WHERE g.androidVersion = :androidVersion")})
public class GcmDevice implements Serializable {
    @OneToMany(mappedBy = "gcmDevice")
    private List<LocationTracker> locationTrackerList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gcmDeviceID")
    private Integer gcmDeviceID;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "registrationID")
    private String registrationID;
    @Size(max = 100)
    @Column(name = "manufacturer")
    private String manufacturer;
    @Size(max = 100)
    @Column(name = "model")
    private String model;
    @Size(max = 100)
    @Column(name = "product")
    private String product;
    @Column(name = "messageCount")
    private Integer messageCount;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @Size(max = 255)
    @Column(name = "serialNumber")
    private String serialNumber;
    @Size(max = 100)
    @Column(name = "androidVersion")
    private String androidVersion;
    @Size(max = 100)
    @Column(name = "app")
    private String app;
    @JoinColumn(name = "staffID", referencedColumnName = "staffID")
    @ManyToOne
    private Staff staff;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne
    private Company company;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne
    private Project project;
    @JoinColumn(name = "monitorID", referencedColumnName = "monitorID")
    @ManyToOne
    private Monitor monitor;

    public GcmDevice() {
    }

    public GcmDevice(Integer gcmDeviceID) {
        this.gcmDeviceID = gcmDeviceID;
    }

    public GcmDevice(Integer gcmDeviceID, String registrationID, Date dateRegistered) {
        this.gcmDeviceID = gcmDeviceID;
        this.registrationID = registrationID;
        this.dateRegistered = dateRegistered;
    }

    public Integer getGcmDeviceID() {
        return gcmDeviceID;
    }

    public void setGcmDeviceID(Integer gcmDeviceID) {
        this.gcmDeviceID = gcmDeviceID;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Company getCompany() {
        return company;
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

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gcmDeviceID != null ? gcmDeviceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GcmDevice)) {
            return false;
        }
        GcmDevice other = (GcmDevice) object;
        if ((this.gcmDeviceID == null && other.gcmDeviceID != null) || (this.gcmDeviceID != null && !this.gcmDeviceID.equals(other.gcmDeviceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.GcmDevice[ gcmDeviceID=" + gcmDeviceID + " ]";
    }

    public List<LocationTracker> getLocationTrackerList() {
        return locationTrackerList;
    }

    public void setLocationTrackerList(List<LocationTracker> locationTrackerList) {
        this.locationTrackerList = locationTrackerList;
    }
    
}
