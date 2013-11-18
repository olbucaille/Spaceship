/**
   * @author Olivier BUCAILLE
   * @version 0.0.1.0 du 19/05/2013
   */

package window;


import java.awt.RenderingHints.Key;
import java.util.ArrayList;
import java.util.List;
import model.Ennemi;
import model.SkinProvider;
import model.Weapon;
import model.character;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import parser.ParserLevel;
import parser.Step;


public class Stage1 implements GameState{

	public static final int ID = 1;

	public int X=0,Y=0;
	character maincharacter;
	List<Ennemi> monster= new ArrayList<Ennemi>();
	public static List<Weapon> missiles = new ArrayList<Weapon>(); 
	Music song;
	Sound shot,boom;
	ArrayList<Step> listestep;
	int nbStep = 0;
	
double x=0;

int y=0;


	public int getID() {return ID;}  

	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		listestep = new ArrayList<Step>();
		maincharacter = new character("maincharacter",SkinProvider.BASIC_CHARACTER, 150,150);
		/*monster.add( new Ennemi("tentaculeman",SkinProvider.BASIC_ENNEMI, 500,150));
		monster.add( new Ennemi("tentaculeman",SkinProvider.BASIC_ENNEMI, 600,150));
		monster.add( new Ennemi("tentaculeman",SkinProvider.BASIC_ENNEMI, 400,300));
		monster.add( new Ennemi("tentaculeman",SkinProvider.BASIC_ENNEMI, 500,300));
		monster.add( new Ennemi("tentaculeman",SkinProvider.BASIC_ENNEMI, 600,300));
		monster.add( new Ennemi("tentaculeman",SkinProvider.BASIC_ENNEMI, 700,300));*/
		
		listestep = ParserLevel.levelprovider();
		for(parser.Ennemi m : listestep.get(nbStep).ennemi)
		{
			monster.add(m.intanciation());
		}
		 song = new Music("./media/grieg.wav");
		
		 
		 shot = new Sound("./media/gun.wav");
		 boom = new Sound("./media/boom.wav");
		
