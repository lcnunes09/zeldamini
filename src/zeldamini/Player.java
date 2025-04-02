package zeldamini;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle {
    // Using the Rectangle class simplifies handling collision and graphical representation in 2D games.

    public int spd = 4; // Speed of the player (movement velocity)
    public boolean right, up, down, left; // Flags for player movement directions

    
    public int curAnimation = 0;
    
    public int curFrames = 0, targetFrames = 15;
    
    public static List<Arrow> arrows = new ArrayList<Arrow>();
    
    public boolean shoot = false;
    
    public int dir = 1; // Player Direction
    
    public Player(int x, int y) {
        super(x, y, 32, 32); // Initialize the player with its starting position and dimensions
    }

    // Handles player movement logic based on direction flags and checks for collisions
    public void tick() {
    	boolean moved = false;
        if (right && World.isFree(x + spd, y)) { 
            x += spd; // Move right if no collision at the new position
            moved = true;
            dir = 1;
        } else if (left && World.isFree(x - spd, y)) { 
            x -= spd; // Move left if no collision at the new position
            moved = true;
            dir = -1;
        }

        if (up && World.isFree(x, y - spd)) { 
            y -= spd;
            moved = true;
            dir = -2; // Up
        } else if (down && World.isFree(x, y + spd)) { 
            y += spd;
            moved = true;
            dir = 2; // Down
        }
        
        if (moved) {
	        curFrames++;
	        if (curFrames == targetFrames) {
	        	curFrames = 0;
	        	curAnimation++;
	        	if(curAnimation == Spritesheet.player_front.length) {
	        		curAnimation = 0;
	        	}
	        }
        }
        
        if (shoot) {
            shoot = false;
            arrows.add(new Arrow(x, y, dir)); // Use the current movement direction
        }
        
        for (int i = 0; i < arrows.size(); i++) {
        	arrows.get(i).tick();
        }
    }

    public void render(Graphics g) {
    	// Draws the player as a blue rectangle on the screen
        // g.setColor(Color.blue); 
        // g.fillRect(x, y, width, height); 
    	
    	g.drawImage(Spritesheet.player_front[curAnimation], x, y, 32, 32, null);
    	
    	for (int i = 0; i < arrows.size(); i++) {
        	arrows.get(i).render(g);
        }
    }
}