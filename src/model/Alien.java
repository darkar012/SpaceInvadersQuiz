package model;

import processing.core.PApplet;

//Class Alien Extends of Father Class Character and Implements Runnable to use threads, Package Logic

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
	
	// run method for the thread
	public void run() {
		moveChar();
	}

	//draw method
	public void drawChar() {
	app.fill(0,255,0);
	app.rect(posX, posY, size, size);
	}

	//move method
	public void moveChar() {
		
		if (changeSide) {
			posX += speed;
		}
		
		if(!changeSide){
			posX -= speed;
			//posY = posY +50;
		}

	}
	
	//vertical jump when the arraylist touch the border of the canvas
	public void moveVertical() {
		posY = posY +50;
	}

	//getters and setters
	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	
}

