package model;

import processing.core.PApplet;

public class Alien extends Character implements Runnable{

	public Alien(int posX, int posY, PApplet app) {
		super(app);
		speed = 5;
		size = 40;
		this.posX = posX;
		this.posY = posY;
	}

	public void run() {
		moveChar();
	}

	public void drawChar() {
	app.rect(posX, posY, size, size);
	}

	public void moveChar() {
		posX += speed;
		if (posX < 0) {
			speed = speed*-1;
			posY = posY +50;
		}
		
		if(posX > 600-(size/2)){
			speed *=-1;
			posY = posY +50;
		}

}
}
