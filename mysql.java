import java.sql.*;

public class mysql {
	private static Connection conn;
	private static Statement statement;
	public static void main(String[] args){
		try{
			conn = DriverManager.getConnection("jdbc:mysql://10.0.10.3:3306/my_database","mchodorowski","pass");
			statement = conn.createStatement();
			switch (args[0]){
				case "insert":
					mysql.insert(args);
					break;
				case "select":
					mysql.show();
					break;
				case "init":
					mysql.init();
					break;
			}
		} catch (Exception e){
			e.printStackTrace();	
		}
	}
	public static void insert(String[] strings){
		try{
			statement.executeUpdate("INSERT INTO users(id,firstname,lastname) VALUES ("+strings[1]+",'"+strings[2]+"','"+strings[3]+"')");
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public static void show(){
		try{
			ResultSet result = statement.executeQuery("SELECT * FROM users");
			while(result.next()){
				int i = result.getInt("id");
				String fn = result.getString("firstname");
				String ln = result.getString("lastname");
				System.out.println(Integer.toString(i)+" "+fn+" "+ln);
			}
		}catch (SQLException e) {
				System.out.println("Cos poszlo nie tak");
				e.printStackTrace();
		}
	}

	public static void init(){
		try {
				Thread.sleep(60000);
				statement.executeUpdate("CREATE TABLE IF NOT EXISTS users ("+
					"id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,"+
					"firstname VARCHAR(30),"+
					"lastname VARCHAR(30)"+
					")");
				statement.executeUpdate("INSERT IGNORE INTO users (id,firstname,lastname) VALUES (1,'Michal', 'Chodorowski'),"+
					"(2,'Wojciech', 'Szczesny'),(3,'Franek', 'Kimono')");
				ResultSet result = statement.executeQuery("SELECT * FROM users");
				while(result.next()){
					int i = result.getInt("id");
					String fn = result.getString("firstname");
					String ln = result.getString("lastname");
					System.out.println(Integer.toString(i)+" "+fn+" "+ln);
				}
				conn.close();
			} catch (SQLException e) {
				System.out.println("Blad dzialania");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Blad dzialania");
				e.printStackTrace();
				
			}
	}
}
