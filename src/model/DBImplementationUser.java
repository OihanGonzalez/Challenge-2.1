package model;

import java.io.File;
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import utilities.Utilities;

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
    final String SQLSELECTGAMEUSER = "SELECT * FROM gameUser WHERE idUser = ? AND idGame = ?";
    final String SQLINSERTGAMEUSER = "INSERT INTO gameUser VALUES(?,?)";
    final String SQLSELECTUSERGAMES = "SELECT * FROM gameUser JOIN gUser ON gameUser.idUser = gUser.idUser WHERE gameUser.idUser = ?";

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
    private User setUser(ResultSet rs) throws SQLException {
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
            ResultSet rs = stmt.executeQuery();
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
            ResultSet rs = stmt.executeQuery();
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
    
    @Override
    public boolean insertUser(User user){
        boolean insertPerformed = false;
        this.openConnection();
        try{
            stmt = con.prepareStatement(SQLINSERT);
            stmt.setString(1, user.getIdUser());
            stmt.setString(2, user.getNameUser());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhoneNumber());
            //conversion from sql.date to localDate
            stmt.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setString(6, user.getRoute());
            
            if (stmt.executeUpdate() > 0) {
                insertPerformed = true;
            }
            stmt.close(); 
            con.close();
        }catch (SQLException e) { 
            System.out.println("Error: " + e.getMessage()); 
        }
            return insertPerformed;
    }

    @Override
    public boolean purchaseGame(File fichero) {
        List<User> aUsers = new ArrayList<User>();
        ArrayList<Game> aGames = new ArrayList<Game>();
        aUsers = getAllUser();
        int userId, gameId;
        boolean found = false;
        
        System.out.println("\n--------------USERS------------");
        for (User u : aUsers) {
            System.out.println(u.toString());
        }
        System.out.println("Select a user to purchase a game: ");
        userId = Utilities.leerInt(1, aUsers.size());
        
        System.out.println("\n----------------GAMES-------------");
        aGames = GameManagement.mostrarJuegos(fichero);
        
        System.out.println("Select game to purchase: ");
        gameId = Utilities.leerInt(1, aGames.size());
        
        this.openConnection(); 
        try {
            stmt = con.prepareStatement(SQLSELECTGAMEUSER);
            stmt.setInt(1, userId);
            stmt.setInt(2, gameId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                found = true;
                System.out.println("The user already has the game.");
            }
            rs.close(); 
            stmt.close(); 
            con.close();
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        
        if (!found) {
            this.openConnection();
            try {
                stmt = con.prepareStatement(SQLINSERTGAMEUSER);
                stmt.setInt(1, userId);
                stmt.setInt(2, gameId);

                int rows = stmt.executeUpdate();
                System.out.println("Game purchased");
            } catch (SQLException e) {
               e.printStackTrace();
           }
        }
        return found ? false : true;
    }
    
    
    @Override
    public boolean viewUserGames(File fichero) {
        List<User> aUsers = new ArrayList<User>();
        ArrayList<Game> aGames = new ArrayList<Game>();
        int userId;
        boolean hasGames = false;
        
        aUsers = getAllUser();
        
        System.out.println("\n--------------USERS------------");
        for (User u : aUsers) {
            System.out.println(u.toString());
        }
        System.out.println("Select a user check his library: ");
        userId = Utilities.leerInt(1, aUsers.size());
        
        aGames = GameManagement.mostrarJuegos(fichero);
        
        this.openConnection();
        try {
            stmt = con.prepareStatement(SQLSELECTUSERGAMES);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("\n--------- " + rs.getString("nameUser") + "'s videogames -------");
                for (Game g : aGames) {
                   if (g.getGameId() == rs.getInt("idGame")) {
                       System.out.println(g.toString());
                       hasGames = true;
                   }
               }
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        if (!hasGames) {
            System.out.println("\nHas 0 games.");
        }
        
        return true;
    }
}
