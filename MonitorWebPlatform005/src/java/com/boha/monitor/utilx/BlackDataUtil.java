/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.utilx;

import com.boha.monitor.data.CompanyExperience;
import com.boha.monitor.data.ExperienceType;
import com.boha.monitor.data.Invoice;
import com.boha.monitor.data.InvoiceRejectReason;
import com.boha.monitor.data.Project;
import com.boha.monitor.data.RejectedInvoice;
import com.boha.monitor.data.TenderCompany;
import com.boha.monitor.data.TenderCompanyProject;
import com.boha.monitor.dto.CompanyExperienceDTO;
import com.boha.monitor.dto.InvoiceDTO;
import com.boha.monitor.dto.RejectedInvoiceDTO;
import com.boha.monitor.dto.TenderCompanyDTO;
import com.boha.monitor.dto.TenderCompanyProjectDTO;
import com.boha.monitor.dto.transfer.ResponseDTO;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Utility class for TenderCompany monitoring
 * @author aubreymalabie
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BlackDataUtil {
    static final Logger log = Logger.getLogger("BlackUtil");
    @PersistenceContext
    EntityManager em;
    
    public ResponseDTO addTenderCompany(TenderCompanyDTO co) throws DataException {
        ResponseDTO w = new ResponseDTO();
        try {
            TenderCompany tc = new TenderCompany();
            tc.setName(co.getName());
            tc.setCellphone(co.getCellphone());
            tc.setEmail(co.getEmail());
            tc.setDateRegistered(new Date());
            em.persist(tc);
            em.flush();
            w.getTenderCompanyList().add(new TenderCompanyDTO(tc));
            log.log(Level.SEVERE, "Tender Company added: {0}", co.getName());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed",e);
            throw new DataException("");
        }
        return w;
    }
    public ResponseDTO addTenderCompanyProjects(List<Integer> projectList, Integer tenderCompanyID) throws DataException {
        ResponseDTO w = new ResponseDTO();
        try {
            TenderCompany tc = em.find(TenderCompany.class, tenderCompanyID);
            for (Integer id : projectList) {
                TenderCompanyProject tcp = new TenderCompanyProject();
                tcp.setTenderCompany(tc);
                tcp.setProject(em.find(Project.class, id));
                em.persist(tcp);
                em.flush();
                w.getTenderCompanyProjectList().add(new TenderCompanyProjectDTO());
            }
            
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed",e);
            throw new DataException("");
        }
        
        
        return w;
    }
    public ResponseDTO addInvoice(InvoiceDTO i) throws DataException {
        ResponseDTO w = new ResponseDTO();
        try {
            TenderCompany tc = em.find(TenderCompany.class, i.getTenderCompanyID());
            Invoice inv = new Invoice();
            inv.setTenderCompany(tc);
            inv.setAmount(i.getAmount());
            inv.setDaysEarly(i.getDaysEarly());
            inv.setDaysLate(i.getDaysLate());
            inv.setInvoiceNumber(i.getInvoiceNumber());
            if (i.getPaymentDate() != null) {
               inv.setPaymentDate(new Date(i.getPaymentDate()));
            }
            inv.setMonth(i.getMonth());
            inv.setYear(i.getYear());
            em.persist(inv);
            em.flush();
            w.getInvoiceList().add(new InvoiceDTO(inv));
        } catch (Exception e) {
            throw new DataException("");
        }
        
        
        return w;
    }
    public ResponseDTO addRejectedInvoice(RejectedInvoiceDTO invoice) throws DataException {
        ResponseDTO w = new ResponseDTO();
        try {
            Invoice inv = em.find(Invoice.class, invoice.getInvoiceID());
            RejectedInvoice r = new RejectedInvoice();
            r.setInvoice(inv);
            r.setDateRegistered(new Date());
            r.setInvoiceRejectReason(em.find(
                    InvoiceRejectReason.class, 
                    invoice.getInvoiceRejectReason().getInvoiceRejectReasonID()));
            em.persist(r);
            em.flush();
            w.getRejectedInvoiceList().add(new RejectedInvoiceDTO(r));
            log.log(Level.INFO, "Rejected invoice added");
        } catch (Exception e) {
            throw new DataException("");
        }
        
        
        return w;
    }
    public ResponseDTO addCompanyExperience(CompanyExperienceDTO experience) throws DataException {
        ResponseDTO w = new ResponseDTO();
        try {
            TenderCompany tc = em.find(TenderCompany.class, experience.getTenderCompanyID());
            CompanyExperience cx = new CompanyExperience();
            cx.setTenderCompany(tc);
            cx.setExperienceDate(new Date(experience.getExperienceDate()));
            cx.setExperienceType(em.find(ExperienceType.class, experience.getExperienceType().getExperienceTypeID()));
            em.persist(cx);
            em.flush();
            w.getCompanyExperienceList().add(new CompanyExperienceDTO(cx));
            log.log(Level.INFO, "CompanyExperience added: {0}", experience.getExperienceType().getName());
        } catch (Exception e) {
            throw new DataException("");
        }
        
        
        return w;
    }
    
}
