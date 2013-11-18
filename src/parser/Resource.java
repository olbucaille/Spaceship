package parser;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Resource {
	
	public String Type;
	
	public ArrayList<Sprite> listSprite;
	
	public Resource(String type)
	{
		Type = type;
		listSprite = new ArrayList<Sprite>();
	}
	

}
