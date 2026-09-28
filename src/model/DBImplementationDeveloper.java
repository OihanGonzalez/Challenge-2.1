package model;

/**
 *
 * @author CJ
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DBImplementationDeveloper {

    private Connection con;
    private PreparedStatement stmt;

    private ResourceBundle configFile;
    private String driverDB;
    private String urlDB;
    private String userDB;
    private String passwordDB;
    private static DBImplementationDeveloper instance;

    final String SQLSELECTALL = "SELECT * FROM developer";
    final String SQLSELECTBYID = "SELECT * FROM developer WHERE idDeveloper = ?";
    final String SQLINSERT = "INSERT INTO developer VALUES(?,?,?,?)";

    public DBImplementationDeveloper() {
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
            System.out.println("Error opening connection: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DBImplementationDeveloper getInstance() {
        if (instance == null) {
            instance = new DBImplementationDeveloper();
        }
        return instance;
    }

    private Developer setDeveloper(ResultSet rs) throws SQLException {
        return new Developer(
                rs.getInt("idDeveloper"),
                rs.getString("nameDeveloper"),
                rs.getString("country"),
                rs.getInt("foundationYear")
        );
    }

    public boolean createDeveloper(Developer developer) {
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLINSERT);
            stmt.setInt(1, developer.getDeveloperID());
            stmt.setString(2, developer.getDeveloperName());
            stmt.setString(3, developer.getCountry());
            stmt.setInt(4, developer.getFoundationYear());

            int rows = stmt.executeUpdate();

            stmt.close();
            con.close();

            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting developer: " + e.getMessage());
        }
        return false;
    }

    public Developer getDeveloperById(int idDeveloper) {
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLSELECTBYID);
            stmt.setInt(1, idDeveloper);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Developer dev = setDeveloper(rs);
                rs.close();
                stmt.close();
                con.close();
                return dev;
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error selecting developer: " + e.getMessage());
        }
        return null;
    }

    public List<Developer> getAllDevelopers() {
        List<Developer> developers = new ArrayList<>();
        this.openConnection();

        try {
            stmt = con.prepareStatement(SQLSELECTALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                developers.add(setDeveloper(rs));
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error selecting developers: " + e.getMessage());
        }

        return developers;
    }
}
