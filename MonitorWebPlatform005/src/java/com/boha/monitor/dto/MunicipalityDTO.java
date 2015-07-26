/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class MunicipalityDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private List<CityDTO> cityList;
    private Integer municipalityID;
    private String municipalityName;
    private Double latitude;
    private Double longitude;
    private Integer provinceID;

    public MunicipalityDTO() {
    }

    public MunicipalityDTO(Integer municipalityID) {
        this.municipalityID = municipalityID;
    }

    public MunicipalityDTO(Integer municipalityID, String municipalityName) {
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

 

    public Integer getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(Integer provinceID) {
        this.provinceID = provinceID;
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
        if (!(object instanceof MunicipalityDTO)) {
            return false;
        }
        MunicipalityDTO other = (MunicipalityDTO) object;
        if ((this.municipalityID == null && other.municipalityID != null) || (this.municipalityID != null && !this.municipalityID.equals(other.municipalityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Municipality[ municipalityID=" + municipalityID + " ]";
    }

    public List<CityDTO> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityDTO> cityList) {
        this.cityList = cityList;
    }

  
    
}
