package model;

import java.io.File;
import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
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
    private static DBImplementationUser instance;

    final String SQLSELECTALL = "SELECT * FROM gUser";
    final String SQLSELECTBYID = "SELECT * FROM gUser WHERE idUser = ?";
    final String SQLINSERT = "INSERT INTO gUser (nameUser, email, phoneNumber, registrationDate, route) VALUES(?,?,?,?,?)";
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
    
    public static DBImplementationUser getInstance(){
        if(instance == null){
            instance = new DBImplementationUser();
        }
        return instance;
    }
    
    //creation of a setUser method to avoid repeating the date conversion code in each User method
    private User setUser(ResultSet rs) throws SQLException {
        //conversion Date (from db) to LocalDate 
        java.sql.Date sqlDate = rs.getDate("registrationDate");
        LocalDate registrationDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;

        return new User(
                rs.getInt("idUser"),
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
                LocalDate registrationDate = (sqlDate != null) ? sqlDate.toLocalDate() : null;
                
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
            stmt.setInt(1, user.getIdUser());
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
    public boolean insertUser(){
        String nameUser, email=null, phoneNumber=null, route;
        boolean insertPerformed = false;
        
        System.out.print("Insert user's name: ");
        nameUser = Utilities.introducirCadena();
        System.out.print("Insert user's email: ");
        email = validateEmail(email);
        System.out.print("Inser user's phone number: ");
        phoneNumber = validatePhone(phoneNumber); 
        System.out.print("Insert user's profile picture route: ");
        route = Utilities.introducirCadena();
        
        this.openConnection();
        try{
            stmt = con.prepareStatement(SQLINSERT);
            stmt.setString(1, nameUser);
            stmt.setString(2, email);
            stmt.setString(3, phoneNumber);
            //conversion from sql.date to localDate
            stmt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            stmt.setString(5, route);
            
            if (stmt.executeUpdate() > 0) {
                insertPerformed = true;
                System.out.println("New user created");
            }
            stmt.close(); 
            con.close();
        }catch (SQLException e) { 
            System.out.println("Error: " + e.getMessage()); 
        }
        return insertPerformed;
    }
    
    public static String validatePhone(String phone) {
	boolean valid = false;
        
	do {
            phone = Utilities.introducirCadena();
            if(Pattern.matches("^(\\+34)?[ -]?[0-9]{3}[ -]?[0-9]{3}[ -]?[0-9]{3}$", phone)) {
                valid = true;
            } else {
                System.out.println("Invalid phone format, please try again.");
            }
	}while(!valid);
            return phone;
	}
    
    public static String validateEmail(String email){
        boolean valid = false;
        
        do{
            email = Utilities.introducirCadena();
            if(Pattern.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", email)){
                valid = true;
            }else{
                System.out.println("Invalid email format, please try again.");
            }
        }while(!valid);
            return email;
    }

    @Override
    public boolean purchaseGame(File file) {
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
        aGames = GameManagement.mostrarJuegos(file);
        
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
    public boolean viewUserGames(File file) {
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
        
        aGames = GameManagement.mostrarJuegos(file);
        
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
        
        return hasGames;
    }
}
