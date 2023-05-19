package za.co.wethinkcode.robotworlds.server.world;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Config_World {
    private int widthOfTheWorld;
    private int lengthOfTheWorld;
    private int visibility;
    private float repairTime;
    private float weaponsReloadTime;
    private int maximumHits;
    private Properties myConfigs;

    public Config_World() {
        loadFileContents();
        loadConfigValues();
    }

    public int getWidthOfTheWorld() {
        return widthOfTheWorld;
    }

    public int getLengthOfTheWorld() {
        return lengthOfTheWorld;
    }

    public int getVisibility() {
        return visibility;
    }

    public float getRepairTime() {
        return repairTime;
    }

    public float getWeaponsReloadTime() {
        return weaponsReloadTime;
    }

    public int getMaximumHits() {
        return maximumHits;
    }

    public void loadFileContents() {
        try (InputStream input = new FileInputStream("World_Config.properties")) {
            this.myConfigs = new Properties();
            myConfigs.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadConfigValues() {
        this.widthOfTheWorld = Integer.parseInt(myConfigs.getProperty("widthOfTheWorld"));
        this.lengthOfTheWorld = Integer.parseInt(myConfigs.getProperty("lengthOfTheWorld"));
        this.visibility = Integer.parseInt(myConfigs.getProperty("visibility"));
        this.repairTime = Float.parseFloat(myConfigs.getProperty("repairTime"));
        this.weaponsReloadTime = Float.parseFloat(myConfigs.getProperty("weaponsReloadTime"));
        this.maximumHits = Integer.parseInt(myConfigs.getProperty("maximumHits"));
    }

    public void displayDefaultConfigs() {
        System.out.println("Here are the default configs of the world:");
        System.out.println("Width of the World: " + getWidthOfTheWorld());
        System.out.println("Length of the World: " + getLengthOfTheWorld());
        System.out.println("Robot Visibility: " + getVisibility());
        System.out.println("Time it Takes to Repair your robot: " + getRepairTime());
        System.out.println("Maximum Robot Hit: " + getMaximumHits());
        System.out.println("The time it takes to Reload your Weapon: " + getWeaponsReloadTime());
    }

    public void changeDefaultConfigs() {
        try (OutputStream output = new FileOutputStream("World_Config.properties")) {
            myConfigs.setProperty("widthOfTheWorld", askUserToChangeConfigs("Width of the World"));
            myConfigs.setProperty("lengthOfTheWorld", askUserToChangeConfigs("Length of the World"));
            myConfigs.setProperty("visibility", askUserToChangeConfigs("Robot Visibility"));
            myConfigs.setProperty("repairTime", askUserToChangeConfigs("Time it Takes to Repair your robot"));
            myConfigs.setProperty("maximumHits", askUserToChangeConfigs("Maximum Robot Hit"));
            myConfigs.setProperty("weaponsReloadTime", askUserToChangeConfigs("Time it takes to Reload your Weapon"));
            myConfigs.store(output, null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String askUserToChangeConfigs(String configName) {
        Scanner userInput = new Scanner(System.in);
        System.out.print("Enter new value for " + configName + ": ");
        return userInput.nextLine();
    }
}
