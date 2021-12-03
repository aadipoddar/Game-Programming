package com.aadi.rain.graphics;

import java.util.Random;

// This class Fills pixels in with the colors that we specify
public class Screen {

	private int width, height;
	public int[] pixels;

	public int[] tiles = new int[64 * 64];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		for (int i = 0; i < 64 * 64; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render() {

		for (int y = 0; y < height; y++) {
			// Deals With Out of Bounds Exception when the pixel goes beyond the size of the frame in the y axis
			if (y < 0 || y >= height) break;

			for (int x = 0; x < width; x++) {
				// Deals With Out of Bounds Exception when the pixel goes beyond the size of the frame in the x axis
				if (x < 0 || x >= width) break;

				int tileIndex = (x >> 4) + (y >> 4) * 64; // same as this { int tileIndex = (x / 16) + (y / 16) * 64; } using bitwise operator				

				pixels[x + y * width] = tiles[tileIndex]; // Index of the array shows the Coordinate System of the Screen
			}
		}
	}

}
