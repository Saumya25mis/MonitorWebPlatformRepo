/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.util;

import com.boha.monitor.data.Bank;
import com.boha.monitor.data.Beneficiary;
import com.boha.monitor.data.CheckPoint;
import com.boha.monitor.data.City;
import com.boha.monitor.data.Client;
import com.boha.monitor.data.Company;
import com.boha.monitor.data.CompanyStaff;
import com.boha.monitor.data.CompanyStaffType;
import com.boha.monitor.data.Country;
import com.boha.monitor.data.ErrorStore;
import com.boha.monitor.data.PhotoUpload;
import com.boha.monitor.data.Project;
import com.boha.monitor.data.ProjectDiaryRecord;
import com.boha.monitor.data.ProjectSite;
import com.boha.monitor.data.ProjectSiteTask;
import com.boha.monitor.data.ProjectSiteTaskStatus;
import com.boha.monitor.data.ProjectStatusType;
import com.boha.monitor.data.Province;
import com.boha.monitor.data.Task;
import com.boha.monitor.data.TaskStatus;
import com.boha.monitor.data.Township;
import com.boha.monitor.dto.BankDTO;
import com.boha.monitor.dto.BeneficiaryDTO;
import com.boha.monitor.dto.CheckPointDTO;
import com.boha.monitor.dto.CityDTO;
import com.boha.monitor.dto.ClientDTO;
import com.boha.monitor.dto.CompanyDTO;
import com.boha.monitor.dto.CompanyStaffDTO;
import com.boha.monitor.dto.CompanyStaffTypeDTO;
import com.boha.monitor.dto.CountryDTO;
import com.boha.monitor.dto.ProjectDTO;
import com.boha.monitor.dto.ProjectDiaryRecordDTO;
import com.boha.monitor.dto.ProjectSiteDTO;
import com.boha.monitor.dto.ProjectSiteTaskDTO;
import com.boha.monitor.dto.ProjectSiteTaskStatusDTO;
import com.boha.monitor.dto.ProjectStatusTypeDTO;
import com.boha.monitor.dto.ProvinceDTO;
import com.boha.monitor.dto.TaskDTO;
import com.boha.monitor.dto.TaskStatusDTO;
import com.boha.monitor.dto.TownshipDTO;
import com.boha.monitor.dto.transfer.PhotoUploadDTO;
import com.boha.monitor.dto.transfer.ResponseDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
        for (Country cp : list) {
            CountryDTO cn = new CountryDTO(cp);
            for (Province p : cp.getProvinceList()) {
                ProvinceDTO province = new ProvinceDTO(p);
                for (City city : p.getCityList()) {
                    CityDTO cityDTO = new CityDTO(city);
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
    
    public ResponseDTO getBankList(Integer countryID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Bank.findByCountry", Bank.class);
        q.setParameter("countryID", countryID);
        List<Bank> list = q.getResultList();
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
        for (PhotoUpload cp : list) {
            resp.getPhotoUploadList().add(new PhotoUploadDTO(cp));
        }
        System.out.println("**** found project photos: "
                + resp.getPhotoUploadList().size());

        return resp;
    }

    public ResponseDTO getPhotosByProjectSite(Integer projectSiteID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("PhotoUpload.findProjectSitePhotos", PhotoUpload.class);
        q.setParameter("projectSiteID", projectSiteID);
        List<PhotoUpload> list = q.getResultList();
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
        for (PhotoUpload cp : list) {
            resp.getPhotoUploadList().add(new PhotoUploadDTO(cp));
        }

        return resp;
    }

    public ResponseDTO getCheckPointList(Integer companyID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("CheckPoint.findByCompany", CheckPoint.class);
        q.setParameter("companyID", companyID);
        List<CheckPoint> list = q.getResultList();
        for (CheckPoint cp : list) {
            resp.getCheckPointList().add(new CheckPointDTO(cp));
        }

        return resp;
    }


    public ResponseDTO getCompanyStaffList(Integer companyID) throws DataException {
        ResponseDTO resp = new ResponseDTO();

        try {
            Query q = em.createNamedQuery("CompanyStaff.findByCompany", CompanyStaff.class);
            q.setParameter("companyID", companyID);
            List<CompanyStaff> sList = q.getResultList();
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

    public ResponseDTO getCompanyStaffTypeList() throws DataException {
        ResponseDTO resp = new ResponseDTO();

        try {
            Query q = em.createNamedQuery("CompanyStaffType.findAll", CompanyStaffType.class);
            List<CompanyStaffType> sList = q.getResultList();
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

    public ResponseDTO getProjectData(Integer projectID) throws DataException {
        ResponseDTO resp = new ResponseDTO();

        try {
            Project p = em.find(Project.class, projectID);
            ProjectDTO pDTO = new ProjectDTO(p);
            Query q = em.createNamedQuery("ProjectSite.findByProject",
                    ProjectSite.class);
            q.setParameter("projectID", projectID);
            List<ProjectSite> pList = q.getResultList();
            
            ResponseDTO resp1 = getTasksByProject(projectID);
            
            resp.setProjectDiaryRecordList(getDiariesByProject(projectID).getProjectDiaryRecordList());
           
            for (ProjectSite site : pList) {
                ProjectSiteDTO s = new ProjectSiteDTO(site);
                for (ProjectSiteTaskDTO task : resp1.getProjectSiteTaskList()) {
                    if (Objects.equals(task.getProjectSiteID(), s.getProjectSiteID())) {
                        s.getProjectSiteTaskList().add(task);
                    }
                }

            }

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project data\n" + getErrorString(e));
        }

        return resp;
    }

    public ResponseDTO getDiariesByProject(Integer projectID) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Query q = em.createNamedQuery("ProjectDiaryRecord.findByProject", ProjectDiaryRecord.class);
            q.setParameter("projectID", projectID);
            List<ProjectDiaryRecord> pstList = q.getResultList();

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

    public ResponseDTO getCompanyDataX(Integer companyID) throws DataException {
        long s = System.currentTimeMillis();
        ResponseDTO resp = new ResponseDTO();
        CompanyDTO c = new CompanyDTO(em.find(Company.class, companyID));
        resp.setCompany(c);
        resp.setCompanyStaffTypeList(getStaffTypeList());
        resp.setCountryList(getCountryList().getCountryList());

        long e = System.currentTimeMillis();
        System.out.println("getCompanyDataX - time elapsed: " + (e - s));
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
        c.setCheckPointList(getCheckPointList(companyID).getCheckPointList());
        c.setBeneficiaryList(getBeneficiariesByCompany(companyID));

        resp.setCompany(c);
        resp.setCompanyStaffTypeList(getStaffTypeList());
        resp.setCountryList(getCountryList().getCountryList());
        if (countryID != null) {
        resp.setBankList(getBankList(countryID).getBankList());
        }
        long e = System.currentTimeMillis();
        System.out.println("getCompanyData - time elapsed: " + (e - s));
        return resp;
    }

    public List<BeneficiaryDTO> getInvoicesByCompany(Integer companyID) throws DataException {
        List<BeneficiaryDTO> resp = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("Invoice.findByCompany", Beneficiary.class);
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
            for (Task cc : pList) {
                resp.add(new TaskDTO(cc));
            }
            log.log(Level.INFO, "company tasks found: {0}", resp.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get company taskss\n" + getErrorString(e));
        }

        return resp;
    }

    public List<ClientDTO> getClientsByCompany(Integer companyID) throws DataException {
        List<ClientDTO> resp = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("Client.findByCompany", Client.class);
            q.setParameter("companyID", companyID);
            List<Client> pList = q.getResultList();
            for (Client cc : pList) {
                resp.add(new ClientDTO(cc));
            }
            log.log(Level.INFO, "company clients found: {0}", resp.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get company clients\n" + getErrorString(e));
        }

        return resp;
    }

    public List<ProjectDTO> getProjectsByCompany(Integer companyID) throws DataException {
        List<ProjectDTO> resp = new ArrayList<>();

        try {
            Query q = em.createNamedQuery("Project.findByCompany", Project.class);
            q.setParameter("companyID", companyID);
            List<Project> pList = q.getResultList();
            q = em.createNamedQuery("PhotoUpload.findProjectPhotosByCompany", PhotoUpload.class);
            q.setParameter("companyID", companyID);
            List<PhotoUpload> photos = q.getResultList();
            List<ProjectSiteDTO> psList = getSitesByCompany(companyID);
            for (Project project : pList) {
                ProjectDTO dto = new ProjectDTO(project);
                for (PhotoUpload px : photos) {
                    if (Objects.equals(px.getProject().getProjectID(), dto.getProjectID())) {
                        dto.getPhotoUploadList().add(new PhotoUploadDTO(px));
                    }
                }
                for (ProjectSiteDTO ps : psList) {
                    if (Objects.equals(ps.getProjectID(), dto.getProjectID())) {
                        dto.getProjectSiteList().add(ps);
                    }
                }
                resp.add(dto);
            }
            log.log(Level.INFO, "company projects found: {0}", resp.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get company projects\n" + getErrorString(e));
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
            List<PhotoUpload> photos = q.getResultList();
            List<ProjectSiteTaskDTO> pstList = getSiteTasksByCompany(companyID);

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

        return list;
    }

    public List<ProjectSiteTaskDTO> getSiteTasksByCompany(Integer companyID) throws DataException {
        List<ProjectSiteTaskDTO> list = new ArrayList<>();
        try {
            Query q = em.createNamedQuery("ProjectSiteTask.findByCompany", ProjectSiteTask.class);
            q.setParameter("companyID", companyID);
            List<ProjectSiteTask> pList = q.getResultList();
            q = em.createNamedQuery("PhotoUpload.findSiteTaskPhotosByCompany", PhotoUpload.class);
            q.setParameter("companyID", companyID);
            List<PhotoUpload> photos = q.getResultList();
            List<ProjectSiteTaskStatusDTO> pstList = getTaskStatusByCompany(companyID);
            for (ProjectSiteTask s : pList) {
                ProjectSiteTaskDTO dto = new ProjectSiteTaskDTO(s);
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
            log.log(Level.OFF, "#### Company site tasks: {0}", list.size());

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get project data\n" + getErrorString(e));
        }

        return list;
    }

    private List<ProjectSiteTaskStatusDTO> getTaskStatusByCompany(Integer companyID) throws DataException {
        List<ProjectSiteTaskStatusDTO> list = new ArrayList<>();
        try {
            Query q = em.createNamedQuery("ProjectSiteTaskStatus.findByCompany", ProjectSiteTaskStatus.class);
            q.setParameter("companyID", companyID);
            List<ProjectSiteTaskStatus> pList = q.getResultList();
            for (ProjectSiteTaskStatus s : pList) {
                list.add(new ProjectSiteTaskStatusDTO(s));
            }

            log.log(Level.OFF, "Company task status: {0}", list.size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to get company task status data\n" + getErrorString(e));
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
    static final Logger log = Logger.getLogger(ListUtil.class.getSimpleName());
}
