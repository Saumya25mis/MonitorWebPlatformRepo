/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.pdf;

import com.boha.monitor.data.ContractorClaim;
import com.boha.monitor.data.Invoice;
import com.boha.monitor.data.InvoiceItem;
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
public class InvoicePDFFactory {

    @PersistenceContext
    EntityManager em;

    public ResponseDTO getInvoiceClaimPDF(
            Integer invoiceID) throws PDFException {
        ResponseDTO resp = new ResponseDTO();
        Invoice invoice = em.find(Invoice.class, invoiceID);
        Query q = em.createNamedQuery("InvoiceItem.findByInvoice", InvoiceItem.class);
        q.setParameter("invoiceID", invoiceID);
        invoice.setInvoiceItemList(q.getResultList());
        log.log(Level.OFF, "items found for invoice: {0}", invoice.getInvoiceItemList().size());
        Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);
        document.addAuthor(invoice.getProject().getCompany().getCompanyName());
        document.addTitle("Invoice");

        File file = PDFUtil.getInvoiceFile(invoice);

        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream(file));
            document.open();
            document.add(getMainPage(invoice));
            if (invoice.getInvoiceItemList().size() > FIRST_PAGE_SIZE) {
                document.newPage();
                document.add(getNextPageItems(invoice, FIRST_PAGE_SIZE));

            }
            document.close();
            resp.setMessage("Invoice PDF generated: " + file.getAbsolutePath());
            log.log(Level.INFO, "Invoice PDF generated: {0}", file.getAbsolutePath());
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
     * Main table containing the overall structure of the Invoice
     *
     * @param invoice
     * @return PdfPTable
     * @throws PDFException
     */
    public PdfPTable getMainPage(Invoice invoice) throws PDFException {
        int[] widths = {600, 200};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(2, widths);
            PdfPCell c1 = new PdfPCell(getCompanyBox(invoice));
            PdfPCell c2 = new PdfPCell(getInvoiceNumberDateBox(invoice));
            PdfPCell c3 = new PdfPCell(getCLientBox(invoice));
            PdfPCell c4 = new PdfPCell(getFirstPageItems(invoice));

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

    private PdfPTable getCompanyBox(Invoice claim) throws PDFException {
        int[] widths = {400};
        PdfPTable t = null;
        File logoFile = null;
        try {
            File dir = MonitorProperties.getImageDir();
            File companyDir = new File(dir, "company" + claim.getCompany().getCompanyID());
            logoFile = new File(companyDir, "logo.png");
        } catch (Exception e) {

        }
        try {
            t = PDFUtil.getTable(1, widths);
            PdfPCell c1 = null;
            if (logoFile != null && logoFile.exists()) {
                c1 = new PdfPCell(Image.getInstance(logoFile.getAbsolutePath()));
            } else {
                c1 = new PdfPCell(new Phrase(" "));
            }
            PdfPCell c1a = new PdfPCell(new Phrase(""));
            PdfPCell c2 = new PdfPCell(new Phrase(""));
            PdfPCell c3 = new PdfPCell(new Phrase(""));
            PdfPCell c4 = new PdfPCell(new Phrase(""));
            PdfPCell c5 = new PdfPCell(new Phrase(""));
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

    private PdfPTable getCLientBox(Invoice claim) throws PDFException {
        int[] widths = {400};
        PdfPTable t = null;
        File logoFile = null;
        try {
            File dir = MonitorProperties.getImageDir();
            File companyDir = new File(dir, "company" + claim.getCompany().getCompanyID());
            File logoDir = new File(companyDir, "client_logos");
            logoFile = new File(logoDir, "logo" + claim.getClient().getClientID() + ".png");
        } catch (Exception e) {

        }
        try {
            t = PDFUtil.getTable(1, widths);
            PdfPCell c1 = null;
            if (logoFile != null && logoFile.exists()) {
                c1 = new PdfPCell(Image.getInstance(logoFile.getAbsolutePath()));
            } else {
                c1 = new PdfPCell(new Phrase(" "));
            }

            PdfPCell c1a = new PdfPCell(new Phrase(""));
            PdfPCell c2 = new PdfPCell(new Phrase(""));
            PdfPCell c3 = new PdfPCell(new Phrase(""));
            PdfPCell c4 = new PdfPCell(new Phrase(""));
            PdfPCell c5 = new PdfPCell(new Phrase(""));
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

    private PdfPTable getInvoiceNumberDateBox(Invoice claim) throws PDFException {
        int[] widths = {400};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(1, widths);
            PdfPCell c1 = new PdfPCell(new Phrase(""));
            PdfPCell c1a = new PdfPCell(new Phrase(""));
            PdfPCell c2 = new PdfPCell(new Phrase(""));
            PdfPCell c3 = new PdfPCell(new Phrase(""));
            PdfPCell c4 = new PdfPCell(new Phrase(""));
            PdfPCell c5 = new PdfPCell(new Phrase(""));
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

    private PdfPTable getFormTitle(ContractorClaim cc) throws PDFException {
        int[] widths = {150, 500};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(2, widths);

            PdfPCell c1 = new PdfPCell(PDFUtil.setBlack8(new Phrase("Contractor")));

            PdfPCell c2 = new PdfPCell(PDFUtil.setBoldBlack12(new Phrase(cc.getProject().getCompany().getCompanyName())));
            PdfPCell c3 = new PdfPCell(PDFUtil.setBlack8(new Phrase("Project Name/No")));
            PdfPCell c4 = new PdfPCell(PDFUtil.setBoldBlack10(new Phrase(cc.getProject().getProjectName())));
            PdfPCell c5 = new PdfPCell(PDFUtil.setBlack8(new Phrase("Engineer")));
            PdfPCell c6 = new PdfPCell(PDFUtil.setBoldBlue(new Phrase(cc.getProjectEngineer().getEngineer().getEngineerName())));
            //PdfPCell c7 = new PdfPCell(PDFUtil.setBoldBlack14(new Phrase("Claim No. " + cc.getClaimNumber())));
            //PdfPCell c8 = new PdfPCell(PDFUtil.setBoldBlue(new Phrase("Date: " + sdf.format(cc.getClaimDate()))));

            c2.setPaddingLeft(10.0f);
            c2.setPaddingBottom(4.0f);
            c4.setPaddingLeft(10.0f);
            c4.setPaddingBottom(4.0f);
            c6.setPaddingLeft(10.0f);
            c6.setPaddingBottom(4.0f);

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

    private PdfPTable getMilestone(ContractorClaim cc) throws PDFException {
        int[] widths = {600};
        PdfPTable t = null;

        try {
            t = PDFUtil.getTable(1, widths);
            Phrase p = new Phrase("MILESTONE: " + cc.getTask().getTaskName());

            PdfPCell c1 = new PdfPCell(PDFUtil.setBoldBlue(p));
            c1.setVerticalAlignment(Rectangle.ALIGN_CENTER);
            c1.setPaddingTop(4f);
            c1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);

            t.addCell(c1);

        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }

        return t;
    }

    static final int FIRST_PAGE_SIZE = 20, NEXT_PAGE_SIZE = 40;

    private PdfPTable getFirstPageItems(Invoice invoice) throws PDFException {
        int[] widths = {200, 200, 200, 40};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(4, widths);
            //PdfPCell c0 = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase("NO.")));
            PdfPCell c1 = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase("IDENTITY NO.")));
            PdfPCell c2 = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase("SURNAME")));
            PdfPCell c3 = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase("SITE NO.")));
            PdfPCell c4 = new PdfPCell(PDFUtil.setBoldBlack8(new Phrase(" ")));
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

            for (InvoiceItem site : invoice.getInvoiceItemList()) {
                PdfPCell c1x = null, c2x = null, c3x = null, c4x = null, c0x = null;

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

    private PdfPTable getNextPageItems(Invoice claim, int startIndex) throws PDFException {
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
            sb.append(" - Invoice No. ").append(claim.getInvoiceNumber());
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

            for (int i = startIndex; i < claim.getInvoiceItemList().size(); i++) {

                InvoiceItem site = claim.getInvoiceItemList().get(i);
                PdfPCell c1x = null, c2x = null, c3x = null, c4x = null, c0x = null;

            }
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }

        PdfPCell a1 = new PdfPCell(t);
        //PdfPCell a2 = new PdfPCell(getClaimStamps(claim, true));
        a1.setBorder(Rectangle.NO_BORDER);
        //a2.setBorder(Rectangle.NO_BORDER);
        mainTable.addCell(a1);
        //mainTable.addCell(a2);

        return mainTable;
    }

    private PdfPTable getHeader(ContractorClaim claim) throws PDFException {
        int[] widths = {200, 40};
        PdfPTable t = null;
        try {
            t = PDFUtil.getTable(2, widths);
            PdfPCell cell1 = new PdfPCell(PDFUtil.getHeaderTitle16(claim.getProject().getClient().getClientName()));
            PdfPCell cell2 = new PdfPCell(PDFUtil.getHeaderTitle16("HS/A1"));
            PdfPCell cell3 = new PdfPCell(PDFUtil.getHeaderTitle18("CONTRACTOR CLAIM FORM"));

            PdfPCell cell4 = new PdfPCell(new Phrase("Claim No. "));
            PdfPCell cell5 = new PdfPCell(new Phrase(claim.getClaimNumber()));

            cell1.setPadding(6f);
            cell2.setPadding(0f);

            cell1.setBorder(Rectangle.NO_BORDER);
            cell2.setBorder(Rectangle.NO_BORDER);
            cell4.setBorder(Rectangle.NO_BORDER);
            cell5.setBorder(Rectangle.NO_BORDER);

            cell3.setColspan(2);
            cell3.setGrayFill(0.95f);
            cell3.setPadding(8.0f);
            cell3.setBorder(Rectangle.NO_BORDER);
            t.addCell(cell1);
            t.addCell(cell2);
            t.addCell(cell3);
            t.addCell(cell4);
            t.addCell(cell5);
        } catch (Exception ex) {
            log.log(Level.SEVERE, null, ex);
            throw new PDFException();
        }
        return t;
    }

    static final Logger log = Logger.getLogger(InvoicePDFFactory.class.getSimpleName());
}
