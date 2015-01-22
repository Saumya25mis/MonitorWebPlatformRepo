/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.util;

import com.boha.monitor.data.PhotoUpload;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author aubreyM
 */

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class FixUtil {

    @PersistenceContext
    EntityManager em;
    
    public void fixUri() {
        Query q = em.createQuery("select a from PhotoUpload a");
        List<PhotoUpload> list = q.getResultList();
        
        for (PhotoUpload p : list) {
            String s = p.getUri();
            int index = s.lastIndexOf("/");
            String fileName = s.substring(index + 1);
            p.setUri(fileName);
            em.merge(p);
            System.out.println(" File updated to uri: " + fileName);
        }
    }
}