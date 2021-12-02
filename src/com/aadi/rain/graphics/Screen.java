package com.aadi.rain.graphics;

// This class Fills pixels in with the colors that we specify
public class Screen {

	private int width, height;
    private int[] pixels;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
    }

}
