package com.aem.mywknd.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
@ObjectClassDefinition(name = "Orchard Factory Service Configuration batch", description = "Orchard Factory Service Configuration batch")
public  @interface ObjectFactoryConfigBatch {
	
	@AttributeDefinition(name = "Name" , description ="name of student", type = AttributeType.STRING)
	String name();
	
	@AttributeDefinition(name = "Password" , description ="password of student", type = AttributeType.PASSWORD)
	String password();
	

}