package zeldamini;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {

    public static List<Block> bloco = new ArrayList<Block>(); // List of blocks that make up the game's world boundaries

    public World() {
        // Create the top boundary of the game world (15 blocks in a row)
        for (int xx = 0; xx < 15; xx++) {
            bloco.add(new Block(xx * 32, 0));
        }

        // Create the bottom boundary of the game world (15 blocks in a row)
        for (int xx = 0; xx < 15; xx++) {
            bloco.add(new Block(xx * 32, 480 - 32));
        }

        // Create the left boundary of the game world (15 blocks in a column)
        for (int yy = 0; yy < 15; yy++) {
            bloco.add(new Block(0, yy * 32));
        }

        // Create the right boundary of the game world (15 blocks in a column)
        for (int yy = 0; yy < 15; yy++) {
            bloco.add(new Block(640 - 32, yy * 32));
        }
    }

    // Checks if a given position (x, y) is free (not occupied by any block)
    public static boolean isFree(int x, int y) {
        for (int i = 0; i < bloco.size(); i++) { 
            Block blocoAtual = bloco.get(i); // Get the current block from the list
            if (blocoAtual.intersects(new Rectangle(x, y, 32, 32))) {
                // If the block intersects with the given rectangle, the position is not free
                return false;
            }
        }

        return true; // Return true if no intersections are found
    }

    // Render all the blocks in the world by iterating over the block list
    public void render(Graphics g) {
        for (int i = 0; i < bloco.size(); i++) {
            bloco.get(i).render(g); // Call the render method of each block
        }
    }
}