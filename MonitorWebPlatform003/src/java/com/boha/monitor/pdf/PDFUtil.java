package com.boha.monitor.pdf;

import com.boha.monitor.data.Company;
import com.boha.monitor.data.ContractorClaim;
import com.boha.monitor.data.Invoice;
import com.boha.monitor.data.Project;
import com.boha.monitor.util.MonitorProperties;
import java.io.IOException;
import java.util.HashMap;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.File;

public class PDFUtil {

    public static File getContractorClaimFile(ContractorClaim cc) {
        File dir = MonitorProperties.getDocumentDir();
        Project p = cc.getProject();
        Company c = p.getCompany();
        File companyDir = new File(dir, "company" + c.getCompanyID());
        if (!companyDir.exists()) {
            companyDir.mkdir();
        }
        File projectDir = new File(companyDir, "project" + p.getProjectID());
        if (!projectDir.exists()) {
            projectDir.mkdir();
        }
        File claimDir = new File(projectDir, "contractorClaims");
        if (!claimDir.exists()) {
            claimDir.mkdir();
        }
        File file = new File(claimDir, "contractorClaim" + cc.getContractorClaimID() + ".pdf");
        if (file.exists()) {
            file.delete();
        }
        
        return file;
        
    }
    public static File getInvoiceFile(Invoice cc) {
        File dir = MonitorProperties.getDocumentDir();
        Project p = cc.getProject();
        Company c = p.getCompany();
        File companyDir = new File(dir, "company" + c.getCompanyID());
        if (!companyDir.exists()) {
            companyDir.mkdir();
        }
        File projectDir = new File(companyDir, "project" + p.getProjectID());
        if (!projectDir.exists()) {
            projectDir.mkdir();
        }
        File invoiceDir = new File(projectDir, "invoices");
        if (!invoiceDir.exists()) {
            invoiceDir.mkdir();
        }
        File file = new File(invoiceDir, "invoice" + cc.getInvoiceID() + ".pdf");
        if (file.exists()) {
            file.delete();
        }
        
        return file;
        
    }
    public static PdfPCell setHeaderCellStyle(PdfPCell cell) {
        cell.setPadding(10.0f);
        cell.setBorderWidth(1.5f);
        cell.setGrayFill(0.90f);
        return cell;
    }

    public static Phrase setHeaderStyle(Phrase p) {
        p.getFont().setColor(new BaseColor(0x000000));
        p.getFont().setStyle(Font.BOLD);
        p.getFont().setSize(Float.parseFloat("16.0"));

        return p;
    }

    public static Phrase setHeadlineStyle(Phrase p) {
        p.getFont().setColor(new BaseColor(0x000000));
        p.getFont().setStyle(Font.BOLD);
        p.getFont().setSize(Float.parseFloat("22.0"));

        return p;
    }

    public static Phrase setBoldBlue(Phrase p) {
        p.getFont().setColor(new BaseColor(0x0000ff));
        p.getFont().setStyle(Font.BOLD);
        p.getFont().setSize(Float.parseFloat("14.0"));
        return p;
    }
    public static Phrase setBoldBlue12(Phrase p) {
        p.getFont().setColor(new BaseColor(0x0000ff));
        p.getFont().setStyle(Font.BOLD);
        p.getFont().setSize(Float.parseFloat("12.0"));
        return p;
    }
    public static Phrase setBoldBlue10(Phrase p) {
        p.getFont().setColor(new BaseColor(0x0000ff));
        p.getFont().setStyle(Font.BOLD);
        p.getFont().setSize(Float.parseFloat("10.0"));
        return p;
    }
    public static Phrase setBoldBlue8(Phrase p) {
        p.getFont().setColor(new BaseColor(0x0000ff));
        p.getFont().setStyle(Font.BOLD);
        p.getFont().setSize(Float.parseFloat("8.0"));
        return p;
    }

    public static Phrase setBoldBlack14(Phrase p) {
        p.getFont().setColor(new BaseColor(0x000000));
        p.getFont().setStyle(Font.BOLD);
        p.getFont().setSize(Float.parseFloat("14.0"));
        return p;
    }
    public static Phrase setBoldBlack12(Phrase p) {
        p.getFont().setColor(new BaseColor(0x000000));
        p.getFont().setStyle(Font.BOLD);
        p.getFont().setSize(Float.parseFloat("12.0"));
        return p;
    }
    public static Phrase setBoldBlack10(Phrase p) {
        p.getFont().setColor(new BaseColor(0x000000));
        p.getFont().setStyle(Font.BOLD);
        p.getFont().setSize(Float.parseFloat("10.0"));
        return p;
    }
    public static Phrase setBoldBlack8(Phrase p) {
        p.getFont().setColor(new BaseColor(0x000000));
        p.getFont().setStyle(Font.BOLD);
        p.getFont().setSize(Float.parseFloat("8.0"));
        return p;
    }
    public static Phrase setBlack8(Phrase p) {
        p.getFont().setColor(new BaseColor(0x000000));
        p.getFont().setStyle(Font.NORMAL);
        p.getFont().setSize(Float.parseFloat("8.0"));
        return p;
    }
    public static Phrase setBlack6(Phrase p) {
        p.getFont().setColor(new BaseColor(0x000000));
        p.getFont().setStyle(Font.NORMAL);
        p.getFont().setSize(Float.parseFloat("6.0"));
        return p;
    }

