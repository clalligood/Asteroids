package game.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Bullet extends AbstractEntity {

	private final static String imageString = "/assets/img/bullets.png";
	
	private static Vector2f bulletVelocity = new Vector2f(0, 2.5f);
	
	private static Vector2f bulletMaxSpeed = new Vector2f(2.5f, 2.5f);

	private int myHealth = 20;
	
	public Bullet(Vector2f thePosition, float theRotation)
			throws SlickException {
		super(imageString, thePosition, bulletVelocity);

		this.rotate(theRotation);
		this.setHitBoxSize(new Point(5, 5));
		this.setMaxSpeed(new Vector2f(bulletMaxSpeed.copy()));
		
		displayMult = 30;
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) {
		
	}

	@Override
	public int update(GameContainer gc, StateBasedGame sbg, int delta) {
		
		this.setVelocity(rotateVector(new Vector2f(bulletVelocity.copy())));
		
		myPosition.add(myVelocity);
		
		if (this.checkDistance()) {
			myHealth = 0;
		}
		
		myHealth -= .0000001f;

		if (myHealth <= 0) {
			return 0;
		} else {
			return 1;
		}
		
	}

	@Override
	public void collide(AbstractEntity theEntity) {
		if(theEntity instanceof Asteroid) {
			applyDamage(100);
			theEntity.applyDamage(100);
		}
	}

}
