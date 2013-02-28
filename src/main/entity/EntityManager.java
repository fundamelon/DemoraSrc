package main.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import main.AudioManager;
import main.entity.*;
import main.item.Item;

@SuppressWarnings("all")
public class EntityManager {
	public static ArrayList<Entity> entityTable = new ArrayList<Entity>();
	public static ArrayList<Entity> tile_ents = new ArrayList<Entity>();
<<<<<<< HEAD
<<<<<<< HEAD
	public static ArrayList<Entity> details = new ArrayList<Entity>();
	public static Entity_cursor cursorEntity = new Entity_cursor();
	
	public static boolean playerSpawned = false;
	
	public static void init() {
		entityTable.clear();
		tile_ents.clear();
		//	spawn(new Entity_umbrin_test());
		spawn(cursorEntity);
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public static Entity_cursor cursorEntity = new Entity_cursor();
	
	public static void init() {
		try {
			addToTable(new Entity_player());
		//	addToTable(new Entity_umbrin_test());
		//	addToTable(cursorEntity);
		} catch (SlickException e) {
			e.printStackTrace();
		}
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		
	//	for(Entity e : entityTable) {
	//		e.init();
	//	}
	}
	
	public static void spawn(Entity ent) {
		try {
			addToTable(ent);
			if(!ent.initialized())
				ent.init();
		} catch(Exception e) {
			System.out.println("Failed to initialize "+ent.getClass().getCanonicalName()+" "+ent.getName());
		}
	}
	
	public static int getIndex(Entity ent) {
		return entityTable.indexOf(ent);
	}
	
	public static Entity getByIndex(int i) {
		return entityTable.get(i);
	}
	
	public static Entity getByName(String name) {
		for(int i = 0; i < entityTable.size(); i++) {
			if(entityTable.get(i).getName().equals(name))
				return entityTable.get(i);
		}
		return null;
	}
	
	public static int getTableLength() {
		return entityTable.size();
	}
	
	public static ArrayList<Entity> getByType(String entType) {
		ArrayList<Entity> outTable = new ArrayList<Entity>();
		for(int i = 0; i < entityTable.size(); i++) {
			if(entityTable.get(i).getType().equals(entType))
				outTable.add(entityTable.get(i));				
		}
		return outTable;
	}
	
	public static boolean addToTable(Entity ent) {
		if(entityTable.indexOf(ent) == -1) {
			entityTable.add(ent);
			return true;
		} else return false;
	}
	
	public static void addToTable(Vector<Entity> oldTable) {
		for(int i = 0; i < oldTable.size(); i++) {
			if(entityTable.indexOf(oldTable.get(i)) != -1)
				entityTable.add(oldTable.get(i));
		}
	}
	
	public static Entity_player getPlayer() {
		for(Entity e : entityTable) {
			if(e.getType().equals("player"))
				return (Entity_player)e;
		}
		return null;
	}
	
	
	public static void draw(String setname) {
		for(int i = 0; i < entityTable.size(); i++) {
			entityTable.get(i).draw();
		}
	}
	
	public static void update() {
		for(int i = 0; i < Item.itemTable.size(); i++) {
			if(!entityTable.contains(Item.itemTable.get(i))) 
				entityTable.add(Item.itemTable.get(i));
		}
		
		for(int i = 0; i < entityTable.size(); i++) {
<<<<<<< HEAD
			Entity e = entityTable.get(i);
			e.update();
			if(e instanceof Entity_mobile) {
				Entity_mobile e_mob = (Entity_mobile)e;
				if(e_mob.isAttacking()) {
					damageAnyWithinArea(e_mob, e_mob.attackArea, e_mob.getAttackDamage()[e_mob.getAttackType()]);
					e_mob.attacking = false;
					e_mob.attacked = true;
				} else {
					e_mob.attacked = false;
				}
			}
=======
			entityTable.get(i).update();
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			
			if(main.GameBase.debug_entities) {
				entityTable.get(i).debugDraw(main.GameBase.g);
			}
		}
		
		
		//sort by y position, for render order
		Collections.sort(entityTable, new PositionComparator());
	}
	
	public static void damageAnyWithinArea(Entity attacker, Shape area, float damage) {
		boolean hit = false;
		for(Entity e : entityTable) {
			if(e instanceof Item && ((Item)e).storable) continue;
			if(e.getBounds().intersects(area) && !e.equals(attacker)) {
				e.damage(damage, attacker);
				hit = true;
				System.out.println(attacker.getName() + " attacked " + e.getName() + " for "+damage+" damage.");
			}
		}
		if(hit) {
			AudioManager.playSound("hit_flesh_medium", 1f, 0.4f);
		} else {
			AudioManager.playSound("whoosh_medium", 1f, 0.4f);
			System.out.println("Missed");
		}
	}
	
}

class PositionComparator implements Comparator<Entity> {
	@Override
	public int compare(Entity e_a, Entity e_b) {
		float a = e_a.getY(), b = e_b.getY();
		return (a>b ? 1 : (a==b ? 0 : -1));
	}
}
