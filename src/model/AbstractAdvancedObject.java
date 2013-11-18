/**
   * @author Olivier BUCAILLE
   * @version 0.0.1.0 du 19/05/2013
   */

package model;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;



/**
 * this class allow you to create a "smart object" who can interact in a game level
 */
public abstract class AbstractAdvancedObject {

	 /**
     * all the pictures and sprite for displaying the object
     */
	private HashMap<String,SpriteSheet> sheet;
	
	 /**
     * all the animation needed for the Object
     */
	public HashMap<String, Animation> animations;
	

	/**
	 * name for the character 
	 */
	protected String name;
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}


	protected int posX;
	public int getPosX() {
		return posX;
	}



	public void setPosX(int posX) {
		this.posX = posX;
	}



	public int getPosY() {
		return posY;
	}



	public void setPosY(int posY) {
		this.posY = posY;
	}


	protected int posY;

	 /**
     * @param void
     * @return the Hashmap with the different sheets/sprites/pictures of the Object
     */
	public HashMap<String, SpriteSheet> getSheet() {
		return sheet;
	}

	

	 /**
    * @param void
    * @return the Hashmap with the different animation of the Object
    */
	public HashMap<String, Animation> getAnimations() {
		return animations;
	}
	

	 /**
    * @param type of the object : character,...
    * @return basic constructor
    */
	public AbstractAdvancedObject(String Type)
	{
		SkinProvider sp = SkinProvider.GetInstance();
		this.sheet = sp.SheetProvider(Type);	
		animations = sp.AnimationProvider(Type);
		
	}


	 /**
    * @param 
    * @return String, the type of the Object : character,....
    */
	public String getType()
	{
		return "unkown";
	}
	


	

	
}
