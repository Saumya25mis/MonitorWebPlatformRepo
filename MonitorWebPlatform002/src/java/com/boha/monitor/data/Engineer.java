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
import javax.validation.constraints.Size;

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "engineer")
@NamedQueries({
    @NamedQuery(name = "Engineer.findByCompany", 
            query = "SELECT e FROM Engineer e where e.company.companyID = :companyID order by e.engineerName"),
    @NamedQuery(name = "Engineer.findByEngineerID", query = "SELECT e FROM Engineer e WHERE e.engineerID = :engineerID"),
    @NamedQuery(name = "Engineer.findByEngineerName", query = "SELECT e FROM Engineer e WHERE e.engineerName = :engineerName"),
    @NamedQuery(name = "Engineer.findByEmail", query = "SELECT e FROM Engineer e WHERE e.email = :email"),
    @NamedQuery(name = "Engineer.findByCellphone", query = "SELECT e FROM Engineer e WHERE e.cellphone = :cellphone")})
public class Engineer implements Serializable {
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "engineer")
    private List<ProjectEngineer> projectEngineerList;
    @JoinColumn(name = "companyID", referencedColumnName = "companyID")
    @ManyToOne(optional = false)
    private Company company;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "engineerID")
    private Integer engineerID;
    @Size(max = 255)
    @Column(name = "engineerName")
    private String engineerName;
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 50)
    @Column(name = "cellphone")
    private String cellphone;
    

    public Engineer() {
    }

    public Engineer(Integer engineerID) {
        this.engineerID = engineerID;
    }

    public Integer getEngineerID() {
        return engineerID;
    }

    public void setEngineerID(Integer engineerID) {
        this.engineerID = engineerID;
    }

    public String getEngineerName() {
        return engineerName;
    }

    public void setEngineerName(String engineerName) {
        this.engineerName = engineerName;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (engineerID != null ? engineerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Engineer)) {
            return false;
        }
        Engineer other = (Engineer) object;
        if ((this.engineerID == null && other.engineerID != null) || (this.engineerID != null && !this.engineerID.equals(other.engineerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.Engineer[ engineerID=" + engineerID + " ]";
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<ProjectEngineer> getProjectEngineerList() {
        return projectEngineerList;
    }

    public void setProjectEngineerList(List<ProjectEngineer> projectEngineerList) {
        this.projectEngineerList = projectEngineerList;
    }

   
    
}
