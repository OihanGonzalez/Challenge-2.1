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
import java.util.ArrayList;
import java.util.List;
import utilities.Utilities;
import utilities.MyObjectOutputStream;

/**
 *
 * @author ivanv
 */
public class GameManagement {

    public static Game createGame(File file) {
        int gameId = getNextId(file);
        String gameName, opcion;
        int developerId, stock;
        double gamePrice;
        GameType type = null;
        System.out.println("Game name:");
        gameName = Utilities.introducirCadena();
        
        DBImplementationDeveloper devDAO = DBImplementationDeveloper.getInstance();
        Developer dev = null;       
        do {
            System.out.println("Developer ID:");
            developerId = Utilities.leerInt();

            dev = devDAO.getDeveloperById(developerId);

            if (dev == null) {
                System.out.println("Developer not found. Try again.");
            }
        } while (dev == null); 
        
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
        return new Game(gameId, developerId, gameName, gamePrice, stock, type);
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

    public static ArrayList<Game> mostrarJuegos(File fichero) {
        if (!fichero.exists()) {
            System.out.println("No hay juegos.");
            return null;
        }

        ArrayList<Game> aGames = new ArrayList<Game>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero))) {
            while (true) {
                Game g = (Game) ois.readObject();
                aGames.add(g);
                System.out.println(g);
            }
        } catch (EOFException e) {
            // Fin del fichero
        } catch (Exception e) {
            e.printStackTrace();
        }
        return aGames;
    }

    public static void fillGames(File fichero) {
        List<Game> games = new ArrayList<>();

        games.add(new Game(1, 1, "Shadow Blade", 29.99, 50, GameType.ACTION));
        games.add(new Game(2, 1, "Dragon Quest Legacy", 39.99, 30, GameType.RPG));
        games.add(new Game(3, 2, "Goal Rush 2026", 59.99, 100, GameType.SPORTS));
        games.add(new Game(4, 2, "Iron Frontier", 49.99, 60, GameType.ACTION));
        games.add(new Game(5, 3, "Mythic Realms", 44.99, 35, GameType.RPG));
        games.add(new Game(6, 3, "Street Basket Pro", 39.99, 70, GameType.SPORTS));
        games.add(new Game(7, 4, "Neon Runner", 14.99, 120, GameType.ACTION));
        games.add(new Game(8, 4, "Chronicles of Eldoria", 47.99, 20, GameType.RPG));
        games.add(new Game(9, 5, "Tennis Masters", 24.99, 90, GameType.SPORTS));
        games.add(new Game(10, 5, "Bullet Storm", 54.99, 55, GameType.ACTION));
        games.add(new Game(11, 6, "Legends of Valoria", 42.99, 40, GameType.RPG));
        games.add(new Game(12, 6, "Football Manager Live", 34.99, 80, GameType.SPORTS));
        games.add(new Game(13, 7, "Cyber Ninja", 27.99, 75, GameType.ACTION));
        games.add(new Game(14, 7, "Realm of Shadows", 37.99, 25, GameType.RPG));
        games.add(new Game(15, 8, "Extreme Snowboard", 19.99, 110, GameType.SPORTS));
        games.add(new Game(16, 8, "Crimson Assault", 44.99, 65, GameType.ACTION));
        games.add(new Game(17, 9, "Ancient Oath", 49.99, 30, GameType.RPG));
        games.add(new Game(18, 9, "Racing Legends GP", 32.99, 95, GameType.SPORTS));
        games.add(new Game(19, 10, "Storm Warrior", 22.99, 85, GameType.ACTION));
        games.add(new Game(20, 10, "Sword of the Realm", 41.99, 45, GameType.RPG));

        for (Game g : games) {
            guardarJuego(g, fichero);
        }
    }

    private static int getNextId(File file) {
        int lastId = 0;

        if (!file.exists()) {
            return 1;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                Game g = (Game) ois.readObject();
                lastId = g.getGameId();
            }
        } catch (EOFException e) {
            // Fin del fichero
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lastId + 1;
    }

}
