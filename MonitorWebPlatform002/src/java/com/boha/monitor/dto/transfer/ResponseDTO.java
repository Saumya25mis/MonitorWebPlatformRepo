/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto.transfer;

import com.boha.monitor.dto.BankDTO;
import com.boha.monitor.dto.BankDetailDTO;
import com.boha.monitor.dto.BeneficiaryDTO;
import com.boha.monitor.dto.CityDTO;
import com.boha.monitor.dto.ClientDTO;
import com.boha.monitor.dto.CompanyDTO;
import com.boha.monitor.dto.CompanyStaffDTO;
import com.boha.monitor.dto.ContractorClaimDTO;
import com.boha.monitor.dto.ContractorClaimSiteDTO;
import com.boha.monitor.dto.CountryDTO;
import com.boha.monitor.dto.EngineerDTO;
import com.boha.monitor.dto.ErrorStoreAndroidDTO;
import com.boha.monitor.dto.ErrorStoreDTO;
import com.boha.monitor.dto.HappyLetterDTO;
import com.boha.monitor.dto.InvoiceDTO;
import com.boha.monitor.dto.InvoiceItemDTO;
import com.boha.monitor.dto.ProjectDTO;
import com.boha.monitor.dto.ProjectDiaryRecordDTO;
import com.boha.monitor.dto.ProjectEngineerDTO;
import com.boha.monitor.dto.ProjectSiteDTO;
import com.boha.monitor.dto.ProjectSiteTaskDTO;
import com.boha.monitor.dto.ProjectSiteTaskStatusDTO;
import com.boha.monitor.dto.ProjectStatusTypeDTO;
import com.boha.monitor.dto.ProvinceDTO;
import com.boha.monitor.dto.SubTaskDTO;
import com.boha.monitor.dto.SubTaskStatusDTO;
import com.boha.monitor.dto.TaskDTO;
import com.boha.monitor.dto.TaskPriceDTO;
import com.boha.monitor.dto.TaskStatusDTO;
import com.boha.monitor.dto.TownshipDTO;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class ResponseDTO {

    private Integer statusCode = 0, statusCountInPeriod, goodCount, badCount;
    private String message = "Request is KOOL!", 
            sessionID, GCMRegistrationID, fileString;
    private Double elapsedRequestTimeInSeconds;
    private String log;
    private List<String> taskImageFileNameList;
    private List<BankDTO> bankList;
    private List<SubTaskStatusDTO> subTaskStatusList;
    private List<TaskPriceDTO> taskPriceList;
    private List<String> siteImageFileNameList;
    private List<SubTaskDTO> subTaskList;
    private List<PhotoUploadDTO> photoUploadList;
    private List<TaskStatusDTO> taskStatusList;
    private List<ProjectStatusTypeDTO> projectStatusTypeList;
    private List<ProjectSiteDTO> projectSiteList;
    private List<ProjectDTO> projectList;
    private List<CompanyStaffDTO> companyStaffList;
    private List<ProjectDiaryRecordDTO> projectDiaryRecordList;
    private List<ProjectSiteTaskDTO> projectSiteTaskList;
    private List<ProjectSiteTaskStatusDTO> projectSiteTaskStatusList;
    private List<ErrorStoreDTO> errorStoreList;
    private List<ErrorStoreAndroidDTO> errorStoreAndroidList;
    private List<InvoiceDTO> invoiceList;
    private List<BeneficiaryDTO> beneficiaryList;
    private List<ProvinceDTO> provinceList;
    private List<HappyLetterDTO> happyLetterList;
    private List<ClientDTO> clientList;
    private List<TaskDTO> taskList;
    private List<CityDTO> cityList;
    private List<TownshipDTO> townshipList;
    private List<CountryDTO> countryList;
    private List<ContractorClaimDTO> contractorClaimList;
    private List<ContractorClaimSiteDTO> contractorClaimSiteList;
    private List<InvoiceItemDTO> invoiceItemList;
    private List<BankDetailDTO> bankDetailList;
    private List<EngineerDTO> engineerList;
    private List<ProjectEngineerDTO> projectEngineerList;
    //
    private CompanyDTO company;
    private CompanyStaffDTO companyStaff;

    public Integer getGoodCount() {
        return goodCount;
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

    public void setGoodCount(Integer goodCount) {
        this.goodCount = goodCount;
    }

    public Integer getBadCount() {
        return badCount;
    }

    public void setBadCount(Integer badCount) {
        this.badCount = badCount;
    }

    

    public Integer getStatusCountInPeriod() {
        return statusCountInPeriod;
    }

    public void setStatusCountInPeriod(Integer statusCountInPeriod) {
        this.statusCountInPeriod = statusCountInPeriod;
    }

    public List<TaskPriceDTO> getTaskPriceList() {
        return taskPriceList;
    }

    public void setTaskPriceList(List<TaskPriceDTO> taskPriceList) {
        this.taskPriceList = taskPriceList;
    }

    
    public List<EngineerDTO> getEngineerList() {
        return engineerList;
    }

    public void setEngineerList(List<EngineerDTO> engineerList) {
        this.engineerList = engineerList;
    }

    public List<ProjectEngineerDTO> getProjectEngineerList() {
        return projectEngineerList;
    }

    public void setProjectEngineerList(List<ProjectEngineerDTO> projectEngineerList) {
        this.projectEngineerList = projectEngineerList;
    }

    
    public List<PhotoUploadDTO> getPhotoUploadList() {
        return photoUploadList;
    }

    public List<BankDTO> getBankList() {
        return bankList;
    }

    public List<SubTaskStatusDTO> getSubTaskStatusList() {
        return subTaskStatusList;
    }

    public void setSubTaskStatusList(List<SubTaskStatusDTO> subTaskStatusList) {
        this.subTaskStatusList = subTaskStatusList;
    }

    public List<SubTaskDTO> getSubTaskList() {
        return subTaskList;
    }

    public void setSubTaskList(List<SubTaskDTO> subTaskList) {
        this.subTaskList = subTaskList;
    }

    public void setBankList(List<BankDTO> bankList) {
        this.bankList = bankList;
    }

    public List<BankDetailDTO> getBankDetailList() {
        return bankDetailList;
    }

    public void setBankDetailList(List<BankDetailDTO> bankDetailList) {
        this.bankDetailList = bankDetailList;
    }

    public void setPhotoUploadList(List<PhotoUploadDTO> photoUploadList) {
        this.photoUploadList = photoUploadList;
    }

    public List<InvoiceItemDTO> getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(List<InvoiceItemDTO> invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
    }

    public List<ContractorClaimDTO> getContractorClaimList() {
        return contractorClaimList;
    }

    public void setContractorClaimList(List<ContractorClaimDTO> contractorClaimList) {
        this.contractorClaimList = contractorClaimList;
    }

    public List<ContractorClaimSiteDTO> getContractorClaimSiteList() {
        return contractorClaimSiteList;
    }

    public void setContractorClaimSiteList(List<ContractorClaimSiteDTO> contractorClaimSiteList) {
        this.contractorClaimSiteList = contractorClaimSiteList;
    }

    
    public List<CountryDTO> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryDTO> countryList) {
        this.countryList = countryList;
    }

    
    public List<CityDTO> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityDTO> cityList) {
        this.cityList = cityList;
    }

    public List<TownshipDTO> getTownshipList() {
        return townshipList;
    }

    public void setTownshipList(List<TownshipDTO> townshipList) {
        this.townshipList = townshipList;
    }

    
    public List<TaskDTO> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskDTO> taskList) {
        this.taskList = taskList;
    }

    

    public List<InvoiceDTO> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<InvoiceDTO> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public List<BeneficiaryDTO> getBeneficiaryList() {
        return beneficiaryList;
    }

    public void setBeneficiaryList(List<BeneficiaryDTO> beneficiaryList) {
        this.beneficiaryList = beneficiaryList;
    }

    public List<ProvinceDTO> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ProvinceDTO> provinceList) {
        this.provinceList = provinceList;
    }

    public List<HappyLetterDTO> getHappyLetterList() {
        return happyLetterList;
    }

    public void setHappyLetterList(List<HappyLetterDTO> happyLetterList) {
        this.happyLetterList = happyLetterList;
    }

    public List<ClientDTO> getClientList() {
        return clientList;
    }

    public void setClientList(List<ClientDTO> clientList) {
        this.clientList = clientList;
    }

    public CompanyStaffDTO getCompanyStaff() {
        return companyStaff;
    }

    public void setCompanyStaff(CompanyStaffDTO companyStaff) {
        this.companyStaff = companyStaff;
    }

    public String getGCMRegistrationID() {
        return GCMRegistrationID;
    }

    public void setGCMRegistrationID(String GCMRegistrationID) {
        this.GCMRegistrationID = GCMRegistrationID;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getTaskImageFileNameList() {
        return taskImageFileNameList;
    }

    public void setTaskImageFileNameList(List<String> taskImageFileNameList) {
        this.taskImageFileNameList = taskImageFileNameList;
    }

    public List<String> getSiteImageFileNameList() {
        return siteImageFileNameList;
    }

    public void setSiteImageFileNameList(List<String> siteImageFileNameList) {
        this.siteImageFileNameList = siteImageFileNameList;
    }

    public List<ErrorStoreDTO> getErrorStoreList() {
        return errorStoreList;
    }

    public void setErrorStoreList(List<ErrorStoreDTO> errorStoreList) {
        this.errorStoreList = errorStoreList;
    }

    public List<ErrorStoreAndroidDTO> getErrorStoreAndroidList() {
        return errorStoreAndroidList;
    }

    public void setErrorStoreAndroidList(List<ErrorStoreAndroidDTO> errorStoreAndroidList) {
        this.errorStoreAndroidList = errorStoreAndroidList;
    }

    public List<TaskStatusDTO> getTaskStatusList() {
        return taskStatusList;
    }

    public void setTaskStatusList(List<TaskStatusDTO> taskStatusList) {
        this.taskStatusList = taskStatusList;
    }

    public List<ProjectStatusTypeDTO> getProjectStatusTypeList() {
        return projectStatusTypeList;
    }

    public void setProjectStatusTypeList(List<ProjectStatusTypeDTO> projectStatusTypeList) {
        this.projectStatusTypeList = projectStatusTypeList;
    }

    public String getFileString() {
        return fileString;
    }

    public void setFileString(String fileString) {
        this.fileString = fileString;
    }

    public List<ProjectSiteDTO> getProjectSiteList() {
        return projectSiteList;
    }

    public void setProjectSiteList(List<ProjectSiteDTO> projectSiteList) {
        this.projectSiteList = projectSiteList;
    }

    public List<ProjectDTO> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectDTO> projectList) {
        this.projectList = projectList;
    }

    public List<CompanyStaffDTO> getCompanyStaffList() {
        return companyStaffList;
    }

    public void setCompanyStaffList(List<CompanyStaffDTO> companyStaffList) {
        this.companyStaffList = companyStaffList;
    }


    public List<ProjectDiaryRecordDTO> getProjectDiaryRecordList() {
        return projectDiaryRecordList;
    }

    public void setProjectDiaryRecordList(List<ProjectDiaryRecordDTO> projectDiaryRecordList) {
        this.projectDiaryRecordList = projectDiaryRecordList;
    }

    public List<ProjectSiteTaskDTO> getProjectSiteTaskList() {
        return projectSiteTaskList;
    }

    public void setProjectSiteTaskList(List<ProjectSiteTaskDTO> projectSiteTaskList) {
        this.projectSiteTaskList = projectSiteTaskList;
    }

    public List<ProjectSiteTaskStatusDTO> getProjectSiteTaskStatusList() {
        return projectSiteTaskStatusList;
    }

    public void setProjectSiteTaskStatusList(List<ProjectSiteTaskStatusDTO> projectSiteTaskStatusList) {
        this.projectSiteTaskStatusList = projectSiteTaskStatusList;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

}
