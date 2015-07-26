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
public class CityDTO implements Serializable {
    private Integer municipalityID;
    private List<ProjectDTO> projectList;
    private static final long serialVersionUID = 1L;
    private Integer cityID;
    private String cityName;
    private Double latitude;
    private Double longitude;

    public CityDTO() {
    }

    public CityDTO(City a) {
        this.cityID = a.getCityID();
        cityName = a.getCityName();
        latitude = a.getLatitude();
        longitude = a.getLongitude();
        if (a.getMunicipality() != null) {
            municipalityID = a.getMunicipality().getMunicipalityID();
        }
    }

    public Integer getMunicipalityID() {
        return municipalityID;
    }

    public void setMunicipalityID(Integer municipalityID) {
        this.municipalityID = municipalityID;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }



    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cityID != null ? cityID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CityDTO)) {
            return false;
        }
        CityDTO other = (CityDTO) object;
        if ((this.cityID == null && other.cityID != null) || (this.cityID != null && !this.cityID.equals(other.cityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.City[ cityID=" + cityID + " ]";
    }

    public List<ProjectDTO> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectDTO> projectList) {
        this.projectList = projectList;
    }

    
}
