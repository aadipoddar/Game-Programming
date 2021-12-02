package com.aadi.rain;

import java.awt.Canvas;
import java.awt.Color;
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
		while (running) {
			update();
			render();
		}
	}

	public void update() {
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

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();

		// Specifications for the Window
		game.frame.setResizable(false);
		game.frame.setTitle("Rain");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}
