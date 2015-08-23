/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.PhotoUpload;
import java.io.Serializable;

/**
 *
 * @author aubreyM
 */
public class PhotoUploadDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer photoUploadID;
    private int pictureType;
    private Long dateTaken;
    private Double latitude;
    private Double longitude;
    private Float accuracy;
    private String uri, name;
    private Integer thumbFlag;
    private Long dateUploaded;
    private String thumbFilePath;
    private Integer staffPictureID;
    private Integer projectID;
    private Integer projectTaskID;
    private Integer staffID, monitorID;
    
    private String secureUrl;
    private String eTag;
    private String signature;
    private Integer width;
    private Integer height;
    private Integer bytes;

    public PhotoUploadDTO() {
    }
    public static final int TASK_IMAGE = 2, PROJECT_IMAGE = 3, STAFF_IMAGE = 4, MONITOR_IMAGE = 5;
    private boolean isFullPicture, isStaffPicture;

    public PhotoUploadDTO(Integer photoUploadID) {
        this.photoUploadID = photoUploadID;
    }

    public PhotoUploadDTO(PhotoUpload a) {
        this.photoUploadID = a.getPhotoUploadID();
        this.pictureType = a.getPictureType();
        this.dateTaken = a.getDateTaken().getTime();
        latitude = a.getLatitude();
        longitude = a.getLongitude();
        accuracy = a.getAccuracy();
        uri = a.getUri();
        dateUploaded = a.getDateUploaded().getTime();
        
        secureUrl = a.getSecureUrl();
        eTag = a.getETag();
        signature = a.getSignature();
        width = a.getWidth();
        height = a.getHeight();
        bytes = a.getBytes();
        
        if (a.getProject() != null) {
            projectID = a.getProject().getProjectID();
            name = a.getProject().getProjectName();
        }
        if (a.getProjectTask() != null) {
            projectTaskID = a.getProjectTask().getProjectTaskID();
            name = a.getProjectTask().getProject().getProjectName() + " - " + a.getProjectTask().getTask().getTaskName();
        }
        if (a.getStaff() != null) {
            staffID = a.getStaff().getStaffID();
            name = a.getStaff().getFirstName() + " " + a.getStaff().getLastName();
        }
        if (a.getMonitor() != null) {
            monitorID = a.getMonitor().getMonitorID();
            name = a.getMonitor().getFirstName() + " " + a.getMonitor().getLastName();
        }
    }

    public String getSecureUrl() {
        return secureUrl;
    }

    public void setSecureUrl(String secureUrl) {
        this.secureUrl = secureUrl;
    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getBytes() {
        return bytes;
    }

    public void setBytes(Integer bytes) {
        this.bytes = bytes;
    }

    public boolean isIsFullPicture() {
        return isFullPicture;
    }

    public Integer getMonitorID() {
        return monitorID;
    }

    public void setMonitorID(Integer monitorID) {
        this.monitorID = monitorID;
    }

    public void setIsFullPicture(boolean isFullPicture) {
        this.isFullPicture = isFullPicture;
    }

    public boolean isIsStaffPicture() {
        return isStaffPicture;
    }

    public void setIsStaffPicture(boolean isStaffPicture) {
        this.isStaffPicture = isStaffPicture;
    }

    public Long getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Long dateTaken) {
        this.dateTaken = dateTaken;
    }

    public Long getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(Long dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public Integer getStaffPictureID() {
        return staffPictureID;
    }

    public void setStaffPictureID(Integer staffPictureID) {
        this.staffPictureID = staffPictureID;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getProjectTaskID() {
        return projectTaskID;
    }

    public void setProjectTaskID(Integer projectTaskID) {
        this.projectTaskID = projectTaskID;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public Integer getPhotoUploadID() {
        return photoUploadID;
    }

    public void setPhotoUploadID(Integer photoUploadID) {
        this.photoUploadID = photoUploadID;
    }

    public int getPictureType() {
        return pictureType;
    }

    public void setPictureType(int pictureType) {
        this.pictureType = pictureType;
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

    public String getThumbFilePath() {
        return thumbFilePath;
    }

    public void setThumbFilePath(String thumbFilePath) {
        this.thumbFilePath = thumbFilePath;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (photoUploadID != null ? photoUploadID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhotoUploadDTO)) {
            return false;
        }
        PhotoUploadDTO other = (PhotoUploadDTO) object;
        if ((this.photoUploadID == null && other.photoUploadID != null) || (this.photoUploadID != null && !this.photoUploadID.equals(other.photoUploadID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.PhotoUpload[ photoUploadID=" + photoUploadID + " ]";
    }


}
