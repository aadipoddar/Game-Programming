package com.aadi.rain;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.aadi.rain.graphics.Screen;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	public static String title = "Rain";

	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

	// Array to store the colors of every pixel of the image which are stored in Hexadecimal format
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Game() {
		Dimension size = new Dimension(width * scale, height * scale); // Size of the window
		setPreferredSize(size);

		screen = new Screen(width, height);

		frame = new JFrame(); // Initializes a new Window
	}

	public synchronized void start() { // Starts the thread which are series of events that are executed in the background
		running = true;
		thread = new Thread(this, " Display");
		thread.start();
	}

	public synchronized void stop() { // Stops the thread which are series of events that are executed in the background
		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() { // Automatically called on Thread Start

		long lastTime = System.nanoTime(); // Computer's Current Time
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;

		while (running) {

			long now = System.nanoTime(); // Current loops time 
			delta += (now - lastTime) / ns; // Calculates the Time taken to Run the while loop
			lastTime = now;

			while (delta >= 1) {
				update();

				updates++;
				delta--;
			}

			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;

				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");

				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	int x = 0, y = 0;

	public void update() {
		x++;
		y++;
	}

	public void render() {
		// It is used to create a buffer strategy for the canvas which is used to render the image on the screen
		// BufferStrategy creates a temporary storage in the RAM which stores all the information of the colors to be presented on the screen
		// It stores the hexadecimal values of the colors to be presented on the screen in the next frame
		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			// We use the value 3 as it is highly optimal Value because
			// first values is that value which is currently begin presented on the screen
			// Second and Third Value are the 2 buffers that are already created to be presented on the screen in the next frame
			createBufferStrategy(3);
			return;
		}

		screen.clear();
		screen.render(x, 0);

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();

		// Specifications for the Window
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}
