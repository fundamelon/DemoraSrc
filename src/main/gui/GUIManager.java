package main.gui;

import main.*;


import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import util.Transition;

public class GUIManager {
	private static Panel p;
	public static int screenMidX = GameBase.getWidth() / 2;
	public static int screenMidY = GameBase.getHeight() / 2;
	
	static Color gray01 = new Color(0.2f, 0.2f, 0.2f, 0.9f);
	static Color gray02 = new Color(0.5f, 0.5f, 0.5f, 0.9f);
	static Color gray03 = new Color(0.6f, 0.6f, 0.6f, 0.8f);
	static Color gray04 = new Color(0.8f, 0.8f, 0.8f, 0.8f);
	
	@SuppressWarnings("deprecation")
	public static Font menuFont01 = new TrueTypeFont(new java.awt.Font("Lucida Console", 1, 12), true);
	
	public static int curPanel = 0;
	
	public static Transition fade;
	
	public static void init() {
		Panel.init();
		show();
		
	}
	
	public static void show() {
		if(GameBase.ingame()) {
			p = new Panel(Panel.PRESET_INGAME);
		} else {
			p = new Panel(Panel.PRESET_MAIN);
		}
	}
	
	public static void update() {
		p.update();
	}
	
	public static void setMenuPanel(int preset) {
		if(GameBase.ingame()) {
			if(preset == Panel.PRESET_MAIN)
				preset = Panel.PRESET_INGAME;
		} else {
			if(preset == Panel.PRESET_INGAME)
				preset = Panel.PRESET_MAIN;
		}
		p.setPreset(preset);
	}
	
	public static void render(Graphics g, float delta) {
		Color oldCol = g.getColor();
<<<<<<< HEAD
<<<<<<< HEAD
		Font oldFont = g.getFont();
		
		g.setFont(menuFont01);
		
		if(GameBase.ingame()) {
			g.setColor(Color.black);
		} else {
			g.setColor(Color.white);
		}
		
		if(p.getPreset() == Panel.PRESET_MAIN || p.getPreset() == Panel.PRESET_INGAME) { 
			g.drawString("Demora v."+GameBase.getVersion(), screenMidX - 100, screenMidY + 225);
		}
		
		for(int i = 0; i < p.getItems().size(); i++) {
			GUIObject item = p.getItemByID(i);
			if(item.isVisible()) {

				if(item.getType().contains("button")) {
					renderItem(g, (Button)item);
				}
				
				if(item.getType().contains("label")) {
					renderItem(g, (Label)item);
=======
=======
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
		for(Panel p : panels) {
			if(p.getPreset() == Panel.PRESET_MAIN) {
				if(GameBase.isInGame()) {
					g.setColor(Color.black);
					p.getItem(0).setText("RESUME");
				}
				else {
					g.setColor(Color.white);
				}
				g.drawString("Demora v."+GameBase.getVersion(), 256, 50);
			}
			for(int i = 0; i < p.getItems().size(); i++) {
				Clickable item = p.getItem(i);
				if(item.isVisible()) {
					if(main.GameBase.debug_menu) {
						if(item.mouseClick()) {
							System.out.println(item.getName() + ": clicked");
						}
						
						if(item.mouseExit()) {
							System.out.println(item.getName() + ": exited");
						} else if(item.mouseEnter()) {
							System.out.println(item.getName() + ": entered");
						}
					}
					
					
					
					Color oldColor = g.getColor();
					if(item.mouseDown()) {
						g.setColor(Color.darkGray);
						Event.fire(item.getEventKey());
					} else if(item.mouseHover()) {
						g.setColor(Color.gray);
					} else {
						g.setColor(Color.white);
					}
					
					g.fill(item.getBounds());
					
					g.setColor(Color.black);
					g.drawString(item.getText(), 
							item.getBounds().getCenterX() - item.getText().length() * 3.3f, 
							item.getBounds().getCenterY() - 5);
					
					g.setColor(oldColor);
>>>>>>> b3a3f1e0343578cd7b99790904c0228228d70ba9
				}
			}
		}
		g.setColor(oldCol);
		g.setFont(oldFont);
	}
	
	public static void renderItem(Graphics g, Button item) {
		if(main.GameBase.debug_menu) {
			if(item.mouseClick()) {
				System.out.println(item.getName() + ": clicked");
			}
			
			if(item.mouseExit()) {
				System.out.println(item.getName() + ": exited");
			} else if(item.mouseEnter()) {
				System.out.println(item.getName() + ": entered");
			}
		}
		Color oldCol = g.getColor();
	
		if(!item.isLocked()) {
			if(item.mouseDown()) {
				g.setColor(gray01);
			} else if(item.mouseHover()) {
				g.setColor(gray02);
			} else {
				g.setColor(gray03);
			}
			
			if(item.mouseClick()) {
				AudioManager.playSound("click01", 2f, 1f);
				Event.fire(item.getEventKey());
			}
		} else {
			g.setColor(Color.darkGray);
		}
		
		g.fill(item.getBounds());
		
		g.setColor(gray01);
		g.setLineWidth(3);
		g.setAntiAlias(true);
		g.drawRoundRect(
				item.getBounds().getX()-2, 
				item.getBounds().getY()-2, 
				item.getBounds().getWidth(), 
				item.getBounds().getHeight(), 
				5);
		g.setLineWidth(1);

		g.setAntiAlias(false);
		
		g.setColor(Color.black);
		g.setFont(Panel.Label01);
		g.drawString(item.getText(), 
				(int)item.getBounds().getCenterX() - g.getFont().getWidth(item.getText())/2, 
				(int)item.getBounds().getCenterY() - g.getFont().getHeight(item.getText())/2);
		
		g.setColor(oldCol);
		
	}
	
	public static void renderItem(Graphics g, Label item) {
		g.setColor(item.getColor());
		g.setFont(item.getFont());
		
		int x = 0, y = 0;
		
		if(item.getAlign().equals("center")) {
			x =(int)(item.getBounds().getX() - g.getFont().getWidth(item.getText())/2);
			y =(int)(item.getBounds().getY() - g.getFont().getHeight(item.getText())/2);
		}
		
		if(item.getAlign().equals("left")) {
			x = (int)(item.getBounds().getX());
			y = (int)(item.getBounds().getCenterY() - g.getFont().getHeight(item.getText())/2);
		}
		
		if(item.getAlign().equals("right")) {
			x = (int)(item.getBounds().getX() - item.getBounds().getWidth());
			y = (int)(item.getBounds().getCenterY() - g.getFont().getHeight(item.getText())/2);
		}
		
		g.setColor(new Color(0.3f, 0.3f, 0.3f, 0.5f));
		g.drawString(item.getText(), x + 3, y + 2);
		
		g.setColor(item.getColor());
		g.drawString(item.getText(), x, y);
	}
}
