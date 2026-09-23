/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import utilidades.Utilities;
import utilities.MyObjectOutputStream;

/**
 *
 * @author ivanv
 */
public class GameManagement {

    public static Game createGame() {
        String gameName, opcion;
        int developerId, stock, gameId;
        double gamePrice;
        GameType type = null;
        System.out.println("Game ID:");
        gameId = Utilities.leerInt();
        System.out.println("Game name:");
        gameName = Utilities.introducirCadena();
        System.out.println("Developer ID:");
        developerId = Utilities.leerInt();
        System.out.println("Game Price");
        gamePrice = Utilities.leerDouble();
        System.out.println("Game Stock");
        stock = Utilities.leerInt();
        System.out.println("What game type");
        opcion = Utilities.introducirCadena("Action", "Sports", "RPG");

        switch (opcion.toUpperCase()) {
            case "ACTION":
                type = GameType.ACTION;
                break;
            case "SPORTS":
                type = GameType.SPORTS;
                break;
            case "RPG":
                type = GameType.RPG;
                break;
        }
        System.out.println("New game created");
        return new Game(gameId, gameName, developerId, gamePrice, stock, type);            
    }
     public static void guardarJuego(Game g, File fichero) {
        try {
            if (fichero.exists()) {
                FileOutputStream fos = new FileOutputStream(fichero, true);
                MyObjectOutputStream moos = new MyObjectOutputStream(fos);
                moos.writeObject(g);
                moos.close();
            } else {
                FileOutputStream fos = new FileOutputStream(fichero);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(g);
                oos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mostrarJuegos(File fichero) {
        if (!fichero.exists()) {
            System.out.println("No hay juegos.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            while (true) {
                Game g = (Game) ois.readObject();
                System.out.println(g);
            }
        } catch (EOFException e) {
            // Fin del fichero
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


