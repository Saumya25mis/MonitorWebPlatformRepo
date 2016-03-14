/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class MonitorDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer monitorID, activeFlag, statusCount, photoCount, projectCount;
    private String firstName;
    private String lastName;
    private String email, companyName, IDNumber, address;
    private String cellphone, pin;
    private Long appInvitationDate;
    private Integer companyID;
    private Short gender;
    private ProjectTaskStatusDTO lastStatus;

    private GcmDeviceDTO gcmDevice;
    private List<GcmDeviceDTO> gcmDeviceList;
    private List<PhotoUploadDTO> photoUploadList;
    private List<LocationTrackerDTO> locationTrackerList;
    private List<MonitorProjectDTO> monitorProjectList;
    private Integer profilePhotoCount;

    public MonitorDTO() {
    }

    public MonitorDTO(Monitor a) {
        this.monitorID = a.getMonitorID();
        this.firstName = a.getFirstName();
        this.lastName = a.getLastName();
        this.email = a.getEmail();
        this.cellphone = a.getCellphone();

        Company c = a.getCompany();
        if (c != null) {
            this.companyID = c.getCompanyID();
            this.companyName = c.getCompanyName();
        }
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
        if (a.getPhotoUploadList() != null) {
            photoCount = a.getPhotoUploadList().size();
            photoUploadList = new ArrayList<>();
            for (PhotoUpload p : a.getPhotoUploadList()) {
                PhotoUploadDTO dto = new PhotoUploadDTO(p);
                if (dto.getPictureType() == PhotoUploadDTO.MONITOR_IMAGE) {
                    photoUploadList.add(new PhotoUploadDTO(p));
                }

            }
            profilePhotoCount = photoUploadList.size();
        }
        if (a.getGcmDeviceList() != null) {
            gcmDeviceList = new ArrayList<>();
            for (GcmDevice g : a.getGcmDeviceList()) {
                gcmDeviceList.add(new GcmDeviceDTO(g));
            }
        }
        if (a.getMonitorProjectList() != null) {
            projectCount = a.getMonitorProjectList().size();
        }
        if (a.getProjectTaskStatusList() != null) {
            statusCount = a.getProjectTaskStatusList().size();
        }

    }

    public List<GcmDeviceDTO> getGcmDeviceList() {
        return gcmDeviceList;
    }

    public void setGcmDeviceList(List<GcmDeviceDTO> gcmDeviceList) {
        this.gcmDeviceList = gcmDeviceList;
    }

    public Integer getProfilePhotoCount() {
        return profilePhotoCount;
    }

    public void setProfilePhotoCount(Integer profilePhotoCount) {
        this.profilePhotoCount = profilePhotoCount;
    }

    public ProjectTaskStatusDTO getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(ProjectTaskStatusDTO lastStatus) {
        this.lastStatus = lastStatus;
    }

    public Integer getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Integer projectCount) {
        this.projectCount = projectCount;
    }

    public Integer getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(Integer statusCount) {
        this.statusCount = statusCount;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
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

    public List<MonitorProjectDTO> getMonitorProjectList() {
        return monitorProjectList;
    }

    public void setMonitorProjectList(List<MonitorProjectDTO> monitorProjectList) {
        this.monitorProjectList = monitorProjectList;
    }

}
