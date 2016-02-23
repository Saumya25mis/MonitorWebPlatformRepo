/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boha.monitor.utilx;




import com.boha.monitor.dto.CompanyDTO;
import com.boha.monitor.dto.transfer.RequestDTO;
import com.google.gson.Gson;

/**
 *
 * @author aubreyM
 */
public class JSONGrabber {

    public static void main(String[] args) {

        //create the object you want in the servlet
        RequestDTO req = new RequestDTO();
        req.setRequestType(RequestDTO.GET_STAFF_DATA);
        req.setStaffID(15);

        //turn the REquestDTO object into a JSON string
        Gson gson = new Gson();
        String json = gson.toJson(req);

        System.out.println("JSON created\n" + json);



    }
}
