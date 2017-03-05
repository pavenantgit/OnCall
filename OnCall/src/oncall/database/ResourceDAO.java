package oncall.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import oncall.database.ConnectionManager;
import oncall.model.Resource;

public class ResourceDAO {

	private static Logger logger = Logger.getLogger(ResourceDAO.class);
	
	private static final String SQL_RESOURCE_INSERT = "insert into resource (firstname, lastname, contactnumber, email, active) values (?, ?, ?, ?, ?)";
	private static final String SQL_RESOURCE_DELETE = "delete from resource where seq = ?";
	private static final String SQL_RESOURCE_UPDATE = "update resource set firstname = ?, lastname = ?, contactnumber = ?, email = ?, active = ? where seq = ?";
	private static final String SQL_RESOURCE_SELECT = "select * from resource where seq = ?";
	private static final String SQL_RESOURCE_SELECT_ALL = "select * from resource";
	private static final String SQL_RESOURCE_SELECT_NAME = "select * from resource where firstname = ? and lastname = ?";

	public void deleteResource(int sequence) {

		// Delete the departments linked to the resource first
		ResourceDepartmentDAO dao = new ResourceDepartmentDAO();
		dao.deleteResourceDepartments(sequence);

		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_RESOURCE_DELETE);
				try {
					stmt.setInt(1, sequence);
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

	public void insertResource(Resource resource) {
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_RESOURCE_INSERT);
				try {
					stmt.setString(1, resource.getFirstName());
					stmt.setString(2, resource.getLastName());
					stmt.setString(3, resource.getContactNumber());
					stmt.setString(4, resource.getEmail());
					stmt.setString(5, resource.getActive() ? "Y" : "N");
					if (logger.isDebugEnabled()) {
						logger.debug(stmt.toString());
					}
					stmt.executeUpdate();
				} finally {
					stmt.close();
				}
				if (resource.getDepartments() != null) {
					stmt = conn.prepareStatement(SQL_RESOURCE_SELECT_NAME);
					try {
						stmt.setString(1, resource.getFirstName());
						stmt.setString(2, resource.getLastName());
						if (logger.isDebugEnabled()) {
							logger.debug(stmt.toString());
						}
						ResultSet rs = stmt.executeQuery();
						int sequence = rs.getInt("sequence");
						ResourceDepartmentDAO dao = new ResourceDepartmentDAO();
						dao.insertResourceDepartments(sequence, resource.getDepartments());
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

	public void updateResource(Resource resource) {
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_RESOURCE_UPDATE);
				try {
					stmt.setString(1, resource.getFirstName());
					stmt.setString(2, resource.getLastName());
					stmt.setString(3, resource.getContactNumber());
					stmt.setString(4, resource.getEmail());
					stmt.setString(5, resource.getActive() ? "Y" : "N");
					stmt.setInt(6, resource.getSequence());
					if (logger.isDebugEnabled()) {
						logger.debug(stmt.toString());
					}
					stmt.executeUpdate();
					ResourceDepartmentDAO dao = new ResourceDepartmentDAO();
					dao.updateResourceDepartments(resource.getSequence(), resource.getDepartments());
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

	public ArrayList<Resource> getResources() {
		ArrayList<Resource> result = new ArrayList<Resource>();
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_RESOURCE_SELECT_ALL);
				try {
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
							
							ResourceDepartmentDAO rddao = new ResourceDepartmentDAO();
							resource.setDepartments(rddao.getResourceDepartments(resource.getSequence()));

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

	public Resource getResource(int sequence) {
		Resource result = new Resource();
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_RESOURCE_SELECT);
				try {
					stmt.setInt(1, sequence);
					if (logger.isDebugEnabled()) {
						logger.debug(stmt.toString());
					}
					ResultSet rs = stmt.executeQuery();
					try {
						while (rs.next()) {
							result.setSequence(rs.getInt("seq"));
							result.setFirstName(rs.getString("firstname"));
							result.setLastName(rs.getString("lastname"));
							result.setContactNumber(rs.getString("contactnumber"));
							result.setEmail(rs.getString("email"));
							result.setActive(rs.getString("active").equalsIgnoreCase("Y") ? true : false);

							ResourceDepartmentDAO rddao = new ResourceDepartmentDAO();
							result.setDepartments(rddao.getResourceDepartments(sequence));

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
