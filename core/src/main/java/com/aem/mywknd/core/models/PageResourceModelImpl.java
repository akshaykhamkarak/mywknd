package com.aem.mywknd.core.models;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.mywknd.service.PageResourceService;

@Model(adaptables = SlingHttpServletRequest.class, 
adapters = PageResourceModel.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class PageResourceModelImpl implements PageResourceModel {

	private static final Logger LOG = LoggerFactory.getLogger(PageResourceModelImpl.class);
	@OSGiService
	PageResourceService pageResourceService;

	@Override
	public String getPageTitle() {

		return pageResourceService.getPageTitle();
	}

	@Override
	public List<String> getPageAndNode() {

		return pageResourceService.getPageAndNode();
	}
//
//	@Override
//	public List<String> getCsvData() {
//
//		return pageResourceService.getCsvData();
//	}

}