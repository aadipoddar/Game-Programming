package com.aadi.rain.graphics;

// This class Fills pixels in with the colors that we specify
public class Screen {

	private int width, height;
    public int[] pixels;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

    public void render() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = 0xff00ff; // Index of the array shows the Coordinate System of the Screen
                //pixels[20 + 30 * width] = 0xff00ff;
                //pixels[40+ 30 * width] = 0xff00ff;
            }
        }
    }

}
