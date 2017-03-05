package oncall.core;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class StartUpListener
 *
 */
@WebListener
public class StartUpListener implements ServletContextListener {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * Initializes log4j with a configuration file specified in web.xml
	 * @param sce
	 */
	private void initializeLogger(ServletContextEvent sce) {
		String pathPrefix;
		String localLog4JFile;
		String localFilePath;
		String fileName;
		java.io.File localFile;

		pathPrefix = sce.getServletContext().getRealPath("/");
		localLog4JFile = "local.lcf";
		fileName = sce.getServletContext().getInitParameter("log4jConfigurationFile");
		localFilePath = pathPrefix + "WEB-INF" + java.io.File.separator + localLog4JFile;
		localFile = new java.io.File(localFilePath);
		if (localFile.exists()) {
			org.apache.log4j.PropertyConfigurator.configure(localFilePath);
			if (logger.isDebugEnabled()) {
				logger.debug("Loading local log4J properties: " + localFilePath);
			}
		} else if (fileName != null) {
			org.apache.log4j.PropertyConfigurator.configure(pathPrefix + fileName);
			if (logger.isDebugEnabled()) {
				logger.debug("Loading log4J properties using file: " + fileName);
			}
		} else {
			System.out.println("Log4J properties not defined");
		}
		
	}

	/**
     * Default constructor. 
     */
    public StartUpListener() {

    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 

    	initializeLogger(sce);
    	
    	logger.info("System: Initializing Application ...");

    	logger.info("System: Application Started Up Successfully");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 

    }

}
