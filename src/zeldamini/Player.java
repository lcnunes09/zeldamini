package zeldamini;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle {
    // Using the Rectangle class simplifies handling collision and graphical representation in 2D games.

    public int spd = 4; // Speed of the player (movement velocity)
    public boolean right, up, down, left; // Flags for player movement directions

    public Player(int x, int y) {
        super(x, y, 32, 32); // Initialize the player with its starting position and dimensions
    }

    // Handles player movement logic based on direction flags and checks for collisions
    public void tick() {
        if (right && World.isFree(x + spd, y)) { 
            x += spd; // Move right if no collision at the new position
        } else if (left && World.isFree(x - spd, y)) { 
            x -= spd; // Move left if no collision at the new position
        }

        if (up && World.isFree(x, y - spd)) { 
            y -= spd; // Move up if no collision at the new position
        } else if (down && World.isFree(x, y + spd)) { 
            y += spd; // Move down if no collision at the new position
        }
    }

    // Draws the player as a blue rectangle on the screen
    public void render(Graphics g) {
        g.setColor(Color.blue); 
        g.fillRect(x, y, width, height); 
    }
}