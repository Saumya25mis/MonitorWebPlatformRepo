/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "chatMember")
@NamedQueries({
    @NamedQuery(name = "ChatMember.findAll", query = "SELECT c FROM ChatMember c"),
    @NamedQuery(name = "ChatMember.findByChatMemberID", query = "SELECT c FROM ChatMember c WHERE c.chatMemberID = :chatMemberID"),
    @NamedQuery(name = "ChatMember.findByDateJoined", query = "SELECT c FROM ChatMember c WHERE c.dateJoined = :dateJoined")})
public class ChatMember implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "chatMemberID")
    private Integer chatMemberID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateJoined")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateJoined;
    @JoinColumn(name = "chatID", referencedColumnName = "chatID")
    @ManyToOne(optional = false)
    private Chat chat;
    @JoinColumn(name = "staffID", referencedColumnName = "staffID")
    @ManyToOne
    private Staff staff;
    @JoinColumn(name = "monitorID", referencedColumnName = "monitorID")
    @ManyToOne
    private Monitor monitor;

    public ChatMember() {
    }

    public ChatMember(Integer chatMemberID) {
        this.chatMemberID = chatMemberID;
    }

    public ChatMember(Integer chatMemberID, Date dateJoined) {
        this.chatMemberID = chatMemberID;
        this.dateJoined = dateJoined;
    }

    public Integer getChatMemberID() {
        return chatMemberID;
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

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
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
        if (!(object instanceof ChatMember)) {
            return false;
        }
        ChatMember other = (ChatMember) object;
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
