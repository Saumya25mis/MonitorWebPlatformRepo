/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto.transfer;

import com.boha.monitor.dto.CompanyDTO;
import com.boha.monitor.dto.CompanyExperienceDTO;
import com.boha.monitor.dto.CompanyTypeDTO;
import com.boha.monitor.dto.StaffDTO;
import com.boha.monitor.dto.ExperienceTypeDTO;
import com.boha.monitor.dto.GcmDeviceDTO;
import com.boha.monitor.dto.LocationTrackerDTO;
import com.boha.monitor.dto.MonitorDTO;
import com.boha.monitor.dto.MonitorProjectDTO;
import com.boha.monitor.dto.MonitorTradeDTO;
import com.boha.monitor.dto.PhotoUploadDTO;
import com.boha.monitor.dto.PortfolioDTO;
import com.boha.monitor.dto.ProgrammeDTO;
import com.boha.monitor.dto.ProjectDTO;
import com.boha.monitor.dto.ProjectStatusTypeDTO;
import com.boha.monitor.dto.ProjectTaskDTO;
import com.boha.monitor.dto.ProjectTaskStatusDTO;
import com.boha.monitor.dto.StaffProjectDTO;
import com.boha.monitor.dto.SubTaskDTO;
import com.boha.monitor.dto.TaskDTO;
import com.boha.monitor.dto.TaskStatusTypeDTO;
import com.boha.monitor.dto.TenderCompanyDTO;
import com.boha.monitor.dto.TenderCompanyProjectDTO;
import com.boha.monitor.dto.VideoUploadDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains properties to handle every kind of expected response.
 * Used by servlets and web socket endpoints to return data to caller
 * 
 * @author aubreyM
 */
public class ResponseDTO {

    private int statusCode;
    private Integer statusCount,
            goodCount, badCount, gcmSuccess, gcmFailure;
    private String message = "Request is KOOL!",
            sessionID, GCMRegistrationID, fileString;
    private Double elapsedRequestTimeInSeconds;
    private String log;
    private StaffDTO staff;
    private MonitorDTO monitor;
    
    List<TenderCompanyDTO> tenderCompanyList;
    List<TenderCompanyProjectDTO> tenderCompanyProjectList;
    List<ExperienceTypeDTO> experienceTypeList;
    List<CompanyTypeDTO> companyTypeList;
    List<CompanyExperienceDTO> companyExperienceList;

    private List<CompanyDTO> companyList;
    private List<LocationTrackerDTO> locationTrackerList;
    private List<String> taskImageFileNameList;
    private List<StaffProjectDTO> staffProjectList;
    private List<MonitorProjectDTO> monitorProjectList;
    private List<String> siteImageFileNameList;
    private List<ProjectStatusTypeDTO> projectStatusTypeList;
    private List<ProjectDTO> projectList;
    private List<TaskDTO> taskList;
    private List<ProjectTaskStatusDTO> projectTaskStatusList;
    private List<TaskStatusTypeDTO> taskStatusTypeList;
    private List<ProjectTaskDTO> projectTaskList;
    private List<PhotoUploadDTO> photoUploadList;
    private List<SubTaskDTO> subTaskList;
    private List<MonitorTradeDTO> monitorTradeList;
    //
    private CompanyDTO company;
    private List<MonitorDTO> monitorList;
    private List<StaffDTO> staffList;
    private ProjectTaskStatusDTO lastStatus;
    private List<PortfolioDTO> portfolioList;
    private List<ProgrammeDTO> programmeList;
    private List<GcmDeviceDTO> gcmDeviceList;
    private List<VideoUploadDTO> videoUploadList;

    public List<TenderCompanyDTO> getTenderCompanyList() {
        if (tenderCompanyList == null) {
            tenderCompanyList = new ArrayList<>();
        }
        return tenderCompanyList;
    }

    public void setTenderCompanyList(List<TenderCompanyDTO> tenderCompanyList) {
        this.tenderCompanyList = tenderCompanyList;
    }

    public List<TenderCompanyProjectDTO> getTenderCompanyProjectList() {
        if (tenderCompanyProjectList == null) {
            tenderCompanyProjectList = new ArrayList<>();
        }
        return tenderCompanyProjectList;
    }

