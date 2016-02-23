/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.servlets;

import com.boha.monitor.dto.transfer.RequestDTO;
import com.boha.monitor.dto.transfer.RequestList;
import com.boha.monitor.dto.transfer.ResponseDTO;
import com.boha.monitor.utilx.Elapsed;
import com.boha.monitor.utilx.TrafficCop;
import com.google.gson.Gson;
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
import com.boha.monitor.utilx.DataUtil;
import com.boha.monitor.utilx.SignInUtil;
import java.io.PrintWriter;

/**
 *
 * @author aubreyM
 */
@WebServlet(name = "CachedRequestServlet", urlPatterns = {"/cachedRequests"})
public class CachedRequestServlet extends HttpServlet {


    @EJB
    DataUtil dataUtil;
    @EJB
    SignInUtil signInUtil;
    
    static final String SOURCE = "CachedRequestServlet";

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
        ResponseDTO resp = new ResponseDTO();        
        int goodCount = 0, badCount = 0;
        try {

            String message = request.getParameter("JSON");
            RequestList dto = gson.fromJson(message, RequestList.class);
            for (RequestDTO req : dto.getRequests()) {
                resp = TrafficCop.processRequest(req, dataUtil, signInUtil);
                if (resp.getStatusCode() == 0) {
                    goodCount++;
                } else {
                    badCount++;
                }
            }
            resp.setStatusCode(0);
            resp.setMessage("Cached requests processed");
            resp.setGoodCount(goodCount);
            resp.setBadCount(badCount);
            long end = System.currentTimeMillis();
            resp.setElapsedRequestTimeInSeconds(Elapsed.getElapsed(start, end));
            log.log(Level.INFO, "Total elapsed time: {0}", resp.getElapsedRequestTimeInSeconds());
        } catch (Exception ex) {
            resp.setMessage("Unable to process cached requests");
            resp.setStatusCode(777);
           
        } finally {
            
            response.setContentType("application/json;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    String json = gson.toJson(resp);
                    out.println(json);
                }
            

            long end = System.currentTimeMillis();
            log.log(Level.INFO, "---> MonitorGatewayServlet completed in {0} seconds", getElapsed(start, end));
        }
    }

    public static double getElapsed(long start, long end) {
        BigDecimal m = new BigDecimal(end - start).divide(new BigDecimal(1000));
        return m.doubleValue();
    }

    private static final Logger log = Logger.getLogger(CachedRequestServlet.class
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
