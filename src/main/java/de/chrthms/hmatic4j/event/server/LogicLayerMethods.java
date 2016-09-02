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

import java.util.Arrays;
import java.util.Optional;

/**
 *
 * @author christian
 */
public enum LogicLayerMethods {

    SYSTEM_MULTICALL("system.multicall"),
    SYSTEM_LIST_METHODS("system.listMethods"),
    EVENT("event"),
    LIST_DEVICES("listDevices"),
    NEW_DEVICES("newDevices"),
    DELETE_DEVICES("deleteDevices"),
    UPDATE_DEVICE("updateDevice"),
    REPLACE_DEVICE("replaceDevice"),
    READDED_DEVICE("readdedDevice");
    
    private final String methodName;

    private LogicLayerMethods(String methodName) {
        this.methodName = methodName;
    }

    public String getName() {
        return methodName;
    }

    public static LogicLayerMethods get(String methodName) {        
        Optional<LogicLayerMethods> optEnum = Arrays.asList(LogicLayerMethods.values())
                .stream()
                .filter(value -> value.getName().equals(methodName))
                .findFirst();
        
        return optEnum.orElse(null);
    }
    
}
