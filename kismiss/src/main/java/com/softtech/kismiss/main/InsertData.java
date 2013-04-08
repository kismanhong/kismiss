package com.softtech.kismiss.main;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang.StringUtils;

public class InsertData {

	public static void main(String[] args) throws IOException {
		Connection connection = null;
		try {
			// Load the JDBC driver
			String driverName = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverName);
			// Create a connection to the database
			String serverName = "aegle";
			String portNumber = "1521";
			String sid = "orcl";
			String url = "jdbc:oracle:thin:@" + serverName + ":" + portNumber
					+ ":" + sid;
			String username = "cmsbaf";
			String password = "cmsbaf";
			connection = DriverManager.getConnection(url, username, password);
			
			for(int i = 11607; i > 0; i--){
			
				// Read File Line By Line
				@SuppressWarnings("unused")
				int row =0;
				int numOfQuery=0;
				try {
					FileInputStream fstream = new FileInputStream("D:\\Personal\\Test\\DUE AMOUNT\\disk"+i+".gsd");
					
					 DataInputStream in = new DataInputStream(fstream);
					    BufferedReader br = new BufferedReader(new InputStreamReader(in));

					StringBuffer buffer = new StringBuffer();
					String strLine;
				//	row = 0;
					Statement st = connection.createStatement();
					while ((strLine = br.readLine()) != null) {
//				System.out.println(strLine);			
							buffer.append(strLine);
						
//				System.out.println(buffer.toString());
						if(strLine.contains(";")){
							st.execute(StringUtils.substring(buffer.toString(), 0, buffer.toString().indexOf(";")));
							buffer = new StringBuffer();
//							System.out.println(numOfQuery);
							numOfQuery++;
						}
							
							
					if(numOfQuery % 50 == 0){
						connection.commit();
						//st.close();
						
					}
					row++;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				connection.commit();
				System.out.println("Number of query :"+numOfQuery);
			}
			connection.close();
		
		} catch (ClassNotFoundException e) { // Could not find the database
												// driver
			e.printStackTrace();
		} catch (SQLException e) { // Could not connect to the database }
			e.printStackTrace();
		}
	}
}
