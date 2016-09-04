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

package de.chrthms.hmatic4j.event.core.impl;

import de.chrthms.hmatic4j.event.client.HMObserver;
import de.chrthms.hmatic4j.event.client.enums.ValueKey;
import java.util.Optional;
import de.chrthms.hmatic4j.event.client.HMEventExecution;
import de.chrthms.hmatic4j.event.core.HMEventRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christian
 */
public class HMObserverImpl implements HMObserver {
    
    private static final Logger LOG = LoggerFactory.getLogger(HMObserverImpl.class);
    
    private String deviceAddress = null;
    private String deviceChannel = null;
    private ValueKey valueKey = null;

    private boolean onceOnly = false;

    private HMEventExecution execution = null;
    private String registryId = null;
    
    @Override
    public HMObserver deviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
        return this;
    }

    @Override
    public HMObserver deviceChannel(String deviceChannel) {
        this.deviceChannel = deviceChannel;
        return this;
    }

    @Override
    public HMObserver valueKey(ValueKey valueKey) {
        this.valueKey = valueKey;
        return this;
    }

    @Override
    public HMObserver onceOnly(boolean onceOnly) {
        this.onceOnly = onceOnly;
        return this;
    }

    @Override
    public Optional<String> start(HMEventExecution execution) {
        this.execution = execution;
        
        HMEventRegistry registry = HMEventRegistryImpl.getInstance();
        registryId = registry.register(this);
        
        if (onceOnly) {
            return Optional.of(registryId);
        }
        return Optional.empty();
    }
    
    public void handleEvent(String deviceAddress, String deviceChannel, String valueKey, Object value) {
        
        LOG.info("About to handle incoming event for deviceAddress = {}, deviceChannel = {}, " +
                "valueKey = {}, value = {}", new Object[]{deviceAddress, deviceChannel, valueKey, value});
        
        // TODO first try : ) FILTER CONDITIONS ARE MISSING
        execution.execute(deviceAddress, deviceChannel, ValueKey.valueOf(valueKey), value);
        
        if (onceOnly) {
            HMEventRegistry registry = HMEventRegistryImpl.getInstance();
            registry.unregister(registryId);
        }
    }
    
}
