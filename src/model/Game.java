/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ivanv
 */
public class Game implements Serializable{
    private int gameId;
    private int developerId;
    private String gameName;
    private double price;
    private int stock;
    private GameType type;
    

    public Game(int gameId, String gameName, int developerId, double price, int stock, GameType type) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.price = price;
        this.stock = stock;
        this.type = type;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", developerId=" + developerId + ", gameName=" + gameName + ", price=" + price + ", stock=" + stock + ", type=" + type + '}';
    }
    
    
    
    
}
