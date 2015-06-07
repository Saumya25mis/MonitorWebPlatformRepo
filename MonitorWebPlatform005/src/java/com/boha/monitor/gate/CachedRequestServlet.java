/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.gate;

import com.boha.monitor.dto.transfer.RequestDTO;
import com.boha.monitor.dto.transfer.RequestList;
import com.boha.monitor.dto.transfer.ResponseDTO;
import static com.boha.monitor.gate.CachedRequestWebSocket.log;
import com.boha.monitor.util.Elapsed;
import com.boha.monitor.util.GZipUtility;
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
@WebServlet(name = "CachedRequestServlet", urlPatterns = {"/cachedRequests"})
public class CachedRequestServlet extends HttpServlet {

   
    @EJB
    PlatformUtil platformUtil;
    @EJB
    TrafficCop trafficCop;
    
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
                resp = trafficCop.processRequest(req);
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
