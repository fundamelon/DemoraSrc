package main.entity;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Math.*;

import main.AIManager;
import main.AudioManager;
import main.ControlManager;
import main.GameBase;
import main.GraphicsManager;
import main.Physics;
import main.ai.Path;
import main.ai.Pathfinder_AStar;
import main.item.Item;
import main.map.EnvObject;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import org.lwjgl.util.vector.*;

import util.Timer;
import util.TimerSet;
import util.Util;


public abstract class Entity_mobile implements Entity{
	

	protected Image TEX_FRONT;
	protected Image TEX_BACK;
	protected Image TEX_LEFT;
	protected Image TEX_RIGHT;
	
	protected Image TEX_JUMP_FRONT;
	protected Image TEX_JUMP_BACK;
	protected Image TEX_JUMP_LEFT;
	protected Image TEX_JUMP_RIGHT;
	
	protected Image[] TEX_DIE;
	
	protected Image[] TEX_RUN_FRONT;
	protected Image[] TEX_RUN_SIDE;
	protected Image[] TEX_RUN_BACK;
	
	protected Image[][] TEX_ATTACK_FRONT = new Image[16][16];
	protected Image[][] TEX_ATTACK_BACK = new Image[16][16];
	protected Image[][] TEX_ATTACK_SIDE = new Image[16][16];
	
	protected Image[] TEX_FOOTSTEP;

	protected Animation ANIM_IDLE_FRONT;
	protected Animation ANIM_IDLE_LEFT;
	protected Animation ANIM_IDLE_RIGHT;
	protected Animation ANIM_IDLE_BACK;
	
	protected Animation ANIM_RUN_FRONT;
	protected Animation ANIM_RUN_LEFT;
	protected Animation ANIM_RUN_RIGHT;
	protected Animation ANIM_RUN_BACK;

	protected Animation[] ANIM_ATTACK_FRONT = new Animation[5];
	protected Animation[] ANIM_ATTACK_SIDE = new Animation[5];
	protected Animation[] ANIM_ATTACK_BACK = new Animation[5];
	
	protected Animation ANIM_DIE;
	
	
	protected Image cur_img = null;
	protected Image last_img = null;
	protected Animation cur_anim = null;
	protected Animation override_anim = null;
	
	protected boolean flipCurImg = false;
	
	protected int blockSize, dustCount = 0;
	
	

	protected boolean isDead = false;
	protected boolean justDied = false;
	
	protected boolean initialized = false;
	
	protected boolean isMoving = false;
	protected boolean isColliding = false;
	protected boolean followingPath = false;
	protected boolean sprinting = false;
	
	public boolean attackRequested = false;
	public boolean canAttack = false;
	public boolean attacking = false;
	public boolean animateAttack = false;
	public boolean attacked = false;
	
	public boolean walkingThroughGrass = false;
	
	protected String moveAxis = "x";
	protected String action = "idle", direction = "front";
	
	protected Rectangle bounds = new Rectangle(0, 0, 32, 32);
	protected Shape attackArea = new Polygon(new float[]{0, 0, -32, 64, 32, 64});
	
	public Vector3f pos = new Vector3f(0, 0, 0);
	public Vector3f vel = new Vector3f(0, 0, 0);
	public Vector2f dir = new Vector2f(0, 0);
	public Vector2f facing = new Vector2f(0, 0);
	public Vector2f imgPos = new Vector2f(0, 0);
	public Vector2f tilepos = new Vector2f(0, 0);
	public Vector2f attackDir;

	public float velMult;
	public float ang;
	public float jumpForce = 0.3f;
	public float stamina = 100f;
	public Timer staminaGrowDelay;
	public float weight = 100f;
	
	public ArrayList<Entity> collidedWith = new ArrayList<Entity>();
	
	public float health = 100f;
	public float maxHealth = 100f;
	
	public float[] attackDamage;
	public Timer attackTimer;
	public int attackDelay = 700;
	
	public float sprintCost = 0.03f;
	
	public float img_offset_x=0, img_offset_y=0;
	public Vector2f shadow_offset = new Vector2f(0, 0);
	public String type = "mobile", name = null;
	protected Animation[] anims;
	protected Path currentPath;
	protected Pathfinder_AStar pathfinder;
	
