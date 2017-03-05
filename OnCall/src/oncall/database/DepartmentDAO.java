package oncall.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import oncall.database.ConnectionManager;
import oncall.model.Department;

public class DepartmentDAO {

	private static Logger logger = Logger.getLogger(DepartmentDAO.class);
	
	private final String SQL_DEPARTMENT_INSERT = "insert into oncall.department (name, description, active) values (?, ?, ?)";
	private final String SQL_DEPARTMENT_DELETE = "delete from oncall.department where seq = ?";
	private final String SQL_DEPARTMENT_SELECT = "select * from department where seq = ?";
	private final String SQL_DEPARTMENT_SELECT_ALL = "select * from department";
	private final String SQL_DEPARTMENT_UPDATE = "update department set name = ?, description = ?, active = ? where seq = ?";

	public DepartmentDAO() {
		
	}

	public void deleteDepartment(int sequence) {
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_DEPARTMENT_DELETE);
				try {
					stmt.setInt(1, sequence);
					stmt.executeUpdate();
					stmt.close();
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

	public void insertDepartment(Department department) {
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_DEPARTMENT_INSERT);
				try {
					stmt.setString(1, department.getName());
					stmt.setString(2, department.getDescription());
					stmt.setString(3, department.getActive() ? "Y" : "N");
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

	public void updateDepartment(Department department) {
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_DEPARTMENT_UPDATE);
				try {
					stmt.setString(1, department.getName());
					stmt.setString(2, department.getDescription());
					stmt.setString(3, department.getActive() ? "Y" : "N");
					stmt.setInt(4, department.getSequence());
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

	public ArrayList<Department> getDepartments() {
		ArrayList<Department> result = new ArrayList<Department>();
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				Statement stmt = conn.createStatement();
				try {
					ResultSet rs = stmt.executeQuery(SQL_DEPARTMENT_SELECT_ALL);
					try {
						while(rs.next()) {
							Department department = new Department();
							department.setSequence(rs.getInt("seq"));
							department.setName(rs.getString("name"));
							department.setDescription(rs.getString("description"));
							department.setActive(rs.getString("active").equalsIgnoreCase("Y") ? true : false);
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
		return result;
	}

	public Department getDepartment(int sequence) {
		Department department = new Department();
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_DEPARTMENT_SELECT);
				try {
					stmt.setInt(1, sequence);
					ResultSet rs = stmt.executeQuery();
					try {
						while (rs.next()) {
							department.setSequence(rs.getInt("seq"));
							department.setName(rs.getString("name"));
							department.setDescription(rs.getString("description"));
							department.setActive(rs.getString("active").equalsIgnoreCase("Y") ? true : false);
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
		return department;
	}

}
