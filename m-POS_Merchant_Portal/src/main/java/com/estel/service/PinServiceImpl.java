package com.estel.service;

import com.estel.dao.DeviceDAO;
import com.estel.dao.GenericDAOImpl;
import com.estel.dao.MerchantDAO;
import com.estel.dao.PinDAO;
import com.estel.entity.Device;
import com.estel.entity.Pin;
import com.estel.utility.Constant;
import com.estel.utility.EncryptionAlgorithm;
import com.estel.utility.EncryptionAlgorithmImpl;
import com.estel.utility.MD5;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PinServiceImpl extends GenericDAOImpl<Pin, Long> implements
        PinService {

    @Autowired
    private PinDAO PinDAO;

    @Autowired
    private MerchantDAO merchantDAO;

    @Autowired
    private DeviceDAO deviceDAO;


    public Pin addPin(Pin Pin) {
        return PinDAO.addPin(Pin);
    }


    public Pin getPinById(Long Id) {
        return PinDAO.getPinById(Id);
    }


    public List<Pin> listPins() {
        return PinDAO.listPins();
    }


    public void updatePin(Pin Pin) {
        PinDAO.updatePin(Pin);

    }

    public Pin getPinByAgentId(Long agentId) {

        return PinDAO.getPinByAgentId(agentId);
    }


    public Pin verifyPinByPinTypeAndAgentId(String pinValue, Long pinTypeId, Long agentId) {
        return PinDAO.verifyPinByPinTypeAndAgentId(pinValue, pinTypeId, agentId);
    }


    public Pin addPinByPinTypeAndAgentId(String pinValue, Long agentClientId, Long pinTypeId, Long agentId) {
        if (pinValue != null) {
            return PinDAO.addPinByPinTypeAndAgentId(pinValue, agentClientId, pinTypeId, agentId);
        } else {
            Device device = deviceDAO.getDeviceById(agentClientId);

            String randomMPIN = RandomStringUtils.randomNumeric(4);
            //String mpin = MD5.MD5Convertor(device.getDeviceNumber() + randomMPIN).toUpperCase();
            EncryptionAlgorithm encryptionAlgorithm = new EncryptionAlgorithmImpl(Constant.SHA2);
            String mpin = encryptionAlgorithm.encrypt(device.getDeviceNumber() + randomMPIN);

            Pin pin = PinDAO.addPinByPinTypeAndAgentId(mpin, agentClientId, pinTypeId, agentId);
            pin.setPinValue(randomMPIN);
            return pin;
        }
    }


    public void updatePinByAgentIdAndClientId(String pinValue, Long agentClientId, Long pinTypeId, Long agentId) {
        if (pinValue != null) {
            PinDAO.updatePinByAgentIdAndClientId(pinValue, agentClientId, pinTypeId, agentId);
        }
    }


    public void updatePinByAgentId(String pinValue, Long pinTypeId, Long agentId) {
        if (pinValue != null) {
            PinDAO.updatePinByAgentId(pinValue, pinTypeId, agentId);
        }
    }


}
