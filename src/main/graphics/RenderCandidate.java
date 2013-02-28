package main.graphics;

import org.lwjgl.util.vector.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

public class RenderCandidate {
	public Image image = null;
	public float priority = -1;
	public Vector2f pos = new Vector2f(0, 0);
	public float hshear = 0, vshear = 0;
	public Color col = new Color(1f, 1f, 1f, 1f);
	public RenderCandidate(Image i, float p) {
		this.image = i.copy();
		this.priority = p;
	}
	
	public RenderCandidate(Image i, Vector2f newpos, float p) {
		this(i, p);
		this.pos = newpos;
	}
	
	public RenderCandidate(Image i, Vector2f newpos, Color c, float p) {
		this(i, newpos, p);
		this.col = c;
	}
	
	public RenderCandidate(Image i, float x, float y, Color c, float p) {
		this(i, new Vector2f(x, y), c, p);
	}
	
	public RenderCandidate(Image i, float x, float y, float h, float v, float p) {
		this(i, x, y, Color.white, p);
		hshear = h;
		vshear = v;
	}
	
	public RenderCandidate(Image i, float x, float y, float p) {
		this(i, new Vector2f(x, y), p);
	}

	public float getPriority() {return priority;}
	public void setPriority(float p) {this.priority = p;}
	
	public Image getImage() {return image;}
	public void setImage(Image i) {image = i.copy();}
	
	public Color getColor() {return col;}
	public void setColor(Color newcol) {col = newcol;}
	
	public Vector2f getPos() {return pos;}
	public float getX() {return pos.x;}
	public float getY() {return pos.y;}
	public void setPos(Vector2f newpos) {pos = newpos;}
	
	public Vector2f getShear() {
		return new Vector2f(hshear, vshear);
	}
}
