package model;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public abstract class AbstractEntity {

	/** The Shape of the Game Object. */
	protected Shape myShape;
	
	/** The Velocity/Speed of the Game Object. */
	protected Vector2f myVelocity;

	public AbstractEntity(Shape theShape) {
		myShape = theShape;
		myVelocity = new Vector2f(0, 0);
	}
	
	/**
	 * Draws the shape for every frame.
	 * 
	 * @param theGameContainer
	 * @param theStateBasedGame
	 * @param theGraphics
	 * @throws SlickException
	 */
	public void render(GameContainer theGameContainer,
			StateBasedGame theStateBasedGame, Graphics theGraphics)
			throws SlickException {
		theGraphics.setColor(Color.white);
		theGraphics.fill(myShape);
	}

	/**
	 * Updates the Game Objects position and speed. It contains
	 * all of the logic that manipulates each Game Object.
	 * 
	 * @param theGameContainer
	 * @param theStageBasedGame
	 * @param theGraphics
	 * @throws SlickException
	 */
	public abstract void update(GameContainer theGameContainer,
			StateBasedGame theStateBasedGame, int theGraphics);


	
}
