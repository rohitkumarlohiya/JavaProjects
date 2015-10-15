package org.firstonlineuniversity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.models.views.ProviderSummaryView;
import org.firstonlineuniversity.services.CustomServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/coursesadmin/providersummary")
@PropertySource("classpath:message.properties")
public class ProviderSummaryController {
	@Autowired
	Environment env;

	@Autowired
	CustomServices customServices;

	static final Logger logger = Logger
			.getLogger(ProviderSummaryController.class);

	/*
	 * Provider Summary List
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<ProviderSummaryView> list(HttpServletRequest request)
			throws HibernateException, Exception {
		List<ProviderSummaryView> list = customServices.providersView();
		return list;
	}
}
