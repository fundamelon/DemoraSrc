package main.entity;

import java.util.ArrayList;

import main.AudioManager;
import main.Camera;
import main.ControlManager;
import main.GameBase;
import main.GraphicsManager;
import main.graphics.ImageLoader;
import main.particles.*;
import main.particles.ParticleEmitter;
import main.particles.ParticleSystem;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.particles.*;

import util.*;

@SuppressWarnings("all")
public class Entity_umbrin_test extends Entity_mobile implements Entity {
	
	public ParticleSystem particles_smoke, ps_hit;
	public float angleToTarget;
	public float targetDistance;
	Entity_player target;
	private Timer attackCharge;
	private float charge = 1f;
	private boolean following = false;
	private Vector2f dir = new Vector2f(), newDir = new Vector2f();
	
	ArrayList<Emitter_UmbrinSmoke_3> followSmoke = new ArrayList<Emitter_UmbrinSmoke_3>();
	
	public Entity_umbrin_test() throws SlickException {
		TEX_FRONT = ImageLoader.getByPath("lib/img/char/umbrin_0/umbrin_front_static.png");
		TEX_BACK = ImageLoader.getByPath("lib/img/char/umbrin_0/umbrin_back_static.png");
		TEX_LEFT = ImageLoader.getByPath("lib/img/char/umbrin_0/umbrin_left_static.png");
		TEX_RIGHT = ImageLoader.getByPath("lib/img/char/umbrin_0/umbrin_right_static.png");
		
		cur_img = TEX_FRONT;
		
		
		bounds = new Rectangle(0, 0, 32, 32);

		
		particles_smoke = new ParticleSystem(ImageLoader.getByPath("lib/img/particle/flamelrg_02.tga"));
		particles_smoke.setPosition(0, 0);
		particles_smoke.setBlendingMode(ParticleSystem.BLEND_COMBINE);
		
		
		ps_hit = new ParticleSystem(ImageLoader.getByPath("lib/img/particle/flamelrg_02.tga"));
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
		
		AudioManager.playSound("eerie_explode02", 1f, 0.6f);
		
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
		
		System.out.println("ENT: umbrin test initialized");
	}
	
	public void init() {
		init(10 + (int)(Math.random()*400), 10 + (int)(Math.random()*400), false);
	}
	
