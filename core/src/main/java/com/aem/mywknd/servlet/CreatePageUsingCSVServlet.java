package com.aem.mywknd.servlet;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.Servlet;
//import javax.servlet.ServletException;
//
//import java.util.List;
//import com.day.cq.wcm.api.Page;
//
//import com.aem.mywknd.service.CreatePageUsingCSV;
//
//import org.apache.sling.api.SlingHttpServletRequest;
//import org.apache.sling.api.SlingHttpServletResponse;
//import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
//import org.apache.sling.servlets.annotations.SlingServletPaths;
//import org.osgi.service.component.annotations.Component;
//import org.osgi.service.component.annotations.Reference;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Component(service = Servlet.class)
//@SlingServletPaths(value = { "/bin/CreatePageByCsv" })
//public class CreatePageUsingCSVServlet extends SlingSafeMethodsServlet {
//
//	private static final Logger LOG = LoggerFactory.getLogger(CreatePageUsingCSVServlet.class);
//
//	@Reference
//	private transient CreatePageUsingCSV createPageUsingCSV;
//
//	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
//			throws ServletException, IOException {
//		try {
//			PrintWriter out = response.getWriter();
//			response.setContentType("text/plain");
//			List<Page> pagesCreated = createPageUsingCSV.addPage();
//			if (pagesCreated != null) {
//				LOG.info("page is created");
//				out.println("The following pages are created");
//				for (Page p : pagesCreated) {
//					out.println(p.getName());
//				}
//			} else {
//				LOG.error("Page is not created");
//				out.println("page is not created");
//			}
//		} catch (Exception e) {
//			LOG.error(e.getMessage());
//		}
//
//	}
//}