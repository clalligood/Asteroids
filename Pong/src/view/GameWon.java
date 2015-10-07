package view;

import model.Paddle;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameWon extends BasicGameState {

	/** The ID of the MenuState. */
	private int myID;
	/** The SelectedOption. */
	private int mySelectedOption = 0;
	/** The Player Who Won. */
	private int myPlayerWon = 0;
	
	/** The StartGameString. */
	private static final String MY_GAME_OVER_STRING = "Game Over. Press enter to play again.";

	/**
	 * Initializes the MenuState part of the game.
	 * @param theID being passed to this GameState.
	 */
	public GameWon(int theID) {
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
		theGraphics.setColor(Color.green);
		centerText(MY_GAME_OVER_STRING, theGameContainer, theGraphics, 160);
		centerText("Congratulations Player" + myPlayerWon + "!", theGameContainer,
				theGraphics, 170);
		centerText("YOU WIN!!!!", theGameContainer,
				theGraphics, 180);
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
			theStateBasedGame.enterState(PongMain.MY_GAME_STATE_ID);
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

	/**
	 * Sets Player who won.
	 */
	public void setPlayer(int theWinner) {
		myPlayerWon = theWinner;
	}
}
