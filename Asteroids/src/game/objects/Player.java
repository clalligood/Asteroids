package game.objects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends AbstractEntity {
		
	private static String imageString = "/assets/img/ship.png";
	private static Vector2f thePosition = new Vector2f(400, 300);
	private static Vector2f theVelocity = new Vector2f(0, 0);
	
	public Player() throws SlickException {
		super(imageString, thePosition, theVelocity);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) {
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_UP)) {
			this.applyForce(rotateVector(new Vector2f(0, 0.025f)));
		} else if (input.isKeyDown(Input.KEY_DOWN)) {
			this.applyForce(rotateVector(new Vector2f(0, -0.025f)));
		}
		
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			this.rotate(2f);
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			this.rotate(-2f);
		}
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
			this.forceInvert();
		}
		
		myPosition.add(myVelocity);
	}

}
