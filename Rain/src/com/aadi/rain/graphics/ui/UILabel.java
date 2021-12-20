package com.aadi.rain.graphics.ui;

import com.aadi.rain.graphics.Font;
import com.aadi.rain.graphics.Screen;
import com.aadi.rain.util.Vector2i;

public class UILabel extends UIComponent {

	public String text;
	private Font font;

	public UILabel(Vector2i position, String text) {
		super(position);
		font = new Font();
		this.text = text;
	}

	public void render(Screen screen) {
		font.render(position.x + offset.x, position.y + offset.y, -4,0,text, screen);
	}

}