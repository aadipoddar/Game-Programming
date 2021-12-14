package com.aadi.rain.entity.mob;

import com.aadi.rain.Game;
import com.aadi.rain.entity.projectile.Projectile;
import com.aadi.rain.entity.projectile.WizardProjectile;
import com.aadi.rain.graphics.AnimatedSprite;
import com.aadi.rain.graphics.Screen;
import com.aadi.rain.graphics.Sprite;
import com.aadi.rain.graphics.SpriteSheet;
import com.aadi.rain.input.Keyboard;
import com.aadi.rain.input.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;

	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);

	private AnimatedSprite animSpite = down;

	private int fireRate = 0;

	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_forward;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_forward;
		fireRate = WizardProjectile.FIRE_RATE;
	}

	public void update() {
		if (walking) animSpite.update();
		else
			animSpite.setFrame(0);

		if (fireRate > 0) fireRate--;

		int xa = 0, ya = 0;

		if (anim < 7500) anim++;
		else
			anim = 0;

		if (input.up) {
			animSpite = up;
			ya--;
		} else if (input.down) {
			animSpite = down;
			ya++;
		}
		if (input.left) {
			animSpite = left;
			xa--;
		} else if (input.right) {
			animSpite = right;
			xa++;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}

		clear();
		updateShooting();
	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
	}

	private void updateShooting() {
		if (Mouse.getButton() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);

			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE;
		}
	}

	public void render(Screen screen) {
		int flip = 0;
		sprite = animSpite.getSprite();
		screen.renderMob(x - 16, y - 16, sprite, flip);
	}

}
