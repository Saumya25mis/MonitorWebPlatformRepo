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
    @NamedQuery(name = "Beneficiary.findByProject", 
            query = "SELECT b FROM Beneficiary b WHERE b.project.projectID = :projectID "
                    + "order by b.lastName, b.firstName")})
public class Beneficiary implements Serializable {
    @Size(max = 255)
    @Column(name = "townshipName")
    private String townshipName;
    @Size(max = 255)
    @Column(name = "status")
    private String status;
     @Size(max = 255)
    @Column(name = "siteNumber")
    private String siteNumber;
    @OneToMany(mappedBy = "beneficiary")
    private List<ProjectSite> projectSiteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "beneficiary")
    private List<HappyLetter> happyLetterList;
    @JoinColumn(name = "projectID", referencedColumnName = "projectID")
    @ManyToOne(optional = false)
    private Project project;
    
    
    @Column(name = "amountAuthorized")
    private Double amountAuthorized;
    
    @Column(name = "amountPaid")
    private Double amountPaid;
   
    @Column(name = "phbDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date phbDate;
    
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSiteNumber() {
        return siteNumber;
    }

    public void setSiteNumber(String siteNumber) {
        this.siteNumber = siteNumber;
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

    public Date getPhbDate() {
        return phbDate;
    }

    public void setPhbDate(Date phbDate) {
        this.phbDate = phbDate;
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

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<ProjectSite> getProjectSiteList() {
        return projectSiteList;
    }

    public void setProjectSiteList(List<ProjectSite> projectSiteList) {
        this.projectSiteList = projectSiteList;
    }

    public List<HappyLetter> getHappyLetterList() {
        return happyLetterList;
    }

    public void setHappyLetterList(List<HappyLetter> happyLetterList) {
        this.happyLetterList = happyLetterList;
    }

    public String getTownshipName() {
        return townshipName;
    }

    public void setTownshipName(String townshipName) {
        this.townshipName = townshipName;
    }


    
}
