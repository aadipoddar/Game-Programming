package com.aadi.rain.graphics;

// This class Fills pixels in with the colors that we specify
public class Screen {

	private int width, height;
	public int[] pixels;

	int xtime = 100, ytime = 50;
	int counter = 0;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render() {
		counter++;

		if (counter % 100 == 0) xtime--;
		if (counter % 100 == 0) ytime--;

		for (int y = 0; y < height; y++) {
			// Deals With Out of Bounds Exception when the pixel goes beyond the size of the frame in the y axis
			if (ytime < 0 || ytime >= height) break;

			for (int x = 0; x < width; x++) {
				// Deals With Out of Bounds Exception when the pixel goes beyond the size of the frame in the x axis
				if (xtime < 0 || xtime >= width) break;

				pixels[xtime + ytime * width] = 0xff00ff; // Index of the array shows the Coordinate System of the Screen
			}
		}
	}

}