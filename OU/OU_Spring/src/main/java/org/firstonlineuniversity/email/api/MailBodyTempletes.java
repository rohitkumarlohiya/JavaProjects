package org.firstonlineuniversity.email.api;

import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class MailBodyTempletes {

	/*
	 * Email body templates
	 */
	public String getEmailTemplete(String templeteName) {

		Resource resource = new ClassPathResource(templeteName + ".html");
		String bodyContent = null;
		try {
			bodyContent = IOUtils.toString(resource.getInputStream(), "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bodyContent;
	}
}
