package com.aem.mywknd.config;

import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = aemConfig.class, immediate = true)
@Designate(ocd = ObjectFactoryConfigBatch.class, factory = true)
public class AemConfigImpl implements aemConfig {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private String data;
	private ObjectFactoryConfigBatch objectFactoryConfigBatch;

	
	public String getName() {
		// TODO Auto-generated method stub
		return objectFactoryConfigBatch.name();
	}

	
	public String getPassword() {
		// TODO Auto-generated method stub
		return objectFactoryConfigBatch.password();
	}
	
	@Activate
	@Modified
	protected void activate(final ObjectFactoryConfigBatch objectFactoryConfigBatch) {
		this.data = PropertiesUtil.toString(objectFactoryConfigBatch.name() + " - " + objectFactoryConfigBatch.password(), "No Config Found");
		logger.info("Orchard read the data : " +data);
		this.objectFactoryConfigBatch = objectFactoryConfigBatch;
	}

}