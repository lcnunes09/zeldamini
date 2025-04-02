package zeldamini;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Arrow extends Rectangle{
	
	public int dir = 1;
	public int speed = 8;
	
	public int frames = 0;
	
	public Arrow(int x, int y, int dir) {
		super(x+16,y,20,20);
		this.dir = dir;
	}
	
	public void tick() {
		x += speed*dir;
		frames++;
		if (frames == 60) {
			Player.arrows.remove(this); // Remove the arrows (better rendering)
			return;
		}
	}
	
	public void render(Graphics g) {
		// g.setColor(Color.red);
		// g.fillRect(x, y, width, height);
		
		g.drawImage(Spritesheet.arrow, x, y, 32, 32, null);
	}
}
