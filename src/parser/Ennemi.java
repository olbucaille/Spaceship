package parser;

import model.SkinProvider;

public class Ennemi {
	
	public String Type;
	public int posX; 
	public int posY;
	public String IARef;
	
	
	public  Ennemi(String type, int posX, int posY, String IAref)
	{
		this.posY = posY;
		this.posX = posX; 
		this.Type = type; 
		this.IARef =  IAref;
	}
	
	public model.Ennemi intanciation()
	{
		return new model.Ennemi("tentaculeman",Type, posX,posY,IARef);
	}

}
