package main.particles;
import org.newdawn.slick.Image;

public class Emitter_UmbrinSmoke_2 extends ParticleEmitter {
	
	/** The particle emission rate */
	private int interval = 30;
	/** Time til the next particle */
	private int timer;
	/** The size of the initial particles */
	private float size = 15f;
	
	
	/** Timer controlling particle variation */
	private int variation_timer = 0;
	/**
	 * Create a default fire effect at 0,0
	 */
	public Emitter_UmbrinSmoke_2() {
	}

	/**
	 * Create a default fire effect at x,y
	 * 
	 * @param x The x coordinate of the fire effect
	 * @param y The y coordinate of the fire effect
	 */
	public Emitter_UmbrinSmoke_2(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Create a default fire effect at x,y
	 * 
	 * @param x The x coordinate of the fire effect
	 * @param y The y coordinate of the fire effect
	 * @param size The size of the particle being pumped out
	 */
	public Emitter_UmbrinSmoke_2(int x, int y, int z, float size) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.size = size;
	}
	
	public Emitter_UmbrinSmoke_2(float x, float y, float z) {
		this((int)x, (int)y, (int)z);
	}
	
	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#update(org.newdawn.slick.particles.ParticleSystem, int)
	 */
	@Override
	public void update(ParticleSystem system, int delta) {
		timer -= delta;
		variation_timer += delta;
		if (timer <= 0 && isEnabled() && variation_timer <= 100) {
			for(int i = 0; i < 30; i++) {
				timer = interval;
				Particle p = system.getNewParticle(this, 3000);
				p.setColor(0f, 0f, 0f, 0.3f + (float)Math.abs(Math.cos(variation_timer * 0.0002 + Math.sin(variation_timer*0.001))) * 0.1f);
				p.setPosition(x + 15f * (float)(Math.random() - 0.5f), y + (float)(Math.random() * 10f - 5f), z + (float)(Math.random() * 70f + 5f));
				p.setSize(size * (float)(Math.random()*0.9f + 0.5f));
				float ang = (float) Math.random() * 360;
				
				vel_x = (float)Math.cos(ang) * 0.1f * ((float)Math.random()) * 3f;
				vel_y = (float)Math.cos(ang) * 0.1f * ((float)Math.random()) * 0.1f;
				vel_z = Math.abs((float)Math.sin(ang) * 0.1f * (float)Math.random() * 4f);
				
				p.setVelocity(vel_x, vel_y, vel_z, 1.1f);
			}
		}
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#updateParticle(org.newdawn.slick.particles.Particle, int)
	 */
	@Override
	public void updateParticle(Particle particle, int delta) {
		if (particle.getLife() > 600) {
			particle.adjustSize((float)(-0.04f*Math.random()) * delta);
		//	particle.adjustColor(0, 0, 0, delta*-0.0001f);
			
		} else {
		//	particle.adjustSize(0.01f * delta * (size / 40.0f));
		//	particle.adjustColor(0,  0, 0, delta*-0.01f);
		}
		float c = 0.002f * delta;
		float vx = (float)Math.sin(variation_timer * 0.0003 + Math.sin(variation_timer*0.001))*0.000001f;
		float vy = 0;
		float vz = 0;
		if(particle.getZ()>= 0) {
			vz -= c * 0.1f;
		} else {
			particle.setPosition(particle.getX(), particle.getY(), 0);
		}
	//	System.out.println(Math.pow(particle.getLife(), 2));
	//	particle.setSpeed(0.6f * (float)(Math.pow(particle.getLife(), 3) * 0.000000000007f));
		particle.adjustVelocity(vx, vy, vz);
		
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#setEnabled(boolean)
	 */
	@Override
	public void setEnabled(boolean enabled) {
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#completed()
	 */
	@Override
	public boolean completed() {
		return false;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#useAdditive()
	 */
	@Override
	public boolean useAdditive() {
		return false;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#getImage()
	 */
	@Override
	public Image getImage() {
		return null;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#usePoints(org.newdawn.slick.particles.ParticleSystem)
	 */
	@Override
	public boolean usePoints(ParticleSystem system) {
		return false;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#isOriented()
	 */
	@Override
	public boolean isOriented() {
		return false;
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#wrapUp()
	 */
	@Override
	public void wrapUp() {
	}

	/**
	 * @see org.newdawn.slick.particles.ParticleEmitter#resetState()
	 */
	@Override
	public void resetState() {
	}

	@Override
	public float getBrightness() {
		return 0.4f + (float)((Math.sin(variation_timer * 0.003f) + Math.cos(variation_timer * 0.01f)) * 0.01f);
	}
}

