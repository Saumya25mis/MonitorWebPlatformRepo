/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.dto;

import com.boha.monitor.data.*;
import java.io.Serializable;

/**
 *
 * @author aubreyM
 */
public class MonitorTradeDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer monitorTradeID;
    private Long dateRegistered;
    private Integer monitorID;

    public MonitorTradeDTO() {
    }

    public MonitorTradeDTO(MonitorTrade a) {
        this.monitorTradeID = a.getMonitorTradeID();
        if (a.getDateRegistered() != null) {
            this.dateRegistered = a.getDateRegistered().getTime();
        }
        this.monitorID = a.getMonitor().getMonitorID();
    }

    public Integer getMonitorTradeID() {
        return monitorTradeID;
    }

    public void setMonitorTradeID(Integer monitorTradeID) {
        this.monitorTradeID = monitorTradeID;
    }

    public Long getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Long dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

  

    public Integer getMonitorID() {
        return monitorID;
    }

    public void setMonitorID(Integer monitorID) {
        this.monitorID = monitorID;
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
        if (!(object instanceof MonitorTradeDTO)) {
            return false;
        }
        MonitorTradeDTO other = (MonitorTradeDTO) object;
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
