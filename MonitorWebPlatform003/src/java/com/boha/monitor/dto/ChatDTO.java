/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class ChatDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer chatID, projectID, projectSiteID, avatarNumber;
    private Date dateStarted;
    private String message;
    private String chatName;
    private List<ChatMemberDTO> chatMemberList;
    private List<ChatMessageDTO> chatMessageList;
    private CompanyStaffDTO companyStaff;

    public ChatDTO() {
    }

    public ChatDTO(Integer chatID) {
        this.chatID = chatID;
    }

    public ChatDTO(Chat a) {
        this.chatID = a.getChatID();
        this.dateStarted = a.getDateStarted();
        if (a.getCompanyStaff() != null) {
            this.companyStaff = new CompanyStaffDTO(a.getCompanyStaff());
        }
        this.message = a.getMessage();
        this.chatName = a.getChatName();
        avatarNumber = a.getAvatarNumber();
        if (a.getProject() != null) {
            projectID = a.getProject().getProjectID();
        }
        if (a.getProjectSite() != null) {
            projectSiteID = a.getProjectSite().getProjectSiteID();
        }
    }

    public Integer getAvatarNumber() {
        return avatarNumber;
    }

    public void setAvatarNumber(Integer avatarNumber) {
        this.avatarNumber = avatarNumber;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
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

    public List<ChatMessageDTO> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessageDTO> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getChatID() {
        return chatID;
    }

    public void setChatID(Integer chatID) {
        this.chatID = chatID;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public List<ChatMemberDTO> getChatMemberList() {
        return chatMemberList;
    }

    public void setChatMemberList(List<ChatMemberDTO> chatMemberList) {
        this.chatMemberList = chatMemberList;
    }

    public CompanyStaffDTO getCompanyStaff() {
        return companyStaff;
    }

    public void setCompanyStaff(CompanyStaffDTO companyStaff) {
        this.companyStaff = companyStaff;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chatID != null ? chatID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChatDTO)) {
            return false;
        }
        ChatDTO other = (ChatDTO) object;
        if ((this.chatID == null && other.chatID != null) || (this.chatID != null && !this.chatID.equals(other.chatID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Chat[ chatID=" + chatID + " ]";
    }

}
