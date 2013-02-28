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
<<<<<<< HEAD
<<<<<<< HEAD
	public void init(float nx, float ny, boolean tilewise);
=======
	public void init(int nx, int ny, boolean tilewise);
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
	public void init(int nx, int ny, boolean tilewise);
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	
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
<<<<<<< HEAD
<<<<<<< HEAD
	
	public void damage(float damage);
	public void damage(float damage, Entity attacker);
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9

	public boolean castShadows();
	
	public Path getCurrentPath();
	public boolean isJumping();
<<<<<<< HEAD
<<<<<<< HEAD
	
	public boolean isDead();
	
	public boolean initialized();
	public void kill();
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
}
