/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.Company;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class CompanyDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer companyID, countryID;
    private String companyName;
    private String address, vatNumber, taxNumber;

    private List<ProjectDTO> projectList;
    private List<ProjectStatusTypeDTO> projectStatusTypeList;
    private List<CompanyStaffDTO> companyStaffList;
    private List<TaskStatusDTO> taskStatusList;
    private List<TaskDTO> taskList;
    private List<ClientDTO> clientList;
    private List<InvoiceDTO> invoiceList;
    private List<BankDetailDTO> bankDetailList;
    private List<GcmDeviceDTO> gcmDeviceList;
    private List<ContractorClaimDTO> contractorClaimList;
    private List<EngineerDTO> engineerList;

    public CompanyDTO() {
    }

    public CompanyDTO(Company a) {
        if (a == null) throw new UnsupportedOperationException("Company object from DB is null");
        this.companyID = a.getCompanyID();
        this.companyName = a.getCompanyName();
        address = a.getAddress();
        vatNumber = a.getVatNumber();
        taxNumber = a.getTaxNumber();
        if (a.getCountry() != null) {
            countryID = a.getCountry().getCountryID();
        }

    }

    private void log() {
        StringBuilder sb = new StringBuilder();
        sb.append("###### Company Data #############").append("\n");
        sb.append("Clients: ").append(clientList.size()).append("\n");
        sb.append("Projects: ").append(projectList.size()).append("\n");
        sb.append("Staff: ").append(companyStaffList.size()).append("\n");
        sb.append("Invoices: ").append(invoiceList.size()).append("\n");
        sb.append("Tasks: ").append(taskList.size()).append("\n");
        sb.append("TaskStatus: ").append(taskStatusList.size()).append("\n");
        sb.append("ProjectStatusTypes: ").append(projectStatusTypeList.size()).append("\n");
        sb.append("Devices: ").append(gcmDeviceList.size()).append("\n");
        sb.append("#######################");
        System.out.println(sb.toString());
    }

    public List<EngineerDTO> getEngineerList() {
        return engineerList;
    }

    public void setEngineerList(List<EngineerDTO> engineerList) {
        this.engineerList = engineerList;
    }

    public List<ContractorClaimDTO> getContractorClaimList() {
        return contractorClaimList;
    }

    public void setContractorClaimList(List<ContractorClaimDTO> contractorClaimList) {
        this.contractorClaimList = contractorClaimList;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public List<BankDetailDTO> getBankDetailList() {
        return bankDetailList;
    }

    public void setBankDetailList(List<BankDetailDTO> bankDetailList) {
        this.bankDetailList = bankDetailList;
    }

    public List<TaskDTO> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskDTO> taskList) {
        this.taskList = taskList;
    }

    public List<ClientDTO> getClientList() {
        return clientList;
    }

    public void setClientList(List<ClientDTO> clientList) {
        this.clientList = clientList;
    }

    public List<InvoiceDTO> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<InvoiceDTO> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public List<GcmDeviceDTO> getGcmDeviceList() {
        return gcmDeviceList;
    }

    public void setGcmDeviceList(List<GcmDeviceDTO> gcmDeviceList) {
        this.gcmDeviceList = gcmDeviceList;
    }

    public List<TaskStatusDTO> getTaskStatusList() {
        return taskStatusList;
    }

    public void setTaskStatusList(List<TaskStatusDTO> taskStatusList) {
        this.taskStatusList = taskStatusList;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<ProjectDTO> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectDTO> projectList) {
        this.projectList = projectList;
    }

    public List<ProjectStatusTypeDTO> getProjectStatusTypeList() {
        return projectStatusTypeList;
    }

    public void setProjectStatusTypeList(List<ProjectStatusTypeDTO> projectStatusTypeList) {
        this.projectStatusTypeList = projectStatusTypeList;
    }

    public List<CompanyStaffDTO> getCompanyStaffList() {
        return companyStaffList;
    }

    public void setCompanyStaffList(List<CompanyStaffDTO> companyStaffList) {
        this.companyStaffList = companyStaffList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyID != null ? companyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyDTO)) {
            return false;
        }
        CompanyDTO other = (CompanyDTO) object;
        if ((this.companyID == null && other.companyID != null) || (this.companyID != null && !this.companyID.equals(other.companyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Company[ companyID=" + companyID + " ]";
    }

}
