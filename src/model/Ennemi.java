/**
   * @author Olivier BUCAILLE
   * @version 0.0.1.0 du 19/05/2013
   */

package model;


import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;

import parser.ParserNames;

import artficial_intelligence.Unknown;
import artficial_intelligence.artificial_intelligence;

import window.Stage1;

public class Ennemi  extends AbstractAdvancedObject
{
	
	boolean dead = false;
	private int life = 1;
	
	private boolean toto = false;
	
	private artificial_intelligence IA;
	public artificial_intelligence getIA() {
		return IA;
	}

	public void setIA(artificial_intelligence iA) {
		IA = iA;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	private Circle bound;
	public Animation ani;

	public Circle getBound() {
		return bound;
	}

	public void setBound(Circle bound) {
		this.bound = bound;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	




	public Ennemi(String name, String Type, int x, int y, String IA )
	{
		super(Type);
		ani = new Animation(this.getSheet().get(SkinProvider.TYPE_BOOM), 0,0,5,0,true, 100, true);
		bound = new Circle(posX,posY,this.getAnimations().get(SkinProvider.TYPE_RUN).getWidth()/2);
		bound.setRadius(this.getAnimations().get(SkinProvider.TYPE_RUN).getWidth()/4);
		this.name=name;	
		posX = x;
		posY= y;
		IAFounder(IA);
	}
	private void IAFounder(String IA) {
		
	if(IA.equals(ParserNames.IAUnknown))
			this.IA = new Unknown() ;
		
		
		
	}

	public void update()
	{
		IA.update(this);
		
	}
	@Override
	public String getType()
	{
		return "ennemi";
	}

	public void setToto(boolean toto) {
		this.toto = toto;
	}

	

	public boolean isToto() {
		// TODO Auto-generated method stub
		return toto;
	}

}
