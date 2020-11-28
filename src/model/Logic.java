package model;

import java.util.ArrayList;

import exception.Lose;
import exception.Win;
import processing.core.PApplet;
// Class Logic Package Model
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

		//Add element extra to bullet to correct unexpected error of java null pointer
		bulletList.add(new Bullet(-20, -20, app));
		//Method to add elements to alienList
		addAlien();
	}

	//Method to add elements to alienList
	public void addAlien() {
		for (int i = 0; i < alienTotal; i++) {
			alienList.add(new Alien(((i*48)+15), alienY, app));
			alienList.add(new Alien(((i*48)+15), alienY*6, app));

		}
	}


	public void drawGame() {
		// condition to paint and erase the counter of death enemies
		if (win==false || lose == false) {
			counter.drawCounter(40);
		}
		
		hero.drawChar();
		// condition to activate or finalize the thread when the player press a button
		if (move == true) {
			Thread moveHeroThread = new Thread(hero);
			moveHeroThread.start();
		}

		for (int i = 0; i < alienList.size()/2; i++) {

			alienList.get(i).drawChar();
			
			//set of the thread of Alien and their conditions to move vertically and horizontally automatically
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
		//box of security to move the alienList element in reference to this box
		box.drawBox(285, 93);
		box.moveBox();

		//set of the thread to the bullet movement when the user press S
		for (int j = 0; j < bulletList.size(); j++) {
			bulletList.get(j).drawBullet();

			Thread bulletThread = new Thread(bulletList.get(j));
			bulletThread.start();
		}

		// exceptions to send the messages of win or lose
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
		
		//win or lose messages
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

	//method to calculate the collision between bullets and aliens
	public void bulletCollision() throws Win, Lose {
		for (int i = 0; i < bulletList.size(); i++) {
			for (int j = 0; j < alienList.size()/2; j++) {
				int otherX = alienList.get(j).getPosX();
				int otherY = alienList.get(j).getPosY();
				int bPosX = bulletList.get(i).getPosX();
				int bPosY = bulletList.get(i).getPosY();
				
				//dist used to define the minimun distance between the objects
				if(app.dist( bPosX, bPosY, otherX, otherY) < 40) {
					//cause problems with the remove, a solution was to send the element in the list to a place outside the canvas
					bulletList.get(i).setPosX(-589);
					if (bulletList.get(i).getPosY() == 10) {
						bulletList.remove(i);
					}
					alienList.get(j).setPosX(2060);
					alienList.get(j).setPosY(0);
					alienList.get(j).setDead(true);
					//death aliens add to the counter
					if(alienList.get(j).isDead()==true) {
						counter.setQuantity(counter.getQuantity()+1);
					}
					
				}
				
				 //throw of exceptions
				
				if (counter.getQuantity() == (alienList.size()/2)) {
					throw new Win ("Has Ganado");
				}

				if (otherY+100-hero.getPosY() > 15) {
					throw new Lose ("Has Perdido");

				}
			}
		}


	}



	//movement and other keyboard process to activate threads or restart and exit the game
	public void change(int key) {

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
	//reinitialized method to restar everything
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
	
	
	//movement keyboard process to improve the movement
	public void notChange(int key) {

		int not = key;

		if (not == 37 || not == 39) {

			move = false;

		}
	}
}
