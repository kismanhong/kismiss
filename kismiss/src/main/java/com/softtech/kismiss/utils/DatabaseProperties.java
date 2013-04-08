package com.softtech.kismiss.utils;


/**
 * @author Kisman Hong
 * has not been used, plan to use this class for getting column size from db
 */
public class DatabaseProperties {
//	public static List getColumnsName(String tableName)
//			throws ClassNotFoundException, SQLException {
//		List columnsName = new ArrayList();
//		// List result = new ArrayList();
//		Connection connection = null;
//		// PreparedStatement prepareState = null;
//
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		// connection =
//		// DriverManager.getConnection(AppProperties.getInstance().getString("db.url"),
//		// AppProperties.getInstance().getString("db.username"),
//		// AppProperties.getInstance().getString("db.password"));
//		ResultSet rslt = null;
//		try {
//			// connection = SQLUtil.getConnection();
//
//			DatabaseMetaData meta = connection.getMetaData();
//
//			rslt = meta.getColumns(null, "TEST", tableName, null);
//			ResultSetMetaData _rsmd = rslt.getMetaData();
//			System.out.println("column size ;" + _rsmd.getColumnCount());
//
//			while (rslt.next()) {
//
//				System.out.print(";" + rslt.getString(4));
//				System.out.println("");
//				// Filter filter = new Filter();
//				// filter.setOrder(rslt.getString(4));
//				// columnsName.add(filter);
//
//			}
//		} catch (Exception e) {
//			// logger.debug(e.getMessage(), e);
//
//		} finally {
//			try {
//				// SQLUtil.close(connection);
//				// SQLUtil.close(rslt);
//
//				connection.close();
//				rslt.close();
//			} catch (Exception e) {
//				//ignored
//			}
//		}
//		return columnsName;
//	}
}
