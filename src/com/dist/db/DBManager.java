package com.dist.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Properties;
import java.util.Vector;

public class DBManager {

	//db details
	private static final String SERVER_NAME = "localhost";
	private static final int PORT_NUMBER = 3306;
	private static final String DB_NAME = "assignment_1";
	public static final String DB_URL ="jdbc:mysql://" + SERVER_NAME + ":" + PORT_NUMBER + "/" + DB_NAME;
	public static final String DB_USER = "root";
	public static final String DB_PASS = "mysql";

	//db tables
	public static final String EMPLOYEE_TABLE = "employee";
	public static final String PROJECT_TABLE = "project";
	public static final String DEPARTMENT_TABLE = "department";

	public DBManager() {
		
	}
	
	/**
	 * Retrieves a savepoint of the database
	 * @return savepoint of the database
	 * @throws SQLException
	 */
	public static Savepoint getSavePoint() throws SQLException{
		return getConnection().setSavepoint();
	}

	/**
	 * Retrieves an SQL Connection using properties
	 * @return Connection object
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException{		
		return DriverManager.getConnection(DB_URL, getProperties());
	}
	
	/**
	 * Closes an SQL connection
	 * @param conn, the connection to close
	 */
	public static void closeConnection(Connection conn){
		try{
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**
	 * @return Properties object containing the properties for accessing the SQL db
	 */
	public static Properties getProperties(){
		Properties props = new Properties();
		props.put("user", DB_USER);
		props.put("password", DB_PASS);
		return props;
	}

	/**
	 * Returns the column names for a specific table
	 * @param tableName, the name of the table to get the columns from
	 * @return the list of column names from the table
	 * @throws SQLException
	 */
	public static Vector<String> getTableColumnNames(final String tableName) throws SQLException{
		Vector<String> columnNames = new Vector<String>();
		ResultSetMetaData metaData = getConnection().createStatement()
													.executeQuery("SELECT * FROM " + tableName + " LIMIT 1")
													.getMetaData();
		for(int i = 1; i <= metaData.getColumnCount(); i++){
			columnNames.add(metaData.getColumnName(i));
		}
		return columnNames;
	}
	
	/**
	 * Gets the table rows of a specific table in a matrix
	 * @param tableName, the name of the table to query
	 * @return matrix representing the table rows
	 * @throws SQLException 
	 */
	public static Vector<Vector<Object>> getTableRows(final String tableName) throws SQLException{
		ResultSet resultSet = getConnection().createStatement()
											 .executeQuery("SELECT * FROM " + tableName);
		int columnCount = resultSet.getMetaData().getColumnCount();
		// data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (resultSet.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(resultSet.getObject(columnIndex));
	        }
	        data.add(vector);
	    }
		return data;
	}

	/**
	 * Rolls back the database to a particular savepoint
	 * @param savepoint, the savepoint to rollback to
	 * @throws SQLException
	 */
	public static void rollback(Savepoint savepoint) throws SQLException {
		getConnection().rollback(savepoint);
	}
}
