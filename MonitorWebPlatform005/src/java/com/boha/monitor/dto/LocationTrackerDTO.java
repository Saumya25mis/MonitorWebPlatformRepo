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
public class LocationTrackerDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer locationTrackerID;
    private Integer staffID, monitorID, companyID;
    private Long dateTracked;
    private Double latitude;
    private Double longitude;
    private float accuracy;
    private String geocodedAddress, staffName, monitorName, message;
    private Long dateAdded;
    private List<Integer> monitorList, staffList;
    private GcmDeviceDTO gcmDevice;

    public LocationTrackerDTO() {
    }

    public LocationTrackerDTO(LocationTracker a) {
        this.locationTrackerID = a.getLocationTrackerID();
        this.latitude = a.getLatitude();
        this.longitude = a.getLongitude();
        this.accuracy = a.getAccuracy();
        this.geocodedAddress = a.getGeocodedAddress();
        
        Staff cs = a.getStaff();
        if (cs != null) {
            this.staffID = cs.getStaffID();
            this.staffName = cs.getFirstName() + " " + cs.getLastName();
        }
        Monitor m = a.getMonitor();
        if (m != null) {
            this.monitorID = m.getMonitorID();
            this.monitorName = m.getFirstName() + " " + m.getLastName();
        }
        if (a.getDateTracked() != null) {
            this.dateTracked = a.getDateTracked().getTime();
        }
        
        if (a.getDateAdded() != null) {
            this.dateAdded = a.getDateAdded().getTime();
        }
        if (a.getGcmDevice() != null) {
            gcmDevice = new GcmDeviceDTO(a.getGcmDevice());
        }
        if (a.getCompany() != null) {
            this.companyID = a.getCompany().getCompanyID();
        }
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getMonitorName() {
        return monitorName;
    }

    public void setMonitorName(String monitorName) {
        this.monitorName = monitorName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Integer> getMonitorList() {
        return monitorList;
    }

    public void setMonitorList(List<Integer> monitorList) {
        this.monitorList = monitorList;
    }

    public List<Integer> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Integer> staffList) {
        this.staffList = staffList;
    }

    public GcmDeviceDTO getGcmDevice() {
        return gcmDevice;
    }

    public void setGcmDevice(GcmDeviceDTO gcmDevice) {
        this.gcmDevice = gcmDevice;
    }

    public Integer getLocationTrackerID() {
        return locationTrackerID;
    }

    public void setLocationTrackerID(Integer locationTrackerID) {
        this.locationTrackerID = locationTrackerID;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public Integer getMonitorID() {
        return monitorID;
    }

    public void setMonitorID(Integer monitorID) {
        this.monitorID = monitorID;
    }

    public Long getDateTracked() {
        return dateTracked;
    }

    public void setDateTracked(Long dateTracked) {
        this.dateTracked = dateTracked;
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

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public String getGeocodedAddress() {
        return geocodedAddress;
    }

    public void setGeocodedAddress(String geocodedAddress) {
        this.geocodedAddress = geocodedAddress;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Long dateAdded) {
        this.dateAdded = dateAdded;
    }

}
