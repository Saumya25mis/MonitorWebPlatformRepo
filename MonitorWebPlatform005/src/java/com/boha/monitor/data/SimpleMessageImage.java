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
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "simpleMessageImage")
@NamedQueries({
    @NamedQuery(name = "SimpleMessageImage.findAll", query = "SELECT s FROM SimpleMessageImage s"),
    @NamedQuery(name = "SimpleMessageImage.findBySimpleMessageImageID", query = "SELECT s FROM SimpleMessageImage s WHERE s.simpleMessageImageID = :simpleMessageImageID"),
    @NamedQuery(name = "SimpleMessageImage.findByUrl", query = "SELECT s FROM SimpleMessageImage s WHERE s.url = :url"),
    @NamedQuery(name = "SimpleMessageImage.findBySecureUrl", query = "SELECT s FROM SimpleMessageImage s WHERE s.secureUrl = :secureUrl"),
    @NamedQuery(name = "SimpleMessageImage.findByDateAdded", query = "SELECT s FROM SimpleMessageImage s WHERE s.dateAdded = :dateAdded")})
public class SimpleMessageImage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "simpleMessageImageID")
    private Integer simpleMessageImageID;
    @Size(max = 255)
    @Column(name = "url")
    private String url;
    @Size(max = 255)
    @Column(name = "secureUrl")
    private String secureUrl;
    @Column(name = "dateAdded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    @JoinColumn(name = "simpleMessageID", referencedColumnName = "simpleMessageID")
    @ManyToOne
    private SimpleMessage simpleMessage;

    public SimpleMessageImage() {
    }

    public SimpleMessageImage(Integer simpleMessageImageID) {
        this.simpleMessageImageID = simpleMessageImageID;
    }

    public Integer getSimpleMessageImageID() {
        return simpleMessageImageID;
    }

    public void setSimpleMessageImageID(Integer simpleMessageImageID) {
        this.simpleMessageImageID = simpleMessageImageID;
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

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public SimpleMessage getSimpleMessage() {
        return simpleMessage;
    }

    public void setSimpleMessage(SimpleMessage simpleMessage) {
        this.simpleMessage = simpleMessage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (simpleMessageImageID != null ? simpleMessageImageID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SimpleMessageImage)) {
            return false;
        }
        SimpleMessageImage other = (SimpleMessageImage) object;
        if ((this.simpleMessageImageID == null && other.simpleMessageImageID != null) || (this.simpleMessageImageID != null && !this.simpleMessageImageID.equals(other.simpleMessageImageID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.SimpleMessageImage[ simpleMessageImageID=" + simpleMessageImageID + " ]";
    }
    
}
