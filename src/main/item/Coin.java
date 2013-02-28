package main.item;

import main.GraphicsManager;
import main.ai.Path;
import main.entity.Entity;
import main.graphics.ImageLoader;

import org.lwjgl.util.vector.*;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Coin extends Item {
	
	public static final int GOLD = 0;
	public static final int SILVER = 1;
	public static final int COPPER = 2;
	public static final int BAG = 3;

	public Coin(Vector2f pos, int variant) {
		super(new Vector3f(pos.x, pos.y, 7));
		bounds = new Rectangle(0,0,16,16);
		type = "coin";
		this.variant = variant;
		storable = true;
		pickupSound = "coin";
		
		
		setSpriteSheet();
	}
	
	public Coin(Vector2f pos, Vector3f vel, int coinVariant) {
		super(new Vector3f(pos.x, pos.y, 8));
		itemHeight = 5;
		this.vel = vel;
		bounds = new Rectangle(0,0,16,16);
		type = "coin";
		this.variant = coinVariant;
		storable = true;
		pickupSound = "coin";
		
		
		setSpriteSheet();
	}

	public Coin(Vector3f pos, Vector3f vel, int coinVariant) {
		this(new Vector2f(pos.x, pos.y), vel, coinVariant);
		this.pos.z = pos.z;
	}

	public void setSpriteSheet() {
		String path="";
		switch(variant) {
		case Coin.GOLD:
			path="gold";
			break;
			
		case Coin.SILVER: 
			path="silver";
			break;
			
		case Coin.COPPER:
			path="copper";
			break;
			
		case Coin.BAG: 
			path="bag";
			pickupSound = "coinsack";
			break;
		}
	//	System.out.println("Created a "+path+" coin");
		path = "lib/img/item/coin/" + path + ".png";
		try {
			cur_anim = new Animation(ImageLoader.getSheet(path), 60 + (variant==Coin.BAG?120:0));
			cur_anim.setCurrentFrame((int)Math.floor(Math.random() * cur_anim.getFrameCount()));
			cur_anim.start();
			cur_anim.setLooping(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	public void drawFgEffects() {}
	public void drawBgEffects() {}

	

	

	
	public boolean hasCollisions() {
		return false;
	}

	
	public boolean isMoving() {
		
		return false;
	}

	
	public void damage(float damage) {
		
		
	}

	
	public void damage(float damage, Entity attacker) {
		
		
	}

	
	public boolean castShadows() {
		
		return false;
	}

	
	public Path getCurrentPath() {
		
		return null;
	}

	
	public boolean isJumping() {
		
		return false;
	}

	
	public boolean isDead() {
		
		return false;
	}

	
	public boolean initialized() {
		
		return false;
	}

	
	public void destroy() {
		
		
	}

}
