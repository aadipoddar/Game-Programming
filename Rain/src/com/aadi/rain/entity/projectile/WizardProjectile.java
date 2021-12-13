package com.aadi.rain.entity.projectile;

import com.aadi.rain.graphics.Screen;

public class WizardProjectile extends Projectile {

	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 4;
		damage = 20;
		rateOfFire = 15;
		sprite = sprite.projectile_wizrd;

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	public void update() {
		move();
	}

	protected void move() {
		x += nx;
		y += ny;
	}

	public void render(Screen screen) {
		screen.renderProjectile(x, y, this);
	}

}
