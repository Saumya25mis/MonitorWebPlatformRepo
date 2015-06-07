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
import javax.inject.Inject;

/**
 *
 * @author aubreyM
 */
@Stateless
public class TrafficCop {

    @Inject
    DataUtil dataUtil;

    public ResponseDTO processRequest(RequestDTO req) {
        long start = System.currentTimeMillis();
        ResponseDTO resp = new ResponseDTO();
        resp.setStatusCode(0);
        try {
            switch (req.getRequestType()) {
                case RequestDTO.GET_MESSAGES_BY_PROJECT:
                    resp = ListUtil.getMessagesByProject(dataUtil.getEntityManager(),req.getProjectID());
                    break;
                case RequestDTO.GET_CHATS_BY_PROJECT_AND_STAFF:
                    resp = ListUtil.getMessagesByProjectAndStaff(dataUtil.getEntityManager(),req.getProjectID(), req.getStaffID());
                    break;
                case RequestDTO.ADD_CHAT:
                    resp = dataUtil.addChat(req.getChat());
                    resp.setChatList(ListUtil.getChatsByProject(dataUtil.getEntityManager(),req.getProjectID()).getChatList());
                    break;
                case RequestDTO.ADD_CHAT_MEMBERS:
                    resp = dataUtil.addChatMembers(req.getChatMemberList());
                    break;
                case RequestDTO.SEND_CHAT_MESSAGE:
//                    resp = cloudMsgUtil.sendChatMessage(req.getChatMessage());
                    break;
                case RequestDTO.GET_CHATS_BY_PROJECT:
                    resp = ListUtil.getChatsByProject(dataUtil.getEntityManager(),req.getProjectID());
                    break;

                case RequestDTO.DELETE_PROJECT_IMAGES:
                    resp = dataUtil.deleteProjectPhotos(req.getPhotoUploadList());
                    break;
                case RequestDTO.NOTIFY_SUPERVISOR_NO_PROJECTS:
                    //TODO - send GCM message to project operations
                    resp = GoogleCloudMessageUtil.sendNoProjectsAssignedMessage(dataUtil.getEntityManager(), req.getCompanyID(), req.getMonitorID());
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
                    resp = dataUtil.registerCompany(req.getCompany(),
                            req.getStaff());
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
                    resp = dataUtil.addProjectTaskStatus(req.getProjectTaskStatus());
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
                    resp = ListUtil.getLocationTracksByCompanyLastMonth(dataUtil.getEntityManager(),
                            req.getCompanyID(), req.getStartDate(), req.getEndDate());
                    break;
                case RequestDTO.GET_LOCATION_TRACK_BY_STAFF_IN_PERIOD:
                    resp = ListUtil.getLocationTracksByStaffInPeriod(dataUtil.getEntityManager(),
                            req.getStaffID(), req.getStartDate(), req.getEndDate());
                    break;
                case RequestDTO.GET_LOCATION_TRACK_BY_STAFF:
                    resp = ListUtil.getLocationTracksByStaff(dataUtil.getEntityManager(),req.getStaffID());
                    break;
                case RequestDTO.GET_MONITOR_PROJECTS:
                    resp = ListUtil.getMonitorProjects(dataUtil.getEntityManager(),req.getMonitorID());
                    break;
                case RequestDTO.GET_ERROR_REPORTS:
                    resp = ListUtil.getServerEvents(dataUtil.getEntityManager(),req.getStartDate(), req.getEndDate());
                    break;
                case RequestDTO.GET_EXEC_COMPANY_DATA:
//                    resp = ListUtil.getCompanyExecData(req.getCompanyID(), req.getCountryID());
                    break;
                case RequestDTO.GET_COMPANY_STATUS_IN_PERIOD:
//                    resp = ListUtil.getCompanyTaskStatusinPeriod(req.getCompanyID(), req.getStartDate(), req.getEndDate());
                    break;
                case RequestDTO.GET_PROJECT_STATUS_IN_PERIOD:
//                    resp = ListUtil.getProjectTaskStatusinPeriod(req.getProjectID(), req.getStartDate(), req.getEndDate());
                    break;

                case RequestDTO.GET_PROJECT_DATA:
                    resp = ListUtil.getProjectData(dataUtil.getEntityManager(),req.getProjectID());
                    break;
                case RequestDTO.GET_COMPANY_STAFF:
                    resp = ListUtil.getCompanyStaffList(dataUtil.getEntityManager(),req.getCompanyID());
                    break;
                case RequestDTO.GET_COMPANY_DATA:
                    resp = ListUtil.getCompanyData(dataUtil.getEntityManager(),req.getCompanyID());
                    break;
                case RequestDTO.GET_TASK_STATUS_LIST:
                    resp = ListUtil.getTaskStatusTypeList(dataUtil.getEntityManager(),req.getCompanyID());
                    break;
                //photos
                case RequestDTO.GET_PROJECT_IMAGES:
                    resp = ListUtil.getPhotosByProject(dataUtil.getEntityManager(),req.getProjectID());
                    break;

                case RequestDTO.GET_TASK_IMAGES:
                    resp = ListUtil.getPhotosByTask(dataUtil.getEntityManager(),req.getProjectTaskID());
                    break;
                case RequestDTO.GET_ALL_PROJECT_IMAGES:
                    resp = ListUtil.getAllPhotosByProject(dataUtil.getEntityManager(),req.getProjectID());
                    break;

                case RequestDTO.LOGIN_STAFF:
                    resp = ListUtil.loginStaff(dataUtil.getEntityManager(),req.getGcmDevice(),
                            req.getEmail(), req.getPin());
                    break;
                case RequestDTO.LOGIN_MONITOR:
                    resp = ListUtil.loginMonitor(dataUtil.getEntityManager(),req.getGcmDevice(),
                            req.getEmail(), req.getPin());
                    break;
                case RequestDTO.SEND_GCM_REGISTRATION:
                    resp = GoogleCloudMessagingRegistrar.sendGCMRegistration(dataUtil.getEntityManager(), req.getGcmRegistrationID());
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
            addErrorStore(StatusCode.ERROR_DATABASE, e.getDescription(), TrafficCop.class.getSimpleName());

        } catch (Exception e) {
            resp.setStatusCode(StatusCode.ERROR_SERVER);
            resp.setMessage(ServerStatus.getMessage(resp.getStatusCode()));
            logger.log(Level.SEVERE, resp.getMessage(), e);
            addErrorStore(StatusCode.ERROR_SERVER, resp.getMessage() + " \n" + dataUtil.getErrorString(e), TrafficCop.class.getSimpleName());

        }
        if (resp.getStatusCode() == null) {
            resp.setStatusCode(0);
        }
        long end = System.currentTimeMillis();
        double elapsed = Elapsed.getElapsed(start, end);
        resp.setElapsedRequestTimeInSeconds(elapsed);
        logger.log(Level.INFO, "******* request elapsed time: {0} seconds", elapsed);
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
            dataUtil.getEntityManager().persist(t);
            logger.log(Level.INFO, "####### ErrorStore row added, origin {0} \nmessage: {1}",
                    new Object[]{origin, message});
        } catch (Exception e) {
            logger.log(Level.SEVERE, "####### Failed to add errorStore from " + origin + "\n" + message, e);
        }
    }

    static final Logger logger = Logger.getLogger(TrafficCop.class.getSimpleName());
}
