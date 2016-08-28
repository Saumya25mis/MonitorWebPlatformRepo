/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.utilx;

import com.boha.monitor.data.GcmDevice;
import com.boha.monitor.dto.LocationTrackerDTO;
import com.boha.monitor.dto.SimpleMessageDTO;
import com.boha.monitor.dto.SimpleMessageDestinationDTO;
import com.boha.monitor.dto.transfer.ResponseDTO;
import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * Utility class for sending GCM messages
 *
 * @author aubreyM
 */
public class GoogleCloudMessageUtil {

    private static final int RETRIES = 5;

    public static final int GCM_MESSAGE_ERROR = 3, ALL_OK = 0, MAX_MESSAGES_IN_BATCH = 1000;
    static Gson gson = new Gson();
    static SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");

    //TODO - think some more about messaging and notifications .... persist in database
    /**
     * Send a SimpleMessage to list of devices via Google Cloud Message servers
     *
     * @param dataUtil
     * @param simple
     * @return
     * @throws Exception
     * @throws DataException
     */
    public static ResponseDTO sendSimpleMessage(DataUtil dataUtil, SimpleMessageDTO simple) throws
            Exception, DataException {
        ResponseDTO resp = new ResponseDTO();
        List<String> registrationIDs;
        simple.setMessageDate(new Date().getTime());

        if (simple.isSendToAllinCompany()) {
            registrationIDs = buildListAllinCompany(dataUtil.getEntityManager(), simple);
        } else {
            registrationIDs = buildList(dataUtil.getEntityManager(), simple);
        }
        if (registrationIDs.isEmpty()) {
            LOG.log(Level.SEVERE, "#### sendSimpleMessage: No gcm registrationIDs found ");
            resp.setMessage("No staff or monitors found or their devices are not registered");
            resp.setStatusCode(StatusCode.ERROR_GCM);
            return resp;
        }

        simple.setSimpleMessageDestinationList(null);
        String gcmMessage = gson.toJson(simple);

        List<GCMResult> gCMResults;
        Sender sender = new Sender(GoogleCloudMessagingRegistrar.API_KEY);
        Message message = new Message.Builder()
                .addData("simpleMessage", gcmMessage)
                .collapseKey("" + System.currentTimeMillis())
                .build();

        LOG.log(Level.INFO, "sendSimpleMessage: About to send message via GCM: {0}", registrationIDs.size());
        GCMResult gcmr = null;
        String rMsg;
        if (registrationIDs.size() == 1) {
            Result result = sender.send(message, registrationIDs.get(0), RETRIES);
            gcmr = handleResult(dataUtil, result, registrationIDs.get(0));
            if (gcmr.isOK) {
                rMsg = "Google GCM - message has been sent to Google servers";
            } else {
                rMsg = "Google GCM - message has not been sent. Error occured";
                resp.setStatusCode(StatusCode.ERROR_GCM);
                resp.setMessage(rMsg);
            }
            resp.setMessage(rMsg);
            return resp;
        } else {
            gCMResults = sendBatches(dataUtil, sender, message, registrationIDs);
        }
        int errors = 0, kool = 0;
        for (GCMResult res : gCMResults) {
            if (res.isOK) {
                kool++;
            } else {
                errors++;
            }
        }

        LOG.log(Level.INFO, "GCM batch send results, success: {0} errors: {1}", new Object[]{kool, errors});
        resp.setMessage("GCM Simple messages sent: " + kool + " errors: " + errors);
        return resp;
    }

