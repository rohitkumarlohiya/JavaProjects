package org.firstonlineuniversity.controller.validation;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.CourseFiles;
import org.firstonlineuniversity.models.status.ValidationEntity;
import org.firstonlineuniversity.services.CustomServices;
import org.firstonlineuniversity.services.DataServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/validation")
@PropertySource("classpath:message.properties")
public class ValidationController {
	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomServices customServices;

	static final Logger logger = Logger.getLogger(ValidationController.class);

	@RequestMapping(value = "/content-key", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ValidationEntity payment(@RequestBody ValidationEntity entity)
			throws HibernateException, Exception {
		boolean flag = dataServices.validateExistance("contentKey", entity
				.getInputString().trim(), CourseFiles.class);
		return new ValidationEntity(entity.getInputString(), flag);
	}

	@RequestMapping(value = "/course-key", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ValidationEntity courseKey(@RequestBody ValidationEntity entity)
			throws HibernateException, Exception {
		boolean flag = customServices.existsCourseKey(entity.getInputString());
		return new ValidationEntity(entity.getInputString(), flag);
	}
}
