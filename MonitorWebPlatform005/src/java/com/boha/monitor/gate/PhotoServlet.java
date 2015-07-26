/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.gate;

import com.boha.monitor.dto.PhotoUploadDTO;
import com.boha.monitor.dto.transfer.RequestDTO;
import com.boha.monitor.dto.transfer.ResponseDTO;
import com.boha.monitor.util.DataUtil;
import com.boha.monitor.util.MonitorProperties;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.joda.time.DateTime;

/**
 * This servlet accepts image files uploaded from CourseMaker devices and saves
 * them on disk according to the requestor's role.
 *
 * @author aubreyM
 */
@WebServlet(name = "PhotoServlet", urlPatterns = {"/photo"})
public class PhotoServlet extends HttpServlet {

  @EJB
  DataUtil dataUtil;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        long start = System.currentTimeMillis();

        ResponseDTO ur = new ResponseDTO();
        String json;
        Gson gson = new Gson();
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                ur = downloadPhotos(request);
                ur.setStatusCode(0);
            } else {
                RequestDTO dto = getRequest(gson, request);
                switch (dto.getRequestType()) {

                }

            }

        } catch (FileUploadException ex) {
            logger.log(Level.SEVERE, "File upload fucked", ex);
            ur.setStatusCode(111);
            ur.setMessage("Error. Unable to download file(s) sent. Contact Support");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Servlet file upload fucked", e);
            ur.setStatusCode(113);
            ur.setMessage("Error. Generic server exception");

        } finally {
            json = gson.toJson(ur);
            out.println(json);
            out.close();
            long end = System.currentTimeMillis();
            logger.log(Level.INFO, "PhotoServlet done, elapsed: {0} seconds", getElapsed(start, end));
        }
    }

    public static double getElapsed(long start, long end) {
        BigDecimal m = new BigDecimal(end - start).divide(new BigDecimal(1000));
        return m.doubleValue();
    }
    static final Logger logger = Logger.getLogger("PhotoServlet");

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private RequestDTO getRequest(Gson gson, HttpServletRequest req) {

        String json = req.getParameter("JSON");
        RequestDTO cr = gson.fromJson(json, RequestDTO.class);

        if (cr == null) {
            cr = new RequestDTO();
        }

        return cr;
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    // ################################################################
    public ResponseDTO downloadPhotos(HttpServletRequest request) throws FileUploadException {
        logger.log(Level.INFO, "######### starting PHOTO DOWNLOAD process\n\n");
        ResponseDTO resp = new ResponseDTO();
        resp.setStatusCode(0);
        InputStream stream = null;
        File rootDir;
        try {
            rootDir = MonitorProperties.getImageDir();
            if (!rootDir.exists()) {
                rootDir.mkdir();
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Properties file problem", ex);
            resp.setMessage("Server file unavailable. Please try later");
            resp.setStatusCode(114);

            return resp;
        }

        PhotoUploadDTO photoUpload = null;
        Gson gson = new Gson();
        File companyDir = null, 
                projectDir = null,
                monitorDir = null,
                companyStaffDir = null,
                projectTaskDir = null;
        try {
            ServletFileUpload upload = new ServletFileUpload();
            FileItemIterator iter = upload.getItemIterator(request);
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                String name = item.getFieldName();
                stream = item.openStream();
                if (item.isFormField()) {
                    if (name.equalsIgnoreCase("JSON")) {
                        String json = Streams.asString(stream);
                        if (json != null) {
                            logger.log(Level.INFO, "picture with associated json: {0}", json);
                            photoUpload = gson.fromJson(json, PhotoUploadDTO.class);
                            if (photoUpload != null) {
                                companyDir = createCompanyDirectory(rootDir, companyDir, photoUpload.getCompanyID());
                                if (photoUpload.getProjectID() != null) {
                                    projectDir = createProjectDirectory(companyDir, projectDir, photoUpload.getProjectID());
                                }
                                
                                if (photoUpload.getProjectTaskID() != null) {
                                    projectTaskDir = createProjectTaskDirectory(
                                            projectDir, projectTaskDir,
                                            photoUpload.getProjectTaskID());
                                }
                                if (photoUpload.getStaffID() != null) {
                                    companyStaffDir = createStaffDirectory(
                                            companyDir, companyStaffDir);
                                }
                                if (photoUpload.getMonitor() != null) {
                                    monitorDir = createMonitorDirectory(companyDir, monitorDir);
                                }

                            }
                        } else {
                            logger.log(Level.WARNING, "JSON input seems pretty fucked up! is NULL..");
                            resp.setStatusCode(116);
                            resp.setMessage("Invalid request JSON");
                            return resp;
                        }
                    }
                } else {
                    File imageFile = null;
                    if (photoUpload == null) {
                        continue;
                    }
                    DateTime dt = new DateTime();
                    String fileName = "";
                    if (photoUpload.isIsFullPicture()) {
                        fileName = "f" + dt.getMillis() + ".jpg";
                    } else {
                        fileName = "t" + dt.getMillis() + ".jpg";
                    }
                    if (photoUpload.getStaffID() != null) {
                        if (photoUpload.isIsStaffPicture()) {
                            if (photoUpload.isIsFullPicture()) {
                                fileName = "f" + photoUpload.getStaffID() + ".jpg";
                            } else {
                                fileName = "t" + photoUpload.getStaffID() + ".jpg";
                            }
                        }
                    }
                    
                    switch (photoUpload.getPictureType()) {
                        
                        case PhotoUploadDTO.TASK_IMAGE:
                            imageFile = new File(projectTaskDir, fileName);
                            break;
                        case PhotoUploadDTO.PROJECT_IMAGE:
                            imageFile = new File(projectDir, fileName);
                            logger.log(Level.INFO, "downloading image for project id: {0}", photoUpload.getProjectID());
                            break;
                        case PhotoUploadDTO.STAFF_IMAGE:
                            imageFile = new File(companyStaffDir, fileName);
                            break;
                            case PhotoUploadDTO.MONITOR_IMAGE:
                            imageFile = new File(monitorDir, fileName);
                            break;
                    }

                    writeFile(stream, imageFile);
                    resp.setStatusCode(0);
                    resp.setMessage("Photo downloaded from mobile app ");
                    //add database
                    logger.log(Level.SEVERE, "image downloaded OK: {0}", imageFile.getAbsolutePath());
                    photoUpload.setUri(imageFile.getName());
                    photoUpload.setDateUploaded(new Date().getTime());
                    if (photoUpload.isIsFullPicture()) {
                        photoUpload.setThumbFlag(null);
                    } else {
                        photoUpload.setThumbFlag(1);
                    }
                    
                    //add photo to database
                    dataUtil.addPhotoUpload(photoUpload);
                    resp.setStatusCode(0);

                }
            }

        } catch (FileUploadException | IOException | JsonSyntaxException ex) {
            logger.log(Level.SEVERE, "Servlet failed on IOException, images NOT uploaded", ex);
            throw new FileUploadException();
        }

       
        return resp;
    }

    private File createProjectTaskDirectory(File projectDir, File taskDir, int id) {
        // logger.log(Level.INFO, "task photo to be downloaded");
        taskDir = new File(projectDir, RequestDTO.TASK_DIR + id);
        if (!taskDir.exists()) {
            taskDir.mkdir();
            logger.log(Level.INFO, "task  directory created - {0}",
                    taskDir.getAbsolutePath());

        }
        return taskDir;
    }

 
    private File createProjectDirectory(File companyDir, File projectDir, int id) {
        projectDir = new File(companyDir, RequestDTO.PROJECT_DIR + id);
        // logger.log(Level.INFO, "just after new {0}", projectDir);
        if (!projectDir.exists()) {
            projectDir.mkdir();
            logger.log(Level.INFO, "projectDir created - {0}",
                    projectDir.getAbsolutePath());

        }
        return projectDir;
    }

    private File createMonitorDirectory(File companyDir, File monitorDir) {
        monitorDir = new File(companyDir, RequestDTO.STAFF_DIR);
        if (!monitorDir.exists()) {
            monitorDir.mkdir();
            logger.log(Level.INFO, "monitorDir created - {0}",
                    monitorDir.getAbsolutePath());

        }
        return monitorDir;
    }
     private File createStaffDirectory(File companyDir, File staffDir) {
        staffDir = new File(companyDir, RequestDTO.STAFF_DIR);
        if (!staffDir.exists()) {
            staffDir.mkdir();
            logger.log(Level.INFO, "staffDir created - {0}",
                    staffDir.getAbsolutePath());

        }
        return staffDir;
    }

    private File createCompanyDirectory(File rootDir, File companyDir, int id) {
        companyDir = new File(rootDir, RequestDTO.COMPANY_DIR + id);
        if (!companyDir.exists()) {
            companyDir.mkdir();
            logger.log(Level.INFO, "company directory created - {0}",
                    companyDir.getAbsolutePath());
        }

        return companyDir;
    }

    private void writeFile(InputStream stream, File imageFile) throws FileNotFoundException, IOException {

        if (imageFile == null) {
            throw new FileNotFoundException();
        }
        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            int read;
            byte[] bytes = new byte[2048];
            while ((read = stream.read(bytes)) != -1) {
                fos.write(bytes, 0, read);
            }
            stream.close();
            fos.flush();
        }

        logger.log(Level.WARNING, "### File downloaded: {0} size: {1}",
                new Object[]{imageFile.getAbsolutePath(), imageFile.length()});
    }
}
