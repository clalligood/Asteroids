import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;


public class Asteroids extends StateBasedGame {

	final static String gameName = "Asteroids";
	
	static AppGameContainer app;
	
	final static Point windowSize = new Point(800, 600);
	
	public Asteroids(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		
	}

	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer(new Asteroids(gameName));
		app.setDisplayMode((int)windowSize.getX(), (int)windowSize.getY(), true);
		app.setShowFPS(false);
		app.setTargetFrameRate(60);
		app.start();
	}

}
