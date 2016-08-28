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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aubreymalabie
 */
@Entity
@Table(name = "photoTag")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhotoTag.findAll", query = "SELECT p FROM PhotoTag p"),
    
})
public class PhotoTag implements Serializable {

    @JoinColumn(name = "photoUploadID", referencedColumnName = "photoUploadID")
    @ManyToOne
    private PhotoUpload photoUpload;
    @JoinColumn(name = "monitorID", referencedColumnName = "monitorID")
    @ManyToOne
    private Monitor monitor;
    @JoinColumn(name = "staffID", referencedColumnName = "staffID")
    @ManyToOne
    private Staff staff;
    @JoinColumn(name = "tagTypeID", referencedColumnName = "tagTypeID")
    @ManyToOne
    private TagType tagType;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "photoTagID")
    private Integer photoTagID;
    @Column(name = "dateTagged")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTagged;
    
    @Column(name = "color")
    private Integer color;

    public PhotoTag() {
    }

    public PhotoTag(Integer photoTagID) {
        this.photoTagID = photoTagID;
    }

    public Integer getPhotoTagID() {
        return photoTagID;
    }

    public void setPhotoTagID(Integer photoTagID) {
        this.photoTagID = photoTagID;
    }

    public Date getDateTagged() {
        return dateTagged;
    }

    public void setDateTagged(Date dateTagged) {
        this.dateTagged = dateTagged;
    }


    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (photoTagID != null ? photoTagID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhotoTag)) {
            return false;
        }
        PhotoTag other = (PhotoTag) object;
        if ((this.photoTagID == null && other.photoTagID != null) || (this.photoTagID != null && !this.photoTagID.equals(other.photoTagID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.PhotoTag[ photoTagID=" + photoTagID + " ]";
    }

    public PhotoUpload getPhotoUpload() {
        return photoUpload;
    }

    public void setPhotoUpload(PhotoUpload photoUpload) {
        this.photoUpload = photoUpload;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public TagType getTagType() {
        return tagType;
    }

    public void setTagType(TagType tagType) {
        this.tagType = tagType;
    }

   
    
}
