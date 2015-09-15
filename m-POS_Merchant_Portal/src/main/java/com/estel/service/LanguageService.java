package com.estel.service;

import com.estel.entity.Language;

import java.util.List;


public interface LanguageService {

	public Language addLanguage(Language Language);
	public Language getLanguageById(Long Id);
	public List<Language> listLanguages();
	public void updateLanguage(Language Language);
}
