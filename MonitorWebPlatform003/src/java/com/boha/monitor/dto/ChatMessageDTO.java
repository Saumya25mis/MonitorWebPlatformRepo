/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.ChatMessage;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author aubreyM
 */
public class ChatMessageDTO implements Serializable {

    private Integer chatID, companyStaffID, chatMessageID, projectID, chatColor;
    private String staffName, message, address, chatName;
    private Date dateSent;
    private Double latitude, longitude;
    private String pictureFileName;

    public ChatMessageDTO(ChatMessage a) {
        chatMessageID = a.getChatMessageID();
        chatID = a.getChat().getChatID();
        chatName = a.getChat().getChatName();
        chatColor = a.getChat().getAvatarNumber();
        companyStaffID = a.getCompanyStaff().getCompanyStaffID();
        staffName = a.getCompanyStaff().getFirstName() + " " + a.getCompanyStaff().getLastName();
        message = a.getMessage();
        dateSent = a.getDateSent();
        longitude = a.getLongitude();
        latitude = a.getLatitude();
        pictureFileName = a.getPictureFileName();
        if (a.getChat().getProject() != null) {
            projectID = a.getChat().getProject().getProjectID();
        }

    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public Integer getChatColor() {
        return chatColor;
    }

    public void setChatColor(Integer chatColor) {
        this.chatColor = chatColor;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public ChatMessageDTO() {
    }

    public Integer getChatMessageID() {
        return chatMessageID;
    }

    public void setChatMessageID(Integer chatMessageID) {
        this.chatMessageID = chatMessageID;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public Integer getChatID() {
        return chatID;
    }

    public void setChatID(Integer chatID) {
        this.chatID = chatID;
    }

    public Integer getCompanyStaffID() {
        return companyStaffID;
    }

    public void setCompanyStaffID(Integer companyStaffID) {
        this.companyStaffID = companyStaffID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
