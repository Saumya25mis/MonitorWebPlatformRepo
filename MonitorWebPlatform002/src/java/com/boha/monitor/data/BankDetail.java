/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "bankDetail")
@NamedQueries({
    @NamedQuery(name = "BankDetail.findAll", query = "SELECT b FROM BankDetail b"),
    @NamedQuery(name = "BankDetail.findByBankDetailID", query = "SELECT b FROM BankDetail b WHERE b.bankDetailID = :bankDetailID"),
    @NamedQuery(name = "BankDetail.findByAccountNumber", query = "SELECT b FROM BankDetail b WHERE b.accountNumber = :accountNumber"),
    @NamedQuery(name = "BankDetail.findByBranchCode", query = "SELECT b FROM BankDetail b WHERE b.branchCode = :branchCode")})
public class BankDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bankDetailID")
    private Integer bankDetailID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "accountNumber")
    private String accountNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "branchCode")
    private String branchCode;
    @JoinColumn(name = "bankID", referencedColumnName = "bankID")
    @ManyToOne(optional = false)
    private Bank bank;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne(optional = false)
    private Company company;

    public BankDetail() {
    }

    public BankDetail(Integer bankDetailID) {
        this.bankDetailID = bankDetailID;
    }

    public BankDetail(Integer bankDetailID, String accountNumber, String branchCode) {
        this.bankDetailID = bankDetailID;
        this.accountNumber = accountNumber;
        this.branchCode = branchCode;
    }

    public Integer getBankDetailID() {
        return bankDetailID;
    }

    public void setBankDetailID(Integer bankDetailID) {
        this.bankDetailID = bankDetailID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
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
        hash += (bankDetailID != null ? bankDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BankDetail)) {
            return false;
        }
        BankDetail other = (BankDetail) object;
        if ((this.bankDetailID == null && other.bankDetailID != null) || (this.bankDetailID != null && !this.bankDetailID.equals(other.bankDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.BankDetail[ bankDetailID=" + bankDetailID + " ]";
    }
    
}
