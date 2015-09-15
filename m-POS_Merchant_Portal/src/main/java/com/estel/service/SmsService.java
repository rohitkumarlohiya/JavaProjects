package com.estel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: estel
 * Date: 11/12/13
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class SmsService {

    @Autowired
    MessageSource messageSource;

    final Logger logger = LoggerFactory.getLogger(SmsService.class.getName());

    public void sendSms(final String whichFunctionality, final String toNumber, final Object arr[]) {

        String smsSending = messageSource.getMessage("sms.sending", null, "default-sms.sending", null);

        if (smsSending.equalsIgnoreCase("on")) {

            boolean responseShow = true;
            String ResponseStr = "";

            String message = messageSource.getMessage(whichFunctionality, arr, "default-" + whichFunctionality, null);

            String XMLRequest = "<estel><header><requesttype>SENDSMS</requesttype></header><request><agentcode>12345</agentcode><source>" + toNumber + "</source><transid>44097299</transid><vendorcode>MPOS</vendorcode><message>" + message + "</message></request></estel>";

            try {
                String ip = messageSource.getMessage("sms.server.ip", null, "default-sms.server.ip", null);
                String port = messageSource.getMessage("sms.server.port", null, "default-sms.server.port", null);

                Socket socket = new Socket(ip, Integer.parseInt(port));
                socket.setKeepAlive(true);
                //socket.setSoTimeout(2000);

                PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                socketOut.println(XMLRequest);

                String result = null;

                /*   if (responseShow) {
         while ((result = in.readLine()) != null) {
             ResponseStr += result;
         }
     }

     System.out.println("ResponseStr====>" + ResponseStr);         */


                socket.close();
            } catch (Exception e) {
                //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                System.out.println("Sms service is not working from server side");
                logger.info("Sms service is not working from server side");
            }
        }

    }
}
