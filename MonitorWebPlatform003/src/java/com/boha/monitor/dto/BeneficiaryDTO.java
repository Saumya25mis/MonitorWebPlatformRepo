/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.Beneficiary;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author aubreyM
 */
public class BeneficiaryDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer beneficiaryID, projectID;
    private String firstName;
    private String lastName;
    private String middleName;
    private String iDNumber;
    private String email, status;
    private String  siteNumber, cellphone;
    private Double amountAuthorized, amountPaid;
    private Date dateRegistered, phbDate;
    private CompanyDTO company;
    private String townshipName;

    public BeneficiaryDTO(Beneficiary a) {
        beneficiaryID = a.getBeneficiaryID();
        firstName = a.getFirstName();
        middleName = a.getMiddleName();
        iDNumber = a.getiDNumber();
        lastName = a.getLastName();
        email = a.getEmail();
        cellphone = a.getCellphone();
        dateRegistered = a.getDateRegistered();
        phbDate = a.getPhbDate();
        status = a.getStatus();
        if (a.getProject() != null) {
            projectID = a.getProject().getProjectID();
        }
        
        if (a.getCompany() != null) {
            company = new CompanyDTO(a.getCompany());
        }
        townshipName = a.getTownshipName();
        amountAuthorized = a.getAmountAuthorized();
        amountPaid = a.getAmountPaid();
        siteNumber = a.getSiteNumber();

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTownshipName() {
        return townshipName;
    }

    public void setTownshipName(String townshipName) {
        this.townshipName = townshipName;
    }

    public String getSiteNumber() {
        return siteNumber;
    }

    public void setSiteNumber(String siteNumber) {
        this.siteNumber = siteNumber;
    }

    public BeneficiaryDTO(Integer beneficiaryID) {
        this.beneficiaryID = beneficiaryID;
    }

    public Integer getBeneficiaryID() {
        return beneficiaryID;
    }

    public Integer getProjectID() {
        return projectID;
    }

    public void setProjectID(Integer projectID) {
        this.projectID = projectID;
    }

    public Double getAmountAuthorized() {
        return amountAuthorized;
    }

    public void setAmountAuthorized(Double amountAuthorized) {
        this.amountAuthorized = amountAuthorized;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setBeneficiaryID(Integer beneficiaryID) {
        this.beneficiaryID = beneficiaryID;
    }

    public Date getPhbDate() {
        return phbDate;
    }

    public void setPhbDate(Date phbDate) {
        this.phbDate = phbDate;
    }


    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getIDNumber() {
        return iDNumber;
    }

    public void setIDNumber(String iDNumber) {
        this.iDNumber = iDNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getiDNumber() {
        return iDNumber;
    }

    public void setiDNumber(String iDNumber) {
        this.iDNumber = iDNumber;
    }

   

}
