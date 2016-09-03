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
package de.chrthms.hmatic4j.event.client;

import de.chrthms.hmatic4j.event.client.enums.ValueKey;
import java.util.Optional;

/**
 *
 * @author christian
 */
public interface HMObserver {
    
    /**
     * Filter for deviceAddress
     * 
     * @param deviceAddress
     * @return the observer instance
     */
    HMObserver deviceAddress(String deviceAddress);
    
    /**
     * Filter for deviceChannel. Note this only will work, if a device address
     * is set.
     * 
     * @param deviceChannel
     * @return the observer instance
     */
    HMObserver deviceChannel(String deviceChannel);
    
    /**
     * Filter for valueKey like EVENT or STOP
     * 
     * @param valueKey
     * @return the observer instance
     */
    HMObserver valueKey(ValueKey valueKey);
    
    /**
     * Only one time observing incoming event.
     * 
     * @param onceOnly if this one is set to true (default is false), the 
     *        observer will only react one time for an incoming event, that
     *        matches for this configured (check filters) observer.
     * @return the observer instance 
     */
    HMObserver onceOnly(boolean onceOnly);
    
    /**
     * Start observing homematic events.
     * 
     * @param execution
     * @return a registryId for this observer. Is relevant for un-observe this
     *         observer. If onceOnly is set to true, no id will be returned.
     */
    Optional<String> start(HMEventExecution execution);
    
}
