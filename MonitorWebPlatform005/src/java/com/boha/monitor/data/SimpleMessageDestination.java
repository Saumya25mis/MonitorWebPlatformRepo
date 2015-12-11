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

/**
 *
 * @author aubreyM
 */
@Entity
@Table(name = "simpleMessageDestination")
@NamedQueries({
    @NamedQuery(name = "SimpleMessageDestination.findAll", query = "SELECT s FROM SimpleMessageDestination s"),
    @NamedQuery(name = "SimpleMessageDestination.findBySimpleMessageDestinationID", query = "SELECT s FROM SimpleMessageDestination s WHERE s.simpleMessageDestinationID = :simpleMessageDestinationID")})
public class SimpleMessageDestination implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "simpleMessageDestinationID")
    private Integer simpleMessageDestinationID;
    @JoinColumn(name = "monitorID", referencedColumnName = "monitorID")
    @ManyToOne
    private Monitor monitor;
    @JoinColumn(name = "simpleMessageID", referencedColumnName = "simpleMessageID")
    @ManyToOne
    private SimpleMessage simpleMessage;
    @JoinColumn(name = "staffID", referencedColumnName = "staffID")
    @ManyToOne
    private Staff staff;

    public SimpleMessageDestination() {
    }

    public SimpleMessageDestination(Integer simpleMessageDestinationID) {
        this.simpleMessageDestinationID = simpleMessageDestinationID;
    }

    public Integer getSimpleMessageDestinationID() {
        return simpleMessageDestinationID;
    }

    public void setSimpleMessageDestinationID(Integer simpleMessageDestinationID) {
        this.simpleMessageDestinationID = simpleMessageDestinationID;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    public SimpleMessage getSimpleMessage() {
        return simpleMessage;
    }

    public void setSimpleMessage(SimpleMessage simpleMessage) {
        this.simpleMessage = simpleMessage;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (simpleMessageDestinationID != null ? simpleMessageDestinationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SimpleMessageDestination)) {
            return false;
        }
        SimpleMessageDestination other = (SimpleMessageDestination) object;
        if ((this.simpleMessageDestinationID == null && other.simpleMessageDestinationID != null) || (this.simpleMessageDestinationID != null && !this.simpleMessageDestinationID.equals(other.simpleMessageDestinationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.SimpleMessageDestination[ simpleMessageDestinationID=" + simpleMessageDestinationID + " ]";
    }
    
}
