/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.utilx;

import com.boha.monitor.dto.ChatMessageDTO;
import com.google.gson.Gson;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aubreyM
 */
public class Content implements Serializable {

public List<String> registration_ids;
public Map<String,String> data;

public void addRegId(String regId){
    if(registration_ids == null)
        registration_ids = new LinkedList<String>();
    registration_ids.add(regId);
}

public void createData(String title, String message){
    if(data == null)
        data = new HashMap<String,String>();

    data.put("title", title);
    data.put("message", message);
}
public void createData(ChatMessageDTO msg){
    if(data == null)
        data = new HashMap<String,String>();

    data.put("message", gson.toJson(msg));
}

static final Gson gson = new Gson();
}

