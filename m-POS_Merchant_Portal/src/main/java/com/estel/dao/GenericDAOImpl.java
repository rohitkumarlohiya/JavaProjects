package com.estel.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericDAOImpl<T, PK extends Serializable> implements
		GenericDAO<T, PK> {
	@Autowired
	private SessionFactory sessionFactory;

	private Class<T> type;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public GenericDAOImpl() {
		this.type = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

	}

	@SuppressWarnings("unchecked")
	public PK create(T o) {
		return (PK) sessionFactory.getCurrentSession().save(o);
	}

	@SuppressWarnings("unchecked")
	public T read(PK id) {
		return (T) sessionFactory.getCurrentSession().get(type, id);
	}

	public void update(T o) {
		sessionFactory.getCurrentSession().update(o);
	}

	public void delete(T o) {
		sessionFactory.getCurrentSession().delete(o);
	}

}
