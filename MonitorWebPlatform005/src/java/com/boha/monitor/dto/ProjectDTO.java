/*
 * To change this license header, choose License Headers in ProjectDTO Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.Portfolio;
import com.boha.monitor.data.Project;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class ProjectDTO implements Serializable, Comparable<ProjectDTO> {

    private List<PhotoUploadDTO> photoUploadList;
    private List<VideoUploadDTO> videoUploadList;
    private static final long serialVersionUID = 1L;
    private Integer projectID, cityID, companyID;
    private Integer programmeID, portfolioID,
            statusCount, photoCount, projectTaskCount, monitorCount, staffCount, videoCount;
    private String projectName;
    private Double latitude;
    private Double longitude;
    private Float accuracy;
    private Boolean activeFlag;
    private Boolean locationConfirmed;
    private String address, cityName, municipalityName;
    private String desc, programmeName, portfolioName;
    private List<ProjectTaskDTO> projectTaskList = new ArrayList<>();
    private List<ProjectStatusDTO> projectStatusList = new ArrayList<>();
    private List<GcmDeviceDTO> gcmDeviceList;
    private List<ChatDTO> chatList;
    private List<MonitorDTO> monitorList;
    private List<StaffDTO> staffList;
    private ProjectTaskStatusDTO lastStatus;

    public ProjectDTO() {
    }
    public ProjectDTO(Project a) {
        setUp(a);
    }

    private void setUp(Project a) {
        this.projectID = a.getProjectID();
        this.projectName = a.getProjectName();
        latitude = a.getLatitude();
        longitude = a.getLongitude();
        accuracy = a.getAccuracy();
        activeFlag = a.getActiveFlag();
        locationConfirmed = a.getLocationConfirmed();
        address = a.getAddress();
        desc = a.getDescription();

        if (a.getCity() != null) {
            cityID = a.getCity().getCityID();
            cityName = a.getCity().getCityName();
            if (a.getCity().getMunicipality() != null) {
                municipalityName = a.getCity().getMunicipality().getMunicipalityName();
            }
        }

        if (a.getCompany() != null) {
            companyID = a.getCompany().getCompanyID();
        }

        if (a.getProgramme() != null) {
            programmeName = a.getProgramme().getProgrammeName();
            programmeID = a.getProgramme().getProgrammeID();
            if (a.getProgramme().getPortfolio() != null) {
                Portfolio p = a.getProgramme().getPortfolio();
                portfolioID = p.getPortfolioID();
                portfolioName = p.getPortfolioName();
            }
        }
    }

    public List<VideoUploadDTO> getVideoUploadList() {
        if (videoUploadList == null) {
            videoUploadList = new ArrayList<>();
        }
        return videoUploadList;
    }

    public void setVideoUploadList(List<VideoUploadDTO> videoUploadList) {
        this.videoUploadList = videoUploadList;
    }

    public Integer getVideoCount() {
        if (videoCount == null) {
            videoCount = 0;
        }
        return videoCount;
    }

    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }

    
    public Integer getMonitorCount() {
        return monitorCount;
    }

    public void setMonitorCount(Integer monitorCount) {
        this.monitorCount = monitorCount;
    }

    public Integer getStaffCount() {
        return staffCount;
    }

    public void setStaffCount(Integer staffCount) {
        this.staffCount = staffCount;
    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public Integer getProjectTaskCount() {
        return projectTaskCount;
    }

    public void setProjectTaskCount(Integer projectTaskCount) {
        this.projectTaskCount = projectTaskCount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getMunicipalityName() {
        return municipalityName;
    }

    public void setMunicipalityName(String municipalityName) {
        this.municipalityName = municipalityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public Integer getPortfolioID() {
        return portfolioID;
    }

    public void setPortfolioID(Integer portfolioID) {
        this.portfolioID = portfolioID;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public ProjectTaskStatusDTO getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(ProjectTaskStatusDTO lastStatus) {
        this.lastStatus = lastStatus;
    }

    public Integer getStatusCount() {
        if (statusCount == null) {
            statusCount = 0;
        }
        return statusCount;
    }

    public void setStatusCount(Integer statusCount) {
        this.statusCount = statusCount;
    }

    public List<PhotoUploadDTO> getPhotoUploadList() {
        if (photoUploadList == null) {
            photoUploadList = new ArrayList<>();
        }
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUploadDTO> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(Integer programmeID) {
        this.programmeID = programmeID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Boolean getLocationConfirmed() {
        return locationConfirmed;
    }

    public void setLocationConfirmed(Boolean locationConfirmed) {
        this.locationConfirmed = locationConfirmed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return desc;
    }

    public void setDescription(String description) {
        this.desc = description;
    }

    public List<ProjectTaskDTO> getProjectTaskList() {
        if (projectTaskList == null) {
            projectTaskList = new ArrayList<>();
        }
        return projectTaskList;
    }

    public void setProjectTaskList(List<ProjectTaskDTO> projectTaskList) {
        this.projectTaskList = projectTaskList;
    }

    public List<ProjectStatusDTO> getProjectStatusList() {
        return projectStatusList;
    }

    public void setProjectStatusList(List<ProjectStatusDTO> projectStatusList) {
        this.projectStatusList = projectStatusList;
    }

    public List<GcmDeviceDTO> getGcmDeviceList() {
        return gcmDeviceList;
    }

    public void setGcmDeviceList(List<GcmDeviceDTO> gcmDeviceList) {
        this.gcmDeviceList = gcmDeviceList;
    }

    public List<ChatDTO> getChatList() {
        return chatList;
    }

    public void setChatList(List<ChatDTO> chatList) {
        this.chatList = chatList;
    }

    public List<MonitorDTO> getMonitorList() {
        return monitorList;
    }

    public void setMonitorList(List<MonitorDTO> monitorList) {
        this.monitorList = monitorList;
    }

    public List<StaffDTO> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<StaffDTO> staffList) {
        this.staffList = staffList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectID != null ? projectID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectDTO)) {
            return false;
        }
        ProjectDTO other = (ProjectDTO) object;
        if ((this.projectID == null && other.projectID != null) || (this.projectID != null && !this.projectID.equals(other.projectID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Project[ projectID=" + projectID + " ]";
    }

    @Override
    public int compareTo(ProjectDTO o) {

        return this.projectName.compareTo(o.projectName);
    }

}