	public void setAnimation() {}
	
	
	public int[] itemTable = new int[128];
	public HashMap<Vector2f, Shape> nearbyTiles;
	
	@Override
	public void init() {
		updateBounds();
		pathfinder = new Pathfinder_AStar(AIManager.getNodeMap());
		
		attackTimer = new Timer(name + " attack", attackDelay, false);
	//	TimerSet.addTimer(attackTimer);
		attackTimer.finish();
		
		staminaGrowDelay = new Timer(name + "_stamina_replenish", 1000, false);
	//	TimerSet.addTimer(staminaGrowDelay);
		staminaGrowDelay.finish();
		
		initialized = true;
	};
	
	@Override
	public void draw() {};
	
	@Override
	public void debugDraw(Graphics g) {
	//	g.setFont(new TrueTypeFont(new java.awt.Font("Helvetica", 10, 10), true));
		g.setColor(org.newdawn.slick.Color.black);
		GraphicsManager.drawString("pos:  "+pos.x + ", "+pos.y + ", "+pos.z, pos.x + bounds.getWidth(), pos.y - 30);
		GraphicsManager.drawString("anim: "+(cur_anim == null ? "null" : cur_anim.toString()), pos.x + bounds.getWidth(), pos.y - 20);
		GraphicsManager.drawString("img:  "+(cur_img == null ? "null" : cur_img.getResourceReference()), pos.x + bounds.getWidth(), pos.y - 10);
	//	GraphicsManager.drawString("moving:    "+isMoving, pos.x + bounds.getWidth(), pos.y - 30);
	//	GraphicsManager.drawString("colliding: "+isColliding, pos.x + bounds.getWidth(), pos.y - 30);
	//	GraphicsManager.drawString("following: "+followingPath, pos.x + bounds.getWidth(), pos.y - 20);
	}
	
	public void move(Vector2f newDir) {
		if(!isJumping()) {
			dir = newDir;
		} else {
			dir.x += newDir.x * 0.003f * ControlManager.getDelta();
			dir.y += newDir.y * 0.003f * ControlManager.getDelta();
		}
		
		if(dir.length() != 0)
			dir.normalise();
		
		vel.x += dir.x * velMult;
		vel.y += dir.y * velMult;
	}
	
	public void setPath(Path in) {
		currentPath = in;
	}
	
	public void setFollowPath(boolean a) {
		followingPath = a;
	}
	
	public void followPath(Path in) {
		if(in.nextStep() == null) {
			System.out.println("Path completed");
			followingPath = false;
			return;
		}
		followingPath = true;
		Vector2f target = new Vector2f((in.nextStep().getX()*GameBase.getMap().getData().getTileWidth() + 16 - pos.x), (in.nextStep().getY()*GameBase.getMap().getData().getTileWidth() + 16 - pos.y));
		move(target);
		target = new Vector2f((in.nextStep().getX()*GameBase.getMap().getData().getTileWidth() + 16 - pos.x), (in.nextStep().getY()*GameBase.getMap().getData().getTileWidth() + 16 - pos.y));
		float radius = 8;
		if(target.length() <= radius) {
			in.nextStep().setVisited(true);
		}
	}
	
	public void followPath() {
		followPath(currentPath);
	}
	

