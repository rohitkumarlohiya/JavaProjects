package com.estel.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    MessageSource messageSource;

    /*
      * public void sendMail(String from, String to, String subject, String msg)
      * {
      *
      * SimpleMailMessage message = new SimpleMailMessage();
      *
      * message.setFrom(from); message.setTo(to); message.setSubject(subject);
      * message.setText(msg); mailSender.send(message); }
      */

    @SuppressWarnings("rawtypes")
    public void sendEmail(final String toEmailAddresses,
                          final String subject,final String vmFileName, final Object arr[]) {

        sendEmail(toEmailAddresses, subject,vmFileName , null, null,
                arr);

    }

    @SuppressWarnings("rawtypes")
    public void sendEmailWithAttachment(final String toEmailAddresses,
                                        final String subject,final String vmFileName,
                                        final String attachmentPath, final String attachmentName,
                                        final Object arr[]) {

        sendEmail(toEmailAddresses, subject,vmFileName,attachmentPath,attachmentName, arr);

    }

    @SuppressWarnings("rawtypes")
    private void sendEmail(final String toEmailAddresses,
                           final String subject,final String vmFileName,
                           final String attachmentPath, final String attachmentName,
                           final Object arr[]) {

        final String fromEmailAddresses = messageSource.getMessage("smtp.from.email", null, "scripts@esteltelecom.com", null);

        final String vmName = "templates/"+vmFileName +".vm";

        final Map<String, Object> map = new HashMap<String, Object>();

        for (int i = 1; i <= arr.length; i++)
            map.put("a" + i, arr[i-1]);

        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
                        true);
                message.setTo(toEmailAddresses);
                message.setSubject(subject);
                message.setFrom(new InternetAddress(fromEmailAddresses));

                String body = VelocityEngineUtils
                        .mergeTemplateIntoString(velocityEngine,
                                vmName, "UTF-8", map);
                message.setText(body, true);

                System.out.println("body = " + body);
                final Logger logger = LoggerFactory.getLogger(EmailService.class.getName());
                logger.info(vmName + body);


                if (!StringUtils.isBlank(attachmentPath)) {
                    FileSystemResource file = new FileSystemResource(
                            attachmentPath);

                    message.addAttachment(attachmentName, file);
                }
            }
        };
        this.mailSender.send(preparator);
    }
}