	public void update() {
		target = EntityManager.getPlayer();
		targetDistance = Util.getDistance(this, target);
		pos.z = 10 + (float)Math.sin(GameBase.gameTime() *0.002f)*10f;
		img_offset_y = -50 - pos.z;
		
		if(attackCharge == null && charge == 1f) {
			//normal movement
			newDir.x = (target.getX() - pos.x);
			newDir.y = (target.getY() - pos.y);
			newDir.normalise();

			dir.x += newDir.x * 0.01f * ControlManager.getDelta();
			dir.y += newDir.y * 0.01f * ControlManager.getDelta();
			dir.normalise();
		}
		
		if(!following) dir.scale(0);
		
		vel.x = dir.x;
		vel.y = dir.y;
		
		
		vel.scale(ControlManager.getDelta() * 0.007f);
	//	System.out.println(vel);
	//	System.out.println(charge);
		
		if(charge != 1f) {
			vel.scale(charge);
		}
		
		if(attackCharge != null) {
			//charging
			vel.scale(0f);
		//	float scale = (attackCharge.totalTime() - attackCharge.timeElapsed()) / attackCharge.totalTime();
		//	vel.x = (float)(Math.random() * ControlManager.getDelta()) * 0.001f * scale;
		//	vel.y = (float)(Math.random() * ControlManager.getDelta()) * 0.001f * scale;
		}

		for(ParticleEmitter p : followSmoke) {
			p.setPos(
					this.getBounds().getCenterX() - this.facing.x*5, 
					this.getBounds().getCenterY(), 
					pos.z);
		}
		
		if(!isDead) {
			if(targetDistance < 250) following = true;
			
			if(targetDistance > 600) following = false;
			
			if(targetDistance < 175) {
				Camera.setShake(true);
				if(attackCharge == null && charge == 1f) {
					AudioManager.playSound("eerie_buildup_short", 1f, 0.3f);
					attackCharge = new util.Timer("umbrin_attack", 700);
					attackCharge.start();
				}
			} else Camera.setShake(false);
			
			if(targetDistance < 50) {
				//damage
				if(charge != 1f && !target.isDead()) {
					AudioManager.stopSound("eerie_buildup_short");
					AudioManager.stopSound("eerie_flyby");
					AudioManager.playSound("eerie_hit_huge", 1f, 0.9f);
					target.damage(10, this);
					charge = 2f;
					vel.x += (this.pos.x - target.pos.x) * 0.01f;
					vel.y += (this.pos.y - target.pos.y) * 0.01f;
		
					target.vel.x += (target.pos.x - this.pos.x) * 0.1f;
					target.vel.y += (target.pos.y - this.pos.y) * 0.1f;
				}
			}
			
			if(attackCharge != null && attackCharge.completed()) {
				//rush forward
				AudioManager.playSound("eerie_flyby", 1f, 0.3f);
				attackCharge = null;
				charge = 6f;
				dir.x = (target.getX() - pos.x);
				dir.y = (target.getY() - pos.y);
				dir.normalise();
			}
			float chargeDecrease = ControlManager.getDelta() * 0.008f;
			if(charge - chargeDecrease > 1f) {
				charge -= chargeDecrease;
			} else {
				charge = 1f;
			}

			
			if(targetDistance < 250) {
				GraphicsManager.overlayAlphas[2] = ((250 - targetDistance) / 250) * (float)Math.pow((float)Math.sin(GameBase.runningTime() * 0.004f)*0.15f + 0.6f, 2);
			} else GraphicsManager.overlayAlphas[2] = 0;
			if(targetDistance < 300) {
				GraphicsManager.overlayAlphas[3] = ((300 - targetDistance) / 300) * (float)Math.pow((float)Math.sin(GameBase.runningTime() * 0.006f)*0.15f + 0.8f, 2);
			} else GraphicsManager.overlayAlphas[3] = 0;
		} else {
			Camera.setShake(false);
			if(GraphicsManager.overlayAlphas[2] > 0f) 
				GraphicsManager.overlayAlphas[2] -= ControlManager.getDelta() * 0.0001f; 
			else 
				GraphicsManager.overlayAlphas[2] = 0f;
			
			if(GraphicsManager.overlayAlphas[3] > 0f) 
				GraphicsManager.overlayAlphas[3] -= ControlManager.getDelta() * 0.0001f; 
			else 
				GraphicsManager.overlayAlphas[3] = 0f;
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
				
				AudioManager.playSound("eerie_bust_thunder", 1f, 1f);
			//	AudioManager.playSound("hit_electric_hard_01", 1f, 0.7f);
			}
			bounds = new Rectangle(0, 0, 0, 0);
			
		} else if(vel.x > 0 && vel.x > Math.abs(vel.y)) {
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
		
	//	move(pos.x, pos.y);
	}
	
	public void damage(float damage, Entity attacker) {
		attackCharge = null;
		AudioManager.stopSound("eerie_flyby");
		AudioManager.stopSound("eerie_buildup_short");
		AudioManager.playSound("eerie_hit01", 1f, 0.9f);
		ps_hit.addEmitter(new Emitter_UmbrinHit(this.pos.x, this.pos.y, this.pos.z));
		((main.particles.ParticleEmitter)ps_hit.getEmitter(ps_hit.getEmitterCount()-1)).setPos(
				this.getBounds().getCenterX(), this.getBounds().getCenterY());
		super.damage(damage, attacker);
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
	}

	public void drawFgEffects() {
		particles_smoke.render();
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
