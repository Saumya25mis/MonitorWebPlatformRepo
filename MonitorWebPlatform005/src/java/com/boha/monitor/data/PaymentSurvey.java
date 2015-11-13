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
@Table(name = "paymentSurvey")
@NamedQueries({
    @NamedQuery(name = "PaymentSurvey.findAll", query = "SELECT p FROM PaymentSurvey p"),
    @NamedQuery(name = "PaymentSurvey.findByPaymentSurveyID", query = "SELECT p FROM PaymentSurvey p WHERE p.paymentSurveyID = :paymentSurveyID"),
    @NamedQuery(name = "PaymentSurvey.findByMunicipalityID", query = "SELECT p FROM PaymentSurvey p WHERE p.municipalityID = :municipalityID"),
    @NamedQuery(name = "PaymentSurvey.findByAccountNumber", query = "SELECT p FROM PaymentSurvey p WHERE p.accountNumber = :accountNumber"),
    @NamedQuery(name = "PaymentSurvey.findByResponseDate", query = "SELECT p FROM PaymentSurvey p WHERE p.responseDate = :responseDate"),
    @NamedQuery(name = "PaymentSurvey.findByResponse", query = "SELECT p FROM PaymentSurvey p WHERE p.response = :response")})
public class PaymentSurvey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "paymentSurveyID")
    private Integer paymentSurveyID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "municipalityID")
    private int municipalityID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "accountNumber")
    private String accountNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "responseDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date responseDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "response")
    private boolean response;

    public PaymentSurvey() {
    }

    public PaymentSurvey(Integer paymentSurveyID) {
        this.paymentSurveyID = paymentSurveyID;
    }

    public PaymentSurvey(Integer paymentSurveyID, int municipalityID, String accountNumber, Date responseDate, boolean response) {
        this.paymentSurveyID = paymentSurveyID;
        this.municipalityID = municipalityID;
        this.accountNumber = accountNumber;
        this.responseDate = responseDate;
        this.response = response;
    }

    public Integer getPaymentSurveyID() {
        return paymentSurveyID;
    }

    public void setPaymentSurveyID(Integer paymentSurveyID) {
        this.paymentSurveyID = paymentSurveyID;
    }

    public int getMunicipalityID() {
        return municipalityID;
    }

    public void setMunicipalityID(int municipalityID) {
        this.municipalityID = municipalityID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public boolean getResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (paymentSurveyID != null ? paymentSurveyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentSurvey)) {
            return false;
        }
        PaymentSurvey other = (PaymentSurvey) object;
        if ((this.paymentSurveyID == null && other.paymentSurveyID != null) || (this.paymentSurveyID != null && !this.paymentSurveyID.equals(other.paymentSurveyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.PaymentSurvey[ paymentSurveyID=" + paymentSurveyID + " ]";
    }
    
}
