package com.estel.dao;

import com.estel.entity.DeviceOrder;
import com.estel.entity.Merchant;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DeviceOrderDAOImpl extends GenericDAOImpl<DeviceOrder, Long> implements DeviceOrderDAO {

    @Autowired
    MerchantDAO merchantDAO;

    @Autowired
    DeviceOrder deviceOrder;

    @Autowired
    StatusDAO statusDAO;

    @Autowired
    ContactdetailDAO contactdetailDAO;


    @Transactional
    public DeviceOrder addDeviceOrder(DeviceOrder DeviceOrder) {
        return (DeviceOrder) this.read(this.create(DeviceOrder));
    }


    @Transactional
    public DeviceOrder getDeviceOrderById(Long Id) {
        return (DeviceOrder) this.read(Id);
    }


    @SuppressWarnings("unchecked")
    @Transactional
    public List<DeviceOrder> listDeviceOrders() {
        return getSession().createQuery("from DeviceOrder").list();
    }


    @Transactional
    public void updateDeviceOrder(DeviceOrder DeviceOrder) {
        getSession().update(DeviceOrder);

    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<DeviceOrder> listDeviceOrderByAgentId(Long agentId) {
        Merchant merchant = merchantDAO.getMerchantById(agentId);

        String sql = "from DeviceOrder where merchant=:merchant";
        Query query = getSession().createQuery(sql);
        query.setEntity("merchant", merchant);
        List<DeviceOrder> orderList = query.list();
        if (orderList.size() <= 0) {
            return null;
        }
        return orderList;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public DeviceOrder addDeviceOrderByAgentId(Long deviceTypeId, Long statusId, String orderNumber, Long agentId, Long quantity, Long addressId) {
        //set mandatory field
        /*device_order_dt_id
        device_order_status
        device_order_order_no
        device_order_merchant_id
        device_order_quantity
        device_order_ts*/

        deviceOrder.setDeviceOrderDtId(deviceTypeId);
        deviceOrder.setDeviceOrderStatus(statusId);
        deviceOrder.setDeviceOrderOrderNo(orderNumber);
        deviceOrder.setMerchant(merchantDAO.getMerchantById(agentId));
        deviceOrder.setDeviceOrderQuantity(quantity);
        deviceOrder.setDeviceOrderTs(new java.sql.Date(System.currentTimeMillis()));
        //mandatory field set

        deviceOrder.setDeviceOrderAddressId(addressId);

        return (DeviceOrder) this.read(this.create(deviceOrder));
    }

}
