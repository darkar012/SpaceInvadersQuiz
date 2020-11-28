package control;

//class MainController Package Controller

import model.Logic;
import processing.core.PApplet;

public class MainController {
	
	private PApplet app;
	private Logic logic;
	
	public MainController(PApplet app) {
		this.app=app;
		logic=new Logic(app);
	}
	public void drawGame() {
		logic.drawGame();
	}
	public void change(int key) {
		logic.change(key);
	}
	public void notChange(int key) {
		logic.notChange(key);
	}
}
