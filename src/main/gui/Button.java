package main.gui;

import main.ControlManager;

import org.newdawn.slick.geom.Rectangle;

@SuppressWarnings("all")
public class Button implements Clickable{

	private Rectangle bounds;
	private int eventKey;
	private String text = "";
	private String type = "button";
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
	private Object value = null;
	
	private String name;
	public int id;

	public Button(int newId, String newName, Rectangle newBounds) {
		name = newName;
		id = newId;
		bounds = newBounds;
	}
	
	public Button(int newId, Rectangle newBounds) {
		this(newId, ""+newId, newBounds);
	}
	
	public Button(int newId, float x, float y, float width, float height) {
		this(newId, new Rectangle(x, y, width, height));
	}
	
	public Button(int newId) { 
		this(newId, new Rectangle(0, 0, 100, 100));
	}
	
	public Button(int newId, String text, float x, float y, float width, float height, int eventKey) {
		this(newId, x, y, width, height);
		this.eventKey = eventKey;
		this.text = text;
	}
	
	public Button(int newId, String text, float x, float y, float width, float height, int eventKey, Object nVal) {
		this(newId, text, x, y, width, height, eventKey);
		value = nVal;
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
		
		status = mouseDown();		
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
	
	public Object getValue() {
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

	public boolean getToggleMode() {
		return toggle;
	}

	public void setToggleMode(boolean mode) {
		toggle = mode;		
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	@Override
	public int getID() {
		return id;
	}

}
