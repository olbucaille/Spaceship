/**
   * @author Olivier BUCAILLE
   * @version 0.0.1.0 du 19/05/2013
   */

package window;
import java.lang.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class window extends StateBasedGame  
{
	
	private GameState game; 

	public window()
	{
		super("SpaceShip");
	
		}

	@Override  
	public void initStatesList(GameContainer container) throws SlickException   
	{  
		if (container instanceof AppGameContainer) {
		}  
		game = new Stage1(); //game
		container.setShowFPS(true);
		addState(game);    // add GameState to wrapper ! 
	
	}  
	
	public static void main(String[] args)   
	{  
		try  
		{  
			AppGameContainer container = new AppGameContainer(new window());  
			container.setDisplayMode(1250,650,false);
			container.setFullscreen(false);
			container.setTargetFrameRate(20);
			container.setMouseGrabbed(false);
			
			container.start();  
		}                         
		catch (SlickException e) {e.printStackTrace();}  
	}  
}


