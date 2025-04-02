package zeldamini;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {
    // Define the size of the game window (480x480 pixels)
    public static int WIDTH = 480, HEIGHT = 480;

    public Player player;
    public World world;

    // Initialize the game with key listeners and dimensions
    public Game() {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        new Spritesheet();

        player = new Player(32, 32); // Create the player at the initial position (top-left corner)
        world = new World(); // Initializes and renders the entire game world, including elements like enemies, blocks, and other entities.
    }

    // Captures game logic, such as player movement and collision detection
    public void tick() {
        player.tick();
    }

    // Renders graphics for the game
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3); // Create a triple-buffer for optimized rendering
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(new Color(0,135,13)); 
        g.fillRect(0, 0, WIDTH, HEIGHT); // Clear the screen by filling it with a black rectangle

        player.render(g); // Render the player object'
        
        world.render(g); // Render the world

        bs.show(); 
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game); 
        frame.setTitle("Mini Zelda"); // Set the title of the game window

        frame.pack(); // Adjust the window size based on its content

        frame.setLocationRelativeTo(null); // Center the game window on the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the Java process when exiting the game

        frame.setVisible(true);

        new Thread(game).start(); // Start the game loop by invoking the "run" method
    }

    @Override
    public void run() {
        while (true) { // Infinite loop to continuously update and render the game
            tick();
            render();

            try {
                Thread.sleep(1000 / 60); // Run the game at 60 frames per second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Unused method
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Update direction flags based on key presses
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Reset direction flags when keys are released
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            player.up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = false;
        }
    }
}