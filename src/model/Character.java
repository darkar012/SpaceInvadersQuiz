package model;

import processing.core.PApplet;

public abstract class Character implements Runnable {
	
	protected PApplet app;
	protected int posX;
	protected int posY;
	protected int speed;
	protected int size;
	protected int key;
	protected boolean changeSide;
	
	public Character (PApplet app) {
		this.app = app;
	}
	
	public abstract void drawChar();
	public abstract void moveChar();

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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
	public boolean isChangeSide() {
		return changeSide;
	}

	public void setChangeSide(boolean changeSide) {
		this.changeSide = changeSide;
	}
	
	
}
