/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.gate;

import com.boha.monitor.dto.transfer.RequestDTO;
import com.boha.monitor.dto.transfer.ResponseDTO;
import com.boha.monitor.util.DataUtil;
import com.boha.monitor.util.GZipUtility;
import com.boha.monitor.util.ListUtil;
import com.boha.monitor.util.PlatformUtil;
import com.boha.monitor.util.ServerStatus;
import com.boha.monitor.util.TrafficCop;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.oreilly.servlet.ServletUtils;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aubreyM
 */
@WebServlet(name = "MonitorGatewayServlet", urlPatterns = {"/gate"})
public class MonitorGatewayServlet extends HttpServlet {

    @EJB
    DataUtil dataUtil;
    @EJB
    ListUtil listUtil;
    @EJB
    PlatformUtil platformUtil;
    @EJB
    TrafficCop trafficCop;

    static final String SOURCE = "MonitorGatewayServlet";

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
        long start = System.currentTimeMillis();

        Gson gson = new Gson();
        RequestDTO dto = getRequest(gson, request);
        ResponseDTO resp = new ResponseDTO();
        resp.setStatusCode(0);

        try {
            if (dto != null && dto.getRequestType() != null) {
                log.log(Level.INFO, "{0} started ..requestType: {1}",
                        new Object[]{MonitorGatewayServlet.class.getSimpleName(), dto.getRequestType()});
                resp = trafficCop.processRequest(dto,
                        dataUtil, listUtil);
            } else {
                resp.setStatusCode(ServerStatus.ERROR_JSON_SYNTAX);
                resp.setMessage(ServerStatus.getMessage(resp.getStatusCode()));
            }
        } catch (Exception e) {
            resp.setStatusCode(ServerStatus.ERROR_SERVER);
            resp.setMessage(ServerStatus.getMessage(resp.getStatusCode()));
        } finally {
            
            response.setContentType("application/zip;charset=UTF-8");
            File zipped;
            String json = gson.toJson(resp);
            try {
                zipped = GZipUtility.getZipped(json);
                ServletUtils.returnFile(zipped.getAbsolutePath(), response.getOutputStream());
                response.getOutputStream().close();
                log.log(Level.OFF, "### Zipped Length of Response: {0} -  "
                        + "unzipped length: {1}", new Object[]{zipped.length(), json.length()});
            } catch (IOException e) {
                log.log(Level.SEVERE, "Zipping problem - probably the zipper cannot find the zipped file", e);
            }

            long end = System.currentTimeMillis();
            log.log(Level.INFO, "---> MonitorGatewayServlet completed in {0} seconds", getElapsed(start, end));
        }
    }

    public static double getElapsed(long start, long end) {
        BigDecimal m = new BigDecimal(end - start).divide(new BigDecimal(1000));
        return m.doubleValue();
    }

    private RequestDTO getRequest(Gson gson, HttpServletRequest req) {

        String json = req.getParameter("JSON");
        log.log(Level.OFF, "...incoming JSON = {0}", json);
        RequestDTO cr = new RequestDTO();

        try {
            cr = gson.fromJson(json, RequestDTO.class);

        } catch (JsonSyntaxException e) {
            log.log(Level.SEVERE, "Failed JSON", e);
        }
        return cr;
    }
    private static final Logger log = Logger.getLogger(MonitorGatewayServlet.class
            .getSimpleName());
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

}
