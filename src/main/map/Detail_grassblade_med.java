package main.map;

import main.GraphicsManager;
import main.entity.Entity;
import main.entity.EntityManager;
import main.entity.Entity_mobile;
<<<<<<< HEAD
<<<<<<< HEAD
import main.graphics.RenderCandidate;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
=======

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.Graphics;

@SuppressWarnings("all")
public class Detail_grassblade_med {
	
	public float x, y;
	public int type;
	public float offset = 0;
	public Image texture;
	public Image drawtex;
	boolean debugLines = false;
	float scaleFactor = 1f;
<<<<<<< HEAD
<<<<<<< HEAD
	int vertOffset;
	float xOffset, yOffset;
	int offsetDir = 0;
	
	public float windStrength = 1f;
	
	//Affects wind sway - more chaos means more random movement.
	public float chaos = 10;
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	int offsetDir = 0;
	
	//Affects wind sway - more chaos means more random movement. 0 - 10.
	public float chaos = 15;
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	
	float randOffset = (float)(Math.random()*0.3);
	
	//Wind direction in degrees
	public float windDir = 0;
	
	public Detail_grassblade_med(float x, float y) {
		this(0, x, y);
	}
	
	public Detail_grassblade_med(int type, float x, float y) {
		this.x = x;
		this.y = y;
		this.type = type;
		
		if(type == 0) {
<<<<<<< HEAD
<<<<<<< HEAD
			texture = GameMap.grassblade_tex0.copy();
			scaleFactor = 0.5f;
		} else if(type == 1) {
			texture = GameMap.grassblade_tex1.copy();
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			texture = GraphicsManager.grassblade_tex0.copy();
			scaleFactor = 0.5f;
		} else if(type == 1) {
			texture = GraphicsManager.grassblade_tex1.copy();
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			scaleFactor = 0.6f;
		} else {
			debugLines = true;
		}
<<<<<<< HEAD
<<<<<<< HEAD
		scaleFactor += (Math.random() * 0.5f) - 0.25f;
		
		xOffset = -(texture.getWidth() * scaleFactor * 0.5f);
		yOffset = -(texture.getHeight() * scaleFactor - 10);
=======
		
		this.x -= texture.getWidth() * 0.5f * scaleFactor;
		this.y -= texture.getHeight() * scaleFactor;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		
		this.x -= texture.getWidth() * 0.5f * scaleFactor;
		this.y -= texture.getHeight() * scaleFactor;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}
	
