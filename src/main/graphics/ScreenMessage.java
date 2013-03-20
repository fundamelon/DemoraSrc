package main.graphics;

import java.util.ArrayList;

import util.Timer;
import util.Transition;

public class ScreenMessage {
	public String text = "";
	public Timer timeout;
	public Transition fade;
	public float fadeAmt;
	public static ScreenMessage current = null;
	
	public ScreenMessage(String s) {
		System.out.println("Sent message: ["+s+"]");
		this.text = s;
		this.fade = new Transition(0f, 10000f, 2000f);
		fade.start();
		
		timeout = new Timer("ScreenMessage", 5000);
		timeout.reset();
		timeout.start();
	}
	
	public static void updateAll() {
		if(current != null)
			current.update();
	}
	
	public void update() {
		fadeAmt = fade.getCurVal()/10000f;
		if(timeout.completed()) {
			fade.setToReverse();
			fade.start();
		}
		if((int)fade.getCurVal() == 0 && fade.finished()) {
			current = null;
			return;
		}
	}
}
