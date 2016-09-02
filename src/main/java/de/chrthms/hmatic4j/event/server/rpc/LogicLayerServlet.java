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

package de.chrthms.hmatic4j.event.server.rpc;

import de.chrthms.hmatic4j.event.server.impl.LogicLayerHandlerImpl;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.XmlRpcHandlerMapping;
import org.apache.xmlrpc.webserver.XmlRpcServlet;

/**
 * Is relevant for some customization of the provided xml-rpc servlet.
 * 
 * @author christian
 */
public class LogicLayerServlet extends XmlRpcServlet {

    @Override
    protected XmlRpcHandlerMapping newXmlRpcHandlerMapping() throws XmlRpcException {
            
        LogicLayerHandlerMapping handlerMapping = new LogicLayerHandlerMapping();
        
        /**
         * this is important for methods like "event", that won't return any
         * value!
         */
        handlerMapping.setVoidMethodEnabled(true);
        
        /**
         * common expected methods without any further namespaces key. Internally
         * a leading dot is required. Check the custom HandlerMapping class.
         */
        handlerMapping.addHandler("", LogicLayerHandlerImpl.class);
        
        /**
         * relevant to provide system.multicall
         */
        handlerMapping.addHandler("system", LogicLayerHandlerImpl.class);
        
        return handlerMapping;                
    }

    
}
