/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.util;

import com.boha.monitor.data.Beneficiary;
import com.boha.monitor.data.CheckPoint;
import com.boha.monitor.data.City;
import com.boha.monitor.data.Client;
import com.boha.monitor.data.Company;
import com.boha.monitor.data.CompanyStaff;
import com.boha.monitor.data.CompanyStaffType;
import com.boha.monitor.data.ErrorStore;
import com.boha.monitor.data.ErrorStoreAndroid;
import com.boha.monitor.data.GcmDevice;
import com.boha.monitor.data.InvoiceCode;
import com.boha.monitor.data.Project;
import com.boha.monitor.data.ProjectDiaryRecord;
import com.boha.monitor.data.ProjectSite;
import com.boha.monitor.data.ProjectSiteStaff;
import com.boha.monitor.data.ProjectSiteTask;
import com.boha.monitor.data.ProjectSiteTaskStatus;
import com.boha.monitor.data.ProjectStatusType;
import com.boha.monitor.data.Province;
import com.boha.monitor.data.Task;
import com.boha.monitor.data.TaskStatus;
import com.boha.monitor.data.Township;
import com.boha.monitor.dto.BeneficiaryDTO;
import com.boha.monitor.dto.CheckPointDTO;
import com.boha.monitor.dto.CityDTO;
import com.boha.monitor.dto.ClientDTO;
import com.boha.monitor.dto.CompanyDTO;
import com.boha.monitor.dto.CompanyStaffDTO;
import com.boha.monitor.dto.ErrorStoreDTO;
import com.boha.monitor.dto.GcmDeviceDTO;
import com.boha.monitor.dto.ProjectDTO;
import com.boha.monitor.dto.ProjectDiaryRecordDTO;
import com.boha.monitor.dto.ProjectSiteDTO;
import com.boha.monitor.dto.ProjectSiteStaffDTO;
import com.boha.monitor.dto.ProjectSiteTaskDTO;
import com.boha.monitor.dto.ProjectSiteTaskStatusDTO;
import com.boha.monitor.dto.ProjectStatusTypeDTO;
import com.boha.monitor.dto.TaskDTO;
import com.boha.monitor.dto.TaskStatusDTO;
import com.boha.monitor.dto.TownshipDTO;
import com.boha.monitor.dto.transfer.ResponseDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.joda.time.DateTime;