	@Override
	public void update() {
		if(attackTimer.completed()) {
			canAttack = true;
			if(attackRequested) {
				attacking = true;
				attackDelay = (int)(4 * (10 + (100 - stamina)));
				attackTimer.setLength(attackDelay);
				attackTimer.restart();
			}
		} else {
			canAttack = false;
			attacking = false;
		}
		attackRequested = false;
		
		
		if(attacking) {
			animateAttack = true;
		//	System.out.println("Setting action to attack");
			setAction("attack");
		} else if(!animateAttack || cur_anim == null || (cur_anim.isStopped() || !cur_anim.getCurrentFrame().getResourceReference().contains("attack"))) {
			animateAttack = false;
		//	System.out.println("No longer animating attack");
		}
		
		if(cur_anim != null) {
		//	System.out.println(cur_anim.isStopped());
			
			if(isSprinting() && !isDead()) {
				cur_anim.update((long)(ControlManager.getDelta() * 1.5f));
			} else {
				cur_anim.update((long)(ControlManager.getDelta()));
			}
			if(cur_img != last_img) {
				for(int i = 0; i < TEX_FOOTSTEP.length; i++)
					if(TEX_FOOTSTEP[i].getResourceReference().equals(cur_anim.getCurrentFrame().getResourceReference())) {
						AudioManager.playSound("footstep", 1f, 0.2f);
						
						if(isSprinting())
							AudioManager.playSound("footstep_dirt01_02", 1f, 0.03f);
						
						if(walkingThroughGrass)
							AudioManager.playSound("grassy_footstep", 1f, 0.35f);
					}
				
				last_img = cur_img;
			}
		}
		
		if(isJumping()) {
			vel.z -= Physics.gravity * ControlManager.getDelta();
			if(pos.z + (vel.z) <= 0 && vel.z <= 0) {
				pos.z = 0;
				vel.z = 0;
				if(cur_anim != null && cur_anim.isStopped()) {
					cur_anim.start();
					cur_anim.setCurrentFrame((cur_anim.getFrame()+1) % cur_anim.getFrameCount());
				}
				
				if(type.equals("player")) {
					AudioManager.playSound("footstep_land", 1f, 0.2f);
				}
			} else {
				setAction("jump");
			}
		}
		
		walkingThroughGrass = false;
		
		if(health <= 0 && !isDead) {
			health = 0;
			isDead = true;
			justDied = true;
			if(ANIM_DIE == null) {
				this.vel.x = (float) Math.random();
				this.vel.y = (float) Math.random();
				jump(jumpForce/2);
			} else {
				this.vel.scale(0f);
			}
		}
		
		if(isDead) {
			vel.x = 0;
			vel.y = 0;
		}

		pos.z += vel.z * ControlManager.getDelta();
		
		
		//skip if not moving
	//	if(attacking) {
	//		setAction("attack");
	//	} else
		if(!animateAttack && vel.x == 0 && vel.y == 0 && !isJumping()) {
			if(isMoving) {
				isMoving = false; 
				setAction("idle");
			}
		//	return;
		} else if(!isJumping()) {
			isMoving = true;
		//	System.out.println("Moving player, vel.x: "+vel.x+", vel.y:"+vel.y);
		}
		//stamina loss
		//time dependent
		float cost = 0;
		if(isSprinting() && isMoving()) {
			cost += sprintCost;
		}
		cost *= ControlManager.getDelta();
		
		//time independent
		if(attacking) {
			cost += 20;
		}
		
		//deplete or replenish stamina
		if(cost > 0) {
			if(!staminaGrowDelay.isRunning()) staminaGrowDelay.restart();
			if(stamina - cost > 0) {
				stamina -= cost;
			} else {
				stamina = 0;
			}
		} else if(staminaGrowDelay.completed()) {
			float gain = 0.03f * ControlManager.getDelta();
			if(stamina + gain < 100) {
				stamina += gain;
			} else {
				stamina = 100;
			}
		}

		//post-stamina-change
		if(stamina == 0) {
			stopSprint();
		}
		
		updateBounds();
		updateNearbyTiles();
		
	//	System.out.println("Velocity x="+vel.x+" y="+vel.y+" z="+vel.z);
		
		Vector2f newvel = new Vector2f(vel.x, vel.y);
		Shape attempted_bounds = new Rectangle(getBounds().getX(), getBounds().getY(), getBounds().getWidth(), getBounds().getHeight());
		isColliding = false;

		collidedWith.clear();
		
		if(Physics.collisions) {
			
			ArrayList<Shape>collisionArray = GameBase.getMap().getObstacleArray(this.bounds);
			
			//Collisions with map objects
			for(int i = 0; i < collisionArray.size(); i++) {
				if(	collisionArray.get(i) == null )
					continue;
				manageCollision(i, collisionArray, attempted_bounds, new Vector2f(vel.x, vel.y), newvel);
			}
			
			//Collisions between entities
			collisionArray.clear();
			
			for(int i = 0; i < EntityManager.entityTable.size(); i++) {
				if(
						//Conditions to ignore collision
						EntityManager.entityTable.get(i).hasCollisions() &&
						!EntityManager.entityTable.get(i).isDead() &&
						i != EntityManager.entityTable.indexOf(this) && 
						i != EntityManager.entityTable.indexOf(EntityManager.cursorEntity)
					)
					
					collisionArray.add(EntityManager.entityTable.get(i).getBounds());
			}
			for(int i = 0; i < collisionArray.size(); i++) {
				if(	collisionArray.get(i) == null )
					continue;
			    if(manageCollision(i, collisionArray, attempted_bounds, new Vector2f(vel.x, vel.y), newvel))
			    	collidedWith.add(EntityManager.entityTable.get(i));
			}
			collisionArray.clear();
		}
		
		if(EntityManager.getPlayer().isDead()) {
			newvel.scale(0);
		}
		
		pos.x += newvel.x * ControlManager.getDelta();
		pos.y += newvel.y * ControlManager.getDelta();
		
		pos.x = Util.clamp(pos.x, 18, GameBase.getMap().getWidth() * GameBase.getMap().getData().getTileWidth() + blockSize + 18);
		pos.y = Util.clamp(pos.y, 18, GameBase.getMap().getHeight() * GameBase.getMap().getData().getTileWidth() + blockSize - 18);
		
		updateBounds();
	}
	
