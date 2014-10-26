/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.data;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "beneficiary")
@NamedQueries({
    @NamedQuery(name = "Beneficiary.findByIDNumberInCompany", 
            query = "SELECT b FROM Beneficiary b "
                    + "WHERE b.iDNumber = :IDNumber and b.company.companyID = :companyID"),
    @NamedQuery(name = "Beneficiary.findByCompany", 
            query = "SELECT b FROM Beneficiary b WHERE b.company.companyID = :companyID order by b.lastName, b.firstName"),
    @NamedQuery(name = "Beneficiary.findByFirstName", query = "SELECT b FROM Beneficiary b WHERE b.firstName = :firstName"),
    @NamedQuery(name = "Beneficiary.findByLastName", query = "SELECT b FROM Beneficiary b WHERE b.lastName = :lastName"),
    @NamedQuery(name = "Beneficiary.findByMiddleName", query = "SELECT b FROM Beneficiary b WHERE b.middleName = :middleName"),
    @NamedQuery(name = "Beneficiary.findByIDNumber", 
            query = "SELECT b FROM Beneficiary b "
                    + "WHERE b.iDNumber = :iDNumber"),
    @NamedQuery(name = "Beneficiary.findByEmail", query = "SELECT b FROM Beneficiary b WHERE b.email = :email"),
    @NamedQuery(name = "Beneficiary.findByCellphone", query = "SELECT b FROM Beneficiary b WHERE b.cellphone = :cellphone"),
    @NamedQuery(name = "Beneficiary.findByDateRegistered", query = "SELECT b FROM Beneficiary b WHERE b.dateRegistered = :dateRegistered")})
public class Beneficiary implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "amountAuthorized")
    private double amountAuthorized;
    @Basic(optional = false)
    @NotNull
    @Column(name = "amountPaid")
    private double amountPaid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "phbDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date phbDate;
    @JoinColumn(name = "townshipID", referencedColumnName = "townshipID")
    @ManyToOne(optional = false)
    private Township township;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beneficiary")
    private List<HappyLetter> happyLetterList;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "beneficiaryID")
    private Integer beneficiaryID;
    @Size(max = 45)
    @Column(name = "firstName")
    private String firstName;
    @Size(max = 45)
    @Column(name = "lastName")
    private String lastName;
    @Size(max = 45)
    @Column(name = "middleName")
    private String middleName;
    @Size(max = 45)
    @Column(name = "IDNumber")
    private String iDNumber;
    @Size(max = 150)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "cellphone")
    private String cellphone;
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @JoinColumn(name = "projectSiteID", referencedColumnName = "projectSiteID")
    @ManyToOne
    private ProjectSite projectSite;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne(optional = false)
    private Company company;

    public Beneficiary() {
    }

    public Beneficiary(Integer beneficiaryID) {
        this.beneficiaryID = beneficiaryID;
    }

    public Integer getBeneficiaryID() {
        return beneficiaryID;
    }

    public void setBeneficiaryID(Integer beneficiaryID) {
        this.beneficiaryID = beneficiaryID;
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

    public ProjectSite getProjectSite() {
        return projectSite;
    }

    public void setProjectSite(ProjectSite projectSite) {
        this.projectSite = projectSite;
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
        hash += (beneficiaryID != null ? beneficiaryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Beneficiary)) {
            return false;
        }
        Beneficiary other = (Beneficiary) object;
        if ((this.beneficiaryID == null && other.beneficiaryID != null) || (this.beneficiaryID != null && !this.beneficiaryID.equals(other.beneficiaryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Beneficiary[ beneficiaryID=" + beneficiaryID + " ]";
    }

    public List<HappyLetter> getHappyLetterList() {
        return happyLetterList;
    }

    public void setHappyLetterList(List<HappyLetter> happyLetterList) {
        this.happyLetterList = happyLetterList;
    }

    public Township getTownship() {
        return township;
    }

    public void setTownship(Township township) {
        this.township = township;
    }

    public double getAmountAuthorized() {
        return amountAuthorized;
    }

    public void setAmountAuthorized(double amountAuthorized) {
        this.amountAuthorized = amountAuthorized;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getPhbDate() {
        return phbDate;
    }

    public void setPhbDate(Date phbDate) {
        this.phbDate = phbDate;
    }

    
}
