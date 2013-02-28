package main.entity;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.particles.ParticleSystem;


import main.*;
import main.ai.Node;
import main.ai.Path;
import main.ai.Pathfinder_AStar;
<<<<<<< HEAD
<<<<<<< HEAD
import main.graphics.ImageLoader;
import main.particles.*;
=======
import main.particles.Emitter_Dust_TrailSmall;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
import main.particles.Emitter_Dust_TrailSmall;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9

@SuppressWarnings("all")
public class Entity_player extends Entity_mobile implements Entity {

<<<<<<< HEAD
<<<<<<< HEAD
	public static ParticleSystem ps_sprintdust;
	
	public int money = 0;
	
	public Entity_player() throws SlickException {
		
		ps_sprintdust = new ParticleSystem(ImageLoader.getByPath("lib/img/particle/smoke_01.tga"));
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public static org.newdawn.slick.particles.ParticleSystem ps_sprintdust;
	
	public Entity_player() throws SlickException {
		
		ps_sprintdust = new ParticleSystem(new Image("lib/img/particle/smoke_01.tga"));
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		ps_sprintdust.setPosition(0, 0);
		ps_sprintdust.setBlendingMode(ParticleSystem.BLEND_COMBINE);
		ps_sprintdust.addEmitter(new Emitter_Dust_TrailSmall(this.pos.x, this.pos.y));
		ps_sprintdust.getEmitter(0).setEnabled(false);
	
		//Load up all textures and animations!
<<<<<<< HEAD
<<<<<<< HEAD
		
		TEX_FRONT = ImageLoader.getByPath("lib/img/char/plr_front_static.png");
		TEX_BACK  = ImageLoader.getByPath("lib/img/char/plr_back_static.png");
		TEX_LEFT  = ImageLoader.getByPath("lib/img/char/plr_left_static.png");
		TEX_RIGHT = ImageLoader.getByPath("lib/img/char/plr_right_static.png");
		
		TEX_JUMP_FRONT = ImageLoader.getByPath("lib/img/char/plr_front_run02.png");
		TEX_JUMP_BACK  = ImageLoader.getByPath("lib/img/char/plr_back_run03.png");
		TEX_JUMP_LEFT  = ImageLoader.getByPath("lib/img/char/plr_side_run02.png");
		TEX_JUMP_RIGHT = ImageLoader.getByPath("lib/img/char/plr_side_run04.png");
		
		TEX_DIE = new Image[] {
				ImageLoader.getByPath("lib/img/char/girl_front_fall01.png"),
				ImageLoader.getByPath("lib/img/char/girl_front_fall02.png"),
				ImageLoader.getByPath("lib/img/char/girl_front_fall03.png"),
				ImageLoader.getByPath("lib/img/char/girl_front_fall04.png"),
				ImageLoader.getByPath("lib/img/char/girl_front_fall05.png"),
				ImageLoader.getByPath("lib/img/char/girl_front_fall06.png"),
				ImageLoader.getByPath("lib/img/char/girl_front_fall07.png"),
				ImageLoader.getByPath("lib/img/char/girl_front_fall08.png")
		};
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		
		TEX_FRONT = new Image("lib/img/char/plr_front_static.png");
		TEX_BACK  = new Image("lib/img/char/plr_back_static.png");
		TEX_LEFT  = new Image("lib/img/char/plr_left_static.png");
		TEX_RIGHT = new Image("lib/img/char/plr_right_static.png");
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		
		TEX_JUMP_FRONT = new Image("lib/img/char/plr_front_run02.png");
		TEX_JUMP_BACK  = new Image("lib/img/char/plr_back_run03.png");
		TEX_JUMP_LEFT  = new Image("lib/img/char/plr_side_run02.png");
		TEX_JUMP_RIGHT = new Image("lib/img/char/plr_side_run04.png");
		
		TEX_JUMP_FRONT = new Image("lib/img/char/plr_front_run02.png");
		TEX_JUMP_BACK  = new Image("lib/img/char/plr_back_run03.png");
		TEX_JUMP_LEFT  = new Image("lib/img/char/plr_side_run02.png");
		TEX_JUMP_RIGHT = new Image("lib/img/char/plr_side_run04.png");
		
		TEX_RUN_FRONT = new Image[] {
			ImageLoader.getByPath("lib/img/char/plr_front_run02.png"),
			ImageLoader.getByPath("lib/img/char/plr_front_run01.png"),
			ImageLoader.getByPath("lib/img/char/plr_front_run03.png")
		};
		
		TEX_RUN_SIDE = new Image[] {
			ImageLoader.getByPath("lib/img/char/plr_side_run01.png"),
			ImageLoader.getByPath("lib/img/char/plr_side_run02.png"),
			ImageLoader.getByPath("lib/img/char/plr_side_run03.png"),
			ImageLoader.getByPath("lib/img/char/plr_side_run04.png")
		};
		
		TEX_RUN_BACK = new Image[] {
			ImageLoader.getByPath("lib/img/char/plr_back_run01.png"),
			ImageLoader.getByPath("lib/img/char/plr_back_run02.png"),
			ImageLoader.getByPath("lib/img/char/plr_back_run03.png"),
			ImageLoader.getByPath("lib/img/char/plr_back_run04.png")
		};
		
		TEX_FOOTSTEP = new Image[] {
			ImageLoader.getByPath("lib/img/char/plr_back_run02.png"),
			ImageLoader.getByPath("lib/img/char/plr_back_run04.png"),
			ImageLoader.getByPath("lib/img/char/plr_front_run01.png"),
			ImageLoader.getByPath("lib/img/char/plr_side_run01.png"),
			ImageLoader.getByPath("lib/img/char/plr_side_run03.png")
		};
		
		TEX_ATTACK_SIDE[0] = new Image[] {
			ImageLoader.getByPath("lib/img/char/plr_attack01_side01.png"),
			ImageLoader.getByPath("lib/img/char/plr_attack01_side02.png"),
			ImageLoader.getByPath("lib/img/char/plr_attack01_side03.png"),
		};
		
		ANIM_IDLE_FRONT = new Animation(new Image[] {TEX_FRONT}, 1, false);
		ANIM_IDLE_BACK 	= new Animation(new Image[] {TEX_BACK}, 1, false);
		ANIM_IDLE_LEFT 	= new Animation(new Image[] {TEX_LEFT}, 1, false);
		ANIM_IDLE_RIGHT = new Animation(new Image[] {TEX_RIGHT}, 1, false);
		
		ANIM_RUN_FRONT = new Animation(TEX_RUN_FRONT, new int[] {200, 120, 200}, false);
		ANIM_RUN_LEFT = new Animation(TEX_RUN_SIDE, new int[] {120, 200, 120, 200}, false);
		ANIM_RUN_RIGHT = new Animation(TEX_RUN_SIDE, new int[] {120, 200, 120, 200}, false);
		ANIM_RUN_BACK = new Animation(TEX_RUN_BACK, new int[] {200, 120, 200, 120}, false);
		
		ANIM_ATTACK_SIDE[0] = new Animation(TEX_ATTACK_SIDE[0], new int[] {50, 120, 80}, false);
		
		ANIM_DIE = new Animation(TEX_DIE, new int[] {100, 70, 70, 300, 120, 70, 70, 350}, false);
		
		cur_img = TEX_FRONT;
		
		action = "idle";
		direction = "front";
		type = "player";
		
		velMult = 0.15f;
		jumpForce = 0.3f;
		
<<<<<<< HEAD
		attackDamage = new float[]{25, 10, 100};
		
<<<<<<< HEAD
		name = "player";
		bounds = new Rectangle(0, 0, 30, 14);
=======
		velMult = 0.15f;
		
		bounds = new Rectangle(0, 0, 30, 30);
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		velMult = 0.15f;
		
		bounds = new Rectangle(0, 0, 30, 30);
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	
	}
	
	/**
	 * Re-load the player at a position
	 * @param nx - start pos x
	 * @param ny - start pos y
	 * @param tilewise - True means tiles, false means pixels.
	 */
<<<<<<< HEAD
<<<<<<< HEAD
	public void init(float nx, float ny, boolean tilewise) {
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public void init(int nx, int ny, boolean tilewise) {
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		
		pos.x = nx * (tilewise ? 1 : 32);
		pos.y = ny * (tilewise ? 1 : 32);
		pos.x = 64;
		pos.y = 64;
		super.init();
		
		System.out.println("ENT: player initialized");
	}

	public void move(Vector2f newDir) {
		if(!isJumping() && !isSprinting()) {
			dir.x += newDir.x * 0.05f * ControlManager.getDelta();
			dir.y += newDir.y * 0.05f * ControlManager.getDelta();
			if(newDir.x == 0) dir.x = 0;
			if(newDir.y == 0) dir.y = 0;
		} else if(isSprinting()) {
			dir.x += newDir.x * 0.005f * ControlManager.getDelta();
			dir.y += newDir.y * 0.005f * ControlManager.getDelta();
		} else {
			dir.x += newDir.x * 0.003f * ControlManager.getDelta();
			dir.y += newDir.y * 0.003f * ControlManager.getDelta();
		}
		
		if(dir.length() != 0)
			dir.normalise();
		
		if(isSprinting()) {
			vel.x += dir.x * velMult * 1.8f;
			vel.y += dir.y * velMult * 1.8f;
		} else {
			vel.x += dir.x * velMult;
			vel.y += dir.y * velMult;
		}
	}
	
	public void init() {
		init(1, 1, true);
	}
	
	public void update() {
		ps_sprintdust.getEmitter(0).setEnabled(isMoving() && isSprinting() && !isJumping());
		
		if(ps_sprintdust.getEmitterCount() != 0) {
			((Emitter_Dust_TrailSmall)ps_sprintdust.getEmitter(0)).setPos(this.getBounds().getCenterX(), this.getBounds().getCenterY() + 10);
			ps_sprintdust.update((int)ControlManager.getDelta());
		}
		super.update();
		super.updateDirection();
		finalize();
	}
	
	public void finalize() {
		if(!isJumping()) {
			vel.x *= 0;
			vel.y *= 0;
<<<<<<< HEAD
<<<<<<< HEAD
		} else {
			vel.x *= 0.0002 * ControlManager.getDelta();
			vel.y *= 0.0002 * ControlManager.getDelta();
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		}
		super.finalize();
	}

	
	
	public void setPath(Node target) {
<<<<<<< HEAD
<<<<<<< HEAD
		pathfinder.pathfind(AIManager.getNodeMap().getNodeAt(GameBase.getMap().getTilePosAt(pos.x, pos.y)), target);
=======
		pathfinder.pathfind(AIManager.getNodeMap().getNodeAt(GameBase.getMap().getTileAt(pos.x, pos.y)), target);
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		pathfinder.pathfind(AIManager.getNodeMap().getNodeAt(GameBase.getMap().getTileAt(pos.x, pos.y)), target);
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		currentPath = pathfinder.createPath();
	}
	
	public Image getImg() {
		img_offset_x = 0;
<<<<<<< HEAD
		img_offset_y = -40;
		shadow_offset.scale(0);
=======
		img_offset_y = 0;
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		if(isJumping()) {
			img_offset_y -= pos.z;
		}
		if(cur_anim != null) {
			cur_img = cur_anim.getCurrentFrame();
			
			//Set of images that will offset player upward
			
			if(facing.x != 0) {
				shadow_offset.y += 12;
				shadow_offset.x += 3 * facing.x;
			}
			if(facing.y != 0) {
				shadow_offset.y += 10;
			}
		//	System.out.println(cur_img.getResourceReference());
			
			if( cur_img.getResourceReference().endsWith("plr_front_run01.png")) {
				img_offset_y -= 3;
			}
			if(	cur_img.getResourceReference().endsWith("plr_back_run02.png") ||
				cur_img.getResourceReference().endsWith("plr_back_run04.png")) {
				img_offset_y += 3;
			}
			if(	cur_img.getResourceReference().endsWith("plr_back_run01.png") ||
				cur_img.getResourceReference().endsWith("plr_front_run02.png")) {
				img_offset_x += 2;
			}
			if(	cur_img.getResourceReference().endsWith("plr_back_run03.png") ||
				cur_img.getResourceReference().endsWith("plr_front_run04.png")) {
				img_offset_x -= 2;
			}
			if(	cur_img.getResourceReference().endsWith("plr_side_run01.png") || 
				cur_img.getResourceReference().endsWith("plr_side_run03.png")) 
			{
				if(flipCurImg)
					img_offset_x -= 2;
				else
					img_offset_x += 2;
				
				img_offset_y -= 2;
			}
		}
		if(flipCurImg) {
			return cur_img.getFlippedCopy(true, false);
		} else {
			return cur_img.copy();
		}
	}
	
	public Image getShadowCasterImg() {
		if(cur_img.equals(TEX_FRONT))
			return TEX_BACK;
		
		else if(cur_img.equals(TEX_BACK))
			return TEX_FRONT;
		
		else return cur_img;
	}

	public Rectangle getBounds() {
		return bounds;
	}
	
	public void jump() {
<<<<<<< HEAD
<<<<<<< HEAD
		if(!isJumping() && !isDead()) {
			//TODO: speed up at jump start, perhaps only with a skill
			this.vel.x *= 1.01f;
			this.vel.y *= 1.01f;
			this.vel.z += jumpForce;
			if(Math.random() < 0.3) {
				AudioManager.playSound("female_exert_grunt", 1f, 0.3f);
			}
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		if(!isJumping()) {
			this.vel.x *= 1.5f;
			this.vel.y *= 1.5f;
			this.vel.z += jumpForce;
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		}
	}

	@Override
	public float getAng() {
		// TODO Auto-generated method stub
		return 0;
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
	public int getMoney() {return money;}
	public void changeMoney(int amt) {money += amt;}
	public void setMoney(int amt) {money = amt;}
	
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	public void drawFgEffects() {
		ps_sprintdust.render();
	}
	
	public void drawBgEffects() {
		
	}
	

	@Override
	public boolean castShadows() {
		return true;
	}
}
