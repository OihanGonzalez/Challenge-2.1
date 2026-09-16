package model;
 
import java.time.LocalDate;

/**
 *
 * @author etnag
 */
public class User {
    private String idUser;
    private String nameUser;
    private String email;
    private String phoneNumber;
    private LocalDate registrationDate;
    private String route;
    
    //contructor
    public User(String idUser, String nameUser, String email, String phoneNumber, LocalDate registrationDate, String route){
        this.idUser=idUser;
        this.nameUser=nameUser;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.registrationDate=registrationDate;
        this.route=route;
    }
    
    //getters and setters

    public String getIdUser() {
        return idUser;
    }
    public String getNameUser() {
        return nameUser;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
    public String getRoute() {
        return route;
    }
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
    public void setRoute(String route) {
        this.route = route;
    }
    
    
    
}
