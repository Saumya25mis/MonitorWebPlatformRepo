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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "happyLetter")
@NamedQueries({
    @NamedQuery(name = "HappyLetter.findAll", query = "SELECT h FROM HappyLetter h"),
    @NamedQuery(name = "HappyLetter.findByHappyLetterID", query = "SELECT h FROM HappyLetter h WHERE h.happyLetterID = :happyLetterID"),
    @NamedQuery(name = "HappyLetter.findByLetterDate", query = "SELECT h FROM HappyLetter h WHERE h.letterDate = :letterDate")})
public class HappyLetter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "happyLetterID")
    private Integer happyLetterID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "letterDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date letterDate;
    @JoinColumn(name = "beneficiaryID", referencedColumnName = "beneficiaryID")
    @ManyToOne(optional = false)
    private Beneficiary beneficiary;

    public HappyLetter() {
    }

    public HappyLetter(Integer happyLetterID) {
        this.happyLetterID = happyLetterID;
    }

    public HappyLetter(Integer happyLetterID, Date letterDate) {
        this.happyLetterID = happyLetterID;
        this.letterDate = letterDate;
    }

    public Integer getHappyLetterID() {
        return happyLetterID;
    }

    public void setHappyLetterID(Integer happyLetterID) {
        this.happyLetterID = happyLetterID;
    }

    public Date getLetterDate() {
        return letterDate;
    }

    public void setLetterDate(Date letterDate) {
        this.letterDate = letterDate;
    }

    public Beneficiary getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Beneficiary beneficiary) {
        this.beneficiary = beneficiary;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (happyLetterID != null ? happyLetterID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HappyLetter)) {
            return false;
        }
        HappyLetter other = (HappyLetter) object;
        if ((this.happyLetterID == null && other.happyLetterID != null) || (this.happyLetterID != null && !this.happyLetterID.equals(other.happyLetterID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.HappyLetter[ happyLetterID=" + happyLetterID + " ]";
    }
    
}
