package com.aem.mywknd.core.utility;
import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

public final class ResolverUtil {
	private ResolverUtil()
	{
		
	}
	
	public static final String SERVICE_USER = "demoservice";


	public static ResourceResolver newResolver(ResourceResolverFactory resourceResolverFactory) throws LoginException {
	
		final Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put(ResourceResolverFactory.SUBSERVICE,SERVICE_USER);
		ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(paramMap);
		return resolver;
	}

}