package com.estel.dao;

import com.estel.entity.Merchant;
import com.estel.entity.Pin;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Repository
public class PinDAOImpl extends GenericDAOImpl<Pin, Long> implements PinDAO {

    @Autowired
    MerchantDAO merchantDAO;

    @Autowired
    PinTypeDAO pinTypeDAO;

    @Autowired
    DeviceDAO deviceDAO;

    @Autowired
    Pin pin;

    @Autowired
    MessageSource messageSource;


    @Transactional
    public Pin addPin(Pin Pin) {
        return (Pin) this.read(this.create(Pin));
    }


    @Transactional
    public Pin getPinById(Long Id) {
        return (Pin) this.read(Id);
    }


    @SuppressWarnings("unchecked")
    @Transactional
    public List<Pin> listPins() {
        return getSession().createQuery("from Pin").list();
    }


    @Transactional
    public void updatePin(Pin Pin) {
        getSession().update(Pin);

    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Pin verifyPinByPinTypeAndAgentId(String pinValue, Long pinTypeId, Long agentId) {

        Merchant merchant = merchantDAO.getMerchantById(agentId);

        String sql = "from Pin where pinValue=:pinValue and pinTypeId=:pinTypeId and merchant=:merchant";
        Query query = getSession().createQuery(sql);

        query.setString("pinValue", pinValue);
        query.setLong("pinTypeId", pinTypeId);
        query.setEntity("merchant", merchant);

        List<Pin> pinList = query.list();
        if (pinList.size() <= 0) {
            return null;
        }
        return pinList.get(0);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Pin getPinByAgentId(Long agentId) {

        Merchant merchant = merchantDAO.getMerchantById(agentId);

        String sql = "from Pin where merchant=:merchant";
        Query query = getSession().createQuery(sql);
        query.setEntity("merchant", merchant);
        List<Pin> pinList = query.list();
        if (pinList.size() <= 0) {
            return null;
        }
        return pinList.get(0);
    }

    @Transactional
    public Pin addPinByPinTypeAndAgentId(String pinValue, Long agentClientId, Long pinTypeId, Long agentId) {
        //madatory field set
        /*pin_value
        pin_type_id
        pin_merchant_id
        pin_ts*/

        this.pin.setPinValue(pinValue);
        this.pin.setPinTypeId(pinTypeId);
        this.pin.setMerchant(merchantDAO.getMerchantById(agentId));
        this.pin.setPinTs(new java.sql.Date(System.currentTimeMillis()));

        this.pin.setPinWrongAttemptCtr(0L);
        //madatory field set

        if (agentClientId != null)
            this.pin.setPinDeviceId(agentClientId);
        this.pin.setMerchant(merchantDAO.getMerchantById(agentId));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, Integer.parseInt(messageSource.getMessage("expiry.date.for.pin",null,"default-expiry.date.for.pin",null)));
        this.pin.setPinExpiryDate(new java.sql.Date(calendar.getTimeInMillis()));

        return (Pin) this.read(this.create(this.pin));

    }


    @Transactional
    public void updatePinByAgentIdAndClientId(String pinValue, Long agentClientId, Long pinTypeId, Long agentId) {
        Merchant merchant = merchantDAO.getMerchantById(agentId);

        String sql = "from Pin where pinDeviceId=:agentClientId and pinTypeId=:pinTypeId and merchant=:merchant";
        Query query = getSession().createQuery(sql);

        query.setLong("agentClientId", agentClientId);
        query.setLong("pinTypeId", pinTypeId);
        query.setEntity("merchant", merchant);

        List<Pin> pinList = query.list();

        pin = pinList.get(0);
        pin.setPinValue(pinValue);
        getSession().update(pin);
    }

    @Transactional
    public void updatePinByAgentId(String pinValue, Long pinTypeId, Long agentId) {
        Merchant merchant = merchantDAO.getMerchantById(agentId);

        String sql = "from Pin where pinTypeId=:pinTypeId and merchant=:merchant";
        Query query = getSession().createQuery(sql);

        query.setLong("pinTypeId", pinTypeId);
        query.setEntity("merchant", merchant);

        List<Pin> pinList = query.list();

        pin = pinList.get(0);
        pin.setPinValue(pinValue);
        getSession().update(pin);
    }
}
