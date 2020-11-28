package model;

import processing.core.PApplet;

public class Hero extends Character implements Runnable{

	public Hero(PApplet app) {
		super(app);
		posX = 300;
		posY = 850;
		speed = 10;
		size = 50;
	}
	
	public void drawChar() {
		
		app.fill(200,116,178);
		app.ellipse(posX, posY, size, size);
		
	}
	
	public void moveChar() {
		
		switch (key) {
		case 39: {
			posX += speed;
			if(posX >= 600-(size/2)){
				posX -= speed;					
			}
		}
		break;
		case 37: {
			posX -=speed;
			if(posX <= 0+ (size/2)){
				posX +=speed;
			}
		}
		default:
		}
		
	}
	
	public void run() {
		moveChar();
		
	}

}
