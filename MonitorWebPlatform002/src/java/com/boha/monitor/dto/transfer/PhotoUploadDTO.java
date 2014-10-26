/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto.transfer;

import com.boha.monitor.data.PhotoUpload;
import com.boha.monitor.data.Project;
import com.boha.monitor.data.ProjectSite;
import com.boha.monitor.data.ProjectSiteTask;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aubreyM
 */

public class PhotoUploadDTO {

    public static final int SITE_IMAGE = 1, TASK_IMAGE = 2, PROJECT_IMAGE = 3, STAFF_IMAGE = 4;
    private boolean isFullPicture;
    private Integer companyID, projectID, projectSiteID, 
            projectSiteTaskID, pictureType, companyStaffID, 
            photoUploadID, thumbFlag;
    private List<String> tags;
    String projectName, projectSiteName, projectSiteTask;
    private Double latitude, longitude;
    private String uri;
    private Date dateTaken, dateUploaded;

    public PhotoUploadDTO(PhotoUpload a) {
        photoUploadID = a.getPhotoUploadID();
        pictureType = a.getPictureType();
        latitude = a.getLatitude();
        longitude = a.getLongitude();
        uri = a.getUri();
        dateTaken = a.getDateTaken();
        companyID = a.getCompany().getCompanyID();
        dateUploaded = a.getDateUploaded();
        thumbFlag = a.getThumbFlag();
        switch (pictureType) {
            case PROJECT_IMAGE:
                Project x = a.getProject();
                projectID = x.getProjectID();
                projectName = x.getProjectName();
                break;
            case SITE_IMAGE:
                ProjectSite p = a.getProjectSite();
                projectID = p.getProject().getProjectID();
                projectName = p.getProject().getProjectName();
                projectSiteID = p.getProjectSiteID();
                projectSiteName = p.getProjectSiteName();
                break;
            case TASK_IMAGE:
                ProjectSiteTask t = a.getProjectSiteTask();
                projectName = t.getProjectSite().getProject().getProjectName();
                projectSiteTaskID = t.getProjectSiteTaskID();
                projectSiteID = t.getProjectSite().getProjectSiteID();
                projectID = t.getProjectSite().getProject().getProjectID();
                projectSiteName = t.getProjectSite().getProjectSiteName();
                projectSiteTask = t.getTask().getTaskName();
                break;
            case STAFF_IMAGE:
                companyStaffID = a.getCompanyStaff().getCompanyStaffID();
                break;
        }
    }

    public Date getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Date dateTaken) {
        this.dateTaken = dateTaken;
    }

    
    public Integer getPhotoUploadID() {
        return photoUploadID;
    }

    public void setPhotoUploadID(Integer photoUploadID) {
        this.photoUploadID = photoUploadID;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getThumbFlag() {
        return thumbFlag;
    }

    public void setThumbFlag(Integer thumbFlag) {
        this.thumbFlag = thumbFlag;
    }

    public Date getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(Date dateUploaded) {
        this.dateUploaded = dateUploaded;
    }
    
    

    public boolean isIsFullPicture() {
        return isFullPicture;
    }

    public void setIsFullPicture(boolean isFullPicture) {
        this.isFullPicture = isFullPicture;
    }

    
    public Integer getCompanyStaffID() {
        return companyStaffID;
    }

    public void setCompanyStaffID(Integer companyStaffID) {
        this.companyStaffID = companyStaffID;
    }

    
    public Integer getPictureType() {
        return pictureType;
    }

    public void setPictureType(Integer pictureType) {
        this.pictureType = pictureType;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getProjectSiteID() {
        return projectSiteID;
    }

    public void setProjectSiteID(Integer projectSiteID) {
        this.projectSiteID = projectSiteID;
    }

    public Integer getProjectSiteTaskID() {
        return projectSiteTaskID;
    }

    public void setProjectSiteTaskID(Integer projectSiteTaskID) {
        this.projectSiteTaskID = projectSiteTaskID;
    }

    

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
   

}
