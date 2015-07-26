/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.util;

import com.boha.monitor.data.Chat;
import com.boha.monitor.data.ChatMember;
import com.boha.monitor.data.ChatMessage;
import com.boha.monitor.data.ErrorStore;
import com.boha.monitor.data.GcmDevice;
import com.boha.monitor.data.Monitor;
import com.boha.monitor.data.Staff;
import com.boha.monitor.dto.ChatDTO;
import com.boha.monitor.dto.ChatMemberDTO;
import com.boha.monitor.dto.ChatMessageDTO;
import com.boha.monitor.dto.transfer.ResponseDTO;
import com.google.android.gcm.server.Constants;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class handles the messaging to Google Cloud Messaging servers
 *
 * @author aubreyM
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@Deprecated
public class CloudMsgUtil {

    @PersistenceContext
    EntityManager em;

    private static final int RETRIES = 5;
    public static final String API_KEY = "AIzaSyC773Vu6nwyqVSt3HWcDetwbgW23Vj2OT0";
    //AIzaSyC773Vu6nwyqVSt3HWcDetwbgW23Vj2OT0

    static final Gson gson = new Gson();

    public ResponseDTO sendChatMessage(ChatMessageDTO msg) throws GCMException {
        ResponseDTO resp = new ResponseDTO();
        resp.setStatusCode(0);
        msg.setDateSent(new Date().getTime());

        try {
            ChatMessage cm = new ChatMessage();
            cm.setChat(em.find(Chat.class, msg.getChat().getChatID()));

            if (msg.getStaff() != null) {
                cm.setStaff(em.find(Staff.class, msg.getStaff().getStaffID()));
            }
            if (msg.getMonitor() != null) {
                cm.setMonitor(em.find(Monitor.class, msg.getMonitor().getMonitorID()));
            }

            cm.setDateSent(new Date());
            cm.setMessage(msg.getMessage());
            cm.setLatitude(msg.getLatitude());
            cm.setLongitude(msg.getLongitude());
            cm.setPictureFileName(msg.getPictureFileName());
            em.persist(cm);

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Failed", e);
            throw new GCMException();
        }

        String stringMessage = gson.toJson(msg);

        Query q = em.createNamedQuery("ChatMember.findByChat", ChatMember.class);
        q.setParameter("chatID", msg.getChat().getChatID());
        List<ChatMember> cmList = q.getResultList();
        List<Integer> staffList = new ArrayList<>();
        for (ChatMember cm : cmList) {
            staffList.add(cm.getStaff().getStaffID());
        }

        q = em.createNamedQuery("GcmDevice.findByStaff", GcmDevice.class);
        q.setParameter("staffList", staffList);
        List<GcmDevice> gList = q.getResultList();
        List<String> registrationIDs = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        for (GcmDevice de : gList) {
            for (ChatMember cm : cmList) {
                if (Objects.equals(cm.getStaff().getStaffID(), de.getStaff().getStaffID())) {
                    if (!map.containsKey(de.getRegistrationID())) {
                        map.put(de.getRegistrationID(), de.getRegistrationID());
                    }
                }

            }

        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            registrationIDs.add(key);

        }
        if (registrationIDs.isEmpty()) {
            LOG.log(Level.SEVERE, "#### No staff registrationIDs found ");
            resp.setMessage("No staff found or their devices are not registered");
            resp.setStatusCode(889);
            addErrorStore(889, "#### No staff devices found ", "Cloud Message Services");
            return resp;
        } else {
            LOG.log(Level.OFF, "Sending {0} messages to GCM", registrationIDs.size());
        }
        Content content = new Content();
        for (String id : registrationIDs) {
            content.addRegId(id);
        }
        content.createData("Title", stringMessage);
        speakToGoogle(content, registrationIDs);
        return resp;
    }

