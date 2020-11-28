package model;

import processing.core.PApplet;

public class Counter {
	private PApplet app;
	private int quantity;
	
	public Counter( PApplet app) {
		this.app = app;
		quantity = 0;
	}
	public void drawCounter (int posY) {
		app.fill(255);
		app.text("Naves Eliminadas "+ quantity, 40 , posY);
	}
	public PApplet getApp() {
		return app;
	}
	public void setApp(PApplet app) {
		this.app = app;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
