package main.gui;

public interface GUIObject {
	public void update();
	
	public boolean isVisible();

	public int getID();
	public String getName();
	
	public String getText();
	public void setText(String text);
	
	public String getType();

	public org.newdawn.slick.geom.Rectangle getBounds();
	public void setBounds(org.newdawn.slick.geom.Rectangle newBounds);
}
