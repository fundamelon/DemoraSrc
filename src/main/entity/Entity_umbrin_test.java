package main.entity;

import java.util.ArrayList;

import main.AudioManager;
import main.ControlManager;
import main.GameBase;
import main.particles.*;
import main.particles.ParticleEmitter;
import main.particles.ParticleSystem;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.particles.*;

import util.Util;

@SuppressWarnings("all")
public class Entity_umbrin_test extends Entity_mobile implements Entity {
	
	public ParticleSystem particles_smoke, ps_hit;
	ArrayList<Emitter_UmbrinSmoke_3> followSmoke = new ArrayList<Emitter_UmbrinSmoke_3>();
	
	public Entity_umbrin_test() throws SlickException {
		TEX_FRONT = new Image("lib/img/char/umbrin_0/umbrin_front_static.png");
		TEX_BACK = new Image("lib/img/char/umbrin_0/umbrin_back_static.png");
		TEX_LEFT = new Image("lib/img/char/umbrin_0/umbrin_left_static.png");
		TEX_RIGHT = new Image("lib/img/char/umbrin_0/umbrin_right_static.png");
		
		cur_img = TEX_FRONT;
		
		bounds = new Rectangle(0, 0, 32, 32);
<<<<<<< HEAD
<<<<<<< HEAD

		
		particles_smoke = new ParticleSystem(new Image("lib/img/particle/flamelrg_02.tga"));
		particles_smoke.setPosition(0, 0);
		particles_smoke.setBlendingMode(ParticleSystem.BLEND_COMBINE);
		
		
		ps_hit = new ParticleSystem(new Image("lib/img/particle/flamelrg_02.tga"));
		ps_hit.setPosition(0, 0);
		ps_hit.setBlendingMode(ParticleSystem.BLEND_COMBINE);
	//	particles_smoke.getEmitter(0).setEnabled(false);
		
		pos.z = 20;
		
		name = "umbrin_test";
		health = 100f;
	}
	
	public Entity_umbrin_test(float nx, float ny, boolean tilewise) throws SlickException {
		this();
		init(nx, ny, tilewise);
	}
	
	public void init(float nx, float ny, boolean tilewise) {
		pos.x = nx;
		pos.y = ny;
		
		velMult = 0.5f;
		
		super.init();
		
		AudioManager.playSound("eerie_shriek", 1f, 0.6f);
		
		for(int i = 0; i < 5; i++) {
			particles_smoke.addEmitter(new Emitter_UmbrinSmoke_1(this.pos.x, this.pos.y, this.pos.z));
			((Emitter_UmbrinSmoke_1)particles_smoke.getEmitter(particles_smoke.getEmitterCount()-1)).setPos(
					this.getBounds().getCenterX(), this.getBounds().getCenterY(), pos.z);
		}
		
		for(int i = 0; i < 8; i++) {
			followSmoke.add(new Emitter_UmbrinSmoke_3(this.pos.x, this.pos.y, this.pos.z));
			particles_smoke.addEmitter(followSmoke.get(i));
			followSmoke.get(i).setPos(this.getBounds().getCenterX(), this.getBounds().getCenterY(), pos.z);
			followSmoke.get(i).setEnabled(true);
		} 
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}
	
	public void init(int nx, int ny, boolean tilewise) {
		pos.x = nx;
		pos.y = ny;
		updateBounds();
		
		velMult = 0.5f;
		
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		
		System.out.println("ENT: umbrin test initialized");
	}
	
	public void init() {
		init(10 + (int)(Math.random()*400), 10 + (int)(Math.random()*400), false);
	}
	
