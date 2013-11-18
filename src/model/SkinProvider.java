/**
   * @author Olivier BUCAILLE
   * @version 0.0.1.0 du 19/05/2013
   */

package model;


import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public  class SkinProvider {
	
	public static String BASIC_CHARACTER = "basic_character";
	public static String BASIC_ENNEMI = "basic_ennemi";
	public static String BASIC_WEAPON = "basic_weapon";
	public static String GUN_WEAPON = "gun_weapon";
	public static String ALIEN_WEAPON = "alien_weapon";
	
	
	public static String TYPE_RUN = "run";
	public static String TYPE_BOOM = "boom";
	public static String TYPE_LAUNCH = "launch";
	public static String TYPE_MAIN = "main";
	

	
	private static SkinProvider instance = null;
	
	private static Map<String, HashMap<String, SpriteSheet>> TypeSheetList = null;
	private static Map<String, HashMap<String, Animation>> TypeAnimationList = null;
	
	private SkinProvider()
	{
		TypeSheetList = new HashMap<String, HashMap<String,SpriteSheet>>();
		TypeAnimationList = new HashMap<String, HashMap<String,Animation>>();
		
		try {
			loadBasicsSheetSkinsForCharacter();
			loadBasicsSheetSkinsForEnnemis();
			loadBasicsSheetSkinsForWeapon();
			
			loadBasicsAnimationSkinsForCharacter();
			loadBasicsAnimationSkinsForEnnemis();
			loadBasicsAnimationSkinsForWeapon();
		} catch (SlickException e) {
			System.exit(0);
		}
	}
	
	public static SkinProvider GetInstance()
	{
		if(instance == null)
			instance = new SkinProvider();
		
		return instance;		
	}
	
	public  HashMap<String, SpriteSheet> SheetProvider( String Type)
	{
		if(TypeSheetList.containsKey((String) Type))
			return TypeSheetList.get((String)Type);	
		else 
			return null;
		
	}
	
	public  HashMap<String, Animation> AnimationProvider( String Type)
	{
		if(TypeAnimationList.containsKey((String) Type))
			return new  HashMap<String, Animation>( TypeAnimationList.get((String)Type));	
		else 
		
			return null;
		
			
		
	}
	
	private static void loadBasicsSheetSkinsForCharacter() throws SlickException
	{			
		HashMap<String, SpriteSheet> basicc =  new HashMap<String, SpriteSheet>();
		basicc.put(TYPE_RUN, new SpriteSheet("./media/character2.png", 65, 50,org.newdawn.slick.Color.black));
		basicc.put(TYPE_BOOM, new SpriteSheet("./media/boom1.png", 64, 70,org.newdawn.slick.Color.white));
		
		TypeSheetList.put(SkinProvider.BASIC_CHARACTER,basicc);		
	}
	
	private static void loadBasicsSheetSkinsForEnnemis() throws SlickException
	{
		HashMap<String, SpriteSheet> basicE =  new HashMap<String, SpriteSheet>();
		basicE.put(TYPE_RUN,new SpriteSheet("./media/monster1.png", 114, 104,org.newdawn.slick.Color.white));
		basicE.put(TYPE_BOOM, new SpriteSheet("./media/boom1.png", 64, 70,org.newdawn.slick.Color.white));
		
		TypeSheetList.put(SkinProvider.BASIC_ENNEMI,basicE);		
	}    
	
	private static void loadBasicsSheetSkinsForWeapon() throws SlickException
	{
		HashMap<String, SpriteSheet> basicW =  new HashMap<String, SpriteSheet>();
		basicW.put(TYPE_MAIN,new SpriteSheet("./media/missile-sprite.png", 92, 24, org.newdawn.slick.Color.white));
		HashMap<String, SpriteSheet> Gun =  new HashMap<String, SpriteSheet>();
		Gun.put(TYPE_MAIN,new SpriteSheet("./media/gun.png", 5, 7, org.newdawn.slick.Color.white));
		HashMap<String, SpriteSheet> alienGun =  new HashMap<String, SpriteSheet>();
		alienGun.put(TYPE_MAIN,new SpriteSheet("./media/aliengun.png", 5, 7, org.newdawn.slick.Color.white));
		
		
		TypeSheetList.put(SkinProvider.BASIC_WEAPON,basicW);	
		TypeSheetList.put(SkinProvider.ALIEN_WEAPON,alienGun);	
		TypeSheetList.put(SkinProvider.GUN_WEAPON,Gun);	
	}
	
	
	
	
	
	private static void loadBasicsAnimationSkinsForCharacter() throws SlickException
	{			
		HashMap<String, Animation> basicc =  new HashMap<String, Animation>();
		basicc.put(TYPE_RUN, new Animation(TypeSheetList.get((String) BASIC_CHARACTER).get(TYPE_RUN), 0,0,3,0,true, 100, true));
		basicc.put(TYPE_BOOM,new Animation(TypeSheetList.get((String) BASIC_CHARACTER).get(TYPE_BOOM), 0,0,5,0,true, 100, true));
		TypeAnimationList.put(SkinProvider.BASIC_CHARACTER,basicc);		
	}
	
	private static void loadBasicsAnimationSkinsForEnnemis() throws SlickException
	{
		HashMap<String, Animation> basicE =  new HashMap<String, Animation>();
		basicE.put(TYPE_RUN, new Animation(TypeSheetList.get((String) BASIC_ENNEMI).get(TYPE_RUN), 0,0,5,0,true, 100, true));
		basicE.put(TYPE_BOOM,new Animation(TypeSheetList.get((String) BASIC_ENNEMI).get(TYPE_BOOM), 0,0,5,0,true, 100, true));
	
		TypeAnimationList.put(SkinProvider.BASIC_ENNEMI,basicE);		
	}
	
	private static void loadBasicsAnimationSkinsForWeapon() throws SlickException
	{
		HashMap<String, Animation> basicW =  new HashMap<String, Animation>();
		basicW.put(TYPE_RUN, new Animation(TypeSheetList.get(BASIC_WEAPON).get(TYPE_MAIN), 0,7,0,12,true, 100, true));
		basicW.put(TYPE_LAUNCH, new Animation(TypeSheetList.get(BASIC_WEAPON).get(TYPE_MAIN), 0,0,0,7,true, 100, true));

		HashMap<String, Animation> Gun =  new HashMap<String, Animation>();
		Gun.put(TYPE_RUN, new Animation(TypeSheetList.get(GUN_WEAPON).get(TYPE_MAIN), 0,0,0,0,true, 1000, true));
		
		TypeAnimationList.put(SkinProvider.BASIC_WEAPON,Gun);		
		
		HashMap<String, Animation> AlienGun =  new HashMap<String, Animation>();
		AlienGun.put(TYPE_RUN, new Animation(TypeSheetList.get(ALIEN_WEAPON).get(TYPE_MAIN), 0,0,0,0,true, 1000, true));
		
		TypeAnimationList.put(SkinProvider.ALIEN_WEAPON,AlienGun);		
	}
	
	
	

}
