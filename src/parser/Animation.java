package parser;

import org.lwjgl.Sys;

public class Animation {
	public int duration; 
	
	public int PosBeginX; 
	public int PosBeginY; 
	public int PosFinishX; 
	public int PosFinishY; 
	
	public Sprite sprite;
	
	public Animation(Sprite sprite,int duration, int PosBeginX, int PosBeginY, int PosFinishX, int PosFinishY)
	{
		this.duration = duration;
		this.PosBeginX = PosBeginX; 
		this.PosBeginY = PosBeginY; 
		this.PosFinishX = PosFinishX; 
		this.PosFinishY = PosFinishY; 
		this.sprite = sprite;
	}
	
	public Animation(Sprite sprite)
	{
		
		this.sprite = sprite;
	}
	
	public void ToString()
	{
		System.out.println("Animation");
		System.out.println("duration" + PosBeginX);
		System.out.println("duration" + PosBeginY);
		System.out.println("duration" + PosFinishX);
		System.out.println("duration" + PosFinishY);
	
	
	}
	

}
