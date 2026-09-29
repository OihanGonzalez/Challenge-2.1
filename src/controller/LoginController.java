/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
 
import java.io.File;
import java.util.List;
import model.*;

/**
 *
 * @author etnag
 */
public class LoginController {
    UserDAO user_dao = new DBImplementationUser();
    DeveloperDAO developer_dao = new DBImplementationDeveloper();
    
    public List<User> getAllUser() {
        return user_dao.getAllUser();
    }
    
    public boolean insertUser() {
        return user_dao.insertUser();
    }
    
    public boolean createDeveloper() {
        return developer_dao.createDeveloper();
    }

    public Developer getDeveloperById(int idDeveloper) {
        return developer_dao.getDeveloperById(idDeveloper);
    }

    public List<Developer> getAllDevelopers() {
        return developer_dao.getAllDevelopers();
    }
    
    public boolean viewDeveloperGames(File file) {
        return developer_dao.viewDeveloperGames(file);
    }
}