	public boolean manageCollision(int i, ArrayList<Shape> collisionArray, Shape attempted_bounds, Vector2f curvel, Vector2f newvel) {
		Vector2f impulse;
		
		GraphicsManager.drawString(""+i , collisionArray.get(i).getCenterX()-4, collisionArray.get(i).getCenterY()-4);

		Shape temp_obs = collisionArray.get(i);
		
		attempted_bounds.setX(getBounds().getX() + newvel.x * ControlManager.getDelta());
		attempted_bounds.setY(getBounds().getY() + newvel.y * ControlManager.getDelta());
		
		//unused
		int cushion = 0;
		if(GameBase.getMap().collisionType(i) == 2) {
			cushion = 1;
		}
		
		impulse = Physics.splitAxisCollision(temp_obs, bounds, newvel);	
	//	if(type.equals("player")) System.out.println(impulse);
		if(impulse.length() > 0) {
			isColliding = true;
			newvel.x += impulse.x / ControlManager.getDelta();
			newvel.y += impulse.y / ControlManager.getDelta();
			return true;
		}
		return false;
	}

	
	public void updateDirection() {
		// direction controller
		if(isJumping()) {
			setAction("jump");
			return;
		}
		if(isDead()) {
			setAction("die");
			return;
		}
		
		String newDirection = "";
		float velDiff = abs(vel.x) - abs(vel.y);
		if(velDiff > 0.001) {
			moveAxis = "x";
		} else if(velDiff < 0) {
			moveAxis = "y";
		}
		
		if(vel.length() != 0) {
			facing.scale(0f);
			if(moveAxis.equals("x")) {
				if(vel.x < 0) {
					newDirection = "left";
					facing.x = -1;
				} else {
					newDirection = "right";
					facing.x = 1;
				}	
			} else if(moveAxis.equals("y")) {
				if(vel.y < 0) {
					newDirection = "back";
					facing.y = -1;
				} else {
					newDirection = "front";
					facing.y = 1;
				}
			}
			direction = newDirection;
			setAction("move");
		} else {
			setAction("idle");
		}
	}
	
	@Override
	public void finalize() {
	//	vel.x *= 0.9;
	//	vel.y *= 0.9;
	}
	
