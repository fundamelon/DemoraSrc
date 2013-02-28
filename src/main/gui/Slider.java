package main.gui;

import org.newdawn.slick.geom.Rectangle;

public class Slider implements Clickable {
	public int id;
	public float value, min, max;
	
	public Slider(int newID, float x, float y, float length, float min, float max, float init) {
		this.id = newID;
		this.setBounds(new Rectangle(x, y, x + length, 25));
		this.min = min;
		this.max = max;
		this.value = init;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public float getValue() {
		return value;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBounds(Rectangle newBounds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseClick() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseRelease() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseDown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseHover() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseEnter() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseExit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getToggleMode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getEventKey() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setToggleMode(boolean mode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEventKey(int key) {
		// TODO Auto-generated method stub
		
	}
	
	
}
