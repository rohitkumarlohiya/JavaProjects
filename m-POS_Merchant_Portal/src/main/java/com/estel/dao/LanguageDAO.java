package com.estel.dao;

import com.estel.entity.Language;

import java.util.List;

public interface LanguageDAO extends GenericDAO<Language, Long> {

	public Language addLanguage(Language Language);
	public Language getLanguageById(Long Id);
	public List<Language> listLanguages();
	public void updateLanguage(Language Language);
}
