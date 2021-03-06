/*
 * Copyright (c) 2018, hiwepy (https://github.com/hiwepy).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.shiro.spring.boot.openid;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.biz.utils.SubjectUtils;
import org.apache.shiro.session.Session;
import org.openid4java.discovery.DiscoveryInformation;

/**
 * Openid DiscoveryInformation Session Provider
 * @author 		： <a href="https://github.com/hiwepy">hiwepy</a>
 */
public class OpenidDiscoveryInformationSessionProvider implements OpenidDiscoveryInformationProvider {

	/**
     * 在Session中存储值的key
     */
	private String discoveredStoreKey = "openid-disc";
	
	@Override
	public void setDiscovered(ServletRequest request, ServletResponse response, DiscoveryInformation discovered) {

		// httpRequest.getSession().setAttribute("openid-disc", discovered);  
		
		Session session = SubjectUtils.getSubject().getSession(true);
		
		// store the text in the session
		session.setAttribute( getDiscoveredStoreKey(), discovered);
		
	}
	
	@Override
	public DiscoveryInformation getDiscovered(ServletRequest request, ServletResponse response) {
		
		// retrieve the previously stored discovery information  
		Session session = SubjectUtils.getSubject().getSession(true);
        DiscoveryInformation discovered = (DiscoveryInformation) session.getAttribute(getDiscoveredStoreKey());  
        
		return discovered;
	}

	public String getDiscoveredStoreKey() {
		return discoveredStoreKey;
	}

	public void setDiscoveredStoreKey(String discoveredStoreKey) {
		this.discoveredStoreKey = discoveredStoreKey;
	}
	
}
