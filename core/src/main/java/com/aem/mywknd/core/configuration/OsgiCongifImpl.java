package com.aem.mywknd.core.configuration;

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
	        public String serviceName() default "AEM  Service";

	        @AttributeDefinition(
	                name = "Service Count",
	                description = "Add Service Count.",
	                type = AttributeType.INTEGER
	        )
	        int getServiceCount() default 5;

	        @AttributeDefinition(
	                name = "Live Data",
	                description = "Check this to get live data.",
	                type = AttributeType.BOOLEAN)
	        boolean getLiveData() default false;

	        @AttributeDefinition(
	                name = "Countries",
	                description = "Add countries locales for which you want to run this service.",
	                type = AttributeType.STRING
	        )
	        String[] getCountries() default {"de","in"};

	        @AttributeDefinition(
	                name = "Run Modes",
	                description = "Select Run Mode.",
	                options = {
	                        @Option(label = "Author",value = "author"),
	                        @Option(label = "Publish",value = "publish"),
	                        @Option(label = "Both",value = "both")
	                },
	                type = AttributeType.STRING)
	        String getRunMode() default "both";
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
	        
	        logger.info("Orchard read the data : " +serviceName+"\n"+serviceCount+"\n"+liveData+"\n"+countries[0]+" "+countries[1]+"\n"+runModes);
     
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
