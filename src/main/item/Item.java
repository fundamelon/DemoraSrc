package main.item;
import java.util.ArrayList;

import org.lwjgl.util.vector.*;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import main.AudioManager;
import main.ControlManager;
import main.GraphicsManager;
import main.Physics;
import main.entity.Entity;
import main.entity.EntityManager;

public abstract class Item implements Entity{
	
	//unique items get an ID
	//otherwise ID is zero for generics
	public final int ID;
	public int variant = 0;
	public Rectangle bounds;
	public Animation cur_anim;
	public String name, type = "generic";
	public String pickupSound;
	public float itemHeight = 0;
	
	public Vector3f pos, vel;
	
	public boolean stored = false;
	public boolean storable = false;
	
	public static ArrayList<Item> itemTable = new ArrayList<Item>();
	
	
	public Item(int nID) {
		ID = nID;
	}
	
	public Item(Vector3f pos) {
		this(itemTable.size());
		this.pos = pos;
	}
	
	public Item(Vector2f pos) {
		this(itemTable.size());
		this.pos = new Vector3f(pos.x, pos.y, this.pos.z);
	}

	public void init() {
		this.bounds = new Rectangle(this.pos.x, this.pos.y, 16, 16);
	}
	public void init(float nx, float ny, boolean tilewise) {}
	
	public void store() {
		this.stored = true;
		if(pickupSound != null) {
			AudioManager.playSound(pickupSound, 1f, 0.4f);
		}
		this.bounds = new Rectangle(0, 0, 0, 0);
		
	}
	
	public void update() {
		if(!stored) {
			if(cur_anim.isStopped()) {
				cur_anim.start();
			}
			cur_anim.update((long)ControlManager.getDelta());
			bounds.setLocation(this.pos.x, this.pos.y);

			if(storable) {
				if(collidingWithPlayer()) {
					store();
					if(type.equals("coin")) {
						EntityManager.getPlayer().changeMoney(5);
						itemTable.remove(this);
						return;
					}
				}
			}
		}

		if(Math.abs(vel.z) < 0.05f && pos.z <= itemHeight) vel.z = 0;
		
		if(pos.z <= itemHeight && vel.z <= 0) {
			pos.z = itemHeight;
			vel.z = vel.z/-2;
		} else {
			vel.z -= Physics.gravity * ControlManager.getDelta();
		}
		
		if(pos.z == itemHeight) {
			vel.scale(1 - (0.01f * ControlManager.getDelta()));
		}
		
		pos.x += vel.x * ControlManager.getDelta();
		pos.y += vel.y * ControlManager.getDelta();
		pos.z += vel.z * ControlManager.getDelta();
	}
	
	public void draw() {
		if(stored) return;
		if(type.equals("coin") && variant==Coin.BAG) {
			GraphicsManager.submitToQueue(this.cur_anim.getCurrentFrame(), pos.x-10, pos.y-9-pos.z, pos.y);
		} else {
			GraphicsManager.submitToQueue(this.cur_anim.getCurrentFrame(), pos.x, pos.y-0-pos.z, pos.y);
		}
	}
	
	public void debugDraw(Graphics g) {
		
	}
	
	public int getID() {return ID;}
	
	public abstract void destroy();

	public boolean collidingWithPlayer() {
		return EntityManager.getPlayer().getBounds().intersects(this.bounds);
	}

	public String getName() {
		return name;
	}

	
	public String getType() {
		
		return type;
	}

	public float getX() {
		return pos.x;
	}

	
	public float getY() {
		return pos.y;
	}

	
	public float getAng() {
		return 0;
	}

	
	public Image getImg() {
		return cur_anim.getCurrentFrame();
	}
	
	public Shape getBounds() {
		return bounds;
	}

	
	public Image getShadowCasterImg() {	return null; }

	
	public void setImg(String path) {}

	
	public float getImgOffsetX() {
		return 0;
	}

	
	public float getImgOffsetY() {
		return 0;
	}

	
	public float getHealth() {
		return 0;
	}
	
	public void kill() {
		itemTable.remove(this);
	}

	
	public float getTotalHealth() {
		return 0;
	}

	
}
