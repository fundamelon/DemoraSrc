package main;

import org.newdawn.slick.geom.*;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import org.lwjgl.util.vector.*;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
import org.lwjgl.util.vector.*;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
import org.lwjgl.util.vector.Vector2f;

import static java.lang.Math.*;

public class Physics {
	public static float gravity = 0.0010f;
	
	public static boolean collisions = true;
	
	public static boolean collidedFromLeft(Shape a, Shape b) {
		System.out.println("Left");
		return a.getX() < b.getX() && a.intersects(b);
	}

	public static boolean collidedFromRight(Shape a, Shape b) {
		System.out.println("Right");
		return a.getX() > b.getX() && a.intersects(b);
	}

	public static boolean collidedFromTop(Shape a, Shape b) {
		System.out.println("Top");
		return a.getY() > b.getY() && a.intersects(b);
	}

	public static boolean collidedFromBottom(Shape a, Shape b) {
		System.out.println("Bottom");
		return a.getY() < b.getY() && a.intersects(b);
	}
	
	/**
	 * Detect collision between two rectangular objects.
	 * @param a
	 * @param b
	 * @return Projection vector for shape b
	 */
	public static Vector2f splitAxisCollision(Shape a, Shape b) {
		Vector2f projection = new Vector2f(0, 0);
		Vector2f apos = new Vector2f(a.getCenterX(), a.getCenterY());
		Vector2f bpos = new Vector2f(b.getCenterX(), b.getCenterY());
		Vector2f diff = new Vector2f(bpos.x - apos.x, bpos.y - apos.y);
		Vector2f aw = new Vector2f(a.getWidth()/2, a.getHeight()/2);
		Vector2f bw = new Vector2f(b.getWidth()/2, b.getHeight()/2);
		
		Vector2f overlap = new Vector2f(0, 0);
		
		
		//Handle corners of collision
		if(true) {
			if(diff.x < 0 && diff.y < 0) {
				//Top left
			//	System.out.println("Top left");
				aw.x *= -1;
				aw.y *= -1;
			} else if(apos.x < bpos.x && apos.y >= bpos.y) {
				//Top right
			//	System.out.println("Top right");
				bw.x *= -1;
				aw.y *= -1;
			} else if(diff.x < 0 && diff.y >= 0) {
				//Bottom left
			//	System.out.println("Bottom left");
				bw.y *= -1;
				aw.x *= -1;
			} else if(diff.x >= 0 && diff.y >= 0) {
				//Bottom right 
			//	System.out.println("Bottom right");
				bw.y *= -1;
				bw.x *= -1;
			}
			overlap = new Vector2f((bpos.x + bw.x) - (apos.x + aw.x), (bpos.y + bw.y) - (apos.y + aw.y));

			if(abs(diff.x) > abs(aw.x) + abs(bw.x)) overlap.x = 0;
			if(abs(diff.y) > abs(aw.y) + abs(bw.y)) overlap.y = 0;

			if(overlap.x == 0 || overlap.y == 0) {
				overlap.scale(0f);
			}
			
			if(abs(overlap.x) < abs(overlap.y)){
				overlap.y = 0;
			} else {
				overlap.x = 0;
			}
		}
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		

		

<<<<<<< HEAD
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	//	System.out.println("Overlap x= "+overlap.x+" y= "+overlap.y);
		
		projection = overlap;
		projection.scale(-1f);
<<<<<<< HEAD
<<<<<<< HEAD
		
		return projection;
	}
	
	/**
	 * Detect collision between two rectangular objects.
	 * @param a - object a (obstacle)
	 * @param b - object b (before moving)
	 * @param vel - object b's velocity
	 * @return Corrected velocity vector for object b
	 */
	public static Vector2f splitAxisCollision(Shape a, Shape b, Vector2f bvel) {
		Vector2f projection = new Vector2f(0, 0);
		Vector2f apos = new Vector2f(a.getCenterX(), a.getCenterY());
		Vector2f bpos = new Vector2f(b.getCenterX() + bvel.x * ControlManager.getDelta(), b.getCenterY() + bvel.y * ControlManager.getDelta());
		Vector2f aw = new Vector2f(a.getWidth()/2, a.getHeight()/2);
		Vector2f bw = new Vector2f(b.getWidth()/2, b.getHeight()/2);
		
		/*
		//Return to manageable position
		//Left
		if(b.getX() < bpos.x && bpos.x > apos.x) {
			bpos.x = apos.x - bw.x * 2;
		}
		//Right
		if(b.getX() > bpos.x && bpos.x+bw.x*2 < apos.x) {
			bpos.x = apos.x + aw.x * 2;
		}
		//Top
		if(b.getY() < bpos.y && bpos.y > apos.y) {
			bpos.y = apos.y - bw.y * 2;
		}
		//Bottom
		if(b.getY() > bpos.y && bpos.y+bw.y*2 < apos.y) {
			bpos.y = apos.y + aw.y * 2;
		}
		*/
		Vector2f diff = new Vector2f(bpos.x - apos.x, bpos.y - apos.y);
		
		Vector2f overlap = new Vector2f(0, 0);
		
		//TODO: Fix high speed behavior
		
		//Handle corners of collision
		if(true) {
			if(diff.x < 0 && diff.y < 0) {
				//Top left
			//	System.out.println("Top left");
				aw.x *= -1;
				aw.y *= -1;
			} else if(apos.x < bpos.x && apos.y >= bpos.y) {
				//Top right
			//	System.out.println("Top right");
				bw.x *= -1;
				aw.y *= -1;
			} else if(diff.x < 0 && diff.y >= 0) {
				//Bottom left
			//	System.out.println("Bottom left");
				bw.y *= -1;
				aw.x *= -1;
			} else if(diff.x >= 0 && diff.y >= 0) {
				//Bottom right 
			//	System.out.println("Bottom right");
				bw.y *= -1;
				bw.x *= -1;
			}
			overlap = new Vector2f((bpos.x + bw.x) - (apos.x + aw.x), (bpos.y + bw.y) - (apos.y + aw.y));

			if(abs(diff.x) > abs(aw.x) + abs(bw.x)) overlap.x = 0;
			if(abs(diff.y) > abs(aw.y) + abs(bw.y)) overlap.y = 0;

			if(overlap.x == 0 || overlap.y == 0) {
				overlap.scale(0f);
			}
			
			if(abs(overlap.x) < abs(overlap.y)){
				overlap.y = 0;
			} else {
				overlap.x = 0;
			}
		}
	//	System.out.println("Overlap x= "+overlap.x+" y= "+overlap.y);
	//	projection = new Vector2f(b.getX() - bpos.x, b.getY() - bpos.y);
	 
		projection = overlap;
		projection.scale(-1f);
	//	projection.x *= (bvel.x == 0 ? 0 : 1);
	//	projection.y *= (bvel.y == 0 ? 0 : 1);
		
		projection.x -= bvel.x;
		projection.y -= bvel.y;
		
	//	projection.x;
	//	projection.y;
		
		projection.x = ((int)(projection.x*1000000)) / 1000000;
		projection.y = ((int)(projection.y*1000000)) / 1000000;

	//	System.out.println("Projection x= "+projection.x+" y= "+projection.y);
=======
		
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		return projection;
	}
}
