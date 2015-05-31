/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "errorStoreAndroid")
@NamedQueries({
    @NamedQuery(name = "ErrorStoreAndroid.findAll", query = "SELECT e FROM ErrorStoreAndroid e"),
    @NamedQuery(name = "ErrorStoreAndroid.findByErrorStoreAndroidID", query = "SELECT e FROM ErrorStoreAndroid e WHERE e.errorStoreAndroidID = :errorStoreAndroidID"),
    @NamedQuery(name = "ErrorStoreAndroid.findByErrorDate", query = "SELECT e FROM ErrorStoreAndroid e WHERE e.errorDate = :errorDate"),
    @NamedQuery(name = "ErrorStoreAndroid.findByPackageName", query = "SELECT e FROM ErrorStoreAndroid e WHERE e.packageName = :packageName"),
    @NamedQuery(name = "ErrorStoreAndroid.findByAppVersionName", query = "SELECT e FROM ErrorStoreAndroid e WHERE e.appVersionName = :appVersionName"),
    @NamedQuery(name = "ErrorStoreAndroid.findByAppVersionCode", query = "SELECT e FROM ErrorStoreAndroid e WHERE e.appVersionCode = :appVersionCode"),
    @NamedQuery(name = "ErrorStoreAndroid.findByBrand", query = "SELECT e FROM ErrorStoreAndroid e WHERE e.brand = :brand"),
    @NamedQuery(name = "ErrorStoreAndroid.findByPhoneModel", query = "SELECT e FROM ErrorStoreAndroid e WHERE e.phoneModel = :phoneModel"),
    @NamedQuery(name = "ErrorStoreAndroid.findByAndroidVersion", query = "SELECT e FROM ErrorStoreAndroid e WHERE e.androidVersion = :androidVersion")})
public class ErrorStoreAndroid implements Serializable {
    @JoinColumn(name = "monitorID", referencedColumnName = "monitorID")
    @ManyToOne
    private Monitor monitor;
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "errorStoreAndroidID")
    private Integer errorStoreAndroidID;
    @Column(name = "errorDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date errorDate;
    @Size(max = 150)
    @Column(name = "packageName")
    private String packageName;
    @Size(max = 10)
    @Column(name = "appVersionName")
    private String appVersionName;
    @Size(max = 10)
    @Column(name = "appVersionCode")
    private String appVersionCode;
    @Size(max = 100)
    @Column(name = "brand")
    private String brand;
    @Size(max = 100)
    @Column(name = "phoneModel")
    private String phoneModel;
    @Size(max = 20)
    @Column(name = "androidVersion")
    private String androidVersion;
    @Lob
    @Size(max = 65535)
    @Column(name = "stackTrace")
    private String stackTrace;
    @Lob
    @Size(max = 65535)
    @Column(name = "logCat")
    private String logCat;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne
    private Company company;
    @JoinColumn(name = "staffID", referencedColumnName = "staffID")
    @ManyToOne
    private Staff staff;

    public ErrorStoreAndroid() {
    }

    public ErrorStoreAndroid(Integer errorStoreAndroidID) {
        this.errorStoreAndroidID = errorStoreAndroidID;
    }

    public Integer getErrorStoreAndroidID() {
        return errorStoreAndroidID;
    }

    public void setErrorStoreAndroidID(Integer errorStoreAndroidID) {
        this.errorStoreAndroidID = errorStoreAndroidID;
    }

    public Date getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(Date errorDate) {
        this.errorDate = errorDate;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getLogCat() {
        return logCat;
    }

    public void setLogCat(String logCat) {
        this.logCat = logCat;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (errorStoreAndroidID != null ? errorStoreAndroidID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErrorStoreAndroid)) {
            return false;
        }
        ErrorStoreAndroid other = (ErrorStoreAndroid) object;
        if ((this.errorStoreAndroidID == null && other.errorStoreAndroidID != null) || (this.errorStoreAndroidID != null && !this.errorStoreAndroidID.equals(other.errorStoreAndroidID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ErrorStoreAndroid[ errorStoreAndroidID=" + errorStoreAndroidID + " ]";
    }

    
    
}
