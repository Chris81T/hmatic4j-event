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

import de.chrthms.hmatic4j.event.server.LogicLayerHandler;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christian
 */
public class LogicLayerHandlerImpl implements LogicLayerHandler {

    private static final Logger LOG = LoggerFactory.getLogger(LogicLayerHandlerImpl.class);
    
    @Override
    public Object multicall(Object requests) {
        LOG.info("\n>>>>>>>>>>> system.multicall >>>>>>>>>>>>");
        LOG.info("requests class type = {}", requests.getClass());
        LOG.info("requests = {}", requests);
        LOG.info("<<<<<<<<<<< system.multicall <<<<<<<<<<<<\n");
        return null;
    }
    
    @Override
    public String[] listMethods(String interfaceId) {
        List<String> methods = new ArrayList<>();
        LOG.info("\n>>>>>>>>>>> system.listMethods >>>>>>>>>>>>");
        LOG.info("interfaceId = {}", interfaceId);

        methods.add("system.multicall");
        methods.add("system.listMethods");
        methods.add("event");
        methods.add("listDevices");
        methods.add("newDevices");
        methods.add("deleteDevices");
        methods.add("updateDevice");
        methods.add("replaceDevice");
        methods.add("readdedDevice");
        
        LOG.info("methods = {}", methods);
        LOG.info("<<<<<<<<<<< system.listMethods <<<<<<<<<<<<\n");

        return methods.toArray(new String[methods.size()]);
    }
    
    @Override
    public void event(String interfaceId, String address, String valueKey, Object value) {
        LOG.info("\n>>>>>>>>>>> EVENT >>>>>>>>>>>>");
        LOG.info("interfaceId = {}", interfaceId);
        LOG.info("address = {}", address);
        LOG.info("valueKey = {}", valueKey);
        LOG.info("value = {}", value);
        LOG.info("<<<<<<<<<<< EVENT <<<<<<<<<<<<\n");
    }

    @Override
    public Object[] listDevices(String interfaceId) {
        LOG.info("\n>>>>>>>>>>> listDevices <<<<<<<<<<<");
        LOG.info("interfaceId = {}\n", interfaceId);
        return new Object[]{};
    }

    @Override
    public void newDevices(String interfaceId, Object deviceDescriptions) {
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
