package model;

/**
 *
 * @author CJ
 */
public class Developer {
    private int developerID;
    private String developerName;
    private String country;
    private int foundationYear;

    public Developer(int developerID, String developerName, String country, int foundationYear) {
        this.developerID = developerID;
        this.developerName = developerName;
        this.country = country;
        this.foundationYear = foundationYear;
    }

    public int getDeveloperID() {
        return developerID;
    }

    public void setDeveloperID(int developerID) {
        this.developerID = developerID;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(int foundationYear) {
        this.foundationYear = foundationYear;
    }
}