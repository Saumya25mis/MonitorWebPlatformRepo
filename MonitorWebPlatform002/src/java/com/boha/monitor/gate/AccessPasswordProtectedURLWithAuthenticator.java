/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.gate;

/**
 *
 * @author aubreyM
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;

public class AccessPasswordProtectedURLWithAuthenticator {

    public static void main(String[] args) {

        try {
			// Sets the authenticator that will be used by the networking code
            // when a proxy or an HTTP server asks for authentication.
            Authenticator.setDefault(new CustomAuthenticator());

            URL url = new URL("http://41.160.126.146/esbapi/V1/newsCategories");

            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();

        } catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }

    }
static String username = "mobileapp";
           static String password = "mobileapp";
    public static class CustomAuthenticator extends Authenticator {

        // Called when password authorization is needed
        protected PasswordAuthentication getPasswordAuthentication() {

            // Get information about the request
            String prompt = getRequestingPrompt();
            String hostname = getRequestingHost();
            InetAddress ipaddr = getRequestingSite();
            int port = getRequestingPort();

            

            // Return the information (a data holder that is used by Authenticator)
            return new PasswordAuthentication(username, password.toCharArray());

        }

    }

}
