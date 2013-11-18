/**
   * @author Olivier BUCAILLE
   * @version 0.0.1.0 du 19/05/2013
   */

package model;

import org.newdawn.slick.geom.Circle;

public class Weapon extends AbstractAdvancedObject {

	public static String UP="up";

	public static String DOWN="down";

	public static String LEFT="left";

	public static String RIGHT="right";
	
	private String name;
	public String direction;
	
	public boolean state = false;
	private Circle bound;
	private AbstractAdvancedObject launcher;
	
	
	public Circle getBound() {
		return bound;
	}
	public void setBound(Circle bound) {
		this.bound = bound;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Weapon(String name, String Type, int x, int y, AbstractAdvancedObject launcher, String direction)
	{
	
		super(Type);
		bound = new Circle(posX,y,posY,8);
		bound.setRadius(this.getAnimations().get(SkinProvider.TYPE_RUN).getWidth()/2);
		this.name=name;	
		posX = x;
		posY= y;
		state=false;
		this.launcher = launcher;
		this.direction = direction;
	}

	public AbstractAdvancedObject getLauncher() {
		return launcher;
	}
	public void setLauncher(AbstractAdvancedObject launcher) {
		this.launcher = launcher;
	}
	public void update()
	{
		 bound.setLocation(posX, posY);
	}
	
	@Override
	public String getType()
	{
		return "weapon";
	}
	
	
}
