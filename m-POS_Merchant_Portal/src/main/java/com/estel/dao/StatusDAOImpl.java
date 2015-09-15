package com.estel.dao;

import com.estel.entity.Status;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StatusDAOImpl extends GenericDAOImpl<Status, Long> implements StatusDAO {
	

	@Transactional
	public Status addStatus(Status Status) {
		return (Status) this.read(this.create(Status));
	}


	@Transactional
	public Status getStatusById(Long Id) {
		return (Status) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Status> listStatuss() {
		return getSession().createQuery("from Status").list();
	}


	@Transactional
	public void updateStatus(Status Status) {
		getSession().update(Status);
		
	}

    @SuppressWarnings("unchecked")
    @Transactional
    public Status getStatusByCode(String statusCode)
    {
        String sql = "from Status where statusCode=:statusCode";
        Query query = getSession().createQuery(sql);
        query.setString("statusCode", statusCode);
        List<Status> statusList = query.list();
        if (statusList.size() <= 0) {
            return null;
        }
        return statusList.get(0);


    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<Status> listStatussByCode(String[] statusCodes)
    {
        String sql = "from Status where ";

        String str ="";
        for(int i=1 ;i <= statusCodes.length ;i++)
        {
            str += "statusCode=:statusCode" + i;
            if(i < statusCodes.length)
                str += " or ";
        }

        sql += str;

        Query query = getSession().createQuery(sql);

        for(int i=1 ;i <= statusCodes.length ;i++)
        {
            query.setString("statusCode"+i, statusCodes[i-1]);
        }

        List<Status> statusList = query.list();
        if (statusList.size() <= 0) {
            return null;
        }
        return statusList;
    }

}
