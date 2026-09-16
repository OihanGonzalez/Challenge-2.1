package model;

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
public class DBImplementationUser implements UserDAO{
    private Connection con; 
    private PreparedStatement stmt;
    
    private ResourceBundle configFile;
    private String driverDB;
    private String urlDB;
    private String userDB;
    private String passwordDB;
    
    final String SQLSELECTALL = "SELECT * FROM gUser";
    final String SQLINSERT = "INSERT INTO gUser VALUES(?,?,?,?,?,?)";
    
    public DBImplementationUser() {
	this.configFile = ResourceBundle.getBundle("configClass");
	this.driverDB = this.configFile.getString("Driver");
	this.urlDB = this.configFile.getString("Conn");
	this.userDB = this.configFile.getString("DBUser");
	this.passwordDB = this.configFile.getString("DBPass");
	}
}
