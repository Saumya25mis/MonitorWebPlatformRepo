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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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
@Table(name = "project")
@NamedQueries({
	@NamedQuery(name = "Project.findByCompany",
            query = "SELECT p FROM Project p WHERE p.company.companyID = :companyID order by p.projectName"),
    @NamedQuery(name = "Project.findByCompanyProjectNames",
            query = "SELECT p FROM Project p WHERE p.company.companyID = :companyID and p.projectName in :list order by p.projectName"),
    @NamedQuery(name = "Project.findByProgramme",
            query = "SELECT p FROM Project p WHERE p.programme.programmeID = :programmeID ORDER BY p.projectName ")
})
public class Project implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.EAGER)
    private List<TenderCompanyProject> tenderCompanyProjectList;
    @OneToMany(mappedBy = "project")
    private List<SimpleMessage> simpleMessageList;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne
    private Company company;
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<VideoUpload> videoUploadList;
    private static final long serialVersionUID = 1L;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.EAGER)
    private List<ProjectTask> projectTaskList;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    @OrderBy("dateTaken desc")
    private List<PhotoUpload> photoUploadList;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "projectID")
    private Integer projectID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "projectName")
    private String projectName;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "accuracy")
    private Float accuracy;
    @Column(name = "activeFlag")
    private Boolean activeFlag;
    @Column(name = "locationConfirmed")
    private Boolean locationConfirmed;
    @Size(max = 300)
    @Column(name = "address")
    private String address;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;

    @JoinColumn(name = "programmeID", referencedColumnName = "programmeID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Programme programme;
    @JoinColumn(name = "cityID", referencedColumnName = "cityID")
    @ManyToOne(optional = false)
    private City city;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.EAGER)
    private List<ProjectStatus> projectStatusList;
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<GcmDevice> gcmDeviceList;
    @OneToMany(mappedBy = "project")
    private List<Chat> chatList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.EAGER)
    private List<MonitorProject> monitorProjectList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project", fetch = FetchType.EAGER)
    private List<StaffProject> staffProjectList;

    public Project() {
    }

    public Project(Integer projectID) {
        this.projectID = projectID;
    }

    public Project(Integer projectID, String projectName) {
        this.projectID = projectID;
        this.projectName = projectName;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
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

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public Boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(Boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Boolean getLocationConfirmed() {
        return locationConfirmed;
    }

    public void setLocationConfirmed(Boolean locationConfirmed) {
        this.locationConfirmed = locationConfirmed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProjectStatus> getProjectStatusList() {
        return projectStatusList;
    }

    public void setProjectStatusList(List<ProjectStatus> projectStatusList) {
        this.projectStatusList = projectStatusList;
    }

    public List<GcmDevice> getGcmDeviceList() {
        return gcmDeviceList;
    }

    public void setGcmDeviceList(List<GcmDevice> gcmDeviceList) {
        this.gcmDeviceList = gcmDeviceList;
    }

    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }

    public List<MonitorProject> getMonitorProjectList() {
        return monitorProjectList;
    }

    public void setMonitorProjectList(List<MonitorProject> monitorProjectList) {
        this.monitorProjectList = monitorProjectList;
    }

    public List<StaffProject> getStaffProjectList() {
        return staffProjectList;
    }

    public void setStaffProjectList(List<StaffProject> staffProjectList) {
        this.staffProjectList = staffProjectList;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectID != null ? projectID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.projectID == null && other.projectID != null) || (this.projectID != null && !this.projectID.equals(other.projectID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Project[ projectID=" + projectID + " ]";
    }

    public List<PhotoUpload> getPhotoUploadList() {
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUpload> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public List<ProjectTask> getProjectTaskList() {
        return projectTaskList;
    }

    public void setProjectTaskList(List<ProjectTask> projectTaskList) {
        this.projectTaskList = projectTaskList;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<VideoUpload> getVideoUploadList() {
        return videoUploadList;
    }

    public void setVideoUploadList(List<VideoUpload> videoUploadList) {
        this.videoUploadList = videoUploadList;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<SimpleMessage> getSimpleMessageList() {
        return simpleMessageList;
    }

    public void setSimpleMessageList(List<SimpleMessage> simpleMessageList) {
        this.simpleMessageList = simpleMessageList;
    }

    public List<TenderCompanyProject> getTenderCompanyProjectList() {
        return tenderCompanyProjectList;
    }

    public void setTenderCompanyProjectList(List<TenderCompanyProject> tenderCompanyProjectList) {
        this.tenderCompanyProjectList = tenderCompanyProjectList;
    }

}
