/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.dto;

import com.boha.monitor.data.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author aubreyM
 */public class LocationTrackerDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer locationTrackerID;
    private int companyStaffID;
    private Date dateTracked;
    private double latitude;
    private double longitude;
    private float accuracy;
    private String geocodedAddress, staffName;
    private Date dateAdded;
    

    public LocationTrackerDTO() {
    }

   
    public LocationTrackerDTO(LocationTracker a) {
        this.locationTrackerID = a.getLocationTrackerID();
        CompanyStaff cs = a.getCompanyStaff();
        this.companyStaffID = cs.getCompanyStaffID();
        this.staffName = cs.getFirstName() + " " + cs.getLastName();
        this.dateTracked = a.getDateTracked();
        this.latitude = a.getLatitude();
        this.longitude = a.getLongitude();
        this.accuracy = a.getAccuracy();
        this.geocodedAddress = a.getGeocodedAddress();
        this.dateAdded = a.getDateAdded();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getGeocodedAddress() {
        return geocodedAddress;
    }

    public void setGeocodedAddress(String geocodedAddress) {
        this.geocodedAddress = geocodedAddress;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Integer getLocationTrackerID() {
        return locationTrackerID;
    }

    public void setLocationTrackerID(Integer locationTrackerID) {
        this.locationTrackerID = locationTrackerID;
    }

    public int getCompanyStaffID() {
        return companyStaffID;
    }

    public void setCompanyStaffID(int companyStaffID) {
        this.companyStaffID = companyStaffID;
    }

    public Date getDateTracked() {
        return dateTracked;
    }

    public void setDateTracked(Date dateTracked) {
        this.dateTracked = dateTracked;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationTrackerID != null ? locationTrackerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocationTrackerDTO)) {
            return false;
        }
        LocationTrackerDTO other = (LocationTrackerDTO) object;
        if ((this.locationTrackerID == null && other.locationTrackerID != null) || (this.locationTrackerID != null && !this.locationTrackerID.equals(other.locationTrackerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.LocationTracker[ locationTrackerID=" + locationTrackerID + " ]";
    }

    
}
