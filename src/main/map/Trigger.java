package main.map;

import org.newdawn.slick.geom.Rectangle;

import main.Event;
import main.entity.Entity;
import main.entity.EntityManager;


public class Trigger {
	private Rectangle bounds;
	public void init(int x, int y) {
		bounds = new Rectangle(x * 32, y * 32, 32, 32);
	}
	
	public void update() {
		for(Entity e : EntityManager.entityTable) {
			if(e.getBounds().intersects(this.getBounds())) {
				
			}
		}
	}
	
	public void activate(int id) {
		Event.fire(id);
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
}
