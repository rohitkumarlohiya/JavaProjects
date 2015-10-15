package org.firstonlineuniversity.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.views.SiteSummaryView;
import org.firstonlineuniversity.services.DataServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/summary/sitesummary")
@PropertySource("classpath:message.properties")
public class SiteSummaryController {

	@Autowired
	DataServices<AbstractEntity> dataServices;

	/*
	 * Site Summary List
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<SiteSummaryView> list(HttpServletRequest request)
			throws HibernateException, Exception {
		List<SiteSummaryView> list = dataServices.siteSummaryList();
		return list;
	}
}
