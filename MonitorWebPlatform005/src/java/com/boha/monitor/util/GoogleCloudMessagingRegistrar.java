/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.util;

import com.boha.monitor.data.ErrorStore;
import com.boha.monitor.dto.transfer.ResponseDTO;
import static com.boha.monitor.util.PlatformUtil.log;
import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 * Manage registration of mobile devices with Google Cloud Messaging servers
 *
 * @author Aubrey Malabie
 */

public class GoogleCloudMessagingRegistrar {
    

    public static ResponseDTO sendGCMRegistration(EntityManager em,String regID) throws IOException {
        logger.log(Level.INFO,
                "#### RegisterGCM starting comms with Google servers...."
                + "sending registration of mobile device\n{0}", regID);
        ResponseDTO resp = new ResponseDTO();
        resp.setGCMRegistrationID(regID);
        Sender sender = new Sender(API_KEY);
        Message message = new Message.Builder().addData(
                "message",
                "SmartCity Mobile device registered to send and receive messages. Completed on "
                + new Date().toString()).build();

        Result result = sender.send(message, regID, 5);
        logger.log(
                Level.INFO,
                "#### GCM Registration result, registrationID: {0} messageID: {1} errorCodeName: {2}",
                new Object[]{result.getCanonicalRegistrationId(),
                    result.getMessageId(), result.getErrorCodeName()});

        String error = result.getErrorCodeName();
        
        
        if (error != null && !error.equalsIgnoreCase("")) {
            if (error.equals(Constants.ERROR_NOT_REGISTERED)) {
                resp.setStatusCode(8);
                resp.setMessage("GCM device not registered. Error from GCM server");
                error = resp.getMessage();
                logger.log(Level.INFO,
                        "#### Google Cloud Messaging device not registered");
            }
            if (error.equals(Constants.ERROR_UNAVAILABLE)) {
                resp.setStatusCode(7);
                resp.setMessage("Google Cloud Messaging  servers not available");
                error = resp.getMessage();
                logger.log(Level.INFO, "#### GCM servers not available");
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Google Cloud Messaging device registration failed.\n");
            sb.append("Error Code Name: ").append(error);

            addErrorStore(em,StatusCode.ERROR_GCM, sb.toString());
        } else {
            // we have SUCCESS!!
            resp.setStatusCode(0);
            resp.setMessage("Google Cloud Messaging device registered to Google successfully");
            logger.log(Level.INFO, "#### Cloud messaging registration & server update - OK, id: {0}",
                    regID);
            StringBuilder sb = new StringBuilder();
            sb.append(resp.getMessage()).append("\n");
            sb.append("This device can now participate in push messaging");
            addErrorStore(em,StatusCode.DEVICE_REGISTERED, sb.toString());
            return resp;
        }

        // check canonical reg id
        if (result.getMessageId() != null) {
            String canonicalRegId = result.getCanonicalRegistrationId();
            if (canonicalRegId != null) {
                logger.log(Level.INFO, "### Canonical registration id found, will update yp folks...");
                resp.setGCMRegistrationID(result.getCanonicalRegistrationId());

            }
        }

        return resp;
    }
public static void addErrorStore(EntityManager em, int statusCode, String message) {
        log.log(Level.OFF, "------ adding errorStore, message: {0} statusCode: {1}", new Object[]{message, statusCode});
        try {
            ErrorStore t = new ErrorStore();
            t.setDateOccured(new Date());
            t.setMessage(message);
            t.setStatusCode(statusCode);
            t.setOrigin(GoogleCloudMessagingRegistrar.class.getSimpleName());
            em.persist(t);
            log.log(Level.INFO, "####### ErrorStore row added, origin {0} statusCode: {1}",
                    new Object[]{t.getOrigin(), t.getStatusCode()});
        } catch (Exception e) {
            log.log(Level.SEVERE, "####### Failed to add errorStore: " + message, e);
        }
    }
    private static final Logger logger = Logger.getLogger(GoogleCloudMessagingRegistrar.class
            .getCanonicalName());

    public static final String API_KEY = "AIzaSyC773Vu6nwyqVSt3HWcDetwbgW23Vj2OT0";
}
