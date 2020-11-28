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
		app.text("Presione S para disparar ", 40 , posY*2);
		app.text("Con las flechitas se mueve ", 40 , posY*3);
		app.text("Si hizo lo que se pudo profe, perdone lo poquito ", 40 , posY*4);
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
