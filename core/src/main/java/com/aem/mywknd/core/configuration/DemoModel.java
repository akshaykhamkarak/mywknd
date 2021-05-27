package com.aem.mywknd.core.configuration;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;



import com.day.cq.wcm.webservicesupport.Service;



@Model(adaptables = Service.class, 

defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DemoModel {
@OSGiService
OsgiConfig osg;

public String getServiceName() {
	return osg.getServiceName();
	

}

public int getServiceCount() {
	return osg.getServiceCount();
	
}

public boolean isLiveData() {
	return osg.isLiveData();
}

public String[] getCountries() {
	return osg.getCountries();
}

public String getRunModes() {
	
	return osg.getRunModes();
}
	
	
}
