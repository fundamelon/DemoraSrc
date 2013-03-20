package main.gui;

import main.ControlManager;

import org.newdawn.slick.geom.Rectangle;

import util.Util;

public class Slider implements Clickable {
	public int id;
	private String name;
	public float value, min, max, prev;
	
	private Rectangle bounds;
	private int eventKey;
	private String text = "";
	private String type = "slider";
	private boolean status;
	private boolean prevStatus;
	private boolean mouseEntered;
	private boolean mouseExited;
	private boolean mouseClicked;
	private boolean mouseReleased;
	private boolean mouseOver;
	private boolean toggle = false;
	private boolean visible = true;
	private boolean locked = false;
	
	public Slider(int newID, String label, float x, float y, float length, float min, float max, float init, int eventKey) {
		this.id = newID;
		this.name = label;
		this.setBounds(new Rectangle(x-32, y-16, length+64, 25+32));
		this.min = min;
		this.max = max;
		this.value = init;
		this.eventKey = eventKey;
	}

	public void update() {
		if(mouseHover() != mouseOver) {
			if(mouseHover()) {
				mouseEntered = true;
			} else {
				mouseExited = true;
			}
			mouseOver = mouseHover();
		} else {
			mouseEntered = false;
			mouseExited = false;
		}
		
		if(!status && mouseDown()) {
			status = true;
			prev = value;
		}

		if(status) {
			if(mouseOver || !mouseDown()) {
			value = Util.clamp(((ControlManager.getMouseX() - bounds.getX() - 32) / (bounds.getWidth() - 64)), min, max);
			} else {
				value = prev;
			}
		}
		
		if(status && !mouseDown()) {
			status = false;
		}
		
		mouseClicked = mouseClick();
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public float getValue() {
		return value;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public void lock() { locked = true;}
	public void unlock() { locked = false;}
	public void lock(boolean a) {locked = a;}

	public boolean mouseClick() {
		return !GUIManager.clicklock && mouseHover() && ControlManager.mouseButtonClicked(ControlManager.mousePrimary);
	}
	
	public boolean mouseRelease() {
		return mouseExited || ControlManager.mouseButtonReleased(ControlManager.mousePrimary);
	}
	
	public boolean mouseDown() {
		return mouseHover() && ControlManager.mouseButtonStatus(ControlManager.mousePrimary);
	}
	
	public boolean mouseHover() {
		return bounds.contains(ControlManager.getMouseX(), ControlManager.getMouseY());
	}

	public boolean mouseEnter() {		
		return mouseEntered;
	}

	public boolean mouseExit() {
		return mouseExited;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle newBounds) {
		bounds = newBounds;
	}

	public void setEventKey(int key) {
		eventKey = key;
	}

	public int getEventKey() {
		return eventKey;
	}

	@Override
	public int getID() {
		return id;
	}
	
	public void setToggleMode(boolean mode) {}
	public boolean getToggleMode() {
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
	}
	
	
}