	public void update() {
<<<<<<< HEAD
<<<<<<< HEAD
		pos.z = 10 + (float)Math.sin(GameBase.gameTime() *0.002f)*10f;
		img_offset_y = -50 - pos.z;
		
		vel.x += (EntityManager.getPlayer().getX() - pos.x) * ControlManager.getDelta() * 0.00001f;
		vel.y += (EntityManager.getPlayer().getY() - pos.y) * ControlManager.getDelta() * 0.00001f;
		

		for(ParticleEmitter p : followSmoke) {
			p.setPos(
					this.getBounds().getCenterX() - this.facing.x*5, 
					this.getBounds().getCenterY(), 
					pos.z);
		}
		
		particles_smoke.update((int)ControlManager.getDelta());
	/*	for(int i = 0; i < particles_smoke.getEmitterCount(); i++) {
			if(particles_smoke.getEmitter(i) instanceof Emitter_UmbrinSmoke_1) {
				particles_smoke.getEmitter(i).setPos(pos.x,  pos.y);
			}
		} */
		
		super.update();
		vel.scale(0.9f);
		
		if(isDead) {
			if(justDied) {
				justDied = false;
				cur_img = TEX_FRONT.copy();
				cur_img.setAlpha(0);
				particles_smoke.setPosition(0, 0);
				
				for(int i = 0; i < 5; i++) {
					particles_smoke.addEmitter(new Emitter_UmbrinSmoke_1(this.pos.x, this.pos.y, this.pos.z));
					((main.particles.Emitter_UmbrinSmoke_1)particles_smoke.getEmitter(particles_smoke.getEmitterCount()-1)).setPos(
							this.getBounds().getCenterX(), this.getBounds().getCenterY(), pos.z);
				}
				for(int i = 0; i < 2; i++) {
					particles_smoke.addEmitter(new Emitter_UmbrinSmoke_2(this.pos.x, this.pos.y, this.pos.z));
					((main.particles.Emitter_UmbrinSmoke_2)particles_smoke.getEmitter(particles_smoke.getEmitterCount()-1)).setPos(
							this.getBounds().getCenterX(), this.getBounds().getCenterY(), pos.z);
				}

				for(ParticleEmitter p : followSmoke)
					p.setEnabled(false);
				
				AudioManager.playSound("eerie_burst_voices", 1f, 0.2f);
				AudioManager.playSound("hit_electric_hard_01", 1f, 0.7f);
			}
			bounds = new Rectangle(0, 0, 0, 0);
			
		} else if(vel.x > 0 && vel.x > Math.abs(vel.y)) {
=======
		super.pos.z = (float)Math.sin((System.currentTimeMillis()) *0.002)*8 - 25;
		
		vel.x = (EntityManager.getPlayer().getX() - pos.x) * 0.01f;
		vel.y = (EntityManager.getPlayer().getY() - pos.y) * 0.01f;
		
		super.update();
		
		if(vel.x > 0 && vel.x > Math.abs(vel.y)) {
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		super.pos.z = (float)Math.sin((System.currentTimeMillis()) *0.002)*8 - 25;
		
		vel.x = (EntityManager.getPlayer().getX() - pos.x) * 0.01f;
		vel.y = (EntityManager.getPlayer().getY() - pos.y) * 0.01f;
		
		super.update();
		
		if(vel.x > 0 && vel.x > Math.abs(vel.y)) {
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
			cur_img = TEX_RIGHT.copy();
			facing.x = 1;
			facing.y = 0;
		}
		else if(vel.x < 0 && -vel.x > Math.abs(vel.y)) {
			cur_img = TEX_LEFT.copy();
			facing.x = -1;
			facing.y = 0;
		}
		else if(vel.y < 0 && -vel.y > Math.abs(vel.x)) {
			cur_img = TEX_BACK.copy();
			facing.x = 0;
			facing.y = -1;
		} else {
			cur_img = TEX_FRONT.copy();
			facing.x = 0;
			facing.y = 1;
		}
		super.finalize();
<<<<<<< HEAD
<<<<<<< HEAD
=======
		super.updateBounds();
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		super.updateBounds();
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		
	//	move(pos.x, pos.y);
	}
	
<<<<<<< HEAD
	public void damage(float damage, Entity attacker) {
		ps_hit.addEmitter(new Emitter_UmbrinHit(this.pos.x, this.pos.y, this.pos.z));
		((main.particles.ParticleEmitter)ps_hit.getEmitter(ps_hit.getEmitterCount()-1)).setPos(
				this.getBounds().getCenterX(), this.getBounds().getCenterY());
		super.damage(damage, attacker);
=======
	public void updateBounds() {
		bounds.setX(pos.x - getBounds().getWidth()/2);
		bounds.setY(pos.y + getBounds().getHeight()/2);
<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}
	
	public Rectangle getBounds() {
		return bounds;
	}

	@Override
	public Image getShadowCasterImg() {
		return cur_img;
	}

	public Image getImg() {
		return cur_img;
	}
	
	public float getX() {
		return pos.x;
	}

	public float getY() {
		return pos.y;
<<<<<<< HEAD
<<<<<<< HEAD
	}

	public void drawFgEffects() {
		particles_smoke.render();
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}

	@Override
	public float getAng() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean castShadows() {
		return true;
	}

}
