package oncall.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import oncall.database.ConnectionManager;
import oncall.model.Schedule;

public class ScheduleDAO {

	private static Logger logger = Logger.getLogger(ScheduleDAO.class);

	private static final String SQL_SCHEDULE_INSERT = "insert into schedule (startdate, enddate, department, resource, active) values (?, ?, ?, ?, ?)";
	private static final String SQL_SCHEDULE_DELETE = "delete from schedule where seq = ?";
	private static final String SQL_SCHEDULE_UPDATE = "update schedule set startdate = ?, enddate = ?, department = ?, resource = ?, active = ? where seq = ?";
	private static final String SQL_SCHEDULE_SELECT = "select * from schedule where seq = ?";
	private static final String SQL_SCHEDULE_SELECT_ALL = "select * from schedule";

	/**
	 * Delete a record in the SCHEDULE table with a specific sequence.
	 * 
	 * @param sequence
	 */
	public void deleteSchedule(int sequence) {
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_SCHEDULE_DELETE);
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

	/**
	 * Insert a record in the SCHEDULE table.
	 * 
	 * @param schedule
	 */
	public void insertSchedule(Schedule schedule) {
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_SCHEDULE_INSERT);
				try {
					stmt.setDate(1, schedule.getStartDate());
					stmt.setDate(2, schedule.getEndDate());
					stmt.setInt(3, schedule.getDepartment());
					stmt.setInt(4, schedule.getResource());
					stmt.setString(5, schedule.getActive() ? "Y" : "N");
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

	public void updateSchedule(Schedule schedule) {
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_SCHEDULE_UPDATE);
				try {
					stmt.setDate(1, schedule.getStartDate());
					stmt.setDate(2, schedule.getEndDate());
					stmt.setInt(3, schedule.getDepartment());
					stmt.setInt(4, schedule.getResource());
					stmt.setString(5, schedule.getActive() ? "Y" : "N");
					stmt.setInt(6, schedule.getSequence());
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

	public ArrayList<Schedule> getSchedules() {
		ArrayList<Schedule> result = new ArrayList<Schedule>();
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_SCHEDULE_SELECT_ALL);
				try {
					if (logger.isDebugEnabled()) {
						logger.debug(stmt.toString());
					}
					ResultSet rs = stmt.executeQuery();
					try {
						while(rs.next()) {
							Schedule schedule = new Schedule();
							schedule.setSequence(rs.getInt("seq"));
							schedule.setStartDate(rs.getDate("startdate"));
							schedule.setEndDate(rs.getDate("enddate"));
							schedule.setDepartment(rs.getInt("department"));
							schedule.setResource(rs.getInt("resource"));
							schedule.setActive(rs.getString("active").equalsIgnoreCase("Y") ? true : false);
							result.add(schedule);
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

	public Schedule getSchedule(int sequence) {
		Schedule result = new Schedule();
		try {
			Connection conn = ConnectionManager.getConnectionManager().getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(SQL_SCHEDULE_SELECT);
				try {
					stmt.setInt(1, sequence);
					if (logger.isDebugEnabled()) {
						logger.debug(stmt.toString());
					}
					ResultSet rs = stmt.executeQuery();
					try {
						while (rs.next()) {
							result.setSequence(rs.getInt("seq"));
							result.setStartDate(rs.getDate("startdate"));
							result.setEndDate(rs.getDate("enddate"));
							result.setDepartment(rs.getInt("department"));
							result.setResource(rs.getInt("resource"));
							result.setActive(rs.getString("active").equalsIgnoreCase("Y") ? true : false);
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