	public void draw() {
		Rectangle clipRect = main.Camera.getVisibleArea();
		clipRect.scaleGrow(1.05f, 1.1f);
		if(!clipRect.contains(x, y)) return;
		float t = System.nanoTime()/2000000000f;
<<<<<<< HEAD
<<<<<<< HEAD
		float swayAmt = (float)Math.cos(t + (Math.sin(x*0.0005) + (randOffset * Math.sin(t + x * 0.001)))*chaos)*8 + (float)Math.cos(t * 0.08 + y*0.001);
		swayAmt += Math.sin(t*0.03)*Math.cos(t*0.004 + x * 5)*Math.cos(t * 0.01 + x * 5)*windStrength*0.1f*swayAmt;
		
		swayAmt *= scaleFactor * 2f;
		
		float dist = 20;
		Entity_mobile e;
		for(Entity e_pre : EntityManager.entityTable){ 
			if(e_pre instanceof Entity_mobile) {
				e = (Entity_mobile) e_pre;
			} else continue;
			
			dist = (float)Math.sqrt(
					Math.pow(e.getBounds().getCenterY() - y, 2) +
					Math.pow(e.getBounds().getCenterX() - x, 2)
				);
			
		//	main.GameBase.g.draw(new Circle(x, y, 20));
			
			if(dist < 20 && !e.isJumping()){
				e.walkingThroughGrass = true;
				if(Math.abs(offset) <= 20 && offsetDir == 0) {
					if(e.vel.x * main.ControlManager.getDelta() > 10f) {
						offsetDir = -1;
					} else if(e.vel.x * main.ControlManager.getDelta() < -10f) {
						offsetDir = 1;
					} else {
						if(e.getBounds().getCenterX() < x) {
							offsetDir = -1;
						} else {
							offsetDir = 1;
						}
					}
				}
				vertOffset = Math.max((int)Math.abs(offset * 0.2f * (dist<20 ? 20-dist : 1)), vertOffset);
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		float swayAmt = (float)Math.cos(t + (Math.sin(x*0.0001) + (randOffset * Math.sin(t + x * 0.001)))*chaos)*8 + (float)Math.cos(t * 0.08 + y*0.001);
		swayAmt += Math.sin(t*0.003)*Math.cos(t*0.004 + x)*Math.cos(t * 0.08)*0.1f*swayAmt;
		float dist = 20;
		for(Entity e : EntityManager.entityTable){ 
			
			dist = (float)Math.sqrt(
					Math.pow(e.getBounds().getCenterY() - (y + texture.getHeight() * scaleFactor * 0.5), 2) +
					Math.pow(e.getBounds().getCenterX() - (x + texture.getWidth() * scaleFactor * 0.5), 2)
				);
			if(dist < 20 && !e.isJumping()){
				if(Math.abs(offset) <= 20 && offsetDir == 0) {
					if(EntityManager.getPlayer().getX()-16 < x) {
						offsetDir = -1;
					} else {
						offsetDir = 1;
					}
				}
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
				
				offset += 0.1 * offsetDir * main.ControlManager.getDelta();
				
				if(offset > 13) offset = 13;
				if(offset < -13) offset = -13;
			} else {
				offset *= 1 - (0.0005 * main.ControlManager.getDelta());
<<<<<<< HEAD
<<<<<<< HEAD
				vertOffset *= 1 - (0.000005 * main.ControlManager.getDelta());
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
				offsetDir = 0;
			}
		}
		
		swayAmt = (float)Math.sin(swayAmt * 0.08 * Math.cos((1/swayAmt) * (1/swayAmt) *-0.1)) * (float)Math.pow(Math.cos(t * 10f + x), 3) + swayAmt;
		swayAmt = (float)(swayAmt * Math.cos(windDir)) + offset;
		
		swayAmt *= 1.2f;
		
		if(!debugLines) {
<<<<<<< HEAD
<<<<<<< HEAD
			float height = texture.getHeight();
			drawtex = texture.getScaledCopy(texture.getWidth(), texture.getHeight() - vertOffset);
			drawtex = drawtex.getSubImage(0, 0, drawtex.getWidth(), drawtex.getHeight() - 1);
			GraphicsManager.submitToQueue(new RenderCandidate(drawtex.getScaledCopy(scaleFactor), x - swayAmt + xOffset, y + vertOffset*scaleFactor + yOffset, swayAmt, 0, y));
		//	drawtex.getScaledCopy(scaleFactor).drawSheared(x - swayAmt + xOffset, y + vertOffset*scaleFactor + yOffset, swayAmt, 0);
		} else {
			float nx = x;
			float ny = y;
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			int vertOffset = (int)Math.abs(offset * 0.2f * (dist<20 ? 20-dist : 1));
			float height = texture.getHeight();
			drawtex = texture.getScaledCopy(texture.getWidth(), texture.getHeight() - vertOffset);
			drawtex = drawtex.getSubImage(0, 0, drawtex.getWidth(), drawtex.getHeight() - 1);
			drawtex.getScaledCopy(scaleFactor).drawSheared(x - swayAmt, y + vertOffset*scaleFactor, swayAmt, 0);
		} else {
			float nx = x + texture.getWidth()/4;
			float ny = y + texture.getHeight()/4;
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			
			new Graphics().drawLine(nx, ny, nx - swayAmt, ny - 20);
		}
	}
}
