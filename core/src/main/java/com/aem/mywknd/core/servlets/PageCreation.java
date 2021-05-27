package com.aem.mywknd.core.servlets;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

import org.apache.commons.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;





@Component(service = Servlet.class)

@SlingServletPaths(value = { "/bin/mypages"})

public class PageCreation extends SlingSafeMethodsServlet {


	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(PageCreation.class);

	@Override
	protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/plain");
    	resp.getWriter().write("Servlet Started\n");
        final ResourceResolver resourceResolver = req.getResourceResolver();
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        
        String csvfilename = "";
        csvfilename = req.getParameter("filename");
        csvfilename+=".csv";
        
        if(csvfilename==null || csvfilename.equals("")) {
        	resp.getWriter().write("name  provided in the url");
        	return;
        }
        
        String path = "/content/dam/mywknd/"+csvfilename;
        
        Resource res = resourceResolver.getResource(path);
	    Asset asset = res.adaptTo(Asset.class);
	    Rendition rendition = asset.getOriginal();		    
	    InputStream inputStream = rendition.adaptTo(InputStream.class);
	    String inputString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
	    String[] arrOfStr = inputString.split("\n");
        for(int i=1;i<arrOfStr.length;i++){
        	String[] indValues = arrOfStr[i].split(",");
            
        	String PARENT_PATH = "/content/mywknd/fr";
            String P_NAME = indValues[0];
            String TEMPLATE = "/conf/mywknd/settings/wcm/templates/content-page";
            String P_TITLE = indValues[1];
            String PDESC = indValues[2];
        	
            try {
    			Page page = pageManager.create(PARENT_PATH, P_NAME, TEMPLATE, P_TITLE);
    			String filePath = PARENT_PATH + "/" + P_NAME + "/jcr:content";
    			Resource pageResource = resourceResolver.getResource(filePath);
    			Node myNode = pageResource.adaptTo(Node.class);
    			myNode.setProperty("jcr:description",PDESC);
    			Session session = resourceResolver.adaptTo(Session.class);
    			session.save();
    			if (page != null) {
    				resp.getWriter().write("created : "+i+"\n");
                }
    		} catch (RepositoryException | WCMException e) {
    			e.printStackTrace();
    		}
        }
        
        
        resp.getWriter().write(" Done");
		
	}

}