    public List<GcmDevice> sendRequestForTalk(ChatDTO ch, String msg, List<ChatMemberDTO> list) throws
            Exception, DataException {
        long start = System.currentTimeMillis();
        List<GcmDevice> validList = new ArrayList<>();
        LOG.log(Level.INFO, "#### send message to Google servers");
        List<Integer> staffList = new ArrayList<>();
        for (ChatMemberDTO cm : list) {
            staffList.add(cm.getStaff().getStaffID());
        }
        Query q = em.createNamedQuery("GcmDevice.findByStaff", GcmDevice.class);
        q.setParameter("staffList", staffList);
        List<GcmDevice> gList = q.getResultList();
        List<String> registrationIDs = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();

        for (GcmDevice m : gList) {
            for (ChatMemberDTO cm : list) {
                if (Objects.equals(cm.getStaff().getStaffID(), m.getStaff().getStaffID())) {
                    if (map.containsKey(m.getRegistrationID())) {
                        continue;
                    }
                    map.put(m.getRegistrationID(), m.getRegistrationID());
                    if (!validList.contains(m)) {
                        validList.add(m);
                    }
                }

            }

        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String string = entry.getKey();
            registrationIDs.add(string);

        }
        if (registrationIDs.isEmpty()) {
            LOG.log(Level.SEVERE, "#### No staff registrationIDs found ");
            throw new Exception();
        } else {
            LOG.log(Level.OFF, "Sending initial {0} messages to GCM", registrationIDs.size());
        }

        Content content = new Content();
        for (String id : registrationIDs) {
            content.addRegId(id);
        }
        ChatMessageDTO mm = new ChatMessageDTO();
        mm.setMessage(msg);
        mm.setDateSent(new Date().getTime());
        mm.setChat(ch);
        mm.setStaff(ch.getStaff());
        content.createData("Title", gson.toJson(mm));
        speakToGoogle(content, registrationIDs);

        long end = System.currentTimeMillis();
        LOG.log(Level.OFF, "sendRequestForTalk, elapsed: {0}", Elapsed.getElapsed(start, end));
        return validList;
    }

    static final String URL = "https://android.googleapis.com/gcm/send",
            CONTENT_TYPE = "application/json", POST = "POST";

