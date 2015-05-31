/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "chat")
@NamedQueries({
    @NamedQuery(name = "Chat.findAll", query = "SELECT c FROM Chat c"),
    @NamedQuery(name = "Chat.findByChatID", query = "SELECT c FROM Chat c WHERE c.chatID = :chatID"),
    @NamedQuery(name = "Chat.findByDateStarted", query = "SELECT c FROM Chat c WHERE c.dateStarted = :dateStarted"),
    @NamedQuery(name = "Chat.findByMessage", query = "SELECT c FROM Chat c WHERE c.message = :message"),
    @NamedQuery(name = "Chat.findByChatName", query = "SELECT c FROM Chat c WHERE c.chatName = :chatName"),
    @NamedQuery(name = "Chat.findByAvatarNumber", query = "SELECT c FROM Chat c WHERE c.avatarNumber = :avatarNumber")})
public class Chat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "chatID")
    private Integer chatID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateStarted")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStarted;
    @Size(max = 255)
    @Column(name = "message")
    private String message;
    @Size(max = 100)
    @Column(name = "chatName")
    private String chatName;
    @Column(name = "avatarNumber")
    private Integer avatarNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chat")
    private List<ChatMessage> chatMessageList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chat")
    private List<ChatMember> chatMemberList;
    @JoinColumn(name = "staffID", referencedColumnName = "staffID")
    @ManyToOne
    private Staff staff;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne
    private Project project;
    @JoinColumn(name = "monitorID", referencedColumnName = "monitorID")
    @ManyToOne
    private Monitor monitor;

    public Chat() {
    }

    public Chat(Integer chatID) {
        this.chatID = chatID;
    }

    public Chat(Integer chatID, Date dateStarted) {
        this.chatID = chatID;
        this.dateStarted = dateStarted;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public Integer getAvatarNumber() {
        return avatarNumber;
    }

    public void setAvatarNumber(Integer avatarNumber) {
        this.avatarNumber = avatarNumber;
    }

    public List<ChatMessage> getChatMessageList() {
        return chatMessageList;
    }

    public void setChatMessageList(List<ChatMessage> chatMessageList) {
        this.chatMessageList = chatMessageList;
    }

    public List<ChatMember> getChatMemberList() {
        return chatMemberList;
    }

    public void setChatMemberList(List<ChatMember> chatMemberList) {
        this.chatMemberList = chatMemberList;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
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
        hash += (chatID != null ? chatID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chat)) {
            return false;
        }
        Chat other = (Chat) object;
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