    /**
     * Send LocationTracker message to list of devices via Google Cloud Message
     *
     * @param dataUtil
     * @param track
     * @return
     * @throws Exception
     * @throws DataException
     */
    public static ResponseDTO sendLocationMessage(DataUtil dataUtil, LocationTrackerDTO track) throws
            Exception, DataException {
        try {
            System.out.println("Monitor ID's " + track.getMonitorList()
                    + " staff ids : " + track.getStaffList());
        } catch (Exception e) {

        }
        ResponseDTO resp = new ResponseDTO();
        String mTrack = gson.toJson(track);

        List<GCMResult> gCMResults;
        //send message to Google servers
        Sender sender = new Sender(GoogleCloudMessagingRegistrar.API_KEY);
        Message message = new Message.Builder()
                .addData("track", mTrack)
                .collapseKey("" + System.currentTimeMillis())
                .build();

        List<String> registrationIDs = new ArrayList<>();
//        registrationIDs.add("ThisisadummyregistrationtotestErrors");
        if (track.getMonitorList() != null && !track.getMonitorList().isEmpty()) {
            Query q = dataUtil.getEntityManager().createNamedQuery("GcmDevice.findByMonitorIDs", GcmDevice.class);
            q.setParameter("list", track.getMonitorList());
            List<GcmDevice> gList = q.getResultList();
            gList.stream().forEach((m) -> {
                registrationIDs.add(m.getRegistrationID());
            });
        }
        if (track.getStaffList() != null && !track.getStaffList().isEmpty()) {
            Query q = dataUtil.getEntityManager().createNamedQuery("GcmDevice.findByStaffIDs", 
                    GcmDevice.class);
            q.setParameter("list", track.getStaffList());
            List<GcmDevice> gList = q.getResultList();
            gList.stream().forEach((m) -> {
                registrationIDs.add(m.getRegistrationID());
            });
        }

        if (registrationIDs.isEmpty()) {
            LOG.log(Level.SEVERE, "#### sendLocationMessage: No gcm location registrationIDs found ");
            resp.setMessage("No staff found or their devices are not registered for Monitor messaging");
            resp.setStatusCode(StatusCode.ERROR_GCM);
            return resp;
        }

        LOG.log(Level.INFO, "sendLocationMessage: About to send tracks via GCM: {0}", registrationIDs.size());
        GCMResult gcmr;
        String rMsg;
        if (registrationIDs.size() == 1) {
            Result result = sender.send(message, registrationIDs.get(0), RETRIES);
            gcmr = handleResult(dataUtil, result, registrationIDs.get(0));
            if (gcmr.isOK) {
                rMsg = "Google GCM - message has been sent to Google servers";
            } else {
                rMsg = "Google GCM - message has not been sent. Error occured";
                resp.setStatusCode(StatusCode.ERROR_GCM);
                resp.setMessage(rMsg);
            }
            resp.setMessage(rMsg);
            return resp;
        } else {
            gCMResults = sendBatches(dataUtil, sender, message, registrationIDs);

        }
        int errors = 0, kool = 0;
        for (GCMResult res : gCMResults) {
            if (res.isOK) {
                kool++;
            } else {
                errors++;
            }
        }

        LOG.log(Level.INFO, "GCM batch send results, success: {0} errors: {1}", new Object[]{kool, errors});
        resp.setMessage("GCM FindMe messages sent: " + kool + " errors: " + errors);
        return resp;
    }

    /**
     * Message sent to Staff to alert them that a monitor has no projects
     * assigned to them
     *
     * @param em
     * @param companyID
     * @param monitorID
     * @return
     * @throws Exception
     * @throws DataException
     */
    public static ResponseDTO sendNoProjectsAssignedMessage(DataUtil dataUtil, Integer companyID, Integer monitorID) throws
            Exception, DataException {
        ResponseDTO resp = new ResponseDTO();

        List<GCMResult> gCMResults;

        //send message to Google servers
        Sender sender = new Sender(GoogleCloudMessagingRegistrar.API_KEY);
        Message message = new Message.Builder()
                .addData("message", "Please assign projects to Monitor")
                .addData("monitorID", monitorID.toString())
                .addData("dateStamp", "" + new Date().getTime()).build();

        Query q = dataUtil.getEntityManager().createNamedQuery("GcmDevice.findCompanyStaffDevices", GcmDevice.class);
        q.setParameter("companyID", companyID);
        List<GcmDevice> gList = q.getResultList();
        List<String> registrationIDs = new ArrayList<>();
        gList.stream().forEach((m) -> {
            registrationIDs.add(m.getRegistrationID());
        });
        if (registrationIDs.isEmpty()) {
            LOG.log(Level.SEVERE, "#### sendNoProjectsAssignedMessage: No gcm registrationIDs found ");
            resp.setMessage("No staff found or their devices are not registered");
            resp.setStatusCode(RETRIES);
//            addErrorStore(em,StatusCode.ERROR_GCM, "#### No devices found to send messages to.");
            return resp;
        }
        GCMResult gcmr = null;

        String rMsg;
        if (registrationIDs.size() == 1) {
            Result result = sender.send(message, registrationIDs.get(0), RETRIES);
            gcmr = handleResult(dataUtil, result, registrationIDs.get(0));
            if (gcmr.isOK) {
                rMsg = "Google GCM - message has been sent to Google servers";
            } else {
                rMsg = "Google GCM - message has not been sent. Error occured";
                resp.setStatusCode(StatusCode.ERROR_GCM);
                resp.setMessage(rMsg);
//                addErrorStore(em,StatusCode.ERROR_GCM, rMsg);
            }
            resp.setMessage(rMsg);
            return resp;
        } else {
            gCMResults = sendBatches(dataUtil, sender, message, registrationIDs);

        }
        int errors = 0, kool = 0;
        for (GCMResult res : gCMResults) {
            if (res.isOK) {
                kool++;
            } else {
                errors++;
            }
        }

        LOG.log(Level.INFO, "GCM batch send results, success: {0} errors: {1}", new Object[]{kool, errors});

        return resp;
    }

