package com.aadi.rain.graphics;

// This class loads our individual sprite
public class Sprite {

	private final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	// This is extracting a single sprite out of our sprite sheet 
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}

}
