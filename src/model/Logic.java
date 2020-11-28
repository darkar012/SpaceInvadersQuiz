package model;

import java.util.ArrayList;

import processing.core.PApplet;

public class Logic {

	private PApplet app;
	private Hero hero;
	private ArrayList<Character> alienList;
	private int alienTotal;
	private int alienY;
	private boolean move;

	public Logic(PApplet app) {

		this.app=app;
		hero = new Hero(app);
		alienList = new ArrayList<Character>();
		alienTotal = 12;
		alienY = 10;
		move = false;

		addAlien();
	}

	public void addAlien() {
		for (int i = 0; i < alienTotal; i++) {
			alienList.add(new Alien(((i*48)+15), alienY, app));
			alienList.add(new Alien(((i*48)+15), alienY*6, app));
		}
	}

	public void drawGame() {
		hero.drawChar();
		if (move == true) {
			Thread moveHeroThread = new Thread(hero);
			moveHeroThread.start();
		}
		for (int i = 0; i < alienList.size()/2; i++) {
			alienList.get(i).drawChar();
			Thread alienThread = new Thread(alienList.get(i));
			alienThread.start();
		}
		
	}


	public void moveHero(int key) {
		hero.setKey(key);

		if (key == 37 || key == 39) {
			move = true;
		}
	}

	public void notMoveHero(int key) {
		int not = key;
		if (not == 37 || not == 39) {
			move = false;
		}
	}
}
