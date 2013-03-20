package main.entity;

import main.ai.Path;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

@SuppressWarnings("all")
public interface Entity {	
	public void draw();
	public void debugDraw(org.newdawn.slick.Graphics g);

	public float getX();
	public float getY();
	public float getAng();
	
	public void init();
	public void init(float nx, float ny, boolean tilewise);
	
	public void update();
	
	public void drawFgEffects();
	public void drawBgEffects();

	public String getName();
	public String getType();
	
	public Shape getBounds();
	public boolean hasCollisions();
	public boolean isMoving();
	
	public Image getImg();
	public Image getShadowCasterImg();
	
	public void setImg(String path);
	
	public float getImgOffsetX();
	public float getImgOffsetY();
	
	public float getHealth();
	public float getTotalHealth();
	
	public void damage(float damage);
	public void damage(float damage, Entity attacker);

	public boolean castShadows();
	
	public Path getCurrentPath();
	public boolean isJumping();
	
	public boolean isDead();
	
	public boolean initialized();
	public void kill();
}
