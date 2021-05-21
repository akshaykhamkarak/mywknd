package com.aem.mywknd.core.codechallenge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import javax.inject.Inject;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, 
adapters = CodeChallenge.class, 
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CodeChallengeImpl implements CodeChallenge {

	@Inject
	private List<String> codingmultifield;

	@Inject
	@Default(values = "{Boolean}false")
	private Boolean checkboolean;

	@Inject
	private String selectdropdown;

	@Inject
	private String name;

	@Override
	public String getName() {

		return name;
	}

	@Override
	public List<String> getCodingmultifield() {
		if (codingmultifield != null) {
			return new ArrayList<String>(codingmultifield);
		} else {
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

}
