/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.data;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aubreymalabie
 */
@Entity
@Table(name = "tagType")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TagType.findAll", query = "SELECT t FROM TagType t"),
    @NamedQuery(name = "TagType.findByTagTypeName", query = "SELECT t FROM TagType t WHERE t.tagTypeName = :tagTypeName")})
public class TagType implements Serializable {

    @OneToMany(mappedBy = "tagType")
    private List<PhotoTag> photoTagList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tagTypeID")
    private Integer tagTypeID;
    @Size(max = 256)
    @Column(name = "tagTypeName")
    private String tagTypeName;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne
    private Company company;

    public TagType() {
    }

    public TagType(Integer tagTypeID) {
        this.tagTypeID = tagTypeID;
    }

    public Integer getTagTypeID() {
        return tagTypeID;
    }

    public void setTagTypeID(Integer tagTypeID) {
        this.tagTypeID = tagTypeID;
    }

    public String getTagTypeName() {
        return tagTypeName;
    }

    public void setTagTypeName(String tagTypeName) {
        this.tagTypeName = tagTypeName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tagTypeID != null ? tagTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TagType)) {
            return false;
        }
        TagType other = (TagType) object;
        if ((this.tagTypeID == null && other.tagTypeID != null) || (this.tagTypeID != null && !this.tagTypeID.equals(other.tagTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.TagType[ tagTypeID=" + tagTypeID + " ]";
    }

    @XmlTransient
    public List<PhotoTag> getPhotoTagList() {
        return photoTagList;
    }

    public void setPhotoTagList(List<PhotoTag> photoTagList) {
        this.photoTagList = photoTagList;
    }
    
}
