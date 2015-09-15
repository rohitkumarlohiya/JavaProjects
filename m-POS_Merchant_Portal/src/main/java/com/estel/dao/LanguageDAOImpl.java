package com.estel.dao;

import com.estel.entity.Language;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class LanguageDAOImpl extends GenericDAOImpl<Language, Long> implements LanguageDAO {
	

	@Transactional
	public Language addLanguage(Language Language) {
		return (Language) this.read(this.create(Language));
	}


	@Transactional
	public Language getLanguageById(Long Id) {
		return (Language) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Language> listLanguages() {
		return getSession().createQuery("from Language").list();
	}


	@Transactional
	public void updateLanguage(Language Language) {
		getSession().update(Language);
		
	}

}
