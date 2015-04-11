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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "chatMessage")
@NamedQueries({
    @NamedQuery(name = "ChatMessage.findByProject", 
            query = "SELECT c FROM ChatMessage c where c.chat.project.projectID = :projectID order by c.dateSent desc"),
    @NamedQuery(name = "ChatMessage.findByProjectAndStaff", 
            query = "SELECT c FROM ChatMessage c where c.chat.project.projectID = :projectID and c.companyStaff.companyStaffID = :companyStaffID order by c.dateSent desc")
})
public class ChatMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "chatMessageID")
    private Integer chatMessageID;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateSent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSent;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Size(max = 255)
    @Column(name = "pictureFileName")
    private String pictureFileName;
    @JoinColumn(name = "chatID", referencedColumnName = "chatID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Chat chat;
    @JoinColumn(name = "companyStaffID", referencedColumnName = "companyStaffID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CompanyStaff companyStaff;

    public ChatMessage() {
    }

    public ChatMessage(Integer chatMessageID) {
        this.chatMessageID = chatMessageID;
    }

    public ChatMessage(Integer chatMessageID, String message, Date dateSent) {
        this.chatMessageID = chatMessageID;
        this.message = message;
        this.dateSent = dateSent;
    }

    public Integer getChatMessageID() {
        return chatMessageID;
    }

    public void setChatMessageID(Integer chatMessageID) {
        this.chatMessageID = chatMessageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
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

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public CompanyStaff getCompanyStaff() {
        return companyStaff;
    }

    public void setCompanyStaff(CompanyStaff companyStaff) {
        this.companyStaff = companyStaff;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chatMessageID != null ? chatMessageID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChatMessage)) {
            return false;
        }
        ChatMessage other = (ChatMessage) object;
        if ((this.chatMessageID == null && other.chatMessageID != null) || (this.chatMessageID != null && !this.chatMessageID.equals(other.chatMessageID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.ChatMessage[ chatMessageID=" + chatMessageID + " ]";
    }
    
}
