/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.servlets;

import com.boha.monitor.dto.transfer.RequestDTO;
import com.boha.monitor.dto.transfer.ResponseDTO;
import com.boha.monitor.utilx.DataUtil;
import com.boha.monitor.utilx.GZipUtility;
import com.boha.monitor.utilx.ServerStatus;
import com.boha.monitor.utilx.SignInUtil;
import com.boha.monitor.utilx.TrafficCop;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.oreilly.servlet.ServletUtils;
import java.io.File;
import java.io.IOException;
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

/**
 *
 * @author aubreyM
 */
@WebServlet(name = "MonitorGatewayServletX", urlPatterns = {"/gatex"})
public class MonitorGatewayServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @EJB
    DataUtil dataUtil;
    @EJB
    SignInUtil signInUtil;
    

    static final String SOURCE = "MonitorGatewayServletX";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long start = System.currentTimeMillis();        
        Gson gson = new Gson();
        RequestDTO dto = getRequest(gson, request);
        ResponseDTO resp = new ResponseDTO();
        resp.setStatusCode(0);

        try {
            if (dto != null && dto.getRequestType() != null) {
                log.log(Level.INFO, "{0} started .....requestType: {1} - {2}",
                        new Object[]{MonitorGatewayServlet.class.getSimpleName(), dto.getRequestType(), new Date().toString()});
                resp = TrafficCop.processRequest(dto, dataUtil, signInUtil);
            } else {
                resp.setStatusCode(ServerStatus.ERROR_JSON_SYNTAX);
                resp.setMessage(ServerStatus.getMessage(resp.getStatusCode()));
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed request", e);
            resp.setStatusCode(ServerStatus.ERROR_SERVER);
            resp.setMessage(ServerStatus.getMessage(resp.getStatusCode()));
        } finally {
            String json = gson.toJson(resp);           
            if (dto != null) {
                if (dto.isZipResponse()) {
                    try {
                        response.setContentType("application/zip;charset=UTF-8");
                        File zipped = GZipUtility.getZipped(json);
                        ServletUtils.returnFile(zipped.getAbsolutePath(), response.getOutputStream());
                        response.getOutputStream().close();
                        log.log(Level.OFF, "JSON returned: {0} zipped: {1}", new Object[]{json.length(), zipped.length()});
                    } catch (IOException e) {
                        response.setContentType("application/json;charset=UTF-8");
                        try (PrintWriter out = response.getWriter()) {
                            out.println(json);
                        }
                        log.log(Level.SEVERE, "Zipping problem - probably the zipper cannot find the zipped file", e);
                    }

                } else {
                    log.log(Level.OFF, "JSON returned: {0}", json.length());
                    response.setContentType("application/json;charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        out.println(json);
                    }
                }
            } else {
                log.log(Level.SEVERE, "Illegal contact, request ignored");
            }

            long end = System.currentTimeMillis();
            log.log(Level.INFO, "---> MonitorGatewayServletX completed in {0} seconds", getElapsed(start, end));
        }
    }

    public static double getElapsed(long start, long end) {
        BigDecimal m = new BigDecimal(end - start).divide(new BigDecimal(1000));
        return m.doubleValue();
    }

    private RequestDTO getRequest(Gson gson, HttpServletRequest req) {

        String json = req.getParameter("JSON");
        log.log(Level.OFF, "....incoming JSON = {0}", json);
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
