/*
 * To change this license header, choose License Headers in ProjectDTO Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.dto;

import com.boha.monitor.data.Programme;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class ProgrammeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer programmeID;
    private String programmeName;
    private String description;
    private Long dateRegistered;
    private Integer completeFlag, portfolioID;
    private List<PhotoUploadDTO> photoUploadList;
    private List<ProjectDTO> projectList;
    private List<TaskDTO> taskList;

    public ProgrammeDTO() {
    }

    public ProgrammeDTO(Integer programmeID) {
        this.programmeID = programmeID;
    }

    public ProgrammeDTO(Programme a) {
        this.programmeID = a.getProgrammeID();
        this.programmeName = a.getProgrammeName();
        if (a.getDateRegistered() != null) {
            this.dateRegistered = a.getDateRegistered().getTime();
        }
        completeFlag = a.getCompleteFlag();
        description = a.getDescription();
        portfolioID = a.getPortfolio().getPortfolioID();
    }

    public List<TaskDTO> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskDTO> taskList) {
        this.taskList = taskList;
    }

  
    
    public Long getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Long dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Integer getProgrammeID() {
        return programmeID;
    }

    public void setProgrammeID(Integer programmeID) {
        this.programmeID = programmeID;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCompleteFlag() {
        return completeFlag;
    }

    public void setCompleteFlag(Integer completeFlag) {
        this.completeFlag = completeFlag;
    }

    public List<PhotoUploadDTO> getPhotoUploadList() {
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUploadDTO> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public List<ProjectDTO> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectDTO> projectList) {
        this.projectList = projectList;
    }

    public Integer getPortfolioID() {
        return portfolioID;
    }

    public void setPortfolioID(Integer portfolioID) {
        this.portfolioID = portfolioID;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (programmeID != null ? programmeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgrammeDTO)) {
            return false;
        }
        ProgrammeDTO other = (ProgrammeDTO) object;
        if ((this.programmeID == null && other.programmeID != null) || (this.programmeID != null && !this.programmeID.equals(other.programmeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Programme[ programmeID=" + programmeID + " ]";
    }
    
}
