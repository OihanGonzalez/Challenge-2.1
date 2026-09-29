package model;

/**
 *
 * @author CJ
 */
import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import utilities.Utilities;

public class DBImplementationDeveloper implements DeveloperDAO {

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
    final String SQLINSERT = "INSERT INTO developer (nameDeveloper, country, foundationYear) VALUES(?,?,?)";

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

    public boolean createDeveloper() {
        String developerName, country;
        int foundationYear;
        boolean insertPerformed = false;

        System.out.println("Developer name:");
        developerName = Utilities.introducirCadena();
        System.out.println("Country:");
        country = Utilities.introducirCadena();
        System.out.println("Foundation year:");
        foundationYear = Utilities.leerInt(1950,LocalDate.now().getYear());
        
        this.openConnection();
        try{
            stmt = con.prepareStatement(SQLINSERT);
            stmt.setString(1, developerName);
            stmt.setString(2, country);
            stmt.setInt(3, foundationYear);
            
            if (stmt.executeUpdate() > 0) {
                insertPerformed = true;
                System.out.println("New developer created");
            }
            stmt.close(); 
            con.close();
        }catch (SQLException e) { 
            System.out.println("Error: " + e.getMessage()); 
        }
        
        return insertPerformed;
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

    public boolean viewDeveloperGames(File file) {
        List<Developer> aDevelopers = new ArrayList<Developer>();
        ArrayList<Game> aGames = new ArrayList<Game>();
        int developerId;
        boolean hasGames = false;
        aDevelopers = getAllDevelopers();

        System.out.println("\n-------------- DEVELOPERS ------------");
        for (Developer d : aDevelopers) {
            System.out.println(d.toString());
        }

        System.out.println("Select a developer to view his games: ");
        developerId = Utilities.leerInt(1, aDevelopers.size());
        
        GameManagement.showGames(file);
        aGames = GameManagement.getAllGames(file);
        System.out.println("\n--------- Games developed by Developer ID " + developerId + " -------");

        for (Game g : aGames) {
            if (g.getDeveloperId() == developerId) {
                System.out.println(g.toString());
                hasGames = true;
            }
        }
        if (!hasGames) {
            System.out.println("\nThis developer has no games.");
        }
        return true;
    }
}
