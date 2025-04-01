package zeldamini;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Rectangle {

    public Block(int x, int y) {
        super(x, y, 32, 32); // Initialize the block's position and size (32x32 pixels)
    }

    // Draw the block as a magenta-filled rectangle with a black border
    public void render(Graphics g) {
        g.setColor(Color.magenta); // Set the block's fill color
        g.fillRect(x, y, width, height); // Draw the filled rectangle

        g.setColor(Color.black); // Set the color for the block's border
        g.drawRect(x, y, width, height); // Draw the rectangle's border
    }
}