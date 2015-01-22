/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.Project;
import com.boha.monitor.data.ProjectSite;
import com.boha.monitor.dto.transfer.PhotoUploadDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class ProjectSiteDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer projectSiteID, photoCount;
    private String projectSiteName, standErfNumber, projectName;
    private Double latitude;
    private Double longitude;
    private Integer activeFlag, locationConfirmed;
    private Float accuracy;
    private BeneficiaryDTO beneficiary;
    private HappyLetterDTO happyLetter;
    private ProjectSiteTaskStatusDTO lastStatus;
    private List<ProjectSiteTaskDTO> projectSiteTaskList = new ArrayList<>();
    private Integer projectID, statusCount;
    private List<PhotoUploadDTO> photoUploadList = new ArrayList<>();
    private List<ProjectSiteTaskStatusDTO> projectSiteTaskStatusList;

    public ProjectSiteDTO() {
    }

    public ProjectSiteDTO(ProjectSite a) {
        this.projectSiteID = a.getProjectSiteID();
        this.projectSiteName = a.getProjectSiteName();
        this.accuracy = a.getAccuracy();
        this.latitude = a.getLatitude();
        this.longitude = a.getLongitude();
        this.activeFlag = a.getActiveFlag();
        Project p = a.getProject();
        this.projectID = p.getProjectID();
        this.projectName = p.getProjectName();
        this.standErfNumber = a.getStandErfNumber();
        this.locationConfirmed = a.getLocationConfirmed();
        if (a.getBeneficiary() != null) {
            this.beneficiary = new BeneficiaryDTO(a.getBeneficiary());
        }
        if (a.getHappyLetter() != null) {
            this.happyLetter = new HappyLetterDTO(a.getHappyLetter());
        }

    }

    public Integer getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(Integer photoCount) {
        this.photoCount = photoCount;
    }

    public List<ProjectSiteTaskStatusDTO> getProjectSiteTaskStatusList() {
        return projectSiteTaskStatusList;
    }

    public void setProjectSiteTaskStatusList(List<ProjectSiteTaskStatusDTO> projectSiteTaskStatusList) {
        this.projectSiteTaskStatusList = projectSiteTaskStatusList;
    }

    public ProjectSiteTaskStatusDTO getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(ProjectSiteTaskStatusDTO lastStatus) {
        this.lastStatus = lastStatus;
    }

    public Integer getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(Integer statusCount) {
        this.statusCount = statusCount;
    }

    
    public Integer getLocationConfirmed() {
        return locationConfirmed;
    }

    public void setLocationConfirmed(Integer locationConfirmed) {
        this.locationConfirmed = locationConfirmed;
    }

    public BeneficiaryDTO getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(BeneficiaryDTO beneficiary) {
        this.beneficiary = beneficiary;
    }

    public HappyLetterDTO getHappyLetter() {
        return happyLetter;
    }

    public void setHappyLetter(HappyLetterDTO happyLetter) {
        this.happyLetter = happyLetter;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public String getStandErfNumber() {
        return standErfNumber;
    }

    public void setStandErfNumber(String standErfNumber) {
        this.standErfNumber = standErfNumber;
    }

    public List<PhotoUploadDTO> getPhotoUploadList() {
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUploadDTO> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public Integer getProjectSiteID() {
        return projectSiteID;
    }

    public void setProjectSiteID(Integer projectSiteID) {
        this.projectSiteID = projectSiteID;
    }

    public String getProjectSiteName() {
        return projectSiteName;
    }

    public void setProjectSiteName(String projectSiteName) {
        this.projectSiteName = projectSiteName;
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

    public Integer getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Integer activeFlag) {
        this.activeFlag = activeFlag;
    }

    public List<ProjectSiteTaskDTO> getProjectSiteTaskList() {
        return projectSiteTaskList;
    }

    public void setProjectSiteTaskList(List<ProjectSiteTaskDTO> projectSiteTaskList) {
        this.projectSiteTaskList = projectSiteTaskList;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

}
