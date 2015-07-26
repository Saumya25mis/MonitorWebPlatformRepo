/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.gate;

import com.boha.monitor.dto.transfer.RequestDTO;
import com.boha.monitor.dto.transfer.ResponseDTO;
import com.boha.monitor.util.DataException;
import com.boha.monitor.util.DataUtil;
import com.boha.monitor.util.GZipUtility;
import com.boha.monitor.util.ServerStatus;
import com.boha.monitor.util.SignInUtil;
import com.boha.monitor.util.StatusCode;
import com.boha.monitor.util.TrafficCop;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author aubreyM
 */
@ServerEndpoint("/wsmonitor")
@Stateless
public class MonitorWebSocket {

    
    @Inject
    DataUtil dataUtil;
    @EJB
    SignInUtil signInUtil;
   

    static final String SOURCE = "MonitorWebSocket";
    public static final Set<Session> peers
            = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public ByteBuffer onMessage(String message) {
        log.log(Level.WARNING, "...incoming socket: onMessage: {0}", message);
        ResponseDTO resp = new ResponseDTO();
        ByteBuffer bb = null;

        try {
            RequestDTO dto = gson.fromJson(message, RequestDTO.class);
            resp = TrafficCop.processRequest(dto, dataUtil, signInUtil);
            bb = GZipUtility.getZippedResponse(resp);
        } catch (JsonSyntaxException | IOException ex) {
            log.log(Level.SEVERE, "Failed...", ex);
            resp.setStatusCode(ServerStatus.ERROR_SERVER);
            resp.setMessage(ServerStatus.getMessage(resp.getStatusCode()));
            dataUtil.addErrorStore(StatusCode.ERROR_JSON_SYNTAX, "JSON Syntax Error", SOURCE);
            
            try {
                bb = GZipUtility.getZippedResponse(resp);
            } catch (IOException ex1) {
                log.log(Level.SEVERE, "Failed to zip error response! What???", ex1);
                resp.setStatusCode(ServerStatus.ERROR_DATA_COMPRESSION);
                resp.setMessage(ServerStatus.getMessage(resp.getStatusCode()));
            }
        } catch (DataException e) {
            resp.setStatusCode(ServerStatus.ERROR_DATABASE);
            resp.setMessage(ServerStatus.getMessage(resp.getStatusCode()));
            log.log(Level.SEVERE, resp.getMessage(), e);
            dataUtil.addErrorStore(StatusCode.ERROR_DATABASE, e.getDescription(), SOURCE);

        } catch (Exception e) {
            resp.setStatusCode(StatusCode.ERROR_SERVER);
            resp.setMessage(ServerStatus.getMessage(resp.getStatusCode()));
            log.log(Level.SEVERE, resp.getMessage(), e);
            
            dataUtil.addErrorStore(StatusCode.ERROR_SERVER, resp.getMessage(), SOURCE);

        }
        return bb;
    }

    @OnOpen
    public void onOpen(Session session) {
        peers.add(session);
        try {
            ResponseDTO r = new ResponseDTO();
            r.setSessionID(session.getId());
            r.setStatusCode(0);
            session.getBasicRemote().sendText(gson.toJson(r));
            log.log(Level.WARNING, "########## onOpen...sent session id: {0}", session.getId());
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Failed to send websocket sessionID", ex);
        }
    }

    @OnClose
    public void onClose(Session session
    ) {
        log.log(Level.WARNING, "onClose - remove session: {0}", session.getId());
        for (Session mSession : peers) {
            if (session.getId().equalsIgnoreCase(mSession.getId())) {
                peers.remove(mSession);
                break;
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable t) {
        log.log(Level.SEVERE, "### @OnError, websocket failed.......");
        try {
            ResponseDTO r = new ResponseDTO();
            r.setStatusCode(ServerStatus.ERROR_WEBSOCKET);
            r.setMessage(ServerStatus.getMessage(r.getStatusCode()));
            session.getBasicRemote().sendText(gson.toJson(r));
        } catch (IOException ex) {
            Logger.getLogger(MonitorWebSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    Gson gson = new Gson();
    static final Logger log = Logger.getLogger(MonitorWebSocket.class.getSimpleName());
}
