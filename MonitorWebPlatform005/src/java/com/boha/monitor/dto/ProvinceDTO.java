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
public class ProvinceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer provinceID;
    private String provinceName;
    private List<MunicipalityDTO> municipalityList;
    private Integer countryID;

    public ProvinceDTO() {
    }

    public ProvinceDTO(Province a) {
        this.provinceID = a.getProvinceID();
        provinceName = a.getProvinceName();
        if (a.getCountry() != null) {
            countryID = a.getCountry().getCountryID();
        }
    }

    public Integer getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(Integer provinceID) {
        this.provinceID = provinceID;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<MunicipalityDTO> getMunicipalityList() {
        return municipalityList;
    }

    public void setMunicipalityList(List<MunicipalityDTO> municipalityList) {
        this.municipalityList = municipalityList;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (provinceID != null ? provinceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProvinceDTO)) {
            return false;
        }
        ProvinceDTO other = (ProvinceDTO) object;
        if ((this.provinceID == null && other.provinceID != null) || (this.provinceID != null && !this.provinceID.equals(other.provinceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Province[ provinceID=" + provinceID + " ]";
    }
    
}
