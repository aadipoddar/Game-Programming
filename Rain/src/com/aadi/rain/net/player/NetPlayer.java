package com.aadi.rain.net.player;

import com.aadi.rain.entity.mob.Mob;
import com.aadi.rain.graphics.Screen;

public class NetPlayer extends Mob {

	public NetPlayer() {
		x = 22 * 16;
		y = 42 * 16;
	}

	public void update() {

	}

	public void render(Screen screen) {
		screen.fillRect(x, y, 32, 32, 0x2030cc, true);
	}

}