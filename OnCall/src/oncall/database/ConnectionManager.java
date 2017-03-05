package oncall.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnectionManager {

	private static Logger logger = Logger.getLogger(ConnectionManager.class);
	
	private static ConnectionManager instance;
	private DataSource dataSource;
	
	public static final String INITIAL_CONTEXT_BASE = "java:comp/env";
	public static final String ONCALL_JDBC_RESOURCE_URL = "jdbc/mysql/oncall";

	private ConnectionManager() {
		try {
			Context envCtx = (Context) new InitialContext().lookup(INITIAL_CONTEXT_BASE);
			dataSource = (DataSource) envCtx.lookup(ONCALL_JDBC_RESOURCE_URL);
		} catch (NamingException e) {
			logger.debug("Exception occurred while trying to lookup JDBC resource.");
			throw new RuntimeException("Exception occurred while trying to lookup JDBC resource", e);
		}
	}

	public static ConnectionManager getConnectionManager() {

		synchronized (ConnectionManager.class) {
			if (null == instance) {
				instance = new ConnectionManager();
			}
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {

		Connection connection;
		connection = dataSource.getConnection();
		// connection.setAutoCommit(true);
		return connection;
	}

}
