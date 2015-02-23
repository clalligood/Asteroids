package game.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Bullet extends AbstractEntity {

	private final static String imageString = "/assets/img/bullets.png";
	
	private static Vector2f bulletVelocity = new Vector2f(0, 1.5f);

	private int myHealth = 20;
	
	public Bullet(Vector2f thePosition, float theRotation)
			throws SlickException {
		super(imageString, thePosition, bulletVelocity);
		
		this.rotate(theRotation);
		bulletVelocity = this.rotateVector(bulletVelocity);
		
		displayMult = 30;
		
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) {
		
	}

	@Override
	public int update(GameContainer gc, StateBasedGame sbg, int delta) {
		
		myPosition.add(myVelocity);
		
		if (this.checkDistance()) {
			myHealth = 0;
		}
		
		if (myHealth <= 0) {
			return 0;
		} else {
			return 1;
		}
	}

}
