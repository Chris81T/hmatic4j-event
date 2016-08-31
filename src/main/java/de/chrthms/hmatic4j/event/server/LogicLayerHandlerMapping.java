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

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.XmlRpcHandler;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcNoSuchHandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christian
 */
public class LogicLayerHandlerMapping extends PropertyHandlerMapping {

    private static final Logger LOG = LoggerFactory.getLogger(LogicLayerHandlerMapping.class);
    
    /**
     * for added handler "" a leading dot is expected. 
     * 
     * For instance "event" has to be transformed to ".event".
     * 
     * @param pHandlerName has to be checked
     * @return expected handler
     * @throws XmlRpcNoSuchHandlerException
     * @throws XmlRpcException 
     */
    @Override
    public XmlRpcHandler getHandler(String pHandlerName) throws XmlRpcNoSuchHandlerException, XmlRpcException {
        
        LOG.trace("check given pHandlerName = {}", pHandlerName);
        
        if (!pHandlerName.contains(".")) {
            pHandlerName = "." + pHandlerName;
            LOG.debug("enrich pHandlerName with a leading dot. pHanderName = ", pHandlerName);
        }
        
        return super.getHandler(pHandlerName); 
    }

    
    
}
