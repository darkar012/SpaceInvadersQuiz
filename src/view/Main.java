package view;

import control.MainController;
import processing.core.PApplet;

public class Main extends PApplet {
	
	MainController control;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("view.Main");
	}
	
	public void settings() {
		
		size(600,900);
		
	}

	public void setup() {
		control = new MainController(this);
	}

	public void draw() {
		background(12, 22, 79);
		control.drawGame();
	}
	
	public void keyPressed() {
		int key = keyCode;
		control.moveHero(key);
	}
	
	public void keyReleased() {
		int key = keyCode;
		control.notMoveHero(key);
	}
}

