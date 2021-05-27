package com.aem.mywknd.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.aem.mywknd.core.utility.ResolverUtil;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;


@Component(service = CsvPageCreation.class, immediate = true)
public class CsvPageCreationImpl implements CsvPageCreation{
	
	@Reference
	private ResourceResolverFactory resourceResolverFactory;
	
	private Session session = null;
	private String user = null;

	@Override
	public String createPage() {
		ResourceResolver resolver = null;
		try {
			resolver = ResolverUtil.newResolver(resourceResolverFactory);
		} catch (LoginException e1) {
			
			e1.printStackTrace();
		}
		Resource csvResource = resolver.getResource("/content/dam/mywknd/csv_content.xlsx");
		Asset asset = csvResource.adaptTo(Asset.class);
		Rendition rendition = asset.getOriginal();
		InputStream inputStream = rendition.adaptTo(InputStream.class);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String line = "";
		String[] tempArr = null;
		try {
			while((line = br.readLine()) != null)
			{
				
				tempArr = line.split(",");
				
				String pagePath = "/content/mywknd/en";
				String pageName = tempArr[0];
				String templatePath = tempArr[1];
				String pageTitle = tempArr[2];
				String description = tempArr[3];
				Page newPage = null;
				PageManager pageManager = null;
				
				try {

					session = resolver.adaptTo(Session.class);
					pageManager = resolver.adaptTo(PageManager.class);
					newPage = pageManager.create(pagePath, pageName, templatePath, pageTitle);
					if (newPage != null) {

						user = resolver.getUserID();

						Node newNode = newPage.adaptTo(Node.class);
						Node cont = newNode.getNode("jcr:content");
						cont.setProperty("jcr:description",description);
						if (cont != null) {
							Node rootNode = session.getRootNode();
							String path = rootNode.getPath();
							Node parNode = JcrUtils.getNodeIfExists(cont, "par");
							Node imageNode = JcrUtils.getOrCreateByPath(parNode.getPath()+"/image", JcrConstants.NT_UNSTRUCTURED, session);
							Node textNode = JcrUtils.getNodeIfExists(parNode, "text");
							imageNode.setProperty("sling:resourceType", "foundation/components/image");
						    imageNode.setProperty("fileReference", "/content/dam/we-retail-screens/we-retail-instore-logo.png");
						    textNode.setProperty("text","Dummy Text");
						    session.save();

						}
					}
					
				} catch (WCMException e) {
					
					e.printStackTrace();
				} catch (PathNotFoundException e) {
					
					e.printStackTrace();
				} catch (RepositoryException e) {
				
					e.printStackTrace();
				}
				

			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		finally
		{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		

		return "Page Created";
	}

}
