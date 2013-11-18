package parser;

public class ParserNames {
	private static ParserNames instance= null; 
	
	private ParserNames(){};
	
	public static  ParserNames GetInstance()
	{
		if(instance == null)
			instance = new ParserNames();
		return instance;
	}
	
	public static String LEVEL="Level";
	public static String INFO_LEVEL="infoLevel";
	public static String NAME_TYPE="nameType";
	public static String RESOURCES="Resources";
	public static String RESOURCE="Resource";
	public static String TYPE="Type";
	public static String SPRITE="sprite";
	public static String PATH="Path";
	public static String SIZE_OF_ONE_ITEM="SizeOfOneItem";
	public static String SIZEX="SizeX";
	public static String SIZEY="Size";
	public static String ALPHA_COLOR="AlphaColor";
	public static String ANIMATION="Animation";
	public static String POSITION_BEGIN="PostionBegin";
	public static String POSITION_FINISH="PostionFinish";
	public static String DURATION="duration";
	public static String NO="no";
	public static String STEP="Step";
	public static String ENNEMI="ennemi";
	public static String POSITION="Position";
	public static String IAREF="IaRef";
	public static String IAUnknown="unknown";
	

}
