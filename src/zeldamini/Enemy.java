package zeldamini;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Rectangle {
    // Using the Rectangle class simplifies handling collision and graphical representation in 2D games.

    public int spd = 2; // Speed of the enemy (movement velocity)
    public int right = 1, up = 0, down = 0, left = 0; // Flags for enemy movement directions (AI)

    
    public int curAnimation = 0;
    
    public int curFrames = 0, targetFrames = 15;
    
    public static List<Arrow> arrows = new ArrayList<Arrow>();
    
    public boolean shoot = false;
    
    public int dir = 1; // Player Direction
    
    public Enemy(int x, int y) {
        super(x, y, 32, 32); // Initialize the player with its starting position and dimensions
    }
    
    public void followPlayer() {
    	Player p = Game.player;
    	if (x < p.x && World.isFree(x+spd, y)) {
    		if (new Random().nextInt(100) < 50) {
    			x += spd;
    		}
    	} else if (x > p.x && World.isFree(x-spd, y)) {
    		if (new Random().nextInt(100) < 50) {
    			x -= spd;
    		}
    	}
    	
    	if (y < p.y && World.isFree(x, y+spd)) {
    		if (new Random().nextInt(100) < 50) {
    			y += spd;
    		}
    	} else if (y > p.y && World.isFree(x, y-spd)) {
    		if (new Random().nextInt(100) < 50) {
    			y -= spd;
    		}
    	}
    }

    // Handles player movement logic based on direction flags and checks for collisions
    public void tick() {
    	boolean moved = true;
        
    	followPlayer();
        
        if (moved) {
	        curFrames++;
	        if (curFrames == targetFrames) {
	        	curFrames = 0;
	        	curAnimation++;
	        	if(curAnimation == Spritesheet.enemy_front.length) {
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
    	
    	g.drawImage(Spritesheet.enemy_front[curAnimation], x, y, 32, 32, null);
    	
    	for (int i = 0; i < arrows.size(); i++) {
        	arrows.get(i).render(g);
        }
    }
}