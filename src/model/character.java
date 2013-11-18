/**
 * @author Olivier BUCAILLE
 * @version 0.0.1.0 du 19/05/2013
 */

package model;

import org.newdawn.slick.geom.Circle;

/**
 * extends AbstractAdvancedObject and represent a character the player can use and control
 */
public class character extends AbstractAdvancedObject {



	/**
	 *define if the character is dead or not
	 */
	boolean dead = false;


	/**
	 *circle to control and manage the collisions
	 */
	private static Circle bound;


	/**
	 * @param void
	 * @return the circle to check the collisions
	 */
	public static Circle getBound() {
		return bound;
	}







	/**
	 * @param 
	 * @return get the name of the new character
	 */
	public String getName() {
		return name;
	}



	/**
	   * @param void
	   * @return return boolean saying if the character is dead or not
	   */
	public boolean isDead() {
		return dead;
	}


	/**
	   * @param boolean to say if the character is dead
	   * @return void
	   */
	public void setDead(boolean dead) {
		this.dead = dead;
	}	
	

	/**
	 * basic constructor
	   * @param name , type of character, Xposition, Yposition
	   * 
	   */
	public character(String name, String Type, int x, int y)
	{
		super(Type);
		bound = new Circle(posX,posY,this.getAnimations().get(SkinProvider.TYPE_RUN).getWidth()/2);
		bound.setRadius(this.getAnimations().get(SkinProvider.TYPE_RUN).getWidth()/4);
		this.name=name;

		posX = x;
		posY= y;
	}




	public void update()
	{



		bound.setLocation(posX, posY);
	}

	@Override
	public String getType()
	{
		return "character";
	}
}
