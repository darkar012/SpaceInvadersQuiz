package model;

import processing.core.PApplet;
//Class SecurityBox Extends PApplet, Package Logic
public class SecurityBox extends PApplet{

	private PApplet app;
	private int posX;
	private int posY;
	private int speed;

	public SecurityBox (int posX, int posY, PApplet app) {
		this.app = app;
		this.posX = posX;
		this.posY = posY;
		speed = 5;
	}
	
	//draw method
	public void drawBox(int sizeX, int sizeY) {
		app.fill(0,0,255,0);
		app.stroke(0,0,255,0);
		app.rect(posX,posY, sizeX,sizeY );
	}

	//move method
	public void moveBox() {
		posX += speed;
		if (posX < 0) {
			speed = speed*-1;
			//posY = posY +50;
		}

		if(posX > 600-281){
			speed *=-1;
		}
	}
	
	//getters and setters
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
