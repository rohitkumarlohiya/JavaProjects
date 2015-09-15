package com.estel.service;

import com.estel.dao.GenericDAOImpl;
import com.estel.dao.LanguageDAO;
import com.estel.entity.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl extends GenericDAOImpl<Language, Long> implements
        LanguageService {

	@Autowired
	private LanguageDAO LanguageDAO;


	public Language addLanguage(Language Language) {
		return LanguageDAO.addLanguage(Language);
	}


	public Language getLanguageById(Long Id) {
		return LanguageDAO.getLanguageById(Id);
	}


	public List<Language> listLanguages() {
		return LanguageDAO.listLanguages();
	}


	public void updateLanguage(Language Language) {
		LanguageDAO.updateLanguage(Language);
		
	}

	
}
