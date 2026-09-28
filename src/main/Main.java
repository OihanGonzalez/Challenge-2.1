package main;

import controller.*;
import java.io.File;
import model.*;
import utilities.*;


/**
 *
 * @author etnag
 */
public class Main {
    public static void main(String[] args) {
        int option;
        LoginController cont = new LoginController();
        File file = new File("vgstore.dat");
        GameManagement.fillGames(file);
        
        do{
            option=menu();
            switch(option){
                case 1:
                    GameManagement.createGame();
                    break;
                case 2:
                    //cont.insertUser(user);
                    break;
                case 3:
                    cont.createDeveloper();
                    break;
                case 4:
                    cont.purchaseGame(file);
                    break;
                case 5:
                    GameManagement.mostrarJuegos(file);
                    break;
                case 6:
                    cont.viewDeveloperGames(file);
                    break;
                case 7:
                    cont.viewUserGames(file);
                    break;
            }
        }while(option!=8);
    }
     
     
    public static int menu(){

        System.out.print("------------------------MENU------------------------"
                + "\n1. Register a game."
                + "\n2. Register a user."
                + "\n3. Register a developer."
                + "\n4. Purchase a game."
                + "\n5. View avaliable games."
                + "\n6. View developer's games."
                + "\n7. View a user's purchase history."
                + "\n8. Exit."
                + "\nSelect an option: "
        );
        return Utilities.leerInt(1, 8);
    }
     
     
}
