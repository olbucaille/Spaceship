package parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;






public class ParserLevel {


	public static ArrayList<Resource> listeResource = new ArrayList<Resource>();;
	public static ArrayList<Step> listeStep = new ArrayList<Step>();;

	
	public static ArrayList<Step> levelprovider() {
		try{
			listeStep =  new ArrayList<Step>();
			listeResource = new ArrayList<Resource>();
			
			// création d'une fabrique de documents
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();

			// création d'un constructeur de documents
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();

			// lecture du contenu d'un fichier XML avec DOM
			File xml = new File("C:/Users/olbucaille/Desktop/Level1.xml");
			Document document = constructeur.parse(xml);
			processor( document);
			//traitement du document
			//voir ExempleDOM.zip

		}catch(ParserConfigurationException pce){
			System.out.println("Erreur de configuration du parseur DOM");
			System.out.println("lors de l'appel à fabrique.newDocumentBuilder();");
		}catch(SAXException se){
			System.out.println("Erreur lors du parsing du document");
			System.out.println("lors de l'appel à construteur.parse(xml)");
		}catch(IOException ioe){
			System.out.println("Erreur d'entrée/sortie");
			System.out.println("lors de l'appel à construteur.parse(xml)");
		}
		
		return listeStep;

	}

	public static void processor(Document document)
	{
		

		Element racine = document.getDocumentElement();
		recupResources(racine);
		recupSteps(racine);


	}

	
	public static void recupResources(Element racine)
	{
		String tag;
		tag = ParserNames.RESOURCE;
		NodeList liste = racine.getElementsByTagName(tag);
		for(int i=0; i<liste.getLength(); i++){
			Element elem = (Element)liste.item(i);
			NodeList listeType =  elem.getElementsByTagName(ParserNames.TYPE);


			for(int i1=0; i1<liste.getLength(); i1++){
				Element type = (Element)listeType.item(i1);
				listeResource.add(new Resource(type.getTextContent())); 			
			}

			tag = ParserNames.SPRITE;
			NodeList listeSprite =  elem.getElementsByTagName(ParserNames.SPRITE);
			saveSprite(listeSprite,i);



		}

	}
	private static void saveSprite(NodeList listeSprite, int indexR) {
		String tag = ParserNames.NAME_TYPE;
		for(int i=0; i<listeSprite.getLength(); i++){
			Element sprite = (Element)listeSprite.item(i);

			String nametype  = sprite.getAttribute(tag);
			String path =  sprite.getElementsByTagName(ParserNames.PATH).item(0).getTextContent();
			int  sizeX = Integer.parseInt( sprite.getElementsByTagName(ParserNames.SIZE_OF_ONE_ITEM).item(0).getChildNodes().item(1).getTextContent());
			int sizeY = Integer.parseInt( sprite.getElementsByTagName(ParserNames.SIZE_OF_ONE_ITEM).item(0).getChildNodes().item(3).getTextContent());
			String Alpha =  sprite.getElementsByTagName(ParserNames.ALPHA_COLOR).item(0).getTextContent();

			listeResource.get(indexR).listSprite.add(new Sprite(nametype, path, sizeX, sizeY, Alpha));
			tag = ParserNames.ANIMATION;
			NodeList listeAnimation =  sprite.getElementsByTagName(tag);
			saveAnimation(listeAnimation, indexR,i);


		}


	}

	private static void saveAnimation(NodeList listeAnimation, int indexR, int indexS) {
		String tag = ParserNames.DURATION;
		for(int i=0; i<listeAnimation.getLength(); i++){
			Element animation = (Element)listeAnimation.item(i);

			int  duration = Integer.parseInt(animation.getAttribute(tag));
			int  beginsizeX = Integer.parseInt(animation.getElementsByTagName(ParserNames.POSITION_BEGIN).item(0).getChildNodes().item(1).getTextContent());
			int  beginsizeY = Integer.parseInt(animation.getElementsByTagName(ParserNames.POSITION_BEGIN).item(0).getChildNodes().item(3).getTextContent());
			int  finishsizeX = Integer.parseInt(animation.getElementsByTagName(ParserNames.POSITION_FINISH).item(0).getChildNodes().item(1).getTextContent());
			int  finishsizeY = Integer.parseInt(animation.getElementsByTagName(ParserNames.POSITION_FINISH).item(0).getChildNodes().item(3).getTextContent());

			listeResource.get(indexR).listSprite.get(indexS).animation.duration =  duration;
			listeResource.get(indexR).listSprite.get(indexS).animation.PosBeginX =  beginsizeX;
			listeResource.get(indexR).listSprite.get(indexS).animation.PosBeginY =  beginsizeY;
			listeResource.get(indexR).listSprite.get(indexS).animation.PosFinishX =  finishsizeX;
			listeResource.get(indexR).listSprite.get(indexS).animation.PosFinishY =  finishsizeY;
			listeResource.get(indexR).listSprite.get(indexS).animation.sprite =  listeResource.get(indexR).listSprite.get(indexS);

				
		}

	}
	
	private static void recupSteps(Element racine) {
		NodeList liste = racine.getElementsByTagName(ParserNames.STEP);
		for(int i=0; i<liste.getLength(); i++){
			Element step = (Element)liste.item(i);
		listeStep.add(new Step(Integer.parseInt( step.getAttribute(ParserNames.NO))));
		 ajouterEnnemis(step, i);
		
		}

		
	}

	private static void ajouterEnnemis(Element step, int indexStep) {
		
		NodeList Ennemis = step.getElementsByTagName(ParserNames.ENNEMI);
		for(int i=0; i<Ennemis.getLength(); i++){
			Element ennemi = (Element) Ennemis.item(i);
			
			
			String type = ennemi.getChildNodes().item(1).getTextContent();
			int posX = Integer.parseInt(ennemi.getElementsByTagName(ParserNames.POSITION).item(0).getChildNodes().item(1).getTextContent());
			int posY = Integer.parseInt(ennemi.getElementsByTagName(ParserNames.POSITION).item(0).getChildNodes().item(3).getTextContent());
			String IA = ennemi.getElementsByTagName(ParserNames.IAREF).item(0).getTextContent();
			
			listeStep.get( indexStep).ennemi.add(new Ennemi(type, posX, posY, IA));
			
		}
	}


}
