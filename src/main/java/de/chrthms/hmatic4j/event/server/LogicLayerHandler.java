/*
 * Copyright 2016 Christian Thomas.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.chrthms.hmatic4j.event.server;

import java.util.List;
import java.util.Map;

/**
 * The "homematic" logic layer (see homematic specs) has to provide several methods.
 * 
 * The key method here is the event method to get the desired events from the
 * homematic devices (push instead of pull/polling).
 * 
 * 
 * @author christian
 */
public interface LogicLayerHandler {
    
    /**
     * provides the system.multicall procedure. Expected by homematic.
     * @param requests
     * @return 
     */
    Object multicall(List<Map<String, Object>> requests);

    String[] listMethods(String interfaceId);
    
    void event(String interfaceId, String address, String valueKey, Object value);
    
    Object[] listDevices(String interfaceId);
    
    void newDevices(String interfaceId, Object[] deviceDescriptions);
    
    void deleteDevices(String interfaceId, String[] addresses);
    
    void updateDevice(String interfaceId, String address, int hint);
    
    void replaceDevice(String interfaceId, String oldDeviceAddress, String newDeviceAddress);
    
    void readdedDevice(String interfaceId, String[] addresses);
    
}
