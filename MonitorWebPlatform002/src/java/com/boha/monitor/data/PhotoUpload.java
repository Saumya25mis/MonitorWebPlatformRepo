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
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "photoUpload")
@NamedQueries({
    @NamedQuery(name = "PhotoUpload.findProjectPhotosByCompany", 
            query = "SELECT p FROM PhotoUpload p "
                    + "where p.project is not null and p.company.companyID = :companyID and (p.project.completeFlag is null)  order by p.dateTaken desc"),
    
    @NamedQuery(name = "PhotoUpload.findProjectSitePhotosByCompany", 
            query = "SELECT p FROM PhotoUpload p WHERE p.projectSite is not null  and (p.project.completeFlag is null) "
                    + "and p.company.companyID = :companyID "
                    + "order by p.dateTaken desc"),
    @NamedQuery(name = "PhotoUpload.findProjectSitePhotosByProject", 
            query = "SELECT p FROM PhotoUpload p WHERE p.projectSite.project.projectID = :projectID  and (p.project.completeFlag is null)"
                    + "order by p.dateTaken desc"),
    @NamedQuery(name = "PhotoUpload.findProjectSitePhotos", 
            query = "SELECT p FROM PhotoUpload p WHERE p.projectSite.projectSiteID = :projectSiteID "
                    + "order by p.dateTaken desc"),
    @NamedQuery(name = "PhotoUpload.findProjectPhotos", 
            query = "SELECT p FROM PhotoUpload p WHERE p.project.projectID = :projectID  and (p.project.completeFlag is null)"
                    + "order by p.dateTaken desc"),
    @NamedQuery(name = "PhotoUpload.findAllProjectPhotos", 
            query = "SELECT p FROM PhotoUpload p WHERE "
                    + "p.projectSite.project.projectID = :projectID "
                    + "order by p.dateTaken desc"),
    @NamedQuery(name = "PhotoUpload.findTaskPhotos", 
            query = "SELECT p FROM PhotoUpload p WHERE p.projectSiteTask = :projectSiteTaskID "
                    + "order by p.dateTaken desc"),
    
    @NamedQuery(name = "PhotoUpload.findSiteTaskPhotosByCompany", 
            query = "SELECT p FROM PhotoUpload p WHERE p.projectSiteTask is not null "
                    + "and p.company.companyID = :companyID order by p.dateTaken desc"),
    @NamedQuery(name = "PhotoUpload.findSiteTaskPhotosByProject", 
            query = "SELECT p FROM PhotoUpload p WHERE p.projectSiteTask is not null and p.project.projectID = :projectID order by p.dateTaken desc"),
    @NamedQuery(name = "PhotoUpload.findByDateTaken", 
            query = "SELECT p FROM PhotoUpload p WHERE p.dateTaken = :dateTaken"),
   })
public class PhotoUpload implements Serializable {
    @Column(name = "staffPicture")
    private Integer staffPicture;
    @Column(name = "accuracy")
    private Float accuracy;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "thumbFlag")
    private Integer thumbFlag;
    @Column(name = "dateUploaded")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUploaded;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "photoUploadID")
    private Integer photoUploadID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pictureType")
    private int pictureType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateTaken")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTaken;
    @Size(max = 255)
    @Column(name = "uri")
    private String uri;
    @Size(max = 255)
    @Column(name = "thumbFilePath")
    private String thumbFilePath;
    @JoinColumn(name = "projectSiteTaskID", referencedColumnName = "projectSiteTaskID")
    @ManyToOne
    private ProjectSiteTask projectSiteTask;
    @JoinColumn(name = "projectSiteID", referencedColumnName = "projectSiteID")
    @ManyToOne
    private ProjectSite projectSite;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne
    private Project project;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne(optional = false)
    private Company company;
    @JoinColumn(name = "companyStaffID", referencedColumnName = "companyStaffID")
    @ManyToOne
    private CompanyStaff companyStaff;

    public PhotoUpload() {
    }

    public PhotoUpload(Integer photoUploadID) {
        this.photoUploadID = photoUploadID;
    }

    public PhotoUpload(Integer photoUploadID, int pictureType, Date dateTaken, double latitude, double longitude) {
        this.photoUploadID = photoUploadID;
        this.pictureType = pictureType;
        this.dateTaken = dateTaken;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getPhotoUploadID() {
        return photoUploadID;
    }

    public String getThumbFilePath() {
        return thumbFilePath;
    }

    public void setThumbFilePath(String thumbFilePath) {
        this.thumbFilePath = thumbFilePath;
    }

    public void setPhotoUploadID(Integer photoUploadID) {
        this.photoUploadID = photoUploadID;
    }

    public int getPictureType() {
        return pictureType;
    }

    public void setPictureType(int pictureType) {
        this.pictureType = pictureType;
    }

    public ProjectSiteTask getProjectSiteTask() {
        return projectSiteTask;
    }

    public void setProjectSiteTask(ProjectSiteTask projectSiteTask) {
        this.projectSiteTask = projectSiteTask;
    }

    public ProjectSite getProjectSite() {
        return projectSite;
    }

    public void setProjectSite(ProjectSite projectSite) {
        this.projectSite = projectSite;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public CompanyStaff getCompanyStaff() {
        return companyStaff;
    }

    public void setCompanyStaff(CompanyStaff companyStaff) {
        this.companyStaff = companyStaff;
    }

    public Date getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Date dateTaken) {
        this.dateTaken = dateTaken;
    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (photoUploadID != null ? photoUploadID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhotoUpload)) {
            return false;
        }
        PhotoUpload other = (PhotoUpload) object;
        if ((this.photoUploadID == null && other.photoUploadID != null) || (this.photoUploadID != null && !this.photoUploadID.equals(other.photoUploadID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.PhotoUpload[ photoUploadID=" + photoUploadID + " ]";
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

    public Integer getThumbFlag() {
        return thumbFlag;
    }

    public void setThumbFlag(Integer thumbFlag) {
        this.thumbFlag = thumbFlag;
    }

    public Date getDateUploaded() {
        return dateUploaded;
    }

    public void setDateUploaded(Date dateUploaded) {
        this.dateUploaded = dateUploaded;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getStaffPicture() {
        return staffPicture;
    }

    public void setStaffPicture(Integer staffPicture) {
        this.staffPicture = staffPicture;
    }
    
}
