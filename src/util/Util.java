package util;

import org.lwjgl.util.vector.*;
import org.newdawn.slick.geom.Shape;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import org.newdawn.slick.geom.Transform;

>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
import org.newdawn.slick.geom.Transform;

>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
import main.Camera;
import main.entity.Entity;

/**Useful utilities and methods */
public class Util {

	/**
	 * Clamp a number i between high and low limits
	 * @param i - number to clamp
	 * @param high - upper limit
	 * @param low - lower limit
	 * @return clamped value
	 */
	public static double clamp(double i, double high, double low) {
		return Math.max(high, Math.min(i, low));
	}

	/**Identical to {@link clamp()}*/
	public static float clamp(float i, float high, float low) {
		return Math.max(high, Math.min(i, low));
	}

	/**Identical to {@link clamp()}*/
	public static int clamp(int i, int high, int low) {
		return Math.max(high, Math.min(i, low));
	}
	
	/**Translate a screen vector to world coordinates*/
	public static Vector2f toScreen(Vector2f o) {
		return new Vector2f(toScreenX(o.x), toScreenY(o.y));
	}
	
	/**Translate a world vector to screen coordinates*/
	public static Vector2f toWorld(Vector2f o) {
		return new Vector2f(toWorldX(o.x), toWorldY(o.y));
	}

	public static Vector3f toWorld(Vector3f pos) {
		return new Vector3f(toWorldX(pos.x), toWorldY(pos.y), pos.z);
	}
	
	/**Translate a world vector to local entity coordinates*/
	public static Vector2f toLocal(Vector2f o, Entity e) {
		return new Vector2f(o.x - e.getX(), o.y - e.getY());
	}

	/**
	 * Translate a global x value to a local x value
	 * @param ox - original x value
	 * @return localized x value
	 */
	public static float toScreenX(float ox) {
		return ox - Camera.getAnchorX();
	}

	/**
	 * Translate a global y value to a local y value
	 * @param oy - original y value
	 * @return localized y value
	 */
	public static float toScreenY(float oy) {
		return oy - Camera.getAnchorY();
	}

	public static float toWorldX(float ox) {
		return ox + Camera.getAnchorX();
	}

	public static float toWorldY(float oy) {
		return oy + Camera.getAnchorY();
	}
<<<<<<< HEAD
<<<<<<< HEAD
	
	public static float getDistance(Shape a, Shape b) {
		return (float)Math.sqrt(Math.pow(b.getX()-a.getX(), 2) + Math.pow(b.getY()-a.getY(), 2));
	}
	
	public static float getDistance(Entity a, Entity b) {
		return (float)Math.sqrt(Math.pow(b.getX()-a.getX(), 2) + Math.pow(b.getY()-a.getY(), 2));
	}
	
	public static String getFilename(String path) {
		return path.substring(path.lastIndexOf("\\")+1, path.lastIndexOf("."));
	}
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9

}
