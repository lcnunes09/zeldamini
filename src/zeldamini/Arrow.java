package zeldamini;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Arrow extends Rectangle {

    public int dir = 1; // Arrow direction (1 for right, -1 for left)
    public int speed = 5; // Arrow speed
    public int frames = 0; // Timer for removing the arrow after a certain period

    public Arrow(int x, int y, int dir) {
    	super(x, y, 20, 20); // Base position

        this.dir = dir;

        // Adjust arrow position based on direction
        if (dir == 1) { // Right
            this.x += 32; // Move arrow to the right of the player
        } else if (dir == -1) { // Left
            this.x -= 16; // Position slightly left of the player
        } else if (dir == -2) { // Up
            this.y -= 16; // Move arrow above the player
        } else if (dir == 2) { // Down
            this.y += 32; // Move arrow below the player
        }
    }

    public void tick() {
        // Move the arrow in the current direction
    	if (dir == 1 || dir == -1) {
            // Left/right movement
            x += speed * dir;
        } else if (dir == 2) {
            // Move downward
            y += speed;
        } else if (dir == -2) {
            // Move upward
            y -= speed;
        }

        // Increment frames and remove the arrow after 60 frames
        frames++;
        if (frames == 60) {
            Player.arrows.remove(this); // Remove the arrow from the list
            return;
        }
    }

    public void render(Graphics g) {
    	BufferedImage arrowSprite;

        // Select the correct arrow sprite based on direction
        if (dir == 1) { // Moving right
            arrowSprite = Spritesheet.arrow_right;
        } else if (dir == -1) { // Moving left
            arrowSprite = Spritesheet.arrow_left;
        } else if (dir == 2) { // Moving down
            arrowSprite = Spritesheet.arrow_down;
        } else { // Moving up
            arrowSprite = Spritesheet.arrow_up;
        }

        g.drawImage(arrowSprite, x, y, 32, 32, null);

    }
}