    private static GCMResult handleResult(DataUtil dataUtil, Result result, String registrationID)
            throws Exception {
        GCMResult gcmr = new GCMResult();
        gcmr.batchNumber = 1;

        LOG.log(Level.INFO, "Handle result from Google GCM servers: {0}", result.toString());
        if (result.getErrorCodeName() != null) {
            gcmr.isOK = false;
            if (result.getErrorCodeName().equals(
                    Constants.ERROR_NOT_REGISTERED)) {
                // TODO remove the registration from the database *****
                LOG.log(Level.SEVERE, "#### GCM device not registered");
                return gcmr;
            }
            if (result.getErrorCodeName().equals(
                    Constants.ERROR_UNAVAILABLE)) {
                LOG.log(Level.SEVERE, "#### GCM servers not available");
                return gcmr;
            }
            LOG.log(Level.SEVERE, "#### GCM message send error : {0}",
                    result.getErrorCodeName());
//            addErrorStore(em,StatusCode.ERROR_GCM,"#### GCM message send error\nErrorCodeName: " + result.getErrorCodeName());
            return gcmr;
        }

        if (result.getMessageId() != null) {
            gcmr.isOK = true;
            gcmr.message = "Result messageID from GCM: " + result.getMessageId();
            LOG.log(Level.INFO, "Result messageID from GCM: {0}", result.getMessageId());
            if (result.getCanonicalRegistrationId() != null) {
                LOG.log(Level.INFO,
                        "### Google GCM - canonical registration id found, have to update gcmdevice db ...");
     
               dataUtil.updateDevice(registrationID, result.getCanonicalRegistrationId());
                

            }
        }
        return gcmr;
    }

    private static GCMResult handleMultiCastResult(DataUtil dataUtil, MulticastResult multiCastResult, int batchNumber)
            throws Exception {
        LOG.log(Level.INFO, "handleMultiCastResult result from Google GCM servers: {0}", multiCastResult.toString());

        GCMResult gcmr = new GCMResult();
        if (multiCastResult.getFailure() == 0
                && multiCastResult.getCanonicalIds() == 0) {
            gcmr.batchNumber = batchNumber;
            gcmr.isOK = true;
            gcmr.message = "Google Cloud messages sent OK";
            gcmr.numberOfMessages = multiCastResult.getTotal();
            LOG.log(Level.INFO, "### Google Cloud message send is OK, messages: {0}", multiCastResult.getTotal());
            return gcmr;
        }
        LOG.log(Level.INFO,
                "### Google GCM - iterating through multicast Result for errors...");
        for (Result result : multiCastResult.getResults()) {
            if (result.getErrorCodeName() != null) {
                gcmr.batchNumber = batchNumber;
                gcmr.isOK = false;
                gcmr.numberOfMessages = multiCastResult.getTotal();
                if (result.getErrorCodeName().equals(
                        Constants.ERROR_NOT_REGISTERED)) {
                    gcmr.message = "GCM device not registered";
                    LOG.log(Level.SEVERE, "#### GCM device not registered");
//                    addErrorStore(em,StatusCode.ERROR_GCM,"#### GCM device not registered");
                    return gcmr;
                }
                if (result.getErrorCodeName().equals(
                        Constants.ERROR_UNAVAILABLE)) {
                    gcmr.message = "GCM servers not available";
                    LOG.log(Level.SEVERE, "#### GCM servers not available");
//                    addErrorStore(em,StatusCode.ERROR_GCM,"#### GCM servers not available");
                    return gcmr;
                }
                gcmr.message = "GCM message send error: " + result.getErrorCodeName();
                LOG.log(Level.SEVERE, "#### GCM message send error : {0}",
                        result.getErrorCodeName());

//                addErrorStore(em,StatusCode.ERROR_GCM, "#### GCM message send error\nErrorCodeName: " + result.getErrorCodeName());
                return gcmr;
            }

            if (result.getMessageId() != null) {
                LOG.log(Level.INFO, "Result messageID from GCM: {0}", result.getMessageId());
                if (result.getCanonicalRegistrationId() != null) {
                    LOG.log(Level.INFO,
                            "### Google GCM - canonical registration id found, should update db ...");
                    //update device registration id - query by gcmdevice by reg id ???????????, 
                    //yeah, do this!!!!!!
                    //TODO ................................

                }
            }
        }
        return gcmr;
    }

