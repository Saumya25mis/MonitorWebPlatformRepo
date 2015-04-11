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
    @NamedQuery(name = "LocationTracker.findByStaff", 
            query = "SELECT l FROM LocationTracker l "
                    + "where l.companyStaff.companyStaffID = :companyStaffID "
                    + "order by l.dateTracked desc"),
    @NamedQuery(name = "LocationTracker.findByCompany", 
            query = "SELECT l FROM LocationTracker l where l.companyStaff.company = :company "
                    + "order by l.dateTracked desc"),
    @NamedQuery(name = "LocationTracker.findAll", 
            query = "SELECT l FROM LocationTracker l  "
                    + "order by l.dateTracked desc"),
    @NamedQuery(name = "LocationTracker.findByStaffInPeriod", 
            query = "SELECT l FROM LocationTracker l "
                    + "WHERE l.companyStaff = :companyStaff "
                    + "and l.dateTracked between :dateFrom and :dateTo "
                    + "order by l.dateTracked desc"),
    @NamedQuery(name = "LocationTracker.findByCompanyInPeriod", 
            query = "SELECT l FROM LocationTracker l where l.dateTrackedLong BETWEEN :dateFrom AND :dateTo "
                    + "and l.companyStaff.company = :company "
                    + "order by l.companyStaff, l.dateTracked desc")
    })
public class LocationTracker implements Serializable {
    @Column(name = "dateTrackedLong")
    private BigInteger dateTrackedLong;
    @Lob
    @Size(max = 65535)
    @Column(name = "geocodedAddress")
    private String geocodedAddress;
    @Column(name = "dateAdded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
     
    @JoinColumn(name = "companyStaffID", referencedColumnName = "companyStaffID")
    @ManyToOne(optional = false)
    private CompanyStaff companyStaff;
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
    

    public LocationTracker() {
    }

    public LocationTracker(Integer locationTrackerID) {
        this.locationTrackerID = locationTrackerID;
    }

    public LocationTracker(Integer locationTrackerID,  Date dateTracked, double latitude, double longitude, float accuracy) {
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

    public CompanyStaff getCompanyStaff() {
        return companyStaff;
    }

    public void setCompanyStaff(CompanyStaff companyStaff) {
        this.companyStaff = companyStaff;
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
    
}
