package util;

import org.newdawn.slick.Color;

public class Transition {
	float a, b, t; //start, end, duration
	float curVal;
	float curTime, startTime;
	String curveType = "linear";
	boolean paused = true;
	boolean reverse = false;
<<<<<<< HEAD
<<<<<<< HEAD
	Color curCol = null;
	Color ocol, tcol;
	Transition red, green, blue, alpha;
	String type;
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	
	String[] validCurveTypes = new String[]{
		"linear", "single", "double", "hold"
	};
	
	/**
	 * Create a new transition.
	 * @param a origin
	 * @param b target
	 * @param t time
	 */
	public Transition(float a, float b, float t) {
		if(a < b) {
			this.a = a;
			this.b = b;
		} else {
			this.a = b;
			this.b = a;
		}
		this.t = t;
		curVal = a;
		startTime = System.nanoTime();
		TransitionManager.addToTable(this);
<<<<<<< HEAD
<<<<<<< HEAD
		type = "value";
	}
	
	/**
	 * Create a new color transition.
	 * @param a original color
	 * @param b target color
	 * @param t time
	 */
	public Transition(Color a, Color b, float t) {
		red = new Transition(a.r, b.r, t);
		green = new Transition(a.g, b.g, t);
		blue = new Transition(a.b, b.b, t);
		alpha = new Transition(a.a, b.a, t);
		ocol = a;
		tcol = b;
		type = "color";
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}
	
	/**
	 * Updates the transition. Pauses once target value is reached
	 * @param delta
	 */
	public void update(float delta) {
		if(paused) return;
		curTime = System.nanoTime();
<<<<<<< HEAD
<<<<<<< HEAD
		
		if(type.equals("value")) {
			//Exponential, smooth transition.
			if(curveType == "single") {
				if(!reverse)
					curVal = Math.min(((curTime - startTime) / t) * (b - a), b);
				else
					curVal = Math.max(((curTime - startTime) / t) * (a - b), a);
			} 
			//Linear transition
			else if(curveType == "linear") {
				if(!reverse)
					curVal = Math.min(curVal + (delta / t) * (b-a), b);
				else 
					curVal = Math.max(curVal - (delta / t) * (b-a), a);
			}
			curVal = Math.round(curVal * 10000) / 10000;
			
			if(curVal == b) paused = true;
			//	System.out.println("a: "+a+" b: "+b+" curVal: "+curVal);
		}
		if(type.equals("color")) {
			curCol = new Color(red.getCurVal(), green.getCurVal(), blue.getCurVal(), alpha.getCurVal());
			if(curCol.r == tcol.r && curCol.g == tcol.g && curCol.b == tcol.b && curCol.a == tcol.a) {
				paused = true;
			}
		}
=======
		
		//Exponential, smooth transition.
		if(curveType == "single") {
			if(!reverse)
				curVal = Math.min(((curTime - startTime) / t) * (b - a), b);
			else
				curVal = Math.max(((curTime - startTime) / t) * (a - b), a);
		} 
		//Linear transition
		else if(curveType == "linear") {
			if(!reverse)
				curVal = Math.min(curVal + (delta / t) * (b-a), b);
			else 
				curVal = Math.max(curVal - (delta / t) * (b-a), a);
		}
		curVal = Math.round(curVal * 10000) / 10000;
		
		if(curVal == (float)b) paused = true;
	//	System.out.println("a: "+a+" b: "+b+" curVal: "+curVal);
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		
		//Exponential, smooth transition.
		if(curveType == "single") {
			if(!reverse)
				curVal = Math.min(((curTime - startTime) / t) * (b - a), b);
			else
				curVal = Math.max(((curTime - startTime) / t) * (a - b), a);
		} 
		//Linear transition
		else if(curveType == "linear") {
			if(!reverse)
				curVal = Math.min(curVal + (delta / t) * (b-a), b);
			else 
				curVal = Math.max(curVal - (delta / t) * (b-a), a);
		}
		curVal = Math.round(curVal * 10000) / 10000;
		
		if(curVal == (float)b) paused = true;
	//	System.out.println("a: "+a+" b: "+b+" curVal: "+curVal);
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	}
	
	/**
	 * Get current value
	 * @return transition value
	 */
	public float getCurVal() {
		return curVal;
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
	public Color getCurCol() {
		return curCol;
	}
	
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
	/**	Start animation	*/
	public void start() {
		paused = false;
	}
	
	/**	Pause animation	*/
	public void pause() {
		paused = true;
	}
	
	/**	Is it at the target value?	*/
	public boolean finished() {
<<<<<<< HEAD
<<<<<<< HEAD
		if(curVal == b) return true;
=======
		if(curVal == (float)b) return true;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
=======
		if(curVal == (float)b) return true;
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		else return false;
	}
	
	/**	Set direction of animation */
	public void setToForward() {
		reverse = false;
	}
	
	/**	Set direction of animation */
	public void setToReverse() {
		reverse = true;
	}
}