    public void setTenderCompanyProjectList(List<TenderCompanyProjectDTO> tenderCompanyProjectList) {
        this.tenderCompanyProjectList = tenderCompanyProjectList;
    }


    public List<ExperienceTypeDTO> getExperienceTypeList() {
        return experienceTypeList;
    }

    public void setExperienceTypeList(List<ExperienceTypeDTO> experienceTypeList) {
        this.experienceTypeList = experienceTypeList;
    }

    public List<CompanyTypeDTO> getCompanyTypeList() {
        return companyTypeList;
    }

    public void setCompanyTypeList(List<CompanyTypeDTO> companyTypeList) {
        this.companyTypeList = companyTypeList;
    }

    public List<CompanyExperienceDTO> getCompanyExperienceList() {
        return companyExperienceList;
    }

    public void setCompanyExperienceList(List<CompanyExperienceDTO> companyExperienceList) {
        this.companyExperienceList = companyExperienceList;
    }

    public List<VideoUploadDTO> getVideoUploadList() {
        if (videoUploadList == null) {
            videoUploadList = new ArrayList<>();
        }
        return videoUploadList;
    }

    public void setVideoUploadList(List<VideoUploadDTO> videoUploadList) {
        this.videoUploadList = videoUploadList;
    }

    
    public StaffDTO getStaff() {
        return staff;
    }

    public void setStaff(StaffDTO staff) {
        this.staff = staff;
    }

