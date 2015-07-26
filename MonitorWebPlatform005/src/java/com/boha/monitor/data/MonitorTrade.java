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
@Table(name = "monitorTrade")
@NamedQueries({
    @NamedQuery(name = "MonitorTrade.findByMonitorl", 
            query = "SELECT m FROM MonitorTrade m where m.monitor.monitorID = :monitorID order by m.dateRegistered")
})
public class MonitorTrade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "monitorTradeID")
    private Integer monitorTradeID;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateRegistered")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;
    @JoinColumn(name = "taskTypeID", referencedColumnName = "taskTypeID")
    @ManyToOne(optional = false)
    private TaskType taskType;
    @JoinColumn(name = "monitorID", referencedColumnName = "monitorID")
    @ManyToOne(optional = false)
    private Monitor monitor;

    public MonitorTrade() {
    }

    public MonitorTrade(Integer monitorTradeID) {
        this.monitorTradeID = monitorTradeID;
    }

    public MonitorTrade(Integer monitorTradeID,  Date dateRegistered) {
        this.monitorTradeID = monitorTradeID;
        this.dateRegistered = dateRegistered;
    }

    public Integer getMonitorTradeID() {
        return monitorTradeID;
    }

    public void setMonitorTradeID(Integer monitorTradeID) {
        this.monitorTradeID = monitorTradeID;
    }

  
    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    public Monitor getMonitor() {
        return monitor;
    }

    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (monitorTradeID != null ? monitorTradeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MonitorTrade)) {
            return false;
        }
        MonitorTrade other = (MonitorTrade) object;
        if ((this.monitorTradeID == null && other.monitorTradeID != null) || (this.monitorTradeID != null && !this.monitorTradeID.equals(other.monitorTradeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.boha.monitor.data.MonitorTrade[ monitorTradeID=" + monitorTradeID + " ]";
    }
    
}
