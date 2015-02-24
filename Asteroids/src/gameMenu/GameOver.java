package gameMenu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


public class GameOver extends org.newdawn.slick.state.BasicGameState {

	private static int myID = 2;
	
	private String printString = "Game Over. Too Bad, So Sad.\nPress Enter to Try Again";
	
	public GameOver(int theID) {
		myID = theID;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		g.drawString(printString, 150, 150);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		if (input.isKeyDown(Input.KEY_ENTER)) {
			sbg.enterState(1);
		}
	}

	@Override
	public int getID() {
		return myID;
	}

}