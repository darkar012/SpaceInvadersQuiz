package model;

import processing.core.PApplet;

public class Alien extends Character implements Runnable{
	
	private boolean isDead;
	
	public Alien(int posX, int posY, PApplet app) {
		super(app);
		speed = 5;
		size = 40;
		this.posX = posX;
		this.posY = posY;
		changeSide=true;
		isDead=false;
	}

	public void run() {
		moveChar();
	}

	public void drawChar() {
	app.fill(0,255,0);
	app.rect(posX, posY, size, size);
	}

	public void moveChar() {
		
		if (changeSide) {
			posX += speed;
		}
		
		if(!changeSide){
			posX -= speed;
			//posY = posY +50;
		}

	}
	public void moveVertical() {
		posY = posY +50;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	
}

