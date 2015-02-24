package game.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;


public abstract class AbstractEntity {
	
	protected Vector2f maxSpeed = new Vector2f(.9f, .9f);
	
	protected Rectangle hitBox;
	protected Vector2f hitBoxSize = new Vector2f(20, 20);
	
	protected int displayMult = 0;
	
	protected Image myImage;
	protected Vector2f myPosition;
	protected Vector2f myVelocity;
	protected float myRotation = 0;
	
	protected int myHealth = 20;
	
	private boolean hasCollided;

	public AbstractEntity(String theImage, Vector2f thePosition, Vector2f theVelocity) throws SlickException {
		setHasCollided(false);
		myImage = new Image(theImage);
		myPosition = thePosition;
		myVelocity = theVelocity;
	}
	
	public abstract void init(GameContainer gc, StateBasedGame sbg) throws SlickException;
	
	public abstract int update(GameContainer gc, StateBasedGame sbg, int delta);
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		myImage.draw(myPosition.x-(myImage.getWidth()/2), myPosition.y-(myImage.getHeight()/2));

		/**
		g.draw(getHitBox());
		
		g.drawString("Pos: " + myPosition.toString(), 15*displayMult, 15);
		g.drawString("Vel: " + myVelocity.toString(), 15*displayMult, 30);
		g.drawString("Rot: " + myRotation, 15*displayMult, 45);
		g.drawString("Img Rot: " + myImage.getRotation(), 15*displayMult, 60);
		*/
	}
	
	public void rotate(float theRotation) {
		myRotation += theRotation;
		myRotation %= 360;
		myImage.setRotation(myRotation);
	}
	
	public void setMaxSpeed(Vector2f theMaxSpeed) {
		maxSpeed = new Vector2f(theMaxSpeed.copy());
	}
	
	public void setVelocity(Vector2f theVelocity) {
		myVelocity = new Vector2f(theVelocity.copy());
	}
	
	public Vector2f getPosition() {
		return myPosition;
	}
	
	public float getRotation() {
		return myRotation;
	}
	
	public void setImage(String theString) throws SlickException {
		myImage = new Image(theString);
	}
	
	public void applyForce(Vector2f theForce) {
		myVelocity.add(theForce);
		
		myVelocity.x = (myVelocity.x > maxSpeed.x) ? maxSpeed.x : myVelocity.x;
		myVelocity.x = (myVelocity.x < -maxSpeed.x) ? -maxSpeed.x : myVelocity.x;
		
		myVelocity.y = (myVelocity.y > maxSpeed.y) ? maxSpeed.y : myVelocity.y;
		myVelocity.y = (myVelocity.y < -maxSpeed.y) ? -maxSpeed.y : myVelocity.y;
	}
	
	public Vector2f rotateVector( Vector2f input ) {
		  Vector2f result = new Vector2f(0, 0);
		  double cs = Math.cos(Math.toRadians(myRotation));
		  double sn = Math.sin(Math.toRadians(myRotation));
		  result.x = (float) (input.x * cs - input.y * sn);
		  result.y = (float) (input.x * sn + input.y * cs);
		  return result;
	}
	
	public void decelerate() {
		Vector2f normalizedVelocity = myVelocity.copy();
		normalizedVelocity.x = (float) (normalizedVelocity.getX() * .02);
		normalizedVelocity.y = (float) (normalizedVelocity.getY() * .02);
		normalizedVelocity.negate();
		myVelocity.sub(normalizedVelocity);
	}

	/**
	 * Determines whether an object has collided with another object.
	 * @param theEntity that it is being checked with
	 * @return boolean of whether this Game Object has collided or not
	 */
	public boolean doesCollide(AbstractEntity theEntity) {
		return this.getHitBox().intersects(theEntity.getHitBox());
	}
	
	public boolean checkDistance() {
		if ((myPosition.x > 1000) || (myPosition.y > 1000) || (myPosition.x < -1000) || (myPosition.y < -1000)) {
			return true;
		} return false;
	}
	
	public Rectangle getHitBox() {
		
		Rectangle getHitBox = new Rectangle(
				myPosition.x - (hitBoxSize.x/2),
				myPosition.y - (hitBoxSize.y/2),
				(hitBoxSize.x),
				(hitBoxSize.y));
		return getHitBox;
	}

	public abstract void collide(AbstractEntity theEntity);
	
	public void applyDamage(int theDamage) {
		myHealth -= theDamage;
	}

	public void setPosition(Vector2f thePosition) {
		myPosition.set(thePosition);
	}
	
	public void setHitBoxSize(Point thePoint) {
		hitBoxSize.set(thePoint.getX(), thePoint.getY());
	}

	public boolean isHasCollided() {
		return hasCollided;
	}

	public void setHasCollided(boolean hasCollided) {
		this.hasCollided = hasCollided;
	}
}
