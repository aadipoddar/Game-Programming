package com.aadi.rain.graphics;

import java.util.Random;

// This class Fills pixels in with the colors that we specify
public class Screen {

	private int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;

	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
			tiles[0] = 0;
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render(int xOffset, int yOffset) {

		for (int y = 0; y < height; y++) {

			int yy = y + yOffset;

			// Deals With Out of Bounds Exception when the pixel goes beyond the size of the frame in the y axis
			//if (yy < 0 || yy >= height) break;

			for (int x = 0; x < width; x++) {

				int xx = x + xOffset;

				// Deals With Out of Bounds Exception when the pixel goes beyond the size of the frame in the x axis
				//if (xx < 0 || xx >= width) break;

				int tileIndex = ((xx >> 4) & MAP_SIZE_MASK) + ((yy >> 4) & MAP_SIZE_MASK) * MAP_SIZE; // same as this { int tileIndex = (x / 16) + (y / 16) * 64; } using bitwise operator				

				pixels[x + y * width] = tiles[tileIndex]; // Index of the array shows the Coordinate System of the Screen
			}
		}
	}

}
