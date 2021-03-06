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
	private static final int shotBaseTime = 100;
	
	private int myLife = 100;
	
	private boolean hasShot = false;
	
	private int myShotTimer = 0;
	
	private boolean isGameOver = false;
	
	public Player() throws SlickException {
		super(imageString, thePosition, theVelocity);
		displayMult = 1;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) {
		
	}

	@Override
	public int update(GameContainer gc, StateBasedGame sbg, int delta) {
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_UP)) {
			this.applyForce(rotateVector(new Vector2f(0, 0.025f)));
		} else if (input.isKeyDown(Input.KEY_DOWN)) {
			this.decelerate();
			//this.applyForce(rotateVector(new Vector2f(0, -0.025f)));
		}
		
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			this.rotate(2f);
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			this.rotate(-2f);
		}
		
		if (input.isKeyDown(Input.KEY_SPACE)) {
			this.shoot();
		}
		

		myPosition.add(myVelocity);
		if (myShotTimer > 0) {
			myShotTimer -= 5;
		}
		if (myLife <= 0) {
			return 0;
		}
		else if (hasShot) {
			hasShot = false;
			return 2;
		}
		else {
			return 1;
		}
	}
	
	public void shoot() {
		if (myShotTimer <= 0) {
			hasShot = true;
			myShotTimer = shotBaseTime;
		}
	}

	@Override
	public void collide(AbstractEntity theEntity) {
		if (theEntity instanceof Asteroid){
			isGameOver = true;
		}
		
	}
	
	public boolean isGameOver() {
		return isGameOver;
	}

	public void resetGame() {
		isGameOver = false;
	}
}
