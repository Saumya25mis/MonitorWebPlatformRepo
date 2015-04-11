/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.pdf;

import com.boha.monitor.data.Client;
import com.boha.monitor.data.ContractorClaim;
import com.boha.monitor.data.ContractorClaimSite;
import com.boha.monitor.data.Invoice;
import com.boha.monitor.dto.transfer.ResponseDTO;
import com.boha.monitor.util.PDFException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author aubreyM
 */
//@Stateless
//@TransactionManagement(TransactionManagementType.CONTAINER)
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
//            document.add(new Phrase("*** End of Period Attendance Report ***"));
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
        Query q = em.createNamedQuery("ContractorClaimSite.findByClaim",ContractorClaimSite.class);
        q.setParameter("contractorClaimID", contractorClaimID);
        cc.setContractorClaimSiteList(q.getResultList());
        log.log(Level.OFF, "Sites found for claim: {0}", cc.getContractorClaimSiteList().size());
        Document document = new Document(PageSize.A4, 10, 10, 10, 10);
        
        File file = PDFUtil.getContractorClaimFile(cc);

        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream(file));
            writer.setBoxSize("art", new Rectangle(10, 10, 800, 560));
            
           
            document.open();
//            document.add(ContractorClaimFactory.getMainPage(cc));          
            //document.add(new Phrase("*** End of Contractor Claim: " + cc.getClaimNumber()));
            document.close();
            resp.setFileString(Utilities.readFileToString(file));
            resp.setMessage("ContractorClaim PDF generated: " + file.getAbsolutePath());
            log.log(Level.INFO, "ContractorClaim PDF generated: {0}", file.getAbsolutePath());
        } catch (BadElementException ex) {
            log.log(Level.SEVERE, "PDF Generation failed", ex);
            throw new PDFException();
        } catch (FileNotFoundException | DocumentException ex) {
            log.log(Level.SEVERE, "PDF Generation failed", ex);
            throw new PDFException();
        } catch (IOException ex) {
            log.log(Level.SEVERE, "PDF file to string failed", ex);
            throw new PDFException();
        }

        return resp;
    }

    private static void example1(PdfWriter writer, Document doc, ContractorClaim claim) throws PDFException {
        Chunk c = new Chunk("This is a chunk");
        Phrase phrase = new Phrase(c);
        PdfPTable tab = new PdfPTable(5);
        PdfContentByte pcb = writer.getDirectContent();
        ColumnText.showTextAligned(pcb,
                Element.ALIGN_CENTER, phrase, 10, 10, 90);
    }

    public void printPageNumber(PdfWriter writer, Document document, String pageNumber) {
        PdfContentByte directContent = writer.getDirectContent();
        PdfTemplate template = directContent.createTemplate(document.getPageSize().getWidth(), document.getPageSize().getHeight());
        template.moveTo(document.left(), document.bottom() - 11);
        template.lineTo(document.right(), document.bottom() - 11);
        template.stroke();
        directContent.addTemplate(template, 0, 0);
        Phrase phrase = new Phrase("this is a phrase");
        ColumnText.showTextAligned(directContent, Element.ALIGN_CENTER, phrase, 
                document.getPageSize().getWidth() / 2, document.bottom() - 20, 0);
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
