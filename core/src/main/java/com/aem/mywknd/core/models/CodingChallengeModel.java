package com.aem.mywknd.core.models;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import jdk.internal.org.jline.utils.Log;

@Model(adaptables = Resource.class, 
adapters = CodingChallengeInterface.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CodingChallengeModel implements CodingChallengeInterface {

	private static final Logger LOG = LoggerFactory.getLogger(CodingChallengeModel.class);
	@ValueMapValue
	private List<String> codingmultifield;
	
	@ValueMapValue
	@Default(values = "{Boolean}false")
	private Boolean checkboolean;
	
	@Inject
	private String name;
	
	@ValueMapValue
	private String selectdropdown;
	
	@ValueMapValue
	String fileReference;
	
	@Override
	public String getFileReference() {
		return fileReference;
	}

	@Override
	public List<String> getCodingmultifield() {
		if (codingmultifield!=null) {
			return new ArrayList<String>(codingmultifield);
		}else {
			return Collections.emptyList();
		}
	}

	@Override
	public Boolean isCheckboolean() {
		return checkboolean;
	}

	@Override
	public String getSelectdropdown() {
		return selectdropdown;
	}

	@Override
	public String getName() {
		return name;
	}
	@PostConstruct
	public void init() {
		LOG.info("Hello-word"+" "+checkboolean+" "+name+""+codingmultifield);
		
	}
}