/**
 *
 * @author aubreyM
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DataUtil {

    @PersistenceContext
    EntityManager em;

    static final int OPERATIONS_MANAGER = 1,
            SITE_SUPERVISOR = 2,
            EXECUTIVE_STAFF = 3,
            PROJECT_MANAGER = 4;

    public ResponseDTO login(GcmDeviceDTO device, String email,
            String pin, ListUtil listUtil, PlatformUtil platformUtil) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        Query q = null;
        try {
            q = em.createNamedQuery("CompanyStaff.login", CompanyStaff.class);
            q.setParameter("email", email);
            q.setParameter("pin", pin);
            q.setMaxResults(1);
            CompanyStaff cs = (CompanyStaff) q.getSingleResult();
            Company company = cs.getCompany();
            resp.setCompanyStaff(new CompanyStaffDTO(cs));

            device.setCompanyID(company.getCompanyID());
            device.setCompanyStaffID(cs.getCompanyStaffID());
            addDevice(device);

            try {
                CloudMessagingRegistrar.sendRegistration(device.getRegistrationID(), platformUtil);
            } catch (IOException ex) {
                Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            }

            //load appropriate data for each type
            int staffCode = cs.getCompanyStaffType().getStaffCode();
            switch (staffCode) {
                case OPERATIONS_MANAGER:
                    resp = listUtil.getCompanyData(company.getCompanyID());
                    resp.setCompanyStaff(new CompanyStaffDTO(cs));
                    break;
                case SITE_SUPERVISOR:
                    resp.setCompanyStaff(new CompanyStaffDTO(cs));
                    break;
                case EXECUTIVE_STAFF:
                    resp = listUtil.getCompanyData(company.getCompanyID());
                    resp.setCompanyStaff(new CompanyStaffDTO(cs));
                    break;
                case PROJECT_MANAGER:
                    resp.setCompanyStaff(new CompanyStaffDTO(cs));
                    break;

            }

        } catch (NoResultException e) {
            log.log(Level.WARNING, "Invalid login attempt: " + email + " pin: " + pin, e);
            resp.setStatusCode(301);
            resp.setMessage("Email address or PIN are invalid. Please try again.");
        }
        return resp;
    }

    public void addAndroidError(ErrorStoreAndroid err) throws DataException {
        try {
            em.persist(err);
            log.log(Level.INFO, "Android error added");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to add Android Error", e);
            throw new DataException("Failed to add Android Error\n"
                    + getErrorString(e));
        }
    }

    public ResponseDTO getServerErrors(
            long startDate, long endDate) throws DataException {
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
            throw new DataException("Failed to getServerErrors\n"
                    + getErrorString(e));
        }
        return r;
    }

    public Company getCompanyByID(Integer id) {
        return em.find(Company.class, id);
    }

    public void addDevice(GcmDeviceDTO d) throws DataException {
        try {
            GcmDevice g = new GcmDevice();
            g.setCompany(em.find(Company.class, d.getCompanyID()));
            g.setCompanyStaff(em.find(CompanyStaff.class, d.getCompanyStaffID()));
            if (d.getProjectSiteID() != null
                    && d.getProjectSiteID() > 0) {
                g.setProjectSite(em.find(ProjectSite.class, d.getProjectSiteID()));
            }
            g.setDateRegistered(new Date());
            g.setManufacturer(d.getManufacturer());
            g.setMessageCount(0);
            g.setModel(d.getModel());
            g.setRegistrationID(d.getRegistrationID());
            g.setSerialNumber(d.getSerialNumber());
            g.setProduct(d.getProduct());

            em.persist(g);
            log.log(Level.WARNING, "New device loaded");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");

        }
    }

    public ResponseDTO addProjectSiteTaskStatus(
            ProjectSiteTaskStatusDTO status) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            ProjectSiteTask c = em.find(ProjectSiteTask.class,
                    status.getProjectSiteTaskID());
            ProjectSiteTaskStatus t = new ProjectSiteTaskStatus();
            t.setDateUpdated(new Date());
            t.setProjectSiteTask(c);
            t.setProjectSiteStaff(em.find(ProjectSiteStaff.class, status.getProjectSiteStaffID()));
            t.setTaskStatus(em.find(TaskStatus.class, status.getTaskStatus().getTaskStatusID()));

            em.persist(t);
            Query q = em.createNamedQuery("ProjectSiteTaskStatus.findbyTask",
                    ProjectSiteTaskStatus.class);
            q.setParameter("id", c.getProjectSiteTaskID());
            List<ProjectSiteTaskStatus> list = q.getResultList();
            for (ProjectSiteTaskStatus s : list) {
                resp.getProjectSiteTaskStatusList().add(new ProjectSiteTaskStatusDTO(s));
            }

            log.log(Level.OFF, "ProjectSiteTaskStatus added");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }

        return resp;

    }

    public ResponseDTO addProjectDiaryRecord(
            ProjectDiaryRecordDTO diary) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            ProjectSiteStaff c = em.find(ProjectSiteStaff.class,
                    diary.getProjectSiteStaff().getProjectSiteStaffID());
            ProjectDiaryRecord t = new ProjectDiaryRecord();
            t.setDiaryDate(new Date());
            t.setProjectSiteStaff(c);
            t.setProjectStatusType(em.find(ProjectStatusType.class,
                    diary.getProjectStatusType().getProjectStatusTypeID()));

            em.persist(t);
            Query q = em.createNamedQuery("ProjectDiaryRecord.findByProjectSite",
                    ProjectDiaryRecord.class);
            q.setParameter("projectSiteID", c.getProjectSite().getProjectSiteID());
            List<ProjectDiaryRecord> list = q.getResultList();
            for (ProjectDiaryRecord projectDiaryRecord : list) {
                resp.getProjectDiaryRecordList().add(new ProjectDiaryRecordDTO(projectDiaryRecord));
            }

            log.log(Level.OFF, "ProjectDiaryRecord added");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }

        return resp;

    }

    public ResponseDTO addCompanyCheckPoint(CheckPointDTO b) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Company c = em.find(Company.class, b.getCompanyID());
            CheckPoint cli = new CheckPoint();
            cli.setCheckPointName(b.getCheckPointName());
            cli.setDescription(b.getDescription());
            cli.setCompany(c);

            em.persist(cli);

            Query q = em.createNamedQuery("CheckPoint.findByCompany", CheckPoint.class);
            q.setParameter("companyID", c.getCompanyID());
            List<CheckPoint> list = q.getResultList();
            for (CheckPoint t : list) {
                resp.getCheckPointList().add(new CheckPointDTO(t));
            }

            log.log(Level.OFF, "######## CheckPoint added: {0}", b.getCheckPointName());

        } catch (PersistenceException e) {
            log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }
        return resp;
    }

    public ResponseDTO addCompanyProjectStatus(ProjectStatusTypeDTO b) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Company c = em.find(Company.class, b.getCompanyID());
            ProjectStatusType cli = new ProjectStatusType();
            cli.setProjectStatusName(b.getProjectStatusName());
            cli.setCompany(c);

            em.persist(cli);

            Query q = em.createNamedQuery("ProjectStatusType.findByProjectStatusNameInCompany", ProjectStatusType.class);
            q.setParameter("companyID", c.getCompanyID());
            q.setParameter("projectStatusName", b.getProjectStatusName());
            List<ProjectStatusType> list = q.getResultList();
            for (ProjectStatusType t : list) {
                resp.getProjectStatusTypeList().add(new ProjectStatusTypeDTO(t));
            }

            log.log(Level.OFF, "######## ProjectStatusType added: {0}", b.getProjectStatusName());

        } catch (PersistenceException e) {
            log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }
        return resp;
    }

    public ResponseDTO addCompanyTaskStatus(TaskStatusDTO b) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Company c = em.find(Company.class, b.getCompanyID());
            TaskStatus cli = new TaskStatus();
            cli.setTaskStatusName(b.getTaskStatusName());
            cli.setCompany(c);

            em.persist(cli);

            Query q = em.createNamedQuery("TaskStatus.findByCompany", TaskStatus.class);
            q.setParameter("companyID", c.getCompanyID());
            List<TaskStatus> list = q.getResultList();
            for (TaskStatus task : list) {
                resp.getTaskStatusList().add(new TaskStatusDTO(task));
            }

            log.log(Level.OFF, "######## TaskStatus added: {0}", b.getTaskStatusName());

        } catch (PersistenceException e) {
            log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }
        return resp;
    }

    public ResponseDTO addCity(CityDTO b) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Province c = em.find(Province.class, b.getProvinceID());
            City cli = new City();
            cli.setCityName(b.getCityName());
            cli.setProvince(c);
            cli.setLatitude(b.getLatitude());
            cli.setLongitude(b.getLongitude());

            em.persist(cli);

            Query q = em.createNamedQuery("City.findByProvince", City.class);
            q.setParameter("provinceID", c.getProvinceID());
            List<City> list = q.getResultList();
            for (City ct : list) {
                resp.getCityList().add(new CityDTO(ct));
            }

            log.log(Level.OFF, "######## City added: {0}", b.getCityName());

        } catch (PersistenceException e) {
            log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }
        return resp;
    }

    public ResponseDTO addTownship(TownshipDTO b) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            City c = em.find(City.class, b.getCityID());
            Township cli = new Township();
            cli.setTownshipName(b.getTownshipName());
            cli.setCity(c);
            cli.setLatitude(b.getLatitude());
            cli.setLongitude(b.getLongitude());

            em.persist(cli);

            Query q = em.createNamedQuery("Township.findByCity", Township.class);
            q.setParameter("cityID", b.getCityID());
            List<Township> list = q.getResultList();
            for (Township ct : list) {
                resp.getTownshipList().add(new TownshipDTO(ct));
            }

            log.log(Level.OFF, "######## Township added: {0}", b.getTownshipName());

        } catch (PersistenceException e) {
            log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }
        return resp;
    }

    public ResponseDTO addCompanyTask(TaskDTO b) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Company c = em.find(Company.class, b.getCompanyID());
            Task cli = new Task();
            cli.setTaskName(b.getTaskName());
            cli.setDescription(b.getDescription());
            cli.setTaskNumber(b.getTaskNumber());
            cli.setCompany(c);

            em.persist(cli);

            Query q = em.createNamedQuery("Task.findByCompany", Task.class);
            q.setParameter("companyID", c.getCompanyID());
            List<Task> list = q.getResultList();
            for (Task task : list) {
                resp.getTaskList().add(new TaskDTO(task));
            }

            log.log(Level.OFF, "######## Task added: {0}", b.getTaskName());

        } catch (PersistenceException e) {
            log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }
        return resp;
    }

    public ResponseDTO addProjectSiteTask(
            ProjectSiteTaskDTO siteTask) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            ProjectSite c = em.find(ProjectSite.class, siteTask.getProjectSiteID());
            Task task = em.find(Task.class, siteTask.getTask().getTaskID());
            ProjectSiteTask t = new ProjectSiteTask();
            t.setDateRegistered(new Date());
            t.setProjectSite(c);
            t.setTask(task);

            em.persist(t);
            Query q = em.createNamedQuery("ProjectSiteTask.findByProjectSite",
                    ProjectSiteTask.class);
            q.setParameter("projectSiteID", siteTask.getProjectSiteID());
            List<ProjectSiteTask> list = q.getResultList();
            for (ProjectSiteTask pst : list) {
                resp.getProjectSiteTaskList().add(new ProjectSiteTaskDTO(pst));
            }

            log.log(Level.OFF, "Project site task registered for: {0} ",
                    new Object[]{c.getProjectSiteName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }

        return resp;

    }

    public ResponseDTO registerProjectSiteStaff(
            ProjectSiteStaffDTO staff) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            ProjectSite c = em.find(ProjectSite.class, staff.getProjectSiteID());
            ProjectSiteStaff ps = new ProjectSiteStaff();
            ps.setCompanyStaff(em.find(CompanyStaff.class, staff.getCompanyStaff().getCompanyStaffID()));
            ps.setDateRegistered(new Date());
            ps.setProjectSite(c);
            ps.setPin(getRandomPin());

            em.persist(ps);
            Query q = em.createNamedQuery("ProjectSiteStaff.findBySiteAndStaff",
                    ProjectSiteStaff.class);
            q.setParameter("companyStaffID", staff.getCompanyStaff().getCompanyStaffID());
            q.setParameter("projectSiteID", staff.getProjectSiteID());
            q.setMaxResults(1);
            ps = (ProjectSiteStaff) q.getSingleResult();
            resp.getProjectSiteStaffList().add(new ProjectSiteStaffDTO(ps));

            log.log(Level.OFF, "Project site staff registered for: {0} ",
                    new Object[]{c.getProjectSiteName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }

        return resp;

    }

    public ResponseDTO registerProjectSite(
            ProjectSiteDTO site) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Project c = em.find(Project.class, site.getProjectID());
            ProjectSite ps = new ProjectSite();
            ps.setProject(c);
            ps.setActiveFlag(0);
            ps.setLatitude(site.getLatitude());
            ps.setLongitude(site.getLongitude());
            ps.setProjectSiteName(site.getProjectSiteName());

            em.persist(ps);

            Query q = em.createNamedQuery("ProjectSite.findByProjectAndSiteName", ProjectSite.class);
            q.setParameter("name", site.getProjectSiteName());
            q.setParameter("projectID", c.getProjectID());
            q.setMaxResults(1);
            ps = (ProjectSite) q.getSingleResult();
            resp.getProjectSiteList().add(new ProjectSiteDTO(ps));

            log.log(Level.OFF, "Project site registered for: {0} - {1} ",
                    new Object[]{c.getProjectName(), site.getProjectSiteName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }

        return resp;

    }

    public ResponseDTO registerProject(
            ProjectDTO proj) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Company c = em.find(Company.class, proj.getCompanyID());
            Project project = new Project();
            project.setCompany(c);
            project.setProjectName(proj.getProjectName());
            project.setDescription(proj.getDescription());
            project.setDateRegistered(new Date());
            em.persist(project);
            Query q = em.createNamedQuery("Project.findByCompanyProjectName", Project.class);
            q.setParameter("projectName", proj.getProjectName());
            q.setParameter("companyID", c.getCompanyID());
            q.setMaxResults(1);
            project = (Project) q.getSingleResult();
            resp.getProjectList().add(new ProjectDTO(project));
            log.log(Level.OFF, "Project registered for: {0} - {1} ",
                    new Object[]{c.getCompanyName(), proj.getProjectName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }

        return resp;

    }

    public ResponseDTO registerCompanyStaff(
            CompanyStaffDTO staff) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Company c = em.find(Company.class, staff.getCompanyID());
            CompanyStaff cs = new CompanyStaff();
            cs.setCompany(c);
            cs.setFirstName(staff.getFirstName());
            cs.setCellphone(staff.getCellphone());
            cs.setEmail(staff.getEmail());
            cs.setLastName(staff.getLastName());
            cs.setPin(getRandomPin());
            cs.setCompanyStaffType(em.find(CompanyStaffType.class,
                    staff.getCompanyStaffType().getCompanyStaffTypeID()));
            em.persist(cs);

            Query q = em.createNamedQuery("CompanyStaff.findByEmail", CompanyStaff.class);
            q.setParameter("email", cs.getEmail());
            q.setMaxResults(1);
            cs = (CompanyStaff) q.getSingleResult();
            resp.getCompanyStaffList().add(new CompanyStaffDTO(cs));

            try {
                if (staff.getGcmDevice() != null) {
                    addDevice(staff.getGcmDevice());
                }

            } catch (DataException e) {
                log.log(Level.WARNING, "Unable to add device to GCMDevice table", e);
            }

            log.log(Level.OFF, "Company staff registered for: {0} - {1} {2}",
                    new Object[]{c.getCompanyName(), staff.getFirstName(), staff.getLastName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }

        return resp;

    }

    public ResponseDTO registerClient(ClientDTO client) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Company c = em.find(Company.class, client.getCompanyID());
            Client cli = new Client();
            cli.setAddress(client.getAddress());
            cli.setCellphone(client.getCellphone());
            cli.setClientName(client.getClientName());
            cli.setEmail(client.getEmail());
            cli.setPostCode(client.getPostCode());
            cli.setCompany(c);

            em.persist(cli);

            Query q = em.createNamedQuery("Client.findByClientNameInCompany", Client.class);
            q.setParameter("clientName", cli.getClientName());
            q.setParameter("companyID", c.getCompanyID());
            q.setMaxResults(1);
            Client x = (Client) q.getSingleResult();
            resp.getClientList().add(new ClientDTO(x));

            log.log(Level.OFF, "######## Client registered: {0}", x.getClientName());

        } catch (PersistenceException e) {
            log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }
        return resp;
    }

    public ResponseDTO registerBeneficiary(BeneficiaryDTO b) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Company c = em.find(Company.class, b.getCompany().getCompanyID());
            Beneficiary cli = new Beneficiary();
            cli.setFirstName(b.getFirstName());
            cli.setCellphone(b.getCellphone());
            cli.setMiddleName(b.getMiddleName());
            cli.setEmail(b.getEmail());
            cli.setIDNumber(b.getIDNumber());
            cli.setDateRegistered(new Date());
            if (b.getTownship() != null) {
                cli.setTownship(em.find(Township.class, b.getTownship().getTownshipID()));
            }
            cli.setAmountAuthorized(b.getAmountAuthorized());
            cli.setAmountPaid(b.getAmountPaid());
            cli.setCompany(c);

            em.persist(cli);

            Query q = em.createNamedQuery("Beneficiary.findByIDNumberInCompany", Beneficiary.class);
            q.setParameter("IDNumber", b.getIDNumber());
            q.setParameter("companyID", c.getCompanyID());
            q.setMaxResults(1);
            Beneficiary x = (Beneficiary) q.getSingleResult();
            resp.getBeneficiaryList().add(new BeneficiaryDTO(x));

            log.log(Level.OFF, "######## Beneficiary registered: {0} {1}", new Object[]{x.getFirstName(), x.getLastName()});

        } catch (PersistenceException e) {
            log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }
        return resp;
    }

    public ResponseDTO registerCompany(CompanyDTO company,
            CompanyStaffDTO staff, ListUtil listUtil) throws DataException {
        log.log(Level.OFF, "####### * attempt to register company");
        ResponseDTO resp = new ResponseDTO();
        try {
            Company c = new Company();
            c.setCompanyName(company.getCompanyName());
            em.persist(c);
            Query q = em.createNamedQuery("Company.findByCompanyName", Company.class);
            q.setParameter("companyName", company.getCompanyName());
            q.setMaxResults(1);
            Company x = (Company) q.getSingleResult();

            //add operations staff
            CompanyStaff cs = new CompanyStaff();
            cs.setCompany(x);
            cs.setFirstName(staff.getFirstName());
            cs.setCellphone(staff.getCellphone());
            cs.setEmail(staff.getEmail());
            cs.setLastName(staff.getLastName());
            cs.setPin(staff.getPin());
            cs.setCompanyStaffType(getOperationsManager());
            em.persist(cs);

            //add sample data - app not empty at startup
            addInitialTaskStatus(c);
            addinitialProjectStatusType(c);
            addInitialCheckpoints(c);
            addInitialTasks(c);
            addInitialInvoiceCodes(c);
            addInitialProject(c);

            q = em.createNamedQuery("CompanyStaff.findByEmail", CompanyStaff.class);
            q.setParameter("email", cs.getEmail());
            q.setMaxResults(1);
            cs = (CompanyStaff) q.getSingleResult();

            resp = listUtil.getCompanyData(x.getCompanyID());
            resp.setCompanyStaff(new CompanyStaffDTO(cs));

            log.log(Level.OFF, "######## Company registered: {0}", x.getCompanyName());

        } catch (PersistenceException e) {
            log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }

        return resp;

    }

    private void addInitialProject(Company c) {

        Client client = new Client();
        client.setClientName("Sample Client #1");
        client.setAddress("999 Sample Boulevard, SomeTown");
        client.setCellphone("999 999 9999");
        client.setCompany(c);
        client.setEmail("client@clientsite.com");
        client.setPostCode("99999");
        em.persist(client);

        Query q = em.createNamedQuery("Client.findByClientNameInCompany");
        q.setParameter("clientName", client.getClientName());
        q.setParameter("companyID", c.getCompanyID());
        q.setMaxResults(1);
        Client dbClient = (Client) q.getSingleResult();

        Project project = new Project();
        project.setCompany(c);
        project.setCompleteFlag(0);
        project.setClient(dbClient);
        project.setDescription("Detailed description of Sample Project #1");
        project.setProjectName("Sample Project #1");
        project.setDateRegistered(new Date());

        em.persist(project);
        q = em.createNamedQuery("Project.findByCompanyProjectName", Project.class);
        q.setParameter("projectName", project.getProjectName());
        q.setParameter("companyID", c.getCompanyID());
        q.setMaxResults(1);
        Project pp = (Project) q.getSingleResult();

        ProjectSite pss1 = new ProjectSite();
        pss1.setActiveFlag(0);
        pss1.setProject(pp);
        pss1.setProjectSiteName("Sample Site #1");
        pss1.setStandErfNumber("Stand #3331");
        em.persist(pss1);

        ProjectSite pss2 = new ProjectSite();
        pss2.setActiveFlag(0);
        pss2.setProject(pp);
        pss2.setProjectSiteName("Sample Site #2");
        pss2.setStandErfNumber("Stand #3332");
        em.persist(pss2);

        ProjectSite pss3 = new ProjectSite();
        pss3.setActiveFlag(0);
        pss3.setProject(pp);
        pss3.setProjectSiteName("Sample Site #3");
        pss3.setStandErfNumber("Stand #3333");
        em.persist(pss3);

        q = em.createNamedQuery("Task.findByCompany", Task.class);
        q.setParameter("companyID", c.getCompanyID());
        List<Task> taskList = q.getResultList();

        q = em.createNamedQuery("ProjectSite.findByCompany", ProjectSite.class);
        q.setParameter("companyID", c.getCompanyID());
        List<ProjectSite> psList = q.getResultList();
        
        for (ProjectSite projectSite : psList) {
            for (Task task : taskList) {
                ProjectSiteTask projectSiteTask = new ProjectSiteTask();
                projectSiteTask.setDateRegistered(new Date());
                projectSiteTask.setProjectSite(projectSite);
                projectSiteTask.setTask(task);
                em.persist(projectSiteTask);
            }
        }
        log.log(Level.INFO, "#### Initial Project and Sites added");
    }

    private void addInitialInvoiceCodes(Company c) {
        InvoiceCode code1 = new InvoiceCode();
        code1.setInvoiceCodeName("InvoiceCode #1");
        code1.setInvoiceCodeNumber("10001");
        code1.setCompany(c);
        em.persist(code1);
        InvoiceCode code2 = new InvoiceCode();
        code2.setInvoiceCodeName("InvoiceCode #2");
        code2.setInvoiceCodeNumber("20002");
        code2.setCompany(c);
        em.persist(code2);
        InvoiceCode code3 = new InvoiceCode();
        code3.setInvoiceCodeName("InvoiceCode #3");
        code3.setInvoiceCodeNumber("30003");
        code3.setCompany(c);
        em.persist(code3);
        
        log.log(Level.INFO, "Initial Invoice Codes added");
    }

    private void addInitialTasks(Company c) {
        Task t1 = new Task();
        t1.setCompany(c);
        t1.setTaskName("Task #1 - First Task at site");
        t1.setDescription("Description of Task #1");
        t1.setTaskNumber(1);
        em.persist(t1);
        Task t2 = new Task();
        t2.setCompany(c);
        t2.setTaskName("Task #2 - Second Task at site");
        t2.setDescription("Description of Task #2");
        t2.setTaskNumber(2);
        em.persist(t2);
        Task t3 = new Task();
        t3.setCompany(c);
        t3.setTaskName("Task #3 - Third Task at site");
        t3.setDescription("Description of Task #3");
        t3.setTaskNumber(1);
        em.persist(t3);
        Task t4 = new Task();
        t4.setCompany(c);
        t4.setTaskName("Task #4 - Fourth Task at site");
        t4.setDescription("Description of Task #4");
        t4.setTaskNumber(1);
        em.persist(t4);
        Task t5 = new Task();
        t5.setCompany(c);
        t5.setTaskName("Task #5 - Fifth Task at site");
        t5.setDescription("Description of Task #5");
        t5.setTaskNumber(1);
        em.persist(t5);
        log.log(Level.INFO, "Initial Tasks added");
    }

    private void addInitialCheckpoints(Company c) {
        CheckPoint cp1 = new CheckPoint();
        cp1.setCompany(c);
        cp1.setCheckPointName("CheckPoint Number One");
        cp1.setDescription("Description of CheckPoint Number One");
        em.persist(cp1);
        CheckPoint cp2 = new CheckPoint();
        cp2.setCompany(c);
        cp2.setCheckPointName("CheckPoint Number Two");
        cp2.setDescription("Description of CheckPoint Number Two");
        em.persist(cp2);
        CheckPoint cp3 = new CheckPoint();
        cp3.setCompany(c);
        cp3.setCheckPointName("CheckPoint Number Three");
        cp3.setDescription("Description of CheckPoint Number Three");
        em.persist(cp3);
        CheckPoint cp4 = new CheckPoint();
        cp4.setCompany(c);
        cp4.setCheckPointName("CheckPoint Number Four");
        cp4.setDescription("Description of CheckPoint Number Four");
        em.persist(cp4);
        CheckPoint cp5 = new CheckPoint();
        cp5.setCompany(c);
        cp5.setCheckPointName("CheckPoint Number Five");
        cp5.setDescription("Description of CheckPoint Number Five");
        em.persist(cp5);
        log.log(Level.INFO, "Initial CheckPoints added");
    }

    private void addinitialProjectStatusType(Company c) {
        ProjectStatusType p1 = new ProjectStatusType();
        p1.setCompany(c);
        p1.setProjectStatusName("Project is ahead of schedule");
        em.persist(p1);
        ProjectStatusType p2 = new ProjectStatusType();
        p2.setCompany(c);
        p2.setProjectStatusName("Project is on schedule");
        em.persist(p2);
        ProjectStatusType p3 = new ProjectStatusType();
        p3.setCompany(c);
        p3.setProjectStatusName("Project is complete");
        em.persist(p3);
        ProjectStatusType p4 = new ProjectStatusType();
        p4.setCompany(c);
        p4.setProjectStatusName("Project is behind schedule");
        em.persist(p4);
        ProjectStatusType p5 = new ProjectStatusType();
        p5.setCompany(c);
        p5.setProjectStatusName("Project is on budget");
        em.persist(p5);
        ProjectStatusType p6 = new ProjectStatusType();
        p6.setCompany(c);
        p6.setProjectStatusName("Project is over budget");
        em.persist(p6);
        log.log(Level.INFO, "Initial ProjectStatusTypes added");
    }

    private void addInitialTaskStatus(Company c) {
        TaskStatus ts1 = new TaskStatus();
        ts1.setTaskStatusName("Completed");
        ts1.setCompany(c);
        em.persist(ts1);
        TaskStatus ts2 = new TaskStatus();
        ts2.setTaskStatusName("Delayed - Weather");
        ts2.setCompany(c);
        em.persist(ts2);
        TaskStatus ts3 = new TaskStatus();
        ts3.setTaskStatusName("Delayed - Staff");
        ts3.setCompany(c);
        em.persist(ts3);
        TaskStatus ts4 = new TaskStatus();
        ts4.setTaskStatusName("Delayed - Materials");
        ts4.setCompany(c);
        em.persist(ts4);
        TaskStatus ts5 = new TaskStatus();
        ts5.setTaskStatusName("Not started yet");
        ts5.setCompany(c);
        em.persist(ts5);
        log.log(Level.INFO, "Initial TaskStatus added");
    }

    private CompanyStaffType getOperationsManager() {

        Query q = em.createNamedQuery("CompanyStaffType.findByCompanyStaffTypeName", CompanyStaffType.class);
        q.setParameter("companyStaffTypeName", "Operations Manager");
        q.setMaxResults(1);
        CompanyStaffType cst = (CompanyStaffType) q.getSingleResult();
        return cst;

    }

    public String getErrorString(Exception e) {
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
    static final Logger log = Logger.getLogger(DataUtil.class.getSimpleName());
}