    public MonitorDTO getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorDTO monitor) {
        this.monitor = monitor;
    }

    
    public List<GcmDeviceDTO> getGcmDeviceList() {
        if (gcmDeviceList == null) {
            gcmDeviceList = new ArrayList<>();
        }
        return gcmDeviceList;
    }

    public void setGcmDeviceList(List<GcmDeviceDTO> gcmDeviceList) {
        this.gcmDeviceList = gcmDeviceList;
    }

    
    public List<SubTaskDTO> getSubTaskList() {
        return subTaskList;
    }

    public void setSubTaskList(List<SubTaskDTO> subTaskList) {
        this.subTaskList = subTaskList;
    }

    public List<MonitorTradeDTO> getMonitorTradeList() {
        return monitorTradeList;
    }

    public void setMonitorTradeList(List<MonitorTradeDTO> monitorTradeList) {
        this.monitorTradeList = monitorTradeList;
    }

    
    public List<CompanyDTO> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<CompanyDTO> companyList) {
        this.companyList = companyList;
    }

    
    public List<ProgrammeDTO> getProgrammeList() {
        if (programmeList == null) {
            programmeList = new ArrayList<>();
        }
        return programmeList;
    }

    public void setProgrammeList(List<ProgrammeDTO> programmeList) {
        this.programmeList = programmeList;
    }
    
    

    public List<MonitorProjectDTO> getMonitorProjectList() {
        if (monitorProjectList == null) {
            monitorProjectList = new ArrayList<>();
        }
        return monitorProjectList;
    }

    public void setMonitorProjectList(List<MonitorProjectDTO> monitorProjectList) {
        this.monitorProjectList = monitorProjectList;
    }

 

    public List<PortfolioDTO> getPortfolioList() {
        return portfolioList;
    }

    public void setPortfolioList(List<PortfolioDTO> portfolioList) {
        this.portfolioList = portfolioList;
    }


    public List<PhotoUploadDTO> getPhotoUploadList() {
        if (photoUploadList == null) {
            photoUploadList = new ArrayList<>();
        }
        return photoUploadList;
    }

    public void setPhotoUploadList(List<PhotoUploadDTO> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public List<ProjectTaskDTO> getProjectTaskList() {
        if (projectTaskList == null) {
            projectTaskList = new ArrayList<>();
        }
        return projectTaskList;
    }

    public void setProjectTaskList(List<ProjectTaskDTO> projectTaskList) {
        this.projectTaskList = projectTaskList;
    }

    public List<TaskStatusTypeDTO> getTaskStatusTypeList() {
        if (taskStatusTypeList == null) {
            taskStatusTypeList = new ArrayList<>();
        }
        return taskStatusTypeList;
    }

    public void setTaskStatusTypeList(List<TaskStatusTypeDTO> taskStatusTypeList) {
        this.taskStatusTypeList = taskStatusTypeList;
    }

    public List<ProjectTaskStatusDTO> getProjectTaskStatusList() {
        return projectTaskStatusList;
    }

    public void setProjectTaskStatusList(List<ProjectTaskStatusDTO> projectTaskStatusList) {
        this.projectTaskStatusList = projectTaskStatusList;
    }

    public List<MonitorDTO> getMonitorList() {
        if (monitorList == null) {
            monitorList = new ArrayList<>();
        }
        return monitorList;
    }

    public void setMonitorList(List<MonitorDTO> monitorList) {
        this.monitorList = monitorList;
    }

    public List<StaffDTO> getStaffList() {
        if (staffList == null) {
            staffList = new ArrayList<>();
        }
        return staffList;
    }

    public void setStaffList(List<StaffDTO> staffList) {
        this.staffList = staffList;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getStatusCount() {
        return statusCount;
    }

    public void setStatusCount(Integer statusCount) {
        this.statusCount = statusCount;
    }

    public ProjectTaskStatusDTO getLastStatus() {
        return lastStatus;
    }

    public void setLastStatus(ProjectTaskStatusDTO lastStatus) {
        this.lastStatus = lastStatus;
    }

    public Integer getGoodCount() {
        return goodCount;
    }

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getBadCount() {
        return badCount;
    }

    public void setBadCount(Integer badCount) {
        this.badCount = badCount;
    }

    public Integer getGcmSuccess() {
        return gcmSuccess;
    }

    public void setGcmSuccess(Integer gcmSuccess) {
        this.gcmSuccess = gcmSuccess;
    }

    public Integer getGcmFailure() {
        return gcmFailure;
    }

    public void setGcmFailure(Integer gcmFailure) {
        this.gcmFailure = gcmFailure;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getGCMRegistrationID() {
        return GCMRegistrationID;
    }

    public void setGCMRegistrationID(String GCMRegistrationID) {
        this.GCMRegistrationID = GCMRegistrationID;
    }

    public String getFileString() {
        return fileString;
    }

    public void setFileString(String fileString) {
        this.fileString = fileString;
    }

    public Double getElapsedRequestTimeInSeconds() {
        return elapsedRequestTimeInSeconds;
    }

    public void setElapsedRequestTimeInSeconds(Double elapsedRequestTimeInSeconds) {
        this.elapsedRequestTimeInSeconds = elapsedRequestTimeInSeconds;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public List<LocationTrackerDTO> getLocationTrackerList() {
        if (locationTrackerList == null) {
            locationTrackerList = new ArrayList<>();
        }
        return locationTrackerList;
    }

    public void setLocationTrackerList(List<LocationTrackerDTO> locationTrackerList) {
        this.locationTrackerList = locationTrackerList;
    }

    public List<String> getTaskImageFileNameList() {
        return taskImageFileNameList;
    }

    public void setTaskImageFileNameList(List<String> taskImageFileNameList) {
        this.taskImageFileNameList = taskImageFileNameList;
    }

    public List<StaffProjectDTO> getStaffProjectList() {
        return staffProjectList;
    }

    public void setStaffProjectList(List<StaffProjectDTO> staffProjectList) {
        this.staffProjectList = staffProjectList;
    }

    public List<String> getSiteImageFileNameList() {
        return siteImageFileNameList;
    }

    public void setSiteImageFileNameList(List<String> siteImageFileNameList) {
        this.siteImageFileNameList = siteImageFileNameList;
    }

    public List<ProjectStatusTypeDTO> getProjectStatusTypeList() {
        return projectStatusTypeList;
    }

    public void setProjectStatusTypeList(List<ProjectStatusTypeDTO> projectStatusTypeList) {
        this.projectStatusTypeList = projectStatusTypeList;
    }

    public List<ProjectDTO> getProjectList() {
        if (projectList == null) 
            projectList = new ArrayList<>();
        return projectList;
    }

    public void setProjectList(List<ProjectDTO> projectList) {
        this.projectList = projectList;
    }

    public List<TaskDTO> getTaskList() {
        if (taskList == null) {
            taskList = new ArrayList<>();
        }
        return taskList;
    }

    public void setTaskList(List<TaskDTO> taskList) {
        this.taskList = taskList;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

}
