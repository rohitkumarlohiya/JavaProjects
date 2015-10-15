package org.firstonlineuniversity.quartz.tasks;

import org.firstonlineuniversity.email.api.EmailAPI;
import org.firstonlineuniversity.email.api.MailBodyTempletes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SendingMailTask {

	@Autowired
	MailBodyTempletes mailBodyTempletes;

	@Autowired
	@Qualifier("mailService")
	EmailAPI mailService;

	@Autowired
	Environment env;

	public void printCurrentTime() {
		// confirmation mail
		String[] to = { "neel4soft@gmail.com", "navin.oracle@gmail.com" };
		String bodyContent = mailBodyTempletes
				.getEmailTemplete("recommended_courses");

		mailService.sendMail(env.getProperty("admin.email"), to,
				env.getProperty("register.subject"), bodyContent);
	}
}
