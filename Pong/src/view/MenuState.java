package view;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState {

	/** The ID of the MenuState. */
	private int myID;
	/** The menu options. */
	private String[] myMenuOptions = {"Start Game", "Options", "Exit Game"};
	/** The SelectedOption. */
	private int mySelectedOption = 0;

	/** The StartGameString. */
	private static final String MY_START_GAME_STRING = "Press Enter to Start the Game.";

	/**
	 * Initializes the MenuState part of the game.
	 * @param theID being passed to this GameState.
	 */
	public MenuState(int theID) {
		myID = theID;
	}
	
	/**
	 * {@inheritDoc}
	 */	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer theGameContainer,
			StateBasedGame theStateBasedGame, Graphics theGraphics)
			throws SlickException {
		String gameTitle = "Welcome to Pong";
		theGraphics.setColor(Color.green);
		centerText(gameTitle, theGameContainer, theGraphics, 150);
		theGraphics.setColor(Color.white);
		//Creates the game menu.
		for(int i = 0; i < myMenuOptions.length; i++) {
			if (i == mySelectedOption) {
				theGraphics.setColor(Color.blue);
			} else {
				theGraphics.setColor(Color.white);
			}
			centerText(myMenuOptions[i], theGameContainer, theGraphics, (200 + 20 * i) );
		}
	}

	/**
	 * Updates the GameState.
	 */
	@Override
	public void update(GameContainer theGameContainer,
			StateBasedGame theStateBasedGame,int theDelta)
			throws SlickException {
		
		// Gets the Input from the Game Container
		Input input = theGameContainer.getInput();

		// If enter is pressed, enter the GameState's state.
		if (input.isKeyDown(Input.KEY_ENTER)) {
			switch(mySelectedOption) {
			case 0:
				theStateBasedGame.enterState(PongMain.MY_GAME_STATE_ID);
			case 1:
			case 2:
			default:
					
				break;
			}
		}
		
		if (input.isKeyPressed(Input.KEY_DOWN)) {
			if (mySelectedOption < myMenuOptions.length - 1)
				mySelectedOption++;
			else
				mySelectedOption = 0;
		} else if (input.isKeyPressed(Input.KEY_DOWN)) {
			if (mySelectedOption < myMenuOptions.length - 1)
				mySelectedOption++;
			else
				mySelectedOption = 0;
		}
		
	}

	/**
	 * Returns the id of the game state.
	 * @return myId
	 */
	@Override
	public int getID() {
		return myID;
	}

	/**
	 * Centers the Text of the Game Menu.
	 * @param theString being written to the main screen.
	 * @param theGameContainer
	 * @param theGraphic
	 * @param theVerticalLocation of the written text.
	 */
	public void centerText(String theString, GameContainer theGameContainer,
			Graphics theGraphic,
			float theVerticalLocation){
		theGraphic.drawString(theString,
				(theGameContainer.getWidth()
						- theGraphic.getFont().getWidth(theString)) / 2.0f,
						theVerticalLocation);
		
	}

}
