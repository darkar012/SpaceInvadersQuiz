package control;

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
	public void moveHero(int key) {
		logic.moveHero(key);
	}
	public void notMoveHero(int key) {
		logic.notMoveHero(key);
	}
}
