/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.pdf;

import com.boha.monitor.data.Client;
import com.boha.monitor.data.ContractorClaim;
import com.boha.monitor.data.Invoice;
import com.boha.monitor.dto.transfer.ResponseDTO;
import com.boha.monitor.util.PDFException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author aubreyM
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class PDFDocumentGenerator {

    @PersistenceContext
    EntityManager em;

    public ResponseDTO getInvoicePDF(
            Integer invoiceID) throws PDFException {
        ResponseDTO resp = new ResponseDTO();
        Invoice inv = em.find(Invoice.class, invoiceID);
        Document document = new Document(PageSize.A4, 10, 10, 10, 10);
        File file = PDFUtil.getInvoiceFile(inv);

        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(file));
            writer.setBoxSize("art", new Rectangle(10, 10, 559, 788));
            document.open();
            PDFUtil.addLogo(document);
//            addHeader(document, inv.getClient());
//			addPeriodHeader(document, className, startDate, endDate, list);
//			addPeriodBody(document, list);
            document.add(new Phrase("*** End of Period Attendance Report ***"));
            document.close();
            resp.setMessage("Invoice PDF generated");
        } catch (BadElementException ex) {
            log.log(Level.SEVERE, "PDF Generation failed", ex);
            throw new PDFException();
        } catch (FileNotFoundException | DocumentException ex) {
            log.log(Level.SEVERE, "PDF Generation failed", ex);
            throw new PDFException();
        }

        return resp;
    }

    public ResponseDTO getContractorClaimPDF(
            Integer contractorClaimID) throws PDFException {
        ResponseDTO resp = new ResponseDTO();
        ContractorClaim cc = em.find(ContractorClaim.class, contractorClaimID);
        Document document = new Document(PageSize.A4, 10, 10, 10, 10);
        File file = PDFUtil.getContractorClaimFile(cc);

        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(file));
            writer.setBoxSize("art", new Rectangle(10, 10, 559, 788));
            document.open();
            PDFUtil.addLogo(document);
            addClaimHeader(document, cc.getProject().getClient());
            document.add(new Phrase("*** End of Period Attendance Report ***"));
            document.close();
            resp.setMessage("ContractorClaim PDF generated");
        } catch (BadElementException ex) {
            log.log(Level.SEVERE, "PDF Generation failed", ex);
            throw new PDFException();
        } catch (FileNotFoundException | DocumentException ex) {
            log.log(Level.SEVERE, "PDF Generation failed", ex);
            throw new PDFException();
        }

        return resp;
    }

    private static void addClaimHeader(Document doc, Client client) throws PDFException {

        try {
            PdfPTable table = new PdfPTable(2);
            int[] widths = {100, 40};
            table.setWidths(widths);
            table.setTotalWidth(160.0f);
            table.setSpacingBefore(5);
            table.getDefaultCell().setPadding(5);
            table.setSpacingAfter(10.0f);
            //

            Phrase pName = PDFUtil.setHeaderStyle(new Phrase(
                    client.getClientName()));
            PDFUtil.setHeaderStyle(pName);

            PdfPCell cell = new PdfPCell(pName);
            PDFUtil.setHeaderCellStyle(cell);
            cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            table.addCell(cell);
            //

            Phrase pPresent = new Phrase("");
            PDFUtil.setHeaderStyle(pPresent);
            pPresent.getFont().setColor(new BaseColor(0x0000ff));
            PdfPCell cell2 = new PdfPCell(pPresent);
            PDFUtil.setHeaderCellStyle(cell2);
            cell2.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            table.addCell(cell2);
            //
            Phrase pAbs = PDFUtil.setHeaderStyle(
                    new Phrase("Total Students Absent"));
            PDFUtil.setHeaderStyle(pAbs);
            PdfPCell cell4 = new PdfPCell(pAbs);
            PDFUtil.setHeaderCellStyle(cell4);
            cell4.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
            table.addCell(cell4);
            //

            doc.add(table);
        } catch (DocumentException e) {
            throw new PDFException();

        }

    }

    static final Logger log = Logger.getLogger(PDFDocumentGenerator.class.getSimpleName());
}
