package model;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import view.GameState;

public class Ball extends AbstractEntity {
	
	/** The default radius of the pong ball. */
	private final static int MY_RADIUS = 10;
	
	private final static float VELOCITY = 4;
	
	/** The default velocity of the pong ball. */
	private Vector2f myInitial;
	
	private int myScore = 0;
	
	
	public Ball(Vector2f thePosition) {
		super(new Circle(thePosition.getX(), thePosition.getY(), MY_RADIUS));
		myInitial = new Vector2f(VELOCITY, VELOCITY);
		this.myVelocity = myInitial.copy();
	}
	
	/**
	 * Increments the position of the pong ball by
	 * the velocity.
	 */
	public void move() {
		myShape.setX(myShape.getX() + myVelocity.x);
		myShape.setY(myShape.getY() + myVelocity.y);
	}
	
	
	/**
	 * Checks if the pong ball will go out of bounds of the
	 * playable area.
	 * 
	 * @param theWidth The width of the playable area.
	 * @param theHeight The height of the playable area.
	 * @return Indicates if the pong ball is out of bounds on move.
	 */
	public boolean hitTopBounds(final int theHeight) {
		boolean result = false;
		
		if (myShape.getY() - MY_RADIUS + myVelocity.getY() < 0 
				|| myShape.getY() + MY_RADIUS + myVelocity.getY() > theHeight) {
			result = true;
		}
		return result;
	}
	
	
	public boolean hitSideBounds(final int theWidth) {
		boolean result = false;
		if (myShape.getX() + myVelocity.getX() <= 0 ) {
			result = true;
			myScore = 1;
		}
		else if(myShape.getX() + myVelocity.getX() > theWidth) {
			myScore = 2;
			result = true;
		}
		return result;
	}
	
	
	
	public boolean hitPaddle(final Shape theRect) {
		boolean result = false;
		if (myShape.intersects(theRect)) {
			result = true;
		}
		return result;
	}
	
	
	
	public void reflectX() {
		myVelocity.set(-1 * myVelocity.getX(), myVelocity.getY());
		move();
	}
	
	
	
	public void reflectY() {
		myVelocity.set(myVelocity.getX(), -1 * myVelocity.getY());
		move();
	}
	
	
	public void reset(GameContainer theGameContainer) {
		Random theRandom = new Random();
		int randomRotation = theRandom.nextInt(360);
		while ((randomRotation % 90 > 65) || (randomRotation % 90 < 25)) {
			randomRotation = theRandom.nextInt(360);
		}
		myVelocity.set(rotateVector(myVelocity, randomRotation));
		
		myShape.setCenterX(theGameContainer.getWidth() / 2);
		myShape.setCenterY(theGameContainer.getHeight() / 2);
	}
	
	

	@Override
	public void update(GameContainer theGameContainer,
		StateBasedGame theStateBasedGame, int theDelta) {
		
		GameState gameState = (GameState) theStateBasedGame.getCurrentState();
		if (hitSideBounds(theGameContainer.getWidth())) {
			move();
			reset(theGameContainer);
		} else if (hitTopBounds(theGameContainer.getHeight())) {
			reflectY();
		} else if (hitPaddle((gameState.getMyPlayer1().getMyShape()))) {
			reflectX();
		} else if (hitPaddle((gameState.getMyPlayer2().getMyShape()))) {
			reflectX();
		} else {
			move();
		}
			
		
	}
	
	public Vector2f rotateVector( Vector2f input, int theRotation) {
		  Vector2f result = new Vector2f(0, 0);
		  double cs = Math.cos(Math.toRadians(theRotation));
		  double sn = Math.sin(Math.toRadians(theRotation));
		  result.x = (float) (input.x * cs - input.y * sn);
		  result.y = (float) (input.x * sn + input.y * cs);
		  return result;
	}

	public int checkPoint() {
		int myReturnScore = myScore;
		myScore = 0;
		return myReturnScore;
	}

}
