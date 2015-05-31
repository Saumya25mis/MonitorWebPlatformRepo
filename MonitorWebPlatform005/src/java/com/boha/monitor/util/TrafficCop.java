/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.util;

import com.boha.monitor.data.ErrorStore;
import com.boha.monitor.dto.transfer.RequestDTO;
import com.boha.monitor.dto.transfer.ResponseDTO;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aubreyM
 */
@Stateless
public class TrafficCop {

    @Inject
    MailUtil mailUtil;
    @Inject
    CloudMsgUtil cloudMsgUtil;

    public ResponseDTO processRequest(RequestDTO req,
            DataUtil dataUtil, ListUtil listUtil) {
        long start = System.currentTimeMillis();
        ResponseDTO resp = new ResponseDTO();
        resp.setStatusCode(0);
        try {
            switch (req.getRequestType()) {
                case RequestDTO.GET_MESSAGES_BY_PROJECT:
                    resp = listUtil.getMessagesByProject(req.getProjectID());
                    break;
                case RequestDTO.GET_CHATS_BY_PROJECT_AND_STAFF:
                    resp = listUtil.getMessagesByProjectAndStaff(req.getProjectID(), req.getStaffID());
                    break;
                case RequestDTO.ADD_CHAT:
                    resp = dataUtil.addChat(req.getChat());
                    resp.setChatList(listUtil.getChatsByProject(req.getProjectID()).getChatList());
                    break;
                case RequestDTO.ADD_CHAT_MEMBERS:
                    resp = dataUtil.addChatMembers(req.getChatMemberList());
                    break;
                case RequestDTO.SEND_CHAT_MESSAGE:
                    resp = cloudMsgUtil.sendChatMessage(req.getChatMessage());
                    break;
                case RequestDTO.GET_CHATS_BY_PROJECT:
                    resp = listUtil.getChatsByProject(req.getProjectID());
                    break;

                case RequestDTO.DELETE_PROJECT_IMAGES:
                    resp = dataUtil.deleteProjectPhotos(req.getPhotoUploadList());
                    break;

                //updates
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

                case RequestDTO.ADD_COMPANY_PROJECT_STATUS_TYPE:
                    resp = dataUtil.addCompanyProjectStatus(req.getProjectStatusType());
                    break;
                case RequestDTO.ADD_COMPANY_TASK:
                    resp = dataUtil.addCompanyTask(req.getTask());
                    break;

                case RequestDTO.ADD_LOCATION_TRACKERS:
                    dataUtil.addLocationTrackers(req.getLocationTrackerList());
                    resp.setStatusCode(0);
                    resp.setMessage("Tracker data written OK");
                    break;

                //register entities    
                case RequestDTO.REGISTER_COMPANY:

                    if (listUtil == null) {
                        System.out.println("listUtil is NULL");
                    }
                    if (dataUtil == null) {
                        System.out.println("dataUtil is NULL");
                    }
                    resp = dataUtil.registerCompany(req.getCompany(),
                            req.getStaff(),
                            listUtil);
                    break;
                case RequestDTO.REGISTER_COMPANY_STAFF:
                    resp = dataUtil.registerCompanyStaff(req.getStaff());
                    break;

                case RequestDTO.REGISTER_PROJECT:
                    resp = dataUtil.registerProject(req.getProject());
                    break;

                //
                case RequestDTO.ADD_DEVICE:
                    dataUtil.addDevice(req.getGcmDevice());
                    break;

                case RequestDTO.ADD_PROJECT_TASK:
                    resp = dataUtil.addProjectTask(req.getProjectTask());
                    break;
                case RequestDTO.ADD_PROJECT_TASK_STATUS:
                    resp = dataUtil.addProjectTaskStatus(req.getProjectSiteTaskStatus());
                    break;
                case RequestDTO.ADD_TASK_STATUS_TYPE:
                    resp = dataUtil.addTaskStatusType(req.getTaskStatus());
                    break;
                case RequestDTO.CONFIRM_LOCATION:
                    dataUtil.confirmLocation(req.getProjectID(), req.getLatitude(), req.getLongitude(), req.getAccuracy());
                    break;

                case RequestDTO.UPDATE_COMPANY_STAFF:
                    resp = dataUtil.updateStaff(req.getStaff());
                    break;
                case RequestDTO.RESET_STAFF_PIN:
                    resp = dataUtil.setNewPin(req.getStaffID());
                    break;

                //lists
                case RequestDTO.GET_LOCATION_TRACK_BY_COMPANY_IN_PERIOD:
                    resp = listUtil.getLocationTracksByCompanyLastMonth(
                            req.getCompanyID(), req.getStartDate(), req.getEndDate());
                    break;
                case RequestDTO.GET_LOCATION_TRACK_BY_STAFF_IN_PERIOD:
                    resp = listUtil.getLocationTracksByStaffInPeriod(
                            req.getStaffID(), req.getStartDate(), req.getEndDate());
                    break;
                case RequestDTO.GET_LOCATION_TRACK_BY_STAFF:
                    resp = listUtil.getLocationTracksByStaff(req.getStaffID());
                    break;
                case RequestDTO.GET_MONITOR_PROJECTS:
                    resp = listUtil.getMonitorProjects(req.getMonitorID());
                    break;
                case RequestDTO.GET_ERROR_REPORTS:
                    resp = listUtil.getServerEvents(req.getStartDate(), req.getEndDate());
                    break;
                case RequestDTO.GET_EXEC_COMPANY_DATA:
//                    resp = listUtil.getCompanyExecData(req.getCompanyID(), req.getCountryID());
                    break;
                case RequestDTO.GET_COMPANY_STATUS_IN_PERIOD:
//                    resp = listUtil.getCompanyTaskStatusinPeriod(req.getCompanyID(), req.getStartDate(), req.getEndDate());
                    break;
                case RequestDTO.GET_PROJECT_STATUS_IN_PERIOD:
//                    resp = listUtil.getProjectTaskStatusinPeriod(req.getProjectID(), req.getStartDate(), req.getEndDate());
                    break;

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
                    resp = listUtil.getTaskStatusTypeList(req.getCompanyID());
                    break;
                //photos
                case RequestDTO.GET_PROJECT_IMAGES:
                    resp = listUtil.getPhotosByProject(req.getProjectID());
                    break;

                case RequestDTO.GET_TASK_IMAGES:
                    resp = listUtil.getPhotosByTask(req.getProjectTaskID());
                    break;
                case RequestDTO.GET_ALL_PROJECT_IMAGES:
                    resp = listUtil.getAllPhotosByProject(req.getProjectID());
                    break;

                case RequestDTO.LOGIN_STAFF:
                    resp = listUtil.loginStaff(req.getGcmDevice(),
                            req.getEmail(), req.getPin(),
                            listUtil);
                    break;
                case RequestDTO.LOGIN_MONITOR:
                    resp = listUtil.loginMonitor(req.getGcmDevice(),
                            req.getEmail(), req.getPin(),
                            listUtil);
                    break;
                case RequestDTO.SEND_GCM_REGISTRATION:
                    resp = CloudMessagingRegistrar.sendRegistration(req.getGcmRegistrationID(), dataUtil);
                    break;
                default:
                    resp.setStatusCode(ServerStatus.ERROR_UNKNOWN_REQUEST);
                    resp.setMessage(ServerStatus.getMessage(resp.getStatusCode()));
                    break;
            }
        } catch (DataException e) {
            resp.setStatusCode(ServerStatus.ERROR_DATABASE);
            resp.setMessage(ServerStatus.getMessage(resp.getStatusCode()));
            logger.log(Level.SEVERE, resp.getMessage(), e);
            addErrorStore(19, e.getDescription(), TrafficCop.class.getSimpleName());
            try {
                mailUtil.sendAdministratorLogs();
            } catch (MessagingException | UnsupportedEncodingException ex) {
                Logger.getLogger(TrafficCop.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (Exception e) {
            resp.setStatusCode(ServerStatus.ERROR_SERVER);
            resp.setMessage(ServerStatus.getMessage(resp.getStatusCode()));
            logger.log(Level.SEVERE, resp.getMessage(), e);
            addErrorStore(19, resp.getMessage() + " \n" + dataUtil.getErrorString(e), TrafficCop.class.getSimpleName());
            try {
                mailUtil.sendAdministratorLogs();
            } catch (MessagingException | UnsupportedEncodingException ex) {
                Logger.getLogger(TrafficCop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (resp.getStatusCode() == null) {
            resp.setStatusCode(0);
        }
        long end = System.currentTimeMillis();
        double elapsed = Elapsed.getElapsed(start, end);
        resp.setElapsedRequestTimeInSeconds(elapsed);
        logger.log(Level.WARNING, "*********** request elapsed time: {0} seconds", elapsed);
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
