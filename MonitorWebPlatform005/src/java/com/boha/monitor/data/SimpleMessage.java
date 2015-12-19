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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "simpleMessage")
@NamedQueries({
    @NamedQuery(name = "SimpleMessage.findAll", query = "SELECT s FROM SimpleMessage s"),
    @NamedQuery(name = "SimpleMessage.findBySimpleMessageID", query = "SELECT s FROM SimpleMessage s WHERE s.simpleMessageID = :simpleMessageID"),
    @NamedQuery(name = "SimpleMessage.findByMessageDate", query = "SELECT s FROM SimpleMessage s WHERE s.messageDate = :messageDate"),
    @NamedQuery(name = "SimpleMessage.findByLocationRequest", query = "SELECT s FROM SimpleMessage s WHERE s.locationRequest = :locationRequest")})
public class SimpleMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "simpleMessageID")
    private Integer simpleMessageID;
    @Lob
    @Size(max = 65535)
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Column(name = "messageDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date messageDate;
    @Column(name = "locationRequest")
    private Boolean locationRequest;
     @Column(name = "emergency")
    private Boolean emergency;
    @JoinColumn(name = "monitorID", referencedColumnName = "monitorID")
    @ManyToOne
    private Monitor monitor;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne
    private Company company;
    @JoinColumn(name = "staffID", referencedColumnName = "staffID")
    @ManyToOne
    private Staff staff;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne
    private Project project;
    @OneToMany(mappedBy = "simpleMessage")
    private List<SimpleMessageDestination> simpleMessageDestinationList;
    @OneToMany(mappedBy = "simpleMessage")
    private List<SimpleMessageImage> simpleMessageImageList;

    public SimpleMessage() {
    }

    public SimpleMessage(Integer simpleMessageID) {
        this.simpleMessageID = simpleMessageID;
    }

    public SimpleMessage(Integer simpleMessageID, Date messageDate) {
        this.simpleMessageID = simpleMessageID;
        this.messageDate = messageDate;
    }

    public Integer getSimpleMessageID() {
        return simpleMessageID;
    }

    public void setSimpleMessageID(Integer simpleMessageID) {
        this.simpleMessageID = simpleMessageID;
    }

    public Boolean getEmergency() {
        return emergency;
    }

    public void setEmergency(Boolean emergency) {
        this.emergency = emergency;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public Boolean getLocationRequest() {
        return locationRequest;
    }

    public void setLocationRequest(Boolean locationRequest) {
        this.locationRequest = locationRequest;
    }

    public List<SimpleMessageDestination> getSimpleMessageDestinationList() {
        return simpleMessageDestinationList;
    }

    public void setSimpleMessageDestinationList(List<SimpleMessageDestination> simpleMessageDestinationList) {
        this.simpleMessageDestinationList = simpleMessageDestinationList;
    }

    public List<SimpleMessageImage> getSimpleMessageImageList() {
        return simpleMessageImageList;
    }

    public void setSimpleMessageImageList(List<SimpleMessageImage> simpleMessageImageList) {
        this.simpleMessageImageList = simpleMessageImageList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (simpleMessageID != null ? simpleMessageID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SimpleMessage)) {
            return false;
        }
        SimpleMessage other = (SimpleMessage) object;
        if ((this.simpleMessageID == null && other.simpleMessageID != null) || (this.simpleMessageID != null && !this.simpleMessageID.equals(other.simpleMessageID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.SimpleMessage[ simpleMessageID=" + simpleMessageID + " ]";
    }
    
}