		 song.loop();
		 song.setVolume((float) 0.5);
	}  

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{  
		x= x-0.15;
		
		
		if(x<-750)
			x=0;
		//g.drawImage(new Image("./media/space.png").getScaledCopy((float) 1.2), 0,0 );
		g.setBackground(Color.black);
		g.drawImage(new Image("./media/space.png", Color.black).getScaledCopy((float) 1.25),0+Math.round(x), y);
		g.drawImage(new Image("./media/space.png", Color.black).getScaledCopy((float) 1.25),500+Math.round(x), y);
		
		

		
		g.drawString("SpaceShip", 100, 50);

		if(maincharacter.isDead()&& maincharacter.getAnimations().get("boom").getFrame()==5)
		{
		//	monster.get(i).getAnimations().get(SkinProvider.TYPE_BOOM).setCurrentFrame(0);
			
		
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.exit(0);
			
		}
		if(monster.isEmpty())
		{
			nbStep ++;
			
			
			if(listestep.size()>=2)
			{
				listestep.remove(0);
				System.out.println(listestep.size());
				nbStep++;
				for(parser.Ennemi m : listestep.get(0).ennemi)
				{
					monster.add(m.intanciation());
				}
			}
			else
				
			g.drawString("YOU ARE THE BEST,CAPTAIN", 300, 450);
			
		}
		
		if(!maincharacter.isDead())
		maincharacter.getAnimations().get("run").draw(maincharacter.getPosX()-30,maincharacter.getPosY()-25);
		else
		{
			maincharacter.getAnimations().get("boom").draw(maincharacter.getPosX()-30,maincharacter.getPosY()-25);
			g.drawString("YOU ARE A LOOSER, BASTARD", 300, 450);
		}
		if(monster!= null)
		{
			
			 for (Ennemi m  : monster) {
					if(!m.isDead())
						m.getAnimations().get("run").draw(m.getPosX(), m.getPosY(),50,50);			
					else
						m.ani.draw(m.getPosX(), m.getPosY());	
			}
		
			
		}

		if(!missiles.isEmpty())	{

			for (  Weapon missile : missiles) {

				if(missile != null )
				{

					missile.getAnimations().get("run").draw(missile.getPosX(), missile.getPosY());

				}

			}

		}
		

	}  
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {  
		Input input = container.getInput();
		maincharacter.setPosX(input.getMouseX());
		maincharacter.setPosY(input.getMouseY());
		checkWeapon( input);
		checkMonster();
		Collision();
		maincharacter.update();
	


	}

	private void checkMonster()
	{
		for(int i=0; i<monster.size();i++)
		{
			monster.get(i).update();
			if(monster.get(i).isDead()&& monster.get(i).ani.getFrame()==5)
			{
			//	monster.get(i).getAnimations().get(SkinProvider.TYPE_BOOM).setCurrentFrame(0);
				monster.remove(i);
				
			}
			
			
		}
	}

	private void Collision()
	{		
		for(int i= 0; i<missiles.size(); i++)
		{
			for(Ennemi m:monster)
			{
			
			if(!m.isDead())
				if(i<missiles.size())
				if(!missiles.isEmpty())
				if(! (missiles.get(i)== null) ||missiles.isEmpty())
				{
				
					if(!missiles.isEmpty())
					if(missiles.get(i).getBound().intersects(m.getBound()) && m.isDead()==false && missiles.get(i).getLauncher().getType().equals("character"))
					{
						m.setLife(m.getLife()-1);
					
						if(m.getLife()==0)
						{
							m.setDead(true);
							boom.play(1,(float) 0.2);							
							
						}
						missiles.remove(i);
						break;
						
					}
					
					
					
				}
			}
			if(i<missiles.size())
				if(!missiles.isEmpty())
				if(! (missiles.get(i)== null) ||missiles.isEmpty())
			if(missiles.get(i).getBound().intersects(maincharacter.getBound()) && maincharacter.isDead()==false && missiles.get(i).getLauncher().getType().equals("ennemi")&& maincharacter.getPosX()>=5)
			{
				System.out.println(maincharacter.getPosX());
				maincharacter.setDead(true);
				maincharacter.getAnimations().get("boom").stopAt(5);
				boom.play(1,(float) 0.01);							
		
			break;
				
			}
		}
			for(Ennemi m:monster)
			{
			
			if(!m.isDead())
				
			
					if(maincharacter.getBound().intersects(m.getBound()) && maincharacter.isDead()==false )
					{
							maincharacter.setDead(true);
							maincharacter.getAnimations().get("boom").stopAt(5);
							boom.play(1,(float) 0.01);							
					
						break;
						
					}
					
				
			}

		
		}
		
		
		

	

	int max(int a, int b)
	{
		if(a>b)
			return a;
		else 
			return b;
	}
	int min(int a, int b)
	{
		if(a>=b)
			return b;
		else 
			return a;	
	}

	private void checkWeapon(Input input)
	{
	
		
			
				if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
				{
					missiles.add( new Weapon("missile",SkinProvider.BASIC_WEAPON,maincharacter.getPosX()+20, maincharacter.getPosY(),maincharacter,Weapon.RIGHT));

					//missiles.add( new Weapon("missile",SkinProvider.BASIC_WEAPON,maincharacter.getPosX(), maincharacter.getPosY()+10));
					//missiles.add( new Weapon("missile",SkinProvider.BASIC_WEAPON,maincharacter.getPosX(), maincharacter.getPosY()-10));
					//missiles.add( new Weapon("missile",SkinProvider.BASIC_WEAPON,maincharacter.getPosX(), maincharacter.getPosY()));
					//missiles.add( new Weapon("missile",SkinProvider.BASIC_WEAPON,maincharacter.getPosX()-20, maincharacter.getPosY()));
					
							shot.play(1,(float) 0.2);
				
					
				}
			
		
			
			

		
		for (int i = 0; i<missiles.size();i++) {
			
			missiles.get(i).update();
			if(missiles.size()>i+1)
			if(missiles.get(i).getPosX()>850 || missiles.get(i).getPosX()< 0 || missiles.get(i).getPosY()>650 ||missiles.get(i).getPosY()<0 )
				missiles.remove(i);
			if( missiles.get(i).state == false)
			{
				missiles.get(i).state=true;
			}
			if(missiles.get(i) != null && missiles.get(i).state)
			{
				if(missiles.get(i).direction == Weapon.LEFT)
					missiles.get(i).setPosX(missiles.get(i).getPosX()-7);
				if(missiles.get(i).direction == Weapon.RIGHT)
					missiles.get(i).setPosX(missiles.get(i).getPosX()+7);
				if(missiles.get(i).direction == Weapon.UP)
					missiles.get(i).setPosX(missiles.get(i).getPosY()+7);
				if(missiles.get(i).direction == Weapon.DOWN)
					missiles.get(i).setPosX(missiles.get(i).getPosY()-7);
			}
		}



	}

	@Override
	public void mouseClicked(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseDragged(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseMoved(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseWheelMoved(int arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void inputEnded() {
		// TODO Auto-generated method stub

	}
	@Override
	public void inputStarted() {
		// TODO Auto-generated method stub

	}
	@Override
	public boolean isAcceptingInput() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setInput(Input arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyPressed(int arg0, char arg1) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub

	}
	@Override
	public void controllerButtonPressed(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}
	@Override
	public void controllerButtonReleased(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}
	@Override
	public void controllerDownPressed(int arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void controllerDownReleased(int arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void controllerLeftPressed(int arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void controllerLeftReleased(int arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void controllerRightPressed(int arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void controllerRightReleased(int arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void controllerUpPressed(int arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void controllerUpReleased(int arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public boolean isRenderPaused() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isUpdatePaused() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void pauseRender() {
		// TODO Auto-generated method stub

	}
	@Override
	public void pauseUpdate() {
		// TODO Auto-generated method stub

	}
	@Override
	public void setRenderPaused(boolean arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void setUpdatePaused(boolean arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void unpauseRender() {
		// TODO Auto-generated method stub

	}
	@Override
	public void unpauseUpdate() {
		// TODO Auto-generated method stub

	}
	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}
	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		// TODO Auto-generated method stub

	}  

}
