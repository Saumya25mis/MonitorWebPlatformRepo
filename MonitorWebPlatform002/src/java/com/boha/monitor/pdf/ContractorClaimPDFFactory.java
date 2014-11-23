/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.pdf;

import com.boha.monitor.data.Beneficiary;
import com.boha.monitor.data.ContractorClaim;
import com.boha.monitor.data.ContractorClaimSite;
import com.boha.monitor.dto.transfer.ResponseDTO;
import com.boha.monitor.util.MonitorProperties;
import com.boha.monitor.util.PDFException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Factory class with methods to produce ContractorClaim Forms
 *
 * @author aubreyM
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ContractorClaimPDFFactory {

    @PersistenceContext
    EntityManager em;

    public ResponseDTO getContractorClaimPDF(
            Integer contractorClaimID) throws PDFException {
        ResponseDTO resp = new ResponseDTO();
        ContractorClaim cc = em.find(ContractorClaim.class, contractorClaimID);
        Query q = em.createNamedQuery("ContractorClaimSite.findByClaim", ContractorClaimSite.class);
        q.setParameter("contractorClaimID", contractorClaimID);
        cc.setContractorClaimSiteList(q.getResultList());
        log.log(Level.OFF, "Sites found for claim: {0}", cc.getContractorClaimSiteList().size());
        Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);
        document.addAuthor(cc.getProject().getCompany().getCompanyName());
        document.addTitle("Contractor Claim Form");

        File file = PDFUtil.getContractorClaimFile(cc);

        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream(file));
            document.open();
            document.add(getMainPage(cc));
            if (cc.getContractorClaimSiteList().size() > FIRST_PAGE_SIZE) {
                document.newPage();
                document.add(getNextPageSites(cc, FIRST_PAGE_SIZE));
                document.newPage();
                document.add(getClaimStamps(cc, false));
            }
            document.close();
            resp.setMessage("ContractorClaim PDF generated: " + file.getAbsolutePath());
            log.log(Level.INFO, "ContractorClaim PDF generated: {0}", file.getAbsolutePath());
        } catch (BadElementException ex) {
            log.log(Level.SEVERE, "PDF Generation failed", ex);
            throw new PDFException();
        } catch (FileNotFoundException | DocumentException ex) {
            log.log(Level.SEVERE, "PDF Generation failed", ex);
            throw new PDFException();
        } 

        return resp;
    }

    /**
     * Main table containing the overall structure of the ContractorClaim Form
     *
     * @param claim
     * @return PdfPTable
     * @throws PDFException
     */
    public PdfPTable getMainPage(ContractorClaim claim) throws PDFException {
        int[] widths = {600, 200};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(2, widths);
            PdfPCell c1 = new PdfPCell(getClaimDetail(claim));
            PdfPCell c2 = new PdfPCell(getClaimStamps(claim, true));
            c1.setBorder(Rectangle.NO_BORDER);
            c2.setBorder(Rectangle.NO_BORDER);
            t.addCell(c1);
            t.addCell(c2);
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }

        return t;
    }

    /**
     * Details of the Contractor Claim Form - header + sites
     *
     * @param claim
     * @return PdfPTable
     * @throws PDFException
     */
    private PdfPTable getClaimDetail(ContractorClaim claim) throws PDFException {
        int[] widths = {800};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(1, widths);
            PdfPCell c1 = new PdfPCell(getHeader(claim));
            PdfPCell c1a = new PdfPCell(getClaimHeader(claim));
            PdfPCell c2 = new PdfPCell(getFormTitle(claim));
            PdfPCell c3 = new PdfPCell(getNote());
            PdfPCell c4 = new PdfPCell(getMilestone(claim));
            PdfPCell c5 = new PdfPCell(getFirstPageSites(claim));
            c1.setBorder(Rectangle.NO_BORDER);
            c1a.setBorder(Rectangle.NO_BORDER);
            c2.setBorder(Rectangle.NO_BORDER);
            c3.setBorder(Rectangle.NO_BORDER);
            c4.setBorder(Rectangle.NO_BORDER);
            c5.setBorder(Rectangle.NO_BORDER);
            t.addCell(c1);
            t.addCell(c1a);
            t.addCell(c2);
            t.addCell(c3);
            t.addCell(c4);
            t.addCell(c5);

        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }

        return t;
    }

    /**
     * Details of Contractor Claim - Company, Engineer, Claim # and date
     *
     * @param cc
     * @return PdfPTable
     * @throws PDFException
     */
    private PdfPTable getFormTitle(ContractorClaim cc) throws PDFException {
        int[] widths = {150, 500};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(2, widths);
            t.setSpacingBefore(10f);
            PdfPCell c1 = new PdfPCell(PDFUtil.setBlack8(new Phrase("Contractor")));

            PdfPCell c2 = new PdfPCell(PDFUtil.setBoldBlack14(new Phrase(cc.getProject().getCompany().getCompanyName())));
            PdfPCell c3 = new PdfPCell(PDFUtil.setBlack8(new Phrase("Project Name/No")));
            PdfPCell c4 = new PdfPCell(PDFUtil.setBoldBlack10(new Phrase(cc.getProject().getProjectName())));
            PdfPCell c5 = new PdfPCell(PDFUtil.setBlack8(new Phrase("Engineer")));
            PdfPCell c6 = new PdfPCell(PDFUtil.setBoldBlue10(new Phrase(cc.getProjectEngineer().getEngineer().getEngineerName())));
            //PdfPCell c7 = new PdfPCell(PDFUtil.setBoldBlack14(new Phrase("Claim No. " + cc.getClaimNumber())));
            //PdfPCell c8 = new PdfPCell(PDFUtil.setBoldBlue(new Phrase("Date: " + sdf.format(cc.getClaimDate()))));

            c2.setPaddingLeft(10.0f);
            c2.setPaddingBottom(4.0f);
            c4.setPaddingLeft(10.0f);
            c4.setPaddingBottom(4.0f);
            c6.setPaddingLeft(10.0f);
            c6.setPaddingBottom(4.0f);
            
            c1.setBorder(Rectangle.NO_BORDER);
            c1.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
            c3.setBorder(Rectangle.NO_BORDER);
            c3.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
            c5.setBorder(Rectangle.NO_BORDER);
            c5.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
            
            c1.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);

            c6.setGrayFill(0.95f);
            t.addCell(c1);
            t.addCell(c2);
            t.addCell(c3);
            t.addCell(c4);
            t.addCell(c5);
            t.addCell(c6);
            //t.addCell(c7);
            //t.addCell(c8);

        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }

        return t;
    }
    static final SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");

    private PdfPTable getNote() throws PDFException {
        Phrase p = new Phrase("Payment Certificate: This is to certify that the site numbers below"
                + " have been inspected by the NHBRC, MUNICIPAL BUILDING INSPECTOR, HS HOUSING TECNICIANS"
                + "(inspectors) and the contractor/Engineer");

        PDFUtil.setBlack8(p);
        int[] widths = {600};
        PdfPTable t = null;

        try {
            t = PDFUtil.getTable(1, widths);
            t.setSpacingBefore(4f);
            PdfPCell c1 = new PdfPCell(p);
            c1.setBorder(Rectangle.NO_BORDER);
            c1.setPadding(6.0f);
            t.addCell(c1);

        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }

        return t;
    }

    private PdfPTable getMilestone(ContractorClaim cc) throws PDFException {
        int[] widths = {600};
        PdfPTable t = null;

        try {
            t = PDFUtil.getTable(1, widths);
            Phrase p = new Phrase("MILESTONE: " + cc.getTask().getTaskName());
            
            PdfPCell c1 = new PdfPCell(PDFUtil.setBoldBlue12(p));
            c1.setVerticalAlignment(Rectangle.ALIGN_CENTER);
            c1.setPaddingTop(4f);
            c1.setPaddingBottom(4f);
            c1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
            
            t.addCell(c1);

        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }

        return t;
    }

    static final int FIRST_PAGE_SIZE = 25, NEXT_PAGE_SIZE = 40;

    private PdfPTable getFirstPageSites(ContractorClaim claim) throws PDFException {
        int[] widths = {200, 200, 200, 40};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(4, widths);
            //PdfPCell c0 = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase("NO.")));
            PdfPCell c1 = new PdfPCell(PDFUtil.setBoldBlack10(new Phrase("IDENTITY NO.")));
            PdfPCell c2 = new PdfPCell(PDFUtil.setBoldBlack10(new Phrase("SURNAME, INITIALS")));
            PdfPCell c3 = new PdfPCell(PDFUtil.setBoldBlack10(new Phrase("SITE NO.")));
            PdfPCell c4 = new PdfPCell(PDFUtil.setBoldBlack10(new Phrase(" ")));
            //c0.setBorderWidth(1.0f);
            c1.setBorderWidth(1.0f);
            c2.setBorderWidth(1.0f);
            c3.setBorderWidth(1.0f);
            c4.setBorderWidth(1.0f);
            c1.setPadding(6f);
            c2.setPadding(6f);
            c3.setPadding(6f);
            c4.setPadding(6f);
            //t.addCell(c0);
            t.addCell(c1);
            t.addCell(c2);
            t.addCell(c3);
            t.addCell(c4);
            int index = 0;

            for (ContractorClaimSite site : claim.getContractorClaimSiteList()) {
                PdfPCell c1x = null, c2x = null, c3x = null, c4x = null, c0x = null;
                //c0x = new PdfPCell(new Phrase("" + (index + 1)));
                Beneficiary b = site.getProjectSite().getBeneficiary();
                if (b != null) {
                    c1x = new PdfPCell(PDFUtil.setBlack8(new Phrase(b.getIDNumber())));
                    StringBuilder sb = new StringBuilder();
                    sb.append(b.getLastName()).append(" ");
                    sb.append(b.getFirstName().substring(0, 1));
                    if (b.getMiddleName() != null) {
                        sb.append(b.getMiddleName().substring(0, 1));
                    }
                    c2x = new PdfPCell(PDFUtil.setBlack8(new Phrase(sb.toString())));

                } else {
                    c1x = new PdfPCell(PDFUtil.setBoldRed12(new Phrase("***")));
                    c2x = new PdfPCell(PDFUtil.setBoldRed12(new Phrase("***")));

                }

                c3x = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase(site.getProjectSite().getProjectSiteName())));
                c4x = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase(" ")));

                int x = index % 2;
                if (x > 0) {
                    //c0x.setGrayFill(0.95f);
                    c1x.setGrayFill(0.95f);
                    c2x.setGrayFill(0.95f);
                    c3x.setGrayFill(0.95f);
                    c4x.setGrayFill(0.95f);
                }
                // t.addCell(c0x);
                c1x.setPaddingLeft(6f);
                c2x.setPaddingLeft(6f);
                c3x.setPaddingLeft(6f);
                c4x.setPaddingLeft(6f);
                c1x.setPaddingBottom(2f);
                c2x.setPaddingBottom(2f);
                c3x.setPaddingBottom(2f);
                c4x.setPaddingBottom(2f);

                t.addCell(c1x);
                t.addCell(c2x);
                t.addCell(c3x);
                t.addCell(c4x);
                index++;
                if (index > FIRST_PAGE_SIZE) {
                    break;
                }

            }
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }

        return t;
    }

    private PdfPTable getNextPageSites(ContractorClaim claim, int startIndex) throws PDFException {
        int[] widths = {200, 200, 200, 40};
        int[] mwidths = {80, 20};
        PdfPTable mainTable = null;
        PdfPTable t = null;
        try {
            mainTable = PDFUtil.getTable(2, mwidths);
            t = PDFUtil.getTable(4, widths);
            //PdfPCell c0 = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase("NO.")));
            PdfPCell c1 = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase("IDENTITY NO.")));
            PdfPCell c2 = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase("SURNAME")));
            PdfPCell c3 = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase("SITE NO.")));
            PdfPCell c4 = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase(" ")));
            StringBuilder sb = new StringBuilder();
            sb.append(claim.getProject().getProjectName());
            sb.append(" - Claim No. ").append(claim.getClaimNumber());
            PdfPCell z1 = new PdfPCell(PDFUtil.setBoldBlack10(new Phrase(sb.toString())));
            z1.setPadding(1f);
            z1.setGrayFill(0.95f);
            z1.setColspan(4);
            z1.setPaddingLeft(20f);
            z1.setPaddingBottom(4f);
            z1.setBorder(Rectangle.NO_BORDER);
            PdfPCell z2 = new PdfPCell(new Phrase("                 "));
            z2.setColspan(4);
            z2.setBorder(Rectangle.NO_BORDER);

            //c0.setBorderWidth(1.0f);
            c1.setBorderWidth(1.0f);
            c2.setBorderWidth(1.0f);
            c3.setBorderWidth(1.0f);
            c4.setBorderWidth(1.0f);
            c1.setPadding(6f);
            c2.setPadding(6f);
            c3.setPadding(6f);
            c4.setPadding(6f);
            //
            t.addCell(z1);
            t.addCell(z2);
            t.addCell(z1);
            t.addCell(c1);
            t.addCell(c2);
            t.addCell(c3);
            t.addCell(c4);
            t.setHeaderRows(3);
            t.setFooterRows(1);
            int index = 0, numberOfPages = 0;
            int rem = (claim.getContractorClaimSiteList().size() - FIRST_PAGE_SIZE) % NEXT_PAGE_SIZE;
            numberOfPages = (claim.getContractorClaimSiteList().size() - FIRST_PAGE_SIZE) / NEXT_PAGE_SIZE;
            if (rem > 0) {
                numberOfPages++;
            }

            for (int i = startIndex; i < claim.getContractorClaimSiteList().size(); i++) {

                ContractorClaimSite site = claim.getContractorClaimSiteList().get(i);
                PdfPCell c1x = null, c2x = null, c3x = null, c4x = null, c0x = null;
                //c0x = new PdfPCell(PDFUtil.setBlack8(new Phrase("" + (startIndex + i + 1))));
                Beneficiary b = site.getProjectSite().getBeneficiary();
                if (b != null) {
                    c1x = new PdfPCell(PDFUtil.setBlack8(new Phrase(b.getIDNumber())));
                    sb = new StringBuilder();
                    sb.append(b.getLastName()).append(" ");
                    sb.append(b.getFirstName().substring(0, 1));
                    if (b.getMiddleName() != null) {
                        sb.append(b.getMiddleName().substring(0, 1));
                    }
                    c2x = new PdfPCell(PDFUtil.setBlack8(new Phrase(sb.toString())));

                } else {
                    c1x = new PdfPCell(PDFUtil.setBoldRed12(new Phrase("***")));
                    c2x = new PdfPCell(PDFUtil.setBoldRed12(new Phrase("***")));

                }

                c3x = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase(site.getProjectSite().getProjectSiteName())));
                c4x = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase(" ")));

                int x = index % 2;
                if (x > 0) {
                    //c0x.setGrayFill(0.95f);
                    c1x.setGrayFill(0.95f);
                    c2x.setGrayFill(0.95f);
                    c3x.setGrayFill(0.95f);
                    c4x.setGrayFill(0.95f);
                }
                c1x.setPaddingLeft(6f);
                c2x.setPaddingLeft(6f);
                c3x.setPaddingLeft(6f);
                c4x.setPaddingLeft(6f);
                c1x.setPaddingBottom(4f);
                c2x.setPaddingBottom(4f);
                c3x.setPaddingBottom(4f);
                c4x.setPaddingBottom(4f);

                t.addCell(c1x);
                t.addCell(c2x);
                t.addCell(c3x);
                t.addCell(c4x);
                index++;

            }
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }

        PdfPCell a1 = new PdfPCell(t);
        PdfPCell a2 = new PdfPCell(getClaimStamps(claim, true));
        a1.setBorder(Rectangle.NO_BORDER);
        a2.setBorder(Rectangle.NO_BORDER);
        mainTable.addCell(a1);
        mainTable.addCell(a2);

        return mainTable;
    }

    private PdfPTable getHeader(ContractorClaim claim) throws PDFException {
        int[] widths = {20, 200, 40};
        PdfPTable t = null;
        File logoFile = null;
        try {
            File dir = MonitorProperties.getImageDir();
            File companyDir = new File(dir, "company" + claim.getProject().getCompany().getCompanyID());
            File logoDir = new File(companyDir, "client_logos");
            logoFile = new File(logoDir, "logo.png");
        } catch (Exception e) {

        }
        try {
            t = PDFUtil.getTable(3, widths);
            PdfPCell logoCell = null;
            if (logoFile != null && logoFile.exists()) {
                logoCell = new PdfPCell(Image.getInstance(logoFile.getAbsolutePath()));
            } else {
                logoCell = new PdfPCell(new Phrase(" "));
            }
            PdfPCell cell1 = new PdfPCell(PDFUtil.getHeaderTitle16(claim.getProject().getClient().getClientName()));
            PdfPCell cell2 = new PdfPCell(PDFUtil.getHeaderTitle16("HS/A1"));
            PdfPCell cell3 = new PdfPCell(PDFUtil.getHeaderTitle18("CONTRACTOR CLAIM FORM"));
            
            
            cell1.setVerticalAlignment(Rectangle.ALIGN_CENTER);
            cell2.setVerticalAlignment(Rectangle.ALIGN_CENTER);
            cell3.setVerticalAlignment(Rectangle.ALIGN_CENTER);
            cell3.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
            
            cell1.setPadding(6f);
            cell2.setPadding(0f);

            cell1.setBorder(Rectangle.NO_BORDER);
            cell2.setBorder(Rectangle.NO_BORDER);
            
            logoCell.setBorder(Rectangle.NO_BORDER);

            cell3.setColspan(3);
            
            cell3.setGrayFill(0.95f);
            cell3.setPadding(8.0f);
            cell3.setBorder(Rectangle.NO_BORDER);
            
            t.addCell(logoCell);
            t.addCell(cell1);
            t.addCell(cell2);
            
            t.addCell(cell3);
            
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }
        return t;
    }
    private PdfPTable getClaimHeader(ContractorClaim claim) throws PDFException {
        int[] widths = {80, 300};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(2, widths);   
            t.setSpacingAfter(10f);
            PdfPCell cell1 = new PdfPCell(PDFUtil.setBlack10(new Phrase("Claim No. ")));
            PdfPCell cell2 = new PdfPCell(PDFUtil.setBoldBlack10(new Phrase(claim.getClaimNumber())));
            PdfPCell cell3 = new PdfPCell(PDFUtil.setBlack10(new Phrase("Claim Date: ")));
            PdfPCell cell4 = new PdfPCell(PDFUtil.setBoldBlue10(new Phrase(sdf.format(claim.getClaimDate()))));
            
            cell4.setPaddingBottom(20f);
            cell3.setPaddingBottom(20f);
            
            cell1.setBorder(Rectangle.NO_BORDER);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell3.setBorder(Rectangle.NO_BORDER);
            cell4.setBorder(Rectangle.NO_BORDER);
            
            cell1.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
            cell3.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
            cell4.setVerticalAlignment(Rectangle.ALIGN_CENTER);

            cell1.setGrayFill(0.95f);
            cell2.setGrayFill(0.95f);
            cell3.setGrayFill(0.95f);
            cell4.setGrayFill(0.95f);
            
            t.addCell(cell1);
            t.addCell(cell2);
            t.addCell(cell3);
            t.addCell(cell4);
            
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }
        return t;
    }

    static final float STAMP_AREA_HEIGHT = 200f, STAMP_AREA_WIDTH = 120f;

    /**
     * Table containing cells for official stamps
     *
     * @return
     * @throws PDFException
     */
    private PdfPTable getClaimStamps(ContractorClaim claim, boolean rotate) throws PDFException {
        int[] widths = {200};
        int[] mWidths = {100, 100, 100, 100};
        PdfPTable t = null;
        PdfPCell c4 = new PdfPCell(PDFUtil.setBlack8(new Phrase("MUNICIPAL INSPECTOR")));
        PdfPCell c3 = new PdfPCell(PDFUtil.setBlack8(new Phrase("DEPARTMENTAL STAMP")));
        PdfPCell c2 = new PdfPCell(PDFUtil.setBlack8(new Phrase("HSBRC STAMP\nQuality Control \nMaterial and Workmanship")));
        PdfPCell c1 = new PdfPCell(PDFUtil.setBlack8(new Phrase("CONTRACTOR'S ENGINEER")));
        
        

        c1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
        c1.setVerticalAlignment(Rectangle.ALIGN_CENTER);
        c2.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
        c2.setVerticalAlignment(Rectangle.ALIGN_CENTER);
        c3.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
        c3.setVerticalAlignment(Rectangle.ALIGN_CENTER);
        c4.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
        c4.setVerticalAlignment(Rectangle.ALIGN_CENTER);

        try {
            if (rotate) {
                t = PDFUtil.getTable(1, widths);
                t.setTotalWidth(240);
                c1.setFixedHeight(STAMP_AREA_WIDTH);
                c2.setFixedHeight(STAMP_AREA_WIDTH);
                c3.setFixedHeight(STAMP_AREA_WIDTH);
                c4.setFixedHeight(STAMP_AREA_WIDTH);
//                c1.setRotation(90);
//                c3.setRotation(90);
//                c2.setRotation(90);
//                c4.setRotation(90);

            } else {
                c1.setFixedHeight(STAMP_AREA_HEIGHT);
                c2.setFixedHeight(STAMP_AREA_HEIGHT);
                c3.setFixedHeight(STAMP_AREA_HEIGHT);
                c4.setFixedHeight(STAMP_AREA_HEIGHT);

                t = PDFUtil.getTable(4, mWidths);
                StringBuilder sb = new StringBuilder();
                sb.append(claim.getProject().getProjectName());
                sb.append(" - Claim No. ").append(claim.getClaimNumber());
                PdfPCell z1 = new PdfPCell(PDFUtil.setBoldBlack10(new Phrase(sb.toString())));
                z1.setPadding(1f);
                z1.setGrayFill(0.95f);
                z1.setColspan(4);
                z1.setPaddingLeft(20f);
                z1.setPaddingBottom(4f);
                z1.setBorder(Rectangle.NO_BORDER);
                PdfPCell z2 = new PdfPCell(new Phrase("                 "));
                z2.setColspan(4);
                z2.setBorder(Rectangle.NO_BORDER);
                t.addCell(z1);
                t.addCell(z2);
                t.setHeaderRows(2);
                t.setFooterRows(1);
            }
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }
        t.addCell(c1);
        t.addCell(c2);
        t.addCell(c3);
        t.addCell(c4);
        return t;
    }

    static final Logger log = Logger.getLogger(ContractorClaimPDFFactory.class.getSimpleName());
}
