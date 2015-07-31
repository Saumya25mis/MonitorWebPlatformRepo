/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.dto;

import com.boha.monitor.data.*;
import java.io.Serializable;

/**
 *
 * @author aubreyM
 */
public class VideoUploadDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer videoUploadID;
    private Long dateTaken;
    private Double latitude;
    private Double longitude;
    private Float accuracy;
    private String url;
    private Long dateUploaded;
    private String secureUrl;
    private String eTag;
    private String signature;
    private Integer width;
    private Integer height;
    private Integer bytes;
    private String audioCodec;
    private String videoCodec;
    private Double frameRate;
    private Integer bitRate;
    private Double duration;
    private Integer staffID, monitorID,  projectID,  projectTaskID;

    public VideoUploadDTO() {
    }

    public VideoUploadDTO(Integer videoUploadID) {
        this.videoUploadID = videoUploadID;
    }

    public VideoUploadDTO(VideoUpload a) {
        this.videoUploadID = a.getVideoUploadID();
        this.dateTaken = a.getDateTaken().getTime();
        latitude = a.getLatitude();
        longitude = a.getLongitude();
        accuracy = a.getAccuracy();
        url = a.getUrl();
        dateUploaded = a.getDateUploaded().getTime();
        secureUrl = a.getSecureUrl();
        eTag = a.getETag();
        signature = a.getSignature();
        width = a.getWidth();
        height = a.getHeight();
        bytes = a.getBytes();
        bitRate = a.getBitRate();
        audioCodec = a.getAudioCodec();
        videoCodec = a.getVideoCodec();
        frameRate = a.getFrameRate();
        duration = a.getDuration();
        if (a.getMonitor() != null) {
            monitorID = a.getMonitor().getMonitorID();
        }
        if (a.getProject() != null) {
            projectID = a.getProject().getProjectID();
        }
        if (a.getStaff() != null) {
            staffID = a.getStaff().getStaffID();
        }
        if (a.getProjectTask() != null) {
            projectTaskID = a.getProjectTask().getProjectTaskID();
        }
    }

    public Integer getVideoUploadID() {
        return videoUploadID;
    }

    public void setVideoUploadID(Integer videoUploadID) {
        this.videoUploadID = videoUploadID;
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

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    public Integer getStaffID() {
        return staffID;
    }

    public void setStaffID(Integer staffID) {
        this.staffID = staffID;
    }

    public Integer getMonitorID() {
        return monitorID;
    }

    public void setMonitorID(Integer monitorID) {
        this.monitorID = monitorID;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getSecureUrl() {
        return secureUrl;
    }

    public void setSecureUrl(String secureUrl) {
        this.secureUrl = secureUrl;
    }

    public String getETag() {
        return eTag;
    }

    public void setETag(String eTag) {
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

    public String getAudioCodec() {
        return audioCodec;
    }

    public void setAudioCodec(String audioCodec) {
        this.audioCodec = audioCodec;
    }

    public String getVideoCodec() {
        return videoCodec;
    }

    public void setVideoCodec(String videoCodec) {
        this.videoCodec = videoCodec;
    }

    public Double getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(Double frameRate) {
        this.frameRate = frameRate;
    }

    public Integer getBitRate() {
        return bitRate;
    }

    public void setBitRate(Integer bitRate) {
        this.bitRate = bitRate;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (videoUploadID != null ? videoUploadID.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.VideoUpload[ videoUploadID=" + videoUploadID + " ]";
    }
    
}
