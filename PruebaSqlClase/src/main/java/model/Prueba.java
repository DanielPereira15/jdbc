package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.setProperty("user", "root");
		connectionProps.setProperty("password", "root");
		connectionProps.setProperty("serverTimezone", "UTC");

		try {
			// info getConnection
			// https://docs.oracle.com/en/java/javase/15/docs/api/java.sql/java/sql/DriverManager.html#getConnection(java.lang.String,java.util.Properties)
			conn = DriverManager.getConnection("jdbc:mysql://10.11.1.171:3306/Cliente", connectionProps);
			System.out.println("Connected to database");
			// System.out.println(conn.setAutoCommit(true));
			System.out.println("Modo autocommit: " + conn.getAutoCommit());
			System.out.println(conn.getMetaData());
			System.out.println("Está cerrado: " + conn.isClosed());
//                    try {
//                        System.out.println(conn.getMetaData().getMaxColumnsInIndex());
//                    } catch (SQLException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    } 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
			System.out.println(e.getSQLState());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}
}