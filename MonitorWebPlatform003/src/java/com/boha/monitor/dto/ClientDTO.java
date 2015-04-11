/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.dto;

import com.boha.monitor.data.Client;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aubreyM
 */
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer clientID;
    private String clientName;
    private String email;
    private String cellphone;
    private String address;
    private String postCode;
    private Date dateRegistered;
    private List<ProjectDTO> projectList;
    private Integer companyID;
    

    public ClientDTO() {
    }

    public ClientDTO(Integer clientID) {
        this.clientID = clientID;
    }

    public ClientDTO(Client a) {
        this.clientID = a.getClientID();
        this.clientName = a.getClientName();
        this.email = a.getEmail();
        this.address = a.getAddress();
        this.postCode = a.getPostCode();
        this.companyID = a.getCompany().getCompanyID();
        this.cellphone = a.getCellphone();
        this.dateRegistered = a.getDateRegistered();
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public List<ProjectDTO> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<ProjectDTO> projectList) {
        this.projectList = projectList;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public Integer getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Integer companyID) {
        this.companyID = companyID;
    }


  
}
