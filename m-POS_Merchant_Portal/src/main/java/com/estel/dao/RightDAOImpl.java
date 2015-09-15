package com.estel.dao;

import com.estel.entity.Right;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RightDAOImpl extends GenericDAOImpl<Right, Long> implements RightDAO {
	

	@Transactional
	public Right addRight(Right Right) {
		return (Right) this.read(this.create(Right));
	}


	@Transactional
	public Right getRightById(Long Id) {
		return (Right) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Right> listRights() {
		return getSession().createQuery("from Right").list();
	}


	@Transactional
	public void updateRight(Right Right) {
		getSession().update(Right);
		
	}

}
