/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.util;

import com.boha.monitor.dto.transfer.RequestDTO;
import com.boha.monitor.dto.transfer.ResponseDTO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aubreyM
 */
public class TrafficCop {

    public static ResponseDTO processRequest(RequestDTO req,
            DataUtil dataUtil, SignInUtil signInUtil)
            throws DataException, IOException, Exception {
        long start = System.currentTimeMillis();
        ResponseDTO resp = new ResponseDTO();
        resp.setStatusCode(0);

        switch (req.getRequestType()) {
            case RequestDTO.GET_COMPANY_LIST:
                resp = ListUtil.getCompanyList(dataUtil.getEntityManager());
                break;
            case RequestDTO.IMPORT_TASK_INFO:
                resp = dataUtil.importTaskType(req.getTaskType());
                break;
            case RequestDTO.IMPORT_PROJECT_INFO:
                resp = dataUtil.importProject(req.getProject());
                break;
            case RequestDTO.GET_MESSAGES_BY_PROJECT:
                resp = ListUtil.getMessagesByProject(dataUtil.getEntityManager(), req.getProjectID());
                break;
            case RequestDTO.GET_CHATS_BY_PROJECT_AND_STAFF:
                resp = ListUtil.getMessagesByProjectAndStaff(dataUtil.getEntityManager(), req.getProjectID(), req.getStaffID());
                break;
            case RequestDTO.ADD_CHAT:
                resp = dataUtil.addChat(req.getChat());
                resp.setChatList(ListUtil.getChatsByProject(dataUtil.getEntityManager(), req.getProjectID()).getChatList());
                break;
            case RequestDTO.ADD_CHAT_MEMBERS:
                resp = dataUtil.addChatMembers(req.getChatMemberList());
                break;
            case RequestDTO.SEND_CHAT_MESSAGE:
//                    resp = cloudMsgUtil.sendChatMessage(req.getChatMessage());
                break;
            case RequestDTO.GET_CHATS_BY_PROJECT:
                resp = ListUtil.getChatsByProject(dataUtil.getEntityManager(), req.getProjectID());
                break;

            case RequestDTO.NOTIFY_SUPERVISOR_NO_PROJECTS:
                //TODO - send GCM message to project operations ...
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
                resp = dataUtil.addProjectStatusTypes(req.getProjectStatusTypeList());
                break;
            case RequestDTO.ADD_LOCATION_TRACKERS:
                dataUtil.addLocationTrackers(req.getLocationTrackerList());
                resp.setStatusCode(0);
                resp.setMessage("Tracker data written OK");
                break;
            case RequestDTO.ADD_LOCATION_TRACK:
                dataUtil.addLocationTrack(req.getLocationTracker());
                resp.setStatusCode(0);
                resp.setMessage("Tracker data written OK");
                break;

            //register entities    
            case RequestDTO.REGISTER_COMPANY:
                resp = dataUtil.registerCompany(req.getCompany());
                break;
            case RequestDTO.ADD_PORTFOLOIOS:
                resp = dataUtil.addPortfolios(req.getPortfolioList());
                break;
            case RequestDTO.ADD_PROGRAMMES:
                resp = dataUtil.addProgrammes(req.getProgrammeList());
                break;
            case RequestDTO.ADD_PROJECTS:
                resp = dataUtil.addProjects(req.getProjectList());
                break;
            case RequestDTO.ADD_MONITORS:
                resp = dataUtil.addMonitors(req.getMonitorList());
                break;
            case RequestDTO.ADD_STAFF:
                resp = dataUtil.addStaff(req.getStaffList());
                break;

            case RequestDTO.ADD_PROJECT_STATUS_TYPES:
                resp = dataUtil.addProjectStatusTypes(req.getProjectStatusTypeList());
                break;
            case RequestDTO.ADD_TASK_STATUS_TYPES:
                resp = dataUtil.addTaskStatusTypes(req.getTaskStatusTypeList());
                break;
            //
            case RequestDTO.ADD_DEVICE:
                dataUtil.addDevice(req.getGcmDevice());
                break;
            case RequestDTO.ADD_PHOTO:
                resp = dataUtil.addPhotoUpload(req.getPhotoUpload());
                break;
            case RequestDTO.GET_MONITOR_PHOTOS:
                resp = ListUtil.getMonitorPhotos(dataUtil.getEntityManager(), req.getMonitorID());
                break;

            case RequestDTO.ADD_PROJECT_TASKS:
                resp = dataUtil.addProjectTasksUsingType(req.getProjectID(), req.getTaskTypeID());
                break;
            case RequestDTO.ADD_PROJECT_TASK_STATUS:
                resp = dataUtil.addProjectTaskStatus(req.getProjectTaskStatus());
                break;
            case RequestDTO.ADD_TASKS:
                resp = dataUtil.addTasks(req.getTaskList());
                break;
            case RequestDTO.ADD_SUB_TASKS:
                resp = dataUtil.addSubTasks(req.getSubTaskList());
                break;
            case RequestDTO.ADD_TASK_TYPES:
                resp = dataUtil.addTaskTypes(req.getTaskTypeList());
                break;
            case RequestDTO.ADD_MONITOR_PROJECTS:
                resp = dataUtil.addMonitorProjects(req.getMonitorProjectList());
                break;

            case RequestDTO.ADD_STAFF_PROJECTS:
                resp = dataUtil.addStaffProjects(req.getStaffProjectList());
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
                resp = ListUtil.getLocationTracksByStaff(dataUtil.getEntityManager(), req.getStaffID());
                break;
            case RequestDTO.GET_MONITOR_PROJECTS:
                resp = ListUtil.getMonitorProjects(dataUtil.getEntityManager(), req.getMonitorID());
                break;
            case RequestDTO.GET_ERROR_REPORTS:
                resp = ListUtil.getServerEvents(dataUtil.getEntityManager(), req.getStartDate(), req.getEndDate());
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
                resp = ListUtil.getProjectData(dataUtil.getEntityManager(), req.getProjectID());
                break;
            case RequestDTO.GET_COMPANY_STAFF:
                resp = ListUtil.getCompanyStaffList(dataUtil.getEntityManager(), req.getCompanyID());
                break;
            case RequestDTO.GET_COMPANY_DATA:
                resp = ListUtil.getCompanyData(dataUtil.getEntityManager(), req.getCompanyID());
                break;
            case RequestDTO.GET_PORTFOLIO_LIST:
                resp = ListUtil.getPortfolioList(dataUtil.getEntityManager(), req.getCompanyID());
                break;
            case RequestDTO.GET_TASK_STATUS_LIST:
                resp = ListUtil.getTaskStatusTypeList(dataUtil.getEntityManager(), req.getCompanyID());
                break;
            //photos
            case RequestDTO.GET_PROJECT_IMAGES:
                resp = ListUtil.getPhotosByProject(dataUtil.getEntityManager(), req.getProjectID());
                break;

            case RequestDTO.GET_TASK_IMAGES:
                resp = ListUtil.getPhotosByTask(dataUtil.getEntityManager(), req.getProjectTaskID());
                break;
            case RequestDTO.GET_ALL_PROJECT_IMAGES:
                resp = ListUtil.getAllPhotosByProject(dataUtil.getEntityManager(), req.getProjectID());
                break;

            case RequestDTO.LOGIN_STAFF:
                resp = signInUtil.loginStaff(dataUtil.getEntityManager(), req.getGcmDevice(),
                        req.getEmail(), req.getPin());
                break;
            case RequestDTO.LOGIN_MONITOR:
                resp = signInUtil.loginMonitor(dataUtil.getEntityManager(), req.getGcmDevice(),
                        req.getEmail(), req.getPin());
                break;
            case RequestDTO.SEND_GCM_REGISTRATION:
                resp = GoogleCloudMessagingRegistrar.sendGCMRegistration(req.getGcmRegistrationID());
                break;
            default:
                resp.setStatusCode(ServerStatus.ERROR_UNKNOWN_REQUEST);
                resp.setMessage(ServerStatus.getMessage(resp.getStatusCode()));
                break;
        }

        long end = System.currentTimeMillis();
        double elapsed = Elapsed.getElapsed(start, end);
        resp.setElapsedRequestTimeInSeconds(elapsed);
        logger.log(Level.INFO, "******* request elapsed time: {0} seconds", elapsed);
        return resp;
    }

    static final Logger logger = Logger.getLogger(TrafficCop.class.getSimpleName());
}
