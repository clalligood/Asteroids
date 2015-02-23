/**
 * 
 */
package game;

import game.objects.AbstractEntity;
import game.objects.Bullet;
import game.objects.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {

	private static int myID = 1;
	
	private static int numberOfStars = 1150;
	private static int galaxyCoreDensity = 700;
	
	private ArrayList<AbstractEntity> gameObjects;
	private Point[] stars = new Point[numberOfStars];
	
	private Set<AbstractEntity> collidedObjects;

	public Game(int theID) {
		myID = theID;
		
		gameObjects = new ArrayList<AbstractEntity>();
		collidedObjects = new HashSet<>();
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#init(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame)
	 */
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		this.createStars();
		
		gameObjects.add(new Player());
		
		for( AbstractEntity gameObject : gameObjects) {
			gameObject.init(gc, sbg);
		}
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#render(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, org.newdawn.slick.Graphics)
	 */
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		drawStars(g);
		
		g.drawString("Mutha Fukkin' Space Wars", gc.getWidth()/2-50, 485);
		
		for(AbstractEntity gameObject : gameObjects) {
			gameObject.render(gc, sbg, g);
		}
		
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.GameState#update(org.newdawn.slick.GameContainer, org.newdawn.slick.state.StateBasedGame, int)
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		for(int i = 0; i < gameObjects.size(); i++) {
			
			AbstractEntity gameObject = gameObjects.get(i);
			
			switch(gameObject.update(gc, sbg, delta)) {
			case 0:
				gameObjects.remove(i);
				break;
			case 2:
				//Create a bullet object
				gameObjects.add(new Bullet(new Vector2f(gameObject.getPosition().copy()), gameObject.getRotation()));
				break;
			case 1:
			default:
				break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.newdawn.slick.state.BasicGameState#getID()
	 */
	@Override
	public int getID() {
		return myID;
	}

	private void drawStars(Graphics g) {
		Color oldColor = g.getColor();
		g.rotate(400, 300, 0);
		g.setColor(Color.white);
		
		for(int i = 0; i < numberOfStars; i++) {
			Point thePoint = stars[i];
			Random randTwinkle = new Random();
			int twinkDistance = 3;
			/**
			 * We do Twinkling here
			 */
			if (randTwinkle.nextInt(1000) > 950) {
				g.drawLine(thePoint.getX(), thePoint.getY(), thePoint.getX()+randTwinkle.nextInt(twinkDistance)-1, thePoint.getY()+randTwinkle.nextInt(twinkDistance)-1);
			} else {
				g.drawLine(thePoint.getX(), thePoint.getY(), thePoint.getX(), thePoint.getY());
			}
		}
		
		g.resetTransform();
		g.setColor(oldColor);
	}
	
	private void createStars() {
		Random randX = new Random();
		Random randY = new Random();
		for(int i = 0; i < numberOfStars-galaxyCoreDensity; i++) {
			double random = randX.nextGaussian() * 200;
			stars[i] = new Point((float)random+400, randY.nextInt(600));
		}
		
		for(int i = numberOfStars-galaxyCoreDensity; i < numberOfStars; i++) {
			double random = randX.nextGaussian() * 34;
			int yPos = randY.nextInt(600);
			stars[i] = new Point((float)random+400, yPos);
		}
	}
	
	/**
	 * Checks to see if their is a collision between two AbstractEntities
	 * @param theAbstractEntities
	 */
	private void checkCollisions(ArrayList<AbstractEntity> theAbstractEntities) {
		for (AbstractEntity entity : theAbstractEntities) {
			entity.getPosition().getX();
			entity.getPosition().getY();
		}
	}
	
}
