import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.state.StateBasedGame;


public class Asteroids extends StateBasedGame {

	final static String gameName = "Asteroids";
	final static boolean fullscreen = false;
	final static boolean showFPS = false;
	final static int targetFPS = 60;
	final static Point windowSize = new Point(800, 600);
	
	private static AppGameContainer app;
		
	public Asteroids(String name) {
		super(name);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		
	}

	public static void main(String[] args) throws SlickException {
		app = new AppGameContainer(new Asteroids(gameName));
		app.setDisplayMode((int)windowSize.getX(), (int)windowSize.getY(), fullscreen);
		app.setShowFPS(showFPS);
		app.setTargetFrameRate(targetFPS);
		app.start();
	}

}
