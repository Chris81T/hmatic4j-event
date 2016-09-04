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

package de.chrthms.hmatic4j.event.server.impl;

import de.chrthms.hmatic4j.event.core.HMEventRegistry;
import de.chrthms.hmatic4j.event.core.impl.HMEventRegistryImpl;
import de.chrthms.hmatic4j.event.exceptions.HMInvalidMethodCallTypesException;
import de.chrthms.hmatic4j.event.exceptions.HMUnsupportedLogicLayerMethodException;
import de.chrthms.hmatic4j.event.server.LogicLayerHandler;
import de.chrthms.hmatic4j.event.server.LogicLayerMethods;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christian
 */
public class LogicLayerHandlerImpl implements LogicLayerHandler {

    private static final Logger LOG = LoggerFactory.getLogger(LogicLayerHandlerImpl.class);
    
    private final List<String> supportedMethods;

    public LogicLayerHandlerImpl() {
        this.supportedMethods = new ArrayList<>();
        this.supportedMethods.add(LogicLayerMethods.SYSTEM_MULTICALL.getName());
        this.supportedMethods.add(LogicLayerMethods.SYSTEM_LIST_METHODS.getName());
        this.supportedMethods.add(LogicLayerMethods.EVENT.getName());
        this.supportedMethods.add(LogicLayerMethods.LIST_DEVICES.getName());
        this.supportedMethods.add(LogicLayerMethods.NEW_DEVICES.getName());
        this.supportedMethods.add(LogicLayerMethods.DELETE_DEVICES.getName());
        this.supportedMethods.add(LogicLayerMethods.UPDATE_DEVICE.getName());
        this.supportedMethods.add(LogicLayerMethods.REPLACE_DEVICE.getName());
        this.supportedMethods.add(LogicLayerMethods.READDED_DEVICE.getName());
        
    }
    
    @Override
    public Object[] multicall(List<Map<String, Object>> requests) {
        LOG.info("\n>>>>>>>>>>> system.multicall >>>>>>>>>>>>");
        LOG.info("requests = {}", requests);
        LOG.info("<<<<<<<<<<< system.multicall <<<<<<<<<<<<\n");
        
        AtomicReference<List<Object>> methodResults = new AtomicReference<>();
        methodResults.set(new ArrayList<>());
        
        requests.forEach(request -> {
            
            /**
             * request should be an HashMap with two entries:
             * entry 1: methodName -> value: String
             * entry 2: params -> value: Object[] 
             */
            try {

                final String methodName = (String) request.get("methodName");
                final Object[] params = (Object[]) request.get("params");

                LOG.info("system.multicall -> incoming methodCall with methodName = {} and params = {}", methodName, params);

                // check, if given methodName is supported...
                LogicLayerMethods method = LogicLayerMethods.get(methodName);
                if (method != null) {
                    
                    switch (method) {
                        case DELETE_DEVICES:
                            LOG.info("TODO TODO TODO switch(method) --> DELETE_DEVICES");
                            break;
                        case EVENT:
                            final String interfaceId = (String) params[0];
                            final String address = (String) params[1];
                            final String valueKey = (String) params[2];
                            event(interfaceId, address, valueKey, params[3]);
                            methodResults.get().add(Void.class);
                            break;
                        case LIST_DEVICES:
                            LOG.info("TODO TODO TODO switch(method) --> LIST_DEVICES");
                            break;
                        case NEW_DEVICES:
                            LOG.info("TODO TODO TODO switch(method) --> NEW_DEVICES");
                            break;
                        case READDED_DEVICE:
                            LOG.info("TODO TODO TODO switch(method) --> READDED_DEVICE");
                            break;
                        case REPLACE_DEVICE:
                            LOG.info("TODO TODO TODO switch(method) --> REPLACE_DEVICE");
                            break;
                        case SYSTEM_LIST_METHODS:
                            LOG.info("TODO TODO TODO switch(method) --> SYSTEM_LIST_METHODS");
                            break;
                        case UPDATE_DEVICE:
                            LOG.info("TODO TODO TODO switch(method) --> UPDATE_DEVICE");
                            break;     
                            
                        
                        
                            
                    }

                } else {
                    throw new HMUnsupportedLogicLayerMethodException("given methodName " + 
                            methodName +
                            " is not supported by this handler!");
                }

            } catch (ClassCastException e) {
                throw new HMInvalidMethodCallTypesException("system.multicall execution failed during cast of methodCall information.", e);
            }
                            
        });
               
        List<Object> resultList = methodResults.get();
        return resultList.toArray(new Object[resultList.size()]);
    }
        
