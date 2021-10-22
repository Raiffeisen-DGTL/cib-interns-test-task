package socks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public class DatabaseProc {
	
	private static final String DB_URL = "jdbc:postgresql://localhost/socks";
	private static final String USER = "postgres";
	private static final String PASS = "1234";
	
	public static final List<String> DB_OPS = Arrays.asList("moreThan", "lessThan", "equal");
	
	public static void incSocks(SocksPack socksPack) throws SQLException {
		
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement st = conn.createStatement();
		
		String sql = "INSERT INTO socks (color, cottonPart, quantity) VALUES (" +
						"\'" + socksPack.getColor() + "\', " +
						socksPack.getCottonPart() + ", " +
						socksPack.getQuantity() + ") " +
						"ON CONFLICT (color, cottonPart) DO UPDATE " +
						"SET quantity = socks.quantity + " + socksPack.getQuantity() + ";";
		
		st.execute(sql);
	}
	
	//Return false if parameter error occurs:
	//record is absent or requested quantity is too high
	public static boolean decSocks(SocksPack socksPack) throws SQLException {
		
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement st = conn.createStatement();
		
		String sql = "SELECT quantity FROM socks WHERE (color = " +
				"\'" + socksPack.getColor() + "\' AND cottonPart = " +
				socksPack.getCottonPart() + ");";
		
		ResultSet res = st.executeQuery(sql);
		
		if (res.next()) { //If exist
			
			int num = res.getInt("quantity");
			
			if (num < socksPack.getQuantity()) { //If enough
				return false;
			} else if(num == socksPack.getQuantity()) { //Delete record is result quantity == 0
				sql = "DELETE FROM socks " +
						"WHERE (color = \'" + socksPack.getColor() + "\' AND cottonPart = " +
						socksPack.getCottonPart() + ");";
			} else { //Modify quantity
				sql = "UPDATE socks SET quantity = quantity - " + socksPack.getQuantity() +
						"WHERE (color = \'" + socksPack.getColor() + "\' AND cottonPart = " +
						socksPack.getCottonPart() + ");";
			}
			
		} else {
			return false;
		}
		
		st.execute(sql);
		
		return true;
	}
	
	//Got SocksPack constraints (color and cottonPart's threshold)
	//and String operation (moreThan, lessThan, equal)
	public static int getAllSocks(SocksPack constraint, String op) throws SQLException {
		
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement st = conn.createStatement();
		
		String localOp = DB_OPS.get(0); //Default local op
		
		if(op.equals(DB_OPS.get(0))) {
			localOp = ">";
		} else if(op.equals(DB_OPS.get(1))) {
			localOp = "<"; 
		} else if(op.equals(DB_OPS.get(2))) {
			localOp = "=";
		}
		
		String sql = "SELECT SUM(quantity) FROM socks WHERE (color = " +
				"\'" + constraint.getColor() + "\' AND cottonPart " + localOp +
				constraint.getCottonPart() + ");";
		
		ResultSet res = st.executeQuery(sql);
			
		//Does exist
		if (res.next()) {
			return res.getInt("sum");			
		} else {
			return 0;
		}
	}
	
}
