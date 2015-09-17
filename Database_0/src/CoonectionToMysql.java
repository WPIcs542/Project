import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoonectionToMysql {
	static Connection conn;
	
	public static void connection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("Worked");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void ConToMysql(){
		connection();
		String host = "jdbc:mysql://localhost:8080/pizzadb";  //change this 
		String username = "jonathan";  //change this as your own
		String pwd = "jonathan";       //change this as your own
		try {
			conn = DriverManager.getConnection(host, username, pwd);
			System.out.println("Connection succeed to: " + host);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		String query = "Select * from toppings where id >2";
		
		ConToMysql();
		try {
			PreparedStatement stm = conn.prepareStatement(query);
			ResultSet result = stm.executeQuery();
//			stm.execute();
			
			while(result.next()){
				System.out.println(result.getString(1) + " " + result.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
