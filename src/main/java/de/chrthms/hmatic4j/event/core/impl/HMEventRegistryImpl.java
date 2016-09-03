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

import de.chrthms.hmatic4j.event.core.HMEventRegistry;
import de.chrthms.hmatic4j.event.exceptions.HMEventRegistryException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christian
 */
public class HMEventRegistryImpl implements HMEventRegistry {

    private static final Logger LOG = LoggerFactory.getLogger(HMEventRegistryImpl.class);
    
    private static HMEventRegistry registry = null;
    
    private Map<String, HMObserverImpl> observers = new HashMap<>();            
    
    private HMEventRegistryImpl() {}
    
    @Override
    public String register(HMObserverImpl observer) {
        final String registryId = UUID.randomUUID().toString();
        observers.put(registryId, observer);
        return registryId;
    }

    @Override
    public void unregister(String registryId) throws HMEventRegistryException {
        HMObserverImpl observer = observers.remove(registryId);
        if (observer == null) {
            throw new HMEventRegistryException("Observer with registryId = " +
                    registryId +
                    " does not exist. Unable to perform unregister for unknown observer!");
        }        
    }

    @Override
    public Collection<HMObserverImpl> getAllObservers() {
        return observers.values();
    }
    
    public static HMEventRegistry getInstance() {
        if (registry == null) {
            LOG.info("Create singleton instance for expected " + HMEventRegistry.class.getSimpleName());
            registry = new HMEventRegistryImpl();                    
        }
        return registry;
    }

}
