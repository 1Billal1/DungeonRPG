package DungeonRPG;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author billalg
 */

/*
 * The TextAdventure class is the main game class that coordinates the
 * different components of the text adventure game.
 */
public class TextAdventure implements java.io.Serializable {
    private InputParser inputParser;
    private AreaManager gameMap;
    private Player player;
    private PlayerManager playerManager;
    private ItemList playerInventory;

    /*
     * Constructs a new TextAdventure object.
     * It initializes the InputParser, AreaManager, Player, PlayerManager,
     * and PlayerInventory.
     */
    public TextAdventure() {
        this.inputParser = new InputParser(this);
        this.gameMap = new AreaManager();
        this.gameMap.constructArea();
        this.playerInventory = new ItemList();
        this.player = new Player("player", "the main character", gameMap.getMap().get(0), playerInventory);
        this.playerManager = new PlayerManager(this);
    }

    /*
     * Returns the player object
     * @return the player object
     */
    public Player getPlayer() {
        return this.player;
    }

    /*
     * Runs the given user command and assigns the command processing to
     * the InputParser
     */
    public String runCommand(String userInput) {
        return inputParser.runCommand(userInput);
    }

    /**
     * Moves the player to the specified direction.
     * It gets the current player location, determines the exit in the
     * specified direction, and updates the player's location if a valid
     * exit is found.
     * @param direction the direction to move the player
     */
    public void movePlayerTo(String direction) {
        Area currentPlayerLocation = player.getPlayerLocation();
        int exit;

        if (direction.equals("north")) {
            exit = currentPlayerLocation.getNorthExit();
        } else if (direction.equals("south")) {
            exit = currentPlayerLocation.getSouthExit();
        } else if (direction.equals("east")) {
            exit = currentPlayerLocation.getEastExit();
        } else if (direction.equals("west")) {
            exit = currentPlayerLocation.getWestExit();
        } else {
            exit = -1; // no exit - player stays in the same area
        }

        // Prints current player location if exit is found.
        if (exit != -1) {
            player.setPlayerLocation(gameMap.getMap().get(exit));
            System.out.println(player.getPlayerLocation().describeArea());
        } else {
            System.out.println("No Exit");
        }
    }

    /*
     * Prints the introduction message for the game.
     * It displays the initial location and prompts the user for input.
     */
    public void printIntroduction() {
        String introductionMessage = "As you tumble down a mysterious rabbit hole, you land softly in\n"
                + "an underground cavern permeated with the scent of ancient troll dwellings.\n"
                + "Which direction shall you venture? (Or type 'quit' to " + "abandon your journey) \n";
        String moveInstruction = "Enter 'move' followed by the direction you wish " + "to go (north, south, west, est): \n";

        System.out.println(introductionMessage + moveInstruction);
        System.out.println(player.getPlayerLocation().describeArea());
    }
}