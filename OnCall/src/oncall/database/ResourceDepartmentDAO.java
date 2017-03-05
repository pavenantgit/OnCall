package oncall.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import oncall.database.ConnectionManager;
import oncall.model.Resource;

public class ResourceDepartmentDAO {

	private static Logger logger = Logger.getLogger(ResourceDepartmentDAO.class);
	
	private static final String SQL_RESOURCEDEPARTMENT_INSERT = "insert into resource_department (resource, department) values (?, ?)";
	private static final String SQL_RESOURCEDEPARTMENT_DELETE = "delete from resource_department where resource = ?";
	private static final String SQL_RESOURCEDEPARTMENT_SELECT_RESOURCE = "select * from resource where sequence in (select resource from resource_department where department = ?)";
	private static final String SQL_RESOURCEDEPARTMENT_SELECT_DEPARTMENT = "select * from resource_department where resource = ?";

	public void deleteResourceDepartments(int resource) {
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_RESOURCEDEPARTMENT_DELETE);
				try {
					stmt.setInt(1, resource);
					if (logger.isDebugEnabled()) {
						logger.debug(stmt.toString());
					}
					stmt.executeUpdate();
				} finally {
					stmt.close();
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	public void insertResourceDepartments(int resource, String[] departments) {
		if (departments == null) {
			return;
		}
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				for (String department : departments) {
					PreparedStatement stmt = conn.prepareStatement(SQL_RESOURCEDEPARTMENT_INSERT);
					try {
						stmt.setInt(1, resource);
						stmt.setInt(2, Integer.parseInt(department));
						if (logger.isDebugEnabled()) {
							logger.debug(stmt.toString());
						}
						stmt.executeUpdate();
					} finally {
						stmt.close();
					}
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
	}

	public void updateResourceDepartments(int resource, String[] departments) {
		deleteResourceDepartments(resource);
		insertResourceDepartments(resource, departments);
	}

	public String[] getResourceDepartments(int resource) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_RESOURCEDEPARTMENT_SELECT_DEPARTMENT);
				try {
					stmt.setInt(1, resource);
					if (logger.isDebugEnabled()) {
						logger.debug(stmt.toString());
					}
					ResultSet rs = stmt.executeQuery();
					try {
						while (rs.next()) {
							String department = Integer.toString(rs.getInt("department"));
							result.add(department);
						}
					} finally {
						rs.close();
					}
				} finally {
					stmt.close();
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result.toArray(new String[result.size()]);
	}

	public ArrayList<Resource> getResourcesInDepartment(int department) {
		ArrayList<Resource> result = new ArrayList<Resource>();
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_RESOURCEDEPARTMENT_SELECT_RESOURCE);
				try {
					stmt.setInt(1, department);
					if (logger.isDebugEnabled()) {
						logger.debug(stmt.toString());
					}
					ResultSet rs = stmt.executeQuery();
					try {
						while (rs.next()) {
							Resource resource = new Resource();
							resource.setSequence(rs.getInt("seq"));
							resource.setFirstName(rs.getString("firstname"));
							resource.setLastName(rs.getString("lastname"));
							resource.setContactNumber(rs.getString("contactnumber"));
							resource.setEmail(rs.getString("email"));
							resource.setActive(rs.getString("active").equalsIgnoreCase("Y") ? true : false);
							result.add(resource);
						}
					} finally {
						rs.close();
					}
				} finally {
					stmt.close();
				}
			} finally {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

}
