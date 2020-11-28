package model;

import java.util.ArrayList;

import exception.Lose;
import exception.Win;
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
	private boolean win;
	private boolean lose;

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
		win = false;
		lose = false;


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
		if (win==false || lose == false) {
		counter.drawCounter(40);
		}
		hero.drawChar();

		if (move == true) {
			Thread moveHeroThread = new Thread(hero);
			moveHeroThread.start();
		}

		for (int i = 0; i < alienList.size()/2; i++) {

			alienList.get(i).drawChar();
			if (lose == false) {
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

		try {
			bulletCollision();
		} catch (Win e) {
			System.out.println("Has Ganado");
			System.out.println("Presiona R para reiniciar");
			System.out.println("Presiona E para salir");
			win = true;
		} catch (Lose e) {
			System.out.println("Has Perdido");
			System.out.println("Presiona R para reiniciar");
			System.out.println("Presiona E para salir");
			lose = true;
		}

		if (win==true) {
			
			app.fill(0, 255, 158);
			app.rect(100, 100, 400, 400);
			app.fill(0);
			app.textSize(35);
			app.text("Has Ganado", 200, 200);
			app.textSize(20);
			app.text("Presiona R para reiniciar", 200, 400);
			app.text("Presiona E para salir", 200, 450);
			
		}
		if (lose==true) {
			
			app.fill(0, 255, 158);
			app.rect(100, 100, 400, 400);
			app.fill(0);
			app.textSize(35);
			app.text("Has perdido", 200, 200);
			app.textSize(20);
			app.text("Presiona R para reiniciar", 200, 400);
			app.text("Presiona E para salir", 200, 450);
			
		}
	}

	public void bulletCollision() throws Win, Lose {
		for (int i = 0; i < bulletList.size(); i++) {
			for (int j = 0; j < alienList.size(); j++) {
				int otherX = alienList.get(j).getPosX();
				int otherY = alienList.get(j).getPosY();
				int bPosX = bulletList.get(i).getPosX();
				int bPosY = bulletList.get(i).getPosY();

				if(app.dist( bPosX, bPosY, otherX, otherY) < 15) {
					bulletList.get(i).setPosX(-20);
					alienList.get(j).setPosX(960);
					alienList.get(j).setPosY(0);
					alienList.get(j).setDead(true);
					if(alienList.get(j).isDead()==true) {
						counter.setQuantity(counter.getQuantity()+1);
					}
				}

				if (counter.getQuantity() == (alienList.size()/2)+2) {
					throw new Win ("Has Ganado");
				}

				if (otherY+100-hero.getPosY() > 15) {
					throw new Lose ("Has Perdido");
					
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
		
		if (win == true || lose == true) {
			if (key==69) {
				app.exit();
			}
			if (key==82) {
				reinitialized();
				
			}
			}
	}

	private void reinitialized() {
		hero = new Hero(app);
		counter = new Counter(app);
		alienList = new ArrayList<Alien>();
		bulletList = new ArrayList<Bullet>();
		box = new SecurityBox(14, 10, app);
		alienTotal = 12;
		alienY = 10;
		move = false;
		win = false;
		lose = false;


		bulletList.add(new Bullet(-20, -20, app));
		addAlien();

		
	}

	public void notMoveHero(int key) {

		int not = key;

		if (not == 37 || not == 39) {

			move = false;

		}
	}
}