    private static List<GCMResult> sendBatches(DataUtil dataUtil,Sender sender, Message message, List<String> registrationIDs) throws IOException, Exception {
        GCMResult gcmr = null;
        List<GCMResult> gCMResults = new ArrayList<>();
        if (registrationIDs.size() < MAX_MESSAGES_IN_BATCH) {
            MulticastResult multiCastResult = sender.send(
                    message, registrationIDs, RETRIES);
            gcmr = handleMultiCastResult(dataUtil, multiCastResult, 1);
            gCMResults.add(gcmr);
        } else {
            int batches = registrationIDs.size() / MAX_MESSAGES_IN_BATCH;
            int rem = registrationIDs.size() % MAX_MESSAGES_IN_BATCH;
            if (rem > 0) {
                batches++;
            }
            LOG.log(Level.OFF, "multiCast message batches: {0}", batches);
            int mainIndex = 0;
            for (int i = 0; i < batches; i++) {
                List<String> batch = new ArrayList<>();
                for (int j = 0; j < MAX_MESSAGES_IN_BATCH; j++) {
                    try {
                        batch.add(registrationIDs.get(mainIndex));
                        mainIndex++;
                    } catch (IndexOutOfBoundsException e) {
                    }

                }
                if (!batch.isEmpty()) {
                    MulticastResult multiCastResult = sender.send(
                            message, batch, RETRIES);
                    GCMResult xx = handleMultiCastResult(dataUtil, multiCastResult, (i + 1));
                    gCMResults.add(xx);
                    if (!xx.isOK) {
                        LOG.log(Level.OFF, "multiCast failed at batch: {0}", i);
                    } else {
                        LOG.log(Level.OFF, "multiCast batch sent OK: {0}", i);
                    }
                }
            }
        }
        return gCMResults;
    }

    private static List<String> buildListAllinCompany(EntityManager em, SimpleMessageDTO simple) {
        List<String> registrationIDs = new ArrayList<>();
        Query q = em.createNamedQuery("GcmDevice.findCompanyDevices", GcmDevice.class);
        q.setParameter("companyID", simple.getCompanyID());
        List<GcmDevice> devices = q.getResultList();

        for (GcmDevice device : devices) {
            registrationIDs.add(device.getRegistrationID());
        }

        return registrationIDs;
    }

    /**
     * Build list of GCMRegistration ID's from list of staffID's and
     * monitorID's. Each monitor or staff has a list of GCMDevice's which
     * contain the registration id
     *
     * @param em
     * @param simple
     * @return
     */
    private static List<String> buildList(EntityManager em, SimpleMessageDTO simple) {
        List<String> registrationIDs = new ArrayList<>();
        if (simple.getSimpleMessageDestinationList() != null
                && !simple.getSimpleMessageDestinationList().isEmpty()) {
            Query q = em.createNamedQuery("GcmDevice.findByMonitorIDs", GcmDevice.class);
            List<Integer> monitorIDList = new ArrayList<>();
            for (SimpleMessageDestinationDTO dest : simple.getSimpleMessageDestinationList()) {
                if (dest.getMonitorID() != null) {
                    monitorIDList.add(dest.getMonitorID());
                }
            }
            if (!monitorIDList.isEmpty()) {
                q.setParameter("list", monitorIDList);
                List<GcmDevice> gList = q.getResultList();
                gList.stream().forEach((m) -> {
                    registrationIDs.add(m.getRegistrationID());
                });
            }
        }
        if (simple.getSimpleMessageDestinationList() != null
                && !simple.getSimpleMessageDestinationList().isEmpty()) {
            Query q = em.createNamedQuery("GcmDevice.findByStaffIDs", GcmDevice.class);
            List<Integer> staffIDList = new ArrayList<>();
            for (SimpleMessageDestinationDTO dest : simple.getSimpleMessageDestinationList()) {
                if (dest.getStaffID() != null) {
                    staffIDList.add(dest.getStaffID());
                }
            }
            if (!staffIDList.isEmpty()) {
                q.setParameter("list", staffIDList);
                List<GcmDevice> gList = q.getResultList();
                gList.stream().forEach((m) -> {
                    registrationIDs.add(m.getRegistrationID());
                });
            }
        }
        LOG.log(Level.SEVERE, "registrationIDS found: {0}", registrationIDs.size());
        return registrationIDs;
    }
    static final Logger LOG = Logger.getLogger("CloudMsgUtil");

    private static class GCMResult {

        boolean isOK;
        int batchNumber, numberOfMessages;
        String message;
    }
}
