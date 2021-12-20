package com.aadi.rain.level.tile.spawn_level;

import com.aadi.rain.graphics.Screen;
import com.aadi.rain.graphics.Sprite;
import com.aadi.rain.level.tile.Tile;

public class SpawnGrassTile extends Tile {

	public SpawnGrassTile(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

}