/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.utilx;

import com.boha.monitor.data.*;
import com.boha.monitor.dto.*;
import com.boha.monitor.dto.transfer.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.joda.time.DateTime;

/**
 *
 * @author aubreyM
 */
public class ListUtil {

    public static ResponseDTO getMonitorPhotos(EntityManager em, Integer monitorID) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        resp.setPhotoUploadList(new ArrayList<>());
        try {
            Query q = em.createNamedQuery("PhotoUpload.findByMonitor", PhotoUpload.class);
            q.setParameter("monitorID", monitorID);
            List<PhotoUpload> list = q.getResultList();
            for (PhotoUpload photoUpload : list) {
                resp.getPhotoUploadList().add(new PhotoUploadDTO(photoUpload));
            }

        } catch (Exception e) {

            throw new DataException(null);
        }
        return resp;
    }

    public static ResponseDTO getStaffPhotos(EntityManager em, Integer staffID) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        resp.setPhotoUploadList(new ArrayList<>());
        try {
            Query q = em.createNamedQuery("PhotoUpload.findByStaff", PhotoUpload.class);
            q.setParameter("staffID", staffID);
            List<PhotoUpload> list = q.getResultList();
            for (PhotoUpload photoUpload : list) {
                resp.getPhotoUploadList().add(new PhotoUploadDTO(photoUpload));
            }

        } catch (Exception e) {

            throw new DataException(null);
        }
        return resp;
    }

    public static ResponseDTO getProjectsForMonitorAssignment(EntityManager em, 
            Integer companyID, Integer monitorID) throws DataException {
        ResponseDTO resp = new ResponseDTO();

        try {
            Query q = em.createNamedQuery("Project.findByCompany", Project.class);
            q.setParameter("companyID", companyID);
            List<Project> list = q.getResultList();
            resp.setProjectList(new ArrayList<>());
            for (Project p : list) {
                ProjectDTO d = new ProjectDTO();
                d.setProjectID(p.getProjectID());
                d.setProjectName(p.getProjectName());
                if (p.getCity() != null) {
                    d.setCityName(p.getCity().getCityName());
                    if (p.getCity().getMunicipality() != null) {
                        d.setMunicipalityName(p.getCity().getMunicipality().getMunicipalityName());
                    }
                    d.setLatitude(p.getLatitude());
                    d.setLongitude(p.getLongitude());
                    resp.getProjectList().add(d);
                }                
            }
            q = em.createNamedQuery("MonitorProject.findMonitorProjects", MonitorProject.class);
            q.setParameter("monitorID", monitorID);
            List<MonitorProject> mpList = q.getResultList();           
            resp.setMonitorProjectList(new ArrayList<>());
            for (MonitorProject mp : mpList) {
                resp.getMonitorProjectList().add(new MonitorProjectDTO(mp));
            }
        } catch (Exception e) {
            log.log(Level.OFF, "Failed", e);
            throw new DataException("Falied to get monitor projects");
        }

        return resp;
    }
    public static ResponseDTO getProjectsForStaffAssignment(EntityManager em, 
            Integer companyID, Integer staffID) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Query q = em.createNamedQuery("Project.findByCompany", Project.class);
            q.setParameter("companyID", companyID);
            List<Project> list = q.getResultList();
            resp.setProjectList(new ArrayList<>());
            for (Project p : list) {
                ProjectDTO d = new ProjectDTO();
                d.setProjectID(p.getProjectID());
                d.setProjectName(p.getProjectName());
                if (p.getCity() != null) {
                    d.setCityName(p.getCity().getCityName());
                    if (p.getCity().getMunicipality() != null) {
                        d.setMunicipalityName(p.getCity().getMunicipality().getMunicipalityName());
                    }
                    d.setLatitude(p.getLatitude());
                    d.setLongitude(p.getLongitude());
                    resp.getProjectList().add(d);
                }                
            }
            q = em.createNamedQuery("StaffProject.findStaffProjects", StaffProject.class);
            q.setParameter("staffID", staffID);
            List<StaffProject> mpList = q.getResultList();
            resp.setStaffProjectList(new ArrayList<>());
            for (StaffProject mp : mpList) {
                resp.getStaffProjectList().add(new StaffProjectDTO(mp));
            }
        } catch (Exception e) {
            log.log(Level.OFF, "Failed", e);
            throw new DataException("Falied to get staff projects");
        }

        return resp;
    }

    public static ResponseDTO getMonitorSummary(EntityManager em, Integer monitorID) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        Monitor monitor = em.find(Monitor.class, monitorID);
        Query q = em.createNamedQuery("MonitorProject.findProjectsByMonitor", Project.class);
        q.setParameter("monitorID", monitorID);
        List<Project> projectList = q.getResultList();
        resp.setProjectList(new ArrayList<>());

        MonitorDTO dto = new MonitorDTO(monitor);
        dto.setPhotoUploadList(new ArrayList<>());
        q = em.createNamedQuery("PhotoUpload.findByMonitor", PhotoUpload.class);
        q.setParameter("monitorID", dto.getMonitorID());
        List<PhotoUpload> pList = q.getResultList();

        dto.setPhotoCount(pList.size());
        dto.setProjectCount(projectList.size());

        q = em.createNamedQuery("ProjectTaskStatus.findByMonitor", ProjectTaskStatus.class);
        q.setParameter("monitorID", monitor.getMonitorID());
        List<ProjectTaskStatus> pts = q.getResultList();
        if (pts.size() > 0) {
            dto.setLastStatus(new ProjectTaskStatusDTO(pts.get(0)));
        }
        dto.setStatusCount(pts.size());

        q = em.createNamedQuery("LocationTracker.findByMonitor", LocationTracker.class);
        q.setParameter("monitorID", monitor.getMonitorID());
        q.setMaxResults(3);
        List<LocationTracker> ltList = q.getResultList();
        dto.setLocationTrackerList(new ArrayList<>());
        for (LocationTracker t : ltList) {
            dto.getLocationTrackerList().add(new LocationTrackerDTO(t));
        }

        for (Project p : projectList) {
            ProjectDTO project = new ProjectDTO(p);
            resp.getProjectList().add(project);
        }
        resp.setMonitorList(new ArrayList<>());
        resp.getMonitorList().add(dto);
        return resp;
    }

    /**
     * Get all the data needed for the Monitor app. This includes data about the
     * projects the monitor is assigned to. Also lists all the monitors assigned
     * to the same projects as the requesting monitor.
     *
     * @param em
     * @param resp
     * @param monitorID
     */
    public static ResponseDTO getProjectDataForMonitor(EntityManager em, Integer monitorID)
            throws DataException {
        ResponseDTO resp = new ResponseDTO();
        Monitor mon = em.find(Monitor.class, monitorID);
        if (mon == null) {
            resp.setStatusCode(StatusCode.ERROR_DATABASE);
            resp.setMessage("Monitor not found");
            return resp;
        }

        resp.setProjectList(new ArrayList<>());
        List<Project> projectList = new ArrayList<>();
        for (MonitorProject mp : mon.getMonitorProjectList()) {
            projectList.add(mp.getProject());
        }
//        Collections.sort(resp.getProjectList());
        for (Project p : projectList) {
            ProjectDTO project = new ProjectDTO(p);
            resp.getProjectList().add(project);
            log.log(Level.OFF, "## project found for monitor: {0} - {1} {2}",
                    new Object[]{project.getProjectName(), mon.getFirstName(), mon.getLastName()});
        }

        Query qCompany = em.createNamedQuery("Task.findByCompany", Task.class);
        qCompany.setParameter("companyID", mon.getCompany().getCompanyID());
        List<Task> txList = qCompany.getResultList();
        resp.setTaskList(new ArrayList<>());
        for (Task task : txList) {
            resp.getTaskList().add(new TaskDTO(task));
        }

        Company c = mon.getCompany();
        resp.setTaskStatusTypeList(
                getTaskStatusTypeList(em, c.getCompanyID()).getTaskStatusTypeList());
        resp.setStaffList(
                getCompanyStaffList(em, c.getCompanyID()).getStaffList());
        resp.setMonitorList(
                getCompanyMonitorList(em, c.getCompanyID()).getMonitorList());
        resp.setTaskStatusTypeList(
                getTaskStatusTypeList(em, c.getCompanyID()).getTaskStatusTypeList());
        return resp;
    }

    public static ResponseDTO getProjectDataForStaff(EntityManager em, Integer staffID) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        resp.setTaskList(new ArrayList<>());
        resp.setProjectList(new ArrayList<>());
        Staff staff = em.find(Staff.class, staffID);
        if (staff == null) {
            resp.setStatusCode(StatusCode.ERROR_DATABASE);
            resp.setMessage("Staff not found");
            return resp;
        }

        for (StaffProject p : staff.getStaffProjectList()) {
            ProjectDTO project = new ProjectDTO(p.getProject());
            resp.getProjectList().add(project);
        }
        Collections.sort(resp.getProjectList());
        System.out.println("Staff Projects found: " + resp.getProjectList().size() + " - " + staff.getFirstName());
        Query qCompany = em.createNamedQuery("Task.findByCompany", Task.class);
        qCompany.setParameter("companyID", staff.getCompany().getCompanyID());
        List<Task> txList = qCompany.getResultList();
        for (Task task : txList) {
            resp.getTaskList().add(new TaskDTO(task));
        }
        System.out.println("Tasks for Company: " + resp.getTaskList().size());
        resp.setStaffList(
                getCompanyStaffList(em, staff.getCompany().getCompanyID()).getStaffList());
        resp.setMonitorList(
                getCompanyMonitorList(em, staff.getCompany().getCompanyID()).getMonitorList());
        resp.setTaskStatusTypeList(
                getTaskStatusTypeList(em, staff.getCompany().getCompanyID()).getTaskStatusTypeList());

        return resp;
    }

    public static ResponseDTO getCompanyData(EntityManager em, Integer companyID) throws DataException {
        ResponseDTO resp = new ResponseDTO();

        resp.setStaffList(getCompanyStaffList(em, companyID).getStaffList());
        resp.setTaskStatusTypeList(getTaskStatusTypeList(em, companyID).getTaskStatusTypeList());
        resp.setProjectStatusTypeList(getProjectStatusTypeList(em, companyID).getProjectStatusTypeList());
        resp.setMonitorList(getMonitorList(em, companyID).getMonitorList());
        resp.setPortfolioList(getPortfolioList(em, companyID).getPortfolioList());

        return resp;
    }

    public static ResponseDTO getPortfolioList(EntityManager em, Integer companyID) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Portfolio.findByCompany", Portfolio.class);
        q.setParameter("companyID", companyID);
        List<Portfolio> tList = q.getResultList();
        resp.setPortfolioList(new ArrayList<>());

        for (Portfolio p : tList) {
            PortfolioDTO dto = new PortfolioDTO(p);
            dto.setProgrammeList(getProgrammeList(em, p.getPortfolioID()));
            resp.getPortfolioList().add(dto);

        }

        resp.setMonitorList(getMonitorList(em, companyID).getMonitorList());
        resp.setStaffList(getCompanyStaffList(em, companyID).getStaffList());
        return resp;
    }

    public static ResponseDTO getCompanyList(EntityManager em) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Company.findAll", Company.class);
        List<Company> tList = q.getResultList();
        resp.setCompanyList(new ArrayList<>());
        for (Company p : tList) {
            CompanyDTO company = new CompanyDTO(p);
            ResponseDTO r = getCompanyData(em, p.getCompanyID());
            company.setStaffList(r.getStaffList());
            company.setMonitorList(r.getMonitorList());
            company.setPortfolioList(r.getPortfolioList());
            company.setProjectStatusTypeList(r.getProjectStatusTypeList());
            company.setTaskStatusTypeList(r.getTaskStatusTypeList());

            resp.getCompanyList().add(company);
        }
        return resp;
    }

    public static List<ProgrammeDTO> getProgrammeList(EntityManager em, Integer portfolioID) throws DataException {
        List<ProgrammeDTO> list = new ArrayList<>();

        Query q = em.createNamedQuery("Programme.findByPortfolio", Programme.class);
        q.setParameter("portfolioID", portfolioID);
        List<Programme> tList = q.getResultList();
        for (Programme programme : tList) {
            ProgrammeDTO dto = new ProgrammeDTO(programme);
            dto.setProjectList(getProjectList(em, programme.getProgrammeID()));
            // dto.setTaskTypeList(getProgrammeTaskTypeList(em,
            // dto.getProgrammeID()).getTaskTypeList());
            list.add(dto);
        }

        return list;
    }

    public static ResponseDTO getLocationTracksByDevice(EntityManager em, Integer deviceID) {
        ResponseDTO resp = new ResponseDTO();
        resp.setLocationTrackerList(new ArrayList<>());
        Query q = em.createNamedQuery("LocationTracker.findByDevice", LocationTracker.class);
        q.setParameter("gcmDeviceID", deviceID);
        List<LocationTracker> tList = q.getResultList();
        for (LocationTracker gcm : tList) {
            resp.getLocationTrackerList().add(new LocationTrackerDTO(gcm));
        }
        log.log(Level.OFF, "Device locations found: {0}", resp.getLocationTrackerList().size());
        return resp;
    }

    public static ResponseDTO getDeviceList(EntityManager em, Integer companyID) {
        ResponseDTO resp = new ResponseDTO();
        resp.setGcmDeviceList(new ArrayList<>());
        Query q = em.createNamedQuery("GcmDevice.findCompanyDevices", GcmDeviceDTO.class);
        q.setParameter("companyID", companyID);
        List<GcmDevice> tList = q.getResultList();
        for (GcmDevice gcm : tList) {
            resp.getGcmDeviceList().add(new GcmDeviceDTO(gcm));
        }
        log.log(Level.OFF, "Company devices found: {0}", resp.getGcmDeviceList().size());
        return resp;
    }

    public static List<ProjectDTO> getProjectList(EntityManager em, Integer programmeID) {
        List<ProjectDTO> list = new ArrayList<>();
        Query q = em.createNamedQuery("Project.findByProgramme", Project.class);
        q.setParameter("programmeID", programmeID);
        List<Project> tList = q.getResultList();
        for (Project proj : tList) {
            ProjectDTO dto = new ProjectDTO(proj);
            dto.setProjectTaskList(getProjectTaskList(em, proj.getProjectID()));
            list.add(dto);
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    public static List<ProjectTaskDTO> getProjectTaskList(EntityManager em, Integer projectID) {
        System.out.println("ProjectID = " + projectID);
        List<ProjectTaskDTO> list = new ArrayList<>();

        Project p = em.find(Project.class, projectID);
        for (ProjectTask pt : p.getProjectTaskList()) {
            list.add(new ProjectTaskDTO(pt));
        }

        return list;
    }

    public static ResponseDTO getMonitorList(EntityManager em, Integer companyID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Monitor.findByCompany", Monitor.class);
        q.setParameter("companyID", companyID);
        List<Monitor> tList = q.getResultList();
        resp.setMonitorList(new ArrayList<>());
        for (Monitor mon : tList) {
            resp.getMonitorList().add(new MonitorDTO(mon));
        }
        return resp;
    }

    public static ResponseDTO getMessagesByProjectAndStaff(EntityManager em, Integer projectID,
            Integer companyStaffID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("ChatMessage.findByProjectAndStaff", ChatMessage.class);
        q.setParameter("projectID", projectID);
        q.setParameter("companyStaffID", companyStaffID);
        List<ChatMessage> list = q.getResultList();
        resp.setChatMessageList(new ArrayList<>());
        for (ChatMessage cm : list) {
            resp.getChatMessageList().add(new ChatMessageDTO(cm));
        }
        return resp;
    }

    public static ResponseDTO getMessagesByProject(EntityManager em, Integer projectID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("ChatMessage.findByProject", ChatMessage.class);
        q.setParameter("projectID", projectID);
        List<ChatMessage> list = q.getResultList();
        resp.setChatMessageList(new ArrayList<>());
        for (ChatMessage cm : list) {
            resp.getChatMessageList().add(new ChatMessageDTO(cm));
        }

        return resp;
    }

    public static ResponseDTO getProjectStatusPhotos(EntityManager em, Integer projectID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("PhotoUpload.findByProject", PhotoUpload.class);
        q.setParameter("projectID", projectID);
        List<PhotoUpload> list = q.getResultList();
        resp.setPhotoUploadList(new ArrayList<>());
        for (PhotoUpload p : list) {
            resp.getPhotoUploadList().add(new PhotoUploadDTO(p));
        }

        q = em.createNamedQuery("ProjectTask.findByProject", ProjectTask.class);
        q.setParameter("projectID", projectID);
        List<ProjectTask> listp = q.getResultList();
        resp.setProjectTaskList(new ArrayList<>());

        Query q1 = em.createNamedQuery("ProjectTaskStatus.findByProject", ProjectTaskStatus.class);
        q1.setParameter("projectID", projectID);
        List<ProjectTaskStatus> tsList = q1.getResultList();
        for (ProjectTask p : listp) {
            ProjectTaskDTO pt = new ProjectTaskDTO(p);
            pt.setProjectTaskStatusList(new ArrayList<>());
            for (ProjectTaskStatus tts : tsList) {
                if (Objects.equals(tts.getProjectTask().getProjectTaskID(), pt.getProjectTaskID())) {
                    pt.getProjectTaskStatusList().add(new ProjectTaskStatusDTO(tts));
                }
            }

            resp.getProjectTaskList().add(pt);
        }

        return resp;
    }

    public static ResponseDTO getChatsByProject(EntityManager em, Integer projectID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Chat.findByProject", Chat.class);
        q.setParameter("projectID", projectID);
        List<Chat> list = q.getResultList();
        q = em.createNamedQuery("ChatMessage.findByProject", ChatMessage.class);
        q.setParameter("projectID", projectID);
        List<ChatMessage> cmList = q.getResultList();
        q = em.createNamedQuery("ChatMember.findByProject", ChatMember.class);
        q.setParameter("projectID", projectID);
        List<ChatMember> mmList = q.getResultList();
        resp.setChatList(new ArrayList<>());
        for (Chat t : list) {
            ChatDTO xx = new ChatDTO(t);
            xx.setChatMessageList(new ArrayList<>());
            xx.setChatMemberList(new ArrayList<>());
            for (ChatMessage cm : cmList) {
                if (Objects.equals(cm.getChat().getChatID(), t.getChatID())) {
                    xx.getChatMessageList().add(new ChatMessageDTO(cm));
                }
            }
            for (ChatMember mm : mmList) {
                if (Objects.equals(mm.getChat().getChatID(), t.getChatID())) {
                    xx.getChatMemberList().add(new ChatMemberDTO(mm));
                }
            }
            resp.getChatList().add(xx);
        }

        return resp;
    }

    public static ResponseDTO getLocationTracksByStaff(EntityManager em, Integer companyStaffID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("LocationTracker.findByStaff", LocationTracker.class);
        q.setParameter("companyStaffID", companyStaffID);
        List<LocationTracker> list = q.getResultList();
        resp.setLocationTrackerList(new ArrayList<>());
        for (LocationTracker t : list) {
            resp.getLocationTrackerList().add(new LocationTrackerDTO(t));
        }

        return resp;
    }

    public static ResponseDTO getLocationTracksByMonitorInPeriod(EntityManager em, Integer monitorID, Long df,
            Long dx) {
        Date dateFrom, dateTo;
        if (df == null) {
            DateTime dt = new DateTime();
            DateTime xx = dt.minusDays(1);
            dateFrom = xx.toDate();
            dateTo = dt.toDate();
            log.log(Level.INFO, "Get Location tracks from {0} to {1}",
                    new Object[]{dateFrom.toString(), dateTo.toString()});
        } else {
            dateFrom = new Date(df);
            dateTo = new Date(dx);
        }
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("LocationTracker.findByMonitorInPeriod", LocationTracker.class);
        q.setParameter("monitorID", monitorID);
        q.setParameter("dateFrom", dateFrom);
        q.setParameter("dateTo", dateTo);
        q.setMaxResults(5);
        List<LocationTracker> list = q.getResultList();
        resp.setLocationTrackerList(new ArrayList<>());
        for (LocationTracker t : list) {
            resp.getLocationTrackerList().add(new LocationTrackerDTO(t));
        }
        return resp;
    }

    public static ResponseDTO getLocationTracksByStaffInPeriod(EntityManager em, Integer companyStaffID, Long df,
            Long dx) {
        Date dateFrom, dateTo;
        if (df == null) {
            DateTime dt = new DateTime();
            DateTime xx = dt.minusDays(1);
            dateFrom = xx.toDate();
            dateTo = dt.toDate();
            log.log(Level.INFO, "Get Location tracks from {0} to {1}",
                    new Object[]{dateFrom.toString(), dateTo.toString()});
        } else {
            dateFrom = new Date(df);
            dateTo = new Date(dx);
        }
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("LocationTracker.findByStaffInPeriod", LocationTracker.class);
        q.setParameter("staffID", companyStaffID);
        q.setParameter("dateFrom", dateFrom);
        q.setParameter("dateTo", dateTo);
        List<LocationTracker> list = q.getResultList();
        resp.setLocationTrackerList(new ArrayList<>());
        for (LocationTracker t : list) {
            resp.getLocationTrackerList().add(new LocationTrackerDTO(t));
        }
        return resp;
    }

    public static ResponseDTO getLocationTracksByCompany(EntityManager em, Integer companyID, Long df, Long dx) {

        Date dateFrom, dateTo;
        if (df == null) {
            DateTime dt = new DateTime();
            DateTime xx = dt.minusDays(7);
            dateFrom = xx.toDate();
            dateTo = dt.toDate();
            log.log(Level.INFO, "Get Location tracks from {0} to {1}",
                    new Object[]{dateFrom.toString(), dateTo.toString()});
        } else {
            dateFrom = new Date(df);
            dateTo = new Date(dx);
        }
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("LocationTracker.findByCompanyInPeriod", LocationTracker.class);
        q.setParameter("companyID", companyID);
        q.setParameter("dateFrom", dateFrom);
        q.setParameter("dateTo", dateTo);

        List<LocationTracker> list = q.getResultList();
        resp.setLocationTrackerList(new ArrayList<>());
        list.stream().forEach((t) -> {
            resp.getLocationTrackerList().add(new LocationTrackerDTO(t));
        });

        log.log(Level.INFO, "LocationTrackers found, db: {0} out: {1}",
                new Object[]{list.size(), resp.getLocationTrackerList().size()});
        return resp;
    }

    public static ResponseDTO getPhotosByProject(EntityManager em, Integer projectID, Date start, Date end) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("PhotoUpload.findByProjectInPeriod", PhotoUpload.class);
        q.setParameter("projectID", projectID);
        q.setParameter("start", start);
        q.setParameter("end", end);
        List<PhotoUpload> list = q.getResultList();
        resp.setPhotoUploadList(new ArrayList<>());
        for (PhotoUpload cp : list) {
            resp.getPhotoUploadList().add(new PhotoUploadDTO(cp));
        }

        return resp;
    }

    public static ResponseDTO getAllPhotosByProject(EntityManager em, Integer projectID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("PhotoUpload.findAllProjectPhotos", PhotoUpload.class);
        q.setParameter("projectID", projectID);
        List<PhotoUpload> list = q.getResultList();
        resp.setPhotoUploadList(new ArrayList<>());
        for (PhotoUpload cp : list) {
            resp.getPhotoUploadList().add(new PhotoUploadDTO(cp));
        }
        System.out.println("**** found project photos: " + resp.getPhotoUploadList().size());

        return resp;
    }

    public static ResponseDTO getProjectStatus(EntityManager em, Integer projectID, Date start, Date end) {
        ResponseDTO resp = new ResponseDTO();

        Query q = em.createNamedQuery("ProjectTask.findByProject", ProjectTask.class);
        q.setParameter("projectID", projectID);
        List<ProjectTask> taskList = q.getResultList();
        resp.setProjectTaskList(new ArrayList<>());
        for (ProjectTask projectTask : taskList) {
            ProjectTaskDTO dto = new ProjectTaskDTO(projectTask);
            dto.setProjectTaskStatusList(new ArrayList<>());
            resp.getProjectTaskList().add(dto);
        }

        q = em.createNamedQuery("ProjectTaskStatus.findByProjectInPeriod", ProjectTaskStatus.class);
        q.setParameter("projectID", projectID);
        q.setParameter("start", start);
        q.setParameter("end", end);
        List<ProjectTaskStatus> taskStatusList = q.getResultList();

        System.out.println("ProjectTaskStatus found: " + taskStatusList.size());

        // get photos for every projectTask taken within period
        q = em.createNamedQuery("PhotoUpload.findByTaskInPeriod", PhotoUpload.class);
        q.setParameter("start", start);
        q.setParameter("end", end);
        for (ProjectTaskStatus projectTaskStatus : taskStatusList) {
            ProjectTaskStatusDTO dto = new ProjectTaskStatusDTO(projectTaskStatus);
            for (ProjectTaskDTO projectTask : resp.getProjectTaskList()) {
                if (Objects.equals(projectTask.getProjectTaskID(), dto.getProjectTaskID())) {
                    q.setParameter("projectTaskID", projectTask.getProjectTaskID());
                    List<PhotoUpload> pList = q.getResultList();
                    projectTask.setPhotoUploadList(new ArrayList<>());
                    for (PhotoUpload photoUpload : pList) {
                        projectTask.getPhotoUploadList().add(new PhotoUploadDTO(photoUpload));
                    }

                    projectTask.getProjectTaskStatusList().add(dto);
                }
            }
        }

        // get photos for the project - not task related, could be event photos
        q = em.createNamedQuery("PhotoUpload.findByProjectInPeriod", PhotoUpload.class);
        q.setParameter("projectID", projectID);
        q.setParameter("start", start);
        q.setParameter("end", end);
        List<PhotoUpload> pList = q.getResultList();
        System.out.println("photos found: " + pList.size());
        resp.setPhotoUploadList(new ArrayList<>());

        for (PhotoUpload photoUpload : pList) {
            resp.getPhotoUploadList().add(new PhotoUploadDTO(photoUpload));
        }

        resp.setStatusCount(taskStatusList.size());
        if (!taskStatusList.isEmpty()) {
            ProjectTaskStatusDTO dto = new ProjectTaskStatusDTO(taskStatusList.get(0));
            resp.setLastStatus(dto);
        }

        System.out.println("################# Hooray, project status done!");
        return resp;
    }

    private String getRandomPin() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random(System.currentTimeMillis());
        int x = rand.nextInt(9);
        if (x == 0) {
            x = 3;
        }
        sb.append(x);
        sb.append(rand.nextInt(9));
        sb.append(rand.nextInt(9));
        sb.append(rand.nextInt(9));
        sb.append(rand.nextInt(9));
        sb.append(rand.nextInt(9));
        return sb.toString();
    }

    public static ResponseDTO getPhotosByTask(EntityManager em, Integer projectTaskID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("PhotoUpload.findByTask", PhotoUpload.class);
        q.setParameter("projectTaskID", projectTaskID);
        List<PhotoUpload> list = q.getResultList();
        resp.setPhotoUploadList(new ArrayList<>());
        for (PhotoUpload cp : list) {
            resp.getPhotoUploadList().add(new PhotoUploadDTO(cp));
        }

        return resp;
    }

    public static ResponseDTO getCompanyStaffList(EntityManager em, Integer companyID) throws DataException {
        ResponseDTO resp = new ResponseDTO();

        try {
            Query q = em.createNamedQuery("Staff.findByCompany", Staff.class);
            q.setParameter("companyID", companyID);
            List<Staff> sList = q.getResultList();
            resp.setStaffList(new ArrayList<>());
            for (Staff cs : sList) {
                StaffDTO dto = new StaffDTO(cs);
                dto.setPhotoUploadList(new ArrayList<>());
                q = em.createNamedQuery("PhotoUpload.findByStaff", PhotoUpload.class);
                q.setParameter("staffID", cs.getStaffID());
                List<PhotoUpload> pList = q.getResultList();
                for (PhotoUpload photoUpload : pList) {
                    dto.getPhotoUploadList().add(new PhotoUploadDTO(photoUpload));
                }
                resp.getStaffList().add(dto);
            }
            log.log(Level.INFO, "company staff found: {0}", resp.getStaffList().size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project data\n" + getErrorString(e));
        }

        return resp;
    }

    public static ResponseDTO getCompanyMonitorList(EntityManager em, Integer companyID) throws DataException {
        ResponseDTO resp = new ResponseDTO();

        try {
            Query q = em.createNamedQuery("Monitor.findByCompany", Monitor.class);
            q.setParameter("companyID", companyID);
            List<Monitor> sList = q.getResultList();
            resp.setMonitorList(new ArrayList<>());
            for (Monitor monitor : sList) {
                MonitorDTO dto = new MonitorDTO(monitor);
                dto.setPhotoUploadList(new ArrayList<>());

                q = em.createNamedQuery("PhotoUpload.findByMonitor", PhotoUpload.class);

                dto.setPhotoUploadList(new ArrayList<>());
                q.setParameter("monitorID", dto.getMonitorID());
                List<PhotoUpload> pList = q.getResultList();
                for (PhotoUpload photoUpload : pList) {
                    dto.getPhotoUploadList().add(new PhotoUploadDTO(photoUpload));
                }

                q = em.createNamedQuery("PhotoUpload.countProjectPhotosByMonitor", PhotoUpload.class);
                q.setParameter("monitorID", monitor.getMonitorID());
                Object obj = q.getSingleResult();
                Long count = (Long) obj;
                dto.setPhotoCount(count.intValue());

                q = em.createNamedQuery("ProjectTaskStatus.countByMonitor", ProjectTaskStatus.class);
                q.setParameter("monitorID", monitor.getMonitorID());
                Object objm = q.getSingleResult();
                Long countm = (Long) objm;
                dto.setStatusCount(countm.intValue());

                q = em.createNamedQuery("MonitorProject.countProjectsByMonitor", MonitorProject.class);
                q.setParameter("monitorID", monitor.getMonitorID());
                Object objx = q.getSingleResult();
                Long countx = (Long) objx;
                dto.setProjectCount(countx.intValue());

                resp.getMonitorList().add(dto);
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project data\n" + getErrorString(e));
        }

        return resp;
    }

    public static ResponseDTO getTaskStatusTypeList(EntityManager em, Integer companyID) throws DataException {
        ResponseDTO resp = new ResponseDTO();

        try {
            Query q = em.createNamedQuery("TaskStatusType.findByCompany", TaskStatusType.class);
            q.setParameter("companyID", companyID);
            List<TaskStatusType> sList = q.getResultList();
            resp.setTaskStatusTypeList(new ArrayList<>());
            for (TaskStatusType cs : sList) {
                resp.getTaskStatusTypeList().add(new TaskStatusTypeDTO(cs));
            }
            log.log(Level.INFO, "task status types found: {0}", sList.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project data\n" + getErrorString(e));
        }

        return resp;
    }

    public static ResponseDTO getProjectStatusTypeList(EntityManager em, Integer companyID) throws DataException {
        ResponseDTO resp = new ResponseDTO();

        try {
            Query q = em.createNamedQuery("ProjectStatusType.findByCompany", ProjectStatusType.class);
            q.setParameter("companyID", companyID);
            List<ProjectStatusType> sList = q.getResultList();
            resp.setProjectStatusTypeList(new ArrayList<>());
            for (ProjectStatusType cs : sList) {
                resp.getProjectStatusTypeList().add(new ProjectStatusTypeDTO(cs));
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project status list\n" + getErrorString(e));
        }

        return resp;
    }

    public static ResponseDTO getProjectTasks(EntityManager em, Integer projectID) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Query q = em.createNamedQuery("ProjectTask.findByProject", ProjectTask.class);
            q.setParameter("projectID", projectID);
            List<ProjectTask> pstList = q.getResultList();
            log.log(Level.INFO, "tasks found: {0}", pstList.size());
            resp.setProjectTaskList(new ArrayList<>());
            for (ProjectTask projectTask : pstList) {
                resp.getProjectTaskList().add(new ProjectTaskDTO(projectTask));
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get tasks\n" + getErrorString(e));
        }

        return resp;
    }

    public static ResponseDTO getProjectStatusData(EntityManager em, Integer projectID, int days) throws DataException {
        long s = System.currentTimeMillis();
        if (days == 0) {
            days = 30;
        }
        ResponseDTO resp = new ResponseDTO();
        try {
            Project p = em.find(Project.class, projectID);
            ProjectDTO project = new ProjectDTO(p);

            DateTime now = new DateTime();
            DateTime then = now.minusDays(days);
            then = then.withHourOfDay(0);
            then = then.withMinuteOfHour(0);
            then = then.withSecondOfMinute(0);

            project.setProjectTaskList(
                    ListUtil.getProjectStatus(em, projectID, then.toDate(), now.toDate()).getProjectTaskList());
            project.setPhotoUploadList(
                    getPhotosByProject(em, projectID, then.toDate(), now.toDate()).getPhotoUploadList());

            resp.setProjectList(new ArrayList<>());
            resp.getProjectList().add(project);

            long e = System.currentTimeMillis();
            log.log(Level.INFO, "############---------- project data retrieved: {0} seconds", Elapsed.getElapsed(s, e));
        } catch (OutOfMemoryError e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project data: OUT OF MEMORY!\n");
        }

        return resp;
    }

    public static String getErrorString(Exception e) {
        StringBuilder sb = new StringBuilder();
        if (e.getMessage() != null) {
            sb.append(e.getMessage()).append("\n\n");
        }
        if (e.toString() != null) {
            sb.append(e.toString()).append("\n\n");
        }
        StackTraceElement[] s = e.getStackTrace();
        if (s.length > 0) {
            StackTraceElement ss = s[0];
            String method = ss.getMethodName();
            String cls = ss.getClassName();
            int line = ss.getLineNumber();
            sb.append("Class: ").append(cls).append("\n");
            sb.append("Method: ").append(method).append("\n");
            sb.append("Line Number: ").append(line).append("\n");
        }

        return sb.toString();
    }

    public static void addErrorStore(EntityManager em, int statusCode, String message, String origin) {
        log.log(Level.OFF, "------ adding errorStore, message: {0} origin: {1}", new Object[]{message, origin});
        try {
            ErrorStore t = new ErrorStore();
            t.setDateOccured(new Date());
            t.setMessage(message);
            t.setStatusCode(statusCode);
            t.setOrigin(origin);
            em.persist(t);
            log.log(Level.INFO, "####### ErrorStore row added, origin {0} \nmessage: {1}",
                    new Object[]{origin, message});
        } catch (Exception e) {
            log.log(Level.SEVERE, "####### Failed to add errorStore from " + origin + "\n" + message, e);
        }
    }

    public static ResponseDTO getServerEvents(EntityManager em, Long dt, Long dx) throws DataException {
        ResponseDTO r = new ResponseDTO();
        Date startDate, endDate;
        if (dt == null) {
            DateTime ed = new DateTime();
            DateTime sd = ed.minusMonths(3);
            startDate = sd.toDate();
            endDate = ed.toDate();
        } else {
            startDate = new Date(dt);
            endDate = new Date(dx);
        }
        try {
            Query q = em.createNamedQuery("ErrorStoreAndroid.findByPeriod", ErrorStoreAndroid.class);
            q.setParameter("from", startDate);
            q.setParameter("to", endDate);
            List<ErrorStoreAndroid> list = q.getResultList();
            List<ErrorStoreAndroidDTO> dList = new ArrayList();
            for (ErrorStoreAndroid e : list) {
                dList.add(new ErrorStoreAndroidDTO(e));
            }
            r.setErrorStoreAndroidList(dList);
            r.setErrorStoreList(getServerErrors(em, startDate.getTime(), endDate.getTime()).getErrorStoreList());

            String logx = LogfileUtil.getFileString();
            r.setLog(logx);
            log.log(Level.OFF, "Android Errors found {0}", r.getErrorStoreAndroidList().size());
        } catch (DataException | IOException e) {
            log.log(Level.SEVERE, "Failed ");
            throw new DataException("Failed\n" + getErrorString(e));
        }
        return r;
    }

    public static ResponseDTO getServerErrors(EntityManager em, long startDate, long endDate) throws DataException {
        ResponseDTO r = new ResponseDTO();
        if (startDate == 0) {
            DateTime ed = new DateTime();
            DateTime sd = ed.minusMonths(3);
            startDate = sd.getMillis();
            endDate = ed.getMillis();
        }
        try {
            Query q = em.createNamedQuery("ErrorStore.findByPeriod", ErrorStore.class);
            q.setParameter("startDate", new Date(startDate));
            q.setParameter("endDate", new Date(endDate));
            List<ErrorStore> list = q.getResultList();
            List<ErrorStoreDTO> dList = new ArrayList();
            for (ErrorStore e : list) {
                dList.add(new ErrorStoreDTO(e));
            }
            r.setErrorStoreList(dList);
            log.log(Level.OFF, "Errors found {0}", r.getErrorStoreList().size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to getServerErrors");
            throw new DataException("Failed to getServerErrors\n" + getErrorString(e));
        }
        return r;
    }

    static final Logger log = Logger.getLogger(ListUtil.class.getSimpleName());
}
