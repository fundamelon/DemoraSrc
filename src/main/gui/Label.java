package main.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.geom.Rectangle;

public class Label implements GUIObject {
	public String align = "center";
	public String text = "";
	public Rectangle bounds;
	public int id;
	public Color col;
	public Font font;
	
	public Label(int newId, String text, float x, float y, Color col, Font f, String align) {
		this.id = newId;
		this.col = col;
		this.font = f;
		this.setText(text);
		this.setBounds(new Rectangle(x, y, f.getWidth(text), f.getHeight(text)));
		this.align = align;
	}
	
	@Override
	public String getType() {
		return "label";
	}
	
	public Color getColor() {
		return col;
	}
	
	public Font getFont() {
		return font;
	}
	
	public String getAlign() {
		return align;
	}

	@Override
	public void update() {}
	
	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public String getName() {
		return null;
	}
	
	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public Rectangle getBounds() {
		return bounds;
	}

	
	@Override
	public void setBounds(Rectangle newBounds) {
		this.bounds = newBounds;
	}
}
