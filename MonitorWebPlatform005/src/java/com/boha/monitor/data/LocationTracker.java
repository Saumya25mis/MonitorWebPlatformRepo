/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "locationTracker")
@NamedQueries({
    @NamedQuery(name = "LocationTracker.findAll", query = "SELECT l FROM LocationTracker l"),
    @NamedQuery(name = "LocationTracker.findByLocationTrackerID", query = "SELECT l FROM LocationTracker l WHERE l.locationTrackerID = :locationTrackerID"),
    @NamedQuery(name = "LocationTracker.findByDateTracked", query = "SELECT l FROM LocationTracker l WHERE l.dateTracked = :dateTracked"),
    @NamedQuery(name = "LocationTracker.findByLatitude", query = "SELECT l FROM LocationTracker l WHERE l.latitude = :latitude"),
    @NamedQuery(name = "LocationTracker.findByLongitude", query = "SELECT l FROM LocationTracker l WHERE l.longitude = :longitude"),
    @NamedQuery(name = "LocationTracker.findByAccuracy", query = "SELECT l FROM LocationTracker l WHERE l.accuracy = :accuracy"),
    @NamedQuery(name = "LocationTracker.findByDateAdded", query = "SELECT l FROM LocationTracker l WHERE l.dateAdded = :dateAdded"),
    @NamedQuery(name = "LocationTracker.findByDateTrackedLong", query = "SELECT l FROM LocationTracker l WHERE l.dateTrackedLong = :dateTrackedLong")})
public class LocationTracker implements Serializable {
    @JoinColumn(name = "monitorID", referencedColumnName = "monitorID")
    @ManyToOne
    private Monitor monitor;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "locationTrackerID")
    private Integer locationTrackerID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateTracked")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTracked;
    @Basic(optional = false)
    @NotNull
    @Column(name = "latitude")
    private double latitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "longitude")
    private double longitude;
    @Basic(optional = false)
    @NotNull
    @Column(name = "accuracy")
    private float accuracy;
    @Lob
    @Size(max = 65535)
    @Column(name = "geocodedAddress")
    private String geocodedAddress;
    @Column(name = "dateAdded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    @Column(name = "dateTrackedLong")
    private BigInteger dateTrackedLong;
    @JoinColumn(name = "staffID", referencedColumnName = "staffID")
    @ManyToOne(optional = false)
    private Staff staff;

    public LocationTracker() {
    }

    public LocationTracker(Integer locationTrackerID) {
        this.locationTrackerID = locationTrackerID;
    }

    public LocationTracker(Integer locationTrackerID, Date dateTracked, double latitude, double longitude, float accuracy) {
        this.locationTrackerID = locationTrackerID;
        this.dateTracked = dateTracked;
        this.latitude = latitude;
        this.longitude = longitude;
        this.accuracy = accuracy;
    }

    public Integer getLocationTrackerID() {
        return locationTrackerID;
    }

    public void setLocationTrackerID(Integer locationTrackerID) {
        this.locationTrackerID = locationTrackerID;
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

    public BigInteger getDateTrackedLong() {
        return dateTrackedLong;
    }

    public void setDateTrackedLong(BigInteger dateTrackedLong) {
        this.dateTrackedLong = dateTrackedLong;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
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
        hash += (locationTrackerID != null ? locationTrackerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocationTracker)) {
            return false;
        }
        LocationTracker other = (LocationTracker) object;
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
