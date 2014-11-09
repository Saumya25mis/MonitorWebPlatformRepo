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
@Table(name = "bank")
@NamedQueries({
    @NamedQuery(name = "Bank.findByCountry", 
            query = "SELECT b FROM Bank b where b.country.countryID = :countryID order by b.bankName"),
    @NamedQuery(name = "Bank.findByBankID", query = "SELECT b FROM Bank b WHERE b.bankID = :bankID"),
    @NamedQuery(name = "Bank.findByBankName", query = "SELECT b FROM Bank b WHERE b.bankName = :bankName")})
public class Bank implements Serializable {
    @JoinColumn(name = "countryID", referencedColumnName = "countryID")
    @ManyToOne
    private Country country;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bankID")
    private Integer bankID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "bankName")
    private String bankName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bank")
    private List<BankDetail> bankDetailList;

    public Bank() {
    }

    public Bank(Integer bankID) {
        this.bankID = bankID;
    }

    public Bank(Integer bankID, String bankName) {
        this.bankID = bankID;
        this.bankName = bankName;
    }

    public Integer getBankID() {
        return bankID;
    }

    public void setBankID(Integer bankID) {
        this.bankID = bankID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<BankDetail> getBankDetailList() {
        return bankDetailList;
    }

    public void setBankDetailList(List<BankDetail> bankDetailList) {
        this.bankDetailList = bankDetailList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bankID != null ? bankID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bank)) {
            return false;
        }
        Bank other = (Bank) object;
        if ((this.bankID == null && other.bankID != null) || (this.bankID != null && !this.bankID.equals(other.bankID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Bank[ bankID=" + bankID + " ]";
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    
}
