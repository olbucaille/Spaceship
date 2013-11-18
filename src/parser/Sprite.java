package parser;

public class Sprite {
	public String NameType; 
	public String Path;
	public int SizeX; 
	public int SizeY;
	
	public String AlphaColor; 
	public Animation animation;
	
	public Sprite(String NameType, String Path, int SizeX, int SizeY, String AlphaColor)
	{
		this.NameType = NameType;
		this.Path = this.Path;
		this.SizeX = SizeX;
		this.SizeY = SizeY;
		this.AlphaColor = AlphaColor;
		this.animation = new Animation(this);	
		
	}
	public void ToString()
	{
		System.out.println("Sprite");
		System.out.println("nametype" + NameType);
		System.out.println("Path" + Path);
		System.out.println("sizeY" + SizeX);
		System.out.println("sizeX" + SizeY);
		System.out.println("AlphaColor" + AlphaColor);
		animation.ToString();
	}
	

}
