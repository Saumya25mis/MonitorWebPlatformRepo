/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "taskStatusType")
@NamedQueries({
    @NamedQuery(name = "TaskStatusType.findByCompany", 
            query = "SELECT t FROM TaskStatusType t WHERE t.company.companyID = :companyID order by t.taskStatusTypeName")
})
public class TaskStatusType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taskStatusTypeID")
    private Integer taskStatusTypeID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "taskStatusTypeName")
    private String taskStatusTypeName;
    @Column(name = "statusColor")
    private Short statusColor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskStatusType")
    private List<ProjectTaskStatus> projectTaskStatusList;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne(optional = false)
    private Company company;

    public TaskStatusType() {
    }

    public TaskStatusType(Integer taskStatusTypeID) {
        this.taskStatusTypeID = taskStatusTypeID;
    }

    public TaskStatusType(Integer taskStatusTypeID, String taskStatusTypeName) {
        this.taskStatusTypeID = taskStatusTypeID;
        this.taskStatusTypeName = taskStatusTypeName;
    }

    public Integer getTaskStatusTypeID() {
        return taskStatusTypeID;
    }

    public void setTaskStatusTypeID(Integer taskStatusTypeID) {
        this.taskStatusTypeID = taskStatusTypeID;
    }

    public String getTaskStatusTypeName() {
        return taskStatusTypeName;
    }

    public void setTaskStatusTypeName(String taskStatusTypeName) {
        this.taskStatusTypeName = taskStatusTypeName;
    }

    public Short getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(Short statusColor) {
        this.statusColor = statusColor;
    }

    public List<ProjectTaskStatus> getProjectTaskStatusList() {
        return projectTaskStatusList;
    }

    public void setProjectTaskStatusList(List<ProjectTaskStatus> projectTaskStatusList) {
        this.projectTaskStatusList = projectTaskStatusList;
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
        hash += (taskStatusTypeID != null ? taskStatusTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TaskStatusType)) {
            return false;
        }
        TaskStatusType other = (TaskStatusType) object;
        if ((this.taskStatusTypeID == null && other.taskStatusTypeID != null) || (this.taskStatusTypeID != null && !this.taskStatusTypeID.equals(other.taskStatusTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.TaskStatusType[ taskStatusTypeID=" + taskStatusTypeID + " ]";
    }
    
}
