package main;

import utilities.*;


/**
 *
 * @author etnag
 */
public class Main {
    public static void main(String[] args) {
        int option;
         
        do{
            option=menu();
            switch(option){
                case 1:
                    break;
                case 2:
                    
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        }while(option!=7);
    }
     
     
    public static int menu(){
        int option;
        System.out.print("------------------------MENU------------------------"
                + "/n1. Register a game."
                + "/n2. Register a user."
                + "/n3. Register a developer."
                + "/n4. Purchase a game."
                + "/n5. View avaliable games."
                + "/n6. View developer's games."
                + "/n7. View a user's purchase history."
                + "/nSelect an option: "
        );
        option=Utilities.leerInt(1, 7);
        return option;
    }
     
     
}
