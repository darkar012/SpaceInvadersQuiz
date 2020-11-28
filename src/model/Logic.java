package model;

import java.util.ArrayList;

import processing.core.PApplet;

public class Logic {

	private PApplet app;
	private Hero hero;
	private SecurityBox box;
	private Counter counter;
	private ArrayList<Alien> alienList;
	private ArrayList<Bullet> bulletList;
	private int alienTotal;
	private int alienY;
	private boolean move;
	private boolean shoot;
	private boolean shoot2;
	private boolean stop;

	public Logic(PApplet app) {

		this.app=app;
		hero = new Hero(app);
		counter = new Counter(app);
		alienList = new ArrayList<Alien>();
		bulletList = new ArrayList<Bullet>();
		box = new SecurityBox(14, 10, app);
		alienTotal = 12;
		alienY = 10;
		move = false;
		stop = false;
		
		
		bulletList.add(new Bullet(-20, -20, app));
		addAlien();
	}

	public void addAlien() {
		for (int i = 0; i < alienTotal; i++) {

			alienList.add(new Alien(((i*48)+15), alienY, app));
			alienList.add(new Alien(((i*48)+15), alienY*6, app));

		}
	}


	public void drawGame() {
		counter.drawCounter(40);
		
		hero.drawChar();

		if (move == true) {
			Thread moveHeroThread = new Thread(hero);
			moveHeroThread.start();
		}

		for (int i = 0; i < alienList.size()/2; i++) {

			alienList.get(i).drawChar();
			if (stop == false) {
			if (box.getPosX() <= 0) {

				alienList.get(i).moveVertical();
				alienList.get(i).setChangeSide(true);

			} else if (box.getPosX() >= 324) {

				alienList.get(i).moveVertical();
				alienList.get(i).setChangeSide(false);

			}
			
			Thread alienThread = new Thread(alienList.get(i));
			alienThread.start();				
			}
		}

		box.drawBox(285, 93);
		box.moveBox();

		for (int j = 0; j < bulletList.size(); j++) {
			bulletList.get(j).drawBullet();

			Thread bulletThread = new Thread(bulletList.get(j));
			bulletThread.start();
		}
		
		bulletCollision();
	}
	
	public void bulletCollision() {
		for (int i = 0; i < bulletList.size(); i++) {
			for (int j = 0; j < alienList.size(); j++) {
				int otherX = alienList.get(j).getPosX();
				int otherY = alienList.get(j).getPosY();
				int bPosX = bulletList.get(i).getPosX();
				int bPosY = bulletList.get(i).getPosY();
				boolean destroyAlien = false;
				
				if(app.dist( bPosX, bPosY, otherX, otherY) < 40) {
					bulletList.get(i).setPosX(-20);
					alienList.get(j).setPosX(960);
					System.out.println(alienList.size());
					if(counter.getQuantity()< alienList.size()/2) {
					counter.setQuantity(counter.getQuantity()+1);
				} 
				}
				
					if (otherY+100-hero.getPosY() > 15) {
						stop=true;
					}
			}
					
					
				}
				
}
				

public void moveHero(int key) {

	hero.setKey(key);

	if (key == 37 || key == 39) {

		move = true;
	}
	if (key == 83) {
		bulletList.add(new Bullet(hero.getPosX(), hero.getPosY(), app));
	}
}

	public void notMoveHero(int key) {

		int not = key;

		if (not == 37 || not == 39) {

			move = false;

		}
		if (key==83) {
			shoot = false;
		}
	}
}
