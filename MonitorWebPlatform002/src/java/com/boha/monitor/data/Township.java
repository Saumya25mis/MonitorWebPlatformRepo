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
@Table(name = "township")
@NamedQueries({
    @NamedQuery(name = "Township.findAll", query = "SELECT t FROM Township t"),
    @NamedQuery(name = "Township.findByTownshipID", query = "SELECT t FROM Township t WHERE t.townshipID = :townshipID"),
    @NamedQuery(name = "Township.findByTownshipName", query = "SELECT t FROM Township t WHERE t.townshipName = :townshipName"),
    @NamedQuery(name = "Township.findByLatitude", query = "SELECT t FROM Township t WHERE t.latitude = :latitude"),
    @NamedQuery(name = "Township.findByLongitude", query = "SELECT t FROM Township t WHERE t.longitude = :longitude")})
public class Township implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "townshipID")
    private Integer townshipID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "townshipName")
    private String townshipName;
    @JoinColumn(name = "cityID", referencedColumnName = "cityID")
    @ManyToOne(optional = false)
    private City cityID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "townshipID")
    private List<Beneficiary> beneficiaryList;

    public Township() {
    }

    public Township(Integer townshipID) {
        this.townshipID = townshipID;
    }

    public Township(Integer townshipID, String townshipName, double latitude, double longitude) {
        this.townshipID = townshipID;
        this.townshipName = townshipName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getTownshipID() {
        return townshipID;
    }

    public void setTownshipID(Integer townshipID) {
        this.townshipID = townshipID;
    }

    public String getTownshipName() {
        return townshipName;
    }

    public void setTownshipName(String townshipName) {
        this.townshipName = townshipName;
    }


    public City getCityID() {
        return cityID;
    }

    public void setCityID(City cityID) {
        this.cityID = cityID;
    }

    public List<Beneficiary> getBeneficiaryList() {
        return beneficiaryList;
    }

    public void setBeneficiaryList(List<Beneficiary> beneficiaryList) {
        this.beneficiaryList = beneficiaryList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (townshipID != null ? townshipID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Township)) {
            return false;
        }
        Township other = (Township) object;
        if ((this.townshipID == null && other.townshipID != null) || (this.townshipID != null && !this.townshipID.equals(other.townshipID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Township[ townshipID=" + townshipID + " ]";
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
    
}
