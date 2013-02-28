package main.gui;


public interface Clickable extends GUIObject {
	public boolean getStatus();
	public boolean mouseClick();
	public boolean mouseRelease();
	public boolean mouseDown();
	public boolean mouseHover();
	public boolean mouseEnter();
	public boolean mouseExit();
	public boolean getToggleMode();
	public int getEventKey();
<<<<<<< HEAD
=======
	public int getID();
	
	public String getName();
	
	public String getText();
	public void setText(String text);
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9

	public void setToggleMode(boolean mode);
	public void setEventKey(int key);
	
}
