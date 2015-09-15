package com.estel.service;

import com.estel.entity.Pin;

import java.util.List;


public interface PinService {

	public Pin addPin(Pin Pin);
	public Pin getPinById(Long Id);
	public List<Pin> listPins();
	public void updatePin(Pin Pin);

    public Pin getPinByAgentId(Long agentId);
    public Pin verifyPinByPinTypeAndAgentId(String pinValue, Long pinTypeId,Long agentId);
    public Pin addPinByPinTypeAndAgentId(String pinValue,Long agentClientId, Long pinTypeId,Long agentId);
    public void updatePinByAgentIdAndClientId(String pinValue,Long agentClientId, Long pinTypeId,Long agentId);
    public void updatePinByAgentId(String pinValue,Long pinTypeId,Long agentId);
}
