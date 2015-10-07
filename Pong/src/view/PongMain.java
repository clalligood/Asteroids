package view;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The 
 * @author Tony, Mehgan, Vlad, Justin, Collin
 */
public class PongMain extends StateBasedGame {
	
	/** The Game Name. */
	private static final String MY_GAME_NAME = "Pong";
	
	private static final int MY_MAX_FPS = 60;

	/** The Menu State's ID. */
	public static final int MY_MENU_STATE_ID = 0;
	
	/** The Game State's ID. */
	public static final int MY_GAME_STATE_ID = 1;

	/** The Game Won State's ID. */
	public static final int MY_GAME_WON_ID = 1;

	/** The Game Container. */
	private static AppGameContainer myApp;

	/**
	 * Initializes the Pong 
	 * @param name
	 */
	public PongMain(String theName) {
		super("Pong");
		
	}

	public static void main(String[] args) throws SlickException {
		myApp = new AppGameContainer(new PongMain(MY_GAME_NAME));
		myApp.setTargetFrameRate(MY_MAX_FPS);
		myApp.setShowFPS(false);
		myApp.start();
	}

	/**
	 * Initializes the state of the game.
	 */
	@Override
	public void initStatesList(GameContainer theGameContainer) throws SlickException {
		this.addState(new MenuState(MY_MENU_STATE_ID));
		this.addState(new GameState(MY_GAME_STATE_ID));
		this.enterState(MY_MENU_STATE_ID);		
	}

}
