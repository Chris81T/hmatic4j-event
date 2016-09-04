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

import de.chrthms.hmatic4j.event.client.HMEventDetails;
import de.chrthms.hmatic4j.event.client.enums.ValueKey;

/**
 *
 * @author christian
 */
public class HMEventDetailsImpl implements HMEventDetails {

    private final String deviceAddress;
    private final String deviceChannel;
    private final ValueKey valueKey;
    private final Object value;

    public HMEventDetailsImpl(String deviceAddress, String deviceChannel, ValueKey valueKey, Object value) {
        this.deviceAddress = deviceAddress;
        this.deviceChannel = deviceChannel;
        this.valueKey = valueKey;
        this.value = value;
    }

    @Override
    public String getDeviceAddress() {
        return this.deviceAddress;
    }

    @Override
    public String getDeviceChannel() {
        return this.deviceChannel;
    }

    @Override
    public ValueKey getValueKey() {
        return this.valueKey;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "HMEventDetailsImpl{" + "deviceAddress=" + deviceAddress + ", deviceChannel=" + deviceChannel + 
                ", valueKey=" + valueKey + ", value=" + value + '}';
    }
    
}
