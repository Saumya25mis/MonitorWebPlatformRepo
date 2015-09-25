/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.dto.transfer;

import com.boha.monitor.dto.LocationTrackerDTO;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class SimpleMessageDTO {
    private String staffName, monitorName, message, url;
    private List<Integer> monitorList, staffList;
    private Integer staffID, monitorID;
    private Long dateSent, dateReceived;
    private Boolean locationRequest;
    private LocationTrackerDTO locationTracker;

    public LocationTrackerDTO getLocationTracker() {
        return locationTracker;
    }

    public void setLocationTracker(LocationTrackerDTO locationTracker) {
        this.locationTracker = locationTracker;
    }
    
    
    public Boolean isLocationRequest() {
        return locationRequest;
    }

    public void setLocationRequest(Boolean isLocationRequest) {
        this.locationRequest = isLocationRequest;
    }

    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
    public Long getDateSent() {
        return dateSent;
    }

    public void setDateSent(Long dateSent) {
        this.dateSent = dateSent;
    }

    public Long getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Long dateReceived) {
        this.dateReceived = dateReceived;
    }

    
    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
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
    
    
}