    private ResponseDTO speakToGoogle(Content content, List<String> regIds) {
        ResponseDTO resp = new ResponseDTO();
        try {
            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(POST);
            conn.setRequestProperty("Content-Type", CONTENT_TYPE);
            conn.setRequestProperty("Authorization", "key=" + API_KEY);
            conn.setDoOutput(true);
            ObjectMapper mapper = new ObjectMapper();
            try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
                try {
                    try {
                        mapper.writeValue(wr, content);
                    } catch (JsonGenerationException | JsonMappingException ex) {
                        LOG.log(Level.SEVERE, null, ex);
                    }
                } catch (IOException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
                wr.flush();
            }
            int responseCode = conn.getResponseCode();
            String respMsg = conn.getResponseMessage();
            if (responseCode == 200) {
                InputStream is = conn.getInputStream();
                StringBuilder out;
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                    out = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        out.append(line);
                    }
                    LOG.log(Level.OFF, "GCM Response Text\n{0}", out.toString());   //Prints the string content read from input stream
                }
                JSONObject o = new JSONObject(out.toString());

                int success = o.getInt("success");
                int failure = o.getInt("failure");
                int canonicalIDs = o.getInt("canonical_ids");
                int index = 0;
                Query query = em.createNamedQuery("GcmDevice.findByRegistrationID", GcmDevice.class);
                if (canonicalIDs > 0) {
                    JSONArray arr = o.getJSONArray("results");
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject x = arr.getJSONObject(i);
                        try {
                            if (x.has("registration_id")) {
                                String regID = x.getString("registration_id");
                                String oldReg = regIds.get(index);
                                query.setParameter("registrationID", oldReg);
                                query.setMaxResults(1);
                                GcmDevice dev = (GcmDevice) query.getSingleResult();
                                dev.setRegistrationID(regID);
                                em.merge(dev);
                                LOG.log(Level.OFF, "GCMDevice registrationID updated");

                            }
                            index++;

                        } catch (NoResultException e) {
                            LOG.log(Level.WARNING, "No device found for gcm registrationID update");
                            index++;
                            continue;
                        }
                    }
                }
                resp.setGcmSuccess(success);
                resp.setGcmFailure(failure);
                LOG.log(Level.WARNING, "GCM success: {0} failure: {1}", new Object[]{success, failure});

            }
            LOG.log(Level.INFO, "GCM Http Response, Code: {0} msg: {1}", new Object[]{responseCode, respMsg});

        } catch (Exception e) {

        }

        return resp;

    }

    public ResponseDTO sendNoProjectsAssignedMessage(Integer companyID, Integer monitorID) throws
            Exception, DataException {
        ResponseDTO resp = new ResponseDTO();
        //send message to Google servers
        Sender sender = new Sender(API_KEY);
        Message message = new Message.Builder()
                .addData("message", "Please assign projects to Monitor")
                .addData("monitorID", monitorID.toString())
                .addData("dateStamp", "" + new Date().getTime()).build();

        Query q = em.createNamedQuery("GcmDevice.findCompanyStaffDevices", GcmDevice.class);
        q.setParameter("companyID", companyID);
        List<GcmDevice> gList = q.getResultList();
        List<String> registrationIDs = new ArrayList<>();
        for (GcmDevice m : gList) {
            registrationIDs.add(m.getRegistrationID());
        }
        if (registrationIDs.isEmpty()) {
            LOG.log(Level.SEVERE, "#### No instructor registrationIDs found ");
            resp.setMessage("No instructor found or their devices are not registered");
            resp.setStatusCode(RETRIES);
            addErrorStore(889, "#### No intructor devices found ", "Cloud Message Services");
            return resp;
        }
        boolean OK;
        String rMsg;
        if (registrationIDs.size() == 1) {
            Result result = sender.send(message, registrationIDs.get(0), RETRIES);
            OK = handleResult(result);
        } else {
            //TODO - batch messages if needed
            MulticastResult multiCastResult = sender.send(
                    message, registrationIDs, RETRIES);
            OK = handleMultiCastResultOld(multiCastResult);
        }
        if (OK) {
            rMsg = "Google GCM - message has been sent to Google servers";
        } else {
            rMsg = "Google GCM - message has not been sent. Error occured";
            resp.setStatusCode(117);
            resp.setMessage(rMsg);
            addErrorStore(889, "Google GCM - message has not been sent. Error occured", "Cloud Message Services");
        }
        resp.setMessage(rMsg);
        return resp;
    }

    public static final int GCM_MESSAGE_ERROR = 3, ALL_OK = 0;

    private int sendMessage(String json, List<String> registrationIDs) throws IOException, Exception {
        Sender sender = new Sender(API_KEY);
        Message message = new Message.Builder()
                .addData("message", json)
                .addData("dateStamp", "" + new Date().getTime()).build();

        boolean OK;
        if (registrationIDs.size() == 1) {
            Result result = sender.send(message, registrationIDs.get(0), RETRIES);
            OK = handleResult(result);
        } else {
            MulticastResult multiCastResult = sender.send(
                    message, registrationIDs, RETRIES);
            OK = handleMultiCastResultOld(multiCastResult);
        }
        if (!OK) {
            addErrorStore(889, "Google GCM - message has not been sent. Error occured", "Cloud Message Services");
            return GCM_MESSAGE_ERROR;
        }
        return ALL_OK;
    }

    private boolean handleResult(Result result)
            throws Exception {

        LOG.log(Level.INFO, "Handle result from Google GCM servers: {0}", result.toString());
        if (result.getErrorCodeName() != null) {
            if (result.getErrorCodeName().equals(
                    Constants.ERROR_NOT_REGISTERED)) {
                // TODO remove the registration from the database
                LOG.log(Level.SEVERE, "#### GCM device not registered");
                addErrorStore(889, "#### GCM device not registered", "Cloud Message Services");
                return false;
            }
            if (result.getErrorCodeName().equals(
                    Constants.ERROR_UNAVAILABLE)) {
                LOG.log(Level.SEVERE, "#### GCM servers not available");
                addErrorStore(889, "#### GCM servers not available", "Cloud Message Services");
                return false;
            }
            LOG.log(Level.SEVERE, "#### GCM message send error : {0}",
                    result.getErrorCodeName());
            addErrorStore(889, "#### GCM message send error\nErrorCodeName: " + result.getErrorCodeName(), "Cloud Message Services");
            return false;
        }

        if (result.getMessageId() != null) {
            LOG.log(Level.INFO, "Result messageID from GCM: {0}", result.getMessageId());
            if (result.getCanonicalRegistrationId() != null) {
                LOG.log(Level.INFO,
                        "### Google GCM - canonical registration id found, updating db ...");
                //TODO update device registration id
                //EntityManager em = EMUtil.getEntityManager();

            }
        }
        return true;
    }

    private boolean handleMultiCastResultOld(MulticastResult multiCastResult)
            throws Exception {
        LOG.log(Level.INFO, "Handle result from Google GCM servers: {0}", multiCastResult.toString());
        if (multiCastResult.getFailure() == 0
                && multiCastResult.getCanonicalIds() == 0) {
            LOG.log(Level.INFO, "### Google Cloud message send is OK, messages: {0}", multiCastResult.getTotal());
            return true;
        }
        LOG.log(Level.INFO,
                "### Google GCM - iterating through multicast Result for errors...");
        for (Result result : multiCastResult.getResults()) {
            if (result.getErrorCodeName() != null) {
                if (result.getErrorCodeName().equals(
                        Constants.ERROR_NOT_REGISTERED)) {
                    // TODO remove the registration from the database
                    LOG.log(Level.SEVERE, "#### GCM device not registered");
                    addErrorStore(889, "#### GCM device not registered", "Cloud Message Services");
                    return false;
                }
                if (result.getErrorCodeName().equals(
                        Constants.ERROR_UNAVAILABLE)) {
                    // TODO resubmit this message after back-off
                    LOG.log(Level.SEVERE, "#### GCM servers not available");
                    addErrorStore(889, "#### GCM servers not available", "Cloud Message Services");
                    return false;
                }
                LOG.log(Level.SEVERE, "#### GCM message send error : {0}",
                        result.getErrorCodeName());
                addErrorStore(889, "#### GCM message send error\nErrorCodeName: " + result.getErrorCodeName(), "Cloud Message Services");
                return false;
            }

            if (result.getMessageId() != null) {
                LOG.log(Level.INFO, "Result messageID from GCM: {0}", result.getMessageId());
                if (result.getCanonicalRegistrationId() != null) {
                    LOG.log(Level.INFO,
                            "### Google GCM - canonical registration id found, updating db ...");
                    //update device registration id - query by gcmdevice by reg id ???????????

                }
            }
        }
        return true;
    }
    static final Logger LOG = Logger.getLogger("CloudMsgUtil");

    public void addErrorStore(int statusCode, String message, String origin) {
        LOG.log(Level.OFF, "------ adding errorStore, message: {0} origin: {1}", new Object[]{message, origin});
        try {
            ErrorStore t = new ErrorStore();
            t.setDateOccured(new Date());
            t.setMessage(message);
            t.setStatusCode(statusCode);
            t.setOrigin(origin);
            em.persist(t);
            LOG.log(Level.INFO, "####### ErrorStore row added, origin {0} \nmessage: {1}",
                    new Object[]{origin, message});
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "####### Failed to add errorStore from " + origin + "\n" + message, e);
        }
    }

}
