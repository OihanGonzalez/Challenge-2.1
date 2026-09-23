package model;

import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author etnag
 */
public class DBImplementationUser implements UserDAO {

    private Connection con;
    private PreparedStatement stmt;

    private ResourceBundle configFile;
    private String driverDB;
    private String urlDB;
    private String userDB;
    private String passwordDB;

    final String SQLSELECTALL = "SELECT * FROM gUser";
    final String SQLSELECTBYID = "SELECT * FROM gUser WHERE idUser = ?";
    final String SQLINSERT = "INSERT INTO gUser VALUES(?,?,?,?,?,?)";

    public DBImplementationUser() {
        this.configFile = ResourceBundle.getBundle("configClass");
        this.driverDB = this.configFile.getString("Driver");
        this.urlDB = this.configFile.getString("Conn");
        this.userDB = this.configFile.getString("DBUser");
        this.passwordDB = this.configFile.getString("DBPass");
    }

    private void openConnection() {
        try {
            con = DriverManager.getConnection(urlDB, this.userDB, this.passwordDB);
        } catch (SQLException e) {
            System.out.println("Error to open BD");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override 
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLSELECTALL);
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //conversión Date de la db a LocalDate 
                java.sql.Date sqlDate = rs.getDate("registrationDate");
                java.time.LocalDate registrationDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                
                users.add(new User(
                        rs.getString("idUser"),
                        rs.getString("nameUser"),
                        rs.getString("email"),
                        rs.getString("phoneNumber"),
                        registrationDate,
                        rs.getString("route")
                ));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return users;
    }
    
//    @Override
//	public User getUsertById(User user) {
//		this.openConnection();
//		try {
//			stmt = con.prepareStatement(SQLSELECTBYID);
//			stmt.setString(1, client.getIdClient());
//			java.sql.ResultSet rs = stmt.executeQuery();
//			if (rs.next()) {
//				Client found = new Client(
//						rs.getString("id_client"),
//						rs.getString("name_client"),
//						rs.getString("surname_client"),
//						rs.getInt("age_client"),
//						rs.getInt("phone_client"),
//						rs.getString("email_client")
//						);
//				rs.close(); stmt.close(); con.close();
//				return found;
//			}
//			rs.close(); stmt.close(); con.close();
//		} catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
//		return null;
//	}

}
