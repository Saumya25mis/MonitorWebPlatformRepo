/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.util;

import com.boha.monitor.data.ErrorStore;
import com.boha.monitor.dto.transfer.ResponseDTO;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author aubreyM
 */
public class BohaUnCaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    
    public interface BohaListener {
        public void onException(ResponseDTO message);
    }

    EntityManager em;
    BohaListener listener;
    
    public BohaUnCaughtExceptionHandler(EntityManager em, BohaListener listener) {
        this.em = em;
        this.listener = listener;
    }
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.log(Level.SEVERE, "uncaughtException fired! Will gracefully climb down...");
        addErrorStore(e);
        ResponseDTO w = new ResponseDTO();
        w.setStatusCode(999);
        w.setMessage("Server error encountered. Please try again.");
        listener.onException(w);
    }
    
    private void addErrorStore(Throwable error) {
        try {
            ErrorStore es = new ErrorStore();
            es.setDateOccured(new Date());
            es.setMessage("Uncaught Server Exception\n" + error.getMessage());
            es.setOrigin("Cloud Server");
            es.setStatusCode(999);
            em.persist(es);
            log.log(Level.SEVERE, "addErrorStore added record to database");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
        }
    }
    
    static final Logger log = Logger.getLogger(BohaUnCaughtExceptionHandler.class.getSimpleName());
}
