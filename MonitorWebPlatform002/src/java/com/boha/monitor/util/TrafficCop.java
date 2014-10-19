/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.util;

import com.boha.monitor.data.ErrorStore;
import com.boha.monitor.dto.transfer.RequestDTO;
import com.boha.monitor.dto.transfer.ResponseDTO;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aubreyM
 */
@Stateless
public class TrafficCop {

    public ResponseDTO processRequest(RequestDTO req,
            DataUtil dataUtil, ListUtil listUtil) {
        ResponseDTO resp = new ResponseDTO();
        try {
            switch (req.getRequestType()) {
                //updates
                case RequestDTO.UPDATE_PROJECT_SITE:
                    dataUtil.updateProjectSite(req.getProjectSite());
                    break;
                case RequestDTO.UPDATE_PROJECT:
                    dataUtil.updateProject(req.getProject());
                    break;
                case RequestDTO.UPDATE_COMPANY_TASK:
                    dataUtil.updateTask(req.getTask());
                    break;
                case RequestDTO.UPDATE_COMPANY_TASK_STATUS:
                    dataUtil.updateTaskStatus(req.getTaskStatus());
                    break;
                //add lookups
                case RequestDTO.ADD_CITY:
                    resp = dataUtil.addCity(req.getCity());
                    break;
                case RequestDTO.ADD_COMPANY_CHECKPOINT:
                    resp = dataUtil.addCompanyCheckPoint(req.getCheckPoint());
                    break;
                case RequestDTO.ADD_COMPANY_PROJECT_STATUS_TYPE:
                    resp = dataUtil.addCompanyProjectStatus(req.getProjectStatusType());
                    break;
                case RequestDTO.ADD_COMPANY_TASK:
                    resp = dataUtil.addCompanyTask(req.getTask());
                    break;
                case RequestDTO.ADD_COMPANY_TASK_STATUS:
                    resp = dataUtil.addCompanyTaskStatus(req.getTaskStatus());
                    break;
                case RequestDTO.ADD_TOWNSHIP:
                    resp = dataUtil.addTownship(req.getTownship());
                    break;

                //register entities    
                case RequestDTO.REGISTER_COMPANY:
                    resp = dataUtil.registerCompany(req.getCompany(), req.getCompanyStaff(), listUtil);
                    break;
                case RequestDTO.REGISTER_COMPANY_STAFF:
                    resp = dataUtil.registerCompanyStaff(req.getCompanyStaff());
                    break;
                case RequestDTO.REGISTER_CLIENT:
                    resp = dataUtil.registerClient(req.getClient());
                    break;
                case RequestDTO.REGISTER_BENEFICIARY:
                    resp = dataUtil.registerBeneficiary(req.getBeneficiary());
                    break;
                case RequestDTO.REGISTER_PROJECT:
                    resp = dataUtil.registerProject(req.getProject());
                    break;
                case RequestDTO.REGISTER_PROJECT_SITE:
                    resp = dataUtil.registerProjectSite(req.getProjectSite());
                    break;
                case RequestDTO.REGISTER_PROJECT_SITE_STAFF:
                    resp = dataUtil.registerProjectSiteStaff(req.getProjectSiteStaff());
                    break;
                //
                case RequestDTO.ADD_SITE_TASK:
                    resp = dataUtil.addProjectSiteTask(req.getProjectSiteTask());
                    break;
                case RequestDTO.ADD_DEVICE:
                    dataUtil.addDevice(req.getGcmDevice());
                    break;
                case RequestDTO.ADD_PROJECT_DIARY_RECORD:
                    resp = dataUtil.addProjectDiaryRecord(req.getProjectDiaryRecord());
                    break;
                case RequestDTO.ADD_PROJECT_SITE_TASK:
                    resp = dataUtil.addProjectSiteTask(req.getProjectSiteTask());
                    break;
                case RequestDTO.ADD_PROJECT_SITE_TASK_STATUS:
                    resp = dataUtil.addProjectSiteTaskStatus(req.getProjectSiteTaskStatus());
                    break;
                case RequestDTO.ADD_PROJECT_STATUS_TYPE:
                    break;
                //lists
                case RequestDTO.GET_PROJECT_DATA:
                    resp = listUtil.getProjectData(req.getProjectID());
                    break;
                case RequestDTO.GET_COMPANY_STAFF:
                    resp = listUtil.getCompanyStaffList(req.getCompanyID());
                    break;
                case RequestDTO.GET_COMPANY_DATA:
                    resp = listUtil.getCompanyData(req.getCompanyID());
                    break;
                case RequestDTO.GET_TASK_STATUS_LIST:
                    resp = listUtil.getTaskStatusList(req.getCompanyID());
                    break;
                case RequestDTO.LOGIN:
                    resp = dataUtil.login(req.getGcmDevice(),
                            req.getEmail(), req.getPin(),
                            listUtil);
                    break;
            }
        } catch (DataException e) {
            resp.setStatusCode(101);
            resp.setMessage("Data service failed to process your request");
            logger.log(Level.SEVERE, "Database related failure", e);
            addErrorStore(19, e.getDescription(), "TrafficCop");
        }
        if (resp.getStatusCode() == null) {
            resp.setStatusCode(0);
        }
        return resp;
    }
    public void addErrorStore(int statusCode, String message, String origin) {
        logger.log(Level.OFF, "------ adding errorStore, message: {0} origin: {1}", new Object[]{message, origin});
        try {
            ErrorStore t = new ErrorStore();
            t.setDateOccured(new Date());
            t.setMessage(message);
            t.setStatusCode(statusCode);
            t.setOrigin(origin);
            em.persist(t);
            logger.log(Level.INFO, "####### ErrorStore row added, origin {0} \nmessage: {1}",
                    new Object[]{origin, message});
        } catch (Exception e) {
            logger.log(Level.SEVERE, "####### Failed to add errorStore from " + origin + "\n" + message, e);
        }
    }
    @PersistenceContext
    EntityManager em;
    static final Logger logger = Logger.getLogger(TrafficCop.class.getSimpleName());
}
