package zeldamini;

import java.awt.image.BufferedImage; // Handles images in memory
import java.io.IOException; // Handles input/output exceptions

import javax.imageio.ImageIO; // Provides methods to read images from files

public class Spritesheet {

    // Static variable to hold the loaded spritesheet image in memory
    public static BufferedImage spritesheet;

    // Individual sprites extracted from the spritesheet
    public static BufferedImage[] player_front; // Sprite representing the player character facing forward
    public static BufferedImage tileWall; // Sprite representing a wall tile

    // Constructor to load the spritesheet and initialize sprites
    public Spritesheet() {
        try {
            // Load the spritesheet image from the resources folder
            spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png"));			
        } catch (IOException e) {
            // Print the stack trace if there's an error loading the image
            e.printStackTrace();
        }

        // Extract specific sprites from the spritesheet based on their location and size
        player_front = new BufferedImage[3]; // Create array to get the player position when moving
        player_front[0] = Spritesheet.getSprite(0, 11, 16, 16); // Extract the sprite for the player character (x=0, y=11, width=16, height=16)
        player_front[1] = Spritesheet.getSprite(16, 11, 16, 16); 
        tileWall = Spritesheet.getSprite(279, 220, 16, 16); // Extract the sprite for a wall tile (x=279, y=220, width=16, height=16)
    }

    // Method to extract a subimage (sprite) from the spritesheet
    // Parameters:
    // - x: The x-coordinate of the top-left corner of the sprite
    // - y: The y-coordinate of the top-left corner of the sprite
    // - width: The width of the sprite
    // - height: The height of the sprite
    public static BufferedImage getSprite(int x, int y, int width, int height) {
        return spritesheet.getSubimage(x, y, width, height); // Returns a cropped portion of the spritesheet as a BufferedImage
    }
}