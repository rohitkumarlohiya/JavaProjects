package com.estel.dao;

import com.estel.entity.BankAccountType;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BankAccountTypeDAOImpl extends GenericDAOImpl<BankAccountType, Long> implements BankAccountTypeDAO {



    @Transactional
    public BankAccountType addBankAccountType(BankAccountType BankAccountType) {
        return (BankAccountType) this.read(this.create(BankAccountType));
    }


    @Transactional
    public BankAccountType getBankAccountTypeById(Long Id) {
        return (BankAccountType) this.read(Id);
    }


    @SuppressWarnings("unchecked")
    @Transactional
    public List<BankAccountType> listBankAccountTypes() {
        return getSession().createQuery("from BankAccountType").list();
    }


    @Transactional
    public void updateBankAccountType(BankAccountType BankAccountType) {
        getSession().update(BankAccountType);

    }


    public BankAccountType getBankAccountTypeByAccountTypeName(String accountTypeName)
    {

        String sql = "from BankAccountType where bankAccountTypeCode=:accountTypeName";
        Query query = getSession().createQuery(sql);
        query.setString("accountTypeName", accountTypeName);
        List<BankAccountType> BankAccountTypeList = query.list();
        if (BankAccountTypeList.size() <= 0) {
            return null;
        }
        return BankAccountTypeList.get(0);
    }

}