	/** Updates the entity's animation or image based on input action and existing direction. */
	protected void setAction(String newAction) {
		if(GameBase.paused) 
			return; 
		if(animateAttack && !newAction.equals("attack") && cur_anim != null  && !cur_anim.isStopped())
			return;
		
		if(newAction.equals("move")){
			if(direction.equals("left")) {
				if(cur_anim == null || !cur_anim.equals(ANIM_RUN_LEFT)) {
					cur_anim = ANIM_RUN_LEFT;
					cur_anim.setPingPong(false);
					cur_anim.start();
				}
				
				flipCurImg = false;
			} else if(direction.equals("right")) {
				if(cur_anim == null || !cur_anim.equals(ANIM_RUN_RIGHT)) {
					cur_anim = ANIM_RUN_RIGHT;
					cur_anim.setPingPong(false);
					cur_anim.start();
				}
				
				flipCurImg = true;
			} else if(direction.equals("back")) {
				if(cur_anim == null || !cur_anim.equals(ANIM_RUN_BACK)) {
					cur_anim = ANIM_RUN_BACK;
					cur_anim.setPingPong(false);
					cur_anim.start();
				}
				
				flipCurImg = false;
			} else if(direction.equals("front")) {
				if(cur_anim == null || !cur_anim.equals(ANIM_RUN_FRONT)) {
					cur_anim = ANIM_RUN_FRONT;
					cur_anim.setPingPong(true);
					cur_anim.start();
				}
				
				flipCurImg = false;
			}
		} 
		
		else if(newAction.equals("attack")) {
			if(type.equals("player")) {
				if(direction.equals("right")) {
					if(cur_anim == null || !cur_anim.equals(ANIM_ATTACK_SIDE[0])) {
						cur_anim = ANIM_ATTACK_SIDE[0].copy();
						cur_anim.setPingPong(false);
						cur_anim.setLooping(false);
						cur_anim.stopAt(2);
						cur_anim.start();
						flipCurImg = false;
					}
				}else if(direction.equals("left")) {
					if(cur_anim == null || !cur_anim.equals(ANIM_ATTACK_SIDE[0])) {
						cur_anim = ANIM_ATTACK_SIDE[0].copy();
						cur_anim.setPingPong(false);
						cur_anim.setLooping(false);
						cur_anim.stopAt(2);
						cur_anim.start();
						flipCurImg = true;
					}
				}
			}
		}
		
		else if(newAction.equals("jump")){
			if(cur_anim != null && !cur_anim.isStopped()) cur_anim.stop();
		} 
		
		else if(newAction.equals("die")) {
			if(ANIM_DIE != null) {
				cur_anim = ANIM_DIE;
				cur_anim.setPingPong(false);
				cur_anim.setLooping(false);
				cur_anim.start();
			}
		} 
		
		else if(newAction.equals("idle")) {
			if(direction.equals("left")) {
				if(cur_anim == null || !cur_anim.equals(ANIM_IDLE_LEFT)) {
					if(ANIM_IDLE_LEFT == null) {
						cur_anim = null;
						cur_img = TEX_LEFT;
					} else {
						cur_anim = ANIM_IDLE_LEFT;
						cur_anim.setPingPong(false);
						cur_anim.start();
					}
				}
				flipCurImg = false;
			} else if(direction.equals("right")) {
				if(cur_anim == null || !cur_anim.equals(ANIM_IDLE_RIGHT)) {
					if(ANIM_IDLE_RIGHT == null) {
						cur_anim = null;
						cur_img = TEX_LEFT;
					} else {
						cur_anim = ANIM_IDLE_RIGHT;
						cur_anim.setPingPong(false);
						cur_anim.start();
					}
				}
				flipCurImg = false;
			} else if(direction.equals("back")) {
				if(cur_anim == null || !cur_anim.equals(ANIM_IDLE_BACK)) {
					if(ANIM_IDLE_BACK == null) {
						cur_anim = null;
						cur_img = TEX_LEFT;
					} else {
						cur_anim = ANIM_IDLE_BACK;
						cur_anim.setPingPong(false);
						cur_anim.start();
					}
				}
				flipCurImg = false;
			} else if(direction.equals("front")) {
				if(cur_anim == null || !cur_anim.equals(ANIM_IDLE_FRONT)) {
					if(ANIM_IDLE_FRONT == null) {
						cur_anim = null;
						cur_img = TEX_LEFT;
					} else {
						cur_anim = ANIM_IDLE_FRONT;
						cur_anim.setPingPong(false);
						cur_anim.start();
					}
				}
				flipCurImg = false;
			}
		} else
			System.out.println("Invalid action: \""+newAction+"\"");
		
		if(GameBase.debug_animation)
			if(cur_anim == null)
				System.out.println(cur_img.toString());
			else
				System.out.println(cur_anim.toString());
	}

	public void resizeBounds(float newWidth, float newHeight) {
		bounds.setWidth(newWidth);
		bounds.setHeight(newHeight);
	}

