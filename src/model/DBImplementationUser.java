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
    
    //creation of a setUser method to avoid repeating the date conversion code in each User method
    private User setUser(java.sql.ResultSet rs) throws SQLException {
        //conversion Date (from db) to LocalDate 
        java.sql.Date sqlDate = rs.getDate("registrationDate");
        java.time.LocalDate registrationDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;

        return new User(
                rs.getString("idUser"),
                rs.getString("nameUser"),
                rs.getString("email"),
                rs.getString("phoneNumber"),
                registrationDate,
                rs.getString("route")
        );
    }

    @Override 
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLSELECTALL);
            java.sql.ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                
                java.sql.Date sqlDate = rs.getDate("registrationDate");
                java.time.LocalDate registrationDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                
                users.add(setUser(rs));
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return users;
    }
    
    @Override
	public User getUserById(User user) { 
		this.openConnection(); 
		try {
			stmt = con.prepareStatement(SQLSELECTBYID);
			stmt.setString(1, user.getIdUser());
			java.sql.ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				User found = setUser(rs);
				rs.close(); 
                                stmt.close(); 
                                con.close();
				return found;
			}
			rs.close(); 
                        stmt.close(); 
                        con.close();
		} catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
		return null;
	}

}
