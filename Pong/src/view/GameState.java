package view;

import model.Ball;
import model.Paddle;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameState extends BasicGameState {

	private int myID;
	
	/** The first player's paddle. */
	private Paddle myPlayer1;
	
	/** The second player's paddle. */	
	private Paddle myPlayer2;
	
	/** The ball. */
	private Ball myBall;
	
	public GameState(int theID) {
		myID = theID;
	}
	
	/**
	 * Initializes the game container by creating new Paddles and a new Ball.
	 */
	@Override
	public void init(GameContainer theGameContainer, StateBasedGame arg1)
			throws SlickException {
		myPlayer1 = new Paddle(new Vector2f(0 + 10, theGameContainer.getHeight() / 2), Input.KEY_W, Input.KEY_S);
		myPlayer2 = new Paddle(new Vector2f(theGameContainer.getWidth() - 20, theGameContainer.getHeight() / 2), Input.KEY_UP, Input.KEY_DOWN);
		myBall = new Ball(new Vector2f(theGameContainer.getWidth() / 2, theGameContainer.getHeight() / 2));
				
	}

	@Override
	public void render(GameContainer theGameContainer,
			StateBasedGame theStateBasedGame, Graphics theGraphics)
			throws SlickException {
		myPlayer1.render(theGameContainer, theStateBasedGame, theGraphics);
		myPlayer2.render(theGameContainer, theStateBasedGame, theGraphics);
		myBall.render(theGameContainer, theStateBasedGame, theGraphics);
		theGraphics.drawString("Pong", theGameContainer.getWidth()/2-50, 485);
		theGraphics.drawString("" + myPlayer1.getScore(), theGameContainer.getWidth()/2 - 60, 15);
		theGraphics.drawString("" + myPlayer2.getScore(), theGameContainer.getWidth()/2 + 50, 15);
		
	}

	@Override
	public void update(GameContainer theGameContainer,
			StateBasedGame theStateBasedGame, int theDelta)
			throws SlickException {
		myPlayer1.update(theGameContainer, theStateBasedGame, theDelta);
		myPlayer2.update(theGameContainer, theStateBasedGame, theDelta);
		myBall.update(theGameContainer, theStateBasedGame, theDelta);
		switch(myBall.checkPoint()) {
		case 1:
			myPlayer2.addScore();
			break;
		case 2:
			myPlayer1.addScore();
			break;
		default:
			break;
		}
		
		if (myPlayer1.getScore() >= 5) {
			((GameWon) theStateBasedGame.getState(PongMain.MY_GAME_WON_ID)).setPlayer(1);
			theStateBasedGame.enterState(PongMain.MY_GAME_WON_ID);
		} else if (myPlayer2.getScore() >= 5) {
			((GameWon) theStateBasedGame.getState(PongMain.MY_GAME_WON_ID)).setPlayer(2);
			theStateBasedGame.enterState(PongMain.MY_GAME_WON_ID);			
		}
	}

	public Paddle getMyPlayer1() {
		return myPlayer1;
	}

	public Paddle getMyPlayer2() {
		return myPlayer2;
	}

	@Override
	public int getID() {
		return myID;
	}

}
