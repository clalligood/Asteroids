package model;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;
/**
 * 
 * @author Tony, Mehgan, Vlad, Justin, Collin
 * @version 2015/03/01
 */
public class Paddle extends AbstractEntity {
	
	/**Width of the paddle. */
	private static final int WIDTH = 10;
	
	/**Height of the paddle. */
	private static final int HEIGHT = 85;
	
	/** The speed at which the Paddle travels */
	private static final float MY_SPEED = 6.5f;
	
	/** Key that moves the paddle up. */
	private int myUpKey;
	
	/** Key that moves the paddle down. */
	private int myDownKey;
	
	/** My Score */
	private int myScore = 0;

	public Paddle(Vector2f thePosition, int theUpKey, int theDownKey) {
		super(new Rectangle(thePosition.getX(), thePosition.getY(), WIDTH, HEIGHT));
		this.setKeys(theUpKey, theDownKey);
	}
	
	/**
	 * Sets the keys to be used for moving the paddle up or down.
	 * @param theUpKey will set the key to move paddle up.
	 * @param theDownKey will set the key to move paddle down.
	 */
	private void setKeys(int theUpKey, int theDownKey) {
		myUpKey = theUpKey;
		myDownKey = theDownKey;
	}
	
	/**
	 * Paddle moves up.
	 */
	public void moveUp(int theDelta) {
		myVelocity.y -= (MY_SPEED * theDelta/ 1000);
		
	}
	
	/**
	 * Paddle moves down.
	 */
	public void moveDown(int theDelta) {
		myVelocity.y += (MY_SPEED * theDelta / 1000);
		
	}
	
	/**
	 * Paddle stays in place.
	 */
	public void stay () {
		myVelocity.set(0, 0);
	}
	
	/**
	 * Checks to make sure the paddle is in bounds. 
	 */
	public void checkBounds(GameContainer theGameContainer) {
		if ((myShape.getY() + myVelocity.y) <= 0) {
			stay();
		} else if ((myShape.getY() + myShape.getHeight() + myVelocity.y) >= theGameContainer.getHeight()) {
			stay();						
		}		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer theGameContainer,
			StateBasedGame theStateBasedGame, int theDelta) {
		if (theGameContainer.getInput().isKeyDown(myUpKey)) {
			this.moveUp(theDelta);
			this.checkBounds(theGameContainer);
		} else if (theGameContainer.getInput().isKeyDown(myDownKey)) {
			this.moveDown(theDelta);
			this.checkBounds(theGameContainer);
		} else {
			this.stay();
		}
		
		this.updatePosition(theGameContainer);
	}

	public void updatePosition(GameContainer theGameContainer) {
		myShape.setY(myShape.getY() + myVelocity.y);
	}
	
	public Shape getMyShape() {
		return myShape;
	}
	
	public void addScore() {
		this.myScore++;
	}
	
	public int getScore() {
		return myScore;
	}
}