    @Override
    public String[] listMethods(String interfaceId) {
        LOG.info("\n>>>>>>>>>>> system.listMethods >>>>>>>>>>>>");
        LOG.info("interfaceId = {}", interfaceId);
        LOG.info("supportedMethods = {}", supportedMethods);
        LOG.info("<<<<<<<<<<< system.listMethods <<<<<<<<<<<<\n");
        return supportedMethods.toArray(new String[supportedMethods.size()]);
    }
    
    @Override
    public void event(String interfaceId, String address, String valueKey, Object value) {
        LOG.info("\n>>>>>>>>>>> EVENT >>>>>>>>>>>>");
        LOG.info("interfaceId = {}", interfaceId);
        LOG.info("address = {}", address);
        LOG.info("valueKey = {}", valueKey);
        LOG.info("value = {}", value);
        LOG.info("<<<<<<<<<<< EVENT <<<<<<<<<<<<\n");
        
        String[] splittedAddress = address.split(":");
        
        final String deviceAddress = splittedAddress[0];
        final String deviceChannel = splittedAddress.length > 1 ? splittedAddress[1] : null;
        
        HMEventRegistry registry = HMEventRegistryImpl.getInstance();
        registry.getAllObservers().forEach(observer -> observer.handleEvent(deviceAddress, deviceChannel, valueKey, value));
    }

    @Override
    public Object[] listDevices(String interfaceId) {
        LOG.info("\n>>>>>>>>>>> listDevices <<<<<<<<<<<");
        LOG.info("interfaceId = {}\n", interfaceId);
        return new Object[]{};
    }

    @Override
    public void newDevices(String interfaceId, Object[] deviceDescriptions) {
        LOG.info("\n>>>>>>>>>>> newDevices <<<<<<<<<<<");
        LOG.info("interfaceId = {}", interfaceId);
        LOG.info("deviceDescriptions = {}\n", deviceDescriptions);
    }

    @Override
    public void deleteDevices(String interfaceId, String[] addresses) {
        LOG.info("\n>>>>>>>>>>> deleteDevices <<<<<<<<<<<");
        LOG.info("interfaceId = {}", interfaceId);
        LOG.info("addresses = {}\n", addresses);
    }

    @Override
    public void updateDevice(String interfaceId, String address, int hint) {
        LOG.info("\n>>>>>>>>>>> updateDevice <<<<<<<<<<<");
        LOG.info("interfaceId = {}", interfaceId);
        LOG.info("address = {}", address);
        LOG.info("hint = {}\n", hint);
    }

    @Override
    public void replaceDevice(String interfaceId, String oldDeviceAddress, String newDeviceAddress) {
        LOG.info("\n>>>>>>>>>>> replaceDevice <<<<<<<<<<<");
        LOG.info("interfaceId = {}", interfaceId);
        LOG.info("oldDeviceAddress = {}", oldDeviceAddress);
        LOG.info("newDeviceAddress = {}\n", newDeviceAddress);
    }

    @Override
    public void readdedDevice(String interfaceId, String[] addresses) {
        LOG.info("\n>>>>>>>>>>> readdedDevice <<<<<<<<<<<");
        LOG.info("interfaceId = {}", interfaceId);
        LOG.info("addresses = {}\n", addresses);
    }

}
