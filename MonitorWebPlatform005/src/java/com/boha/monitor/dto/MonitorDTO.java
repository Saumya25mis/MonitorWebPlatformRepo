/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.dto;

import com.boha.monitor.data.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class MonitorDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer monitorID, activeFlag;
    private String firstName;
    private String lastName;
    private String email, companyName, IDNumber, address;
    private String cellphone, pin;
    private Long appInvitationDate;
    private Integer companyID;
    private Short gender;
    private GcmDeviceDTO gcmDevice;
    private List<PhotoUploadDTO> photoUploadList;
    private List<LocationTrackerDTO> locationTrackerList;
    private List<StaffProjectDTO> staffProjectList;

    public MonitorDTO() {
    }

    public MonitorDTO(Monitor a) {
        this.monitorID = a.getMonitorID();
        this.firstName = a.getFirstName();
        this.lastName = a.getLastName();
        this.email = a.getEmail();
        this.cellphone = a.getCellphone();
        Company c = a.getCompany();
        this.companyID = c.getCompanyID();
        this.companyName = c.getCompanyName();
        this.activeFlag = a.getActiveFlag();
        this.address = a.getAddress();
        this.IDNumber = a.getIDNumber();
        this.gender = a.getGender();
        
        if (a.getAppInvitationDate() != null) {
            this.appInvitationDate = a.getAppInvitationDate().getTime();
        }
        if (a.getPin() != null) {
        this.pin = a.getPin();
        }
        
        
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    public Integer getMonitorID() {
        return monitorID;
    }

    public void setMonitorID(Integer monitorID) {
        this.monitorID = monitorID;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Long getAppInvitationDate() {
        return appInvitationDate;
    }

    public void setAppInvitationDate(Long appInvitationDate) {
        this.appInvitationDate = appInvitationDate;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public GcmDeviceDTO getGcmDevice() {
        return gcmDevice;
    }

    public void setGcmDevice(GcmDeviceDTO gcmDevice) {
        this.gcmDevice = gcmDevice;
    }

    public List<PhotoUploadDTO> getPhotoUploadList() {
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUploadDTO> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public List<LocationTrackerDTO> getLocationTrackerList() {
        return locationTrackerList;
    }

    public void setLocationTrackerList(List<LocationTrackerDTO> locationTrackerList) {
        this.locationTrackerList = locationTrackerList;
    }

    public List<StaffProjectDTO> getStaffProjectList() {
        return staffProjectList;
    }

    public void setStaffProjectList(List<StaffProjectDTO> staffProjectList) {
        this.staffProjectList = staffProjectList;
    }
    
}