/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.util;

import com.boha.monitor.data.Bank;
import com.boha.monitor.data.Beneficiary;
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
import com.boha.monitor.data.Invoice;
import com.boha.monitor.data.InvoiceItem;
import com.boha.monitor.data.PhotoUpload;
import com.boha.monitor.data.Project;
import com.boha.monitor.data.ProjectDiaryRecord;
import com.boha.monitor.data.ProjectSite;
import com.boha.monitor.data.ProjectSiteTask;
import com.boha.monitor.data.ProjectSiteTaskStatus;
import com.boha.monitor.data.ProjectStatusType;
import com.boha.monitor.data.Province;
import com.boha.monitor.data.Task;
import com.boha.monitor.data.TaskPrice;
import com.boha.monitor.data.TaskStatus;
import com.boha.monitor.data.Township;
import com.boha.monitor.dto.BankDTO;
import com.boha.monitor.dto.BeneficiaryDTO;
import com.boha.monitor.dto.CityDTO;
import com.boha.monitor.dto.ClientDTO;
import com.boha.monitor.dto.CompanyDTO;
import com.boha.monitor.dto.CompanyStaffDTO;
import com.boha.monitor.dto.CompanyStaffTypeDTO;
import com.boha.monitor.dto.ContractorClaimDTO;
import com.boha.monitor.dto.ContractorClaimSiteDTO;
import com.boha.monitor.dto.CountryDTO;
import com.boha.monitor.dto.EngineerDTO;
import com.boha.monitor.dto.InvoiceDTO;
import com.boha.monitor.dto.InvoiceItemDTO;
import com.boha.monitor.dto.ProjectDTO;
import com.boha.monitor.dto.ProjectDiaryRecordDTO;
import com.boha.monitor.dto.ProjectSiteDTO;
import com.boha.monitor.dto.ProjectSiteTaskDTO;
import com.boha.monitor.dto.ProjectSiteTaskStatusDTO;
import com.boha.monitor.dto.ProjectStatusTypeDTO;
import com.boha.monitor.dto.ProvinceDTO;
import com.boha.monitor.dto.TaskDTO;
import com.boha.monitor.dto.TaskPriceDTO;
import com.boha.monitor.dto.TaskStatusDTO;
import com.boha.monitor.dto.TownshipDTO;
import com.boha.monitor.dto.transfer.PhotoUploadDTO;
import com.boha.monitor.dto.transfer.ResponseDTO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author aubreyM
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ListUtil {

    @PersistenceContext
    EntityManager em;

    public ResponseDTO getCountryList() {
        ResponseDTO resp = new ResponseDTO();

        Query q = em.createNamedQuery("Country.findAll", Country.class);
        List<Country> list = q.getResultList();
        resp.setCountryList(new ArrayList<CountryDTO>());
        for (Country cp : list) {
            CountryDTO cn = new CountryDTO(cp);
            for (Province p : cp.getProvinceList()) {
                ProvinceDTO province = new ProvinceDTO(p);
                province.setCityList(new ArrayList<CityDTO>());
                for (City city : p.getCityList()) {
                    CityDTO cityDTO = new CityDTO(city);
                    cityDTO.setTownshipList(new ArrayList<TownshipDTO>());
                    for (Township ts : city.getTownshipList()) {
                        cityDTO.getTownshipList().add(new TownshipDTO(ts));
                    }
                    province.getCityList().add(cityDTO);
                }
                cn.getProvinceList().add(province);
            }
            resp.getCountryList().add(cn);
        }

        return resp;
    }

    public ProjectSite getProjectSite(Integer id) {
        ProjectSite ps = em.find(ProjectSite.class, id);
        return ps;
    }

    public ResponseDTO getContractorClaimListByProject(Integer projectID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("ContractorClaim.findByProject", ContractorClaim.class);
        q.setParameter("projectID", projectID);
        List<ContractorClaim> list = q.getResultList();
        resp.setContractorClaimList(new ArrayList<ContractorClaimDTO>());
        for (ContractorClaim cc : list) {
            resp.getContractorClaimList().add(new ContractorClaimDTO(cc));
        }

        return resp;
    }

    public ResponseDTO getBankList(Integer countryID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Bank.findByCountry", Bank.class);
        q.setParameter("countryID", countryID);
        List<Bank> list = q.getResultList();
        resp.setBankList(new ArrayList<BankDTO>());
        for (Bank bank : list) {
            resp.getBankList().add(new BankDTO(bank));
        }

        return resp;
    }

    public ResponseDTO getPhotosByProject(Integer projectID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("PhotoUpload.findProjectPhotos", PhotoUpload.class);
        q.setParameter("projectID", projectID);
        List<PhotoUpload> list = q.getResultList();
        resp.setPhotoUploadList(new ArrayList<PhotoUploadDTO>());
        for (PhotoUpload cp : list) {
            resp.getPhotoUploadList().add(new PhotoUploadDTO(cp));
        }

        return resp;
    }

    public ResponseDTO getAllPhotosByProject(Integer projectID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("PhotoUpload.findAllProjectPhotos", PhotoUpload.class);
        q.setParameter("projectID", projectID);
        List<PhotoUpload> list = q.getResultList();
        resp.setPhotoUploadList(new ArrayList<PhotoUploadDTO>());
        for (PhotoUpload cp : list) {
            resp.getPhotoUploadList().add(new PhotoUploadDTO(cp));
        }
        System.out.println("**** found project photos: "
                + resp.getPhotoUploadList().size());

        return resp;
    }

    public ResponseDTO getSiteStatus(Integer projectSiteID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("ProjectSiteTaskStatus.findByProjectSite", ProjectSiteTaskStatus.class);
        q.setParameter("projectSiteID", projectSiteID);
        List<ProjectSiteTaskStatus> statusList = q.getResultList();

        q = em.createNamedQuery("ProjectSiteTask.findByProjectSite", ProjectSiteTask.class);
        q.setParameter("projectSiteID", projectSiteID);
        List<ProjectSiteTask> taskList = q.getResultList();

        resp.setProjectSiteTaskList(new ArrayList<ProjectSiteTaskDTO>());
        for (ProjectSiteTask task : taskList) {
            ProjectSiteTaskDTO taskDTO = new ProjectSiteTaskDTO(task);
            taskDTO.setProjectSiteTaskStatusList(new ArrayList<ProjectSiteTaskStatusDTO>());
            for (ProjectSiteTaskStatus status : statusList) {
                if (Objects.equals(status.getProjectSiteTask().getProjectSiteTaskID(), taskDTO.getProjectSiteTaskID())) {
                    taskDTO.getProjectSiteTaskStatusList().add(new ProjectSiteTaskStatusDTO(status));
                }
            }
            resp.getProjectSiteTaskList().add(taskDTO);
        }

        return resp;
    }

    public ResponseDTO getPhotosByProjectSite(Integer projectSiteID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("PhotoUpload.findProjectSitePhotos", PhotoUpload.class);
        q.setParameter("projectSiteID", projectSiteID);
        List<PhotoUpload> list = q.getResultList();
        resp.setPhotoUploadList(new ArrayList<PhotoUploadDTO>());
        for (PhotoUpload cp : list) {
            resp.getPhotoUploadList().add(new PhotoUploadDTO(cp));
        }

        return resp;
    }

    public ResponseDTO getPhotosByTask(Integer projectSiteTaskID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("PhotoUpload.findTaskPhotos", PhotoUpload.class);
        q.setParameter("projectSiteTaskID", projectSiteTaskID);
        List<PhotoUpload> list = q.getResultList();
        resp.setPhotoUploadList(new ArrayList<PhotoUploadDTO>());
        for (PhotoUpload cp : list) {
            resp.getPhotoUploadList().add(new PhotoUploadDTO(cp));
        }

        return resp;
    }

    public ResponseDTO getCompanyStaffList(Integer companyID) throws DataException {
        ResponseDTO resp = new ResponseDTO();

        try {
            Query q = em.createNamedQuery("CompanyStaff.findByCompany", CompanyStaff.class);
            q.setParameter("companyID", companyID);
            List<CompanyStaff> sList = q.getResultList();
            resp.setCompanyStaffList(new ArrayList<CompanyStaffDTO>());
            for (CompanyStaff cs : sList) {
                resp.getCompanyStaffList().add(new CompanyStaffDTO(cs));
            }
            log.log(Level.OFF, "company staff found: {0}", sList.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project data\n" + getErrorString(e));
        }

        return resp;
    }

    private boolean constraintValidationsDetected(CompanyStaff entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<CompanyStaff>> constraintViolations = validator.validate(entity);
        if (constraintViolations.size() > 0) {
            Iterator<ConstraintViolation<CompanyStaff>> iterator = constraintViolations.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<CompanyStaff> cv = iterator.next();
                System.err.println(cv.getRootBeanClass().getName() + "." + cv.getPropertyPath() + " " + cv.getMessage());

            }
            return true;
        } else {
            return false;
        }
    }

    public ResponseDTO getCompanyStaffTypeList() throws DataException {
        ResponseDTO resp = new ResponseDTO();

        try {
            Query q = em.createNamedQuery("CompanyStaffType.findAll", CompanyStaffType.class);
            List<CompanyStaffType> sList = q.getResultList();
            resp.setCompanyStaffTypeList(new ArrayList<CompanyStaffTypeDTO>());
            for (CompanyStaffType cs : sList) {
                resp.getCompanyStaffTypeList().add(new CompanyStaffTypeDTO(cs));
            }
            log.log(Level.OFF, "company staff types found: {0}", sList.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project data\n" + getErrorString(e));
        }

        return resp;
    }

    public ResponseDTO getTaskStatusList(Integer companyID) throws DataException {
        ResponseDTO resp = new ResponseDTO();

        try {
            Query q = em.createNamedQuery("TaskStatus.findByCompany", TaskStatus.class);
            q.setParameter("companyID", companyID);
            List<TaskStatus> sList = q.getResultList();
            resp.setTaskStatusList(new ArrayList<TaskStatusDTO>());
            for (TaskStatus cs : sList) {
                resp.getTaskStatusList().add(new TaskStatusDTO(cs));
            }
            log.log(Level.OFF, "task status types found: {0}", sList.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project data\n" + getErrorString(e));
        }

        return resp;
    }

    public ResponseDTO getProjectStatusList(Integer companyID) throws DataException {
        ResponseDTO resp = new ResponseDTO();

        try {
            Query q = em.createNamedQuery("ProjectStatusType.findByCompany", ProjectStatusType.class);
            q.setParameter("companyID", companyID);
            List<ProjectStatusType> sList = q.getResultList();
            resp.setProjectStatusTypeList(new ArrayList<ProjectStatusTypeDTO>());
            for (ProjectStatusType cs : sList) {
                resp.getProjectStatusTypeList().add(new ProjectStatusTypeDTO(cs));
            }
            log.log(Level.OFF, "project status types found: {0}", sList.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project status list\n" + getErrorString(e));
        }

        return resp;
    }

    public ResponseDTO getDiariesByProject(Integer projectID) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Query q = em.createNamedQuery("ProjectDiaryRecord.findByProject", ProjectDiaryRecord.class);
            q.setParameter("projectID", projectID);
            List<ProjectDiaryRecord> pstList = q.getResultList();
            resp.setProjectDiaryRecordList(new ArrayList<ProjectDiaryRecordDTO>());
            for (ProjectDiaryRecord pss : pstList) {
                resp.getProjectDiaryRecordList().add(new ProjectDiaryRecordDTO(pss));
            }
            log.log(Level.INFO, "diaries found: {0}", pstList.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get tasks\n" + getErrorString(e));
        }
        return resp;
    }

    public ResponseDTO getTasksByProject(Integer projectID) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Query q = em.createNamedQuery("ProjectSiteTask.findByProject", ProjectSiteTask.class);
            q.setParameter("projectID", projectID);
            List<ProjectSiteTask> pstList = q.getResultList();
            log.log(Level.INFO, "tasks found: {0}", pstList.size());
            q = em.createNamedQuery("ProjectSiteTaskStatus.findByProject",
                    ProjectSiteTaskStatus.class);
            List<ProjectSiteTaskStatus> xList = q.getResultList();
            log.log(Level.INFO, "task status found: {0}", xList.size());
            resp.setProjectSiteTaskList(new ArrayList<ProjectSiteTaskDTO>());
            for (ProjectSiteTask projectSiteTask : pstList) {
                ProjectSiteTaskDTO task = new ProjectSiteTaskDTO(projectSiteTask);
                for (ProjectSiteTaskStatus s : xList) {
                    if (Objects.equals(s.getProjectSiteTask().getProjectSiteTaskID(), projectSiteTask.getProjectSiteTaskID())) {
                        task.getProjectSiteTaskStatusList().add(new ProjectSiteTaskStatusDTO(s));
                    }
                }
                //
                resp.getProjectSiteTaskList().add(task);
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get tasks\n" + getErrorString(e));
        }
        return resp;
    }

    public ResponseDTO getCompanyData(Integer companyID,
            Integer countryID) throws DataException {
        long s = System.currentTimeMillis();
        ResponseDTO resp = new ResponseDTO();
        CompanyDTO c = new CompanyDTO(em.find(Company.class, companyID));

        c.setCompanyStaffList(getCompanyStaffList(companyID).getCompanyStaffList());
        c.setProjectStatusTypeList(getProjectStatusList(companyID).getProjectStatusTypeList());
        c.setTaskStatusList(getTaskStatusList(companyID).getTaskStatusList());
        c.setProjectList(getProjectsByCompany(companyID));
        c.setClientList(getClientsByCompany(companyID));
        c.setTaskList(getTasksByCompany(companyID));
        c.setEngineerList(getCompanyEngineers(companyID).getEngineerList());

        resp.setCompany(c);
        resp.setCompanyStaffTypeList(getStaffTypeList());
        resp.setCountryList(getCountryList().getCountryList());
        if (countryID != null) {
            resp.setBankList(getBankList(countryID).getBankList());
        }

        Calendar cal = GregorianCalendar.getInstance();
        for (int i = 0; i < 7; i++) {
            cal.roll(Calendar.DATE, false);
        }
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        Integer xx = countCompanyTaskStatusinPeriod(companyID, cal.getTime(), new Date());
        resp.setStatusCountInPeriod(xx);

        long e = System.currentTimeMillis();
        System.out.println("getCompanyData - time elapsed: " + (e - s));
        return resp;
    }

    public ResponseDTO getCompanyEngineers(Integer companyID) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Query q = em.createNamedQuery("Engineer.findByCompany", Engineer.class);
            q.setParameter("companyID", companyID);
            List<Engineer> list = q.getResultList();
            resp.setEngineerList(new ArrayList<EngineerDTO>());
            for (Engineer e : list) {
                resp.getEngineerList().add(new EngineerDTO(e));
            }
            System.out.println("company engineers: " + resp.getEngineerList().size());
        } catch (Exception e) {

            throw new DataException("Failed");
        }

        return resp;
    }

    public List<InvoiceDTO> getInvoicesByCompany(Integer companyID) throws DataException {
        List<InvoiceDTO> invList = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("Invoice.findByCompany", Invoice.class);
            q.setParameter("companyID", companyID);
            List<Invoice> pList = q.getResultList();
            for (Invoice cc : pList) {
                invList.add(new InvoiceDTO(cc));
            }
            log.log(Level.INFO, "company invoices found: {0}", invList.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get company invoices\n" + getErrorString(e));
        }

        return invList;
    }

    public List<ContractorClaimDTO> getContractorClaimsByCompany(Integer companyID) throws DataException {
        List<ContractorClaimDTO> ccList = new ArrayList<>();
        try {
            Query q = em.createNamedQuery("ContractorClaim.findByCompany", ContractorClaim.class);
            q.setParameter("companyID", companyID);
            List<ContractorClaim> pList = q.getResultList();
            for (ContractorClaim cc : pList) {
                ccList.add(new ContractorClaimDTO(cc));
            }
            log.log(Level.INFO, "company ContractorClaims found: {0}", ccList.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get company ContractorClaims\n" + getErrorString(e));
        }

        return ccList;
    }

    public List<BeneficiaryDTO> getBeneficiariesByCompany(Integer companyID) throws DataException {
        List<BeneficiaryDTO> resp = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("Beneficiary.findByCompany", Beneficiary.class);
            q.setParameter("companyID", companyID);
            List<Beneficiary> pList = q.getResultList();
            for (Beneficiary cc : pList) {
                resp.add(new BeneficiaryDTO(cc));
            }
            log.log(Level.INFO, "company bennies found: {0}", resp.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get company beneficiaries\n" + getErrorString(e));
        }

        return resp;
    }

    public List<BeneficiaryDTO> getBeneficiariesByProject(Integer projectID) throws DataException {
        List<BeneficiaryDTO> resp = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("Beneficiary.findByProject", Beneficiary.class);
            q.setParameter("projectID", projectID);
            List<Beneficiary> pList = q.getResultList();
            for (Beneficiary cc : pList) {
                resp.add(new BeneficiaryDTO(cc));
            }
            log.log(Level.INFO, "project bennies found: {0}", resp.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project beneficiaries\n" + getErrorString(e));
        }

        return resp;
    }

    public List<CompanyStaffTypeDTO> getStaffTypeList() {
        List<CompanyStaffTypeDTO> list = new ArrayList<>();
        Query q = em.createNamedQuery("CompanyStaffType.findAll", CompanyStaffType.class);
        List<CompanyStaffType> sList = q.getResultList();
        for (CompanyStaffType companyStaffType : sList) {
            list.add(new CompanyStaffTypeDTO(companyStaffType));
        }

        return list;
    }

    public List<TaskDTO> getTasksByCompany(Integer companyID) throws DataException {
        List<TaskDTO> resp = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("Task.findByCompany", Task.class);
            q.setParameter("companyID", companyID);
            List<Task> pList = q.getResultList();

            q = em.createNamedQuery("TaskPrice.findByCompany", TaskPrice.class);
            q.setParameter("companyID", companyID);
            List<TaskPrice> tpList = q.getResultList();

            for (Task cc : pList) {
                TaskDTO dto = new TaskDTO(cc);
                dto.setTaskPriceList(new ArrayList<TaskPriceDTO>());
                for (TaskPrice tp : tpList) {
                    if (Objects.equals(dto.getTaskID(), tp.getTask().getTaskID())) {
                        dto.getTaskPriceList().add(new TaskPriceDTO(tp));
                    }
                }
                resp.add(new TaskDTO(cc));
            }
            log.log(Level.INFO, "company tasks found: {0}", resp.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get company tasks\n" + getErrorString(e));
        }

        return resp;
    }

    public List<TaskPriceDTO> getTaskPriceListByProject(Integer projectID) throws DataException {
        List<TaskPriceDTO> resp = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("TaskPrice.findByProject", TaskPrice.class);
            q.setParameter("projectID", projectID);
            List<TaskPrice> tpList = q.getResultList();
            for (TaskPrice taskPrice : tpList) {
                resp.add(new TaskPriceDTO(taskPrice));
            }

            log.log(Level.INFO, "project taskPrices found: {0}", resp.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get company tasks\n" + getErrorString(e));
        }

        return resp;
    }

    public List<ClientDTO> getClientsByCompany(Integer companyID) throws DataException {
        List<ClientDTO> resp = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("Client.findByCompany", Client.class
            );
            q.setParameter(
                    "companyID", companyID);
            List<Client> pList = q.getResultList();
            for (Client cc : pList) {
                resp.add(new ClientDTO(cc));
            }

            log.log(Level.INFO,
                    "company clients found: {0}", resp.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get company clients\n" + getErrorString(e));
        }

        return resp;
    }

    public List<ProjectDTO> getProjectsByCompany(Integer companyID) throws DataException {
        List<ProjectDTO> resp = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("Project.findActiveProjectsByCompany", Project.class
            );
            q.setParameter("companyID", companyID);
            List<Project> pList = q.getResultList();

            q = em.createNamedQuery("PhotoUpload.findProjectPhotosByCompany", PhotoUpload.class);
            q.setParameter("companyID", companyID);
            List<PhotoUpload> photos = q.getResultList();

            q = em.createNamedQuery("Beneficiary.findByCompany", Beneficiary.class);
            q.setParameter("companyID", companyID);
            List<Beneficiary> bList = q.getResultList();

            List<ContractorClaimDTO> ccList = getContractorClaimsByCompany(companyID);
            List<InvoiceDTO> invList = getInvoicesByCompany(companyID);
            List<ProjectSiteDTO> psList = getSitesByCompany(companyID);

            for (Project project : pList) {
                q = em.createNamedQuery("ProjectSiteTaskStatus.countByProject", ProjectSiteTaskStatus.class);
                q.setParameter(
                        "projectID", project.getProjectID());
                Long x = (Long) q.getSingleResult();

                ProjectDTO dto = new ProjectDTO(project);
                dto.setStatusCount(Integer.parseUnsignedInt("" + x.intValue()));
                dto.setInvoiceList(new ArrayList<InvoiceDTO>());
                dto.setBeneficiaryList(new ArrayList<BeneficiaryDTO>());
                dto.setContractorClaimList(new ArrayList<ContractorClaimDTO>());
                int benCount = 0, invCount = 0, claimCount = 0, siteCount = 0, photoCount = 0;
                for (Beneficiary b : bList) {
                    if (Objects.equals(b.getProject().getProjectID(), dto.getProjectID())) {
                        //dto.getBeneficiaryList().add(new BeneficiaryDTO(b));
                        benCount++;
                    }
                }
                for (InvoiceDTO inv : invList) {
                    if (Objects.equals(inv.getProject().getProjectID(), dto.getProjectID())) {
                        //dto.getInvoiceList().add(inv);
                        invCount++;
                    }

                }
                for (ContractorClaimDTO cc : ccList) {
                    if (Objects.equals(cc.getProjectID(), dto.getProjectID())) {
                        //dto.getContractorClaimList().add(cc);
                        claimCount++;
                    }
                }
                for (PhotoUpload px : photos) {
                    if (Objects.equals(px.getProject().getProjectID(), dto.getProjectID())) {
                        //dto.getPhotoUploadList().add(new PhotoUploadDTO(px));
                        photoCount++;
                    }
                }
                for (ProjectSiteDTO ps : psList) {
                    if (Objects.equals(ps.getProjectID(), dto.getProjectID())) {
                        //dto.getProjectSiteList().add(ps);
                        siteCount++;
                    }
                }
                dto.setBeneficiaryCount(benCount);
                dto.setContractorClaimCount(claimCount);
                dto.setSiteCount(siteCount);
                dto.setPhotoCount(photoCount);
                dto.setInvoiceCount(invCount);

                resp.add(dto);
            }

            log.log(Level.INFO,
                    "company projects found: {0}", resp.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get company projects\n" + getErrorString(e));
        }

        return resp;
    }

    public ResponseDTO getProjectData(Integer projectID) throws DataException {
        long s = System.currentTimeMillis();
        ResponseDTO resp = new ResponseDTO();

        try {
            Project p = em.find(Project.class, projectID);
            ProjectDTO project = new ProjectDTO(p);
            project.setTaskPriceList(getTaskPriceListByProject(projectID));

            Query q = em.createNamedQuery("PhotoUpload.findProjectPhotos", PhotoUpload.class);
            q.setParameter(
                    "projectID", projectID);
            List<PhotoUpload> photos = q.getResultList();
            log.log(Level.INFO,
                    "---------- photos: {0}", photos.size());
            q = em.createNamedQuery("Beneficiary.findByProject", Beneficiary.class);
            q.setParameter(
                    "projectID", projectID);
            List<Beneficiary> bList = q.getResultList();

            log.log(Level.INFO,
                    "---------- beneficiaries: {0}", bList.size());

            q = em.createNamedQuery("ContractorClaim.findByProject", ContractorClaim.class);
            q.setParameter(
                    "projectID", projectID);
            List<ContractorClaim> ccList = q.getResultList();
            log.log(Level.INFO,
                    "---------- contractorClaims: {0}", ccList.size());

            q = em.createNamedQuery("ContractorClaimSite.findByProject", ContractorClaimSite.class);
            q.setParameter(
                    "projectID", projectID);
            List<ContractorClaimSite> ccSiteList = q.getResultList();

            log.log(Level.INFO,
                    "---------- claim sites: {0}", ccSiteList.size());

            q = em.createNamedQuery("Invoice.findByProject", Invoice.class);
            q.setParameter(
                    "projectID", projectID);
            List<Invoice> invList = q.getResultList();

            log.log(Level.INFO,
                    "---------- invoices: {0}", invList.size());

            q = em.createNamedQuery("InvoiceItem.findByProject", Invoice.class);
            q.setParameter(
                    "projectID", projectID);
            List<InvoiceItem> itemList = q.getResultList();

            log.log(Level.INFO,
                    "---------- invoice items: {0}", itemList.size());

            q = em.createNamedQuery("ProjectSite.findByProject", ProjectSite.class);
            q.setParameter(
                    "projectID", projectID);
            List<ProjectSite> projectSiteList = q.getResultList();

            log.log(Level.INFO,
                    "---------- project sites: {0}", projectSiteList.size());

            q = em.createNamedQuery("ProjectSiteTask.findByProject", ProjectSiteTask.class);
            q.setParameter(
                    "projectID", projectID);
            List<ProjectSiteTask> siteTaskList = q.getResultList();
            log.log(Level.WARNING,
                    "---------- site tasks: {0}", siteTaskList.size());

            q = em.createNamedQuery("ProjectSiteTaskStatus.countByProject", ProjectSiteTaskStatus.class);
            q.setParameter(
                    "projectID", projectID);
            Long x = (Long) q.getSingleResult();
            project.setStatusCount(Integer.parseUnsignedInt("" + x.intValue()));

            log.log(Level.WARNING,
                    "---------- project task status count: {0}", x);

            q = em.createNamedQuery("ProjectSiteTaskStatus.findByProject", ProjectSiteTaskStatus.class);
            q.setParameter(
                    "projectID", projectID);
            q.setMaxResults(1);
            ProjectSiteTaskStatus lastTask = (ProjectSiteTaskStatus) q.getSingleResult();
            if (lastTask != null) {
                project.setLastStatus(new ProjectSiteTaskStatusDTO(lastTask));
            }

            project.setBeneficiaryList(new ArrayList<BeneficiaryDTO>());

            for (Beneficiary b : bList) {
                if (Objects.equals(b.getProject().getProjectID(), project.getProjectID())) {
                    project.getBeneficiaryList().add(new BeneficiaryDTO(b));
                }
            }

            log.log(Level.WARNING,
                    "###---------- bennies done: {0}", project.getBeneficiaryList().size());
            project.setInvoiceList(new ArrayList<InvoiceDTO>());
            for (Invoice inv : invList) {
                InvoiceDTO dto = new InvoiceDTO(inv);
                dto.setInvoiceItemList(new ArrayList<InvoiceItemDTO>());
                for (InvoiceItem item : itemList) {
                    dto.getInvoiceItemList().add(new InvoiceItemDTO(item));
                }
                project.getInvoiceList().add(dto);
            }

            log.log(Level.WARNING,
                    "###---------- invoices done: {0}", project.getInvoiceList().size());
            invList = null;

            project.setPhotoUploadList(new ArrayList<PhotoUploadDTO>());
            for (PhotoUpload px : photos) {
                if (px.getProjectSite() == null) {
                    project.getPhotoUploadList().add(new PhotoUploadDTO(px));
                }

            }

            log.log(Level.WARNING,
                    "###---------- proj photos done: {0}", project.getPhotoUploadList().size());
            q = em.createNamedQuery("ProjectSiteTaskStatus.countByProjectSite", ProjectSiteTaskStatus.class);
            Query q2 = em.createNamedQuery("ProjectSiteTaskStatus.findByProjectSite", ProjectSiteTaskStatus.class);

            project.setProjectSiteList(new ArrayList<ProjectSiteDTO>());
            for (ProjectSite ps : projectSiteList) {
                q.setParameter(
                        "projectSiteID", ps.getProjectSiteID());
                Long xcount = (Long) q.getSingleResult();
                q2.setParameter(
                        "projectSiteID", ps.getProjectSiteID());
                q2.setMaxResults(1);
                ProjectSiteTaskStatus taskStatus = (ProjectSiteTaskStatus) q2.getSingleResult();

                ProjectSiteDTO projectSiteDTO = new ProjectSiteDTO(ps);
                projectSiteDTO.setPhotoUploadList(new ArrayList<PhotoUploadDTO>());
                projectSiteDTO.setProjectSiteTaskList(new ArrayList<ProjectSiteTaskDTO>());
                for (PhotoUpload up : photos) {
                    if (up.getProjectSite() != null) {
                        if (Objects.equals(projectSiteDTO.getProjectSiteID(), up.getProjectSite().getProjectSiteID())) {
                            projectSiteDTO.getPhotoUploadList().add(new PhotoUploadDTO(up));
                        }
                    }
                }

                for (ProjectSiteTask pst : siteTaskList) {
                    if (Objects.equals(ps.getProjectSiteID(), pst.getProjectSite().getProjectSiteID())) {
                        ProjectSiteTaskDTO pstDTO = new ProjectSiteTaskDTO(pst);
                        projectSiteDTO.getProjectSiteTaskList().add(pstDTO);
                    }
                }

                projectSiteDTO.setStatusCount(Integer.parseUnsignedInt("" + xcount.intValue()));
                if (taskStatus != null) {
                    projectSiteDTO.setLastStatus(new ProjectSiteTaskStatusDTO(taskStatus));
                }
                project.getProjectSiteList().add(projectSiteDTO);
            }

            log.log(Level.WARNING,
                    "###---------- sites done: {0}", project.getProjectSiteList().size());
            project.setContractorClaimList(new ArrayList<ContractorClaimDTO>());

            for (ContractorClaim cc : ccList) {
                ContractorClaimDTO dto = new ContractorClaimDTO(cc);
                dto.setContractorClaimSiteList(new ArrayList<ContractorClaimSiteDTO>());
                int count = 0;
                for (ContractorClaimSite ccSite : ccSiteList) {
                    if (Objects.equals(cc.getContractorClaimID(), ccSite.getContractorClaim().getContractorClaimID())) {
                        count++;
                    }
                }
                dto.setSiteCount(count);
                project.getContractorClaimList().add(dto);
            }

            log.log(Level.WARNING,
                    "###---------- contractor claims done: {0}", project.getContractorClaimList().size());
            ccList = null;
            photos = null;
            siteTaskList = null;

            project.setBeneficiaryCount(project.getBeneficiaryList().size());
            project.setContractorClaimCount(project.getContractorClaimList().size());
            project.setSiteCount(project.getProjectSiteList().size());
            project.setPhotoCount(project.getPhotoUploadList().size());
            project.setInvoiceCount(project.getInvoiceList().size());

            Calendar cal = GregorianCalendar.getInstance();
            for (int i = 0; i < 7; i++) {
                cal.roll(Calendar.DATE, false);
            }
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);

            Integer xx = countProjectTaskStatusinPeriod(projectID, cal.getTime(), new Date());
            resp.setStatusCountInPeriod(xx);

            resp.setProjectList(new ArrayList<ProjectDTO>());
            resp.getProjectList().add(project);

            long e = System.currentTimeMillis();
            log.log(Level.INFO,
                    "############---------- project data retrieved: {0} seconds", Elapsed.getElapsed(s, e));
        } catch (OutOfMemoryError e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project data: OUT OF MEMORY!\n");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project data\n" + getErrorString(e));
        }

        return resp;
    }

    public List<ProjectSiteDTO> getSitesByCompany(Integer companyID) throws DataException {
        List<ProjectSiteDTO> list = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("ProjectSite.findByCompany", ProjectSite.class);
            q.setParameter("companyID", companyID);
            List<ProjectSite> pList = q.getResultList();
            q = em.createNamedQuery("PhotoUpload.findProjectSitePhotosByCompany", PhotoUpload.class);
            q.setParameter("companyID", companyID);

            for (ProjectSite s : pList) {
                ProjectSiteDTO dto = new ProjectSiteDTO(s);
                list.add(dto);
            }

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project data\n" + getErrorString(e));
        }

        return list;
    }

    public ResponseDTO getSitesByProject(Integer projectID) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        List<ProjectSiteDTO> list = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("ProjectSite.findByProject", ProjectSite.class
            );
            q.setParameter(
                    "projectID", projectID);
            List<ProjectSite> pList = q.getResultList();
            q = em.createNamedQuery("PhotoUpload.findProjectSitePhotosByProject", PhotoUpload.class);

            q.setParameter(
                    "projectID", projectID);
            List<PhotoUpload> photos = q.getResultList();
            List<ProjectSiteTaskDTO> pstList = getSiteTasksByProject(projectID);

            for (ProjectSite s : pList) {
                ProjectSiteDTO dto = new ProjectSiteDTO(s);
                for (ProjectSiteTaskDTO pst : pstList) {
                    if (Objects.equals(pst.getProjectSiteID(), dto.getProjectSiteID())) {
                        dto.getProjectSiteTaskList().add(pst);
                    }
                }
                for (PhotoUpload ph : photos) {
                    if (Objects.equals(ph.getProjectSite().getProjectSiteID(), dto.getProjectSiteID())) {
                        dto.getPhotoUploadList().add(new PhotoUploadDTO(ph));
                    }
                }

                list.add(dto);
            }

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project data\n" + getErrorString(e));
        }

        resp.setProjectSiteList(list);
        return resp;
    }

    public List<ProjectSiteTaskDTO> getSiteTasksByCompany(Integer companyID) throws DataException {
        List<ProjectSiteTaskDTO> list = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("ProjectSiteTask.findByCompany", ProjectSiteTask.class);
            q.setParameter("companyID", companyID);
            List<ProjectSiteTask> pList = q.getResultList();
            q = em.createNamedQuery("PhotoUpload.findSiteTaskPhotosByCompany", PhotoUpload.class);

            q.setParameter(
                    "companyID", companyID);
            List<PhotoUpload> photos = q.getResultList();
            List<ProjectSiteTaskStatusDTO> pstList = getTaskStatusByCompany(companyID);
            for (ProjectSiteTask s : pList) {
                ProjectSiteTaskDTO dto = new ProjectSiteTaskDTO(s);
                dto.setProjectSiteTaskStatusList(new ArrayList<ProjectSiteTaskStatusDTO>());
                dto.setPhotoUploadList(new ArrayList<PhotoUploadDTO>());
                for (ProjectSiteTaskStatusDTO pst : pstList) {
                    if (Objects.equals(pst.getProjectSiteTaskID(), dto.getProjectSiteTaskID())) {
                        dto.getProjectSiteTaskStatusList().add(pst);
                    }
                }
                for (PhotoUpload px : photos) {
                    if (Objects.equals(px.getProjectSiteTask().getProjectSiteTaskID(), dto.getProjectSiteTaskID())) {
                        dto.getPhotoUploadList().add(new PhotoUploadDTO(px));
                    }
                }
                list.add(dto);
            }

            log.log(Level.OFF,
                    "#### Company site tasks: {0}", list.size());

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project data\n" + getErrorString(e));
        }

        return list;
    }

    public List<ProjectSiteTaskDTO> getSiteTasksByProject(Integer projectID) throws DataException {
        List<ProjectSiteTaskDTO> list = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("ProjectSiteTask.findByProject", ProjectSiteTask.class
            );
            q.setParameter(
                    "projectID", projectID);
            List<ProjectSiteTask> pList = q.getResultList();
            q = em.createNamedQuery("PhotoUpload.findSiteTaskPhotosByProject", PhotoUpload.class);

            q.setParameter(
                    "projectID", projectID);
            List<PhotoUpload> photos = q.getResultList();
            List<ProjectSiteTaskStatusDTO> pstList = getTaskStatusByProject(projectID);
            for (ProjectSiteTask s : pList) {
                ProjectSiteTaskDTO dto = new ProjectSiteTaskDTO(s);
                dto.setProjectSiteTaskStatusList(new ArrayList<ProjectSiteTaskStatusDTO>());
                dto.setPhotoUploadList(new ArrayList<PhotoUploadDTO>());
                for (ProjectSiteTaskStatusDTO pst : pstList) {
                    if (Objects.equals(pst.getProjectSiteTaskID(), dto.getProjectSiteTaskID())) {
                        dto.getProjectSiteTaskStatusList().add(pst);
                    }
                }
                for (PhotoUpload px : photos) {
                    if (Objects.equals(px.getProjectSiteTask().getProjectSiteTaskID(), dto.getProjectSiteTaskID())) {
                        dto.getPhotoUploadList().add(new PhotoUploadDTO(px));
                    }
                }
                list.add(dto);
            }

            log.log(Level.OFF,
                    "#### Project site tasks: {0}", list.size());

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project data\n" + getErrorString(e));
        }

        return list;
    }

    public ResponseDTO getProjectTaskStatusinPeriod(Integer projectID,
            Date startDate, Date endDate) throws DataException {

        ResponseDTO resp = new ResponseDTO();
        resp.setProjectSiteTaskStatusList(new ArrayList<ProjectSiteTaskStatusDTO>());

        try {
            Query q = em.createNamedQuery("ProjectSiteTaskStatus.findByProjectInPeriod", ProjectSiteTaskStatus.class);
            q.setParameter(
                    "projectID", projectID);
            q.setParameter("startDate", startDate);
            q.setParameter("endDate", endDate);

            List<ProjectSiteTaskStatus> pList = q.getResultList();
            for (ProjectSiteTaskStatus s : pList) {
                resp.getProjectSiteTaskStatusList().add(new ProjectSiteTaskStatusDTO(s));
            }

            log.log(Level.OFF,
                    "project task status in period : {0}", resp.getProjectSiteTaskStatusList().size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project task status data\n" + getErrorString(e));
        }

        return resp;
    }
    public Integer countProjectTaskStatusinPeriod(Integer projectID,
            Date startDate, Date endDate) throws DataException {

        ResponseDTO resp = new ResponseDTO();
        resp.setProjectSiteTaskStatusList(new ArrayList<ProjectSiteTaskStatusDTO>());

        try {
            Query q = em.createNamedQuery("ProjectSiteTaskStatus.countByProjectInPeriod", ProjectSiteTaskStatus.class);
            q.setParameter(
                    "projectID", projectID);
            q.setParameter("startDate", startDate);
            q.setParameter("endDate", endDate);
            Long x = (Long)q.getSingleResult();
            Integer y = Integer.parseUnsignedInt("" + x.intValue());

            log.log(Level.OFF,
                    "project task status count in period : {0}", resp.getProjectSiteTaskStatusList().size());
            return y;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project task status data\n" + getErrorString(e));
        }

    }

    public ResponseDTO getSiteTaskStatusinPeriod(Integer projectSiteID,
            Date startDate, Date endDate) throws DataException {

        ResponseDTO resp = new ResponseDTO();
        resp.setProjectSiteTaskStatusList(new ArrayList<ProjectSiteTaskStatusDTO>());

        try {
            Query q = em.createNamedQuery("ProjectSiteTaskStatus.findByProjectSiteInPeriod", ProjectSiteTaskStatus.class);
            q.setParameter(
                    "projectSiteID", projectSiteID);
            q.setParameter("startDate", startDate);
            q.setParameter("endDate", endDate);

            List<ProjectSiteTaskStatus> pList = q.getResultList();
            for (ProjectSiteTaskStatus s : pList) {
                resp.getProjectSiteTaskStatusList().add(new ProjectSiteTaskStatusDTO(s));
            }

            log.log(Level.OFF,
                    "site task statusin period : {0}", resp.getProjectSiteTaskStatusList().size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project task status data\n" + getErrorString(e));
        }

        return resp;
    }

    public ResponseDTO getCompanyTaskStatusinPeriod(Integer companyID,
            Date startDate, Date endDate) throws DataException {

        ResponseDTO resp = new ResponseDTO();
        resp.setProjectSiteTaskStatusList(new ArrayList<ProjectSiteTaskStatusDTO>());

        try {
            Query q = em.createNamedQuery("ProjectSiteTaskStatus.findByCompanyInPeriod", ProjectSiteTaskStatus.class);
            q.setParameter(
                    "companyID", companyID);
            q.setParameter("startDate", startDate);
            q.setParameter("endDate", endDate);

            List<ProjectSiteTaskStatus> pList = q.getResultList();
            for (ProjectSiteTaskStatus s : pList) {
                resp.getProjectSiteTaskStatusList().add(new ProjectSiteTaskStatusDTO(s));
            }

            log.log(Level.OFF,
                    "Company task statusin period : {0}", resp.getProjectSiteTaskStatusList().size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get company task status data\n" + getErrorString(e));
        }

        return resp;
    }
    public Integer countCompanyTaskStatusinPeriod(Integer companyID,
            Date startDate, Date endDate) throws DataException {

        ResponseDTO resp = new ResponseDTO();
        resp.setProjectSiteTaskStatusList(new ArrayList<ProjectSiteTaskStatusDTO>());

        try {
            Query q = em.createNamedQuery("ProjectSiteTaskStatus.countByCompanyInPeriod", ProjectSiteTaskStatus.class);
            q.setParameter(
                    "companyID", companyID);
            q.setParameter("startDate", startDate);
            q.setParameter("endDate", endDate);

            Long x = (Long)q.getSingleResult();
            Integer y = Integer.parseUnsignedInt("" + x.intValue());
           
            
            log.log(Level.OFF,
                    "Company task status count in period : {0}", y);
            return y;
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get company task status data\n" + getErrorString(e));
        }

    }

    private List<ProjectSiteTaskStatusDTO> getTaskStatusByCompany(Integer companyID) throws DataException {
        List<ProjectSiteTaskStatusDTO> list = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("ProjectSiteTaskStatus.findByCompany", ProjectSiteTaskStatus.class);
            q.setParameter(
                    "companyID", companyID);
            List<ProjectSiteTaskStatus> pList = q.getResultList();
            for (ProjectSiteTaskStatus s : pList) {
                list.add(new ProjectSiteTaskStatusDTO(s));
            }

            log.log(Level.OFF,
                    "Company task status: {0}", list.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get company task status data\n" + getErrorString(e));
        }

        return list;
    }

    private List<ProjectSiteTaskStatusDTO> getTaskStatusByProject(Integer projectID) throws DataException {
        List<ProjectSiteTaskStatusDTO> list = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("ProjectSiteTaskStatus.findByProject", ProjectSiteTaskStatus.class
            );
            q.setParameter(
                    "projectID", projectID);
            List<ProjectSiteTaskStatus> pList = q.getResultList();
            for (ProjectSiteTaskStatus s : pList) {
                list.add(new ProjectSiteTaskStatusDTO(s));
            }

            log.log(Level.OFF,
                    "Project task status: {0}", list.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project task status data\n" + getErrorString(e));
        }

        return list;
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
    static final Logger log = Logger.getLogger(ListUtil.class
            .getSimpleName());
}
