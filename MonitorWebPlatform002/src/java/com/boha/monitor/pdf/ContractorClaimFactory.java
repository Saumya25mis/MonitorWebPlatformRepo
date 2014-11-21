/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.pdf;

import com.boha.monitor.data.Beneficiary;
import com.boha.monitor.data.Client;
import com.boha.monitor.data.ContractorClaim;
import com.boha.monitor.data.ContractorClaimSite;
import com.boha.monitor.util.PDFException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Factory class with methods to produce ContractorClaim Forms
 *
 * @author aubreyM
 */
public class ContractorClaimFactory {

    /**
     * Main table containing the overall structure of the ContractorClaim Form
     *
     * @param claim
     * @return PdfPTable
     * @throws PDFException
     */
    public static PdfPTable getMainPage(ContractorClaim claim) throws PDFException {
        int[] widths = {600, 200};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(2, widths);
            PdfPCell c1 = new PdfPCell(getClaimDetail(claim));
            PdfPCell c2 = new PdfPCell(getClaimStamps());
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
    private static PdfPTable getClaimDetail(ContractorClaim claim) throws PDFException {
        int[] widths = {600};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(1, widths);
            PdfPCell c1 = new PdfPCell(getHeader(claim.getProject().getClient()));
            PdfPCell c2 = new PdfPCell(getFormTitle(claim));
            PdfPCell c3 = new PdfPCell(getNote());
            PdfPCell c4 = new PdfPCell(getMilestone(claim));
            PdfPCell c5 = new PdfPCell(getSiteTable(claim));
            c1.setBorder(Rectangle.NO_BORDER);
            c2.setBorder(Rectangle.NO_BORDER);
            c3.setBorder(Rectangle.NO_BORDER);
            c4.setBorder(Rectangle.NO_BORDER);
            c5.setBorder(Rectangle.NO_BORDER);
            t.addCell(c1);
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
    private static PdfPTable getFormTitle(ContractorClaim cc) throws PDFException {
        int[] widths = {150, 500};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(2, widths);

            PdfPCell c1 = new PdfPCell(PDFUtil.setBlack8(new Phrase("Contractor")));

            PdfPCell c2 = new PdfPCell(PDFUtil.setBoldBlack12(new Phrase(cc.getProject().getCompany().getCompanyName())));
            PdfPCell c3 = new PdfPCell(PDFUtil.setBlack8(new Phrase("Project Name/No")));
            PdfPCell c4 = new PdfPCell(PDFUtil.setBoldBlack10(new Phrase(cc.getProject().getProjectName())));
            PdfPCell c5 = new PdfPCell(PDFUtil.setBlack8(new Phrase("Engineer")));
            PdfPCell c6 = new PdfPCell(PDFUtil.setBoldBlack10(new Phrase(cc.getProjectEngineer().getEngineer().getEngineerName())));
            //PdfPCell c7 = new PdfPCell(PDFUtil.setBoldBlack14(new Phrase("Claim No. " + cc.getClaimNumber())));
            //PdfPCell c8 = new PdfPCell(PDFUtil.setBoldBlue(new Phrase("Date: " + sdf.format(cc.getClaimDate()))));

            c2.setPadding(2.0f);
            c4.setPadding(2.0f);
            c6.setPadding(2.0f);
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

    private static PdfPTable getNote() throws PDFException {
        Phrase p = new Phrase("Payment Certificate: This is to certify that the site numbers below"
                + " have been inspected by the NHBRC, MUNICIPAL BUILDING INSPECTOR, HS HOUSING TECNICIANS"
                + "(inspectors) and the contractor/Engineer");

        PDFUtil.setBlack6(p);
        int[] widths = {600};
        PdfPTable t = null;

        try {
            t = PDFUtil.getTable(1, widths);
            PdfPCell c1 = new PdfPCell(p);
            c1.setPadding(6.0f);
            t.addCell(c1);

        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }

        return t;
    }

    private static PdfPTable getMilestone(ContractorClaim cc) throws PDFException {
        int[] widths = {600};
        PdfPTable t = null;

        try {
            t = PDFUtil.getTable(1, widths);
            PdfPCell c1 = new PdfPCell(PDFUtil.setBoldBlack10(new Phrase("MILESTONE: " + cc.getTask().getTaskName())));
            c1.setVerticalAlignment(c1.ALIGN_CENTER);
            c1.setPadding(2f);
            t.addCell(c1);

        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }

        return t;
    }


    static final int PAGE_SIZE = 25;

    private static PdfPTable getSiteTable(ContractorClaim claim) throws PDFException {
        int[] widths = {200, 200, 200, 40};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(4, widths);
            PdfPCell c1 = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase("IDENTITY NO.")));
            PdfPCell c2 = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase("SURNAME")));
            PdfPCell c3 = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase("SITE NO.")));
            PdfPCell c4 = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase(" ")));
            c1.setBorderWidth(1.0f);
            c2.setBorderWidth(1.0f);
            c3.setBorderWidth(1.0f);
            c4.setBorderWidth(1.0f);
            t.addCell(c1);
            t.addCell(c2);
            t.addCell(c3);
            t.addCell(c4);
            int index = 0;

            for (ContractorClaimSite site : claim.getContractorClaimSiteList()) {
                PdfPCell c1x = null, c2x = null, c3x = null, c4x = null;
                Beneficiary b = site.getProjectSite().getBeneficiary();
                if (b != null) {
                    log.log(Level.INFO, "beneficiary not null");
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
                    c1x.setGrayFill(0.95f);
                    c2x.setGrayFill(0.95f);
                    c3x.setGrayFill(0.95f);
                }
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

        return t;
    }

    private static PdfPTable getHeader(Client client) throws PDFException {
        int[] widths = {200, 40};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(2, widths);
            PdfPCell cell1 = new PdfPCell(PDFUtil.getHeaderTitle16(client.getClientName()));
            PdfPCell cell2 = new PdfPCell(PDFUtil.getHeaderTitle16("HS/A1"));
            PdfPCell cell3 = new PdfPCell(PDFUtil.getHeaderTitle18("CONTRACTOR CLAIM FORM"));
            cell1.setPadding(4f);
            cell2.setPadding(0f);
            cell1.setBorder(Rectangle.NO_BORDER);
            cell2.setBorder(Rectangle.NO_BORDER);
            
            
            cell3.setColspan(2);
            cell3.setGrayFill(0.95f);
            cell3.setPadding(8.0f);
            cell3.setBorder(Rectangle.NO_BORDER);
            t.addCell(cell1);
            t.addCell(cell2);
            t.addCell(cell3);
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }
        return t;
    }

    /**
     * Table containing cells for official stamps
     *
     * @return
     * @throws PDFException
     */
    public static PdfPTable getClaimStamps() throws PDFException {
        int[] widths = {200};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(1, widths);
            PdfPCell c4 = new PdfPCell(PDFUtil.setBlack6(new Phrase("MUNICIPAL INSPECTOR")));
            PdfPCell c3 = new PdfPCell(PDFUtil.setBlack6(new Phrase("DEPARTMENTAL STAMP")));
            PdfPCell c2 = new PdfPCell(PDFUtil.setBlack6(new Phrase("HSBRC STAMP\nQuality Control - Material and Workmanship")));
            PdfPCell c1 = new PdfPCell(PDFUtil.setBlack6(new Phrase("CONTRACTOR'S ENGINEER")));
            
            c1.setBorderWidthLeft(0f);
            c2.setBorderWidthLeft(0f);
            c3.setBorderWidthLeft(0f);
            c4.setBorderWidthLeft(0f);
            
            c1.setRotation(90);
            c3.setRotation(90);
            c2.setRotation(90);
            c4.setRotation(90);

            t.addCell(c1);
            t.addCell(c2);
            t.addCell(c3);
            t.addCell(c4);

        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }

        return t;
    }

    static final Logger log = Logger.getLogger(ContractorClaimFactory.class.getSimpleName());
}
