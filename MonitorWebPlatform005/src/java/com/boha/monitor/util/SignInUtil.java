/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.util;

import com.boha.monitor.data.Company;
import com.boha.monitor.data.GcmDevice;
import com.boha.monitor.data.Monitor;
import com.boha.monitor.data.Portfolio;
import com.boha.monitor.data.Staff;
import com.boha.monitor.data.StaffProject;
import com.boha.monitor.dto.CompanyDTO;
import com.boha.monitor.dto.GcmDeviceDTO;
import com.boha.monitor.dto.MonitorDTO;
import com.boha.monitor.dto.PortfolioDTO;
import com.boha.monitor.dto.ProjectDTO;
import com.boha.monitor.dto.StaffDTO;
import com.boha.monitor.dto.transfer.ResponseDTO;
import static com.boha.monitor.util.ListUtil.log;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author aubreyM
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SignInUtil {

    @PersistenceContext
    EntityManager em;

    public  ResponseDTO loginStaff(EntityManager em, GcmDeviceDTO device, String email,
            String pin) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        resp.setStaffList(new ArrayList<>());
        Query q = null;
        try {
            q = em.createNamedQuery("Staff.login", Staff.class);
            q.setParameter("email", email);
            q.setParameter("pin", pin);
            q.setMaxResults(1);
            Staff cs = (Staff) q.getSingleResult();
            Company company = cs.getCompany();
            resp.getStaffList().add(new StaffDTO(cs));
            resp.setCompany(new CompanyDTO(company));

            if (device != null) {
                device.setCompanyID(company.getCompanyID());
                device.setStaffID(cs.getStaffID());
                addDevice(device);
            }

            q = em.createNamedQuery("StaffProject.findByStaff", StaffProject.class);
            q.setParameter("staffID", cs.getStaffID());
            List<StaffProject> sList = q.getResultList();
            resp.setProjectList(new ArrayList<>());
            for (StaffProject x : sList) {
                resp.getProjectList().add(new ProjectDTO(x.getProject()));
            }
            resp.setMonitorList(getCompanyMonitors(company.getCompanyID()));
            resp.setPortfolioList(getPortfolioList(em, company.getCompanyID()).getPortfolioList());
            

        } catch (NoResultException e) {
            log.log(Level.WARNING, "Invalid login attempt: " + email + " pin: " + pin, e);
            resp.setStatusCode(ServerStatus.ERROR_LOGGING_IN);
            resp.setMessage(ServerStatus.getMessage(resp.getStatusCode()));
        }
        return resp;
    }

    public  ResponseDTO loginMonitor(EntityManager em, GcmDeviceDTO device, String email,
            String pin) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        resp.setMonitorList(new ArrayList<>());
        Query q = null;
        try {
            q = em.createNamedQuery("Monitor.login", Monitor.class);
            q.setParameter("email", email);
            q.setParameter("pin", pin);
            q.setMaxResults(1);
            Monitor cs = (Monitor) q.getSingleResult();
            Company company = cs.getCompany();
            resp.getMonitorList().add(new MonitorDTO(cs));
            resp.setCompany(new CompanyDTO(company));

            if (device != null) {
                device.setCompanyID(company.getCompanyID());
                device.setMonitorID(cs.getMonitorID());
                addDevice(device);
            }

            ListUtil.getProjectDataForMonitor(em, resp, cs.getMonitorID());
            resp.setTaskStatusTypeList(ListUtil.getTaskStatusTypeList(em, company.getCompanyID()).getTaskStatusTypeList());
            List<MonitorDTO> mList = getCompanyMonitors(company.getCompanyID());
            for (MonitorDTO monitorDTO : mList) {
                
            }
            resp.setStaffList(getCompanyStaff(company.getCompanyID()));

        } catch (NoResultException e) {
            log.log(Level.WARNING, "Invalid monitor login attempt: " + email + " pin: " + pin, e);
            resp.setStatusCode(ServerStatus.ERROR_LOGGING_IN);
            resp.setMessage(ServerStatus.getMessage(resp.getStatusCode()));
        }
        return resp;
    }
    public static ResponseDTO getPortfolioList(EntityManager em, Integer companyID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Portfolio.findByCompany", Portfolio.class);
        q.setParameter("companyID", companyID);
        List<Portfolio> tList = q.getResultList();
        resp.setPortfolioList(new ArrayList<>());
        for (Portfolio p : tList) {
            resp.getPortfolioList().add(new PortfolioDTO(p));
        }
        return resp;
    }
    private List<MonitorDTO> getCompanyMonitors(Integer companyID) {
        List<MonitorDTO> list = new ArrayList<>();
        Query q = em.createNamedQuery("Monitor.findByCompany", Monitor.class);
        q.setParameter("companyID", companyID);
        List<Monitor> mList = q.getResultList();
        for (Monitor monitor : mList) {
            list.add(new MonitorDTO(monitor));
        }
        return list;
    }
    public List<StaffDTO> getCompanyStaff(Integer companyID) {
        List<StaffDTO> list = new ArrayList<>();
        Query q = em.createNamedQuery("Staff.findByCompany", Staff.class);
        q.setParameter("companyID", companyID);
        List<Staff> mList = q.getResultList();
        for (Staff st : mList) {
            list.add(new StaffDTO(st));
        }
        return list;
    }
 public  void addDevice(GcmDeviceDTO d) throws DataException {
        try {
            GcmDevice g = new GcmDevice();
            g.setCompany(em.find(Company.class, d.getCompanyID()));
            if (d.getStaffID() != null) {
                g.setStaff(em.find(Staff.class, d.getStaffID()));
            }
            if (d.getMonitorID() != null) {
                g.setMonitor(em.find(Monitor.class, d.getMonitorID()));
            }

            g.setDateRegistered(new Date());
            g.setManufacturer(d.getManufacturer());
            g.setMessageCount(0);
            g.setModel(d.getModel());
            g.setRegistrationID(d.getRegistrationID());
            g.setSerialNumber(d.getSerialNumber());
            g.setProduct(d.getProduct());
            g.setAndroidVersion(d.getAndroidVersion());

            em.persist(g);
            log.log(Level.WARNING, "New device loaded");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to add device\n" + ListUtil.getErrorString(e));

        }
    }

}
