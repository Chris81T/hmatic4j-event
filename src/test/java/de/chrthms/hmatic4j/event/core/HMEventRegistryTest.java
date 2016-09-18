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
package de.chrthms.hmatic4j.event.core;

import de.chrthms.hmatic4j.event.client.HMEventBuilder;
import de.chrthms.hmatic4j.event.client.HMObserver;
import de.chrthms.hmatic4j.event.client.enums.ValueKey;
import de.chrthms.hmatic4j.event.core.impl.HMEventBuilderImpl;
import de.chrthms.hmatic4j.event.core.impl.HMEventRegistryImpl;
import de.chrthms.hmatic4j.event.core.impl.HMObserverImpl;
import de.chrthms.hmatic4j.event.server.LogicLayerHandler;
import de.chrthms.hmatic4j.event.server.impl.LogicLayerHandlerImpl;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author christian
 */
public class HMEventRegistryTest {

    public HMEventRegistryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void checkSimpleUnregister() {

        HMEventRegistry registry = HMEventRegistryImpl.getInstance();

        String registryId = registry.register(new HMObserverImpl());

        assertTrue(registry.getAllObservers().size() == 1);

        registry.unregister(registryId);

        assertTrue(registry.getAllObservers().isEmpty());

    }

    @Test
    public void checkUnregisterAfterEventHandling() {

        HMEventRegistry registry = HMEventRegistryImpl.getInstance();                 
        LogicLayerHandler handler = new LogicLayerHandlerImpl();
        HMEventBuilder builder = new HMEventBuilderImpl();
        
        HMObserver observer = builder.observe();
        String registryId = observer.start((event) -> {
            
            System.out.println("Incoming Test event with details = " + event);
            
        }).orElse(null);
                
        handler.event("1234567:89", "9876543:21", ValueKey.PONG.toString(), "SOME_VALUE");
        
        builder.unobserve(registryId);

        handler.event("1234567:89", "9876543:21", ValueKey.PONG.toString(), "SOME_VALUE");
        
    }

}
