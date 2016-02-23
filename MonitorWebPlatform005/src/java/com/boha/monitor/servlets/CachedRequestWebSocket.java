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
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import com.boha.monitor.utilx.DataUtil;
import com.boha.monitor.utilx.ServerStatus;
import com.boha.monitor.utilx.SignInUtil;
import com.boha.monitor.utilx.StatusCode;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author aubreyM
 */
@ServerEndpoint("/wsrequest")
@Stateless
public class CachedRequestWebSocket {

    @EJB
    DataUtil dataUtil;
    @EJB
    SignInUtil signInUtil;

    static final String SOURCE = "CachedRequestWebSocket";
    //TODO - clean up expired sessions!!!!
    public static final Set<Session> peers
            = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public ByteBuffer onMessage(String message) {
        log.log(Level.WARNING, SOURCE + " - onMessage: {0}", message);
        ResponseDTO response = new ResponseDTO();
        ByteBuffer bb = null;
        int goodCount = 0, badCount = 0;
        long start = System.currentTimeMillis();
        try {
            RequestList dto = gson.fromJson(message, RequestList.class);
            for (RequestDTO req : dto.getRequests()) {
                ResponseDTO resp = TrafficCop.processRequest(req, dataUtil, signInUtil);
                if (resp.getStatusCode() == 0) {
                    goodCount++;
                } else {
                    badCount++;
                }
            }
            response.setStatusCode(0);
            response.setMessage("Cached requests processed");
            response.setGoodCount(goodCount);
            response.setBadCount(badCount);
            long end = System.currentTimeMillis();
            response.setElapsedRequestTimeInSeconds(Elapsed.getElapsed(start, end));
            log.log(Level.INFO, "Total elapsed time: {0}", response.getElapsedRequestTimeInSeconds());
        } catch (IOException ex) {
            response.setMessage("Unable to process cached requests");
            response.setStatusCode(777);
            
        } catch (Exception ex) {
            response.setStatusCode(StatusCode.ERROR_SERVER);
            response.setMessage(ServerStatus.getMessage(response.getStatusCode()));
            log.log(Level.SEVERE, response.getMessage(), ex);
            dataUtil.addErrorStore(StatusCode.ERROR_SERVER, response.getMessage(), SOURCE);

        }
        bb = ByteBuffer.wrap(gson.toJson(response).getBytes());
        return bb;
    }

    @OnOpen
    public void onOpen(Session session) {

        peers.add(session);
        try {
            ResponseDTO r = new ResponseDTO();
            r.setSessionID(session.getId());
            session.getBasicRemote().sendText(gson.toJson(r));
            log.log(Level.WARNING, SOURCE + " - onOpen...sent session id: {0}", session.getId());
        } catch (IOException ex) {
            log.log(Level.SEVERE, "Failed to send websocket sessionID", ex);
        }
    }

    @OnClose
    public void onClose(Session session
    ) {
        log.log(Level.WARNING, SOURCE + " onClose - remove session: {0}", session.getId());
        for (Session mSession : peers) {
            if (session.getId().equalsIgnoreCase(mSession.getId())) {
                peers.remove(mSession);
                break;
            }
        }
    }

    @OnError
    public void onError(Throwable t) {
        log.log(Level.SEVERE, SOURCE, t);

    }

    Gson gson = new Gson();
    static final Logger log = Logger.getLogger(CachedRequestWebSocket.class.getSimpleName());

}
