package com.aadi.rain.entity.particle;

import java.util.ArrayList;
import java.util.List;

import com.aadi.rain.entity.Entity;
import com.aadi.rain.graphics.Screen;
import com.aadi.rain.graphics.Sprite;

public class Particle extends Entity {

	private List<Particle> particles = new ArrayList<Particle>();
	private Sprite sprite;

	private int life;

	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.life = life;
		sprite = Sprite.particle_normal;

		particles.add(this);
	}

	public Particle(int x, int y, int life, int amount) {
		this(x, y, life);

		for (int i = 0; i < amount - 1; i++) {
			particles.add(new Particle(x, y, life));
		}
	}

	public void update() {
	}

	public void render(Screen screen) {
	}

}
