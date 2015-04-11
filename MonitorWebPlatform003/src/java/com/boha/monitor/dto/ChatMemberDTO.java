/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author aubreyM
 */
public class ChatMemberDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer chatMemberID;
    private Date dateJoined;
    private CompanyStaffDTO companyStaff, chatOwner;
    private Integer chatID;

    public ChatMemberDTO() {
    }

    public ChatMemberDTO(ChatMember a) {
        this.chatMemberID = a.getChatMemberID();
        this.dateJoined = a.getDateJoined();
        if (a.getCompanyStaff() != null) {
            companyStaff = new CompanyStaffDTO(a.getCompanyStaff());
        }
        if (a.getChat().getCompanyStaff() != null) {
            chatOwner = new CompanyStaffDTO(a.getChat().getCompanyStaff());
        }
        chatID = a.getChat().getChatID();
    }

    public Integer getChatMemberID() {
        return chatMemberID;
    }

    public CompanyStaffDTO getChatOwner() {
        return chatOwner;
    }

    public void setChatOwner(CompanyStaffDTO chatOwner) {
        this.chatOwner = chatOwner;
    }

    public void setChatMemberID(Integer chatMemberID) {
        this.chatMemberID = chatMemberID;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public CompanyStaffDTO getCompanyStaff() {
        return companyStaff;
    }

    public void setCompanyStaff(CompanyStaffDTO companyStaff) {
        this.companyStaff = companyStaff;
    }

    public Integer getChatID() {
        return chatID;
    }

    public void setChatID(Integer chatID) {
        this.chatID = chatID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chatMemberID != null ? chatMemberID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChatMemberDTO)) {
            return false;
        }
        ChatMemberDTO other = (ChatMemberDTO) object;
        if ((this.chatMemberID == null && other.chatMemberID != null) || (this.chatMemberID != null && !this.chatMemberID.equals(other.chatMemberID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ChatMember[ chatMemberID=" + chatMemberID + " ]";
    }

}
