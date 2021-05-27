package com.aem.mywknd.core.configuration;

import java.util.ArrayList;
import java.util.List;


import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = OsgiConfig.class,immediate = true)
@Designate(ocd = OsgiCongifImpl.ServiceConfig.class )
public class OsgiCongifImpl implements OsgiConfig {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	  @ObjectClassDefinition(name="Custome configuration - OSGi Configuration",
	            description = "OSGi Configuration demo.")
	    public @interface ServiceConfig {

	        @AttributeDefinition(
	                name = "Service Name",
	                description = "Enter service name.",
	                type = AttributeType.STRING)
	        public String serviceName();

	        @AttributeDefinition(
	                name = "Service Count",
	                description = "Add Service Count.",
	                type = AttributeType.INTEGER
	        )
	        int getServiceCount();

	        @AttributeDefinition(
	                name = "Live Data",
	                description = "Check this to get live data.",
	                type = AttributeType.BOOLEAN)
	        boolean getLiveData();

	        @AttributeDefinition(
	                name = "Countries",
	                description = "Add countries locales for which you want to run this service.",
	                type = AttributeType.STRING
	        )
	        String[] getCountries();
	        
	        

	        
	        
	        @AttributeDefinition(
	                name = "Run Modes",
	                description = "Select Run Mode.",
	                options = {
	                        @Option(label = "Author",value = "author"),
	                        @Option(label = "Publish",value = "publish"),
	                        @Option(label = "Both",value = "both")
	                },
	                type = AttributeType.STRING)
	        String getRunMode();
	    }

	    private String serviceName;
	    private int serviceCount;
	    private boolean liveData;
	    private String[] countries;
	    private String runModes;
	    
	


	    @Activate
	    protected void activate(ServiceConfig serviceConfig){
	        serviceName=serviceConfig.serviceName();
	        serviceCount=serviceConfig.getServiceCount();
	        liveData=serviceConfig.getLiveData();
	        countries=serviceConfig.getCountries();
	        runModes=serviceConfig.getRunMode();
	        List<String>country=new ArrayList<>();
	      for(String str:countries) {
	    	  country.add(str);
	      }
	        logger.info("Orchard read the data : \n" +serviceName+"\n"+serviceCount+"\n"+liveData+"\n"+country+"\n"+runModes);
     
	    }

	    @Override
	    public String getServiceName() {
	        return serviceName;
	    }
	    @Override
	    public int getServiceCount() {
	        return serviceCount;
	    }
	    @Override
	    public boolean isLiveData() {
	        return liveData;
	    }
	    @Override
	    public String[] getCountries() {
	        return countries;
	    }
	    @Override
	    public String getRunModes() {
	        return runModes;
	    }

	
}
