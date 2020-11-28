package model;

import processing.core.PApplet;

//Class Bullet Extends PApplet and Implements Runnable to use threads, Package Logic

public class Bullet extends PApplet implements Runnable {
	PApplet app;
	private int posX;
	private int posY;
	private int speed;

	public Bullet (int posX, int posY, PApplet app) {
		this.posX = posX;
		this.posY = posY;
		speed = 6;
		this.app = app;
	}
	
	//draw method
	public void drawBullet() {
		app.fill(203,52,248);
		app.ellipse(posX, posY ,10,10);
	}
	
	// run method for the thread
	public void run() {
		moveBullet();
		
	}
	
	//getters and setters
	public void moveBullet () {
		posY -= speed;
	}

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