	public void updateBounds() {
		bounds.setLocation(pos.x - getBounds().getWidth()/2, pos.y - getBounds().getHeight()/2);
		tilepos.x = GameBase.getMap().getTileAtX(pos.x);
		tilepos.y = GameBase.getMap().getTileAtY(pos.y);
		attackDir = dir.length() > 0 ? dir : facing;

		attackArea = new Polygon(new float[]{0, 0, -24, 48, 0, 54, 24, 48}).transform(
				Transform.createRotateTransform(
						-(float)Math.atan2(attackDir.x, attackDir.y)
				));
		
		attackArea.setCenterX(pos.x + 32 * attackDir.x);
		attackArea.setCenterY(pos.y + 32 * attackDir.y);
	}
	
	public void updateNearbyTiles() {
		nearbyTiles = GameBase.getMap().getNearbyTiles(bounds, 1);
	}
	
	public HashMap<Vector2f, Shape> getNearbyTiles() {
		return nearbyTiles;
	}
	
	@Override
	public void damage(float dmg) {
		this.health -= dmg;
	}
	
	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	
	public Vector3f pos() {
		return pos;
	}
	
	public Vector2f pos2d() {
		return new Vector2f(pos.x, pos.y);
	}

	@Override
	public float getX() {return pos.x;}
	@Override
	public float getY() {return pos.y;}
	public float getZ() {return pos.z;}
	
	public Vector getImgPos() {
		return imgPos;
	}
	
	public Vector vel() {
		return vel;
	}
	
	public float getDX() {
		return vel.x;
	}
	
	public float getDY() {
		return vel.y;
	}
	
	@Override
	public Image getImg() {
		return cur_img;
	}
	
	@Override
	public float getImgOffsetX() {
		return img_offset_x;
	}
	
	@Override
	public float getImgOffsetY() {
		return img_offset_y;
	}
	
	@Override
	public void setImg(String newPath) {
		try {
			cur_img = new Image(newPath);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Path getCurrentPath() {
		return currentPath;
	}
	
	public Shape getAttackArea() {
		return attackArea;
	}

	public void setVelZ(float newVel) {
		vel.z = newVel;
	}
	
	@Override
	public boolean isJumping() {
		return (pos.z == 0 && vel.z == 0) ? false : true;
	}
	
	public boolean isFollowingPath() {
		return followingPath;
	}
	
	public boolean isAttacking() {
		return  attacking;
	}
	
	public void attack() {
		if(canAttack) {
			attackRequested = true;
		} else {
		//	System.out.println(attackTimer.timeElapsed()+"ms: Attack timer not completed");
		}
	}
	
	@Override
	public void damage(float damage, Entity attacker) {
		vel.x += (((Entity_mobile)attacker).attackDir.x)/weight * 35f;
		vel.y += (((Entity_mobile)attacker).attackDir.y)/weight * 35f;
		health -= damage;
	}
	
	public float[] getAttackDamage() {
		return attackDamage;
	}
	
	public int getAttackType() {
		//TODO
		return 0;
	}
	
	public void startSprint() {
		if(stamina > 0 && !isJumping()) {
			sprinting = true;
		} else {
			sprinting = false;
		}
	}
	public void stopSprint() {
		sprinting = false;
	}
	public boolean isSprinting() {
		return sprinting;
	}
	
	public float getStaminaFraction() {
		return stamina / 100;
	}
	
	@Override
	public boolean hasCollisions() {
		return true;
	}
	
	@Override
	public boolean isMoving() {
		return isMoving;
	}
	
	public void jump() {
		jump(jumpForce);
	}
	
	public void jump(float force) {
		if(!isJumping()) {
			this.vel.z += force;
		}
	}
	
	public void kill() {
		health = 0;
	}
	
	@Override
	public boolean isDead() {
		return isDead;
	}
	
	@Override
	public float getHealth() {
		return health;
	}
	
	@Override
	public float getTotalHealth() {
		return maxHealth;
	}
	
	@Override
	public void drawFgEffects() {}
	@Override
	public void drawBgEffects() {}
	
	@Override
	public boolean initialized() {
		return initialized;
	}

}
