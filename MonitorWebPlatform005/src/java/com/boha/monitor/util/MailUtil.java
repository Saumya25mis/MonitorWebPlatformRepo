/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.monitor.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author aubreyM
 */
@Stateless
public class MailUtil {

    @Resource(authenticationType = Resource.AuthenticationType.CONTAINER, name = "mail/smartcity")
    private Session mailSession;

    public void sendAdministratorLogs() throws MessagingException, UnsupportedEncodingException {
        
            File dir = MonitorProperties.getLogDir();
            File file = new File(dir, "server.log");

            Message m = new MimeMessage(mailSession);
            m.setSubject("Monitor Server Log");
            m.setRecipient(Message.RecipientType.TO, new InternetAddress("aubrey@mlab.co.za"));
            m.setFrom(new InternetAddress("malengadev@gmail.com", "Monitor Cloud Server"));

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Ola,\n\nThe attached is the latest log file from the Monitor Cloud Platform. There is fire where smoke can be seen!\n\nFrom your friendly server bot :)");

            // Multipart message.
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            // Attachment file from file.
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setFileName(file.getName());
            DataSource src = new FileDataSource(file.getAbsolutePath());
            messageBodyPart.setDataHandler(new DataHandler(src));
            multipart.addBodyPart(messageBodyPart);
            // Add multipart message to email.
            m.setContent(multipart);

            // Send email.
            Transport.send(m);
            System.out.println("Administrator email, with logfile attached, has been sent");
    }
}