    public static Phrase setBoldRed14(Phrase p) {
        p.getFont().setColor(new BaseColor(0xff0000));
        p.getFont().setStyle(Font.BOLD);
        p.getFont().setSize(Float.parseFloat("14.0"));
        return p;
    }
    public static Phrase setBoldRed12(Phrase p) {
        p.getFont().setColor(new BaseColor(0xff0000));
        p.getFont().setStyle(Font.BOLD);
        p.getFont().setSize(Float.parseFloat("12.0"));
        return p;
    }

    public static Phrase setBlack14(Phrase p) {
        p.getFont().setColor(new BaseColor(0x000000));
        p.getFont().setStyle(Font.NORMAL);
        p.getFont().setSize(Float.parseFloat("14.0"));
        return p;
    }
    public static Phrase setBlack12(Phrase p) {
        p.getFont().setColor(new BaseColor(0x000000));
        p.getFont().setStyle(Font.NORMAL);
        p.getFont().setSize(Float.parseFloat("12.0"));
        return p;
    }
    public static Phrase setBlack10(Phrase p) {
        p.getFont().setColor(new BaseColor(0x000000));
        p.getFont().setStyle(Font.NORMAL);
        p.getFont().setSize(Float.parseFloat("10.0"));
        return p;
    }

    public static void addLogo(Document doc)
             {

//		try {
////			Drawable d = ctx.getResources().getDrawable(R.drawable.bird48);
////			Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
//			ByteArrayOutputStream stream = new ByteArrayOutputStream();
//			bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//			byte[] bitmapdata = stream.toByteArray();
//
//			Image image1 = Image.getInstance(bitmapdata);
//			image1.setAlignment(Image.ALIGN_LEFT);
//			doc.add(image1);
//
//		} catch (BadElementException ex) {
//		} catch (IOException ex) {
//		}
    }

    public static PdfPTable getTable(int columns, int[] widths) throws Exception {
        PdfPTable table = new PdfPTable(columns);
        table.setWidths(widths);
        table.setSpacingBefore(0.0f);
        table.getDefaultCell().setPadding(5.0f);
        table.setSpacingAfter(0.0f);

        return table;
    }

    public static Paragraph getHeaderTitle22(String title) throws Exception {
        float size = Float.parseFloat("22.0");
        Paragraph par = new Paragraph();
        par.setSpacingBefore(5);
        par.setFont(getHelveticaFont());
        par.setAlignment(Paragraph.ALIGN_CENTER);
        par.getFont().setStyle(Font.BOLD);
        par.getFont().setSize(size);
        par.setSpacingAfter(10);
        par.add(title);
        return par;
    }

    public static Paragraph getHeaderTitle18(String title) throws Exception {
        float size = Float.parseFloat("18.0");
        Paragraph par = new Paragraph();
        par.setSpacingBefore(5);
        par.setFont(getHelveticaFont());
        par.setAlignment(Paragraph.ALIGN_CENTER);
        par.getFont().setStyle(Font.BOLD);
        par.getFont().setSize(size);
        par.setSpacingAfter(5);
        par.add(title);
        return par;
    }

    public static Paragraph getHeaderTitle16(String title) throws Exception {
        float size = Float.parseFloat("16.0");
        Paragraph par = new Paragraph();
        par.setSpacingBefore(50);
        par.setFont(getHelveticaFont());
        par.setAlignment(Paragraph.ALIGN_CENTER);
        par.getFont().setStyle(Font.BOLD);
        par.getFont().setSize(size);
        par.setSpacingAfter(5);
        par.add(title);
        return par;
    }
     public static Paragraph getHeaderTitle14(String title) throws Exception {
        float size = Float.parseFloat("14");
        Paragraph par = new Paragraph();
        par.setSpacingBefore(5);
        par.setFont(getHelveticaFont());
        par.setAlignment(Paragraph.ALIGN_CENTER);
        par.getFont().setStyle(Font.BOLD);
        par.getFont().setSize(size);
        par.setSpacingAfter(5);
        par.add(title);
        return par;
    }
    static BaseFont bf_helv;
    static BaseFont bf_times;
    static BaseFont bf_courier;
    static BaseFont bf_symbol;
    static HashMap<String, String> imgMap;

    public static Font getHelveticaFont() throws Exception {
        setFonts();
        return new Font(bf_helv);
    }

    public static Font getTimesFont() throws Exception {
        setFonts();
        return new Font(bf_times);
    }

    public static Font getSymbolFont() throws Exception {
        setFonts();
        return new Font(bf_symbol);
    }

    public static Font getCourierFont() throws Exception {
        setFonts();
        return new Font(bf_courier);
    }

    private static void setFonts() throws Exception {
        if (bf_helv == null) {
            try {
                bf_helv = BaseFont.createFont(BaseFont.HELVETICA, "Cp1252",
                        false);
                bf_times = BaseFont.createFont(BaseFont.TIMES_ROMAN, "Cp1252",
                        false);
                bf_courier = BaseFont.createFont(BaseFont.COURIER, "Cp1252",
                        false);
                bf_symbol = BaseFont.createFont(BaseFont.SYMBOL, "Cp1252",
                        false);
            } catch (IOException ex) {
                throw new Exception();
            }
        }

    }

}
