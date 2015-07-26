/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "municipality")
@NamedQueries({
    @NamedQuery(name = "Municipality.findAll", query = "SELECT m FROM Municipality m"),
    @NamedQuery(name = "Municipality.findByMunicipalityID", query = "SELECT m FROM Municipality m WHERE m.municipalityID = :municipalityID"),
    @NamedQuery(name = "Municipality.findByMunicipalityName", query = "SELECT m FROM Municipality m WHERE m.municipalityName = :municipalityName"),
    @NamedQuery(name = "Municipality.findByLatitude", query = "SELECT m FROM Municipality m WHERE m.latitude = :latitude"),
    @NamedQuery(name = "Municipality.findByLongitude", query = "SELECT m FROM Municipality m WHERE m.longitude = :longitude")})
public class Municipality implements Serializable {
    @OneToMany(mappedBy = "municipality", fetch = FetchType.LAZY)
    private List<City> cityList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "municipalityID")
    private Integer municipalityID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 512)
    @Column(name = "municipalityName")
    private String municipalityName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @JoinColumn(name = "provinceID", referencedColumnName = "provinceID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Province province;

    public Municipality() {
    }

    public Municipality(Integer municipalityID) {
        this.municipalityID = municipalityID;
    }

    public Municipality(Integer municipalityID, String municipalityName) {
        this.municipalityID = municipalityID;
        this.municipalityName = municipalityName;
    }

    public Integer getMunicipalityID() {
        return municipalityID;
    }

    public void setMunicipalityID(Integer municipalityID) {
        this.municipalityID = municipalityID;
    }

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (municipalityID != null ? municipalityID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipality)) {
            return false;
        }
        Municipality other = (Municipality) object;
        if ((this.municipalityID == null && other.municipalityID != null) || (this.municipalityID != null && !this.municipalityID.equals(other.municipalityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Municipality[ municipalityID=" + municipalityID + " ]";
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
    
}
