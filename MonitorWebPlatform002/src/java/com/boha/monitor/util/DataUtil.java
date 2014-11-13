/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.util;

import com.boha.monitor.data.Bank;
import com.boha.monitor.data.BankDetail;
import com.boha.monitor.data.Beneficiary;
import com.boha.monitor.data.CheckPoint;
import com.boha.monitor.data.City;
import com.boha.monitor.data.Client;
import com.boha.monitor.data.Company;
import com.boha.monitor.data.CompanyStaff;
import com.boha.monitor.data.CompanyStaffType;
import com.boha.monitor.data.ContractorClaim;
import com.boha.monitor.data.ContractorClaimSite;
import com.boha.monitor.data.Country;
import com.boha.monitor.data.Engineer;
import com.boha.monitor.data.ErrorStore;
import com.boha.monitor.data.ErrorStoreAndroid;
import com.boha.monitor.data.GcmDevice;
import com.boha.monitor.data.Invoice;
import com.boha.monitor.data.InvoiceItem;
import com.boha.monitor.data.PhotoUpload;
import com.boha.monitor.data.Project;
import com.boha.monitor.data.ProjectDiaryRecord;
import com.boha.monitor.data.ProjectEngineer;
import com.boha.monitor.data.ProjectSite;
import com.boha.monitor.data.ProjectSiteTask;
import com.boha.monitor.data.ProjectSiteTaskStatus;
import com.boha.monitor.data.ProjectStatusType;
import com.boha.monitor.data.Province;
import com.boha.monitor.data.Task;
import com.boha.monitor.data.TaskStatus;
import com.boha.monitor.data.Township;
import com.boha.monitor.dto.BankDTO;
import com.boha.monitor.dto.BankDetailDTO;
import com.boha.monitor.dto.BeneficiaryDTO;
import com.boha.monitor.dto.CheckPointDTO;
import com.boha.monitor.dto.CityDTO;
import com.boha.monitor.dto.ClientDTO;
import com.boha.monitor.dto.CompanyDTO;
import com.boha.monitor.dto.CompanyStaffDTO;
import com.boha.monitor.dto.ContractorClaimDTO;
import com.boha.monitor.dto.ContractorClaimSiteDTO;
import com.boha.monitor.dto.EngineerDTO;
import com.boha.monitor.dto.ErrorStoreDTO;
import com.boha.monitor.dto.GcmDeviceDTO;
import com.boha.monitor.dto.InvoiceDTO;
import com.boha.monitor.dto.InvoiceItemDTO;
import com.boha.monitor.dto.ProjectDTO;
import com.boha.monitor.dto.ProjectDiaryRecordDTO;
import com.boha.monitor.dto.ProjectEngineerDTO;
import com.boha.monitor.dto.ProjectSiteDTO;
import com.boha.monitor.dto.ProjectSiteTaskDTO;
import com.boha.monitor.dto.ProjectSiteTaskStatusDTO;
import com.boha.monitor.dto.ProjectStatusTypeDTO;
import com.boha.monitor.dto.TaskDTO;
import com.boha.monitor.dto.TaskStatusDTO;
import com.boha.monitor.dto.TownshipDTO;
import com.boha.monitor.dto.transfer.PhotoUploadDTO;
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
            String pin, ListUtil listUtil) throws DataException {
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
            resp.setCompany(new CompanyDTO(company));

            device.setCompanyID(company.getCompanyID());
            device.setCompanyStaffID(cs.getCompanyStaffID());
            addDevice(device);

            try {
                CloudMessagingRegistrar.sendRegistration(device.getRegistrationID(), this);
            } catch (IOException ex) {
                Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            }

            //load appropriate data for each type
            int staffCode = cs.getCompanyStaffType().getStaffCode();
            switch (staffCode) {
                case OPERATIONS_MANAGER:
                    resp = listUtil.getCompanyData(company.getCompanyID(), company.getCountry().getCountryID());
                    resp.setCompanyStaff(new CompanyStaffDTO(cs));
                    break;
                case SITE_SUPERVISOR:
                    resp.setCompanyStaff(new CompanyStaffDTO(cs));
                    break;
                case EXECUTIVE_STAFF:
                    resp = listUtil.getCompanyData(company.getCompanyID(), company.getCountry().getCountryID());
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

    public void addPhotoUpload(PhotoUploadDTO pu) {
        try {
            PhotoUpload u = new PhotoUpload();
            u.setCompany(em.find(Company.class, pu.getCompanyID()));
            if (pu.getProjectID() != null) {
                u.setProject(em.find(Project.class, pu.getProjectID()));
            }
            if (pu.getProjectSiteID() != null) {
                u.setProjectSite(em.find(ProjectSite.class, pu.getProjectSiteID()));
            }
            if (pu.getProjectSiteTaskID() != null) {
                u.setProjectSiteTask(em.find(ProjectSiteTask.class, pu.getProjectSiteTaskID()));
            }
            if (pu.getCompanyStaffID() != null) {
                u.setCompanyStaff(em.find(CompanyStaff.class, pu.getCompanyStaffID()));
            }
            u.setPictureType(pu.getPictureType());
            u.setLatitude(pu.getLatitude());
            u.setLongitude(pu.getLongitude());
            u.setUri(pu.getUri());
            u.setDateTaken(pu.getDateTaken());
            u.setDateUploaded(pu.getDateUploaded());
            u.setThumbFlag(pu.getThumbFlag());
            em.persist(u);
            em.flush();
            if (pu.getProjectSiteID() != null) {
                updateProjectSiteLocation(pu.getProjectSiteID(), u);
            }
            log.log(Level.OFF, "PhotoUpload added to table");
        } catch (Exception e) {
            log.log(Level.SEVERE, "PhotoUpload failed", e);
            addErrorStore(9,
                    "PhotoUpload database add failed\n"
                    + getErrorString(e), "DataUtil");

        }

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
            g.setAndroidVersion(d.getAndroidVersion());

            em.persist(g);
            log.log(Level.WARNING, "New device loaded");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to add device\n" + getErrorString(e));

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
            t.setStatusDate(new Date());
            t.setProjectSiteTask(c);
            t.setCompanyStaff(em.find(CompanyStaff.class, status.getCompanyStaffID()));
            t.setTaskStatus(em.find(TaskStatus.class, status.getTaskStatus().getTaskStatusID()));

            em.persist(t);
            em.flush();
            resp.getProjectSiteTaskStatusList().add(
                    new ProjectSiteTaskStatusDTO(t));
            log.log(Level.OFF, "ProjectSiteTaskStatus added");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }

        return resp;

    }

    public ResponseDTO addProjectDiaryRecord(
            ProjectDiaryRecordDTO diary) throws DataException, DataException, DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            ProjectDiaryRecord t = new ProjectDiaryRecord();
            t.setDiaryDate(new Date());
            //t.setProjectSiteStaff(c);
            t.setProjectStatusType(em.find(ProjectStatusType.class,
                    diary.getProjectStatusType().getProjectStatusTypeID()));

            em.persist(t);
            em.flush();
            resp.getProjectDiaryRecordList().add(
                    new ProjectDiaryRecordDTO(t));
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
            em.flush();
            resp.getCheckPointList().add(new CheckPointDTO(cli));
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
            cli.setStatusColor(b.getStatusColor());
            cli.setCompany(c);

            em.persist(cli);
            em.flush();
            resp.getProjectStatusTypeList().add(new ProjectStatusTypeDTO(cli));
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
            cli.setStatusColor(b.getStatusColor());
            cli.setCompany(c);

            em.persist(cli);
            em.flush();
            resp.getTaskStatusList().add(new TaskStatusDTO(cli));
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
            em.flush();
            resp.getCityList().add(new CityDTO(cli));
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
            em.flush();
            resp.getTownshipList().add(new TownshipDTO(cli));
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
            em.flush();
            resp.getTaskList().add(new TaskDTO(cli));
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
            ProjectSiteTaskDTO siteTask, ListUtil listUtil) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            ProjectSite site = em.find(ProjectSite.class, siteTask.getProjectSiteID());
            Task task = em.find(Task.class, siteTask.getTask().getTaskID());
            ProjectSiteTask t = new ProjectSiteTask();
            t.setDateRegistered(new Date());
            t.setProjectSite(site);
            t.setTask(task);

            em.persist(t);
            em.flush();
            System.out.println("## after add, list of tasks: " + site.getProjectSiteTaskList().size());
            System.out.println("## ListUtil tasks: " + listUtil.getProjectSite(site.getProjectSiteID()).getProjectSiteTaskList().size());
            resp.getProjectSiteTaskList().add(new ProjectSiteTaskDTO(t));
            resp.setStatusCode(0);
            resp.setMessage("ProjectSiteTask added successfully");
            log.log(Level.OFF, "Project site task registered for: {0} ",
                    new Object[]{site.getProjectSiteName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }

        return resp;

    }

    public ResponseDTO addContractorClaim(ContractorClaimDTO cc) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Project p = em.find(Project.class, cc.getProjectID());
            Task task = em.find(Task.class, cc.getTaskID());
            ContractorClaim t = new ContractorClaim();
            t.setClaimDate(cc.getClaimDate());
            t.setClaimNumber(getClaimNumber(cc));
            t.setProject(p);
            t.setTask(task);
            t.setProjectEngineer(em.find(ProjectEngineer.class, 
                    cc.getProjectEngineerID()));

            em.persist(t);
            em.flush();
            ContractorClaimDTO dto = new ContractorClaimDTO(t);
            if (cc.getContractorClaimSiteList() != null) {
                for (ContractorClaimSiteDTO site : cc.getContractorClaimSiteList()) {
                    dto.getContractorClaimSiteList().add(addContractorClaimSite(site).getContractorClaimSiteList().get(0));
                }
            }
            resp.setStatusCode(0);
            resp.setMessage("ContractorClaim added successfully");
            log.log(Level.OFF, "ContractorClaim registered");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }

        return resp;

    }

    public ResponseDTO addBank(BankDTO bank) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Bank t = new Bank();
            t.setBankName(bank.getBankName());
            em.persist(t);
            em.flush();
            resp.getBankList().add(new BankDTO(t));
            resp.setStatusCode(0);
            resp.setMessage("Bank added successfully");
            log.log(Level.OFF, "Bank added");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }

        return resp;

    }

    public ResponseDTO addBankDetails(BankDetailDTO detail) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            BankDetail t = new BankDetail();
            t.setBank(em.find(Bank.class, detail.getBank().getBankID()));
            t.setCompany(em.find(Company.class, detail.getCompanyID()));
            t.setAccountNumber(detail.getAccountNumber());
            t.setBranchCode(detail.getBranchCode());

            em.persist(t);
            em.flush();
            resp.getBankDetailList().add(new BankDetailDTO(t));
            resp.setStatusCode(0);
            resp.setMessage("BankDetail added successfully");
            log.log(Level.OFF, "BankDetail added");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }

        return resp;

    }

    public ResponseDTO addInvoice(InvoiceDTO invoice) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Invoice t = new Invoice();
            t.setClient(em.find(Client.class, invoice.getClient().getClientID()));
            t.setCompany(em.find(Company.class, invoice.getCompany().getCompanyID()));
            t.setDateRegistered(new Date());
            t.setInvoiceDate(invoice.getInvoiceDate());
            t.setInvoiceNumber(getInvoiceNumber(invoice));
            t.setProject(em.find(Project.class, invoice.getProject().getProjectID()));
            t.setReference(invoice.getReference());

            em.persist(t);
            em.flush();
            InvoiceDTO dto = new InvoiceDTO(t);
            if (invoice.getInvoiceItemList() != null) {
                for (InvoiceItemDTO i : invoice.getInvoiceItemList()) {
                    i.setInvoiceID(t.getInvoiceID());
                    dto.getInvoiceItemList().add(addInvoiceItem(i).getInvoiceItemList().get(0));
                }
            }
            resp.setStatusCode(0);
            resp.setMessage("Invoice added successfully");
            log.log(Level.OFF, "Invoice added");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }

        return resp;

    }

    private String getInvoiceNumber(InvoiceDTO inv) {
        StringBuilder sb = new StringBuilder();
        sb.append(inv.getCompany().getCompanyID()).append("-");
        sb.append(inv.getClient().getClientID()).append("-");
        sb.append(System.currentTimeMillis());
        return sb.toString();
    }

    public ResponseDTO addInvoiceItem(InvoiceItemDTO invoiceItem) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            InvoiceItem c = new InvoiceItem();
            c.setInvoice(em.find(Invoice.class, invoiceItem.getInvoiceID()));
            c.setNettPrice(invoiceItem.getNettPrice());
            c.setUnitPrice(invoiceItem.getUnitPrice());
            c.setQuantity(invoiceItem.getQuantity());
            c.setTax(invoiceItem.getTax());
            c.setProjectSite(em.find(ProjectSite.class, invoiceItem.getProjectSite().getProjectSiteID()));
            c.setProjectSiteTask(em.find(ProjectSiteTask.class, invoiceItem.getTask().getProjectSiteTaskID()));

            em.persist(c);
            em.flush();
            resp.getInvoiceItemList().add(new InvoiceItemDTO(c));
            log.log(Level.OFF, "Invoice item added");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }

        return resp;

    }

    public ResponseDTO addContractorClaimSite(ContractorClaimSiteDTO site) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            ContractorClaimSite c = new ContractorClaimSite();
            c.setContractorClaim(em.find(ContractorClaim.class, site.getContractorClaimID()));
            c.setProjectSite(em.find(ProjectSite.class, site.getProjectSite().getProjectSiteID()));
            em.persist(c);
            em.flush();
            resp.getContractorClaimSiteList().add(new ContractorClaimSiteDTO(c));

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }

        return resp;

    }

    private String getClaimNumber(ContractorClaimDTO cc) {
        StringBuilder sb = new StringBuilder();
        sb.append(cc.getProjectID()).append("-");
        sb.append(cc.getEngineerID()).append("-");
        sb.append(System.currentTimeMillis());
        return sb.toString();
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
            em.flush();
            if (site.getBeneficiary() != null) {
                resp = registerBeneficiary(site.getBeneficiary());
                if (resp.getStatusCode() == 0) {
                    resp = connectBeneficiaryToSite(site.getProjectSiteID(), 
                            resp.getBeneficiaryList().get(0).getBeneficiaryID());
                    ps = em.find(ProjectSite.class, ps.getProjectSiteID());
                }
                
            }
            resp.getProjectSiteList().add(new ProjectSiteDTO(ps));
            log.log(Level.OFF, "Project site registered for: {0} - {1} ",
                    new Object[]{c.getProjectName(), site.getProjectSiteName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
        }

        return resp;

    }

    public ResponseDTO registerProject(
            ProjectDTO proj) throws DataException, DataException, DataException, DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Company c = em.find(Company.class, proj.getCompanyID());
            Client cl = em.find(Client.class, proj.getClientID());
            Project project = new Project();
            project.setCompany(c);
            project.setClient(cl);
            project.setProjectName(proj.getProjectName());
            project.setDescription(proj.getDescription());
            project.setDateRegistered(new Date());
            em.persist(project);
            em.flush();
            //create first project site
            ProjectSite ps = new ProjectSite();
            ps.setProject(project);
            ps.setProjectSiteName("Project Site #1");
            em.persist(ps);
            em.flush();
            ProjectDTO dto = new ProjectDTO(project);
            dto.setProjectSiteList(new ArrayList<ProjectSiteDTO>());
            dto.getProjectSiteList().add(new ProjectSiteDTO(ps));
            resp.getProjectList().add(dto);
            log.log(Level.OFF, "Project registered for: {0} - {1} ",
                    new Object[]{c.getCompanyName(), proj.getProjectName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed\n" + getErrorString(e));
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
            em.flush();
            log.log(Level.OFF, "ID just generated : {0}", cs.getCompanyStaffID());
            //ch
            c = em.find(Company.class, staff.getCompanyID());
            log.log(Level.OFF, "checking staff: {0}", c.getCompanyStaffList().size());

            resp.setCompany(new CompanyDTO(c));
            resp.setCompanyStaff(new CompanyStaffDTO(cs));
            try {
                if (staff.getGcmDevice() != null) {
                    addDevice(staff.getGcmDevice());
                }

            } catch (DataException e) {
                log.log(Level.WARNING, "Unable to add device to GCMDevice table", e);
            }

            log.log(Level.OFF, "Company staff registered for: {0} - {1} {2}",
                    new Object[]{c.getCompanyName(), staff.getFirstName(), staff.getLastName()});
        } catch (PersistenceException e) {
            resp.setStatusCode(9);
            resp.setMessage("Duplicate email or name detected. Ignoring request");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to register staff\n" + getErrorString(e));
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
            em.flush();
            resp.getClientList().add(new ClientDTO(cli));
            log.log(Level.OFF, "######## Client registered: {0}", cli.getClientName());

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
            em.flush();
            resp.getBeneficiaryList().add(new BeneficiaryDTO(cli));
            log.log(Level.OFF, "######## Beneficiary registered: {0} {1}",
                    new Object[]{cli.getFirstName(), cli.getLastName()});

        } catch (PersistenceException e) {
            log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to register beneficiary\n" + getErrorString(e));
        }
        return resp;
    }

    public ResponseDTO connectBeneficiaryToSite(
        Integer projectSiteID, Integer beneficiaryID) throws DataException {
       ResponseDTO resp = new ResponseDTO();
        try {
            Beneficiary p = em.find(Beneficiary.class, beneficiaryID);
            ProjectSite cli = em.find(ProjectSite.class, projectSiteID);
            cli.setBeneficiary(p);
            em.merge(cli);
            log.log(Level.OFF, "######## ProjectSite Beneficiary updated");

        } catch (PersistenceException e) {
            log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to update ProjectSite Beneficiary \n" + getErrorString(e));
        }
        return resp; 
    }
    public ResponseDTO connectEngineerToProject(
        Integer projectID, Integer engineerID) throws DataException {
       ResponseDTO resp = new ResponseDTO();
        try {
            Project p = em.find(Project.class, projectID);
            Engineer eng = em.find(Engineer.class, engineerID);
            ProjectEngineer cli = new ProjectEngineer();
            cli.setEngineer(eng);
            cli.setProject(p);
            cli.setActiveFlag(null);

            em.persist(cli);
            em.flush();
            resp.getProjectEngineerList().add(new ProjectEngineerDTO(cli));
            log.log(Level.OFF, "######## ProjectEngineer registered");

        } catch (PersistenceException e) {
            log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to add ProjectEngineer\n" + getErrorString(e));
        }
        return resp; 
    }
    public ResponseDTO registerEngineer(EngineerDTO b) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Company c = em.find(Company.class, b.getCompanyID());
            Engineer cli = new Engineer();
            cli.setEngineerName(b.getEngineerName());
            cli.setCellphone(b.getCellphone());
            cli.setEmail(b.getEmail());
            cli.setCompany(c);

            em.persist(cli);
            em.flush();
            resp.getEngineerList().add(new EngineerDTO(cli));
            log.log(Level.OFF, "######## Beneficiary registered: {0} at {1}",
                    new Object[]{cli.getEngineerName(), c.getCompanyName()});

        } catch (PersistenceException e) {
            log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to register Engineer\n" + getErrorString(e));
        }
        return resp;
    }

    public ResponseDTO registerCompany(CompanyDTO company,
            CompanyStaffDTO staff, 
            double latitude, double longitude,
            ListUtil listUtil) throws DataException {
        log.log(Level.OFF, "####### * attempt to register company");
        ResponseDTO resp = new ResponseDTO();
        try {
            Company c = new Company();
            c.setCompanyName(company.getCompanyName());
            c.setAddress(company.getAddress());
            c.setVatNumber(company.getVatNumber());
            c.setTaxNumber(company.getTaxNumber());
            c.setCountry(em.find(Country.class, company.getCountryID()));
            em.persist(c);
            em.flush();

            //add operations staff - employee #1
            CompanyStaff cs = new CompanyStaff();
            cs.setCompany(c);
            cs.setFirstName(staff.getFirstName());
            cs.setCellphone(staff.getCellphone());
            cs.setEmail(staff.getEmail());
            cs.setLastName(staff.getLastName());
            cs.setPin(staff.getPin());
            cs.setCompanyStaffType(getOperationsManager());
            em.persist(cs);
            em.flush();

            //add sample data - app not empty at startup
            addInitialTaskStatus(c);
            addinitialProjectStatusType(c);
            addInitialCheckpoints(c);
            addInitialTasks(c);
            addInitialProject(c);
            // ***********************************************************
            //TODO Remove this line... or think some more
            Generator.generate(em, c.getCompanyID(), latitude, longitude);
            // ***********************************************************
            
            resp = listUtil.getCompanyData(c.getCompanyID(), company.getCountryID());
            resp.setCompanyStaff(new CompanyStaffDTO(cs));

            log.log(Level.OFF, "######## Company registered: {0}", c.getCompanyName());

        } catch (PersistenceException e) {
            log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to register company\n" + getErrorString(e));
        }

        return resp;

    }

    private void addInitialProject(Company c) {
        //client
        Client client = new Client();
        client.setClientName("Sample Client #1");
        client.setAddress("999 Sample Boulevard, SomeTown");
        client.setCellphone("999 999 9999");
        client.setCompany(c);
        client.setEmail("client@clientsite.com");
        client.setPostCode("99999");
        em.persist(client);
        em.flush();

        Project project = new Project();
        project.setCompany(c);
        project.setCompleteFlag(0);
        project.setClient(client);
        project.setDescription("This is a sample project meant to help you practice the features of the Monitor app. "
                + "This project can be removed when you are done");
        project.setProjectName("Sample Construction Project");
        project.setDateRegistered(new Date());

        em.persist(project);
        em.flush();
        //      
        ProjectSite pss1 = new ProjectSite();
        pss1.setActiveFlag(0);
        pss1.setProject(project);
        pss1.setProjectSiteName("Construction Site #1");
        pss1.setStandErfNumber("Stand #3331");
        em.persist(pss1);

        ProjectSite pss2 = new ProjectSite();
        pss2.setActiveFlag(0);
        pss2.setProject(project);
        pss2.setProjectSiteName("Construction Site #2");
        pss2.setStandErfNumber("Stand #3332");
        em.persist(pss2);

        ProjectSite pss3 = new ProjectSite();
        pss3.setActiveFlag(0);
        pss3.setProject(project);
        pss3.setProjectSiteName("Construction Site #3");
        pss3.setStandErfNumber("Stand #3333");
        em.persist(pss3);

        Query q = em.createNamedQuery("Task.findByCompany", Task.class);
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

    private void addInitialTasks(Company c) {
        Task t1 = new Task();
        t1.setTaskName("P5.0 Clear Building Site");
        t1.setTaskNumber(1);
        t1.setDescription("Preparation of site prior to commencement of construction");
        t1.setCompany(c);
        em.persist(t1);
        Task t2 = new Task();
        t2.setTaskName("P5.1 Building Foundation");
        t2.setDescription("Construction of building foundation and supports");
        t2.setTaskNumber(2);
        t2.setCompany(c);
        em.persist(t2);
        Task t3 = new Task();
        t3.setTaskName("P5.2 Walls and Stuff");
        t3.setDescription("Construct walls, windows, doors, entrance etc.");
        t3.setTaskNumber(3);
        t3.setCompany(c);
        em.persist(t3);
        Task t4 = new Task();
        t4.setTaskName("P5.3 Bulding Completion");
        t4.setDescription("Complete roofing and sundry fittings");
        t4.setTaskNumber(4);
        t4.setCompany(c);
        em.persist(t4);
        Task t5 = new Task();
        t5.setTaskName("P5.4 Site Cleanup");
        t5.setDescription("Remove construction rubble and associated debris");
        t5.setTaskNumber(5);
        t5.setCompany(c);
        em.persist(t5);
        Task t6 = new Task();
        t6.setTaskName("P5.4 Snag List Preparation");
        t6.setDescription("Prepare and document Snag List");
        t6.setTaskNumber(5);
        t6.setCompany(c);
        em.persist(t6);
        em.flush();
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
        p1.setStatusColor((short) TaskStatusDTO.STATUS_COLOR_GREEN);
        em.persist(p1);
        ProjectStatusType p2 = new ProjectStatusType();
        p2.setCompany(c);
        p2.setStatusColor((short) TaskStatusDTO.STATUS_COLOR_GREEN);
        p2.setProjectStatusName("Project is on schedule");
        em.persist(p2);
        ProjectStatusType p3 = new ProjectStatusType();
        p3.setCompany(c);
        p3.setStatusColor((short) TaskStatusDTO.STATUS_COLOR_GREEN);
        p3.setProjectStatusName("Project is complete");
        em.persist(p3);
        ProjectStatusType p4 = new ProjectStatusType();
        p4.setCompany(c);
        p4.setProjectStatusName("Project is behind schedule");
        p4.setStatusColor((short) TaskStatusDTO.STATUS_COLOR_RED);
        em.persist(p4);
        ProjectStatusType p5 = new ProjectStatusType();
        p1.setStatusColor((short) TaskStatusDTO.STATUS_COLOR_GREEN);
        p5.setCompany(c);
        p5.setProjectStatusName("Project is on budget");
        em.persist(p5);
        ProjectStatusType p6 = new ProjectStatusType();
        p6.setCompany(c);
        p1.setStatusColor((short) TaskStatusDTO.STATUS_COLOR_YELLOW);
        p6.setProjectStatusName("Project is over budget");
        em.persist(p6);

        log.log(Level.INFO, "*** Initial ProjectStatusTypes added");
    }

    private void addInitialTaskStatus(Company c) {
        TaskStatus ts1 = new TaskStatus();
        ts1.setTaskStatusName("Completed");
        ts1.setCompany(c);
        ts1.setStatusColor((short) TaskStatusDTO.STATUS_COLOR_GREEN);
        em.persist(ts1);
        TaskStatus ts2 = new TaskStatus();
        ts2.setTaskStatusName("Delayed - Weather");
        ts2.setStatusColor((short) TaskStatusDTO.STATUS_COLOR_RED);
        ts2.setCompany(c);
        em.persist(ts2);
        TaskStatus ts3 = new TaskStatus();
        ts3.setTaskStatusName("Delayed - Staff");
        ts3.setStatusColor((short) TaskStatusDTO.STATUS_COLOR_RED);
        ts3.setCompany(c);
        em.persist(ts3);
        TaskStatus ts4 = new TaskStatus();
        ts4.setTaskStatusName("Delayed - Materials");
        ts4.setStatusColor((short) TaskStatusDTO.STATUS_COLOR_RED);
        ts4.setCompany(c);
        em.persist(ts4);
        TaskStatus ts5 = new TaskStatus();
        ts5.setTaskStatusName("Not started yet");
        ts5.setStatusColor((short) TaskStatusDTO.STATUS_COLOR_YELLOW);
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

    public void removeContractorClaimSite(ContractorClaimSiteDTO site) throws DataException {
        try {
            ContractorClaimSite s = em.find(ContractorClaimSite.class, site.getContractorClaimSiteID());
            em.remove(s);
            log.log(Level.OFF, "ContractorClaimSite removed");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to remove ContractorClaimSite\n" + getErrorString(e));
        }
    }

    public void removeContractorClaim(ContractorClaimDTO claim) throws DataException {
        try {
            ContractorClaim s = em.find(ContractorClaim.class, claim.getContractorClaimID());
            em.remove(s);
            log.log(Level.OFF, "ContractorClaim removed");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to remove ContractorClaim\n" + getErrorString(e));
        }
    }

    public void removeInvoiceItem(InvoiceItemDTO i) throws DataException {
        try {
            InvoiceItem s = em.find(InvoiceItem.class, i.getInvoiceItemID());
            em.remove(s);
            log.log(Level.OFF, "InvoiceItem removed");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to remove InvoiceItem\n" + getErrorString(e));
        }
    }

    public void removeInvoice(InvoiceDTO i) throws DataException {
        try {
            Invoice s = em.find(Invoice.class, i.getInvoiceID());
            em.remove(s);
            log.log(Level.OFF, "Invoice removed");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to remove Invoice\n" + getErrorString(e));
        }
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

    public void updateProjectStatusType(ProjectStatusTypeDTO dto) throws DataException {
        try {
            ProjectStatusType ps = em.find(ProjectStatusType.class, dto.getProjectStatusTypeID());
            if (ps != null) {
                if (dto.getProjectStatusName() != null) {
                    ps.setProjectStatusName(dto.getProjectStatusName());
                }
                if (dto.getStatusColor() != null) {
                    ps.setStatusColor(dto.getStatusColor());
                }
                em.merge(ps);
                log.log(Level.INFO, "Project Status Type updated");
            }
        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update project status type\n" + getErrorString(e));
        }

    }

    public void updateTaskStatus(TaskStatusDTO dto) throws DataException {
        try {
            TaskStatus ps = em.find(TaskStatus.class, dto.getTaskStatusID());
            if (ps != null) {
                if (dto.getTaskStatusName() != null) {
                    ps.setTaskStatusName(dto.getTaskStatusName());
                }
                if (dto.getStatusColor() != null) {
                    ps.setStatusColor(dto.getStatusColor());
                }
                em.merge(ps);
                log.log(Level.INFO, "Task Status updated");
            }
        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update project\n" + getErrorString(e));
        }

    }

    public void updateTask(TaskDTO dto) throws DataException {
        try {
            Task ps = em.find(Task.class, dto.getTaskID());
            if (ps != null) {
                if (dto.getTaskName() != null) {
                    ps.setTaskName(dto.getTaskName());
                }
                if (dto.getDescription() != null) {
                    ps.setDescription(dto.getDescription());
                }
                em.merge(ps);
                log.log(Level.INFO, "Task updated");
            }
        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update project\n" + getErrorString(e));
        }

    }

    public void updateProject(ProjectDTO dto) throws DataException {
        try {
            Project ps = em.find(Project.class, dto.getProjectID());
            if (ps != null) {
                if (dto.getProjectName() != null) {
                    ps.setProjectName(dto.getProjectName());
                }
                if (dto.getDescription() != null) {
                    ps.setDescription(dto.getDescription());
                }
                em.merge(ps);
                log.log(Level.INFO, "Project updated");
            }
        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update project\n" + getErrorString(e));
        }

    }

    public void updateProjectSiteLocation(Integer projectSiteID, PhotoUpload dto) throws DataException {
        try {
            ProjectSite ps = em.find(ProjectSite.class, projectSiteID);
            if (ps != null) {
                if (ps.getAccuracy() == null) {
                    ps.setLatitude(dto.getLatitude());
                    ps.setLongitude(dto.getLongitude());
                    ps.setAccuracy(dto.getAccuracy());
                    em.merge(ps);
                    log.log(Level.INFO, "Project Site location updated, accuracy: {0}", dto.getAccuracy());
                } else {
                    if (ps.getAccuracy() > dto.getAccuracy()) {
                        ps.setLatitude(dto.getLatitude());
                        ps.setLongitude(dto.getLongitude());
                        ps.setAccuracy(dto.getAccuracy());
                        em.merge(ps);
                        log.log(Level.INFO, "Project Site location, accuracy updated, accuracy: {0}", dto.getAccuracy());
                    }
                }
            }
        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update projectSite\n" + getErrorString(e));
        }
    }

    public void updateProjectSite(ProjectSiteDTO dto) throws DataException {
        try {
            ProjectSite ps = em.find(ProjectSite.class, dto.getProjectSiteID());
            if (ps != null) {
                if (dto.getProjectSiteName() != null) {
                    ps.setProjectSiteName(dto.getProjectSiteName());
                }
                if (dto.getStandErfNumber() != null) {
                    ps.setStandErfNumber(dto.getStandErfNumber());
                }
                if (dto.getLatitude() != null) {
                    ps.setLatitude(dto.getLatitude());
                }
                if (dto.getLongitude() != null) {
                    ps.setLongitude(dto.getLongitude());
                }
                em.merge(ps);
                log.log(Level.INFO, "Project Site updated");
            }
        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update projectSite\n" + getErrorString(e));
        }
    }

    public void addErrorStore(int statusCode, String message, String origin) {
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
}
